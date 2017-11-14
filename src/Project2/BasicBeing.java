package Project2;

import jig.Entity;
import jig.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import java.lang.Math;

import static Project2.InputManager.InputCommands;
import static Project2.InputManager.InputCommands.*;

/**
 * The BasicBeing class will define all HeroTypes and MobTypes
 * this class defines all attributes heroes and enemies.
 * the biggest distinction between the two is controller type
 * HeroTypes will never be controlled by AI according to spec.
 */
public class BasicBeing extends Entity{

    private float health = 1f;

    private Animation walkRightAnim, walkLeftAnim,
                      walkUpAnim, walkDnAnim,
                      idleAnim, attackAnim, hitAnim,
                      deathAnim, currentAnim;

    private String name = "default";
    private int beingID = 0;

    private InputCommands nextMoveCommand;
    private Vector nextPosition;
    private Vector nextMoveDirection;
    private Vector nextMoveTranslation;
    private Vector currentDisplacement;

    private float speed =2f;
    private Animation currentAnimation;
    boolean isHit = false;


    /**
     * BasicBeing: Constructs a basic being. Initializes <code>nextMoveCommand</code> to idle.
     * Then the <code>InitAnimations(SpriteSheet, SpriteSheet)</code> is called.
     * The being gets initialized to the idle animation to begin with.
     * Finally All the vectors for the next Being and map movement are initialized.
     * @param position: A JIG Vector that set the Being starting location
     * @param walkingSheet: A sprite sheet of non attacking animation frames
     * @param attackingSheet: A sprite sheet of attack animation frames
     */
    public BasicBeing(Vector position, SpriteSheet walkingSheet, SpriteSheet attackingSheet) {
        super(position);
        nextMoveCommand = idle;
        InitAnimations(walkingSheet, attackingSheet);
        setCurrentAnimation(idleAnim);
        InitNextVectors();
    }


    /**
     * @return if health is less than or equal to zero.
     */
    public boolean isDead(){
        return health <= 0;
    }


    /**
     * subroutine which initializes the direction, translation, and
     * position vectors in the basic being class.
     */
    private void InitNextVectors() {
//        init next position to current position
        setNextPosition();
//        init the direction based on nextMoveCommand == idle
        setNextMoveDirection(nextMoveCommand);
//      init the move translation using the other zero vectors
        setNextMoveTranslation();
    }

    private void setNextPosition() {
        if(this.nextPosition == null)
            this.nextPosition = this.getPosition().copy();
        else
            this.nextPosition = new Vector( this.nextMoveTranslation.getX() + this.getPosition().getX(),
                                            this.nextMoveTranslation.getY() + this.getPosition().getY());
    }

    private void setNextMoveTranslation() {
        nextMoveTranslation = new Vector(nextMoveDirection.getX()*speed,
                nextMoveDirection.getY()*speed);
    }

    private void setNextMoveDirection(InputCommands nextMoveCommand) {
        float diagSpeed = (float)Math.sqrt(2)/2;
        switch (nextMoveCommand){
            case up:
                this.nextMoveDirection = new Vector(0,-1f);
                break;
            case down:
                this.nextMoveDirection = new Vector(0,1f);
                break;
            case left:
                this.nextMoveDirection = new Vector(-1f,0);
                break;
            case right:
                this.nextMoveDirection = new Vector(1f,0);
                break;
            case ulDiag:
                this.nextMoveDirection = new Vector(-1f,-1f).scale(0.75f);
                break;
            case dlDiag:
                this.nextMoveDirection = new Vector(-1f,1f).scale(0.75f);
                break;
            case urDiag:
                this.nextMoveDirection = new Vector(1f,-1f).scale(0.75f);
                break;
            case drDiag:
                this.nextMoveDirection = new Vector(1f,1f).scale(0.75f);
                break;
            case idle:
                this.nextMoveDirection = new Vector(0,0);
                break;
            case attack:
                this.nextMoveDirection = new Vector(0,0);
                break;
        }
    }

    public float getCurrentDisplacementX(){
        return this.currentDisplacement.getX();
    }

