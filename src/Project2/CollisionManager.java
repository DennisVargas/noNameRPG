package Project2;

import Project2.InputManager.InputCommands;
import jig.Collision;
import jig.Vector;

import java.util.ArrayList;

import static Project2.InputManager.InputCommands.*;
import static java.lang.StrictMath.abs;

/**
 * manages all collisions between being/being and being/wall
 */
public class CollisionManager {


    public static boolean CheckValidMove(BasicBeing being){
//        System.out.println(Project2.settings.checkTile((int)floor(being.getWorldPositionX()),(int)floor(being.getWorldPositionY())));
        if (Project2.settings.checkTile((int) Math.round(being.getWorldPositionX()-.5) + 20, (int) Math.round(being.getWorldPositionY()) + 11).equals("abyss") |
                Project2.settings.checkTile((int) Math.round(being.getWorldPositionX()-.5) + 20, (int) Math.round(being.getWorldPositionY()) + 11).equals("wall")) {
            being.setWorldPosition(MovementCalc.CalcWorldPosition(ReverseCommand(being.getCommand()), being.getWorldPosition(),being.getSpeed()));
            if(being.getCommand().equals(InputCommands.dlDiag)){
                being.setCommand(InputCommands.down);
                being.setWorldPosition(MovementCalc.CalcWorldPosition(being.getCommand(),being.getWorldPosition(),being.getSpeed()));
                if (!CheckValidMove(being)){
                    being.setCommand(InputCommands.left);
                    being.setWorldPosition(MovementCalc.CalcWorldPosition(being.getCommand(),being.getWorldPosition(),being.getSpeed()));
                    return CheckValidMove(being);
                }else
                    return true;
            }else if(being.getCommand().equals(InputCommands.drDiag)){
                being.setCommand(InputCommands.down);
                being.setWorldPosition(MovementCalc.CalcWorldPosition(being.getCommand(),being.getWorldPosition(),being.getSpeed()));
                if (!CheckValidMove(being)){
                    being.setCommand(InputCommands.right);
                    being.setWorldPosition(MovementCalc.CalcWorldPosition(being.getCommand(),being.getWorldPosition(),being.getSpeed()));
                    return CheckValidMove(being);
                }else
                    return true;
            }else if(being.getCommand().equals(InputCommands.ulDiag)){
                being.setCommand(InputCommands.up);
                being.setWorldPosition(MovementCalc.CalcWorldPosition(being.getCommand(),being.getWorldPosition(),being.getSpeed()));
                if (!CheckValidMove(being)){
                    being.setCommand(InputCommands.left);
                    being.setWorldPosition(MovementCalc.CalcWorldPosition(being.getCommand(),being.getWorldPosition(),being.getSpeed()));
                    return CheckValidMove(being);
                }else
                    return true;
            }else if(being.getCommand().equals(InputCommands.urDiag)){
                being.setCommand(InputCommands.up);
                being.setWorldPosition(MovementCalc.CalcWorldPosition(being.getCommand(),being.getWorldPosition(),being.getSpeed()));
                if (!CheckValidMove(being)){
                    being.setCommand(InputCommands.right);
                    being.setWorldPosition(MovementCalc.CalcWorldPosition(being.getCommand(),being.getWorldPosition(),being.getSpeed()));
                    return CheckValidMove(being);
                }else
                    return true;
            }
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
        boolean diagAdjustmentCollided= false;
        InputCommands oldDiagCommand = null;
        for(Mob mob: mobs) {
            Collision collides = null;
            if (!mob.IsDead()) {
                if (hero.getCommand() == InputCommands.attack && !hero.isRanged()) {
//                make an attack move so you collide in the direction moving if you don't collide go back.
                    Vector attackPos = MovementCalc.CalcWorldPosition(hero.getLastDirectionCommand(), hero.getWorldPosition(), hero.getSpeed());
                    hero.setPosition(new Vector(attackPos.getX() * 32f, attackPos.getY() * 32f));

                    if ((collides = hero.collides(mob)) != null) {
//                        System.out.println("hit MOB before health: " + mob.getHealth());

                        mob.HitBeing(hero.getAttackPower());
//                        System.out.println("hit MOB after health: " + mob.getHealth());
                        if (mob.IsDead()) {
                            mob.setCommand(InputCommands.death);
//                            System.out.println("I'm Dead");
                        }
                    }
//              reverse the attack move
                    attackPos = MovementCalc.CalcWorldPosition(ReverseCommand(hero.getLastDirectionCommand()), hero.getWorldPosition(), hero.getSpeed());
                    hero.setPosition(new Vector(attackPos.getX() * 32f, attackPos.getY() * 32f));
                }
                if ((collides = hero.collides(mob)) != null) {
//                    System.out.println("Collides Min Pen: "+collides.getMinPenetration());
                    Vector collisionSide = collides.getMinPenetration();
                    if((abs(collisionSide.getX()) == 0) && (hero.getCommand().equals(InputCommands.left)||hero.getCommand().equals(InputCommands.right)))
                        continue;
                    else if((abs(collisionSide.getY()) == 0) && (hero.getCommand().equals(InputCommands.up)||hero.getCommand().equals(InputCommands.down)))
                        continue;
                    else if((collisionSide.getX() > 0) && (hero.getCommand().equals(InputCommands.right)))
                        continue;
                    else if((collisionSide.getX() < 0) && (hero.getCommand().equals(InputCommands.left)))
                        continue;
                    else if((collisionSide.getY() < 0) && (hero.getCommand().equals(InputCommands.up)))
                        continue;
                    else if((collisionSide.getY() > 0) && (hero.getCommand().equals(InputCommands.down)))
                        continue;
                    hero.setWorldPosition(MovementCalc.CalcWorldPosition(ReverseCommand(hero.getCommand()), hero.getWorldPosition(), hero.getSpeed()));
                    hero.setPosition(new Vector(hero.getWorldPositionX() * 32f, hero.getWorldPositionY() * 32f));

                    if(hero.getCommand().equals(InputCommands.dlDiag)){
                        hero.setCommand(InputCommands.down);
                        hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                        if collision
                        if (diagAdjustmentCollided = CheckHeroMobCollisions(hero, mobs)){
                            hero.setCommand(InputCommands.left);
                            hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                            return result of if collidied or not
                            diagAdjustmentCollided = CheckHeroMobCollisions(hero,mobs);
                            if(!diagAdjustmentCollided)
                                hero.setCommand(InputCommands.dlDiag);
                            return diagAdjustmentCollided;
//                            continue;
                        }else
//                            no collision
                            hero.setCommand(InputCommands.dlDiag);
                            return diagAdjustmentCollided;
//                            continue;
                    }else if(hero.getCommand().equals(InputCommands.drDiag)){
                        hero.setCommand(InputCommands.down);
                        hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                      if collision
                        if (diagAdjustmentCollided = CheckHeroMobCollisions(hero, mobs)){
                            hero.setCommand(InputCommands.right);
                            hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                            return result of if collidied or not
                            diagAdjustmentCollided = CheckHeroMobCollisions(hero,mobs);
                            if(!diagAdjustmentCollided)
                                hero.setCommand(InputCommands.drDiag);
                            return diagAdjustmentCollided;
//                            continue;
                        }else
//                            no collision
                            hero.setCommand(InputCommands.drDiag);
                            return diagAdjustmentCollided;
//                            continue;
                    }else if(hero.getCommand().equals(InputCommands.ulDiag)){
                        hero.setCommand(InputCommands.up);
                        hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                      if collision
                        if (diagAdjustmentCollided = CheckHeroMobCollisions(hero, mobs)){
                            hero.setCommand(InputCommands.left);
                            hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                            return result of if collidied or not
                            diagAdjustmentCollided = CheckHeroMobCollisions(hero,mobs);
                            if(!diagAdjustmentCollided)
                                hero.setCommand(InputCommands.ulDiag);
                            return diagAdjustmentCollided;
//                            continue;
                        }else{
//                            no collision
                            hero.setCommand(InputCommands.ulDiag);
                            return diagAdjustmentCollided;
//                            continue;
                        }
                    }else if(hero.getCommand().equals(InputCommands.urDiag)){
                        hero.setCommand(InputCommands.up);
                        hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                      if collision
                        if (diagAdjustmentCollided = CheckHeroMobCollisions(hero, mobs)){
                            hero.setCommand(InputCommands.right);
                            hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                            return result of if collidied or not
                            diagAdjustmentCollided = CheckHeroMobCollisions(hero,mobs);
                            if(!diagAdjustmentCollided)
                                hero.setCommand(InputCommands.urDiag);
                            return diagAdjustmentCollided;
//                            continue;
                        }else {
//                            no collision
                            hero.setCommand(InputCommands.urDiag);
                            return diagAdjustmentCollided;
//                            continue;
                        }
                    }
                    return true;
//                System.out.println("we collided Hero mob style x,y hero " + hero.getPosition() + "x,y mob: " + mob.getPosition());
//                System.out.println("collision min penetration: " + collides.getMinPenetration());
                }
            }
        }
        return false;
    }

    public static boolean CheckHeroCrateCollisions(Hero hero, ArrayList<Crate> crates) {
        boolean diagAdjustmentCollided= false;
        InputCommands oldDiagCommand = null;
        for(int i = 0; i < crates.size(); i++) {
            Collision collides = null;
            if (!crates.get(i).IsDead()) {
                if (hero.getCommand() == InputCommands.attack && !hero.isRanged()) {
//                make an attack move so you collide in the direction moving if you don't collide go back.
                    Vector attackPos = MovementCalc.CalcWorldPosition(hero.getLastDirectionCommand(), hero.getWorldPosition(), hero.getSpeed());
                    hero.setPosition(new Vector(attackPos.getX() * 32f, attackPos.getY() * 32f));

                    if ((collides = hero.collides(crates.get(i))) != null) {
//                        System.out.println("hit MOB before health: " + mob.getHealth());

                        crates.get(i).HitBeing(hero.getAttackPower());
//                        System.out.println("hit MOB after health: " + mob.getHealth());
                        if (crates.get(i).IsDead()) {
                            crates.get(i).setCommand(InputCommands.death);
//                            System.out.println("I'm Dead");
                        }
                    }
//              reverse the attack move
                    attackPos = MovementCalc.CalcWorldPosition(ReverseCommand(hero.getLastDirectionCommand()), hero.getWorldPosition(), hero.getSpeed());
                    hero.setPosition(new Vector(attackPos.getX() * 32f, attackPos.getY() * 32f));
                }
                if ((collides = hero.collides(crates.get(i))) != null) {
//                    System.out.println("Collides Min Pen: "+collides.getMinPenetration());
                    Vector collisionSide = collides.getMinPenetration();
                    if((abs(collisionSide.getX()) == 0) && (hero.getCommand().equals(InputCommands.left)||hero.getCommand().equals(InputCommands.right)))
                        continue;
                    else if((abs(collisionSide.getY()) == 0) && (hero.getCommand().equals(InputCommands.up)||hero.getCommand().equals(InputCommands.down)))
                        continue;
                    else if((collisionSide.getX() > 0) && (hero.getCommand().equals(InputCommands.right)))
                        continue;
                    else if((collisionSide.getX() < 0) && (hero.getCommand().equals(InputCommands.left)))
                        continue;
                    else if((collisionSide.getY() < 0) && (hero.getCommand().equals(InputCommands.up)))
                        continue;
                    else if((collisionSide.getY() > 0) && (hero.getCommand().equals(InputCommands.down)))
                        continue;
                    hero.setWorldPosition(MovementCalc.CalcWorldPosition(ReverseCommand(hero.getCommand()), hero.getWorldPosition(), hero.getSpeed()));
                    hero.setPosition(new Vector(hero.getWorldPositionX() * 32f, hero.getWorldPositionY() * 32f));

                    if(hero.getCommand().equals(InputCommands.dlDiag)){
                        hero.setCommand(InputCommands.down);
                        hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                        if collision
                        if (diagAdjustmentCollided = CheckHeroCrateCollisions(hero, crates)){
                            hero.setCommand(InputCommands.left);
                            hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                            return result of if collidied or not
                            diagAdjustmentCollided = CheckHeroCrateCollisions(hero,crates);
                            if(!diagAdjustmentCollided)
                                hero.setCommand(InputCommands.dlDiag);
                            return diagAdjustmentCollided;
//                            continue;
                        }else
//                            no collision
                            hero.setCommand(InputCommands.dlDiag);
                        return diagAdjustmentCollided;
//                            continue;
                    }else if(hero.getCommand().equals(InputCommands.drDiag)){
                        hero.setCommand(InputCommands.down);
                        hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                      if collision
                        if (diagAdjustmentCollided = CheckHeroCrateCollisions(hero, crates)){
                            hero.setCommand(InputCommands.right);
                            hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                            return result of if collidied or not
                            diagAdjustmentCollided = CheckHeroCrateCollisions(hero,crates);
                            if(!diagAdjustmentCollided)
                                hero.setCommand(InputCommands.drDiag);
                            return diagAdjustmentCollided;
//                            continue;
                        }else
//                            no collision
                            hero.setCommand(InputCommands.drDiag);
                        return diagAdjustmentCollided;
//                            continue;
                    }else if(hero.getCommand().equals(InputCommands.ulDiag)){
                        hero.setCommand(InputCommands.up);
                        hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                      if collision
                        if (diagAdjustmentCollided = CheckHeroCrateCollisions(hero, crates)){
                            hero.setCommand(InputCommands.left);
                            hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                            return result of if collidied or not
                            diagAdjustmentCollided = CheckHeroCrateCollisions(hero,crates);
                            if(!diagAdjustmentCollided)
                                hero.setCommand(InputCommands.ulDiag);
                            return diagAdjustmentCollided;
//                            continue;
                        }else{
//                            no collision
                            hero.setCommand(InputCommands.ulDiag);
                            return diagAdjustmentCollided;
//                            continue;
                        }
                    }else if(hero.getCommand().equals(InputCommands.urDiag)){
                        hero.setCommand(InputCommands.up);
                        hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                      if collision
                        if (diagAdjustmentCollided = CheckHeroCrateCollisions(hero, crates)){
                            hero.setCommand(InputCommands.right);
                            hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                            return result of if collidied or not
                            diagAdjustmentCollided = CheckHeroCrateCollisions(hero,crates);
                            if(!diagAdjustmentCollided)
                                hero.setCommand(InputCommands.urDiag);
                            return diagAdjustmentCollided;
//                            continue;
                        }else {
//                            no collision
                            hero.setCommand(InputCommands.urDiag);
                            return diagAdjustmentCollided;
//                            continue;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean CheckMobHeroCollisions(Mob mob, ArrayList<Hero> heroes) {
        for(Hero hero: heroes){
            Collision collides = null;
//            if(mob.getCommand() == InputCommands.attack){
////                make an attack move so you collide in the direction moving if you don't collide go back.
//                Vector attackPos = MovementCalc.CalcWorldPosition(mob.getLastDirectionCommand(), mob.getWorldPosition(), mob.getSpeed());
//                mob.setPosition(new Vector (attackPos.getX()*32f,attackPos.getY()*32f));
//
//                if ((collides = mob.collides(hero))!= null){
////                    System.out.println("hit Hero before health: "+hero.getHealth());
//
////                    hero.HitBeing(mob.getAttackPower());
////                    System.out.println("hit Hero after health: "+hero.getHealth());
//                }
////              reverse the attack move
//                attackPos = MovementCalc.CalcWorldPosition(ReverseCommand(mob.getLastDirectionCommand()), mob.getWorldPosition(), mob.getSpeed());
//                mob.setPosition(new Vector (attackPos.getX()*32f,attackPos.getY()*32f));
//            }
            if((collides = mob.collides(hero))!=null){
                mob.setWorldPosition(MovementCalc.CalcWorldPosition(ReverseCommand(mob.getCommand()), mob.getWorldPosition(), mob.getSpeed()));
                mob.setPosition(new Vector(mob.getWorldPositionX()*32f,mob.getWorldPositionY()*32f));
//                System.out.println("hit HERO before health: " + hero.getHealth());

                hero.HitBeing(mob.meleeAttack());
//                System.out.println("hit HERO after health: " + hero.getHealth());
//                System.out.println("we collided Mob hero style x,y mob "+mob.getPosition()+"x,y mob: "+hero.getPosition());
//                System.out.println("collision min penetration: "+collides.getMinPenetration());
                return true;
            }
        }
        return false;
    }

    public static boolean CheckHeroHeroCollisions(Hero hero, ArrayList<Hero> heroes) {
        ArrayList<Hero> testList = (ArrayList<Hero>)heroes.clone();
        testList.remove(hero);
        for(Hero testHero: testList){
            Collision collides = null;
            if((collides = hero.collides(testHero))!=null){
                hero.setWorldPosition(MovementCalc.CalcWorldPosition(ReverseCommand(hero.getCommand()), hero.getWorldPosition(), hero.getSpeed()));
                hero.setPosition(new Vector(hero.getWorldPositionX()*32f,hero.getWorldPositionY()*32f));
                return true;
            }
        }
        return false;
    }

    public static boolean CheckMobMobCollisions(Mob mob, ArrayList<Mob> mobs) {
        ArrayList<Mob> testList = (ArrayList<Mob>)mobs.clone();
        testList.remove(mob);
        for(Mob testMob: testList){
            Collision collides = null;
            if((collides = mob.collides(testMob))!=null){
                mob.setWorldPosition(MovementCalc.CalcWorldPosition(ReverseCommand(mob.getCommand()), mob.getWorldPosition(), mob.getSpeed()));
                mob.setPosition(new Vector(mob.getWorldPositionX()*32f,mob.getWorldPositionY()*32f));
                return true;
            }
        }
        return false;
    }

    public static Money CheckHeroMoneyCollision(BasicBeing player, ArrayList<Money> moneys) {
        if (moneys.isEmpty() | moneys == null | player == null)
            return null;
        for(Money money: moneys) {
            Collision collides = null;
            if ((collides = money.collides(player)) != null) {
                return money;
            }
        }
        return null;
    }

    public static Health CheckHeroHealthCollision(BasicBeing player, ArrayList<Health> healths) {
        if(player.getHealth() >= 1f)
            return null;
        if (healths.isEmpty() | healths == null | player == null)
            return null;
        for (Health health: healths) {
            Collision collides = null;
            if ((collides = health.collides(player)) != null) {
                return health;
            }
        }
        return null;
    }

    public static void CheckEntityBallCollisions(BasicBeing entity, ArrayList<Ball> balls) {
        Collision collides = null;

        float entityMinX = entity.getCoarseGrainedMinX();
        float entityMaxX = entity.getCoarseGrainedMaxX();
        float entityMinY = entity.getCoarseGrainedMinY();
        float entityMaxY = entity.getCoarseGrainedMaxY();
        for (int i = 0; i < balls.size(); i++) {
            float ballX = balls.get(i).getX();
            float ballY = balls.get(i).getY();

            if (ballX > entityMinX && ballX < entityMaxX) {
                if (ballY > entityMinY && ballY < entityMaxY) {
//                    System.out.println("Collision check: entity hit");
                    entity.HitBeing(balls.get(i).getAttackPower());
                    balls.get(i).setCommand(rm);
                }
            }
        }
    }
    public static void CheckBallsToWall(ArrayList<Ball> balls) {
        for (int i = 0; i < balls.size(); i++) {
            if (Project2.settings.checkTile((int) Math.round(balls.get(i).getWorldPositionX()-.5) + 20, (int) Math.round(balls.get(i).getWorldPositionY()) + 11).equals("abyss") |
                    Project2.settings.checkTile((int) Math.round(balls.get(i).getWorldPositionX()-.5) + 20, (int) Math.round(balls.get(i).getWorldPositionY()) + 11).equals("wall")) {
                balls.get(i).setCommand(rm);
            }
        }
    }

    public static boolean CheckHeroDestinationCollision(Hero player) {
        if (Project2.settings.checkTile((int) Math.round(player.getWorldPositionX()-.5) + 20, (int) Math.round(player.getWorldPositionY()) + 11).equals("dest")) {
            return true;
        }
        return false;
    }

    public static boolean CheckHeroGoalCollision(Hero player) {
        if (Project2.settings.checkTile((int) Math.round(player.getWorldPositionX()-.5) + 20, (int) Math.round(player.getWorldPositionY()) + 11).equals("goal")) {
            return true;
        }
        return false;
    }

    public static Key CheckHeroKeyCollision(BasicBeing player, ArrayList<Key> keys) {
        for (Key key : keys) {
            Collision collides = null;
            if ((collides = key.collides(player)) != null) {
                return key;
            }
        }
        return null;
    }

    public static Door CheckHeroDoorCollision(BasicBeing player, ArrayList<Door> doors){
        for (Door door: doors) {
            Collision collides = null;
            if ((collides = door.collides(player)) != null) {
                Project2.settings.editTileMapping(door.getWorldPositionX(), door.getWorldPositionY(), "path");
                return door;
            }
        }
        return null;
    }
}
