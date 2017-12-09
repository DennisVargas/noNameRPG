package Project2;

import Project2.InputManager.InputCommands;
import jig.Collision;
import jig.Vector;

import java.util.ArrayList;

import static Project2.InputManager.InputCommands.*;

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
        for(Mob mob: mobs) {
            Collision collides = null;
            if (!mob.IsDead()) {
                if (hero.getCommand() == InputCommands.attack) {
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
                    hero.setWorldPosition(MovementCalc.CalcWorldPosition(ReverseCommand(hero.getCommand()), hero.getWorldPosition(), hero.getSpeed()));
                    hero.setPosition(new Vector(hero.getWorldPositionX() * 32f, hero.getWorldPositionY() * 32f));

                    if(hero.getCommand().equals(InputCommands.dlDiag)){
                        hero.setCommand(InputCommands.down);
                        hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                        if collision
                        if (CheckHeroMobCollisions(hero, mobs)){
                            hero.setCommand(InputCommands.left);
                            hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                            return result of if collidied or not
                            return CheckHeroMobCollisions(hero,mobs);
                        }else
//                            no collision
                            return false;
                    }else if(hero.getCommand().equals(InputCommands.drDiag)){
                        hero.setCommand(InputCommands.down);
                        hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                      if collision
                        if (CheckHeroMobCollisions(hero, mobs)){
                            hero.setCommand(InputCommands.right);
                            hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                            return result of if collidied or not
                            return CheckHeroMobCollisions(hero,mobs);
                        }else
//                            no collision
                            return false;
                    }else if(hero.getCommand().equals(InputCommands.ulDiag)){
                        hero.setCommand(InputCommands.up);
                        hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                      if collision
                        if (CheckHeroMobCollisions(hero, mobs)){
                            hero.setCommand(InputCommands.left);
                            hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                            return result of if collidied or not
                            return CheckHeroMobCollisions(hero,mobs);
                        }else
//                            no collision
                            return false;
                    }else if(hero.getCommand().equals(InputCommands.urDiag)){
                        hero.setCommand(InputCommands.up);
                        hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                      if collision
                        if (CheckHeroMobCollisions(hero, mobs)){
                            hero.setCommand(InputCommands.right);
                            hero.setWorldPosition(MovementCalc.CalcWorldPosition(hero.getCommand(),hero.getWorldPosition(),hero.getSpeed()));
//                            return result of if collidied or not
                            return CheckHeroMobCollisions(hero,mobs);
                        }else
//                            no collision
                            return false;
                    }
                    return true;
//                System.out.println("we collided Hero mob style x,y hero " + hero.getPosition() + "x,y mob: " + mob.getPosition());
//                System.out.println("collision min penetration: " + collides.getMinPenetration());
                }
            }
        }
        return false;
    }

    public static boolean CheckMobHeroCollisions(Mob mob, ArrayList<Hero> heroes) {
        for(Hero hero: heroes){
            Collision collides = null;
            if(mob.getCommand() == InputCommands.attack){
//                make an attack move so you collide in the direction moving if you don't collide go back.
                Vector attackPos = MovementCalc.CalcWorldPosition(mob.getLastDirectionCommand(), mob.getWorldPosition(), mob.getSpeed());
                mob.setPosition(new Vector (attackPos.getX()*32f,attackPos.getY()*32f));

                if ((collides = mob.collides(hero))!= null){
//                    System.out.println("hit Hero before health: "+hero.getHealth());

                    hero.HitBeing(mob.getAttackPower());
//                    System.out.println("hit Hero after health: "+hero.getHealth());
                }
//              reverse the attack move
                attackPos = MovementCalc.CalcWorldPosition(ReverseCommand(mob.getLastDirectionCommand()), mob.getWorldPosition(), mob.getSpeed());
                mob.setPosition(new Vector (attackPos.getX()*32f,attackPos.getY()*32f));
            }
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
        if(player.getHealth() == 2)
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
}
