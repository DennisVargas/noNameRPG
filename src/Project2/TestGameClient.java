package Project2;

/**
 * Page should load from either a screen where the player sets up a server (enters port number) or the player chooses to
 * join someone else's game (enters IP and port number)
 * */

import Networking.Client;
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
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.*;


import static Project2.InputManager.InputCommands;
import static Project2.InputManager.InputCommands.*;

public class TestGameClient extends BasicGameState{
    private double x, y;
    private int mapX, mapY;
    private int stateId;
    private BasicBeing being1;
    private InputCommands inputCommand;
    private ExecutorService execService;
    TestGameServer gameserver;
    private String ipAddress; // client's IP address
    private String serverAddress; // server's IP address
    private int port;
    private boolean listening = false;
    Socket socket;

    public TestGameClient(int state_id) {
        this.stateId = state_id;
    }

    private final String WALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    private final String ATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";

//    public TiledMap mapTest = null;
    public TiledMap map1 = null;
//    private final String TESTLEVELRSC = "resources/Levels/testlevel.tmx";
    private final String LEVEL1RSC = "resources/Levels/Level1Remake.tmx";
    private final String TILESHEETRSC = "resources/Levels";

    @Override
    public int getID() {
        return stateId;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        ResourceManager.loadImage(WALKINGSHEETRSC);
        ResourceManager.loadImage(ATTACKINGSHEETRSC);
        // TODO: load map based on info received in init packet from server
        map1 = new TiledMap(LEVEL1RSC, TILESHEETRSC);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);

        // SERVER STUFF (if client is running server)
        // TODO: send proper state ID for current level (and port number)
        if (true) { // should evaluate whether or not player is hosting the server
            System.out.println("Client's state_id" + Integer.toString(stateId));
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



        // TODO: shouldn't be loading any map or player info until map info received from server
        // MAP STUFF
        mapX = 90;
        mapY = 104;

        // ENTITY STUFF
        being1 = new BasicBeing(new Vector(container.getWidth()/2,container.getHeight()/2), new Vector(mapX, mapY), ResourceManager.getSpriteSheet(WALKINGSHEETRSC,32,32),
                ResourceManager.getSpriteSheet(ATTACKINGSHEETRSC,32,32));
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        // VIEWPORT STUFF
        int displaceX, displaceY, worldPosX, worldPosY;
        displaceX = (int)being1.getCurrentDisplacementX(); displaceY = (int)being1.getCurrentDisplacementY();
        worldPosX = (int)being1.getWorldPositionX(); worldPosY = (int)being1.getWorldPositionY();
        //  render the map using the client displacement from tile center
        //  and current world position.
        map1.render(displaceX-32, displaceY-32,
                worldPosX, worldPosY, worldPosX+45, worldPosY+30 );
        graphics.drawString("Test Game Client ", 740,360);

        // ENTITY STUFF
        being1.RenderBeing(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        inputCommand = InputManager.ProcessInput(input, stateId);
        ProcessInputCommand(inputCommand);
    }

    private void ProcessInputCommand(InputCommands inputCommand) {
        // other things to do in playstate for input various things will need this input?
        //  pass the inputCommand to the Being and it will then react to its command.
        //  they will check their own next move for collision and will set the next move in
        //  preparation for their update call.
        being1.GenerateNextMove(inputCommand);
        //  update the beings position and health inside of
        //  private methods.
        being1.UpdateBeingPosition();
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

        // UDP
//        try {
//            serverAddress = InetAddress.getByName(ipAddress);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//            return false;
//        }
//        try {
//            socket = new DatagramSocket(); // generates with random port number
//        } catch (SocketException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        byte[] connMsg = "conn".getBytes();
//        send(connMsg);
        // wait for server to reply
        return true;
    }

    private void listen() {
        // listen while socket is open
        while(listening) {
            // TCP
            try {
                InputStream inFromServer = socket.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                System.out.println("Client: server sent message: " + in.readUTF());
                send("Client: still connected");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Client: server has a problem listening");
                listening = false;
            }

            // UDP
            // create packet to hold received information
//            DatagramPacket packet = new DatagramPacket(dataBuffer, MAX_PACKET_SIZE);

            // wait for packet to received
//            try {
//                socket.receive(packet);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("packet received");
//            process(packet);
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void process() {
//
//    }


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
