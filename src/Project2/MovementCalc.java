package Project2;

import jig.Vector;
import static Project2.InputManager.InputCommands;


public abstract class MovementCalc {


    /**
     * Calculates the next move direction dependent on
     * the current value of the <code>nextMoveCommand</code> field.
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


    public static Vector CalcTranslation(Vector direction, float speed) {
        return new Vector(direction.getX()*speed,direction.getY()*speed);
    }


//    private void CalcNextPosition() {
//        if(this.nextScreenPosition == null)
//            this.nextScreenPosition = this.getPosition().copy();
//        else
//            this.nextScreenPosition = new Vector( this.nextMoveTranslation.getX() + this.getPosition().getX(),
//                    this.nextMoveTranslation.getY() + this.getPosition().getY());
//    }

    /**
     * Calculates the BasicBeing's displacement given
     * an inverted current next move translation vector's x and y
     * components. Thus the field <code>this.currentDisplacement</code>
     * holds a running total of the beings displacement from the center
     * of their closest tile. Translation is inverted because the map
     * will move in the opposite direction of the Being.
     */
    public static Vector CalcWorldPosition(Vector translation, Vector curWorldPos) {
        float x = curWorldPos.getX();
        float y = curWorldPos.getY();
        x += (translation.getX()/32f);
        y += (translation.getY()/32f);
        return new Vector(x, y);
    }
}
