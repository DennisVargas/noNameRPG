package Project2;

import jig.Vector;

import java.util.ArrayList;
import Project2.InputManager.*;

import static Project2.InputManager.InputCommands.*;
import static java.lang.Math.abs;
import static java.lang.Math.floor;

/**
 * manages all collisions between being/being and being/wall
 */
public class CollisionManager {


    /**
     * iterates through all the collision checkers being and then wall.
     * @param being
     * @param beings
     * @return
     */
    public static boolean CheckCollisions(BasicBeing being, ArrayList<BasicBeing>beings){

//        while(collisionFound)
            return CheckBeingBeingCollisions(being, beings);
    }


    /**
     * checks the collisions between a being and the rest of the beings on the map.
     * @param being being to be adjusted if collision detected
     * @param beings list of beings including the being to be adjusted
     * @return true if a collision was detected
     */
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
//                System.out.println("XY-Collide");
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
        return false;
    }

    public static void CheckPlayerObjectCollision(Hero player, ArrayList<Object> objectList){
        for(Object object: objectList){
//          get the difference between the x coords
            float diffX = player.getWorldPositionX() - object.getWorldPositionX();
//          get the difference between the y coords
            float diffY = player.getWorldPositionY() - object.getWorldPositionY();

//          if differences are less than one you've collided
            if(abs(diffX) < 1 && abs(diffY) < 1) {
//              Remove object, add to player
                if (object.getName().contains("money")) {
                    player.increaseValue(object.getName(), object.value);
                }
            }
        }
    }

    public static boolean CheckValidMove(BasicBeing being){
//        System.out.println(Project2.settings.checkTile((int)floor(being.getWorldPositionX()),(int)floor(being.getWorldPositionY())));
        if (Project2.settings.checkTile((int) Math.round(being.getWorldPositionX()-.5) + 20, (int) Math.round(being.getWorldPositionY()) + 11).equals("abyss") |
                Project2.settings.checkTile((int) Math.round(being.getWorldPositionX()-.5) + 20, (int) Math.round(being.getWorldPositionY()) + 11).equals("wall")) {
            being.setWorldPosition(MovementCalc.CalcWorldPosition(
                    MovementCalc.CalcTranslation(
                            MovementCalc.CalcDirection(
                                    ReverseCommand(being.getCommand()))
                            , being.getSpeed())
                    , being.getWorldPosition()));
            return false;
        }
        else
            return true;
    }

    /**
     * is given an InputCommands and reverses the order; Up goes to Down
     * @param command
     * @return
     */
    private static InputManager.InputCommands ReverseCommand(InputCommands command) {
        switch (command){
            case up:
                return down;
            case down:
                return up;
            case left:
                return right;
            case right:
                return left;
            case ulDiag:
                return drDiag;
            case dlDiag:
                return urDiag;
            case urDiag:
                return dlDiag;
            case drDiag:
                return ulDiag;
        }
        return command;
    }

}
