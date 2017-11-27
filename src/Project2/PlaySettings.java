package Project2;

import jig.Vector;

public class PlaySettings {
    private String ipAddress;
    private Vector hostStartLevel1 = new Vector(90,105);
    private Vector guestStartLevel1 = new Vector(91,105);

    private Hero player;

    public PlaySettings(String ipAddress) {
        this.ipAddress = ipAddress;
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
