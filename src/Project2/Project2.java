package Project2;

import jdk.nashorn.internal.objects.annotations.Property;
import jig.Entity;
import org.lwjgl.util.mapped.MappedField;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Project2 extends StateBasedGame {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = (WIDTH/16)*9;
    /**
     * If changing the screen resolution this SCALE variable
     * can be adjusted to set the graphics ratio.
     * at 1280x720 scale = 1f;
     * at 1600x900 scale = 1.25;
     * at 1920x1080 scale = 1.50
     */
    public static final float SCALE = 1f;
    public static final String NAME = "Game Project 2";

    public static final int MAINMENUSTATE = 0;
    /**
     * these are defined test states for development purposes
     */
    public static final int BASICBEINGTESTSTATE = 20;



    public Project2() {
        super(NAME);
    }

    public static void main(String[] args)throws SlickException{
        try {
            AppGameContainer app = new AppGameContainer(new Project2());
            app.setDisplayMode((int) (WIDTH * SCALE), (int) (HEIGHT * SCALE), false);
            app.setVSync(true);
            app.setShowFPS(false);
            app.start();
        }catch(Exception e){}
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        Entity.setCoarseGrainedCollisionBoundary(Entity.CIRCLE);
        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new BasicBeingTestState(BASICBEINGTESTSTATE));
    }

}
