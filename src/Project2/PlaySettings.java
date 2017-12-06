package Project2;

import jig.Vector;
import org.newdawn.slick.tiled.TiledMap;

/**
 * class which holds the settings for a players game instance.
 */
public class PlaySettings {
    private Map tilemapping;
    private String ipAddress;

//    private Vector hostStartLevel1 = new Vector(90,105);
//    private Vector guestStartLevel1 = new Vector(91,105);
    public InputManager.InputCommands [] randoCommands = {InputManager.InputCommands.up,
                    InputManager.InputCommands.down, InputManager.InputCommands.right,InputManager.InputCommands.left,
                    InputManager.InputCommands.ulDiag, InputManager.InputCommands.urDiag, InputManager.InputCommands.dlDiag,
                    InputManager.InputCommands.drDiag};

    private String serverIP = "localhost";
    private int port = 1234;
    private boolean hosting = false; // true if player is hosting multiplayer (so server allows other players)
    private boolean joining = false; // true if player is joining multiplayer (to block singleplayer server launch)
    private Vector hostStartLevel1 = new Vector(90.5f,105.5f);
    private Vector guestStartLevel1 = new Vector(91.5f,105.5f);


    private Hero player;

    /**
     * initializes the PlaySettings by passing the Players IP Address
     * @param ipAddress String holding the players ipAddress
     */
    public PlaySettings(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void createTileMapping(TiledMap map, int level){
        this.tilemapping = new Map(map, level);
    }

    public String checkTile(int x, int y){
        return tilemapping.tiles[x][y].getType();
    }

    public Vector getGuestStartLevel1() {
        return guestStartLevel1;
    }



    public Vector getHostStartLevel1() {
        return hostStartLevel1;
    }

    public String getserverIP() {
        return serverIP;
    }

    public void setserverIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() { return port; }

    public void setPort(int p) { port = p; };

    public Hero getPlayer() {
        return player;
    }

    public void setPlayer(Hero player) {
        this.player = player;
    }

    public boolean getHosting() { return hosting; }

    public void setHosting(boolean host) { hosting = host; }

    public boolean getJoining() { return joining; }

    public void setJoining(boolean join) { joining = join; }
}
