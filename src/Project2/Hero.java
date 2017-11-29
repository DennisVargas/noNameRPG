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
    private static final SpriteSheet meleeWalkingSheet = ResourceManager.getSpriteSheet(Project2.MELEEHEROWALKINGSHEETRSC,32,32);
    private static final SpriteSheet meleeAttackingSheet = ResourceManager.getSpriteSheet(Project2.MELEEHEROATTACKINGSHEETRSC,32,32);
    Rectangle healthBar;

    /**
     * constructs a hero who is centered in screen position .
     * @param worldPosition starting world map tile position as a Vector
     * @param isRanged boolean which dictates if a character is Ranged or Melee
     */
    public Hero(Vector worldPosition, boolean isRanged, String name) {
//        initialize the hero as if they are a client and a melee character
        super(worldPosition, worldPosition, meleeWalkingSheet, meleeAttackingSheet);
        this.setName(name);
        this.setAttackPower(100f);
        this.setRanged(isRanged);
        this.setCommand(InputManager.InputCommands.idle);
        this.setLastDirectionCommand(InputManager.InputCommands.left);
//        if the hero is ranged then set them to the ranged animation
//        if (this.isRanged()) {
//            InitHeroRangedAnimations(rangedWalkingSheet,rangedAttackingSheet);
//        }
//        if the hero isn't a client then don't place them in center screen
        System.out.println(Project2.settings.getIpAddress());
        if(Project2.settings.getIpAddress().equals(name)) {
            this.setPosition(new Vector(Project2.WIDTH/2f,Project2.HEIGHT/2f));

            Project2.getSettings().setPlayer(this);
        }
        InitHealthBarRect();
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
        healthBar.setWidth(heroWidth*this.getHealth());
    }

    public Shape getHealthBar() {
        return healthBar;
    }
}