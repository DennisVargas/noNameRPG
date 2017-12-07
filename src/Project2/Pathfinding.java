package Project2;

import jig.Vector;

import java.util.ArrayList;

public class Pathfinding {
    public static float [][] distanceFromPlayer = new float[150][150];
    public static Vector [][] path = new Vector[150][150];
    public static ArrayList<Tile> mapCopy = new ArrayList<>();
    //public static boolean print = true;

    public static boolean range(Vector player, Vector mob){
        float playerXMin =  player.getX()-5;
        float playerYMin = player.getY()-5;
        float playerXMax = player.getX()+5;
        float playerYMax = player.getY()+5;

        if (mob.getX() >= playerXMin && mob.getX() <= playerXMax &&
                mob.getY() >= playerYMin && mob.getY() <= playerYMax)
            return true;

        return false;
    }

    public static void Dijkstra(Map tiles, Vector playerPosition){
        initialize(playerPosition.getX(), playerPosition.getY() );
        makeGraphCopy(tiles, playerPosition.getX(), playerPosition.getY());


        while(mapCopy.size() > 0){
            float lowValue =1000;
            int lowSpot = 0;

            for (int i =0; i < mapCopy.size(); i++){
                int x = mapCopy.get(i).getXoff();
                int y = mapCopy.get(i).getYoff();
                if (distanceFromPlayer[x][y] < lowValue){
                    lowValue = distanceFromPlayer[x][y];
                    lowSpot = i;
                }
                /*if (print)
                    System.out.println("i:"+i+" lowValue:"+(int)lowValue+ " x:"+x+" y:"+y);*/
            }
            //print = false;
            if (lowValue < 100){
                int neighborX, neighborY;
                 /*  a[x-1,y-1] b[x,y-1] c[x+1,y-1]
                 *  d[x-1,y]   [x,y]   e[x+1,y]
                 *  f[x-1,y+1] g[x,y+1] h[x+1,y+1]
                 * */
                if (mapCopy.get(lowSpot).a){
                    neighborX = (mapCopy.get(lowSpot).getXoff())-1;
                    neighborY = (mapCopy.get(lowSpot).getYoff())-1;
                    evaluate(mapCopy.get(lowSpot).getXoff(),
                            mapCopy.get(lowSpot).getYoff(),
                            neighborX, neighborY);
                }
                if (mapCopy.get(lowSpot).b){
                    neighborX = (mapCopy.get(lowSpot).getXoff());
                    neighborY = (mapCopy.get(lowSpot).getYoff())-1;
                    evaluate(mapCopy.get(lowSpot).getXoff(),
                            mapCopy.get(lowSpot).getYoff(),
                            neighborX, neighborY);
                }
                if (mapCopy.get(lowSpot).c){
                    neighborX = (mapCopy.get(lowSpot).getXoff())+1;
                    neighborY = (mapCopy.get(lowSpot).getYoff())-1;
                    evaluate(mapCopy.get(lowSpot).getXoff(),
                            mapCopy.get(lowSpot).getYoff(),
                            neighborX, neighborY);
                }
                if (mapCopy.get(lowSpot).d){
                    neighborX = (mapCopy.get(lowSpot).getXoff())-1;
                    neighborY = (mapCopy.get(lowSpot).getYoff());
                    evaluate(mapCopy.get(lowSpot).getXoff(),
                            mapCopy.get(lowSpot).getYoff(),
                            neighborX, neighborY);
                }
                if (mapCopy.get(lowSpot).e){
                    neighborX = (mapCopy.get(lowSpot).getXoff())+1;
                    neighborY = (mapCopy.get(lowSpot).getYoff());
                    evaluate(mapCopy.get(lowSpot).getXoff(),
                            mapCopy.get(lowSpot).getYoff(),
                            neighborX, neighborY);
                }
                if (mapCopy.get(lowSpot).f){
                    neighborX = (mapCopy.get(lowSpot).getXoff())-1;
                    neighborY = (mapCopy.get(lowSpot).getYoff())+1;
                    evaluate(mapCopy.get(lowSpot).getXoff(),
                            mapCopy.get(lowSpot).getYoff(),
                            neighborX, neighborY);
                }
                if (mapCopy.get(lowSpot).g){
                    neighborX = (mapCopy.get(lowSpot).getXoff());
                    neighborY = (mapCopy.get(lowSpot).getYoff())+1;
                    evaluate(mapCopy.get(lowSpot).getXoff(),
                            mapCopy.get(lowSpot).getYoff(),
                            neighborX, neighborY);
                }
                if (mapCopy.get(lowSpot).h){
                    neighborX = (mapCopy.get(lowSpot).getXoff())+1;
                    neighborY = (mapCopy.get(lowSpot).getYoff())+1;
                    evaluate(mapCopy.get(lowSpot).getXoff(),
                            mapCopy.get(lowSpot).getYoff(),
                            neighborX, neighborY);
                }
            }
            mapCopy.remove(lowSpot);
        }
    }

    public static String getPath(int x, int y){
        if(path[x][y] != null) {
            int pathX = (int) path[x][y].getX();
            int pathY = (int) path[x][y].getY();
        /*
        * a, b, c
        * d,  , e
        * f, g, h
        * */

            if (pathX == x - 1 && pathY == y - 1) {
                return "ulDiag";
            } else if (pathX == x && pathY == y - 1) {
                return "up";
            } else if (pathX == x + 1 && pathY == y - 1) {
                return "urDiag";
            } else if (pathX == x - 1 && pathY == y) {
                return "left";
            } else if (pathX == x + 1 && pathY == y) {
                return "right";
            } else if (pathX == x - 1 && pathY == y + 1) {
                return "dlDiag";
            } else if (pathX == x && pathY == y + 1) {
                return "down";
            } else if (pathX == x + 1 && pathY == y + 1) {
                return "drDiag";
            } else return "idle";
        } else {
            return "idle";
        }
    }

    public static Vector nextTile(int x, int y, String cmd){
        return new Vector(path[x][y].getX(), path[x][y].getY());
    }

    public static void evaluate(int x, int y, int nx, int ny){
        double something = Math.hypot(nx-x, ny-y);

        if (distanceFromPlayer[nx][ny] > distanceFromPlayer[x][y]+something){
            distanceFromPlayer[nx][ny] = (float)(distanceFromPlayer[x][y]+something);
            path[nx][ny] = new Vector(x, y);
        }

    }

    public static void initialize(float x, float y){
        //float xmin = x-5; float ymin = y-5;
        //float xmax = x+5; float ymax = y+5;

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

    public static void makeGraphCopy(Map map, float x, float y){
        //use mapCopy
        x = x+20; y = y+11;
        float xmin = x-5; float ymin = y-5;
        float xmax = x+5; float ymax = y+5;

        for (int i = (int)xmin; i < (int)xmax; i++){
            for (int j = (int)ymin; j < (int)ymax; j++){
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
