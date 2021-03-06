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
    private ArrayList<Ball> HeroBalls;
    private MobList moblist;
    private DoorList doorList;
    private KeyList keyList;
    private CrateList crateList;
    private ArrayList<Mob> Mobs;
    private ArrayList<Ball> MobBalls;
    private ArrayList<Door> Doors;
    private ArrayList<Crate> Crates;
    private ArrayList<Key> Keys;
    private ArrayList<Money> MoneyDrops;
    private ArrayList<Health> HealthDrops;
    private ArrayList<Key> KeyDrops;
    private ArrayList<Money> NewMoneyDrops;
    private ArrayList<Health> NewHealthDrops;
    private ArrayList<Key> NewKeyDrops;
    private ArrayList<Mob> IgnoreList;
    private ArrayList<Crate> IgnoreCrateList;
    private static int PlayerCount = 2;
    private String changes = "";
    private String moneyDropChanges = "";
    private String healthDropChanges = "";
    private String moneyPickupChanges = "";
    private String healthPickupChanges = "";
    private String keyPickupChanges = "";
    private String doorChanges = "";
    private String crateRemoval = "";
    int playersMoney;
    int playersKeys;
    int playersLives;
    private boolean livesChange = true;
    int activeLevel = 1;
    boolean levelTransition = false;
    boolean win = false;

    //        map stuff
    public TiledMap map = null;
    private Map mapping = null;

    // constructor sets port number and state ID for current level
    public TestGameServer(int stateId, int port) throws SlickException {
        Mobs = new ArrayList<>();
        Doors = new ArrayList<>();
        Crates = new ArrayList<>();
        MobBalls = new ArrayList<>();
        HeroBalls = new ArrayList<>();
        moblist = new MobList();
        doorList = new DoorList();
        crateList = new CrateList();
        keyList = new KeyList();
        MoneyDrops = new ArrayList<Money>();
        NewMoneyDrops = new ArrayList<>();
        HealthDrops = new ArrayList<Health>();
        NewHealthDrops = new ArrayList<Health>();
        KeyDrops = new ArrayList<Key>();
        NewKeyDrops = new ArrayList<Key>();
        playersMoney = 0;
        clients = new ArrayList<>();
        playersLives = 3;
        playersKeys = 0;
        livesChange = false;
        // Set game info based on what level was requested by host
        // TODO: have state_id set map level info - currently hardcoded to test state, but should have switch or series of if/thens
        if (stateId == 22) {


            Mobs = moblist.getMobList(activeLevel);
            Doors = doorList.getDoorList(activeLevel);
            Crates = crateList.getCrateList(activeLevel);
            Keys = keyList.getKeyList(activeLevel);
            if (activeLevel == 1) {
                mapX = 45f;
                mapY = 105f;
                map = new TiledMap(Project2.LEVEL1RSC, Project2.TILESHEETRSC);
            }
            else if (activeLevel == 2) {
                mapX = 86f;
                mapY = 78f;
                map = new TiledMap(Project2.LEVEL2RSC, Project2.TILESHEETRSC);
            }
            Project2.settings.createTileMapping(map, activeLevel);
            for (int i = 0; i < Doors.size(); i++){
                Project2.settings.editTileMapping(Doors.get(i).getWorldPositionX(), Doors.get(i).getWorldPositionY(), "abyss");
            }
            for(Mob mob: Mobs){
                mob.setPosition(new Vector(mob.getWorldPositionX()*32f, mob.getWorldPositionY()*32f));
            }
            for(Door door: Doors){
                door.setPosition(new Vector(door.getWorldPositionX()*32, door.getWorldPositionY()*32));
            }
            for(Crate crate: Crates){
                crate.setPosition(new Vector(crate.getWorldPositionX()*32, crate.getWorldPositionY()*32));
            }
            mapping = Project2.settings.getTilemapping();
            for(Key key: Keys){
                key.setPosition(new Vector(key.getWorldPositionX()*32, key.getWorldPositionY()*32));
            }
        }

        // Set port info
        this.port = port;
        this.stateId = stateId;
    }

    private void switchLevel() throws SlickException {
        Mobs = new ArrayList<>();
        Doors = new ArrayList<>();
        Crates = new ArrayList<>();
        MobBalls = new ArrayList<>();
        HeroBalls = new ArrayList<>();
        moblist = new MobList();
        doorList = new DoorList();
        crateList = new CrateList();
        keyList = new KeyList();
        MoneyDrops = new ArrayList<Money>();
        NewMoneyDrops = new ArrayList<>();
        HealthDrops = new ArrayList<Health>();
        NewHealthDrops = new ArrayList<Health>();
        KeyDrops = new ArrayList<Key>();
        NewKeyDrops = new ArrayList<Key>();
//        playersMoney = 0;
//        clients = new ArrayList<>();

        playersKeys = 0;

        // Set game info based on what level was requested by host
        Mobs = moblist.getMobList(activeLevel);
        Doors = doorList.getDoorList(activeLevel);
        Crates = crateList.getCrateList(activeLevel);
        Keys = keyList.getKeyList(activeLevel);
        if (activeLevel == 1) {
            mapX = 45f;
            mapY = 105f;
            map = new TiledMap(Project2.LEVEL1RSC, Project2.TILESHEETRSC);
        }
        else if (activeLevel == 2) {
            mapX = 86f;
            mapY = 78f;
            map = new TiledMap(Project2.LEVEL2RSC, Project2.TILESHEETRSC);
        }
        Project2.settings.createTileMapping(map, activeLevel);
        for (int i = 0; i < Doors.size(); i++){
            Project2.settings.editTileMapping(Doors.get(i).getWorldPositionX(), Doors.get(i).getWorldPositionY(), "abyss");
        }
        for(Mob mob: Mobs){
            mob.setPosition(new Vector(mob.getWorldPositionX()*32f, mob.getWorldPositionY()*32f));
        }
        for(Door door: Doors){
            door.setPosition(new Vector(door.getWorldPositionX()*32, door.getWorldPositionY()*32));
        }
        for(Crate crate: Crates){
            crate.setPosition(new Vector(crate.getWorldPositionX()*32, crate.getWorldPositionY()*32));
        }
        mapping = Project2.settings.getTilemapping();
        for(Key key: Keys){
            key.setPosition(new Vector(key.getWorldPositionX()*32, key.getWorldPositionY()*32));
        }
    }
    /** Game Functions */
    private void addPlayer(String playerID, boolean type) {
        Hero hero = new Hero(new Vector(mapX, mapY), type, playerID);
        hero.setPosition(new Vector(mapX,mapY));
        Players.add(hero);
        String newChange  = " " + playerID;
        newChange += " " + idle;
        newChange += " " + hero.getWorldPositionX();
        newChange += " " + hero.getWorldPositionY();
        newChange += " " + String.valueOf(type);

        changes += newChange;
    }

    private void ballUpdate() {
        for (int i = 0; i < MobBalls.size(); i++) {
            if ((MobBalls.get(i).getTime() + 1000) <= System.currentTimeMillis() || MobBalls.get(i).getCommand() == rm) {
                String balls = "";
                balls += " " + MobBalls.get(i).getName();
                balls += " " + rm;
                balls += " " + MobBalls.get(i).getWorldPositionX();
                balls += " " + MobBalls.get(i).getWorldPositionY();
                balls += " " + "DoesntMatter";
                changes += balls;
                MobBalls.remove(i);
            } else {
                String balls = "";
                MobBalls.get(i).update();
                balls += " " + MobBalls.get(i).getName();
                balls += " " + up;
                balls += " " + MobBalls.get(i).getWorldPositionX();
                balls += " " + MobBalls.get(i).getWorldPositionY();
                balls += " " + "DoesntMatter";
                changes += balls;
            }
        }
        for (int i = 0; i < HeroBalls.size(); i++) {
            if ((HeroBalls.get(i).getTime() + 1000) <= System.currentTimeMillis() || HeroBalls.get(i).getCommand() == rm) {
                String balls = "";
                balls += " " + HeroBalls.get(i).getName();
                balls += " " + rm;
                balls += " " + HeroBalls.get(i).getWorldPositionX();
                balls += " " + HeroBalls.get(i).getWorldPositionY();
                balls += " " + "DoesntMatter";
                changes += balls;
                HeroBalls.remove(i);
            } else {
                String balls = "";
                HeroBalls.get(i).update();
                balls += " " + HeroBalls.get(i).getName();
                balls += " " + up;
                balls += " " + HeroBalls.get(i).getWorldPositionX();
                balls += " " + HeroBalls.get(i).getWorldPositionY();
                balls += " " + "DoesntMatter";
                changes += balls;
            }
        }
    }

    private void mobRangedAttack(int i) {
        if ((System.currentTimeMillis() - Mobs.get(i).getAttacktimer()) >= Mobs.get(i).getAttackdelay()) {
            String balls = "";
            int num = MobBalls.size();
            String name = "mball" + num;
            MobBalls.add(Mobs.get(i).rangedAttack(name));
            balls += " " + name;
            balls += " " + Mobs.get(i).getLastDirectionCommand();
            balls += " " + Mobs.get(i).getWorldPositionX();
            balls += " " + Mobs.get(i).getWorldPositionY();
            balls += " " + "DoesntMatter";
            changes += balls;
        }
    }

    private void heroRangedAttack(int i) {
        if ((System.currentTimeMillis() - Players.get(i).getAttacktimer()) >= Players.get(i).getAttackdelay()) {
            String balls = "";
            int num = HeroBalls.size();
            String name = "hball" + num;
            HeroBalls.add(Players.get(i).rangedAttack(name));
            balls += " " + name;
            balls += " " + Players.get(i).getLastDirectionCommand();
            balls += " " + Players.get(i).getWorldPositionX();
            balls += " " + Players.get(i).getWorldPositionY();
            balls += " " + "DoesntMatter";
            changes += balls;
        }
    }


