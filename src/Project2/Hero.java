package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

/**
 * client players will be of type <code>Hero</code> which extends <code>BasicBeing</code>;
 * the screen position for client Hero is in the center of the screen.
 *
 */
public class Hero extends BasicBeing{

//  get all possible hero sprite sheets
    private static final SpriteSheet rangedWalkingSheet = ResourceManager.getSpriteSheet(Project2.RANGEDHEROWALKINGSHEETRSC,32,32);
    private static final SpriteSheet rangedAttackingSheet = ResourceManager.getSpriteSheet(Project2.RANGEDHEROATTACKINGSHEETRSC,32,32);
    private static final SpriteSheet meleeWalkingSheet = ResourceManager.getSpriteSheet(Project2.MELEEHEROWALKINGSHEETRSC,32,32);
    private static final SpriteSheet meleeAttackingSheet = ResourceManager.getSpriteSheet(Project2.MELEEHEROATTACKINGSHEETRSC,32,32);

    /**
     * constructs a hero who is centered in screen position.
     * @param worldPosition starting world map tile position as a Vector
     * @param isRanged boolean which dictates if a character is Ranged or Melee
     */
    public Hero(Vector worldPosition, boolean isRanged) {
        super(new Vector((Project2.WIDTH * Project2.SCALE)/2f,(Project2.HEIGHT * Project2.SCALE)/2f),
                worldPosition, meleeWalkingSheet, meleeAttackingSheet);
        if (isRanged) {
            this.InitAnimations(rangedWalkingSheet,rangedAttackingSheet);
        }
    }


    /**
     * constructs a hero who has a given starting screen position.
     * @param screenPosition starting screen position of the the Hero
     * @param worldPosition map position of the Hero
     * @param isRanged
     */
    public Hero( Vector screenPosition, Vector worldPosition, boolean isRanged) {
        super(screenPosition, worldPosition, meleeWalkingSheet, meleeAttackingSheet);
        if (isRanged) {
            this.InitAnimations(rangedWalkingSheet,rangedAttackingSheet);
        }
    }
}