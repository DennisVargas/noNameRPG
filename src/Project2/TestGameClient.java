package Project2;

/**
 * Page should load from either a screen where the player sets up a server (enters port number) or the player chooses to
 * join someone else's game (enters IP and port number)
 *
 * */

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static Project2.InputManager.InputCommands;


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
    private int stateId;
//    private BasicBeing being1;
    private InputCommands inputCommand;
    private final String WALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    private final String ATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    private ArrayList<BasicBeing> Players;

    // MAP STUFF
    private double x, y;
    private int mapX, mapY;
    //    public TiledMap mapTest = null;
    public TiledMap map1 = null;
    //    private final String TESTLEVELRSC = "resources/Levels/testlevel.tmx";
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
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        ResourceManager.loadImage(WALKINGSHEETRSC);
        ResourceManager.loadImage(ATTACKINGSHEETRSC);
        Players = new ArrayList<>();
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);

        // SERVER STUFF (if client is running server)
        // TODO: determine level number and send as variable
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
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        if (init) {
            // VIEWPORT STUFF
            int displaceX, displaceY, worldPosX, worldPosY;

            //  render the map using the client displacement from tile center and current world position
            displaceX = (int)Players.get(0).getCurrentDisplacementX(); displaceY = (int)Players.get(0).getCurrentDisplacementY();
            worldPosX = (int)Players.get(0).getWorldPositionX(); worldPosY = (int)Players.get(0).getWorldPositionY();

            map1.render(displaceX-32, displaceY-32, worldPosX, worldPosY, worldPosX+45, worldPosY+30 );
            g.drawString("Test Game Client ", 740,360);

            // ENTITY STUFF
            // render players
            for (int i = 0; i < Players.size(); i++) {
                Players.get(i).render(g);
            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (init) {
//            System.out.println("executing update");
            Input input = gameContainer.getInput();
            inputCommand = InputManager.ProcessInput(input, stateId);
            ProcessInputCommand(inputCommand);
        }
    }

    private void ProcessInputCommand(InputCommands inputCommand) {
        // other things to do in playstate for input various things will need this input?
        //  pass the inputCommand to the Being and it will then react to its command.
        //  they will check their own next move for collision and will set the next move in
        //  preparation for their update call.
        Players.get(0).GenerateNextMove(inputCommand);
        //  update the beings position and health inside of
        //  private methods.
        Players.get(0).UpdateBeingPosition();
    }

    private void loadLevel(int level) throws SlickException {
        System.out.println("executing loadLevel " + level);

        // should match switch statement in TestGameServer constructor
        switch (level) {
            case 1:
                map1 = new TiledMap(LEVEL1RSC, TILESHEETRSC);
                mapX = 90;
                mapY = 104;
                break;
            default:
                System.out.println("Client: unknown level");
                break;
        }
    }

    private void addPlayer(String playerID, float xPos, float yPos) {
//        System.out.println("executing addPlayer");
        // TODO: Add playerID and ClassID to Basic Being constructor or player constructor, whatever gets used here
        BasicBeing being1 = new BasicBeing(new Vector(640,360), new Vector(xPos, yPos), ResourceManager.getSpriteSheet(WALKINGSHEETRSC,32,32),
                ResourceManager.getSpriteSheet(ATTACKINGSHEETRSC,32,32));
        Players.add(being1);
    }


/** Client Functions */
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
    public boolean connect() {
        // TCP
        try {
            // open client socket
            socket = new Socket(ipAddress, port);
            // create output stream and send initial message to server
            OutputStream outToServer = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
//            out.writeUTF("Hello from " + socket.getLocalSocketAddress());
            out.writeUTF("INIT " + socket.getLocalSocketAddress() + " " + 1);

            // set up listening thread for client listener
            listening = true;
            Thread clientThread = new Thread(() -> listen(), "ClientListener");
            clientThread.start();

//            InputStream inFromServer = socket.getInputStream();
//            DataInputStream in = new DataInputStream(inFromServer);
//            System.out.println("Server says " + in.readUTF());
//            socket.close();
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
            // TCP
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

    // process incoming messages from clients
    public void processMessage(DataInputStream in) throws SlickException {
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
        INPT playerIP true false false true false
         */
        // sets empty space as delimiter
        String delims = "[ ]";
        // splits message into tokens at every space
        String[] tokens = msg.split(delims);

        switch (tokens[0]) {
            case "INIT":
                System.out.println("Client: got INIT response ");
                loadLevel(Integer.parseInt(tokens[1]));
                addPlayer(tokens[2], Float.parseFloat(tokens[3]), Float.parseFloat(tokens[4]));
                init = true;
                break;
            case "INPT":
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

}