    public float getCurrentDisplacementY(){
        return this.currentDisplacement.getY();
    }
    private void CalculateCurrentDisplacement() {
        if(this.currentDisplacement == null)
            this.currentDisplacement = new Vector(0,0);
        else{
            int dx = (int)this.currentDisplacement.getX();
            int dy = (int)this.currentDisplacement.getY();
            dx += (int)this.nextMoveTranslation.getX();
            dy += (int)this.nextMoveTranslation.getY();
//            if (dx < 0) {
//                dx = 32;
//            }
//            if (dx > 32) {
//                dx = 0;
//            }
//            if (dy < 0) {
//                dy = 32;
//            }
//            if (dy > 32) {
//                dy = 0;
//            }
//            dx %= 34;
//            dy %= 34;
            this.currentDisplacement = new Vector(dx, dy);
        }
    }
    private void InitAnimations(SpriteSheet walkingSheet, SpriteSheet attackingSheet) {
        this.walkRightAnim = new Animation(walkingSheet, 0,0,5,0,true,100,true);
        this.walkLeftAnim = new Animation(walkingSheet, 0,1,5,1,true,100,true);
        this.walkDnAnim = new Animation(walkingSheet, 0,2,5,2,true,100,true);
        this.walkUpAnim = new Animation(walkingSheet, 0,3,5,3,true,100,true);
        this.idleAnim = new Animation(walkingSheet, 0,5,5,5,true,100,true);
        this.hitAnim = new Animation(walkingSheet, 0,4,1,4,true,100,true);
        this.deathAnim = new Animation(walkingSheet, 0,6,1,6,true,100,true);
        //  Attack and hit anim are the same except he shoots things when attacking.
        this.attackAnim = new Animation(walkingSheet, 0,4,1,4,true,100,true);
    }

    public void RenderBeing(Graphics g){
        this.render(g);
    }

    private void setNextMoveCommand(InputCommands nextMoveCommand) {
        this.nextMoveCommand = nextMoveCommand;
    }

    public void GenerateNextMove(InputCommands nextMoveCommand){
        //  read into this object the next move
        setNextMoveCommand(nextMoveCommand);

        //  process what the next move means to animation
        ProcessNextMoveAnimation();

        //  todo: create a collison check here for the beings next move.
        //  each being will acknowledge their own collision and so each will have to be tested against the rest.
        //  check for collisions get adjusted move
        //  Collisions.checkBeingForCollides()
        //  todo:================

        //  update the move after the collisonCheck
        UpdateNextMove();
    }

    private void UpdateNextMove() {
//        looking at the move command set the direction of movement
        setNextMoveDirection(getNextMoveCommand());
//        multiply direction by movement speed
//        setNextMoveTranslation();

        setNextPosition();
    }

    private void ProcessNextMoveAnimation() {
        switch(nextMoveCommand){
            case up:
                setCurrentAnimation(walkUpAnim);
                break;
            case down:
                setCurrentAnimation(walkDnAnim);
                break;
            case left:
            case dlDiag:
            case ulDiag:
                setCurrentAnimation(walkLeftAnim);
                break;
            case right:
            case drDiag:
            case urDiag:
                setCurrentAnimation(walkRightAnim);
                break;
            case idle:
                setCurrentAnimation(idleAnim);
                break;
            case attack:
                setCurrentAnimation(attackAnim);
                break;
            case hit:
                setCurrentAnimation(hitAnim);
                break;
            case death:
                setCurrentAnimation(deathAnim);
                break;

        }
    }

    public void setCurrentAnimation(Animation anim) {
        this.removeAnimation(currentAnim);
        this.currentAnim = anim;
        this.addAnimation(currentAnim);
    }

    public void UpdateBeing() {
        //  update being position based on next move
        //  and health if they were hit in the last collision check
        this.setPosition(this.nextPosition);
    }

    public InputCommands getNextMoveCommand() {
        return nextMoveCommand;
    }

    public int getBeingID() {
        return beingID;
    }

    public void setBeingID(int beingID) {
        this.beingID = beingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }
}
