package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Hero extends BasicBeing{

//    get all possible hero sprite sheets
    private static final SpriteSheet rangedWalkingSheet = ResourceManager.getSpriteSheet(Project2.RANGEDHEROWALKINGSHEETRSC,32,32);
    private static final SpriteSheet rangedAttackingSheet = ResourceManager.getSpriteSheet(Project2.RANGEDHEROATTACKINGSHEETRSC,32,32);
    private static final SpriteSheet meleeWalkingSheet = ResourceManager.getSpriteSheet(Project2.MELEEHEROWALKINGSHEETRSC,32,32);
    private static final SpriteSheet meleeAttackingSheet = ResourceManager.getSpriteSheet(Project2.MELEEHEROATTACKINGSHEETRSC,32,32);



    public Hero(Vector worldPosition, boolean isRanged) {
        super(new Vector((Project2.HEIGHT * Project2.SCALE) / 2f, (Project2.WIDTH * Project2.SCALE) / 2f),
                worldPosition, meleeWalkingSheet, meleeAttackingSheet);
        if (isRanged) {
            this.InitAnimations(rangedWalkingSheet,rangedAttackingSheet);
        }
    }
}
