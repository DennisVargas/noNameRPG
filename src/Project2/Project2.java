package Project2;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Project2 extends StateBasedGame {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = (WIDTH/16)*9;
    public static final float SCALE = 1f;
    public static final String NAME = "Game Project 2";

    public Project2() {
        super(NAME);
    }

    public static void main(String[] args)throws SlickException{
        AppGameContainer app = new AppGameContainer(new Project2());
        app.setDisplayMode((int)(WIDTH*SCALE), (int)(HEIGHT*SCALE),false);
        app.setVSync(true);
        app.setShowFPS(false);
        app.start();
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
    }

}
