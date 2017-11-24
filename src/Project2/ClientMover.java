//package Project2;
//
//import jig.Vector;
//
//public class ClientMover {
//    /**
//     * Evaluates the current displacement x and y components,
//     * then sets the world position accordingly. If the current
//     * displacement x or y component is less than 0 then world
//     * position is incremented and the displacement set to 32.
//     * If the displacement is greater than 32 then the world
//     * position is decremented and the displacement set to 0.
//     */
//    public Vector CalcCurrentWorldPosition(Vector worldPosition) {
//        float x = worldPosition.getX();
//        float y = worldPosition.getY();
//
//        if(getClientDisplacementX() < 0){
//            setClientDisplacementX(32);
//            x = worldPosition.getX();
//            y = worldPosition.getY();
//            return new Vector(++x, y);
//        }else if(getClientDisplacementX() > 32){
//            setClientDisplacementX(0);
//            x = worldPosition.getX();
//            y = worldPosition.getY();
//            return new Vector(--x,y);
//        }
//        if(getClientDisplacementY() < 0){
//            setClientDisplacementY(32);
//            x = worldPosition.getX();
//            y = worldPosition.getY();
//            return new Vector(x, ++y);
//        }else if(getClientDisplacementY() > 32){
//            setClientDisplacementX(0);
//            x = worldPosition.getX();
//            y = worldPosition.getY();
//            return new Vector(x,--y);
//        }
//        else
//            return null;
////        if (getCurrentDisplacementX() < 0) {
////            float x = getWorldPositionX();
////            setWorldPositionX(++x);
////            setCurrentDisplacementX(32);
////        }else if(getCurrentDisplacementX() > 32){
////            float x = getWorldPositionX();
////            setWorldPositionX(--x);
////            setCurrentDisplacementX(0);
////        }if(getCurrentDisplacementY() <0){
////            float y = getWorldPositionY();
////            setWorldPositionY(++y);
////            setCurrentDisplacementY(32);
////        }else if (getCurrentDisplacementY()> 32){
////            float y = getWorldPositionY();
////            setWorldPositionY(--y);
////            setCurrentDisplacementY(0);
////        }
//    }
//
//    private void setClientDisplacement(Vector v){
//        clientDisplacement = v;
//    }
//    private void setClientDisplacementX(float x){
//        float y = clientDisplacement.getY();
//        clientDisplacement = new Vector (x, y);
//    }
//    private void setClientDisplacementY(float y){
//        float x = clientDisplacement.getX();
//        clientDisplacement = new Vector (x, y);
//    }
//
//    private Vector getClientDisplacement(){
//        return clientDisplacement;
//    }
//    private float getClientDisplacementX(){
//        return clientDisplacement.getX();
//    }
//    private float getClientDisplacementY(){
//        return clientDisplacement.getY();
//    }
//
//    /**
//     * Calculates the BasicBeing's displacement given
//     * an inverted current next move translation vector's x and y
//     * components. Thus the field <code>this.currentDisplacement</code>
//     * holds a running total of the beings displacement from the center
//     * of their closest tile. Translation is inverted because the map
//     * will move in the opposite direction of the Being.
//     */
////    private void CalcCurrentDisplacement() {
////        if(this.currentDisplacement == null)
////            this.currentDisplacement = new Vector(0,0);
////        else{
////            int dx = (int)this.currentDisplacement.getX();
////            int dy = (int)this.currentDisplacement.getY();
////            dx += (int)this.nextMoveTranslation.getX()*-1;
////            dy += (int)this.nextMoveTranslation.getY()*-1;
//////            System.out.println("dx: "+dx+" dy: "+dy);
////            this.currentDisplacement = new Vector(dx, dy);
////        }
////    }
//}
