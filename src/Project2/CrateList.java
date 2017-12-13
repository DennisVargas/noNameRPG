package Project2;


import jig.Vector;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.SlickException;

public class CrateList {
    private ArrayList<Crate> Crates;
    public CrateList() {
        Crates = new ArrayList<Crate>();
    }

    public ArrayList getCrateList (int level)  throws SlickException {
        Crate crate;
        int crateNum = 0;
        String crateName = "crate" + crateNum;
        Random random = new Random();
        if (level == 1) {
//            Hallway to Room 1
            crate = new Crate(new Vector(56.5f, 104f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(57.5f, 104f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(58.5f, 104f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(38.5f, 106f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(39.5f, 106f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(40.5f, 106f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//          Room 1
            crate = new Crate(new Vector(13.5f, 80f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(14.5f, 80f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(18.5f, 80f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(19.5f, 80f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(20.5f, 80f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(21.5f, 80f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(22.5f, 80f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(13.5f, 81f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(13.5f, 88f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(13.5f, 89f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(24.5f, 89f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(23.5f, 90f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(24.5f, 90f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(27.5f, 94f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(27.5f, 95f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(27.5f, 96f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(27.5f, 97f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(13.5f, 98f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(13.5f, 99f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(13.5f, 100f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(13.5f, 105f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(13.5f, 106f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(14.5f, 106f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(15.5f, 106f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Hallway off room1
            crate = new Crate(new Vector(31.5f, 92f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(32.5f, 92f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(33.5f, 92f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(31.5f, 93f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(32.5f, 93f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(33.5f, 93f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Room 3
            crate = new Crate(new Vector(34.5f, 62f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(35.5f, 62f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(36.5f, 62f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(31.5f, 73f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(39.5f, 73f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(31.5f, 74f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(38.5f, 74f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(39.5f, 74f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(31.5f, 75f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(31.5f, 76f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(35.5f, 84f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(36.5f, 84f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(37.5f, 84f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(38.5f, 84f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(39.5f, 84f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(31.5f, 87f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(31.5f, 88f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(32.5f, 88f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(33.5f, 88f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(34.5f, 88f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Hallway to Hallway 7-5
            crate = new Crate(new Vector(56.5f, 74f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(57.5f, 74f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(57.5f, 75f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(57.5f, 76f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Hallway 3-5
            crate = new Crate(new Vector(45.5f, 56f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(45.5f, 57f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(55.5f, 62f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(56.5f, 62f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(61.5f, 68f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(62.5f, 68f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(63.5f, 68f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(63.5f, 69f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(58.5f, 70f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(59.5f, 70f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(60.5f, 70f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(63.5f, 70f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Hallway 3-2
            crate = new Crate(new Vector(13.5f, 50f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(14.5f, 50f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(13.5f, 51f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(13.5f, 52f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(31.5f, 52f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(32.5f, 52f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(21.5f, 56f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(22.5f, 56f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(23.5f, 56f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(31.5f, 56f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(32.5f, 56f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(33.5f, 56f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(17.5f, 62f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(18.5f, 62f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(19.5f, 62f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(22.5f, 64f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(23.5f, 64f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(24.5f, 64f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Room 7
            crate = new Crate(new Vector(43.5f, 80f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(44.5f, 80f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(45.5f, 80f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(46.5f, 80f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(43.5f, 81f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(43.5f, 90f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(43.5f, 91f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(43.5f, 100f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(44.5f, 100f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(48.5f, 100f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(49.5f, 100f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Room 5
            crate = new Crate(new Vector(64.5f, 74f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(65.5f, 74f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(66.5f, 74f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(61.5f, 79f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(61.5f, 80f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(61.5f, 81f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(61.5f, 82f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(61.5f, 83f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(69.5f, 86f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(64.5f, 87f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(65.5f, 87f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(69.5f, 87f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Room 8
            crate = new Crate(new Vector(84.5f, 68f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(85.5f, 68f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(86.5f, 68f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(93.5f, 68f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(93.5f, 69f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(79.5f, 73f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(79.5f, 74f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(79.5f, 75f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(79.5f, 76f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(80.5f, 76f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Room 6
            crate = new Crate(new Vector(85.5f, 38f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(86.5f, 38f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(85.5f, 39f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(85.5f, 45f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(85.5f, 46f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(85.5f, 47f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(85.5f, 56f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(85.5f, 57f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(85.5f, 58f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(85.5f, 59f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(90.5f, 64f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(91.5f, 64f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Hallway 6-8
            crate = new Crate(new Vector(80.5f, 50f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(81.5f, 50f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(82.5f, 50f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(79.5f, 56f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(79.5f, 57f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(79.5f, 58f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(81.5f, 61f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(81.5f, 62f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(81.5f, 63f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Hallway 6-2
            crate = new Crate(new Vector(60.5f, 20f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(61.5f, 20f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(62.5f, 20f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(87.5f, 20f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(88.5f, 20f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(76.5f, 22f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(77.5f, 22f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(78.5f, 22f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(97.5f, 27f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(97.5f, 28f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(97.5f, 29f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(91.5f, 30f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(97.5f, 30f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(91.5f, 31f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(91.5f, 32f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(92.5f, 34f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(93.5f, 34f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Room 4
            crate = new Crate(new Vector(68.5f, 38f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(69.5f, 38f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(70.5f, 38f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(59.5f, 41f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(60.5f, 42f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(75.5f, 42f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(75.5f, 43f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(55.5f, 44f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(75.5f, 44f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(55.5f, 45f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(75.5f, 45f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(55.5f, 46f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(55.5f, 47f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(70.5f, 47f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(55.5f, 48f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(69.5f, 48f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(70.5f, 48f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(59.5f, 52f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(60.5f, 52f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(59.5f, 53f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(60.5f, 53f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(55.5f, 58f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(56.5f, 58f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(63.5f, 58f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(64.5f, 58f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Hallway off Room 4
            crate = new Crate(new Vector(65.5f, 32f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(66.5f, 32f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(67.5f, 32f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(68.5f, 32f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(70.5f, 34f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(71.5f, 34f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(72.5f, 34f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(73.5f, 34f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Hallway 4-2
            crate = new Crate(new Vector(81.5f, 37f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(81.5f, 38f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Hallway 4-8
            crate = new Crate(new Vector(75.5f, 65f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(75.5f, 66f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(75.5f, 67f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
//            Room 2
            crate = new Crate(new Vector(25.5f, 20f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(26.5f, 20f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(27.5f, 20f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(36.5f, 28f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(37.5f, 28f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(36.5f, 29f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(37.5f, 29f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(33.5f, 31f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(34.5f, 31f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(19.5f, 35f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(25.5f, 35f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(19.5f, 36f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(25.5f, 36f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(26.5f, 36f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(43.5f, 37f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(42.5f, 38f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(30.5f, 40f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(31.5f, 40f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(32.5f, 40f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(33.5f, 40f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(30.5f, 41f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(31.5f, 41f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(32.5f, 41f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(33.5f, 41f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(51.5f, 43f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(51.5f, 44f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(51.5f, 45f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
        } else if (level == 2){
//            Room 8
            crate = new Crate(new Vector(29.5f, 17f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(30.5f, 17f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(31.5f, 17f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(35.5f, 17f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(36.5f, 17f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(37.5f, 17f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(37.5f, 18f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(32.5f, 19f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(33.5f, 19f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(34.5f, 19f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(37.5f, 19f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
            crate = new Crate(new Vector(37.5f, 20f), crateName); crateNum++; crateName = "crate" + crateNum;
            Crates.add(crate);
        }
        return this.Crates;
    }
}
