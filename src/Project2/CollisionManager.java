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

    public static boolean CheckBeingBeingCollisions(BasicBeing being, ArrayList<BasicBeing>beings) {
        ArrayList <BasicBeing> testList;
        testList = (ArrayList<BasicBeing>)beings.clone();
        testList.remove(being);
        for(BasicBeing testBeing: testList){
//          get the difference between the x coords
            float diffX = being.getWorldPositionX() - testBeing.getWorldPositionX();
//          get the difference between the y coords
            float diffY = being.getWorldPositionY() - testBeing.getWorldPositionY();

//            if differences are less than one you've collided
            if(abs(diffX) < 1 && abs(diffY) < 1){
                System.out.println("XY-Collide");
//              Reverse the being's last move by reversing the move command and creating a new world position vector
//              alternatively we could store the being previous location
                being.setWorldPosition(MovementCalc.CalcWorldPosition(
                        MovementCalc.CalcTranslation(
                                MovementCalc.CalcDirection(
                                        ReverseCommand(being.getCommand()))
                                ,being.getSpeed())
                        ,being.getWorldPosition()));
//                the being is not a mob
                if(!being.getName().contains("mob")){
                    if(being.getCommand() == InputCommands.attack){
                    }
                }
                return true;
            }
        }
    }

}
