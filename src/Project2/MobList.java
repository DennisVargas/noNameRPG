package Project2;


import jig.Vector;

import java.util.ArrayList;

public class MobList {

    private ArrayList<Mob> Mobs;

    public MobList() {
        Mobs = new ArrayList<Mob>();
    }

    public ArrayList getMobList (int level) {
        Mob mob;
        int mobNum = 1;
        String mobName = "mob" + mobNum;
        if (level == 1) {
            // hallway 1
//            mob = new Mob(new Vector(75f, 105.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            mob = new Mob(new Vector(74f, 104.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            mob = new Mob(new Vector(73f, 105f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

//            mob = new Mob(new Vector(65f, 104.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            mob = new Mob(new Vector(64f, 105.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            mob = new Mob(new Vector(63f, 104f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            mob = new Mob(new Vector(62.5f, 106f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            /*mob = new Mob(new Vector(50f, 104f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(49f, 105f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(51.5f, 105f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(50f, 106f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
*/
            mob = new Mob(new Vector(31f, 104.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(30f, 105f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(31f, 105.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            // room 1
            mob = new Mob(new Vector(23f, 104f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(22f, 105f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23.5f, 105.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(15.5f, 102f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(16.5f, 102f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(15f, 103f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(16f, 103f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(19f, 97f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(20f, 97.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(18f, 98f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(19f, 99f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(25f, 98.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(24f, 99f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(25f, 99.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23f, 100f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(24f, 100f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(15f, 94f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(14f, 95f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(16f, 95.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(24f, 93f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(24f, 94f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(26f, 94f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23f, 95f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(25f, 95f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(18f, 90f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(20f, 90f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(17f, 91f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(19f, 91.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(18f, 92f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(15f, 83f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(16f, 84f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(17f, 84.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(15f, 85f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(16f, 86f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(24f, 84f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(25f, 84.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23f, 85f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(25.5f, 85.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(24f, 86f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            // small hallway off room 1
/*            mob = new Mob(new Vector(31f, 99f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(30f, 99.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(32f, 99.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            // room 2
            mob = new Mob(new Vector(21.5f, 22.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(22.5f, 23.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(22.5f, 26.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(24.5f, 27.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23f, 28.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(22.5f, 29.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(32.5f, 22.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(31.5f, 24.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(33.5f, 24.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(30.5f, 25.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(35.5f, 25.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(43.5f, 22.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(39.5f, 24.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(41.5f, 25f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(41.5f, 26.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(43.5f, 26.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(49f, 23f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(47.5f, 24f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(48.5f, 24.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(32f, 30f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(29.5f, 30.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(28.5f, 32f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(31.5f, 32.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(42.5f, 30f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(44f, 31.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(41.5f, 32f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(40.5f, 32.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(43.5f, 33.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(48f, 29.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(49.5f, 30.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(48.5f, 31.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(24f, 35.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(22.5f, 37.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23.5f, 38.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(25.5f, 38.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(34.5f, 34.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(31.5f, 36.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(33.5f, 36.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(32.5f, 38f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(30.5f, 38.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(34.5f, 38.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(38f, 37.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(40f, 37.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(39.5f, 38.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(48.5f, 36.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(46.5f, 37.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(48.5f, 38.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(49.5f, 38.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(47.5f, 39.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(25f, 42.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(22.5f, 43.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(26.5f, 43.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(25f, 44.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23.5f, 45.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(37.5f, 42.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(35.5f, 43.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(34.5f, 44.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(36.5f, 45f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(47.5f, 43f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(44f, 43.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(43.5f, 44.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(45.5f, 45f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(41.5f, 45.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
*/
            // room 3
            mob = new Mob(new Vector(33f, 64.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(32.5f, 63.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(33.5f, 63.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(32f, 62.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(38.5f, 68.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(36.5f, 69.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(38f, 69.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(37.5f, 70.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(38.5f, 70.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(34.5f, 73.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(33.5f, 74.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(34.5f, 75f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(33.5f, 75.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(37.5f, 77.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(35.5f, 78.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(36.5f, 78.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(37.5f, 79.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(36.5f, 80f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(32.5f, 81.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(33.5f, 81.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(32.5f, 86.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(33.5f, 87f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(38.5f, 86.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(37.5f, 87f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(38.5f, 87.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(39.5f, 87.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            // room 4
/*            mob = new Mob(new Vector(56.5f, 39.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(58.5f, 39.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(57f, 40.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(63.5f, 40.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(66.5f, 41f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(65f, 41.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(64f, 43f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(66f, 43.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(72.5f, 39.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(73.5f, 40.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(70.5f, 42.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(72.5f, 42.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(71f, 43.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(73.5f, 43.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(72f, 45f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(59f, 46.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(58f, 47.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(60f, 47.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(59f, 49f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(60.5f, 49.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(67.5f, 47.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(64.5f, 48.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(66f, 49f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(66.5f, 50.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(70.5f, 49.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(72f, 50f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(71f, 51f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(72.5f, 51.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(57.5f, 54.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(60.5f, 55.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(57.5f, 56.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(59f, 56.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(58.5f, 57.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(66.5f, 54f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(68f, 55f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(65.5f, 55.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(68.5f, 56f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(67.5f, 56.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(74.5f, 54f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(73.5f, 54.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(73.5f, 55.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(72.5f, 56.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            // sm hallway off room 4
            mob = new Mob(new Vector(62.5f, 34.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(66.5f, 34f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(68.5f, 33.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(71.5f, 33.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(73.5f, 32.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
*/
            // room 5
            mob = new Mob(new Vector(62.5f, 75.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(65.5f, 76.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(67.5f, 76.6f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(64f, 77f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(63.5f, 78.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(67f, 80.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(68.5f, 81.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(66f, 82f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(67.5f, 82.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(66f, 85.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(64.5f, 86.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(67.5f, 86.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(62.5f, 87.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(66.5f, 87.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            // room 6
/*            mob = new Mob(new Vector(91f, 39f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(90f, 40f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(92f, 40f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(90f, 41f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(92.5f, 41.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(87f, 42f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(86.5f, 43f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(88f, 44f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(89f, 47f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(91f, 47f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(88f, 48f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(90f, 48.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(89f, 49f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(87f, 52f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(88f, 52.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(87f, 53f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(92f, 52f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(91f, 52.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(91f, 53.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(92f, 54f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(91f, 56.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(88f, 57f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(89f, 57.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(90f, 57.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(88f, 58.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(92.5f, 60.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(90.5f, 61f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(91.5f, 62f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(90.5f, 62.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(87.5f, 62.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(86f, 63f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(87f, 64f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
*/
            // room 7
            mob = new Mob(new Vector(49f, 81.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(51f, 81.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(50f, 82.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(49f, 83.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(50.5f, 83.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(44.5f, 86.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(45.5f, 87.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(44.5f, 88.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(50.5f, 87.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(49.5f, 88f),3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(50.5f, 89f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(48.5f, 89.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(50.5f, 90.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(45.5f, 93.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(46.5f, 94.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(44.5f, 95f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(48f, 95f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(49.5f, 95.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(50.5f, 95.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(47.5f, 96.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(44.5f, 98.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(47f, 99f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(45.5f, 99.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            // room 8
/*            mob = new Mob(new Vector(80.5f, 69.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(81f, 69.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(79.5f, 71f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum; // 2

            mob = new Mob(new Vector(85.5f, 69.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(86.5f, 70.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(87.5f, 70.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(87.5f, 72.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum; // 2

            mob = new Mob(new Vector(91.5f, 69f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(92.5f, 70.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(82.5f, 73.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(84.5f, 73.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(82f, 74.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(83.5f, 75f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(92.5f, 73.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;// 2
            mob = new Mob(new Vector(90.5f, 74.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(91.5f, 75f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(92.5f, 75.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            // sm hallway off room 8
            mob = new Mob(new Vector(88.5f, 81.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
*/
            // hallway from 2-3
            mob = new Mob(new Vector(17.5f, 51.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(19.5f, 51f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(24.5f, 52f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(26.5f, 51.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(27.5f, 51f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(32.5f, 49.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(17.5f, 58f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(19.5f, 57.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(27f, 57f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(28f, 58f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(29.5f, 57.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(38.5f, 58f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(39.5f, 57.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(41f, 57.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(14f, 60f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(15f, 61f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(17.5f, 62.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(19.5f, 62f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(22.5f, 63f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(25.5f, 63f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(26.5f, 63.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            // hallway from 3-5
            mob = new Mob(new Vector(44.5f, 60.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(45f, 62.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(48.5f, 64f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(50.5f, 63.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(51.5f, 63.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(61.5f, 64f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(62.5f, 63.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(65f, 63f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(68f, 64f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(69f, 65f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(56f, 67f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(57f, 68f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(56.5f, 69.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(68f, 70f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(69f, 70f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            // hallway from 3-7-5
            mob = new Mob(new Vector(50.5f, 72.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(50f, 74.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(51.5f, 75.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(57f, 84f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(56f, 85.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(57f, 86.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(57f, 95.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(56f, 96.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(56.5f, 98f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(62f, 96.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(62.5f, 98f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(62.5f, 99.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            // hallway from 5-4
/*            mob = new Mob(new Vector(75f, 61.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(74.5f, 62.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(74f, 67f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(74f, 71f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(72.5f, 71.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            // hallway from 4-2
            mob = new Mob(new Vector(78.5f, 27.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(80.5f, 28.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(80.5f, 30.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(80.5f, 33.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(80f, 34.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(79.5f, 39f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(78.5f, 40f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(62.5f, 26.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(63.5f, 27.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(62.5f, 28f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(70.5f, 27f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(71.5f, 28f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            // hallway from 6-2
            mob = new Mob(new Vector(65.5f, 22f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(66.5f, 21f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(68f, 21.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(74f, 21f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(76.5f, 21.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(85.5f, 21.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(87f, 22f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(94.5f, 21.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(96.5f, 21.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(92f, 24f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(91.5f, 28.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(92f, 31.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

            mob = new Mob(new Vector(99f, 27.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(98.5f, 31.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(98f, 36.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(99f, 37.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
*/
        } else if (level == 2) {
//            Room 8
            mob = new Mob(new Vector(16f, 17.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23f, 18.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(13f, 19.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(19f, 19.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(29f, 19.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(16f, 21.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(36f, 21.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(21f, 22.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(13f, 23.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(20f, 25.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23f, 25.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(27f, 26.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(15f, 27.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(18f, 27.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(36f, 27.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23f, 29.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(27f, 29.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(13f, 30.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            Room 6
            mob = new Mob(new Vector(59f, 18.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(69f, 18.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(65f, 19.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(74f, 19.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(56f, 20.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(54f, 22.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(60f, 22.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(54f, 22.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(76f, 22.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(67f, 24.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(57f, 25.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(62f, 26.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(56f, 28.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(59f, 29.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(64f, 29.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(54f, 30.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            Hallway 6-7
            mob = new Mob(new Vector(102f, 27.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(102f, 34.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(101f, 39.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(103f, 39.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(102f, 41.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(101f, 43.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(102f, 56.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(103f, 58.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(101f, 59.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(102f, 65.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(102f, 34.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(102f, 96.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(103f, 105.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(83f, 108.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(98f, 108.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            Room 9
            mob = new Mob(new Vector(19f, 36.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23f, 37.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(28f, 37.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(33f, 38.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(25f, 40.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(31f, 40.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(12f, 42.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(22f, 42.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(28f, 42.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(18f, 43.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(332f, 43.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(14f, 44.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(24f, 45.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(31f, 46.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(15f, 47.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(20f, 47.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(28f,47.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(34f, 47.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            Room 4
            mob = new Mob(new Vector(63f, 37.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(50f, 38.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(56f, 39.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(62f, 43.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(49f, 44.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(54f, 45.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(62f, 47.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(58f, 50.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(64f, 53.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            Room 2
            mob = new Mob(new Vector(78f, 30.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(75f, 33.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(74f, 36.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(79f, 36.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(82f, 39.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(85f, 39.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(74f, 41.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(95f, 42.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(89f, 43.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(76f, 44.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(82f, 45.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(85f, 45.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(73f, 46.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(78f, 47.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(93f, 47.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(81f, 50.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(93f, 50.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(88f, 53.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(82f, 54.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(87f, 55.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(92f, 55.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(84f, 58.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(89f, 58.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(94f, 59.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            Room 10
            mob = new Mob(new Vector(14f, 55.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(17f, 55.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23f, 55.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(12f, 57.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(21f, 57.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(13f, 59.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(19f, 59.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(22f, 59.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            Room 5
            mob = new Mob(new Vector(32f, 61.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(35f, 61.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(34f, 63.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(36f, 63.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(28f, 67.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(33f, 68.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(38f, 68.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(26f, 70.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(40f, 72.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(32f, 73.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(34f, 73.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(32f, 77.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(41f, 77.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            Room 3
            mob = new Mob(new Vector(49f, 66.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(53f, 67.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(60f, 67.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(62f, 67.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(61f, 69.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(62f, 73.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(52f, 75.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(50f, 79.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(62f, 79.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(52f, 80.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(60f, 80.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(46f, 84.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(62f, 85.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(52f, 86.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(42f, 87.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(60f, 89.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(47f, 90.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            Hallway 3-7
            mob = new Mob(new Vector(50f, 101.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(42f, 102.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(59f, 108.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(67f, 108.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(57f, 109.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            Room 1
            mob = new Mob(new Vector(16f, 85.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(18f, 85.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(20f, 85.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(28f, 87.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(30f, 87.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(32f, 87.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(18f, 89.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(20f, 89.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(27f, 89.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(28f, 89.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(30f, 89.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(32f, 89.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(33f, 89.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(17f, 91.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(19f, 91.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(21f, 91.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(15f, 93.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(27f, 93.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(29f, 93.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(14f, 95.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(16f, 95.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(27f, 95.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(29f, 95.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(21f, 96.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23f, 96.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(21f, 98.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(23f, 98.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(14f, 99.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(15f, 99.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(26f, 99.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(30f, 99.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(12f, 101.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(14f, 101.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(15f, 101.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(17f, 101.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(20f, 101.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(22f, 101.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(28f, 101.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(20f, 103.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(22f, 103.5f), 1, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(26f, 103.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(30f, 103.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
//            Room 7
            mob = new Mob(new Vector(72f, 84.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(74f, 84.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(83f, 85.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(72f, 86.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(74f, 86.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(81f, 86.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(84f, 89.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(73f, 90.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(78f, 91.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(80f, 91.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(77f, 93.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(79f, 93.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(81f, 93.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(78f, 95.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(80f, 95.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(64f, 97.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(69f, 97.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(86f, 98.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(62f, 99.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(81f, 99.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(66f, 100.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(73f, 100.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(61f, 102.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(80f, 102.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;
            mob = new Mob(new Vector(88f, 102.5f), 3, mobName); Mobs.add(mob); mobNum++; mobName = "mob" + mobNum;

        }
        return Mobs;
    }
}
