package Project2;

import jig.Vector;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class KeyList {
    private ArrayList<Key> Keys;

    public KeyList() {
        Keys = new ArrayList<>();
    }

    public ArrayList getKeyList (int level)  throws SlickException {
        Key key;
        int keyNum = 0;
        String keyName = "key" + keyNum;
        if (level == 1) {
            // Room 1
            key = new Key(new Vector(30.5f, 105.25f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(35.5f, 105.25f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);


        }
        return this.Keys;
    }
}
