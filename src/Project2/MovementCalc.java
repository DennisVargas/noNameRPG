package Project2;

import jig.Vector;

public abstract class MovementCalc {


//
//    private void CalcNextMoveDirection() {
//        float diagSpeed = (float)Math.sqrt(2)/2;
//        switch (this.nextMoveCommand){
//            case up:
//                this.nextMoveDirection = new Vector(0,-1f);
//                break;
//            case down:
//                this.nextMoveDirection = new Vector(0,1f);
//                break;
//            case left:
//                this.nextMoveDirection = new Vector(-1f,0);
//                break;
//            case right:
//                this.nextMoveDirection = new Vector(1f,0);
//                break;
//            case ulDiag:
//                this.nextMoveDirection = new Vector(-1f,-1f);
//                break;
//            case dlDiag:
//                this.nextMoveDirection = new Vector(-1f,1f);
//                break;
//            case urDiag:
//                this.nextMoveDirection = new Vector(1f,-1f);
//                break;
//            case drDiag:
//                this.nextMoveDirection = new Vector(1f,1f);
//                break;
//            case idle:
//                this.nextMoveDirection = new Vector(0,0);
//                break;
//            case attack:
//                this.nextMoveDirection = new Vector(0,0);
//                break;
//        }
//    }
//
//
//    private void CalcNextMoveTranslation() {
//        nextMoveTranslation = new Vector(nextMoveDirection.getX()*speed,
//                nextMoveDirection.getY()*speed);
//    }
//
//
//    private void CalcNextPosition() {
//        if(this.nextScreenPosition == null)
//            this.nextScreenPosition = this.getPosition().copy();
//        else
//            this.nextScreenPosition = new Vector( this.nextMoveTranslation.getX() + this.getPosition().getX(),
//                    this.nextMoveTranslation.getY() + this.getPosition().getY());
//    }
}
