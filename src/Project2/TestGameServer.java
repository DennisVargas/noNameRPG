package Project2;
/**
 * Need to fix player creation and init packet - pass player's socket so init only goes to new player
 * clean up remaining server errors that are referencing server and not client list loop
 * find way to tell client that player disconnected and is gone
 * player 2 resets position to player 1's position when window loses and regains focus - possibly referencing (0) somewhere
 *
 * Once player 2 disconnects, is unable to reconnect, possible server thinks two clients still connected
 */

/**
 * When changes made, add change to changes string
 * " " + ENTITY_ID + inputCommand + WorldPos_X + WorldPos_Y
 */

//import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.SlickException;
//import sun.net.ConnectionResetException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
//import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static Project2.InputManager.InputCommands;
import static Project2.InputManager.InputCommands.*;
import static Project2.MovementCalc.*;


public class TestGameServer {
    // SERVER STUFF
    private int port;
//    private Thread timerThread;
    private ServerSocket socket;
    private Socket server;
    private ArrayList<Socket> clients;
    ScheduledExecutorService executor;

    // GAME STUFF
    private int stateId;
    private int mapX, mapY;
    private ArrayList<Hero> Players;
    private MobList moblist;
    private ArrayList<Mob> Mobs;
    private static int PlayerCount = 2;
    private String changes = "";

    // TODO: eventually remove this
//    private final String WALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
//    private final String ATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";


    // constructor sets port number and state ID for current level
    public TestGameServer(int stateId, int port) throws SlickException {
        Mobs = new ArrayList<>();
        moblist = new MobList();
        clients = new ArrayList<>();
        // Set game info based on what level was requested by host
        // TODO: eventually remove spritesheets
        // TODO: have state_id set map level info - currently hardcoded to test state, but should have switch or series of if/thens
        if (stateId == 22) {
//            ResourceManager.loadImage(WALKINGSHEETRSC);
//            ResourceManager.loadImage(ATTACKINGSHEETRSC);
            mapX = 90;
            mapY = 104;
            Mobs = moblist.getMobList(1);
//            for(Mob mob: Mobs){
//                mob.setPosition(new Vector(mob.getWorldPositionX()*32f, mob.getWorldPositionY()*32f));
//            }
        }

        // Set port info
        this.port = port;
        this.stateId = stateId;
    }


    /** Game Functions */
    private void addPlayer(String playerID, int type) {
        // TODO: Pass hero type to this method eventually
        Hero hero = new Hero(new Vector(mapX, mapY),false, playerID);
        hero.setPosition(new Vector(mapX,mapY));
        Players.add(hero);
        String newChange  = " " + playerID;
        newChange += " " + idle;
        newChange += " " + hero.getWorldPositionX();
        newChange += " " + hero.getWorldPositionY();

        changes += newChange;
    }


/** Server Functions */
    public void init () {
        Players = new ArrayList<>();
//        These are being initialized in constructor and this one sets them to zero again.
//        Mobs = new ArrayList<>();
//        moblist = new MobList();
        // try to open server socket, catch error if fails
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server: cannot create ServerSocket");
        }

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(sendUpdate, 0, 15, TimeUnit.MILLISECONDS);