/** Server Functions */
    public void init () {
        Players = new ArrayList<>();
        IgnoreList = new ArrayList<Mob>();
        IgnoreCrateList = new ArrayList<Crate>();
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
                    addPlayer(player, Boolean.parseBoolean(tokens[2]));
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
                    if (CollisionManager.CheckHeroDestinationCollision(Players.get(i))) {
                        levelTransition = true;
                    }
                    if (CollisionManager.CheckHeroGoalCollision(Players.get(i))) {
                        win = true;
                    }
                    if (Players.get(i).getName().equals(player)) {
                        // process movement based on input
                        Players.get(i).setCommand(inputCommand);
                        Vector newWorldPosition = CalcWorldPosition(Players.get(i).getCommand(),Players.get(i).getWorldPosition(),Players.get(i).getSpeed());
                        // set map position
                        Players.get(i).setWorldPosition(newWorldPosition);
                        // set jig entity vector for collisions.
                        Players.get(i).setPosition(new Vector(newWorldPosition.getX() * 32f, newWorldPosition.getY() * 32f));
                        float x = Players.get(i).getWorldPositionX();
                        float y = Players.get(i).getWorldPositionY();
                        // if player is ranged and input was attack, call heroRangedAttack
                        if (Players.get(i).isRanged() && inputCommand == attack)
                            heroRangedAttack(i);
                        // check for player/wall collisions
                        CollisionManager.CheckHeroMobCollisions(Players.get(i), Mobs);
                        CollisionManager.CheckHeroCrateCollisions(Players.get(i), Crates);
                        if(CollisionManager.CheckValidMove(Players.get(i))) {
                            // if movement was valid, add update to changes
                            String newChange = " " + player;
                            newChange += " " + Players.get(i).getCommand();
                            newChange += " " + x;
                            newChange += " " + y;
                            newChange += " " + "DoesntMatter";

                            changes += newChange;
                        }

                        Money money;
                        money = CollisionManager.CheckHeroMoneyCollision(Players.get(i),MoneyDrops);
                        if (money != null) {
                            for(int j = 0; j < MoneyDrops.size(); j++){
                                if(MoneyDrops.get(j).getName().contains(money.getName())){
                                    playersMoney += money.value;

                                    if(playersMoney >= 500) {
                                        playersMoney -= 500;
                                        playersLives += 1;
                                        livesChange = true;
                                    }
                                    moneyPickupChanges += " " + money.getName();
                                    MoneyDrops.remove(MoneyDrops.get(j));
                                }
                            }
                        }
                        Health health;
                        health = CollisionManager.CheckHeroHealthCollision(Players.get(i), HealthDrops);
                        if (health != null) {
                            for (int j = 0; j < HealthDrops.size(); j++){
                                if (HealthDrops.get(j).getName().contains(health.getName()) && Players.get(i).getHealth() < 1f){
                                    Players.get(i).setHealth(Players.get(i).getHealth()+.5f);
                                    healthPickupChanges += " " + health.getName();
                                    HealthDrops.remove(HealthDrops.get(j));
                                }
                            }
                        }
                        Key key;
                        key = CollisionManager.CheckHeroKeyCollision(Players.get(i), Keys);
                        if (key != null) {
                            for(int j = 0; j < Keys.size(); j++){
                                if(Keys.get(j).getName().contains(key.getName())){
                                    playersKeys += 1;
                                    keyPickupChanges += " " + key.getName();
                                    Keys.remove(Keys.get(j));
                                }
                            }
                        }
                        if(playersKeys > 0) {
                            Door door;
                            door = CollisionManager.CheckHeroDoorCollision(Players.get(i), Doors);
                            if (door != null) {
                                for (int j = 0; j < Doors.size(); j++) {
                                    if (Doors.get(j).getName().contains(door.getName())) {
                                        playersKeys -= 1;
                                        doorChanges += " " + door.getName();
                                        Doors.remove(Doors.get(j));
                                    }
                                }
                            }
                        }
                    }
                }

                break;
            case "SKIP":
                levelTransition = true;
                break;
            case "ADDKEYS":
                playersKeys += 100;
            default:
