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
     * constructs a hero who is centered in screen position .
     * @param worldPosition starting world map tile position as a Vector
     * @param isRanged boolean which dictates if a character is Ranged or Melee
     */
    public Hero(Vector worldPosition, boolean isRanged, String name) {
//        initialize the hero as if they are a client and a melee character
        super(new Vector((Project2.WIDTH * Project2.SCALE)/2f,(Project2.HEIGHT * Project2.SCALE)/2f),
                worldPosition, meleeWalkingSheet, meleeAttackingSheet);
        this.setName(name);
//        if the hero is ranged then set them to the ranged animation
        if (isRanged) {
            this.InitAnimations(rangedWalkingSheet,rangedAttackingSheet);
        }
//        if the hero isn't a client then don't place them in center screen
//        todo: when the world class is created change this to a comparison of the ip address and a name argument passed into the Hero constructor
        if(this.getName() != Project2.settings.getIpAddress())
            this.setPosition(MovementCalc.CalcScreenPosition(
                    new Vector((Project2.WIDTH * Project2.SCALE)/2f,(Project2.HEIGHT * Project2.SCALE)/2f),
                    this.getWorldPosition()));
    }
}