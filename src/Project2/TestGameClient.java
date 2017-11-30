package Project2;

/**
 * Page should load from either a screen where the player sets up a server (enters port number) or the player chooses to
 * join someone else's game (enters IP and port number)
 *
 * Need to fix every hardcoded ipaddress and port once menus are done
 *
 * */

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import sun.plugin.perf.PluginRollup;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static Project2.InputManager.InputCommands;
import static Project2.InputManager.InputCommands.*;


public class TestGameClient extends BasicGameState{
    // SERVER STUFF
    TestGameServer gameserver;
    private String ipAddress; // client's IP address
    private String serverAddress; // server's IP address
    private int port;
    private boolean listening = false;
    Socket socket;

    // GAME STUFF
    private boolean init = false;
    private Vector screenCenter;
    private int stateId;
    private InputCommands inputCommand;
    private final String WALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    private final String ATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    private ArrayList<Hero> Players;
    private MobList moblist;
    private DoorList doorList;
    private ArrayList<Door> Doors;
    private ArrayList<Mob> Mobs;
    private boolean isIdle = true;
    private Map mapping = null;
    private ArrayList<Mob>mobsToMove;

    // MAP STUFF
    private double x, y;
    private int mapX, mapY;
    public TiledMap map1 = null;
    private final String LEVEL1RSC = "resources/Levels/Level1Remake.tmx";
    private final String TILESHEETRSC = "resources/Levels";


    public TestGameClient(int state_id) {
        this.stateId = state_id;
    }


    @Override
    public int getID() {
        return stateId;
    }

    @Override
    public void init(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        ResourceManager.loadImage(WALKINGSHEETRSC);
        ResourceManager.loadImage(ATTACKINGSHEETRSC);
        Players = new ArrayList<>();
        Mobs = new ArrayList<>();
        Doors = new ArrayList<>();
        mobsToMove = new ArrayList<>();
        screenCenter = (new Vector(container.getWidth()/2,container.getHeight()/2));
        map1 = new TiledMap(LEVEL1RSC, TILESHEETRSC);
        Project2.settings.createTileMapping(map1, 1);
        mapping = Project2.settings.getTilemapping();
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);

        // SERVER STUFF (if client is running server)
        // TODO: determine level number and send as variable in place of stateId
        if (true) { // should evaluate whether or not player is hosting the server
            port = 1234;
            serverAddress = "localhost";
            gameserver = new TestGameServer(stateId, port);
            Thread serverThread = new Thread(() -> gameserver.init(), "GameServer");
            serverThread.start();
        }

