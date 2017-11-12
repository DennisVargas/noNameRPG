package Project2;

import jig.Entity;
import jig.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

/**
 * The BasicBeing class will define all HeroTypes and MobTypes
 * this class defines all attributes heroes and enemies.
 * the biggest distinction between the two is controller type
 * HeroTypes will never be controlled by AI according to spec.
 */
public class BasicBeing extends Entity{
    private float health = 1f;
    Animation walkRightAnim, walkLeftAnim;
    String name = "default";
    private String nextMoveCommand;

    public BasicBeing(Vector position, SpriteSheet walkingSheet, SpriteSheet attackingSheet) {
        super(position);
        InitAnimations(walkingSheet, attackingSheet);

    }
    public BasicBeing(){

    }

    private void InitAnimations(SpriteSheet walkingSheet, SpriteSheet attackingSheet) {
        this.walkRightAnim = new Animation(walkingSheet, 0,0,5,0,true,100,true);
        this.walkLeftAnim = new Animation(attackingSheet, 1,2,6,1,true,100,true);
    }

    public void RenderBeing(){
        if(nextMoveCommand == "walk-right"){
            walkRightAnim.draw( getPosition().getX(),
                                getPosition().getY());}
        else if(nextMoveCommand == "walk-left"){
            walkLeftAnim.draw( getPosition().getX(),
                               getPosition().getY());
        }
    }




}
