package Project2;

/**
 * When changes made, add change to changes string
 * " " + ENTITY_ID + inputCommand + WorldPos_X + WorldPos_Y
 */

import jig.Collision;
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
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static Project2.InputManager.InputCommands;
import static Project2.InputManager.InputCommands.*;
import static Project2.MovementCalc.*;


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
    private ArrayList<Hero> Players;
    private MobList moblist;
    private DoorList doorList;
    private ArrayList<Mob> Mobs;                                                                                                
    private ArrayList<Door> Doors;                                                                                              
    private ArrayList<Money> MoneyDrops;
    private ArrayList<Health> HealthDrops;
    private ArrayList<Money> NewMoneyDrops;
    private ArrayList<Health> NewHealthDrops;
    private ArrayList<Mob> IgnoreList;
    private static int PlayerCount = 2;
    private String mobChanges = "";
    private String moneyDropChanges = "";
    private String healthDropChanges = "";
    private String moneyPickupChanges = "";
    private String healthPickupChanges = "";
    private String e = "";
    int playersMoney;

    // TODO: eventually remove this
    private final String WALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    private final String ATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";


    // constructor sets port number and state ID for current level
    public TestGameServer(int stateId, int port) throws SlickException {
        Mobs = new ArrayList<>();
        Doors = new ArrayList<>();
        Money = new ArrayList<>();
        moblist = new MobList();
        doorList = new DoorList();
        MoneyDrops = new ArrayList<Money>();
        NewMoneyDrops = new ArrayList<>();
        HealthDrops = new ArrayList<Health>();
        NewHealthDrops = new ArrayList<Health>();
        playersMoney = 0;
        // Set game info based on what level was requested by host
        // TODO: eventually remove spritesheets
        // TODO: have state_id set map level info - currently hardcoded to test state, but should have switch or series of if/thens
        if (stateId == 22) {
            ResourceManager.loadImage(WALKINGSHEETRSC);
            ResourceManager.loadImage(ATTACKINGSHEETRSC);
            mapX = 90;
            mapY = 104;
            Mobs = moblist.getMobList(1);
            Doors = doorList.getDoorList(1);
            for(Mob mob: Mobs){
                mob.setPosition(new Vector(mob.getWorldPositionX()*32f, mob.getWorldPositionY()*32f));
            }
            for(Door door: Doors){
                door.setPosition(new Vector(door.getWorldPositionX()*32, door.getWorldPositionY()*32));
            }
        }

        // Set port info
        this.port = port;
        this.stateId = stateId;
    }


    /** Game Functions */
    private void addPlayer(String playerID, int type) {
        // TODO: Add playerID and ClassID to Basic Being constructor or player constructor, whatever gets used here
        Hero hero1 = new Hero(new Vector(mapX, mapY),false, playerID);
        hero1.setPosition(new Vector(mapX,mapY));
        Players.add(hero1);
    }

    // TODO: function for running dijkstra's
        // to adjust mob positions, call in the sendUpdate runnable? may need a new name then

    // TODO: function for detecting collisions
        // mob/player, mob/wall only, and player-object only
        // call in the sendUpdate runnable?



