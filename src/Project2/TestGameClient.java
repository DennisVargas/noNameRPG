package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;
import org.newdawn.slick.tiled.TiledMap;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private List<Hero> Players;
    private MobList moblist;
    private DoorList doorList;
    private KeyList keyList;
    private CrateList crateList;
    private List<Door> Doors;
    private List<Crate> Crates;
    private List<Mob> Mobs;
    private ArrayList<Ball> MobBalls;
    private ArrayList<Ball> HeroBalls;
    private List<Key> Keys;
    private List<Money> MoneyDrops;
    private List<Health> HealthDrops;
    private boolean isIdle = true;
    private Map mapping = null;
    private ArrayList<Mob>mobsToMove;
    private int playersMoney;
    private int playersKey;
    private int playersLives = 3;
    public Music music;
    public Sound coin;
    boolean win;

    private int temp = 0;

    // MAP STUFF
    private double x, y;
    private int mapX, mapY;
    public TiledMap map = null;
    public TiledMap map1 = null;
    public TiledMap map2 = null;


    public TestGameClient(int state_id) {
        this.stateId = state_id;
        playersMoney = 0;
    }


    @Override
    public int getID() {
        return stateId;
    }

    @Override
    public void init(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        music = new Music("resources/Sounds/background_music.wav");
        coin = new Sound("resources/Sounds/enemy_dropped_coin2.wav");

        Players = Collections.synchronizedList(new ArrayList<Hero>());
        Mobs = Collections.synchronizedList(new ArrayList<>());
        MobBalls = new ArrayList<Ball>();
        HeroBalls = new ArrayList<Ball>();
        Doors = Collections.synchronizedList(new ArrayList<Door>());
        Crates = Collections.synchronizedList(new ArrayList<Crate>());
        MoneyDrops = Collections.synchronizedList(new ArrayList<Money>());
        HealthDrops = Collections.synchronizedList(new ArrayList<Health>());
        mobsToMove = new ArrayList<>();
//        MoneyDrops = new ArrayList<>();
        Keys = Collections.synchronizedList(new ArrayList<Key>());
        screenCenter = (new Vector(container.getWidth()/2,container.getHeight()/2));
        map1 = new TiledMap(Project2.LEVEL1RSC, Project2.TILESHEETRSC);
        map2 = new TiledMap(Project2.LEVEL2RSC, Project2.TILESHEETRSC);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        Players.clear();
        coin.stop();
        music.loop();
        if (!Project2.settings.getJoining()) { // launch server if hosting or singleplayer
            // SERVER STUFF (if client is running server)
            // TODO: determine level number and send as variable in place of stateId
            serverAddress = "localhost";
            port = Project2.settings.getPort();
            gameserver = new TestGameServer(stateId, port);
            Thread serverThread = new Thread(() -> gameserver.init(), "GameServer");
            serverThread.start();
            // CLIENT STUFF
            System.out.println("Host/Single Client: connecting to " + serverAddress + " on port " + port);
            connect(game);
        } else {
            // if joining, pull server info out of settings
            // CLIENT STUFF
            serverAddress = Project2.settings.getserverIP();
            port = Project2.settings.getPort();
            System.out.println("Joining Client: connecting to " + serverAddress + " on port " + port);
            connect(game);
        }
    }


    @Override
    public synchronized void render(GameContainer container, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        int viewportX = 0; int viewportY = 0;
        if (init) {
            // VIEWPORT STUFF
            float displaceX, displaceY, worldPosX, worldPosY;
            for (int i = 0; i < Players.size(); i++) {
                if (Players.get(i).getName().equals(socket.getLocalSocketAddress().toString())) {
                    worldPosX = (int) Players.get(i).getWorldPositionX();
                    worldPosY = (int) Players.get(i).getWorldPositionY();
                    displaceX = (Players.get(i).getWorldPositionX() - worldPosX) * -32;
                    displaceY = (Players.get(i).getWorldPositionY() - worldPosY) * -32;
                    map.render((int)displaceX, (int)displaceY,
                            (int)worldPosX, (int)worldPosY, (int)worldPosX+45, (int)worldPosY+30 );

//                    g.drawString("player name: "+Players.get(i).getName(), 200,170);
//                    g.drawString("balls: "+ MobBalls.size(), 200,170);
//                    g.drawString("displaceX: "+displaceX*-1 + " displaceY:"+displaceY*-1, 200,200);
//                    g.drawString("worldX: "+Players.get(i).getWorldPositionX() + "      worldY:"+Players.get(i).getWorldPositionY(), 200,230);
//                    g.drawString("screenX: "+Players.get(i).getScreenPositionX() + " screenY:"+Players.get(i).getScreenPositionY(), 200,260);


//            g.drawString("displaceX: "+displaceX*-1
//                    +" displaceY:"+displaceY*-1, 200,200);
//            g.drawString("worldX: "+Players.get(0).getWorldPositionX()
//                    +"      worldY:"+Players.get(0).getWorldPositionY(), 200,230);
//            g.drawString("screenX: "+Players.get(0).getScreenPositionX()
//                    +" screenY:"+Players.get(0).getScreenPositionY(), 200,260);


            /*g.drawString("displaceX: "+displaceX*-1
                    +" displaceY:"+displaceY*-1, 100,200);
            g.drawString("worldX: "+Players.get(0).getWorldPositionX()
                    +"      worldY:"+Players.get(0).getWorldPositionY(), 100,230);
            g.drawString("screenX: "+Players.get(0).getScreenPositionX()
                    +" screenY:"+Players.get(0).getScreenPositionY(), 100,260);
    */
                //<editor-fold desc ="Grid STUFF do NOT touch"
                    float xoff = (float)Math.floor(Math.floor(Players.get(0).getWorldPositionX())-5);
                    float yoff = (float)Math.floor(Math.floor(Players.get(0).getWorldPositionY())-5);
                    Vector offSet = MovementCalc.CalcScreenPosition(
                            Players.get(0).getWorldPosition(),
                            new Vector(xoff, yoff));
                    if (temp == 1){
                        for (int x = 0; x < 11; x++){
                            for (int j = 0; j < 11; j++){
                                Vector temp = worldOffset(xoff, yoff);
                                int cost = mapping.getTileCost((int)temp.getX()+x, (int)temp.getY()+j);
                                g.setColor(Color.white);
                                g.drawString(String.valueOf(cost), (offSet.getX())+(x*32), (offSet.getY())+(j*32));
                            }
                        }
                    }
                    if (temp == 2){
                        for (int x = 0; x < 11; x++){
                            for (int j = 0; j < 11; j++){
                                float distance = Pathfinding.distanceFromPlayer[(int)xoff+x][(int)yoff+j];
                                g.setColor(Color.white);
                                g.drawString(String.valueOf((int)distance), (offSet.getX())+(x*32), (offSet.getY())+(j*32));
                            }
                        }
                    }
                    g.setColor(Color.green);
            //</editor-fold
            //g.drawString("Mobs in range: "+mobsToMove.size(),100, 300 );
            //g.drawString("Players Account: "+playersMoney, 200,200);
                    // only set up to do mob list now
                    viewportX = (int)((Players.get(i).getWorldPositionX()*32.0) - screenCenter.getX());
                    viewportY = (int)((Players.get(i).getWorldPositionY()*32.0) - screenCenter.getY());

                }// if the playing player
            }// for each player in players

            // convert all non-controlling player entities world to screen coords
            worldToScreen(viewportX, viewportY);

            // render mobs here when we have them
//            System.out.println("moblist size = " + Mobs.size());
            for (int i = 0; i < Mobs.size(); i++) {
                Mobs.get(i).render(g);
            }
            for (int i = 0; i < Doors.size(); i++) {
                Doors.get(i).render(g);
            }
            for (int i = 0; i < Crates.size(); i++) {
                Crates.get(i).render(g);
            }
            for (int i = 0; i < MoneyDrops.size(); i++)
                MoneyDrops.get(i).render(g);
            for (int i = 0; i < HealthDrops.size(); i++)
                HealthDrops.get(i).render(g);
            for (int i = 0; i < MobBalls.size(); i++) {
                g.setColor(Color.red);
                g.fill(MobBalls.get(i).ball);
            }
            for (int i = 0; i < HeroBalls.size(); i++) {
                g.setColor(Color.white);
                g.fill(HeroBalls.get(i).ball);
            }
            for (int i = 0; i < Keys.size(); i++)
                Keys.get(i).render(g);
            // ENTITY STUFF
            // render players
            for (int i = 0; i < Players.size(); i++) {
                Players.get(i).render(g);
                g.setColor(Color.red);
                Players.get(i).UpdateHealthBarLocation();
                g.fill(Players.get(i).getHealthBar());


//
                if(Players.get(i).getCommand().equals(InputCommands.death)) {
                    g.drawString("You Have Died", Project2.WIDTH / 2f-60, Project2.HEIGHT / 2f);
                    if(playersLives>0)
                        g.drawString("Awaiting Resurection. Lives Remaining: "+playersLives, Project2.WIDTH / 2f-160,Project2.HEIGHT / 2f+20 );
                    else
                        g.drawString("NO RESURRECTION. Lives Remaining: " + playersLives, Project2.WIDTH / 2f-160,Project2.HEIGHT / 2f+20 );
                }

            }
            g.setColor(Color.black);
            g.fillRect(0,0,80,50);
            g.setColor(Color.white);
            g.drawRect(0,0,80, 50);
            g.setColor(Color.yellow);
            g.drawImage(ResourceManager.getImage(Project2.MONEYUIRSC),0,0);
            g.drawString(playersMoney+"/500", 16,0);
            g.setColor(Color.green);
            g.drawImage(ResourceManager.getImage(Project2.KEYUIRSC), 0, 18);
            g.drawString(Integer.toString(playersKey), 16, 16);
            g.setColor(Color.red);
            g.drawImage(ResourceManager.getImage(Project2.LIVESUIRSC),0,34);
            g.drawString(Integer.toString(playersLives), 16, 32);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        // collect commands
        if(win) {
            win = false;
            stateBasedGame.enterState(Project2.CONGRATULATIONS);
            return;
        }
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
            if (input.isKeyPressed(Input.KEY_F12))
                send("SKIP ALL");
            if (input.isKeyPressed(Input.KEY_F11))
                send("ADDKEYS ALL");
            float playerOffX = (float)Math.floor(Players.get(0).getWorldPositionX());
            float playerOffY = (float)Math.floor(Players.get(0).getWorldPositionY());
            Vector playerPosition = new Vector(playerOffX, playerOffY);
            //constant player graph update
            //Pathfinding.Dijkstra(mapping, playerPosition);
            //<editor-fold desc="Mobs in range"
            /*for (int i = 0; i < Mobs.size(); i++) {
                float mobX = (float)Math.floor(Mobs.get(i).getWorldPositionX());
                float mobY = (float)Math.floor(Mobs.get(i).getWorldPositionY());
                Vector mobPosition = new Vector(mobX, mobY);
                if (Pathfinding.range(playerPosition, mobPosition) &&
                        !mobsToMove.contains(Mobs.get(i))){
                    if (!Mobs.get(i).IsDead()) {
                        mobsToMove.add(Mobs.get(i));
                    }
                } if (Mobs.get(i).IsDead()){
                  mobsToMove.remove(Mobs.get(i));
                } else if (!Pathfinding.range(playerPosition, mobPosition) &&
                        mobsToMove.contains(Mobs.get(i))){
                    mobsToMove.remove(Mobs.get(i));
                }
            }*/
            //</editor-fold


            //<editor-fold desc="Debug printout"
            if (input.isKeyPressed(Input.KEY_1)){
                temp = 1;
            }
            if (input.isKeyPressed(Input.KEY_2)){
                temp = 2;
            }
            if (input.isKeyPressed(Input.KEY_3)){
                temp = 3;
            }
            //</editor-fold
        }
    }


