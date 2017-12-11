package Project2;

import jig.ConvexPolygon;
import jig.Entity;
import jig.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

import static Project2.InputManager.InputCommands;


/**
 * The BasicBeing class will define all HeroTypes and MobTypes
 * this class defines all attributes heroes and enemies.
 */
public class BasicBeing extends Entity{

    //translation = velocity


    private float attackPower = 10f;
    private float health = 1f;
    private float speed = 2f;
    boolean isClient = false;
    boolean dead = false;


    boolean isRanged = false;

    protected Animation   walkRightAnim, walkLeftAnim, walkUpAnim,
            walkDnAnim, idleAnimLt, idleAnimRt, attackAnim,
            hitAnimLt, hitAnimRt, deathAnim, currentAnim;

    private String name = "default";
    private int beingID = 0;

    private InputCommands inputCommand = InputCommands.idle;

    private InputCommands lastDirectionCommand = InputCommands.left;
    private Vector worldPosition;
    private Vector translation;
    private Vector screenPosition;

    private Animation currentAnimation;

    private long attacktimer = 0; // time of last attack
    private int attackdelay = 500; // time between attacks


    /**
     * Constructs a basic being by initializing its Screen and World position, current Animation, and next Vectors.
     * The being gets initialized to the idle animation to begin with.
     * Finally All the vectors for the next Being and map movement are initialized.
     *
     * @param screenPosition: A JIG Vector that set the Being starting location
     * @param walkingSheet:   A sprite sheet of non attacking animation frames
     * @param attackingSheet: A sprite sheet of attack animation frames
     */
    public BasicBeing(Vector screenPosition, Vector worldPosition, SpriteSheet walkingSheet, SpriteSheet attackingSheet) {
        super(screenPosition);
        setScreenPosition(screenPosition);
        setWorldPosition(worldPosition);
        InitAnimations(walkingSheet, attackingSheet);
        setCommand(InputCommands.idle);
        setLastDirectionCommand(InputCommands.left);
//        InitNextVectors();
        this.debugThis = true;
    }

    public BasicBeing(Vector screenPosition, Vector worldPosition){
        super(screenPosition);
        setScreenPosition(screenPosition);
        setWorldPosition(worldPosition);
//        InitNextVectors();
        this.debugThis = false;
    }

    public InputManager.InputCommands getLastDirectionCommand() {
        return lastDirectionCommand;
    }

    public void setLastDirectionCommand(InputManager.InputCommands lastDirectionCommand) {
        this.lastDirectionCommand = lastDirectionCommand;
    }

    /**
     * Sets the next animation dependent on
     * the value of <code>nextMoveCommand</code> field.
     * Evaluates the possible animation options based on
     * the <code>InputManager.InputCommands</code> enumeration.
     */
    private void ProcessNextAnimation(InputCommands command) {

        switch(command){
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
                if(currentAnim == walkLeftAnim || currentAnim == hitAnimLt || currentAnim == walkDnAnim)
                    setCurrentAnimation(idleAnimLt);
                else if(currentAnim == walkRightAnim || currentAnim == hitAnimRt || currentAnim == walkUpAnim)
                    setCurrentAnimation(idleAnimRt);
                break;
            case attack:
                if(currentAnim == walkLeftAnim || currentAnim == idleAnimLt || currentAnim == walkDnAnim)
                    setCurrentAnimation(hitAnimLt);
                else if(currentAnim == walkRightAnim || currentAnim == idleAnimRt || currentAnim == walkUpAnim)
                    setCurrentAnimation(hitAnimRt);
                break;
            case hitLt:
                setCurrentAnimation(hitAnimLt);
                break;
            case hitRt:
                setCurrentAnimation(hitAnimRt);
                break;
            case death:
                setCurrentAnimation(deathAnim);
                break;
        }
    }

    public Ball rangedAttack(String name) {
        Ball ball = new Ball(this, name);
        attacktimer = System.currentTimeMillis();
        return ball;
    }

    protected float meleeAttack() {
//        System.out.println((System.currentTimeMillis()-getAttacktimer()));
        if ((System.currentTimeMillis()-getAttacktimer()) >= getAttackdelay()) {
            setAttacktimer(System.currentTimeMillis());
            return getAttackPower();
        } else
            return 0;
    }
    /**
     * attack power is used in hit method for reducing another being's health.
     * @return float value for the being attack power
     */
    public float getAttackPower() {
        return attackPower;
    }

