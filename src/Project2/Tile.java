package Project2;

public class Tile {
    int minX = 0;
    int maxX = 0;
    int minY = 0;
    int maxY = 0;
    int centerX = 0;
    int centerY = 0;
    boolean blocked;

    Tile(int minx, int maxx, int miny, int maxy, int centerx, int centery, boolean blocked){
        this.minX = minx;
        this.maxX = maxx;
        this.minY = miny;
        this.maxY = maxy;
        this.centerX = centerx;
        this.centerY = centery;
        if (blocked == true){
            //TODO Create collision box
        }
        this.blocked = blocked;
    }
}