/** Game Functions */

private synchronized void moveEntity(String entity, InputCommands input, Float posX, Float posY, String playerBool) {
        if(entity.contains("/")){
            boolean found = false;
            for (int i=0; i < Players.size(); i++) {
                if(entity.equals(Players.get(i).getName())){
                    found = true;
                    if (input == rm) {
                        Players.remove(i);
//                        System.out.println("Client removed player, player size = " + Players.size());
                    }
                    else {
//                        System.out.println("client:setcommand" + input);
                        Players.get(i).setCommand(input);
                        Players.get(i).UpdateBeing(input, new Vector(posX, posY));
//                        System.out.println("Client: updated player: " + entity + " to " + Float.toString(posX) + ", " + Float.toString(posY));
                    }
                    break;
                }
            }
            if (!found)
                    addPlayer(entity, posX, posY, playerBool);
        }else if(entity.contains("mob")){
            for(BasicBeing mob: Mobs){
                if(entity.equals(mob.getName())){
                    mob.UpdateBeing(input, new Vector(posX, posY));
                    break;
                }
            }
        } else if(entity.contains("mball")){
            boolean found = false;
            for (int i=0; i < MobBalls.size(); i++) {
                if(entity.equals(MobBalls.get(i).getName())){
                    found = true;
                    if (input == rm) {
                        MobBalls.remove(i);
                    }
                    else {
                        MobBalls.get(i).setWorldPosition(new Vector(posX, posY));
                        MobBalls.get(i).ball.setLocation(MobBalls.get(i).getWorldPositionX(), MobBalls.get(i).getWorldPositionY());
                    }
                    break;
                }
            }
            if (!found)
                MobBalls.add(new Ball(posX, posY, entity));
        } else if(entity.contains("hball")){
                boolean found = false;
                for (int i=0; i < HeroBalls.size(); i++) {
                    if(entity.equals(HeroBalls.get(i).getName())){
                        found = true;
                        if (input == rm) {
                            HeroBalls.remove(i);
                        }
                        else {
                            HeroBalls.get(i).setWorldPosition(new Vector(posX, posY));
                            HeroBalls.get(i).ball.setLocation(HeroBalls.get(i).getWorldPositionX(), HeroBalls.get(i).getWorldPositionY());
                        }
                        break;
                    }
                }
                if (!found)
                    HeroBalls.add(new Ball(posX, posY, entity));
            }
    }

    private void loadLevel(int level) throws SlickException {
//        System.out.println("executing loadLevel " + level);
        moblist = new MobList();
        doorList = new DoorList();
        keyList = new KeyList();
        crateList = new CrateList();
        // should match info switch statement in TestGameServer constructor
//        map1 = new TiledMap(Project2.LEVEL1RSC, Project2.TILESHEETRSC);
        switch (level) {
            case 1:
                map = map1;
//                map1 = new TiledMap(Project2.LEVEL1RSC, Project2.TILESHEETRSC);
                mapX = 45;
                mapY = 100;
                Mobs = moblist.getMobList(1);
                Doors = doorList.getDoorList(1);
                Keys = keyList.getKeyList(1);
                Crates = crateList.getCrateList(1);
                mapping = Project2.settings.getTilemapping();
                break;
            case 2:
                map = map2;
//                map1 = new TiledMap(Project2.LEVEL2RSC, Project2.TILESHEETRSC);
                mapX = 86;
                mapY = 78;
                Mobs = moblist.getMobList(2);
                Doors = doorList.getDoorList(2);
                Keys = keyList.getKeyList(2);
                Crates = crateList.getCrateList(2);
                mapping = Project2.settings.getTilemapping();
                break;
            default:
                System.out.println("Client: unknown level");
                break;
        }
    }


    private void addPlayer(String playerID, float xPos, float yPos, String typeString) {
    boolean type = false;
    if (typeString.equalsIgnoreCase("true"))
        type = true;
//        System.out.println("Adding Player at " + xPos + ", " + yPos);
        boolean found = false;
        for (int i=0; i < Players.size(); i++) {
            if (playerID.equals(Players.get(i).getName())) {
                found = true;
            }
        }
        if (!found) {
            Hero hero = new Hero(new Vector(xPos, yPos), type, playerID); // melee
//        Hero hero = new Hero(new Vector(xPos,yPos), true, playerID);
            Players.add(hero);
        }

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
            case "rm": inputCommand = rm; break;
        }
        return inputCommand;
    }


    private void worldToScreen(int viewportX, int viewportY) {
        for (int i = 0; i < Players.size(); i++) {
            if (!Players.get(i).getName().equals(socket.getLocalSocketAddress().toString())) {
//                System.out.println("Updating screen position of " + Players.get(i).getName());
                int entityX = (int)(Players.get(i).getWorldPositionX()*32.0);
                int entityY = (int)(Players.get(i).getWorldPositionY()*32.0);
                int newX = entityX - viewportX;
                int newY = entityY - viewportY;
                Players.get(i).setPosition(newX, newY);
            }
        }

        for (int i = 0; i < Mobs.size(); i++) {
            int entityX = (int)(Mobs.get(i).getWorldPositionX()*32.0);
            int entityY = (int)(Mobs.get(i).getWorldPositionY()*32.0);
            int newX = entityX - viewportX;
            int newY = entityY - viewportY;
            Mobs.get(i).setPosition(newX, newY);
        }
        for (int i = 0; i < Doors.size(); i++){
            int entityX = (int)(Doors.get(i).getWorldPositionX()*32.0);
            int entityY = (int)(Doors.get(i).getWorldPositionY()*32.0);
            int newX = entityX - viewportX;
            int newY = entityY - viewportY;
            Doors.get(i).setPosition(newX, newY);
        }
        for (int i = 0; i < Crates.size(); i++) {
            int entityX = (int)(Crates.get(i).getWorldPositionX()*32.0);
            int entityY = (int)(Crates.get(i).getWorldPositionY()*32.0);
            int newX = entityX - viewportX;
            int newY = entityY - viewportY;
            Crates.get(i).setPosition(newX, newY);
        }
        for (int i = 0; i < MoneyDrops.size(); i++){
            int entityX = (int)(MoneyDrops.get(i).getWorldPositionX()*32.0);
            int entityY = (int)(MoneyDrops.get(i).getWorldPositionY()*32.0);
            int newX = entityX - viewportX;
            int newY = entityY - viewportY;
            MoneyDrops.get(i).setPosition(newX, newY);
        }
        for (int i = 0; i < HealthDrops.size(); i++){
            int entityX = (int)(HealthDrops.get(i).getWorldPositionX()*32.0);
            int entityY = (int)(HealthDrops.get(i).getWorldPositionY()*32.0);
            int newX = entityX - viewportX;
            int newY = entityY - viewportY;
            HealthDrops.get(i).setPosition(newX, newY);
        }
        for (int i = 0; i < MobBalls.size(); i++){
            int entityX = (int)(MobBalls.get(i).getWorldPositionX()*32.0);
            int entityY = (int)(MobBalls.get(i).getWorldPositionY()*32.0);
            int newX = entityX - viewportX;
            int newY = entityY - viewportY;
            MobBalls.get(i).setPosition(newX, newY);
            MobBalls.get(i).ball.setLocation(newX, newY);
        }
        for (int i = 0; i < HeroBalls.size(); i++){
            int entityX = (int)(HeroBalls.get(i).getWorldPositionX()*32.0);
            int entityY = (int)(HeroBalls.get(i).getWorldPositionY()*32.0);
            int newX = entityX - viewportX;
            int newY = entityY - viewportY;
            HeroBalls.get(i).setPosition(newX, newY);
            HeroBalls.get(i).ball.setLocation(newX, newY);
        }
        for (int i = 0; i < Keys.size(); i++){
            int entityX = (int)(Keys.get(i).getWorldPositionX()*32.0);
            int entityY = (int)(Keys.get(i).getWorldPositionY()*32.0);
            int newX = entityX - viewportX;
            int newY = entityY - viewportY;
            Keys.get(i).setPosition(newX, newY);
        }
    }

    private Vector worldOffset(float x, float y){
        return new Vector(x+20, y+11);
    }

