package Project2;

import jig.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.Random;

import static Project2.InputManager.InputCommands;
import static Project2.InputManager.ProcessInput;
import static Project2.MovementCalc.*;

public class TestStateBeingCollides extends BasicGameState {

    private int stateID;
    private Hero hero1;
    private Hero hero2;
    private Mob mob1;
    private Door door1;
    private ArrayList<BasicBeing> beingList ;
    private ArrayList<Object> objectList;
    private TiledMap map1;
    private final String LEVEL1RSC = "resources/Levels/Level1Remake.tmx";
    private final String TILESHEETRSC = "resources/Levels";
    public TestStateBeingCollides(int stateID) {
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
//        collides with mob1
        hero1 = new Hero(Project2.getSettings().getHostStartLevel1(), false, "localhost");
//        sets player to control hero1
        Project2.getSettings().setPlayer(hero1);
//        in hero1 prev location
        hero2 = new Hero(Project2.getSettings().getGuestStartLevel1(), false, "jugHead");
//        collides with hero1
        mob1 = new Mob(new Vector(40f,105f), 1,"mob1");
        mob1.setHealth(10f);
        door1 = new Door(new Vector(28.5f, 105.25f), "doorV");
        beingList = new ArrayList<BasicBeing>();
        objectList = new ArrayList<Object>();
        beingList.add(hero1);
        beingList.add(hero2);
        beingList.add(mob1);
        objectList.add(door1);
        Vector hero1Trans, hero2Trans, hero3Trans;
//        hero1.setTranslation(new Vector(-1f*hero1.getSpeed(), 0f));
//        hero2.setTranslation(new Vector(-1f*hero2.getSpeed(), 0f));
//        mob1.setTranslation(new Vector(0, 0f));
        map1 = new TiledMap(LEVEL1RSC, TILESHEETRSC);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        RenderMap(g);
        for(BasicBeing being:beingList){
            being.render(g);
        }
        for(Object object:objectList){
            object.render(g);
        }

    }

    private void RenderMap(Graphics g) {
        float displaceX, displaceY, worldPosX, worldPosY;
        worldPosX = (int)hero1.getWorldPositionX(); worldPosY = (int)hero1.getWorldPositionY();
        displaceX = (hero1.getWorldPositionX()-worldPosX)*-32; displaceY = (hero1.getWorldPositionY()-worldPosY)*-32;

        //  render the map using the client displacement from tile center
        //  and current world position.

        map1.render((int)displaceX, (int)displaceY,
                (int)worldPosX, (int)worldPosY, (int)worldPosX+45, (int)worldPosY+30 );

        g.drawString("displaceX: "+displaceX*-1
                +" displaceY:"+displaceY*-1, 200,200);
        g.drawString("worldX: "+hero1.getWorldPositionX()
                +"      worldY:"+hero1.getWorldPositionY(), 200,230);
        g.drawString("screenX: "+hero1.getScreenPositionX()
                +" screenY:"+hero1.getScreenPositionY(), 200,260);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        InputCommands command = ProcessInput(input, getID());
        hero1.setCommand(command);
        hero2.setCommand(InputCommands.left);
        mob1.setCommand(InputCommands.idle);
        mob1.setHealth((float)(mob1.getHealth() - 0.1f));
//        CollisionManager.CheckCollisions(beingList);
        UpdateBeings(beingList);
        UpdateObjects(objectList);
    }

    private void UpdateBeings(ArrayList<BasicBeing> beings) throws SlickException {
//  make a move for each being when the "player" moves everybody must update their JIG Vector
        ArrayList<BasicBeing> removals = new ArrayList<BasicBeing>();
        ArrayList<BasicBeing> additions = new ArrayList<BasicBeing>();
        for(BasicBeing being: beings) {
            Vector translation;
            translation = CalcTranslation(CalcDirection(being.getCommand()), being.getSpeed());
//        a being's previous translation can be used to move them back from where they came if need be.
            being.setTranslation(translation);
            Vector newWorldPosition = CalcWorldPosition(translation,being.getWorldPosition());
//          updates the beings animation and world position.
//          server can just do being.setNewWorldPosition()
//          if swapping animation is unwanted extra computation cost
            being.UpdateBeing(being.getCommand(), newWorldPosition);
            if (CollisionManager.CheckValidMove(being))
                CollisionManager.CheckCollisions(being, beings);

//          if server write new world position to client packet
            if (being.getName() != Project2.getSettings().getIpAddress()) {
//                hero1 is taking the place of localhost
                being.setScreenPosition(CalcScreenPosition(Project2.getSettings().getPlayer().getWorldPosition(), being.getWorldPosition()));
            }
            if(being.getHealth() <= 0){
                Vector position = being.getWorldPosition();
                removals.add(being);
                Random random = new Random();
                int low = 1;
                int high = 21;
                int value = random.nextInt(high-low) + low;
                Money money = new Money(position, "money", value);
                money.setHealth(1);
                objectList.add(money);
            }
        }
        for(BasicBeing being : removals)
            beingList.remove(being);
        for (BasicBeing being : additions)
            beingList.add(being);
        removals.clear();
        additions.clear();
//        check their new position for collides
    }

    private void UpdateObjects(ArrayList<Object> objects) throws SlickException {
//  make a move for each Object when the "player" moves everybody must update their JIG Vector
        ArrayList<Object> removals = new ArrayList<Object>();
        ArrayList<Object> additions = new ArrayList<Object>();
        for(Object object: objects) {
            Vector translation;
            translation = CalcTranslation(CalcDirection(InputCommands.idle), 0f);
//        a object's previous translation can be used to move them back from where they came if need be.
            object.setTranslation(translation);
            Vector newWorldPosition = CalcWorldPosition(translation,object.getWorldPosition());
//          updates the objects animation and world position.
//          server can just do object.setNewWorldPosition()
            object.UpdateObject(newWorldPosition);

//          if server write new world position to client packet
            if (object.getName() != Project2.getSettings().getIpAddress()) {
//                hero1 is taking the place of localhost
                object.setScreenPosition(CalcScreenPosition(Project2.getSettings().getPlayer().getWorldPosition(), object.getWorldPosition()));
            }
        }
        for(Object object : removals)
            objectList.remove(object);
        for (Object object : additions)
            objectList.add(object);
        removals.clear();
        additions.clear();
//        check their new position for collides
    }
}
