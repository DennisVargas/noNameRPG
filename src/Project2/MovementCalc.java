package Project2;

import jig.Vector;
import static Project2.InputManager.InputCommands;


public class MovementCalc {

    /**
     * Calculates the direction dependent on
     * the current value of the <code>moveCommand</code> argument.
     * Movement commands are defined in the InputManager class. The
     * command is placed in a switch and evaluated accordingly. A
     * resulting UNIT Vector is set as the next move direction vector.
     * Thus an up command produces vector (0,1).
     */
    public static Vector CalcDirection(InputCommands moveCommand) {
        float diagSpeed = (float)Math.sqrt(2)/2;
        switch (moveCommand){
            case up:
                return new Vector(0,-1f);
            case down:
                return new Vector(0,1f);
            case left:
                return new Vector(-1f,0);
            case right:
                return new Vector(1f,0);
            case ulDiag:
                return new Vector(-1f,-1f);
            case dlDiag:
                return new Vector(-1f,1f);
            case urDiag:
                return new Vector(1f,-1f);
            case drDiag:
                return new Vector(1f,1f);
            case idle:
                return new Vector(0,0);
            case attack:
                return new Vector(0,0);
        }
        return null;
    }


    /**
     * A new vector is returned that is the product of direction*speed; translation vector.
     * @param direction unit Vector in the direction of the movement
     * @param speed scalar speed of float type
     * @return
     */
    public static Vector CalcTranslation(Vector direction, float speed) {
        return new Vector(direction.getX()*speed,direction.getY()*speed);
    }


    /**
     * Calculates the world position given arguments translation and current world position.
     * new world position = "current world position" + (translation)/32
     */
    public static Vector CalcWorldPosition(Vector translation, Vector curWorldPos) {
        float x = curWorldPos.getX();
        float y = curWorldPos.getY();
        x += (translation.getX()/32f);
        y += (translation.getY()/32f);
        return new Vector(x, y);
    }
}