        // CLIENT STUFF
        // TODO: connect to servername and port based on user input from previous screen
        // parser for receiving in ip:port form is commented out below
        if (true) {
            serverAddress = "localhost";
            int port = 1234;
            System.out.println("Client: connecting to " + serverAddress + " on port " + port);
            connect();
        }
    }


    @Override
    public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        if (init) {
            // VIEWPORT STUFF
            // TODO: loop through Player array until beingID == socket.getLocalSocketAddress()
            float displaceX, displaceY, worldPosX, worldPosY;
            worldPosX = (int)Players.get(0).getWorldPositionX();
            worldPosY = (int)Players.get(0).getWorldPositionY();
            displaceX = (Players.get(0).getWorldPositionX()-worldPosX)*-32;
            displaceY = (Players.get(0).getWorldPositionY()-worldPosY)*-32;

            map1.render((int)displaceX, (int)displaceY,
                    (int)worldPosX, (int)worldPosY, (int)worldPosX+45, (int)worldPosY+30 );

            
            g.drawString("displaceX: "+displaceX*-1
                    +" displaceY:"+displaceY*-1, 200,200);
            g.drawString("worldX: "+Players.get(0).getWorldPositionX()
                    +"      worldY:"+Players.get(0).getWorldPositionY(), 200,230);
            g.drawString("screenX: "+Players.get(0).getScreenPositionX()
                    +" screenY:"+Players.get(0).getScreenPositionY(), 200,260);

            //grid range
            float xoff = (float)Math.floor(Math.floor(Players.get(0).getWorldPositionX())-5);
            float yoff = (float)Math.floor(Math.floor(Players.get(0).getWorldPositionY())-5);
            Vector offSet = MovementCalc.CalcScreenPosition(
                    Players.get(0).getWorldPosition(),
                    new Vector(xoff, yoff));
            g.drawString("xoff:"+xoff+" yoff:"+yoff, 100, 280);
            for (int i = 0; i < 11; i++){
                for (int j = 0; j < 11; j++){
                    g.drawString("0", (offSet.getX()+16)+(i*32), (offSet.getY()+16)+(j*32));
                }
            }
            g.drawString("Mobs in range: "+mobsToMove.size(),100, 300 );



            // convert all non-controlling player entities world to screen coords
            // only set up to do mob list now
            int viewportX = (int)((Players.get(0).getWorldPositionX()*32.0) - screenCenter.getX());
            int viewportY = (int)((Players.get(0).getWorldPositionY()*32.0) - screenCenter.getY());
            worldToScreen(viewportX, viewportY);



            // render mobs here when we have them
//            System.out.println("moblist size = " + Mobs.size());
            for (int i = 0; i < Mobs.size(); i++) {
                Mobs.get(i).render(g);
            }
            for (int i = 0; i < Doors.size(); i++) {
                Doors.get(i).render(g);
            }
            // ENTITY STUFF
            // render players
            for (int i = 0; i < Players.size(); i++) {
                Players.get(i).render(g);
                g.setColor(Color.red);
                Players.get(i).UpdateHealthBarLocation();
                g.fill(Players.get(i).getHealthBar());

            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        // collect commands
        if (init) {
            Input input = gameContainer.getInput();
            inputCommand = InputManager.ProcessInput(input, stateId);

            // send commands to server, updates will be processed as received
            String inputCmd = inputCommand.toString();
            if (inputCmd == "idle" && !isIdle) {
                isIdle = true;
                inptMsg(inputCommand.toString());
            }
            if (inputCmd != "idle") {
                isIdle = false;
                inptMsg(inputCommand.toString());
            }
            float playerOffX = (float)Math.floor(Math.floor(Players.get(0).getWorldPositionX())-5);
            float playerOffY = (float)Math.floor(Math.floor(Players.get(0).getWorldPositionY())-5);
            Vector playerPosition = new Vector(playerOffX, playerOffY);
            Pathfinding.Dijkstra(mapping, playerPosition);
            for (int i = 0; i < Mobs.size(); i++) {
                float mobX = (float)Math.floor(Mobs.get(i).getWorldPositionX());
                float mobY = (float)Math.floor(Mobs.get(i).getWorldPositionY());
                Vector mobPosition = new Vector(mobX, mobY);
                if (Pathfinding.range(playerPosition, mobPosition) &&
                        !mobsToMove.contains(Mobs.get(i))){
                    if (!Mobs.get(i).IsDead()) {
                        mobsToMove.add(Mobs.get(i));
                    }
                    System.out.println(Mobs.get(i).IsDead());
                } if (Mobs.get(i).IsDead()){
                  mobsToMove.remove(Mobs.get(i));
                } else if (!Pathfinding.range(playerPosition, mobPosition) &&
                        mobsToMove.contains(Mobs.get(i))){
                    mobsToMove.remove(Mobs.get(i));
                }
            }
        }
    }


/** Game Functions */
private void moveEntity(String entity, InputCommands input, Float posX, Float posY) {
    int i = 0;
    // TODO: have some indication if entity is a mob so it loops through correct ArrayList

    if(entity.contains("/")){
        for(Hero hero: Players){
            if(entity.equals(hero.getName())){
                hero.setCommand(input);
                hero.UpdateBeing(input, new Vector(posX, posY));
                break;
            }
        }
    }else if(entity.contains("mob")){
        for(Mob mob: Mobs){
            if(entity.equals(mob.getName())){
                mob.setCommand(input);
                mob.UpdateBeing(input, new Vector(posX, posY));
                break;
            }
        }
    }

//    Players.get(i).UpdateBeing(input, new Vector(posX, posY));
    // need to send input to figure out animation

    // for use when IP is properly stored in player class
//        for (int i = 0; i < Players.size(); i++) {
//            if (Players.get(i).getName() == entity) {
//                Players.get(i).setPosition(posX, posY);
//            }
//        }

    // Call pathfinding
    }

    private void loadLevel(int level) throws SlickException {
        System.out.println("executing loadLevel " + level);
        moblist = new MobList();
        doorList = new DoorList();
        // should match info switch statement in TestGameServer constructor
        switch (level) {
            case 1:
                map1 = new TiledMap(LEVEL1RSC, TILESHEETRSC);
                mapX = 90;
                mapY = 104;
                Mobs = moblist.getMobList(1);
                Doors = doorList.getDoorList(1);
                break;
            default:
                System.out.println("Client: unknown level");
                break;
        }
    }


    private void addPlayer(String playerID, float xPos, float yPos) {
//        System.out.println("executing addPlayer");
        // TODO: Add playerID and ClassID to Basic Being constructor or player constructor, whatever gets used here
        Hero hero1 = new Hero(new Vector(xPos,yPos),false, playerID);
        Players.add(hero1);
    }

    private InputCommands getCommand(String rawCommand) {
        InputCommands inputCommand = null;
        switch(rawCommand) {
            case "up": inputCommand = up; break;
            case "down": inputCommand = down; break;
            case "left": inputCommand = left; break;
            case "right": inputCommand = right; break;
            case "ulDiag": inputCommand = ulDiag; break;
            case "dlDiag": inputCommand = dlDiag; break;
            case "urDiag": inputCommand = urDiag; break;
            case "drDiag": inputCommand = drDiag; break;
            case "attack": inputCommand = attack; break;
            case "idle": inputCommand = idle; break;
            case "death": inputCommand = death; break;
        }
        return inputCommand;
    }


    private void worldToScreen(int viewportX, int viewportY) {

        for(Hero hero: Players){
            if(!Project2.getSettings().getIpAddress().equals(hero.getName())){
                int entityX = (int)(hero.getWorldPositionX()*32.0);
                int entityY = (int)(hero.getWorldPositionY()*32.0);
                int newX = entityX - viewportX;
                int newY = entityY - viewportY;
                hero.setPosition(newX, newY);
            }
        }
        for (Mob mob:Mobs) {
            int entityX = (int)(mob.getWorldPositionX()*32.0);
            int entityY = (int)(mob.getWorldPositionY()*32.0);
            int newX = entityX - viewportX;
            int newY = entityY - viewportY;
            mob.setPosition(newX, newY);
        }
        for (int i = 0; i < Doors.size(); i++){
            int entityX = (int)(Doors.get(i).getWorldPositionX()*32.0);
            int entityY = (int)(Doors.get(i).getWorldPositionY()*32.0);
            int newX = entityX - viewportX;
            int newY = entityY - viewportY;
            Doors.get(i).setPosition(newX, newY);
        }
    }


/** Client Functions */
    public boolean connect() {
        // TCP
        try {
            // open client socket
            socket = new Socket(ipAddress, port);
            Project2.settings.setIpAddress(socket.getLocalSocketAddress().toString());
            // send message to initialize player on server (INIT PLAYERIP CLASS)
            send("INIT " + socket.getLocalSocketAddress() + " " + 1);

            // set up listening thread for client listener
            listening = true;
            Thread clientThread = new Thread(() -> listen(), "ClientListener");
            clientThread.start();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Client: client cannot connect");
            return false;
        }
        return true;
    }

    private void listen() {
        // listen while socket is open
        while(listening) {
            try {
                InputStream inFromServer = socket.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                processMessage(in);
//                System.out.println("Client: server sent message: " + in.readUTF());
//                send("Client: still connected");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Client: server has a problem listening");
                listening = false;
            } catch (SlickException s) {
                s.printStackTrace();
                System.out.println("Client: problem in listen() calling processMessage()");
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // send input packet
    private void inptMsg(String cmd) {
        String msg = "INPT " + socket.getLocalSocketAddress();
        msg += " " + cmd;
        send(msg);
    }


    // process incoming messages from clients
    private void processMessage(DataInputStream in) throws SlickException {
        String msg = null;
        // converts data steam into string
        try {
            msg = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        Message Structures:
        INIT levelID playerIP xPos yPos
        PLCH playerIP command posX posY
         */
        // sets empty space as delimiter
        String delims = "[ ]";
        // splits message into tokens at every space
        String[] tokens = msg.split(delims);
        String command = tokens[0];

        switch (command) {
            case "INIT":
//                System.out.println("Client: got INIT response ");
                loadLevel(Integer.parseInt(tokens[1]));
                addPlayer(tokens[2], Float.parseFloat(tokens[3]), Float.parseFloat(tokens[4]));
//                addPlayer("/animalCrackers", 92f, 105f);
                init = true;
                break;
            case "UPDT":
//                System.out.println("Client: got UPDT response ");
                // loop through tokens by fours (entity, input, velX, velY);
                for (int i = 1; i < tokens.length; i += 4) {
//                    System.out.println("UPDT loop: entering; length: " + tokens.length);
                    InputCommands input = getCommand(tokens[i+1]);
                    moveEntity(tokens[i], input, Float.parseFloat(tokens[i+2]), Float.parseFloat(tokens[i+3]));
//                    System.out.println("UPDT loop: i+4 = " + (i+4) + "; tokens.length = " + tokens.length);
                }
                break;
            default:
                System.out.println("Server: unknown message received");
                break;
        }
    }


    private void send(String msg) {
        // send an outgoing message
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    // takes host in format "127.0.0.1:5000"
    public Client(String host) {
        String[] hostSplit = host.split(":");
        if (hostSplit.length != 2) {
            System.out.println("Invalid host string: Client constructor");
            return;
        }
        ipAddress = hostSplit[0];
        try {
            port = Integer.parseInt(hostSplit[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Invalid port: Client constructor");
        }

    }

    // takes address in form "127.0.0.1"
    public Client(String address, int port) {
        this.ipAddress = address;
        this.port = port;
    }
    */

}
