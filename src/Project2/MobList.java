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
        if (level == 1) {
            // hallway 1
            mob = new Mob(new Vector(75f, 105.5f), 1, "mob1"); Mobs.add(mob);
            mob = new Mob(new Vector(74f, 104.5f), 1, "mob2"); Mobs.add(mob);
            mob = new Mob(new Vector(73f, 105f), 1, "mob3"); Mobs.add(mob);

            mob = new Mob(new Vector(65f, 104.5f), 1, "mob4"); Mobs.add(mob);
            mob = new Mob(new Vector(64f, 105.5f), 1, "mob5"); Mobs.add(mob);
            mob = new Mob(new Vector(63f, 104f), 1, "mob6"); Mobs.add(mob);
            mob = new Mob(new Vector(62.5f, 106f), 1, "mob7"); Mobs.add(mob);

            mob = new Mob(new Vector(50f, 104f), 1, "mob8"); Mobs.add(mob);
            mob = new Mob(new Vector(49f, 105f), 1, "mob9"); Mobs.add(mob);
            mob = new Mob(new Vector(51.5f, 105f), 1, "mob10"); Mobs.add(mob);
            mob = new Mob(new Vector(50f, 106f), 1, "mob11"); Mobs.add(mob);

            mob = new Mob(new Vector(31f, 104.5f), 1, "mob12"); Mobs.add(mob);
            mob = new Mob(new Vector(30f, 105f), 1, "mob13"); Mobs.add(mob);
            mob = new Mob(new Vector(31f, 105.5f), 1, "mob14"); Mobs.add(mob);

            // room 1
            mob = new Mob(new Vector(22.5f, 104f), 1, "mob15"); Mobs.add(mob);
            mob = new Mob(new Vector(22f, 105f), 1, "mob16"); Mobs.add(mob);
            mob = new Mob(new Vector(23f, 105.5f), 1, "mob17"); Mobs.add(mob);

            mob = new Mob(new Vector(15.5f, 102f), 1, "mob18"); Mobs.add(mob);
            mob = new Mob(new Vector(16.5f, 102f), 1, "mob19"); Mobs.add(mob);
            mob = new Mob(new Vector(15f, 103f), 1, "mob20"); Mobs.add(mob);
            mob = new Mob(new Vector(16f, 103f), 1, "mob21"); Mobs.add(mob);

            mob = new Mob(new Vector(19f, 97f), 1, "mob22"); Mobs.add(mob);
            mob = new Mob(new Vector(20f, 97.5f), 1, "mob23"); Mobs.add(mob);
            mob = new Mob(new Vector(18f, 98f), 1, "mob24"); Mobs.add(mob);
            mob = new Mob(new Vector(19f, 99f), 1, "mob25"); Mobs.add(mob);

            mob = new Mob(new Vector(25f, 98.5f), 1, "mob26"); Mobs.add(mob);
            mob = new Mob(new Vector(24f, 99f), 1, "mob27"); Mobs.add(mob);
            mob = new Mob(new Vector(25f, 99.5f), 1, "mob28"); Mobs.add(mob);
            mob = new Mob(new Vector(23f, 100f), 1, "mob29"); Mobs.add(mob);
            mob = new Mob(new Vector(24f, 100f), 1, "mob30"); Mobs.add(mob);

            mob = new Mob(new Vector(15f, 94f), 1, "mob31"); Mobs.add(mob);
            mob = new Mob(new Vector(14f, 95f), 1, "mob32"); Mobs.add(mob);
            mob = new Mob(new Vector(16f, 95.5f), 1, "mob33"); Mobs.add(mob);

            mob = new Mob(new Vector(24f, 93f), 1, "mob34"); Mobs.add(mob);
            mob = new Mob(new Vector(24f, 94f), 1, "mob35"); Mobs.add(mob);
            mob = new Mob(new Vector(26f, 94f), 1, "mob36"); Mobs.add(mob);
            mob = new Mob(new Vector(23f, 95f), 1, "mob37"); Mobs.add(mob);
            mob = new Mob(new Vector(25f, 95f), 1, "mob38"); Mobs.add(mob);

            mob = new Mob(new Vector(18f, 90f), 1, "mob39"); Mobs.add(mob);
            mob = new Mob(new Vector(20f, 90f), 1, "mob40"); Mobs.add(mob);
            mob = new Mob(new Vector(17f, 91f), 1, "mob41"); Mobs.add(mob);
            mob = new Mob(new Vector(19f, 91.5f), 1, "mob42"); Mobs.add(mob);
            mob = new Mob(new Vector(18f, 92f), 1, "mob43"); Mobs.add(mob);

            mob = new Mob(new Vector(15f, 83f), 1, "mob44"); Mobs.add(mob);
            mob = new Mob(new Vector(16f, 84f), 1, "mob45"); Mobs.add(mob);
            mob = new Mob(new Vector(17f, 84.5f), 1, "mob46"); Mobs.add(mob);
            mob = new Mob(new Vector(15f, 85f), 1, "mob47"); Mobs.add(mob);
            mob = new Mob(new Vector(16f, 86f), 1, "mob48"); Mobs.add(mob);

            mob = new Mob(new Vector(24f, 84f), 1, "mob49"); Mobs.add(mob);
            mob = new Mob(new Vector(25f, 84.5f), 1, "mob50"); Mobs.add(mob);
            mob = new Mob(new Vector(23f, 85f), 1, "mob51"); Mobs.add(mob);
            mob = new Mob(new Vector(25.5f, 85.5f), 1, "mob52"); Mobs.add(mob);
            mob = new Mob(new Vector(24f, 86f), 1, "mob53"); Mobs.add(mob);

            // small hallway off room 1
            mob = new Mob(new Vector(31f, 99f), 1, "mob54"); Mobs.add(mob);
            mob = new Mob(new Vector(30f, 99.5f), 1, "mob55"); Mobs.add(mob);
            mob = new Mob(new Vector(32f, 99.5f), 1, "mob56"); Mobs.add(mob);

        }
        return Mobs;
    }
}
