package Project2;

/*
Mobs 1 and 2 are melee
Mobs 3 and 4 are ranged
 */

import jig.ConvexPolygon;
import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Mob extends BasicBeing {
    private int mobType;
    private static final SpriteSheet mob1Walking = ResourceManager.getSpriteSheet(Project2.MOB1WALKINGSHEETRSC,48,48);
    private static final SpriteSheet mob1Attacking = ResourceManager.getSpriteSheet(Project2.MOB1ATTACKINGSHEETRSC,32,32);
    private static final SpriteSheet mob2Walking = ResourceManager.getSpriteSheet(Project2.MOB2WALKINGSHEETRSC,32,32);
    private static final SpriteSheet mob2Attacking = ResourceManager.getSpriteSheet(Project2.MOB2ATTACKINGSHEETRSC,32,32);
    private static final SpriteSheet mob3Walking = ResourceManager.getSpriteSheet(Project2.MOB3WALKINGSHEETRSC,32,32);
    private static final SpriteSheet mob3Attacking = ResourceManager.getSpriteSheet(Project2.MOB3ATTACKINGSHEETRSC,32,32);
    private static final SpriteSheet mob4Walking = ResourceManager.getSpriteSheet(Project2.MOB4WALKINGSHEETRSC,32,32);
    private static final SpriteSheet mob4Attacking = ResourceManager.getSpriteSheet(Project2.MOB4ATTACKINGSHEETRSC,32,32);

    public Mob(Vector worldPosition, int mobType, String name) {
        super(worldPosition, worldPosition, mob1Walking, mob1Attacking);
        this.setName(name);
        setMobType(mobType);
        this.setCommand(InputManager.InputCommands.idle);
        this.setLastDirectionCommand(InputManager.InputCommands.left);
        this.setCurrentAnimation(idleAnimLt);
    }

    public int getMobType(){
        return this.mobType;
    }

    private void setMobType(int mobType) {
        switch(mobType){
            case 1:
                InitMob1Animations(mob1Walking, mob1Attacking);
                this.setRanged(false);
                break;
            case 2:
                InitMob2Animations(mob2Walking, mob2Attacking);
                this.setRanged(false);
                break;
            case 3:
                InitMob3Animations(mob3Walking, mob3Attacking);
                this.setRanged(true);
                break;
            case 4:
                InitAnimations(mob4Walking, mob4Attacking);
                this.setRanged(true);
                break;
        }
        this.mobType = mobType;
    }

    private void InitMob2Animations(SpriteSheet mob2Walking, SpriteSheet mob2Attacking) {

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
        this.attackAnim = new Animation(walkingSheet, 0,5,7,5,true,100,true);

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
    protected void InitMob3Animations(SpriteSheet walkingSheet, SpriteSheet attackingSheet) {
//        same as default crystal buddy sheet
        this.InitAnimations(walkingSheet,attackingSheet);
    }

}
