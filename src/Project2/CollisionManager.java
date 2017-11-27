package Project2;

import jig.Vector;

import java.util.ArrayList;
import Project2.InputManager.*;

import static Project2.InputManager.InputCommands.*;
import static java.lang.Math.abs;

public class CollisionManager {

    public static boolean CheckCollisions(BasicBeing being, ArrayList<BasicBeing>beings){

//        while(collisionFound)
            return CheckBeingBeingCollisions(being, beings);
    }

    public static void CheckBeingBeingCollisions(ArrayList<BasicBeing> beings) {
//        as beings are tested move
        ArrayList <BasicBeing> testList;
        for(BasicBeing being: beings){
            testList = (ArrayList<BasicBeing>)beings.clone();
            testList.remove(being);
            for(BasicBeing testBeing: testList){
                if(being.collides(testBeing)!= null){
                    System.out.println("collision between "+being.getName()+" and "+ testBeing.getName());
                    float undoX, undoY;
                    undoX = being.getTranslation().getX()*-1f;
                    undoY = being.getTranslation().getY()*-1f;
                    Vector undoMoveTranslation = new Vector(undoX, undoY);
                    being.setTranslation(undoMoveTranslation);
                    being.setWorldPosition(MovementCalc.CalcWorldPosition(undoMoveTranslation,being.getWorldPosition()));
                }
            }
        }
    }

}
