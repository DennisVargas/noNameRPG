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
        if (level == 1) {
            // Room 1
            key = new Key(new Vector(28.5f, 105.25f), "key", 1);
            Keys.add(key);

        }
        return this.Keys;
    }
}
