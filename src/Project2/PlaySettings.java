package Project2;

import jig.Vector;
import org.newdawn.slick.tiled.TiledMap;

/**
 * class which holds the settings for a players game instance.
 */
public class PlaySettings {
    private Map tilemapping;
    private String ipAddress;
    private Vector hostStartLevel1 = new Vector(90,105);
    private Vector guestStartLevel1 = new Vector(91,105);

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

    public Vector getGuestStartLevel1() {
        return guestStartLevel1;
    }



    public Vector getHostStartLevel1() {
        return hostStartLevel1;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Hero getPlayer() {
        return player;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setPlayer(Hero player) {
        this.player = player;
    }
}
