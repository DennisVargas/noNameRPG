package Project2;

import jig.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

import static Project2.InputManager.ProcessInput;
import static Project2.MovementCalc.*;
import static Project2.InputManager.InputCommands;

public class TestStateHero extends BasicGameState {

    private int stateID;
    private Hero hero1;
    private ArrayList<BasicBeing> beingList ;

    public TestStateHero(int stateID) {
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        hero1 = new Hero(new Vector(0,0), false);
        beingList = new ArrayList<BasicBeing>();
        beingList.add(hero1);

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        hero1.render(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        InputCommands command = ProcessInput(input, getID());
        UpdateBeings(beingList, command);
    }

    private void UpdateBeings(ArrayList<BasicBeing> beings, InputCommands command) {
//        set the translation for each being.

        for(BasicBeing being: beings) {
            System.out.println(being.getClass());
            Vector translation = CalcTranslation(CalcDirection(command), being.getSpeed());
//        a being's previous translation can be used to move them back from where they came if need be.
            being.setTranslation(translation);
        }
//        check the beings collision
    }
}
