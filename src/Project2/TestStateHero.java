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

import static Project2.InputManager.ProcessInput;
import static Project2.MovementCalc.*;
import static Project2.InputManager.InputCommands;

public class TestStateHero extends BasicGameState {

    private int stateID;
    private Hero hero1;
    private Hero hero2;
    private Mob mob1;
    private ArrayList<BasicBeing> beingList ;
    private TiledMap map1;
    private final String LEVEL1RSC = "resources/Levels/Level1Remake.tmx";
    private final String TILESHEETRSC = "resources/Levels";
    public TestStateHero(int stateID) {
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        hero1 = new Hero(new Vector(90,105), false, "localhost");
        hero2 = new Hero(new Vector(92,105), false, "jugHead");
        mob1 = new Mob(new Vector(91,105), 1,"mob1");
        beingList = new ArrayList<BasicBeing>();
        beingList.add(hero1);
        beingList.add(hero2);
        beingList.add(mob1);
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
        hero2.setCommand(InputCommands.death);
        mob1.setCommand(InputCommands.attack);
        UpdateBeings(beingList);
    }

    public void UpdateBeings(ArrayList<BasicBeing> beings) {
//        set the translation for each being.

        for(BasicBeing being: beings) {
            Vector translation = CalcTranslation(CalcDirection(being.getCommand()), being.getSpeed());
//        a being's previous translation can be used to move them back from where they came if need be.
            being.setTranslation(translation);
            Vector newWorldPosition = CalcWorldPosition(translation,being.getWorldPosition());

//          updates the beings animation and world position.
//          server can just do being.setNewWorldPosition()
//          if swapping animation is unwanted extra computation cost
            being.UpdateBeing(being.getCommand(), newWorldPosition);

            if(being.getName() != Project2.getSettings().getIpAddress()){
//                hero1 is taking the place of localhost
                being.setScreenPosition(CalcScreenPosition(this.hero1.getWorldPosition(), being.getWorldPosition()));
            }
//            Allow Collision Manager to adjust for collisions.
            CollisionManager.CheckCollisions(beings);
//          if server write new world position to client packet
        }
    }
}
