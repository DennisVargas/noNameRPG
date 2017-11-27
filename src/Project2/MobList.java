package Project2;


import jig.Vector;

import java.util.ArrayList;

public class MobList {

    private ArrayList<Mob> Mobs;

    public MobList() {
        Mobs = new ArrayList<Mob>();
    }

    public ArrayList getMobList (int level) {
        Mob mob1, mob2, mob3, mob4, mob5, mob6;
        if (level == 1) {
            mob1 = new Mob(new Vector(81f, 104f), 1, "mob1");
            mob2 = new Mob(new Vector(80.5f, 104.5f), 1, "mob2");
            mob3 = new Mob(new Vector(81.6f, 104.3f), 1, "mob3");
            mob4 = new Mob(new Vector(81.1f, 105f), 1, "mob4");
            mob5 = new Mob(new Vector(79.5f, 105f), 1, "mob5");
            mob6 = new Mob(new Vector(79.7f, 105.5f), 1, "mob6");
            Mobs.add(mob1);
            Mobs.add(mob2);
            Mobs.add(mob3);
            Mobs.add(mob4);
            Mobs.add(mob5);
            Mobs.add(mob6);
        }
        return Mobs;
    }
}
