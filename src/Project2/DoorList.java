package Project2;


import jig.Vector;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;

public class DoorList {

    private ArrayList<Door> Doors;

    public DoorList() {
        Doors = new ArrayList<Door>();
    }

    public ArrayList getDoorList (int level)  throws SlickException {
        Door door;
        if (level == 1) {
            // Room 1
//            mob = new Mob(new Vector(75f, 105.5f), 1, "mob1"); Mobs.add(mob);
            door = new Door(new Vector(28.5f, 105.25f), "doorV");
            Doors.add(door);

        }
        return this.Doors;
    }
}
