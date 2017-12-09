package Project2;

import jig.Entity;
import jig.Vector;
import org.newdawn.slick.geom.Circle;
import static Project2.InputManager.InputCommands;

public class Ball extends Entity {

    private Circle ball;
    private float attackPower = 20f;
    private InputCommands input;
    private Vector worldPosition;
    private Vector velocity;

    // x and y are actual world coordinates, tx and ty are tiled coordinates (WorldPosition on beings)
    public Ball (BasicBeing attacker) {
        super(attacker.getX(), attacker.getY());
        ball = new Circle(attacker.getX(), attacker.getY(), 3);
        worldPosition = new Vector(attacker.getWorldPositionX(), attacker.getWorldPositionY());
        attackPower = attacker.getAttackPower();
        setVelocity(attacker.getLastDirectionCommand());
    }

    public void setVelocity(InputCommands input) {
        switch (input) {
            case up:
                velocity =  new Vector(0,-1f);
            case down:
                velocity =  new Vector(0,1f);
            case left:
                velocity =  new Vector(-1f,0);
            case right:
                velocity =  new Vector(1f,0);
            case ulDiag:
                velocity =  new Vector(-1f,-1f);
            case dlDiag:
                velocity =  new Vector(-1f,1f);
            case urDiag:
                velocity =  new Vector(1f,-1f);
            case drDiag:
                velocity =  new Vector(1f,1f);
        }
    }

    public void update() {
        translate(velocity.scale(15));
    }
}
