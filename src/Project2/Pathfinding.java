package Project2;

import jig.Vector;

import java.util.ArrayList;

public class Pathfinding {
    public static float [][] distanceFromPlayer = new float[150][150];
    public static Vector [][] path = new Vector[150][150];
    public static ArrayList<Tile> mapCopy = new ArrayList<>();

    public static boolean range(Vector player, Vector mob){
        float playerXMax = player.getX()+10;
        float playerYMax = player.getY()+10;

        if (mob.getX() >= player.getX() && mob.getX() <= playerXMax &&
                mob.getY() >= player.getY() && mob.getY() <= playerYMax)
            return true;

        return false;
    }

    public static void Dijkstra(Map tiles, Vector playerPosition){
        initialize(playerPosition.getX(), playerPosition.getY());
        makeGraphCopy(tiles);
    }

    /*public static Vector getPath(){

    }*/

    public static void evaluate(){
        //double something = Math.hypot()
    }

    public static void initialize(float x, float y){
        for (int i = 0; i < 150; i++){
            for (int j = 0; j < 150; j++){
                distanceFromPlayer[i][j] = 100;
            }
        }
        for (int i = 0; i < 150; i++){
            for (int j = 0; j < 150; j++){
                path[i][j] = null;
            }
        }
        distanceFromPlayer[(int)x][(int)y] = 0;
        path[(int)x][(int)y] = new Vector(x, y);
    }
    public static void makeGraphCopy(Map map){
        //use mapCopy
        for (int i = 0; i < 150; i++){
            for (int j = 0; j < 150; j++){
                if(!map.tiles[i][j].getType().equalsIgnoreCase("abyss")){
                    // Tile(int x, int y, String type, int level)
                    Tile tempTile = new Tile(map.tiles[i][j].getCoordX(),
                            map.tiles[i][j].getCoordY(),
                            map.tiles[i][j].getType(),
                            map.tiles[i][j].getLevel());
                    tempTile.neighbors(map.tiles[i][j]);
                    mapCopy.add(tempTile);
                }
            }
        }
    }
}
