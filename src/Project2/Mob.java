package Project2;

/*
Mobs 1 and 2 are melee
Mobs 3 and 4 are ranged
 */

import jig.ConvexPolygon;
import jig.ResourceManager;
import jig.Shape;
import jig.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Mob extends BasicBeing {
    private int mobType;
    //pencil mob melee
    private static final SpriteSheet mob1Walking = ResourceManager.getSpriteSheet(Project2.MOB1WALKINGSHEETRSC,48,48);
    private static final SpriteSheet mob1Attacking = ResourceManager.getSpriteSheet(Project2.MOB1ATTACKINGSHEETRSC,48,48);
    //agav mob melee
    private static final SpriteSheet mob2Walking = ResourceManager.getSpriteSheet(Project2.MOB2WALKINGSHEETRSC,32,56);
    private static final SpriteSheet mob2Attacking = ResourceManager.getSpriteSheet(Project2.MOB2ATTACKINGSHEETRSC,32,56);
    //crystal mob range
    private static final SpriteSheet mob3Walking = ResourceManager.getSpriteSheet(Project2.MOB3WALKINGSHEETRSC,32,32);
    private static final SpriteSheet mob3Attacking = ResourceManager.getSpriteSheet(Project2.MOB3ATTACKINGSHEETRSC,32,32);
    //wizhoo mob range
    private static final SpriteSheet mob4Walking = ResourceManager.getSpriteSheet(Project2.MOB4WALKINGSHEETRSC,32,32);
    private static final SpriteSheet mob4Attacking = ResourceManager.getSpriteSheet(Project2.MOB4ATTACKINGSHEETRSC,32,32);

    public Mob(Vector worldPosition, int mobType, String name) {
        super(worldPosition, worldPosition, true);
        //super(worldPosition, worldPosition, mob1Walking, mob1Attacking);
        this.setName(name);
        setMobType(mobType);
        this.setCommand(InputManager.InputCommands.idle);
        this.setLastDirectionCommand(InputManager.InputCommands.left);
        this.setCurrentAnimation(idleAnimLt);
        //setDebug(true);
    }

    public int getMobType(){
        return this.mobType;
    }

    private void setMobType(int mobType) {
//        Shape oldHitBox = this.getShapes().get(0);
//        this.removeShape(oldHitBox);
        switch(mobType){
            case 1://pencil
//                mob1 has different dimensions
                InitMob1Animations(mob1Walking, mob1Attacking);
//                this.InitAnimations(mob1Walking,mob1Attacking);
                this.setRanged(false);
                this.setAttackPower(1f);
                break;
            case 2: //Agav
                InitMob2Animations(mob2Walking, mob2Attacking);
                this.setRanged(false);
                this.setAttackPower(1f);
                break;
            case 3: //crystal Dude
                this.InitMob3Animations(mob3Walking, mob3Attacking);
//                this.InitAnimations(mob3Walking,mob3Attacking);
                this.setRanged(true);
                this.setAttackPower(1f);

                break;
            case 4: //wizhoo
                InitMob4Animations(mob4Walking, mob4Attacking);
                this.setRanged(true);
                this.setAttackPower(1f);
                break;
        }
        this.mobType = mobType;
    }

    private void InitMob2Animations(SpriteSheet mob2Walking, SpriteSheet mob2Attacking) {
        this.walkRightAnim = new Animation(mob2Walking, 0,0,8,0,true,100,true);
        this.walkLeftAnim = new Animation(mob2Walking, 0,1,8,1,true,100,true);
        this.walkDnAnim = new Animation(mob2Walking, 0,2,8,2,true,100,true);
        this.walkUpAnim = new Animation(mob2Walking, 0,3,8,3,true,100,true);
        this.hitAnimRt = new Animation(mob2Walking, 0,4,1,4,true,100,true);
        this.hitAnimLt = new Animation(mob2Walking, 0,5,1,5,true,100,true);
        this.idleAnimRt = new Animation(mob2Walking, 0,6,3,6,true,100,true);
        this.idleAnimLt = new Animation(mob2Walking, 0,7,3,7,true,100,true);

        this.deathAnim = new Animation(mob2Walking, 0,8,10,8,true,100,true);
        deathAnim.setLooping(false);
        //  Attack and hit anim are the same except he shoots things when attacking.
        // Attack needs to be different for melee mobs
        this.attackAnimRt =  new Animation(mob2Walking, 0, 9, 3, 9, true, 100, true);
        this.attackAnimLt = new Animation(mob2Walking, 0,10,3,10, true, 100, true);

//        set bounding box for being based on animation
        ConvexPolygon beingBoundBox = new ConvexPolygon((float)this.walkLeftAnim.getWidth(),(float)this.walkLeftAnim.getHeight());
        this.addShape(beingBoundBox);

    }

    private void InitMob1Animations(SpriteSheet walkingSheet, SpriteSheet attackingSheet) {
        this.walkRightAnim = new Animation(walkingSheet, 0,0,3,0,true,100,true);
        this.walkLeftAnim = new Animation(walkingSheet, 0,1,3,1,true,100,true);
        this.walkDnAnim = new Animation(walkingSheet, 0,2,3,2,true,100,true);
        this.walkUpAnim = new Animation(walkingSheet, 0,3,3,3,true,100,true);
        this.hitAnimRt = new Animation(walkingSheet, 0,4,1,4,true,100,true);
        this.hitAnimLt = new Animation(walkingSheet, 0,5,1,5,true,100,true);
        this.idleAnimRt = new Animation(walkingSheet, 0,6,8,6,true,100,true);
        this.idleAnimLt = new Animation(walkingSheet, 0,7,8,7,true,100,true);

        this.deathAnim = new Animation(walkingSheet, 0,8,12,8,true,100,true);
        deathAnim.setLooping(false);
        //  Attack and hit anim are the same except he shoots things when attacking.
        // Attack needs to be different for melee mobs
        //this.attackAnim = new Animation(walkingSheet, 0,5,7,5,true,100,true);
        this.attackAnimRt =  new Animation(walkingSheet, 0, 9, 7, 9, true, 100, true);
        this.attackAnimLt = new Animation(walkingSheet, 0,10,7,10, true, 100, true);
//        set bounding box for being based on animation
        ConvexPolygon beingBoundBox = new ConvexPolygon((float)this.walkLeftAnim.getWidth(),(float)this.walkLeftAnim.getHeight());
        this.addShape(beingBoundBox);
    }
    /**
     * Initializes the Being Animations for walking, idle, attacking, and death.
     * Sprite sheets are passed into the Basic Being Constructor.
     * @param walkingSheet
     * @param attackingSheet
     */
    private void InitMob3Animations(SpriteSheet walkingSheet, SpriteSheet attackingSheet) {
        this.walkRightAnim = new Animation(walkingSheet, 0,0,5,0,true,100,true);
        this.walkLeftAnim = new Animation(walkingSheet, 0,1,5,1,true,100,true);
        this.walkDnAnim = new Animation(walkingSheet, 0,2,5,2,true,100,true);
        this.walkUpAnim = new Animation(walkingSheet, 0,3,5,3,true,100,true);
        this.hitAnimRt = new Animation(walkingSheet, 0,4,1,4,true,100,true);
        this.hitAnimLt = new Animation(walkingSheet, 0,5,1,5,true,100,true);
        this.idleAnimRt = new Animation(walkingSheet, 0,6,5,6,true,100,true);
        this.idleAnimLt = new Animation(walkingSheet, 0,7,5,7,true,100,true);

        this.deathAnim = new Animation(walkingSheet, 0,8,7,8,true,100,true);
        deathAnim.setLooping(false);
        //  Attack and hit anim are the same except he shoots things when attacking.
        this.attackAnim = new Animation(walkingSheet, 0,5,1,5,true,100,true);

//        set bounding box for being based on animation
        ConvexPolygon beingBoundBox = new ConvexPolygon((float)this.walkLeftAnim.getWidth(),(float)this.walkLeftAnim.getHeight());
        this.addShape(beingBoundBox);
    }

    private void InitMob4Animations(SpriteSheet walking, SpriteSheet attacking){
        this.walkRightAnim = new Animation(walking, 0,0,3,0,true,100,true);
        this.walkLeftAnim = new Animation(walking, 0,1,3,1,true,100,true);
        this.walkDnAnim = new Animation(walking, 0,2,3,2,true,100,true);
        this.walkUpAnim = new Animation(walking, 0,3,3,3,true,100,true);
        this.hitAnimRt = new Animation(walking, 0,4,1,4,true,100,true);
        this.hitAnimLt = new Animation(walking, 0,5,1,5,true,100,true);
        this.idleAnimRt = new Animation(walking, 0,6,9,6,true,100,true);
        this.idleAnimLt = new Animation(walking, 0,7,9,7,true,100,true);

        this.deathAnim = new Animation(walking, 0,8,5,8,true,100,true);
        deathAnim.setLooping(false);
        //  Attack and hit anim are the same except he shoots things when attacking.
        this.attackAnim = new Animation(walking, 0,5,1,5,true,100,true);

//        set bounding box for being based on animation
        ConvexPolygon beingBoundBox = new ConvexPolygon((float)this.walkLeftAnim.getWidth(),(float)this.walkLeftAnim.getHeight());
        this.addShape(beingBoundBox);
    }
}
