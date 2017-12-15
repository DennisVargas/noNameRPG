package Project2;

import jig.ConvexPolygon;
import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * client players will be of type <code>Hero</code> which extends <code>BasicBeing</code>;
 * the screen position for client Hero is in the center of the screen.
 *
 */
public class Hero extends BasicBeing{

//  get all possible hero sprite sheets
    private static final SpriteSheet rangedWalkingSheet = ResourceManager.getSpriteSheet(Project2.RANGEDHEROWALKINGSHEETRSC,32,32);
    private static final SpriteSheet rangedAttackingSheet = ResourceManager.getSpriteSheet(Project2.RANGEDHEROATTACKINGSHEETRSC,32,32);

    private static final SpriteSheet meleeWalkingSheet = ResourceManager.getSpriteSheet(Project2.MELEEHEROWALKINGSHEETRSC,21,32);
    private static final SpriteSheet meleeAttackingSheet = ResourceManager.getSpriteSheet(Project2.MELEEHEROATTACKINGSHEETRSC,21,32);
    Rectangle healthBar;

    /**
     * constructs a hero who is centered in screen position .
     * @param worldPosition starting world map tile position as a Vector
     * @param isRanged boolean which dictates if a character is Ranged or Melee
     */
    public Hero(Vector worldPosition, boolean isRanged, String name) {
//        initialize the hero as if they are a client and a melee character
        super(worldPosition, worldPosition, true);//, meleeWalkingSheet, meleeAttackingSheet);
        this.setName(name);
        this.setAttackPower(100f);
        this.setRanged(isRanged);
        this.setCommand(InputManager.InputCommands.idle);
        this.setLastDirectionCommand(InputManager.InputCommands.left);
//        if the hero is ranged then set them to the ranged animation
        if (isRanged()) {
            InitHeroRangedAnimations(rangedWalkingSheet,rangedAttackingSheet);
        } else {
            InitHeroMeleeAnimations(meleeWalkingSheet, meleeAttackingSheet);
        }
//        if the hero isn't a client then don't place them in center screen
//        System.out.println(Project2.settings.getIpAddress());
        if(Project2.settings.getIpAddress().equals(name)) {
            this.setPosition(new Vector(Project2.WIDTH/2f,Project2.HEIGHT/2f));

            Project2.getSettings().setPlayer(this);
        }
        //InitHeroRangedAnimations(rangedWalkingSheet,rangedAttackingSheet);
        InitHealthBarRect();
        this.setCurrentAnimation(idleAnimLt);

    }

    public void InitHealthBarRect() {
        float heroWidth,heroHeight;

        heroWidth = this.getLocallyOffsetShapes().getFirst().getHeight()*this.getScale();
        heroHeight = this.getLocallyOffsetShapes().getFirst().getWidth()*this.getScale();
        healthBar = new Rectangle(this.getX()-heroWidth/2,
                this.getY()+heroHeight/2,
                heroWidth,
                heroHeight*0.2f);
    }

    public void UpdateHealthBarLocation(){
        float heroWidth,heroHeight;
        heroWidth = this.getLocallyOffsetShapes().getFirst().getHeight()*this.getScale();
        heroHeight = this.getLocallyOffsetShapes().getFirst().getWidth()*this.getScale();
        healthBar.setLocation(this.getX()-heroWidth/2,
                this.getY() + heroHeight/2);
        float curHealth = this.getHealth();
        if(curHealth<0)
            curHealth = 0;
        healthBar.setWidth(heroWidth*curHealth);


    }

    public Shape getHealthBar() {
        return healthBar;
    }

    private void InitHeroRangedAnimations(SpriteSheet walkingSheet, SpriteSheet attackingSheet) {
        this.walkRightAnim = new Animation(walkingSheet, 0,0,9,0,true,100,true);
        this.walkLeftAnim = new Animation(walkingSheet, 0,1,9,1,true,100,true);
        this.walkDnAnim = new Animation(walkingSheet, 0,2,9,2,true,100,true);
        this.walkUpAnim = new Animation(walkingSheet, 0,3,9,3,true,100,true);
        this.hitAnimRt = new Animation(walkingSheet, 0,4,1,4,true,100,true);
        this.hitAnimLt = new Animation(walkingSheet, 0,5,1,5,true,100,true);
        this.idleAnimRt = new Animation(walkingSheet, 0,6,0,6,true,100,true);
        idleAnimRt.setLooping(false);
        this.idleAnimLt = new Animation(walkingSheet, 0,7,0,7,true,100,true);
        idleAnimLt.setLooping(true);
        this.deathAnim = new Animation(walkingSheet, 0,8,0,8,true,100,true);
        deathAnim.setLooping(true);
        //  Attack and hit anim are the same except he shoots things when attacking.
        this.attackAnim = new Animation(walkingSheet, 0,5,1,5,true,100,true);

//        set bounding box for being based on animation
        ConvexPolygon beingBoundBox = new ConvexPolygon((float)this.walkLeftAnim.getWidth(),(float)this.walkLeftAnim.getHeight());
        this.addShape(beingBoundBox);
    }

    private void InitHeroMeleeAnimations(SpriteSheet walking, SpriteSheet attacking){
        this.walkRightAnim = new Animation(walking, 0,0,3,0,true,100,true);
        this.walkLeftAnim = new Animation(walking, 0,1,3,1,true,100,true);
        this.walkDnAnim = new Animation(walking, 0,2,3,2,true,100,true);
        this.walkUpAnim = new Animation(walking, 0,3,3,3,true,100,true);
        this.hitAnimRt = new Animation(walking, 0,4,1,4,true,100,true);
        this.hitAnimLt = new Animation(walking, 0,5,1,5,true,100,true);
        this.idleAnimRt = new Animation(walking, 0,6,3,6,true,150,true);
        idleAnimRt.setLooping(true);
        this.idleAnimLt = new Animation(walking, 0,7,3,7,true,150,true);
        idleAnimLt.setLooping(true);
        this.deathAnim = new Animation(walking, 0,8,0,8,true,100,true);
        deathAnim.setLooping(true);
        //  Attack and hit anim are the same except he shoots things when attacking.
        //this.attackAnim = new Animation(walking, 0,5,1,5,true,100,true);
        this.attackAnimLt = new Animation(walking, 0,9,3,9, true, 100, true);
        this.attackAnimRt = new Animation(walking, 0,10,3,10, true, 100, true);
//        set bounding box for being based on animation
        ConvexPolygon beingBoundBox = new ConvexPolygon((float)this.walkLeftAnim.getWidth(),(float)this.walkLeftAnim.getHeight());
        this.addShape(beingBoundBox);
    }
}