        while (true) {
            System.out.println("Server: waiting for client on port " + socket.getLocalPort() + "...");
            try {
                server = socket.accept();
                clients.add(server);
                // creates new thread to listen on so server can do other things while waiting
                new Thread(() -> listen(server), "GameServerListener").start();
            } catch (IOException e) {
                System.out.println("Server: server has a problem listening");
            }
        }
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
                if (Players.size() < PlayerCount) {
                    addPlayer(player, Integer.parseInt(tokens[2]));
                    msg = initPacket();

                    // loop through player list, find player that sent init message
                    for (int i = 0; i < Players.size(); i++) {
                        if (Players.get(i).getName().equals(player)) {
                            // find matching client thread for player
                            for (int j = 0; j < clients.size(); j++) {
                                String address = clients.get(j).getInetAddress().getHostAddress();
                                int port = clients.get(j).getPort();
                                String test = "/" + address + ":" + port;
                                // only sent init response to player that sent init request
                                if (test.equals(player)) {
                                    try {
                                        DataOutputStream out = new DataOutputStream(clients.get(j).getOutputStream());
                                        out.writeUTF(msg);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }
                    }
                }
                break;
            case "INPT":
//                System.out.println("Server: got INPT message from: " + player);
                InputCommands inputCommand = getCommand(tokens[2]);

                for (int i = 0; i < Players.size(); i++) {
                    if (Players.get(i).getName().equals(player)) {
                        // process movement based on input
                        Players.get(i).setCommand(inputCommand);
                        Vector velocity = (CalcTranslation(CalcDirection(inputCommand), Players.get(i).getSpeed()));
                        Players.get(i).setTranslation(velocity);
                        Vector newWorldPosition = CalcWorldPosition(velocity, Players.get(i).getWorldPosition());
                        // set map position
                        Players.get(i).setWorldPosition(newWorldPosition);
                        // set jig entity vector for collisions.
                        Players.get(i).setPosition(new Vector(newWorldPosition.getX()*32f,newWorldPosition.getY()*32f));
                        float x = Players.get(i).getWorldPositionX();
                        float y = Players.get(i).getWorldPositionY();
                        // check for player/wall collisions
                        CollisionManager.CheckHeroMobCollisions(Players.get(i), Mobs);
                        // if movement was valid, add update to changes
                        String newChange  = " " + player;
                        newChange += " " + tokens[2];
                        newChange += " " + x;
                        newChange += " " + y;

                        changes += newChange;
                    }
                }

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

    private String initPacket() {
        // TODO: send proper level info, currently hardcoded to level 1
//        System.out.println("Server: sending INIT message");
        String msg = "INIT " + Integer.toString(1); // Integer.toString(LEVEL_NO)
        for (int i = 0; i < Players.size(); i++) {
            msg += " " + Players.get(i).getName();
            msg += " " + Float.toString(Players.get(i).getWorldPositionX());
            msg += " " + Float.toString(Players.get(i).getWorldPositionY());
        }
        return msg;
    }

    // listen on port for client connections
    private void listen(Socket listener) {
        boolean listening = true;

        while(listening) {
            try {
                DataInputStream in = new DataInputStream(listener.getInputStream());
                String msg;

                msg = in.readUTF();

                while (listener.isConnected()) {
                    processMessage(msg);
                    msg = in.readUTF();
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Server: server has a problem listening");
                listening = false;
            }
        }
        try {
            listener.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // only going to reach this point if client disconnected
        for (int i = 0; i < clients.size(); i++) {
            // find client in client list
            if (listener == clients.get(i)) {
                String address = clients.get(i).getInetAddress().getHostAddress();
                int port = clients.get(i).getPort();
                String player = "/" + address + ":" + port;

                for (int j = 0; j < Players.size(); j++) {
                    // find corresponding player in player list and remove
                    if (player.equals(Players.get(j).getName())) {
                        String newChange  = " " + Players.get(j).getName();
                        newChange += " " + InputCommands.dc;
                        newChange += " " + Players.get(j).getWorldPositionX();
                        newChange += " " + Players.get(j).getWorldPositionY();
                        changes += newChange;
                        Players.remove(j);
                        System.out.println("Server removed player, player size = " + Players.size());
                    }
                }
                // remove player's client from client list
                clients.remove(i);
                System.out.println("Server: Disconnected client, client count = " + clients.size());
            }
        }
    }


    // send message to all clients
    private void send(String msg) {
        // send an outgoing message
        for (int i = 0; i < clients.size(); i++) {
            try {
                DataOutputStream out = new DataOutputStream(clients.get(i).getOutputStream());
                out.writeUTF(msg);
            } catch (IOException e) {
                System.out.println("Server sending to player that doesn't exist");
                clients.remove(i);
                e.printStackTrace();
            }
        }
    }


    // timer for update packets
    private Runnable sendUpdate = new Runnable() {
        public void run() {
//            Mob mob = Mobs.get(0);
//            try{mob.setCommand(InputCommands.right);}catch(Exception e){ System.out.println("emptyMOB ON SERvER");}
//            Vector newMobPosition = MovementCalc.CalcWorldPosition(MovementCalc.CalcTranslation(
//                    MovementCalc.CalcDirection(mob.getCommand()),mob.getSpeed()),mob.getWorldPosition());
//            mob.setWorldPosition(newMobPosition);
//            mob.setPosition(new Vector(newMobPosition.getX()*32f, newMobPosition.getY()*32f));
//            CollisionManager.CheckMobHeroCollisions(mob, Players);
//            CollisionManager.CheckBeingBeingCollisions(Mobs.get(0), Mobs);
            //            // if movement was valid, add update to changes
//            String newChange  = " " + Mobs.get(0).getName();
//            newChange += " " + InputCommands.right;
//            newChange += " " + Mobs.get(0).getWorldPositionX();
//            newChange += " " + Mobs.get(0).getWorldPositionY();
//
//            changes = changes.concat(newChange);
//            System.out.println("seerver change: "+changes);
            if (changes != "") {
                String msg = "UPDT" + changes;
                changes = "";
//                System.out.println(msg);
                send(msg);
            }
        }
    };

}