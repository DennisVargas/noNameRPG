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


import jig.Vector;
import org.newdawn.slick.SlickException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
    private ServerSocket socket;
    private Socket server;
    private ArrayList<Socket> clients;
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    // GAME STUFF
    private int stateId;
    private float mapX, mapY;
    private ArrayList<Hero> Players;
    private MobList moblist;
    private DoorList doorList;
    private ArrayList<Mob> Mobs;
    private ArrayList<Door> Doors;
    private ArrayList<Money> Money;
    private ArrayList<Money> MoneyDrops;
    private ArrayList<Health> HealthDrops;
    private ArrayList<Money> NewMoneyDrops;
    private ArrayList<Health> NewHealthDrops;
    private ArrayList<Mob> IgnoreList;
    private static int PlayerCount = 2;
    private String changes = "";
    private String moneyDropChanges = "";
    private String healthDropChanges = "";
    private String moneyPickupChanges = "";
    private String healthPickupChanges = "";
    int playersMoney;
    private Map mapping = null;


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
        clients = new ArrayList<>();

        // Set game info based on what level was requested by host
        // TODO: have state_id set map level info - currently hardcoded to test state, but should have switch or series of if/thens
        if (stateId == 22) {
            mapX = 45f;
            mapY = 105f;
            Mobs = moblist.getMobList(1);
            Doors = doorList.getDoorList(1);
            for(Mob mob: Mobs){
                mob.setPosition(new Vector(mob.getWorldPositionX()*32f, mob.getWorldPositionY()*32f));
            }
            for(Door door: Doors){
                door.setPosition(new Vector(door.getWorldPositionX()*32, door.getWorldPositionY()*32));
            }
            mapping = Project2.settings.getTilemapping();
        }

        // Set port info
        this.port = port;
        this.stateId = stateId;
    }


    /** Game Functions */
    private void addPlayer(String playerID, int type) {
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
        IgnoreList = new ArrayList<Mob>();
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
                        Vector newWorldPosition = CalcWorldPosition(Players.get(i).getCommand(),Players.get(i).getWorldPosition(),Players.get(i).getSpeed());
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

                        Money money;
                        money = CollisionManager.CheckHeroMoneyCollision(Players.get(i),MoneyDrops);
                        if (money != null) {
                            for(int j = 0; j < MoneyDrops.size(); j++){
                                if(MoneyDrops.get(j).getName().contains(money.getName())){
                                    playersMoney += money.value;
                                    moneyPickupChanges += " " + money.getName();
                                    MoneyDrops.remove(MoneyDrops.get(j));
                                }
                            }
                        }
                        Health health;
                        health = CollisionManager.CheckHeroHealthCollision(Players.get(i), HealthDrops);
                        if (health != null) {
                            for (int j = 0; j < HealthDrops.size(); j++){
                                if (HealthDrops.get(j).getName().contains(health.getName()) & Players.get(i).getHealth() < 10){
                                    Players.get(i).setHealth(Players.get(i).getHealth()+1);
                                    healthPickupChanges += " " + health.getName();
                                    HealthDrops.remove(HealthDrops.get(j));
                                }
                            }
                        }
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
            System.out.println("Running fine");
            Random random = new Random();
            for (int bubbles = 0; bubbles < Players.size(); bubbles++) {
                //<editor-fold desc="Dijkstra stuffs">
                float playerX = (float)Math.floor(Players.get(bubbles).getWorldPositionX());
                float playerY = (float)Math.floor(Players.get(bubbles).getWorldPositionY());
                Vector playerPosition = new Vector(playerX,playerY);
                Pathfinding.Dijkstra(mapping, playerPosition);
                //</editor-fold>
                for (int i = 0; i < Mobs.size(); i++) {
                    //TODO: if mob is within player range, give it a path
                    //TODO: check which direction the mob should move in
                    float mobX = (float)Math.floor(Mobs.get(i).getWorldPositionX());
                    float mobY = (float)Math.floor(Mobs.get(i).getWorldPositionY());
                    Vector cheesyMobs = new Vector(mobX, mobY);
                    if (Pathfinding.range(playerPosition, cheesyMobs) &&
                            !Mobs.get(i).IsDead()){
                        String command = Pathfinding.getPath((int)mobX, (int)mobY);
                        //Vector whatever = Pathfinding.nextTile((int)mobX, (int)mobY, command);
                        /*up, down, left, right, ulDiag, dlDiag, urDiag, drDiag*/
                        if (command.equalsIgnoreCase("up")){Mobs.get(i).setCommand(InputCommands.up);}
                        else if (command.equalsIgnoreCase("down")){Mobs.get(i).setCommand(InputCommands.down);}
                        else if (command.equalsIgnoreCase("left")){Mobs.get(i).setCommand(InputCommands.left);}
                        else if (command.equalsIgnoreCase("right")){Mobs.get(i).setCommand(InputCommands.right);}
                        else if (command.equalsIgnoreCase("ulDiag")){Mobs.get(i).setCommand(InputCommands.ulDiag);}
                        else if (command.equalsIgnoreCase("dlDiag")){Mobs.get(i).setCommand(InputCommands.dlDiag);}
                        else if (command.equalsIgnoreCase("urDiag")){Mobs.get(i).setCommand(InputCommands.urDiag);}
                        else if (command.equalsIgnoreCase("drDiag")){Mobs.get(i).setCommand(InputCommands.drDiag);}
                    } else {
                        Mobs.get(i).setCommand(InputCommands.idle);
                    }
                    Vector newMobPosition = MovementCalc.CalcWorldPosition(Mobs.get(i).getCommand(), Mobs.get(i).getWorldPosition(), Mobs.get(i).getSpeed());
                    Mobs.get(i).setWorldPosition(newMobPosition);
                    Mobs.get(i).setPosition(new Vector(newMobPosition.getX() * 32f, newMobPosition.getY() * 32f));
                    CollisionManager.CheckMobHeroCollisions(Mobs.get(i), Players);
                    //CollisionManager.CheckMobMobCollisions(Mobs.get(i), Mobs);
    //            CollisionManager.CheckBeingBeingCollisions(Mobs.get(0), Mobs);
                    Money money;
                    money = CollisionManager.CheckHeroMoneyCollision(Players.get(0), MoneyDrops);
                    if (money != null) {
                        for (int j = 0; j < MoneyDrops.size(); j++) {
                            if (MoneyDrops.get(j).getName().contains(money.getName())) {
                                playersMoney += money.value;
                                moneyPickupChanges += " " + money.getName();
                                MoneyDrops.remove(MoneyDrops.get(j));
                            }
                        }
                    }
                    Health health;
                    health = CollisionManager.CheckHeroHealthCollision(Players.get(0), HealthDrops);
                    if (health != null) {
                        for (int j = 0; i < HealthDrops.size(); j++) {
                            if (HealthDrops.get(j).getName().contains(health.getName()) & Players.get(0).getHealth() < 10) {
                                Players.get(0).setHealth(Players.get(0).getHealth() + 1);
                                healthPickupChanges += " " + health.getName();
                                HealthDrops.remove(HealthDrops.get(j));
                            }
                        }
                    }
    //              CollisionManager.CheckBeingBeingCollisions(Mobs.get(0), Mobs);
                    //            // if movement was valid, add update to changes

                    if (Mobs.get(i).getCommand() == InputCommands.death) {
    //                    System.out.println(mob.getName() + " " + mob.getCommand());
                        Vector position = Mobs.get(i).getWorldPosition();
                        int value = random.nextInt((21 - 1) + 1);
                        int coinFlip = random.nextInt((2 - 1) + 1);
                        if (coinFlip == 1) {
                            if (!IgnoreList.contains(Mobs.get(i))) {
                                IgnoreList.add(Mobs.get(i));
                                String moneyChange = "";
                                NewMoneyDrops.clear();
                                try {
                                    if (value > 0) {
                                        NewMoneyDrops.add(new Money(position, "money" + MoneyDrops.size(), value));
                                    }
                                } catch (SlickException e) {
                                    System.out.println("Failed to drop money off of " + Mobs.get(i).getName());
                                }
                                for (int j = 0; j < NewMoneyDrops.size(); j++) {
                                    moneyChange += " " + NewMoneyDrops.get(j).getName();
                                    moneyChange += " " + NewMoneyDrops.get(j).getWorldPositionX();
                                    moneyChange += " " + NewMoneyDrops.get(j).getWorldPositionY();
                                    moneyChange += " " + NewMoneyDrops.get(j).value;
                                }
                                MoneyDrops.addAll(NewMoneyDrops);
                                moneyDropChanges = moneyChange;
                            }
                        } else {
                            if (!IgnoreList.contains(Mobs.get(i))) {
                                IgnoreList.add(Mobs.get(i));
                                String healthChange = "";
                                NewHealthDrops.clear();
                                try {
                                    if (value > 0) {
                                        NewHealthDrops.add(new Health(position, "health" + MoneyDrops.size(), value));
                                    }
                                } catch (SlickException e) {
                                    System.out.println("Failed to drop money off of " + Mobs.get(i).getName());
                                }
                                for (int j = 0; j < NewHealthDrops.size(); j++) {
                                    healthChange += " " + NewHealthDrops.get(j).getName();
                                    healthChange += " " + NewHealthDrops.get(j).getWorldPositionX();
                                    healthChange += " " + NewHealthDrops.get(j).getWorldPositionY();
                                    healthChange += " " + NewHealthDrops.get(j).value;
                                }
                                HealthDrops.addAll(NewHealthDrops);
                                healthDropChanges = healthChange;
                            }
                        }
                    }

                    String mobChange  = " " + Mobs.get(i).getName();
                    mobChange += " " + Mobs.get(i).getCommand();
                    mobChange += " " + Mobs.get(i).getWorldPositionX();
                    mobChange += " " + Mobs.get(i).getWorldPositionY();

                    changes = changes.concat(mobChange);
                }
            }


            if (changes != "") {
                String msg = "UPDT" + changes;
                changes = "";
//                System.out.println("Server: "+msg);
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