/** Server Functions */
    public void init () {
        Players = new ArrayList<>();
        IgnoreList = new ArrayList<Mob>();
//        These are being initialized in constructor and this one sets them to zero again.
//        Mobs = new ArrayList<>();
//        moblist = new MobList();
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
//                addPlayer("/animalCrackers", 1);
                initPacket();
                break;
            case "INPT":
//                System.out.println("Server: got INPT message: " + tokens[2]);
                InputCommands inputCommand = getCommand(tokens[2]);
                Hero hero1 = Players.get(0);
                // TODO: fix this after IP is stored in player class
                // process movement based on input
                hero1.setCommand(inputCommand);
//                Players.get(0).UpdateAttackRect();
//                Vector velocity = (CalcTranslation(CalcDirection(inputCommand), Players.get(0).getSpeed()));
//                hero1.setTranslation(velocity);
                Vector newWorldPosition = CalcWorldPosition(hero1.getCommand(),hero1.getWorldPosition(),hero1.getSpeed());
//                set map position
                hero1.setWorldPosition(newWorldPosition);
//                set jig entity vector for collisions.
                hero1.setPosition(new Vector(newWorldPosition.getX()*32f,newWorldPosition.getY()*32f));
                float x =  hero1.getWorldPositionX();
                float y =  hero1.getWorldPositionY();
                CollisionManager.CheckHeroMobCollisions(hero1, Mobs);
                CollisionManager.CheckHeroHeroCollisions(hero1, Players);


                // check for player/wall collisions
                if(CollisionManager.CheckValidMove(Players.get(0))) {
                    CollisionManager.CheckHeroMobCollisions(Players.get(0), Mobs);
                    // if movement was valid, add update to changes
                    String newChange = " " + player;
                    newChange += " " + tokens[2];
                    newChange += " " + x;
                    newChange += " " + y;

                    mobChanges += newChange;
                }
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
            Random random = new Random();
            for(Mob mob: Mobs){
                try{mob.setCommand(InputCommands.idle);}catch(Exception e){ System.out.println("emptyMOB ON SERvER");}
                Vector newMobPosition = MovementCalc.CalcWorldPosition(mob.getCommand(),mob.getWorldPosition(),mob.getSpeed());
                mob.setWorldPosition(newMobPosition);
                mob.setPosition(new Vector(newMobPosition.getX()*32f, newMobPosition.getY()*32f));
                CollisionManager.CheckMobHeroCollisions(mob, Players);
                CollisionManager.CheckMobMobCollisions(mob, Mobs);
                Money money;
                money = CollisionManager.CheckHeroMoneyCollision(Players.get(0),MoneyDrops);
                if (money != null) {
                    for(int i = 0; i < MoneyDrops.size(); i++){
                        if(MoneyDrops.get(i).getName().contains(money.getName())){
                            playersMoney += money.value;
                            moneyPickupChanges += " " + money.getName();
                            MoneyDrops.remove(MoneyDrops.get(i));
                        }
                    }
                }
                Health health;
                health = CollisionManager.CheckHeroHealthCollision(Players.get(0), HealthDrops);
                if (health != null) {
                    for (int i = 0; i < HealthDrops.size(); i++){
                        if (HealthDrops.get(i).getName().contains(health.getName()) & Players.get(0).getHealth() < 10){
                            Players.get(0).setHealth(Players.get(0).getHealth()+1);
                            healthPickupChanges += " " + health.getName();
                            HealthDrops.remove(HealthDrops.get(i));
                        }
                    }
                }
//              CollisionManager.CheckBeingBeingCollisions(Mobs.get(0), Mobs);
                //            // if movement was valid, add update to changes
                String mobChange  = " " + mob.getName();
                mobChange += " " + mob.getCommand();
                if(mob.getCommand() == InputCommands.death) {
//                    System.out.println(mob.getName() + " " + mob.getCommand());
                    Vector position = mob.getWorldPosition();
                    int value = random.nextInt((21 - 1) + 1);
                    int coinFlip = random.nextInt((2-1) + 1);
                    if (coinFlip == 1) {
                        if (!IgnoreList.contains(mob)) {
                            IgnoreList.add(mob);
                            String moneyChange = "";
                            NewMoneyDrops.clear();
                            try {
                                if (value > 0) {
                                    NewMoneyDrops.add(new Money(position, "money" + MoneyDrops.size(), value));
                                }
                            } catch (SlickException e) {
                                System.out.println("Failed to drop money off of " + mob.getName());
                            }
                            for (int i = 0; i < NewMoneyDrops.size(); i++) {
                                moneyChange += " " + NewMoneyDrops.get(i).getName();
                                moneyChange += " " + NewMoneyDrops.get(i).getWorldPositionX();
                                moneyChange += " " + NewMoneyDrops.get(i).getWorldPositionY();
                                moneyChange += " " + NewMoneyDrops.get(i).value;
                            }
                            MoneyDrops.addAll(NewMoneyDrops);
                            moneyDropChanges = moneyChange;
                        }
                    } else {
                        if (!IgnoreList.contains(mob)) {
                            IgnoreList.add(mob);
                            String healthChange = "";
                            NewHealthDrops.clear();
                            try {
                                if (value > 0) {
                                    NewHealthDrops.add(new Health(position, "health" + MoneyDrops.size(), value));
                                }
                            } catch (SlickException e) {
                                System.out.println("Failed to drop money off of " + mob.getName());
                            }
                            for (int i = 0; i < NewHealthDrops.size(); i++) {
                                healthChange += " " + NewHealthDrops.get(i).getName();
                                healthChange += " " + NewHealthDrops.get(i).getWorldPositionX();
                                healthChange += " " + NewHealthDrops.get(i).getWorldPositionY();
                                healthChange += " " + NewHealthDrops.get(i).value;
                            }
                            HealthDrops.addAll(NewHealthDrops);
                            healthDropChanges = healthChange;
                        }
                    }
                }
                mobChange += " " + mob.getWorldPositionX();
                mobChange += " " + mob.getWorldPositionY();

                mobChanges = mobChanges.concat(mobChange);
            }

//            System.out.println("seerver change: "+changes);
            if (mobChanges != "") {
                String msg = "UPDT" + mobChanges;
                mobChanges = "";
//                System.out.println(msg);
                send(msg);
            }
            if (healthDropChanges != "") {
                String msg = "DROPH" + healthDropChanges;
                healthDropChanges = "";
                send(msg);
            }

            if (healthPickupChanges != ""){
                String msg = "PCKUPH " + healthPickupChanges;
                healthPickupChanges = "";
                send(msg);
            }

            if (moneyDropChanges != "") {
                String msg = "DROPM " + moneyDropChanges;
                moneyDropChanges = "";
                send(msg);
            }

            if (moneyPickupChanges != "") {
                String msg = "PCKUPM " + playersMoney + " " + moneyPickupChanges;
                moneyPickupChanges = "";
                send(msg);
            }
        }
    };
}