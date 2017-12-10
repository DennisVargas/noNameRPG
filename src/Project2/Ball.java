package Project2;

import jig.Entity;
import jig.Vector;
import org.newdawn.slick.geom.Circle;
import static Project2.InputManager.InputCommands;
import static Project2.InputManager.InputCommands.*;

public class Ball extends Entity {

    public Circle ball;
    private float attackPower = 20f;
    private Vector worldPosition;
    private int radius = 3;
    private Vector velocity;
    private String name;
    private float speed = 0.3f;
    private long time;

    // server side ball constructor
    // x and y are actual world coordinates, tx and ty are tiled coordinates (WorldPosition on beings)
    public Ball (BasicBeing attacker, String name) {
        super(attacker.getX(), attacker.getY());
        worldPosition = new Vector(attacker.getWorldPositionX(), attacker.getWorldPositionY());
        ball = new Circle(attacker.getWorldPositionX(), attacker.getWorldPositionY(), radius);
        radius = 3;
        attackPower = attacker.getAttackPower();
        time = System.currentTimeMillis();
        InputCommands cmd = attacker.getCommand();
        if (cmd == up || cmd == down || cmd == left || cmd == right ||
                cmd == ulDiag || cmd == urDiag || cmd == dlDiag || cmd == drDiag) {
            setVelocity(cmd);
        } else {
            cmd = attacker.getLastDirectionCommand();
            setVelocity(cmd);
        }

        this.name = name;
    }

    // client side ball constructor
    public Ball (float x, float y, String name) {
        super(x, y);
        worldPosition = new Vector(x, y);
        ball = new Circle(x, y, radius);
        radius = 3;
        this.name = name;
    }

    public void setVelocity(InputCommands input) {
        switch (input) {
            case up:
                velocity =  new Vector(0,-speed); break;
            case down:
                velocity =  new Vector(0,speed); break;
            case left:
                velocity =  new Vector(-speed,0); break;
            case right:
                velocity =  new Vector(speed,0); break;
            case ulDiag:
                velocity =  new Vector(-speed,-speed); break;
            case dlDiag:
                velocity =  new Vector(-speed,speed); break;
            case urDiag:
                velocity =  new Vector(speed,-speed); break;
            case drDiag:
                velocity =  new Vector(speed,speed); break;
        }
    }

    public void setWorldPosition(Vector worldPosition) {
        this.worldPosition = worldPosition;
    }

    public float getWorldPositionX() {
        return this.worldPosition.getX();
    }

    public float getWorldPositionY() {
        return this.worldPosition.getY();
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    public void update() {
        translate(velocity.scale(20));
        setWorldPosition(new Vector(getX()/32, getY()/32));
        ball.setLocation(getWorldPositionX(), getWorldPositionY());
    }
}