/** Client Functions */
    public boolean connect(StateBasedGame game) {
        // TCP
        try {
            // open client socket
            socket = new Socket(serverAddress, port);
            Project2.settings.setIpAddress(socket.getLocalSocketAddress().toString());

            // send message to initialize player on server (INIT PLAYERIP CLASS)
//            System.out.println("Sending init message to server");
            send("INIT " + socket.getLocalSocketAddress() + " " + Project2.settings.getRanged());

            // set up listening thread for client listener
            listening = true;
            Thread clientThread = new Thread(() -> listen(game), "ClientListener");
            clientThread.start();

        } catch (IOException e) {
            e.printStackTrace();
            // TODO: return client to ip entry screen if could not connect to server
            System.out.println("Client: client cannot connect");
            return false;
        }
        return true;
    }

    private void listen(StateBasedGame game) {
        // listen while socket is open
        while(listening) {
            try {
                InputStream inFromServer = socket.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                if (in != null) {
                    processMessage(in);
                }
//                System.out.println("Client: server sent message: " + in.readUTF());
//                send("Client: still connected");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Client: server has a problem listening");
                listening = false;
            } catch (SlickException s) {
                s.printStackTrace();
                System.out.println("Client: problem in listen() calling processMessage()");
                listening = false;
            }
        }
        try {
            socket.close();
            System.out.println("Client: Host has closed connection");
            game.enterState(Project2.DISCONNECTED, new EmptyTransition(), new HorizontalSplitTransition() );
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
        String msg = "";
        // converts data steam into string
        try {
            msg = in.readUTF();
        } catch (IOException e) {
            listening = false;
            e.printStackTrace();
            System.out.println("Error in processMessage");
        }

        /*
        Message Structures:
        INIT levelID playerIP xPos yPos
        PLCH playerIP command posX posY
         */
        // sets empty space as delimiter
        String delims = "[ ]";
        // splits message into tokens at every space
        if (msg.length() >= 0) {
            String[] tokens = msg.split(delims);
            String command = tokens[0];
//            System.out.println("Client " + msg);

            switch (command) {
                case "INIT":
    //                System.out.println("Client: got INIT response ");
                    loadLevel(Integer.parseInt(tokens[1]));
                    addPlayer(tokens[2], Float.parseFloat(tokens[3]), Float.parseFloat(tokens[4]), tokens[5]);
    //                addPlayer("/animalCrackers", 92f, 105f);
                    init = true;
                    break;
                case "UPDT":
    //                System.out.println("Client: got UPDT response ");
                    // loop through tokens by fours (entity, input, velX, velY);
                    for (int i = 1; i < tokens.length; i += 5) {
    //                    System.out.println("UPDT loop: entering; length: " + tokens.length);
                        InputCommands input = getCommand(tokens[i + 1]);
//                        if(tokens[i].equals(Project2.getSettings().getIpAddress()))
//                            System.out.println("client Command: "+input);
                        moveEntity(tokens[i], input, Float.parseFloat(tokens[i + 2]), Float.parseFloat(tokens[i + 3]), tokens[i + 4]);
    //                    System.out.println("UPDT loop: i+4 = " + (i+4) + "; tokens.length = " + tokens.length);
                    }
                    break;
                case "UPDTHLTH":
//                    System.out.println("Client: got UPDTHLTH response");

                    if(Project2.getSettings().getIpAddress().equals(tokens[1])) {
//                        System.out.println("This is the player: "+tokens[1]);
//                        System.out.println("Health value " + tokens[2]);
                        for(Hero hero:Players){
                            if(hero.getName().equals(Project2.getSettings().getIpAddress())){
                                hero.setHealth(Float.parseFloat(tokens[2]) );}
                        }
                    }
                    else
//                        System.out.println("Not the player "+tokens[1]);
                    break;
                case "DROPM":
                    for (int i = 1; i < tokens.length; i++) {
                        if (tokens[i].contains("money")) {
                            MoneyDrops.add(new Money(new Vector(Float.parseFloat(tokens[i + 1]), (Float.parseFloat(tokens[i + 2]))), tokens[i], Integer.parseInt(tokens[i + 3])));
                            coin.play();
                        }
                    }
                    break;
                case "PCKUPM":
                    playersMoney = Integer.parseInt(tokens[1]);
                    for (int i = 3; i < tokens.length; i++) {
                        for (int j = 0; j < MoneyDrops.size(); j++) {
                            if (MoneyDrops.get(j).getName().equals(tokens[i])) {
                                MoneyDrops.remove(MoneyDrops.get(j));
                            }
                        }
                    }
                    break;
                case "DROPH":
                    for (int i = 1; i < tokens.length; i++) {
                        if (tokens[i].contains("health"))
                            HealthDrops.add(new Health(new Vector(Float.parseFloat(tokens[i + 1]), (Float.parseFloat(tokens[i + 2]))), tokens[i], Integer.parseInt(tokens[i + 3])));
                    }
                    break;
                case "PCKUPH":
                    Players.get(0).setHealth(Players.get(0).getHealth() + 1);
                    for (int i = 2; i < tokens.length; i++) {
                        for (int j = 0; j < HealthDrops.size(); j++) {
                            if (HealthDrops.get(j).getName().equals(tokens[i])) {
                                HealthDrops.remove(HealthDrops.get(j));
                            }
                        }
                    }
                    break;
                case "PCKUPK":
                    playersKey = Integer.parseInt(tokens[1]);
                    for(int i = 2; i < tokens.length; i++){
                        for(int j = 0; j < Keys.size(); j++) {
                            if (Keys.get(j).getName().equals(tokens[i])){
                                Keys.remove(Keys.get(j));
                            }
                        }
                    }
                    break;
                case "OPEND":
                    playersKey = Integer.parseInt(tokens[1]);
                    for(int i = 2; i < tokens.length; i++){
                        for(int j = 0; j < Doors.size(); j++) {
                            if (Doors.get(j).getName().equals(tokens[i])){
                                Doors.remove(Doors.get(j));
                            }
                        }
                    }
                    break;
                case "RMVC":
                    for(int i = 1; i < tokens.length; i++) {
                        for(int j = 0; j < Crates.size(); j++) {
                            if (Crates.get(j).getName().equals(tokens[i])){
                                Crates.remove(Crates.get(j));
                            }
                        }
                    }
                    break;
                case "TRNS2":
                    System.out.print("Here");
                    Mobs.clear();
                    Doors.clear();
                    Keys.clear();
                    Crates.clear();
                    MoneyDrops.clear();
                    HealthDrops.clear();
                    loadLevel(2);
                    for(int i = 0; i < Players.size(); i++) {
                        Players.get(i).setWorldPosition(new Vector(87 + i, 78));
                    }
                    System.out.print("Done");
                    break;
                case "LIVES":
                    playersLives = Integer.parseInt(tokens[1]);
                    break;
                case "WIN":
                    loadLevel(1);
                    win = true;
                default:
                    System.out.println("Client: unknown message received:"+msg);
                    break;
            }
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
