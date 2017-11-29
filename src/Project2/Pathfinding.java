package Project2;

import jig.Vector;

import java.util.ArrayList;

public class Pathfinding {

    public static boolean range(Vector player, Vector mob){
        float playerXMax = player.getX()+10;
        float playerYMax = player.getY()+10;

        if (mob.getX() >= player.getX() && mob.getX() <= playerXMax &&
                mob.getY() >= player.getY() && mob.getY() <= playerYMax)
            return true;

        return false;
    }

    public static void Dijkstra(){

    }

    /*public static Vector getPath(){

    }*/

    public static void evaluate(){

    }

    public static void initialize(){

    }
}
