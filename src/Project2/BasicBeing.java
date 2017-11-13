package Project2;

import jig.Entity;
import jig.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

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
    private Animation walkRightAnim, walkLeftAnim, walkUpAnim, walkDnAnim,
            idleAnim, attackAnim, hitAnim, deathAnim, currentAnim;
    private String name = "default";
    private InputCommands nextMoveCommand;
    private Vector nextPosition;
    private Vector nextMoveDirection;
    private Vector nextMoveTranslation;
    private float speed =2f;
    private Animation currentAnimation;
    boolean isHit = false;


    public BasicBeing(Vector position, SpriteSheet walkingSheet, SpriteSheet attackingSheet) {
        super(position);
        nextMoveCommand = idle;
        InitAnimations(walkingSheet, attackingSheet);
//        this.addAnimation(walkRightAnim);
//        this.addAnimation(walkLeftAnim);
        setCurrentAnimation(idleAnim);
        InitNextVectors();

    }

    public boolean isDead(){
        return health <= 0;
    }

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
        setNextMoveTranslation();
//
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
                setCurrentAnimation(walkLeftAnim);
                break;
            case right:
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
}