//                System.out.println("Server: unknown message received: " + msg);
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
        // TODO: send proper level info, currently hardcoded to level activeLevel
//        System.out.println("Server: sending INIT message");
        String msg = "INIT " + Integer.toString(activeLevel); // Integer.toString(LEVEL_NO)
        for (int i = 0; i < Players.size(); i++) {
            msg += " " + Players.get(i).getName();
            msg += " " + Float.toString(Players.get(i).getWorldPositionX());
            msg += " " + Float.toString(Players.get(i).getWorldPositionY());
            msg += " " + Players.get(i).isRanged();
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
                        newChange += " " + InputCommands.rm;
                        newChange += " " + Players.get(j).getWorldPositionX();
                        newChange += " " + Players.get(j).getWorldPositionY();
                        newChange += " " + "DoesntMatter";
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
//            System.out.println("Running fine");
            Random random = new Random();

            // update position of mob and player projectiles
            ballUpdate();
            CollisionManager.CheckBallsToWall(MobBalls);
            CollisionManager.CheckBallsToWall(HeroBalls);
            for (int bubbles = 0; bubbles < Players.size(); bubbles++) {
                CollisionManager.CheckEntityBallCollisions(Players.get(bubbles), MobBalls);

                //<editor-fold desc="Dijkstra stuffs">
                float playerX = (float)Math.floor(Players.get(bubbles).getWorldPositionX());
                float playerY = (float)Math.floor(Players.get(bubbles).getWorldPositionY());
                Vector playerPosition = new Vector(playerX,playerY);
                Pathfinding.Dijkstra(mapping, playerPosition);
                //</editor-fold>
                for (int i = 0; i < Crates.size(); i++) {
                    CollisionManager.CheckEntityBallCollisions(Crates.get(i), HeroBalls);
                    Vector position = Crates.get(i).getWorldPosition();
                    int value;
                    random = new Random();
                    value = random.nextInt((21 - 1) + 1);
                    if(Crates.get(i).IsDead())
                        if (!IgnoreCrateList.contains(Crates.get(i))) {
                            IgnoreCrateList.add(Crates.get(i));
                            moneyDropChanges = moneyDropChanges(position, Crates.get(i), value+1);
                            crateRemoval += " " + Crates.get(i).getName();
                            Crates.remove(Crates.get(i));
                        }
                }
                for (int i = 0; i < Mobs.size(); i++) {
                    Boolean isIdle = false;
                    String command = "";
                    float mobX = (float)Math.floor(Mobs.get(i).getWorldPositionX());
                    float mobY = (float)Math.floor(Mobs.get(i).getWorldPositionY());
                    Vector cheesyMobs = new Vector(mobX, mobY);

                    // if mob is 5 tiles or less away from player and is not dead
                    if (Pathfinding.range(playerPosition, cheesyMobs) && !Mobs.get(i).IsDead()){
                        command = Pathfinding.getPath((int) mobX, (int) mobY);
                        // if mob is melee or if mob is greater than 3 tiles away, set it's path
                        if (!Mobs.get(i).isRanged() || (!Pathfinding.rangedRange(playerPosition, cheesyMobs))) {
                            if (Pathfinding.meleeRange(playerPosition, cheesyMobs)){Mobs.get(i).ProcessNextAnimation(InputCommands.attack);}
                            else Mobs.get(i).setCommand(getCommand(command));
                        } else {
                            // if mob is ranged and 3 or fewer tiles away, set to idle (so it stops and attacks)
                            // get direction mob has to face to hit player
                            Mobs.get(i).setCommand(InputCommands.attack);
                            isIdle = true;
                        }
                    }

                    Vector newMobPosition = MovementCalc.CalcWorldPosition(Mobs.get(i).getCommand(), Mobs.get(i).getWorldPosition(), Mobs.get(i).getSpeed());
                    Mobs.get(i).setWorldPosition(newMobPosition);
                    Mobs.get(i).setPosition(new Vector(newMobPosition.getX() * 32f, newMobPosition.getY() * 32f));
                    if (Mobs.get(i).getCommand() != InputCommands.death)
                        CollisionManager.CheckMobHeroCollisions(Mobs.get(i), Players);
                    //CollisionManager.CheckMobMobCollisions(Mobs.get(i), Mobs);

                    if (!Mobs.get(i).IsDead()) CollisionManager.CheckEntityBallCollisions(Mobs.get(i), HeroBalls);


                    if (Mobs.get(i).getCommand() == InputCommands.death) {
    //                    System.out.println(mob.getName() + " " + mob.getCommand());
                        Vector position = Mobs.get(i).getWorldPosition();
                        int coinFlip = random.nextInt((2-1) + 1);
                        if (coinFlip == 1) {
                            int diceRoll = random.nextInt((4 - 0));
                            if (!IgnoreList.contains(Mobs.get(i))) {
                                IgnoreList.add(Mobs.get(i));
                                int value;
                                random = new Random();
                                value = random.nextInt((10 - 1) + 1);
                                if (diceRoll == 1)
                                    moneyDropChanges = moneyDropChanges(position, Mobs.get(i), value);
                            }
                        } else {
                            int diceRoll = random.nextInt((4 - 0));
                            if (!IgnoreList.contains(Mobs.get(i))) {
                                IgnoreList.add(Mobs.get(i));
                                if(diceRoll == 1)
                                    healthDropChanges = healthDropChanges(position, Mobs.get(i));
                            }
                        }
                    }

                    // after movements, but before update string, adjust the direction the mob is facing if ranged
                    if (isIdle) {
                        Mobs.get(i).setCommand(getCommand(command));
                        mobRangedAttack(i);
                    }

                    String mobChange  = " " + Mobs.get(i).getName();
                    mobChange += " " + Mobs.get(i).getCommand();
                    mobChange += " " + Mobs.get(i).getWorldPositionX();
                    mobChange += " " + Mobs.get(i).getWorldPositionY();
                    mobChange += " " + Mobs.get(i).isRanged();
                    changes = changes.concat(mobChange);
                }
//                </editor-fold desc="iterate through the mobs">

                //<editor-fold desc= "Player Update Health">
                String msg = "UPDTHLTH "+Players.get(bubbles).getName()
                        + " " + Players.get(bubbles).getHealth();
                send(msg);

                if(Players.get(bubbles).getCommand().equals(InputCommands.death)){
//                    System.out.println("Player lives: "+playersLives);
                    if(playersLives > 0)
                        Players.get(bubbles).setCommand(InputCommands.idle);
                    if(Players.get(bubbles).getCommand().equals(InputCommands.idle)) {
                        playersLives--;
                        livesChange = true;
//                        System.out.println("Changed Command: " + Players.get(bubbles).getCommand());
                        Players.get(bubbles).setHealth(1f);
                    }

//                    System.out.println("server resurrect message: "+msg);
                }
                msg = "UPDT "+Players.get(bubbles).getName()+" "
                        +Players.get(bubbles).getCommand()+" "
                        +Players.get(bubbles).getWorldPositionX()
                        +" "+Players.get(bubbles).getWorldPositionY()
                        +" "+ Players.get(bubbles).isRanged();
                send(msg);

                //</editor-fold desc= "Player Update Health">
            }
            //</editor-fold desc= "for each player logged in">
            if(playersMoney >= 500) {
                playersMoney -= 500;
                playersLives += 1;
                livesChange = true;
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
            if (keyPickupChanges != "") {
                String msg = "PCKUPK " + playersKeys + keyPickupChanges;
                keyPickupChanges = "";
                send(msg);
            }
            if (doorChanges != "") {
                String msg = "OPEND " + playersKeys + doorChanges;
                doorChanges = "";
                send(msg);
            }
            if (crateRemoval != "") {
                String msg = "RMVC" + crateRemoval;
                crateRemoval = "";
                send(msg);
            }
            if (livesChange) {
                String msg = "LIVES " + playersLives;
                livesChange = false;
                send(msg);
            }
            if (levelTransition) {
                playersKeys = 0;
                activeLevel = 2;
//                for(Hero player: Players) {
//                    player.setHealth(1f);
//                    player.setCommand(InputCommands.idle);
//                }

                String msg = "TRNS2 ";
                send(msg);
                Mobs.clear();
                Doors.clear();
                Keys.clear();
                MoneyDrops.clear();
                HealthDrops.clear();
                try{switchLevel();} catch(SlickException e){System.out.print(e);}
                playersKeys = 0;
                for(int i = 0; i < Players.size(); i++) {
                    Players.get(i).setWorldPosition(new Vector(87 + i, 78));
                }
                levelTransition = false;
            }
            if(win) {
                String msg = "WIN ";
                send(msg);
                win = false;
            }
        }
    };
    private String moneyDropChanges(Vector position, BasicBeing mob, int value){
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
        return moneyDropChanges;
    }

    private String healthDropChanges(Vector position, Mob mob) {
        String healthChange = "";
        int value = 1;
        NewHealthDrops.clear();
        try {
            if (value > 0) {
                NewHealthDrops.add(new Health(position, "health" + MoneyDrops.size(), value));
            }
        } catch (SlickException e) {
            System.out.println("Failed to drop health off of " + mob.getName());
        }
        for (int i = 0; i < NewHealthDrops.size(); i++) {
            healthChange += " " + NewHealthDrops.get(i).getName();
            healthChange += " " + NewHealthDrops.get(i).getWorldPositionX();
            healthChange += " " + NewHealthDrops.get(i).getWorldPositionY();
            healthChange += " " + NewHealthDrops.get(i).value;
        }
        HealthDrops.addAll(NewHealthDrops);
        healthDropChanges = healthChange;
        return healthDropChanges;
    }
    private void playerObjectCollisions(){
        Money money;
        money = CollisionManager.CheckHeroMoneyCollision(Players.get(0),MoneyDrops);
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
        health = CollisionManager.CheckHeroHealthCollision(Players.get(0), HealthDrops);
        if (health != null) {
            for (int j = 0; j < HealthDrops.size(); j++){
                if (HealthDrops.get(j).getName().contains(health.getName()) & Players.get(0).getHealth() < 10){
                    Players.get(0).setHealth(Players.get(0).getHealth()+1);
                    healthPickupChanges += " " + health.getName();
                    HealthDrops.remove(HealthDrops.get(j));
                }
            }
        }
        Key key;
        key = CollisionManager.CheckHeroKeyCollision(Players.get(0), Keys);
        if (key != null) {
            for (int j = 0; j < Keys.size(); j++) {
                if(Keys.get(j).getName().contains(key.getName())){
                    playersKeys += 1;
                    keyPickupChanges += " " + key.getName();
                    Keys.remove(Keys.get(j));
                }
            }
        }
        Door door;
        if (playersKeys > 0) {
            door = CollisionManager.CheckHeroDoorCollision(Players.get(0), Doors);
            if (door != null) {
                for (int j = 0; j < Doors.size(); j++) {
                    if (Doors.get(j).getName().equals(door.getName())) {
                        playersKeys -= 1;
                        doorChanges += " " + door.getName();
                        Doors.remove(Doors.get(j));
                    }
                }
            }
        }
    }
}