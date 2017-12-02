package Project2;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
    Tile[][] tiles;

    Map(TiledMap map, int level){
        tiles  = new Tile[150][150];
        for (int j = 0; j < 150; j++){
            for (int i = 0; i < 150; i++){
                try{
                    tiles[i][j] = new Tile(i,j, map.getTileProperty(map.getTileId(i, j, map.getLayerIndex("Main")), "type", "abyss"), level);
                }
                catch (NullPointerException e){
                    tiles[i][j] = new Tile(i,j,"abyss", level);
                }

            }
        }
    }

    public void setNeighbors(){
        for (int i = 0; i < 150; i++){
            for (int j = 0; j < 150; j++){
                if (!tiles[i][j].getType().equalsIgnoreCase("abyss")){
                    if (!tiles[i-1][j-1].getType().equalsIgnoreCase("abyss"))
                        tiles[i][j].a = true;
                    if (!tiles[i][j-1].getType().equalsIgnoreCase("abyss"))
                        tiles[i][j].b = true;
                    if (!tiles[i+1][j-1].getType().equalsIgnoreCase("abyss"))
                        tiles[i][j].c = true;
                    if (!tiles[i-1][j].getType().equalsIgnoreCase("abyss"))
                        tiles[i][j].d = true;
                    if (!tiles[i+1][j].getType().equalsIgnoreCase("abyss"))
                        tiles[i][j].e = true;
                    if (!tiles[i-1][j+1].getType().equalsIgnoreCase("abyss"))
                        tiles[i][j].f = true;
                    if (!tiles[i][j+1].getType().equalsIgnoreCase("abyss"))
                        tiles[i][j].g = true;
                    if (!tiles[i+1][j+1].getType().equalsIgnoreCase("abyss"))
                        tiles[i][j].h = true;
                }
            }
        }
    }

    public void setStaticTileCost(){
        for (int i = 0; i < 150; i++){
            for (int j = 0; j < 150; j++){
                if(tiles[i][j].getType().equalsIgnoreCase("abyss")){
                    tiles[i][j].setTileCost(100);
                } else {
                    tiles[i][j].setTileCost(1);
                }
            }
        }
    }

    public int getTileCost(int i, int j){
        return tiles[i][j].getTileCost();
    }
}
