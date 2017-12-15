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
        int doorNum = 0;
        String doorVName = "doorV" + doorNum;
        String doorHName = "doorH" + doorNum;
        if (level == 1) {
//          Room 1
            door = new Door(new Vector(28.5f, 105.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(26.5f, 79.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
            Doors.add(door);
//            door = new Door(new Vector(28.5f, 99.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
//            Doors.add(door);

//          Room 3
//            door = new Door(new Vector(30.5f, 69.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
//            Doors.add(door);
            door = new Door(new Vector(40.5f, 69.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(38.5f, 61.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
            Doors.add(door);
//            door = new Door(new Vector(41.5f, 87.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
//            Doors.add(door);

//          Room 7
//            door = new Door(new Vector(52.5f, 99.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
//            Doors.add(door);

//          Room 5
//            door = new Door(new Vector(62.5f, 89.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
//            Doors.add(door);
//            door = new Door(new Vector(68.5f, 73.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
//            Doors.add(door);

//            Room 8
            door = new Door(new Vector(78.5f, 75.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(94.5f, 69.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(80.5f, 67.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
            Doors.add(door);
//            door = new Door(new Vector(86.5f, 77.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
//            Doors.add(door);

//            Room 6
//            door = new Door(new Vector(84.5f, 51.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
//            Doors.add(door);
//            door = new Door(new Vector(94.5f, 39.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
//            Doors.add(door);

//            Room 4
//            door = new Door(new Vector(62.5f, 37.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
//            Doors.add(door);
            door = new Door(new Vector(76.5f, 39.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(74.5f, 59.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
            Doors.add(door);

//            Room 2
//            door = new Door(new Vector(18.5f, 33.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
//            Doors.add(door);
            door = new Door(new Vector(52.5f, 27.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(32.5f, 47.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
            Doors.add(door);
        } else if (level == 2) {
            door = new Door(new Vector(26.5f, 18.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(80.5f, 18.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(32.5f, 30.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(60.5f, 33.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(18.5f, 82.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(24.5f, 82.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(36.5f, 82.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(42.5f, 82.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(42.5f, 92.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(92.5f, 96.25f), doorVName); doorNum++; doorVName = "doorV" + doorNum;
            Doors.add(door);
            door = new Door(new Vector(72.5f, 104.25f), doorHName); doorNum++; doorHName = "doorH" + doorNum;
            Doors.add(door);
        }
        return this.Doors;
    }
}
