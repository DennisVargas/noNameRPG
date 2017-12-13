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
        } else if (level == 2){
            key = new Key(new Vector(102f, 18f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(47f, 30f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(96f, 30f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(42f, 48f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(72f, 66f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(13f, 78f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(90f, 90f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
            key = new Key(new Vector(96f, 102f), keyName, 1); keyNum++; keyName = "key" + keyNum;
            Keys.add(key);
        }
        return this.Keys;
    }
}
