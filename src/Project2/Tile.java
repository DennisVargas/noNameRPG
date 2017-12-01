package Project2;

public class Tile {
    private int coordX;
    private int coordY;
    private String type;
    private int room;

    boolean a,b,c;
    boolean d,  e;
    boolean f,g,h;

    Tile(int x, int y, String type, int level) {
        this.coordX = x;
        this.coordY = y;
        this.type = type;
        setupRoomNumber(x,y,level);
    }
    int getCoordX(){
        return this.coordX;
    }
    int getCoordY(){
        return this.coordY;
    }
    int getRoom(){
        return this.room;
    }
    String getType(){
        return this.type;
    }

    void setupRoomNumber(int x, int y, int level) {
        if (level == 1) {
            this.type = type;
            if (x <= 47 & x >= 33 & y <= 117 & y >= 91)
                this.room = 1;
            if (x <= 71 & x >= 39 & y <= 57 & y >= 31)
                this.room = 2;
            if (x <= 59 & x >= 51 & y <= 99 & y >= 73)
                this.room = 3;
            if (x <= 95 & x >= 75 & y <= 69 & y >= 49)
                this.room = 4;
            if (x <= 89 & x >= 81 & y <= 99 & y >= 85)
                this.room = 5;
            if (x <= 113 & x >= 105 & y <= 75 & y >= 49)
                this.room = 6;
            if (x <= 71 & x >= 63 & y <= 111 & y >= 91)
                this.room = 7;
            if (x <= 113 & x >= 99 & y <= 87 & y >= 79)
                this.room = 8;
        }
    }
}