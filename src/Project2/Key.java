package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Key extends Object {
    private static final SpriteSheet keySheet = ResourceManager.getSpriteSheet(Project2.KEYSHEETRSC,32,32);
    public Key (Vector worldPosition, String name, int value) throws SlickException {
        super(worldPosition, name, value);
        InitImage();
        setPosition(worldPosition.getX()*32, worldPosition.getY()*32);
    }

    protected void InitImage(){
            addImageWithBoundingBox(keySheet.getSprite(0,0));

    }
    public Vector getWorldPosition(){return super.getWorldPosition();}
}