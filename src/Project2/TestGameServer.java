package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import static Project2.InputManager.InputCommands;

import Networking.Server;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import static Project2.InputManager.InputCommands.*;


public class TestGameServer {
    // SERVER STUFF
    private int port;
    private Thread listeningThread;
    private boolean listening = false;
    private ServerSocket socket;
    private Socket server;

    // GAME STUFF
    private int stateId;
    private int n;
    private int mapX, mapY;
    private BasicBeing being1;
    private InputCommands inputCommand;
    //    private static Server server;
    private ArrayList<BasicBeing> Players;
    private static int PlayerCount = 2;

    // MAP STUFF
    public TiledMap mapTest = null;
    public TiledMap map1 = null;
    private final String TESTLEVELRSC = "resources/Levels/testlevel.tmx";
    private final String LEVEL1RSC = "resources/Levels/Level1Remake.tmx";
    private final String TILESHEETRSC = "resources/Levels";


    // TODO: eventually remove this
    private final String WALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    private final String ATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";


    // constructor sets port number and state ID for current level
    public TestGameServer(int stateId, int port) throws SlickException {
        // Set game info based on what level was requested by host
        // TODO: eventually remove spritesheets
        // TODO: have state_id set map level info - currently hardcoded to test state, but should have switch or series of if/thens
        if (stateId == 23) {
            ResourceManager.loadImage(WALKINGSHEETRSC);
            ResourceManager.loadImage(ATTACKINGSHEETRSC);
//            map1 = new TiledMap(LEVEL1RSC, TILESHEETRSC);
            mapX = 90;
            mapY = 104;
        }

        // Set port info
        this.port = port;
        this.stateId = stateId;
    }

    public void init () {
        Players = new ArrayList<>();
        // TCP, try to open server socket, catch error if fails
        try {
            socket = new ServerSocket(port);
            socket.setSoTimeout(10000);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server: cannot create ServerSocket");
        }

        listening = true;

        // creates new thread to listen on so server can do other things while waiting
        listeningThread = new Thread(() -> listen(), "GameServerListener");
        listeningThread.start();
    }

//    public static void main(String[] args)throws SlickException{
//        System.out.println("Initializing Networking...");
//        int port = 1234;
//        server = new Server(port);
//        server.start();
//        addPlayer("ipaddress", 1);
//    }

    // process incoming messages from clients
    public void processMessage(String msg) {
        /*
        Message Structures:
        INIT playerIP class#
        INPT playerIP true false false true false
         */
        // sets empty space as delimiter
        String delims = "[ ]";
        // splits message into tokens at every space
        String[] tokens = msg.split(delims);

        switch (tokens[0]) {
            case "INIT":
                System.out.println("Server: got INIT message");
                addPlayer(tokens[1], Integer.parseInt(tokens[2]));
                initPacket();
                break;
            case "INPT":
                break;
            default:
                System.out.println("Server: unknown message received");
                break;
        }
    }

    // TODO: initialization function for sending level info to player, creating player in game
    private void initPacket() {
        System.out.println("executing initPackage");
        // TODO: map info
        // TODO: player positions
//        String msg = "INIT " + Integer.toString(stateId);
        String msg = "INIT " + Integer.toString(1);
        for (int i = 0; i < Players.size(); i++) {
            msg += " ";
            msg += Players.get(i).getName();
            msg += " ";
            msg += Float.toString(Players.get(i).getWorldPositionX());
            msg += " ";
            msg += Float.toString(Players.get(i).getWorldPositionY());
        }
        send(msg);
    }


    // TODO: function for detecting collisions

    // TODO: function for running dijkstra's

    // TODO: function for packaging game state that calls server.send()
        // inc message to send out whenever player joins/leaves (will need to create/delete entity client side)


    private void addPlayer(String playerID, int type) {
        // TODO: Add playerID and ClassID to Basic Being constructor or player constructor, whatever gets used here
        being1 = new BasicBeing(new Vector(640,360), new Vector(mapX, mapY), ResourceManager.getSpriteSheet(WALKINGSHEETRSC,32,32),
                ResourceManager.getSpriteSheet(ATTACKINGSHEETRSC,32,32));
        Players.add(being1);
    }


/** Server Functions */
    // listen on port for client connections
    private void listen() {
        // listen while socket is open
        while(listening) {
            // TCP
            try {
                System.out.println("Server: waiting for client on port " + socket.getLocalPort() + "...");

                // Connect a client
                server = socket.accept();
                connectClient(server);
//                if(server != null) {
//                    connectClient(server);
//                }

            } catch (SocketTimeoutException t) {
                System.out.println("Server: socket timed out");
                send("Server: Are you still there?");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Server: server has a problem listening");
            }
        }
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectClient(Socket server) {
        try {
            // prints connect message to server's console window
            System.out.println("Server: just connected to client " + server.getRemoteSocketAddress());
            // collects incoming data from data stream
            DataInputStream in = new DataInputStream(server.getInputStream());
            // converts data steam into string
            String initMsg = in.readUTF();
            // sends string to processMessage()
            processMessage(initMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send() {
        // send an outgoing message
        try {
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
                    + "\nGoodbye!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        // send an outgoing message
        try {
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





/*  OLD STATE VERSION
public class TestGameServer extends BasicGameState{
    private int stateId;
    private int n;
    private BasicBeing being1;
    private InputCommands inputCommand;
    private static Server server;
    private ArrayList<BasicBeing> Players;
    private static int PlayerCount = 2;

    public TestGameServer(int state_id) {
        this.stateId = state_id;
    }

    private final String WALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    private final String ATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";

    @Override
    public int getID() {
        return this.stateId;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Players = new ArrayList<BasicBeing>(PlayerCount);
        ResourceManager.loadImage(WALKINGSHEETRSC);
        ResourceManager.loadImage(ATTACKINGSHEETRSC);
        System.out.println("Initializing Networking...");
        int port = 1234;
        server = new Server(port);
        server.start();
        addPlayer("ipaddress", 1);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
    }

    public void addPlayer(String playerID, int ClassID) {
        // TODO: Add playerID and ClassID to Basic Being constructor or player constructor, whatever gets used here
//        being1 = new BasicBeing(new Vector(1260,680), ResourceManager.getSpriteSheet(WALKINGSHEETRSC,32,32),
                ResourceManager.getSpriteSheet(ATTACKINGSHEETRSC,32,32);
        Players.add(being1);
    }

    /** Game Server will eventually not have a render loop, leaving it in for testing */ /*
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString("Test Game Server " + n, 640,360);
//        being1.RenderBeing(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        inputCommand = InputManager.ProcessInput(input, stateId);

        // TODO: need to hand ProcessInputCommand the player IP
        // ProcessInputCommand will probably be called by the server, not the update loop
        ProcessInputCommand("being1", inputCommand);
    }

    private void ProcessInputCommand(String playerID, InputCommands inputCommand) {
        // other things to do in playstate for input various things will need this input?
        //  pass the inputCommand to the Being and it will then react to its command.
        //  they will check their own next move for collision and will set the next move in
        //  preparation for their update call.
//        being1.GenerateNextMove(inputCommand);
        //  update the beings position and health inside of
        //  private methods.
//        being1.UpdateBeing();
    }

}
*/
