package Project2;

/**
 * When changes made, add change to changes string
 * " " + ENTITY_ID + inputCommand + WorldPos_X + WorldPos_Y
 */

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.SlickException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static Project2.InputManager.InputCommands;
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
    private int mapX, mapY;
    private ArrayList<BasicBeing> Players;
    private static int PlayerCount = 2;
    private String changes = "";

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
            mapX = 90;
            mapY = 104;
        }

        // Set port info
        this.port = port;
        this.stateId = stateId;
    }


    /** Game Functions */
    private void addPlayer(String playerID, int type) {
        // TODO: Add playerID and ClassID to Basic Being constructor or player constructor, whatever gets used here
        BasicBeing being1 = new BasicBeing(new Vector(mapX, mapY), new Vector(mapX, mapY), ResourceManager.getSpriteSheet(WALKINGSHEETRSC,32,32),
                ResourceManager.getSpriteSheet(ATTACKINGSHEETRSC,32,32));
        Players.add(being1);
    }

    // TODO: function for running dijkstra's
        // to adjust mob positions, call in the sendUpdate runnable? may need a new name then

    // TODO: function for detecting collisions
        // mob/player, mob/wall only, and player-object only
        // call in the sendUpdate runnable?

    // TODO: remove these when they can be linked from proper class position
    /**
     * Calculates the direction dependent on
     * the current value of the <code>moveCommand</code> argument.
     * Movement commands are defined in the InputManager class. The
     * command is placed in a switch and evaluated accordingly. A
     * resulting UNIT Vector is set as the next move direction vector.
     * Thus an up command produces vector (0,1).
     */
    public static Vector CalcDirection(InputCommands moveCommand) {
        float diagSpeed = (float)Math.sqrt(2)/2;
        switch (moveCommand){
            case up:
                return new Vector(0,-1f);
            case down:
                return new Vector(0,1f);
            case left:
                return new Vector(-1f,0);
            case right:
                return new Vector(1f,0);
            case ulDiag:
                return new Vector(-1f,-1f);
            case dlDiag:
                return new Vector(-1f,1f);
            case urDiag:
                return new Vector(1f,-1f);
            case drDiag:
                return new Vector(1f,1f);
            case idle:
                return new Vector(0,0);
            case attack:
                return new Vector(0,0);
        }
        return null;
    }


    /**
     * A new vector is returned that is the product of direction*speed; translation vector.
     * @param direction unit Vector in the direction of the movement
     * @param speed scalar speed of float type
     * @return
     */
    public static Vector CalcVelocity(Vector direction, float speed) {
        return new Vector(direction.getX()*speed,direction.getY()*speed);
    }


    /**
     * Calculates the world position given arguments translation and current world position.
     * new world position = "current world position" + (translation)/32
     */
    public static Vector CalcWorldPosition(Vector translation, Vector curWorldPos) {
        float x = curWorldPos.getX();
        float y = curWorldPos.getY();
        x += (translation.getX()/32f);
        y += (translation.getY()/32f);
        return new Vector(x, y);
    }



/** Server Functions */
    public void init () {
        Players = new ArrayList<>();
        // try to open server socket, catch error if fails
        try {
            socket = new ServerSocket(port);
            socket.setSoTimeout(10000);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server: cannot create ServerSocket");
        }

        // creates new thread to listen on so server can do other things while waiting
        listening = true;
        listeningThread = new Thread(() -> listen(), "GameServerListener");
        listeningThread.start();
    }


    // process incoming messages from clients
    private void processMessage(String msg) {
        /*
        Message Structures:
        INIT playerIP class#
        INPT playerIP dirCommand
         */

        // sets empty space as delimiter
        String delims = "[ ]";
        // splits message into tokens at every space
        String[] tokens = msg.split(delims);
        String command = tokens[0];
        String player = tokens[1];

        switch (command) {
            case "INIT":
//                System.out.println("Server: got INIT message");
                addPlayer(player, Integer.parseInt(tokens[2]));
                initPacket();
                break;
            case "INPT":
//                System.out.println("Server: got INPT message: " + tokens[2]);
                InputCommands inputCommand = getCommand(tokens[2]);

                // TODO: fix this after IP is stored in player class
                // process movement based on input
                Vector velocity = (CalcVelocity(CalcDirection(inputCommand), Players.get(0).getSpeed()));
                Players.get(0).setVelocity(velocity);
                Vector newWorldPosition = CalcWorldPosition(velocity, Players.get(0).getWorldPosition());
                Players.get(0).setWorldPosition(newWorldPosition);
                float x = Players.get(0).getWorldPositionX();
                float y = Players.get(0).getWorldPositionY();

                // check for player/wall collisions

                // if movement was valid, add update to changes
                changes += " " + player;
                changes += " " + tokens[2];
                changes += " " + x;
                changes += " " + y;
                // for use when IP is properly stored in player class
                /*
                for (int i = 0; i < Players.size(); i++) {
                    if (Players.get(i).getName() == player) {
                        Players.get(i).GenerateNextMove(inputCommand);
                        Players.get(i).UpdateBeingPosition();
                        changes += " " + player;
                        changes += " " + Players.get(i).getX();
                        changes += " " + Players.get(i).getY();
                    }
                }
                */
                break;
            default:
                System.out.println("Server: unknown message received");
                break;
        }
    }

    /** Accepts a string and converts it to an InputCommand */
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
        }
        return inputCommand;
    }

    private void initPacket() {
        // TODO: send proper level info, currently hardcoded to level 1
        String msg = "INIT " + Integer.toString(1); // Integer.toString(LEVEL_NO)
        for (int i = 0; i < Players.size(); i++) {
            msg += " " + Players.get(i).getName();
            msg += " " + Float.toString(Players.get(i).getX());
            msg += " " + Float.toString(Players.get(i).getY());
        }
        send(msg);
    }

    // listen on port for client connections
    private void listen() {
        // listen while socket is open
        while(listening) {
            try {
                System.out.println("Server: waiting for client on port " + socket.getLocalPort() + "...");

                // Connect a client
                server = socket.accept();
                connectClient(server);

                ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
                executor.scheduleAtFixedRate(sendUpdate, 0, 15, TimeUnit.MILLISECONDS);

                DataInputStream in = new DataInputStream(server.getInputStream());
                String msg;

                // listen for incoming messages
                while ((msg = in.readUTF()) != null) {
                    processMessage(msg);
                }

            } catch (SocketTimeoutException t) {
                System.out.println("Server: socket timed out");
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


    private void send(String msg) {
        // send an outgoing message
        try {
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // timer for update packets
    private Runnable sendUpdate = new Runnable() {
        public void run() {
            if (changes != "") {
                String msg = "UPDT" + changes;
                changes = "";
                send(msg);
            }
        }
    };

}