    /**
     * attack power is used in hit method for reducing another being's health.
     * @param attackPower float that denotes the amount a beings health is reduced
     */
    public void setAttackPower(float attackPower) {
        this.attackPower = attackPower;
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
     * @return string identification for the being
     */
    public String getName() {
        return name;
    }


    /**
     * Get the next move velocity generated based on the
     * last next move command given. Velocity is the vector
     * defined by the multiplication of speed and direction.
     * @return currently projected speed and direction of movement for the
     *         Being's next move.
     */

    public Vector getTranslation() {
        return translation;
    }


    /**
     * Gets the X component of the ScreenPosition.
     * ScreenPosition will be useful to Beings who are
     * not clients.
     * @return  float x component of the
     *          current screen position.
     */
    public float getScreenPositionX() {
        return this.screenPosition.getX();
    }
    /**
     * Gets the Y component of the ScreenPosition.
     * ScreenPosition will be useful to Beings who are
     * not clients.
     * @return  float Y component of the
     *          current screen position.
     */
    public float getScreenPositionY() {
        return this.screenPosition.getY();
    }

    /**
     * Get the current value of the speed field. Speed is used in
     * calculating the velocity vector.
     * @return current scalar speed factor used in calculating
     *         the velocity vector.
     */
    public float getSpeed() {
        return speed;
    }


    /**
     * Gets the Vector representing the Being current
     * map world position.
     * @return Position Vector of current map world
     */
    public Vector getWorldPosition(){
        return this.worldPosition;
    }

    /**
     * Gets the world position X coordinate.
     * @return float of the world position x component
     */
    public float getWorldPositionX() {
        return this.worldPosition.getX();
    }

    /**
     * Gets the world position Y coordinate.
     * @return float of the world position y component
     */
    public float getWorldPositionY() {
        return this.worldPosition.getY();
    }

    public boolean isRanged() {
        return isRanged;
    }



    /**
     * Initializes the Being Animations for walking, idle, attacking, and death.
     * Sprite sheets are passed into the Basic Being Constructor.
     * @param walkingSheet
     * @param attackingSheet
     */
    protected void InitAnimations(SpriteSheet walkingSheet, SpriteSheet attackingSheet) {
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

    /**
     * subtracts a percentage of the current health from the current health.
     * @param attackValue float that
     */
    public void HitBeing(float attackValue){
//        reduces attack value by a percentage of its health
        if (this.health > 0) {
            this.health = this.health - (attackValue/100);
        }
        if (this.getHealth()<=0)
            this.dead = true;
    }

    /**
     * Initializes the direction, velocity, and
     * position vectors in the basic being class.
     */
    private void InitNextVectors() {
//        init next position to current position
//        CalcNextPosition();
////        init the direction based on nextMoveCommand == idle
//        CalcNextMoveDirection();
////      init the move translation using the other zero vectors
//        CalcNextMoveTranslation();
//        CalcCurrentDisplacement();
    }

    /**
     * Returns true when the client flag is enabled during
     * initialization.
     * @return true when client flag enabled on construction
     */
    public boolean isClient() {
        return isClient;
    }

    /**
     * Determines if a Being is dead
     * @return true if health less than or equal zero
     */
    public boolean IsDead(){
        if(dead || this.getCommand().equals(InputCommands.death))
            return true;
        else
            return false;
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
     * Sets the current health value to the value passed to this method.
     * @param health float value that will be used to set health.
     */
    public void setHealth(float health) {
        if(health > 1)
            this.health = 1;
        else
            this.health = health;
    }


    public void setRanged(boolean ranged) {
        isRanged = ranged;
    }

    /**
     * Sets the <code>BasicBeing</code> name field to the value passed in
     * the argument.
     * @param name a String value that can be a player name or their IP address.
     */
    public void setName(String name) {
        this.name = name;
    }


    public void setTranslation(Vector v){
        this.translation = v;
    }

    /**
     * Sets the Being screen position to the Vector passed
     * into the method arguments.
     * @param screenPosition Vector of the new current
     *                       Being screen position
     */
    public void setScreenPosition(Vector screenPosition) {
        this.screenPosition = screenPosition;
//        JIG entity position which is what the screen will render
        this.setPosition(screenPosition);
    }

    /**
     * Sets the X component of Being screen position Vector.
     * @param x new x component
     */
    public void setScreenPositionX(float x){
        float y = this.screenPosition.getY();
        this.screenPosition = new Vector(x, y);
    }

    /**
     * Sets the Y component of Being screen position Vector.
     * @param y new y component
     */
    public void setScreenPositionY(float y){
        float x = this.screenPosition.getX();
        this.screenPosition = new Vector(x, y);
    }

    /**
     * Sets the speed to the float value passed it.
     * @param speed a float value that will be used as the magnitude
     *              of the velocity vector.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Sets the world position to the Vector passed into the argument.
     * @param worldPosition Vector for the new current world position
     */
    public void setWorldPosition(Vector worldPosition) {
        this.worldPosition = worldPosition;
    }

    /**
     * Sets the x component of the <code>BasicBeing</code>
     * world position vector.
     * @param x float value for new world position x component
     */
    public void setWorldPositionX(float x) {
        float y = getWorldPositionY();
        this.worldPosition = new Vector(x, y);
    }
    /**
     * Sets the y component of the <code>BasicBeing</code>
     * world position vector.
     * @param y float value for new world position y component
     */
    public void setWorldPositionY(float y) {
        float x = getWorldPositionX();
        this.worldPosition = new Vector(x, y);
    }

    /**
     * method which updates the <code>BasicBeing</code> animation and world position
     * @param command InputCommands command which switches animation
     * @param newWorldPos Vector which becomes new world position
     */
    public void UpdateBeing(InputCommands command, Vector newWorldPos){
        ProcessNextAnimation(command);
        setWorldPosition(newWorldPos);
    }
    
    public void setCommand(InputCommands cmd) {
        if(!this.inputCommand.equals(InputCommands.death) ) {
            if (cmd.equals(InputManager.InputCommands.down)
                    || cmd.equals(InputManager.InputCommands.up)
                    || cmd.equals(InputManager.InputCommands.left)
                    || cmd.equals(InputManager.InputCommands.right)
                    || cmd.equals(InputManager.InputCommands.dlDiag)
                    || cmd.equals(InputManager.InputCommands.drDiag)
                    || cmd.equals(InputManager.InputCommands.ulDiag)
                    || cmd.equals(InputManager.InputCommands.urDiag))
                this.setLastDirectionCommand(cmd);
            this.inputCommand = cmd;
        }
    }

    public InputCommands getCommand(){
        return this.inputCommand;
    }

    public long getAttacktimer() { return attacktimer; }

    public int getAttackdelay() { return attackdelay; }

    public void setAttacktimer(long attacktimer) {
        this.attacktimer = attacktimer;
    }
}
