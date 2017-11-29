package Project2;

import jig.Collision;
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

            if(being.collides(testBeing)!= null){
                System.out.println("XY-Collide");
//              Reverse the being's last move by reversing the move command and creating a new world position vector
//              alternatively we could store the being previous location
                being.setWorldPosition(MovementCalc.CalcWorldPosition(ReverseCommand(being.getCommand()), being.getWorldPosition(),being.getSpeed()));

//                the being is not a mob
                if(!being.getName().contains("mob")){
                    if(being.getCommand() == InputCommands.attack){
//                        beings
                        switch(being.getLastDirectionCommand()){
                            case up:
//                                testBeing is above being
                                if(testBeing.getWorldPositionY() < being.getWorldPositionY())
                                    testBeing.HitBeing(being.getAttackPower());
                                break;
                            case down:
                                break;
                            case left:
                                break;
                            case right:
                                break;
                            case attack:
                                break;
                            case hit:
                                break;
                            case ulDiag:
                                break;
                            case dlDiag:
                                break;
                            case urDiag:
                                break;
                            case drDiag:
                                break;
                            case hitLt:
                                break;
                            case hitRt:
                                break;
                            case death:
                                break;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean CheckValidMove(BasicBeing being){
//        System.out.println(Project2.settings.checkTile((int)floor(being.getWorldPositionX()),(int)floor(being.getWorldPositionY())));
        if (Project2.settings.checkTile((int) Math.round(being.getWorldPositionX()-.5) + 20, (int) Math.round(being.getWorldPositionY()) + 11).equals("abyss") |
                Project2.settings.checkTile((int) Math.round(being.getWorldPositionX()-.5) + 20, (int) Math.round(being.getWorldPositionY()) + 11).equals("wall")) {
            being.setWorldPosition(MovementCalc.CalcWorldPosition(ReverseCommand(being.getCommand()), being.getWorldPosition(),being.getSpeed()));
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

    public static boolean CheckHeroMobCollisions(Hero hero, ArrayList<Mob> mobs) {
        for(Mob mob: mobs){
            Collision collides = null;
            if(hero.getCommand() == InputCommands.attack){
//                make an attack move so you collide in the direction moving if you don't collide go back.
                Vector attackPos = MovementCalc.CalcWorldPosition(hero.getLastDirectionCommand(), hero.getWorldPosition(), hero.getSpeed());
                hero.setPosition(new Vector (attackPos.getX()*32f,attackPos.getY()*32f));

                if ((collides = hero.collides(mob))!= null){
                    System.out.println("hit MOB before health: "+mob.getHealth());

                    mob.HitBeing(hero.getAttackPower());
                    System.out.println("hit MOB after health: "+mob.getHealth());
                }
//              reverse the attack move
                attackPos = MovementCalc.CalcWorldPosition(ReverseCommand(hero.getLastDirectionCommand()), hero.getWorldPosition(), hero.getSpeed());
                hero.setPosition(new Vector (attackPos.getX()*32f,attackPos.getY()*32f));
            }
            if((collides = hero.collides(mob))!=null){

                hero.setWorldPosition(MovementCalc.CalcWorldPosition(ReverseCommand(hero.getCommand()), hero.getWorldPosition(), hero.getSpeed()));

                hero.setPosition(new Vector(hero.getWorldPositionX() * 32f, hero.getWorldPositionY() * 32f));
//                System.out.println("we collided Hero mob style x,y hero " + hero.getPosition() + "x,y mob: " + mob.getPosition());
//                System.out.println("collision min penetration: " + collides.getMinPenetration());
                return true;
            }
        }
        return false;
    }

    public static boolean CheckMobHeroCollisions(Mob mob, ArrayList<Hero> heroes) {
        for(Hero hero: heroes){
            Collision collides = null;
            if((collides = mob.collides(hero))!=null){
                mob.setWorldPosition(MovementCalc.CalcWorldPosition(ReverseCommand(mob.getCommand()), mob.getWorldPosition(), mob.getSpeed()));
                mob.setPosition(new Vector(mob.getWorldPositionX()*32f,mob.getWorldPositionY()*32f));
//                System.out.println("we collided Mob hero style x,y mob "+mob.getPosition()+"x,y mob: "+hero.getPosition());
//                System.out.println("collision min penetration: "+collides.getMinPenetration());
                return true;
            }
        }
        return false;
    }
}
