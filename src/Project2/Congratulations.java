package Project2;

import jig.ResourceManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;


public class Congratulations extends BasicGameState {
    private static final String CONGRATS = "resources/Other/congrats.png";


    private int timer;

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        ResourceManager.loadImage(CONGRATS);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
        timer = 2000;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        Project2 proj = (Project2)game;
        g.drawImage(ResourceManager.getImage(CONGRATS), 230,
                200);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game,
                       int delta) throws SlickException {
        timer -= delta;
        if (timer <= 0)
            game.enterState(Project2.MAINMENUSTATE, new EmptyTransition(), new HorizontalSplitTransition() );
    }

    @Override
    public int getID() {
        return Project2.CONGRATULATIONS;
    }



}
