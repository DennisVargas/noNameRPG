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
            key = new Key(new Vector(62f, 105f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(33f, 93f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(26f, 63f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(50f, 69f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(63f, 69f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(92f, 81f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(92f, 33f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(74f, 33f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
        }
        return this.Keys;
    }
}
