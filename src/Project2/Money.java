package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Money extends Object {
    int value;
    private static final SpriteSheet moneySheet = ResourceManager.getSpriteSheet(Project2.MONEYSHEETRSC,32,32);
    public Money (Vector worldPosition, String name, int value) throws SlickException {
        super(worldPosition, name, value);
        InitImage();
        this.value = value;
    }

    protected void InitImage(){
        if(this.value == 1)
            addImageWithBoundingBox(moneySheet.getSprite(0,0));
        else if (this.value == 2)
            addImageWithBoundingBox(moneySheet.getSprite(1,0));
        else if (this.value <= 5)
            addImageWithBoundingBox(moneySheet.getSprite(2,0));
        else if (this.value <= 10)
            addImageWithBoundingBox(moneySheet.getSprite(3,0));
        else
            addImageWithBoundingBox(moneySheet.getSprite(4,0));
    }
    public Vector getWorldPosition(){return super.getWorldPosition();}
}
