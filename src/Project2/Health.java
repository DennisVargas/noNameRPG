package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Health extends Object {
    int value;
    private static final SpriteSheet healthSheet = ResourceManager.getSpriteSheet(Project2.POTION,32,32);
    public Health (Vector worldPosition, String name, int value) throws SlickException {
        super(worldPosition, name, value);
        this.value = value;
        InitImage();
        setPosition(worldPosition.getX()*32, worldPosition.getY()*32);
    }

    protected void InitImage(){
        addImageWithBoundingBox(healthSheet.getSprite(0,0));

    }
    public Vector getWorldPosition(){return super.getWorldPosition();}
}
