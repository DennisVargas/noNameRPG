package Project2;

import jig.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TestStateHero extends BasicGameState {

    private int stateID;
    private Hero hero1;

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

    }
}
