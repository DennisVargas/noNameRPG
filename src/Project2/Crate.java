package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import java.util.Random;

public class Crate extends Object {
    private static final SpriteSheet CrateSheet = ResourceManager.getSpriteSheet(Project2.CRATESHEETRSC,16,16);
    public Crate(Vector worldPosition, String name) throws SlickException{
        super(worldPosition, name, 0);
        Random random = new Random();
        int crateType = random.nextInt((3-1)+1);
        InitImage(crateType);
    }

    protected void InitImage(int crateType){
        if (crateType == 0)
            addImageWithBoundingBox(CrateSheet.getSprite(0,0));
        else if (crateType == 1)
            addImageWithBoundingBox(CrateSheet.getSprite(1,0));
        else
            addImageWithBoundingBox(CrateSheet.getSprite(2,0));
    }

}
