package Project2;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
    Tile[][] tiles;

    Map(TiledMap map, int level){
        tiles  = new Tile[150][150];
        for (int j = 0; j < 150; j++) {
            for(int i = 0; i < 150; i++)
                tiles[i][j] = null;
        }
        for (int j = 0; j < 150; j++){
            for (int i = 0; i < 150; i++){
                try{
                    tiles[i][j] = new Tile(i,j, map.getTileProperty(map.getTileId(i, j, map.getLayerIndex("Main")), "type", "abyss"), level);
                    System.out.println(tiles[i][j].getType() +" "+ i +" "+ j);
                }
                catch (NullPointerException e){
                    tiles[i][j] = new Tile(i,j,"abyss", level);
                }

            }
        }
        System.out.println("Finished making tilemapping");
    }
}
