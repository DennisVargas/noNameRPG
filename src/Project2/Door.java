package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Door extends Object {
    public Door(Vector worldPosition, String name) throws SlickException{
        super(worldPosition, name, 0);
        if(name.contains("doorH"))
            InitImage(true);
        else
            InitImage(false);
    }

    protected void InitImage(boolean horizontal){
        if(horizontal == true)
            addImageWithBoundingBox(ResourceManager.getImage(Project2.DOORHSHEETRSC));
        else
            addImageWithBoundingBox(ResourceManager.getImage(Project2.DOORVSHEETRSC));
    }

}
