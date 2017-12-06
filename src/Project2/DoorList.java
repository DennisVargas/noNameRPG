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
//          Room 1
            door = new Door(new Vector(28.5f, 105.25f), "doorV");
            Doors.add(door);
            door = new Door(new Vector(26.5f, 79.25f), "doorH");
            Doors.add(door);
            door = new Door(new Vector(28.5f, 99.25f), "doorV");
            Doors.add(door);

//          Room 3
            door = new Door(new Vector(30.5f, 69.25f), "doorV");
            Doors.add(door);
            door = new Door(new Vector(40.5f, 69.25f), "doorV");
            Doors.add(door);
            door = new Door(new Vector(38.5f, 61.25f), "doorH");
            Doors.add(door);
            door = new Door(new Vector(41.5f, 87.25f), "doorV");
            Doors.add(door);

//          Room 7
            door = new Door(new Vector(52.5f, 99.25f), "doorV");
            Doors.add(door);

//          Room 5
            door = new Door(new Vector(62.5f, 89.25f), "doorH");
            Doors.add(door);
            door = new Door(new Vector(68.5f, 73.25f), "doorH");
            Doors.add(door);

//            Room 8
            door = new Door(new Vector(78.5f, 75.25f), "doorV");
            Doors.add(door);
            door = new Door(new Vector(94.5f, 69.25f), "doorV");
            Doors.add(door);
            door = new Door(new Vector(80.5f, 67.25f), "doorH");
            Doors.add(door);
            door = new Door(new Vector(86.5f, 77.25f), "doorH");
            Doors.add(door);

//            Room 6
            door = new Door(new Vector(84.5f, 51.25f), "doorV");
            Doors.add(door);
            door = new Door(new Vector(94.5f, 39.25f), "doorV");
            Doors.add(door);

//            Room 4
            door = new Door(new Vector(62.5f, 37.25f), "doorH");
            Doors.add(door);
            door = new Door(new Vector(76.5f, 39.25f), "doorV");
            Doors.add(door);
            door = new Door(new Vector(74.5f, 59.25f), "doorH");
            Doors.add(door);

//            Room 2
            door = new Door(new Vector(18.5f, 33.25f), "dooV");
            Doors.add(door);
            door = new Door(new Vector(52.5f, 27.25f), "doorV");
            Doors.add(door);
            door = new Door(new Vector(32.5f, 47.25f), "doorH");
            Doors.add(door);
        }
        return this.Doors;
    }
}
