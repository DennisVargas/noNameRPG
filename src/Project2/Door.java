package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Door extends BasicBeing {
    public Door(Vector worldPosition, String name) throws SlickException{
        super(new Vector(-5000, -5000), worldPosition);
        this.setName(name);
        if(name.equals("doorH"))
            super.InitImage(true);
        else
            super.InitImage(false);
        this.setPosition(MovementCalc.CalcScreenPosition(
                Project2.getSettings().getPlayer().getWorldPosition(),
                this.getWorldPosition()));
    }

}
