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

    private Animation   walkRightAnim, walkLeftAnim, walkUpAnim,
                        walkDnAnim, idleAnimLt, idleAnimRt, attackAnim,
                        hitAnimLt, hitAnimRt, deathAnim, currentAnim;

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
     * Constructs a basic being. Initializes <code>nextMoveCommand</code> to idle.
     * Then the <code>InitAnimations(SpriteSheet, SpriteSheet)</code> is called.
     * The being gets initialized to the idle animation to begin with.
     * Finally All the vectors for the next Being and map movement are initialized.
     * @param position: A JIG Vector that set the Being starting location
     * @param walkingSheet: A sprite sheet of non attacking animation frames
     * @param attackingSheet: A sprite sheet of attack animation frames
     */
    public BasicBeing(Vector position, SpriteSheet walkingSheet, SpriteSheet attackingSheet) {
        super(position);
        setNextMoveCommand(idle);
        InitAnimations(walkingSheet, attackingSheet);
        setCurrentAnimation(idleAnim);
        InitNextVectors();
    }

    /**
     * Calculates the BasicBeing's displacement given
     * the current next move translation vector's x and y
     * components. Thus the field <code>this.currentDisplacement</code>
     * holds a running total of the beings displacement from their
     * original position at game start.
     */
    private void CalcCurrentDisplacement() {
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


    /**
     * Sets the next animation dependent on
     * the value of <code>nextMoveCommand</code> field.
     * Evaluates the possible animation options based on
     * the <code>InputManager.InputCommands</code> enumeration.
     */
    private void CalcNextMoveAnimation() {
        switch(this.nextMoveCommand){
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
            case hitLt:
                setCurrentAnimation(hitAnimLt);
                break;
            case hitRt:
                break;
            case death:
                setCurrentAnimation(deathAnim);
                break;
        }
    }


    /**
     * Calculates the next move direction dependent on
     * the current value of the <code>nextMoveCommand</code> field.
     * Movement commands are defined in the InputManager class. The
     * command is placed in a switch and evaluated accordingly. A
     * resulting UNIT Vector is set as the next move direction vector.
     * Thus an up command produces vector (0,1).
     */
    private void CalcNextMoveDirection() {
        float diagSpeed = (float)Math.sqrt(2)/2;
        switch (this.nextMoveCommand){
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
                this.nextMoveDirection = new Vector(-1f,-1f);
                break;
            case dlDiag:
                this.nextMoveDirection = new Vector(-1f,1f);
                break;
            case urDiag:
                this.nextMoveDirection = new Vector(1f,-1f);
                break;
            case drDiag:
                this.nextMoveDirection = new Vector(1f,1f);
                break;
            case idle:
                this.nextMoveDirection = new Vector(0,0);
                break;
            case attack:
                this.nextMoveDirection = new Vector(0,0);
                break;
        }
    }


    /**
     * Calculates the translation vector for the next move.
     * A move translation is calculated by multiplying the
     * Being speed by the next move direction vectors x and y
     * components. The result is used to instantiate a new
     * JIG Vector that is stored as nextMoveTranslation
     */
    private void CalcNextMoveTranslation() {
        nextMoveTranslation = new Vector(nextMoveDirection.getX()*speed,
                nextMoveDirection.getY()*speed);
    }

    /**
     * Calculates the Being's next position.
     * This will be the sum of the players current position
     * and the next move translation. These are vectors so each
     * component must be added separately and used to create a
     * new JIG Vector. Vector is an immutable type and must be
     * destroyed and replaced in order to change the values.
     */
    private void CalcNextPosition() {
        if(this.nextPosition == null)
            this.nextPosition = this.getPosition().copy();
        else
            this.nextPosition = new Vector( this.nextMoveTranslation.getX() + this.getPosition().getX(),
                    this.nextMoveTranslation.getY() + this.getPosition().getY());
    }


    /**
     * Calculate Next Direction, Translation, and Direction Vectors.
     * The next move command field is set from the argument passed so that the
     * direction calculation has a
     * next move animation is Calculated.
     * Once the animation is set for the next move the next move direction
     * is calculated. With move direction next move translation is calculated
     * with the formula "translation = speed*direction". With the next translation
     * calculated the next position can be estimated by the
     * sum of (current position + translation).
     * This method does not account for collisions
     * the server will have to test being next move against everything that collides
     * and adjusting the next translation vector accordingly.
     * @param nextMoveCommand <code>InputManager.InputCommands</code> type
     */
    public void GenerateNextMove(InputCommands nextMoveCommand){
//      read into this object the next move
        setNextMoveCommand(nextMoveCommand);

//      process what the next move means to animation
        CalcNextMoveAnimation();
//      looking at the move command set the direction of movement
        CalcNextMoveDirection();

//      multiply direction by movement speed
        CalcNextMoveTranslation();
        CalcNextPosition();
    }

    /**
     * Gets the int value of the being id
     * @return  the value of beingID that has been set
     *          on creation.
     */
    public int getBeingID() {
        return beingID;
    }

    /**
     * Get the Animation currently used by the
     * <code>BasicBeing</code>.
     * @return Currently set Being Animation
     */
    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    /**
     * The current displacement vector counts the number of
     * "steps" the <code>BasicBeing</code> has taken away from
     * their start game location.
     * This value is used in calculating the movement of the map
     * underneath the BasicBeing.
     * @return current displacement JIG Vector
     */
    public Vector getCurrentDisplacement() {
        return currentDisplacement;
    }

    /**
     * Gives only the X component of current displacement Vector.
     * @return current displacement vector X component
     */
    public float getCurrentDisplacementX(){
        return this.currentDisplacement.getX();
    }

    /**
     * Gives only the Y component of current displacement Vector.
     * @return current displacement vector Y component
     */
    public float getCurrentDisplacementY(){
        return this.currentDisplacement.getY();
    }

    /**
     * Health will be a stored value in <code>BasicBeing</code>
     * which all Hero and Mob types will inherit. Hit and IsDead
     * methods will have to reference the health value to make
     * their decisions.
     * @return current float value of being health
     */
    public float getHealth() {
        return health;
    }

    /**
     * Gets the String name identifier for the Being.
     * A separate identifier in string format. This name
     * field may serve as an identifier for clients to the
     * server.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get the last next move command the Being processed.
     * @return the last next move command sent the Being.
     */
    public InputCommands getNextMoveCommand() {
        return nextMoveCommand;
    }


    /**
     * Get the next move direction generated based on the
     * last next move command given.
     * @return currently projected direction of movement for the
     *         Being's next move.
     */
    public Vector getNextMoveDirection() {
        return nextMoveDirection;
    }

    /**
     * Get the next move translation generated based on the
     * last next move command given. Translation is the vector
     * defined by the multiplication of speed and direction.
     * @return currently projected speed and direction of movement for the
     *         Being's next move.
     */
    public Vector getNextMoveTranslation() {
        return nextMoveTranslation;
    }

    /**
     * Get the next position projected based on the current
     * next move translation.
     * @return currently projected next position of the Being.
     */
    public Vector getNextPosition() {
        return nextPosition;
    }

    /**
     * Get the current value of the speed field. Speed is used in
     * calculating the translation vector.
     * @return current scalar speed factor used in calculating
     *         the translation vector.
     */
    public float getSpeed() {
        return speed;
    }


    /**
     * Initializes the Being Animations for walking
     * and attacking. These are set in the Basic Being
     * Constructor.
     * @param walkingSheet
     * @param attackingSheet
     */
    private void InitAnimations(SpriteSheet walkingSheet, SpriteSheet attackingSheet) {
        this.walkRightAnim = new Animation(walkingSheet, 0,0,5,0,true,100,true);
        this.walkLeftAnim = new Animation(walkingSheet, 0,1,5,1,true,100,true);
        this.walkDnAnim = new Animation(walkingSheet, 0,2,5,2,true,100,true);
        this.walkUpAnim = new Animation(walkingSheet, 0,3,5,3,true,100,true);
        this.hitAnimRt = new Animation(walkingSheet, 0,4,1,4,true,100,true);
        this.hitAnimLt = new Animation(walkingSheet, 0,4,1,4,true,100,true);
        this.idleAnim = new Animation(walkingSheet, 0,5,5,5,true,100,true);

        this.deathAnim = new Animation(walkingSheet, 0,6,1,6,true,100,true);
        //  Attack and hit anim are the same except he shoots things when attacking.
        this.attackAnim = new Animation(walkingSheet, 0,4,1,4,true,100,true);
    }

    /**
     * Initializes the direction, translation, and
     * position vectors in the basic being class.
     */
    private void InitNextVectors() {
//        init next position to current position
        CalcNextPosition();
//        init the direction based on nextMoveCommand == idle
        CalcNextMoveDirection();
//      init the move translation using the other zero vectors
        CalcNextMoveTranslation();
    }

    /**
     * Determines if a Being is dead
     * @return true if health less than or equal zero
     */
    public boolean IsDead(){
        return health <= 0;
    }


    /**
     * Returns the value of <code>this.isHit</code> which is a flag
     * denoting the Being has encountered a collision. This method may
     * end up deprecated soon enough as it may not have a use.
     * @return the field
     */
    public boolean IsHit() {
        return isHit;
    }

    /**
     * Uses the Graphics parameter passed to it to render
     * the <code>BasicBeing</code>. <code>this.render(Graphics)</code>
     * is a method inherited from the JIG Entity class that
     * causes the Entity to show up on the screen.
     * @param g Slick2d Graphic type that is passed from the
     *          client renderer.
     */
    public void RenderBeing(Graphics g){
        this.render(g);
    }

    /**
     * Sets the <code>this.beingID</code> field with an int value.
     * @param beingID An integer value used to identify
     *                a being object.
     */
    public void setBeingID(int beingID) {
        this.beingID = beingID;
    }

    /**
     * Removes the current animation, then sets the currently
     * playing animation to the value passed to the method.
     * @param anim An Slick2d Animation type which gets stored in
     *             the field <code>this.currentAnim</code>.
     */
    public void setCurrentAnimation(Animation anim) {
        this.removeAnimation(currentAnim);
        this.currentAnim = anim;
        this.addAnimation(currentAnim);
    }


    /**
     * Sets a new current displacement Vector overwriting the old values.
     * @param currentDisplacement
     */
    public void setCurrentDisplacement(Vector currentDisplacement) {
        this.currentDisplacement = currentDisplacement;
    }

    /**
     * Calculates a new current displacement vector
     * with the argument as the new X value and the current Y value.
     * @param newX an integer value that will become
     *             the new X value of the current displacement.
     */
    public void setCurrentDisplacementX(int newX) {
        this.currentDisplacement = new Vector(newX,this.getCurrentDisplacementY());
    }

    /**
     * Creates a new current displacement vector
     * with the current X value and newY value.
     * @param newY an integer value that will become
     *             the new Y value of the current displacement.
     */
    public void setCurrentDisplacementY(int newY) {
        this.currentDisplacement = new Vector(this.getCurrentDisplacementX(),newY);
    }


    /**
     * Sets the current health value to the value passed to this method.
     * @param health float value that will be used to set health.
     */
    public void setHealth(float health) {
        this.health = health;
    }


    /**
     * Sets the <code>isHit</code> field with a boolean value.
     * @param hit a boolean value that sets the hit flag
     */
    public void setHit(boolean hit) {
        isHit = hit;
    }

    /**
     * Sets the next move command which dictates the Being
     * next move direction.
     * @param nextMoveCommand - An <code>InputManager</code> defined enum
     *                          <code>InputCommands</code>
     */
    private void setNextMoveCommand(InputCommands nextMoveCommand) {
        this.nextMoveCommand = nextMoveCommand;
    }

    /**
     * Sets the <code>BasicBeing</code> name field to the value passed in
     * the argument.
     * @param name a String value that can be a player name or their IP address.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Sets the speed to the float value passed it.
     * @param speed a float value that will be used as the magnitude
     *              of the translation vector.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }


    /**
     * Sets the Beings Position to the currently calculated <code>this.nextPosition</code>;
     * this should be called after collision checks have been made.
     */
    public void UpdateBeingPosition() {
        //  update being position based on next move
        //  and health if they were hit in the last collision check
        this.setPosition(this.nextPosition);
    }
}
