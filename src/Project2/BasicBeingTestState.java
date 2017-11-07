package Project2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class BasicBeingTestState extends BasicGameState{
    private int state_id;
    private int n;
    private BasicBeing being1;
    public BasicBeingTestState(int state_id) {
        this.state_id = state_id;
    }

    @Override
    public int getID() {
        return this.state_id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString("hello Test Basic Being " + n, 640,360);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        n++;
    }
}
