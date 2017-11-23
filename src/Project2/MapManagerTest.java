package Project2;

import jig.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapManagerTest {
    MapManager mapMgr;

    @BeforeEach
    void setup(){
        mapMgr = new MapManager();
    }

    @Test
    void testMoveMapRight(){
        Vector worldPos = new Vector(0,0);
        mapMgr.clientDisplacement = new Vector(-1f,23f);
        Vector result = mapMgr.CalcCurrentWorldPosition(worldPos);
        Assertions.assertEquals(32, mapMgr.clientDisplacement.getX());
        Assertions.assertEquals(1, worldPos.getX());
    }
}