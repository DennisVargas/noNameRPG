package Project2;

import jig.Entity;
import jig.ResourceManager;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


/**
 * a Hammerwatch/Gauntlent slick2d <code>StateBasedGame</code>.
 */
public class Project2 extends StateBasedGame {
    private boolean testStatePlay = false;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = (WIDTH/16)*9;



    public static PlaySettings settings = new PlaySettings("localhost");
    /**
     * If changing the screen resolution this SCALE variable
     * can be adjusted to set the graphics ratio.
     * at 1280x720 scale = 1f;
     * at 1600x900 scale = 1.25;
     * at 1920x1080 scale = 1.50
     */
    public static final float SCALE = 1f;
    public static final String NAME = "Game Project 2";

    /**
     * 5 MAIN GAME STATES
     */
    public static final int MAINMENUSTATE = 0;
    public static int NEWSINGLEMENUSTATE = 1;
    public static final int NEWMULTIMENUSTATE = 2;
    public static final int OPTIONMENUSTATE = 3;
    public static final int GAMEPLAYSTATE = 4;

    /**
     * these are defined test states for development purposes
     */
    public static final int TESTSTATEBASICBEING = 20;
    public static final int MENUITEMTESTSTATE = 21;
    public static final int TESTSTATEHERO = 22;



    public static final String MELEEHEROATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    public static final String MELEEHEROWALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    public static final String RANGEDHEROWALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    public static final String RANGEDHEROATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    public static final String MOB1WALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    public static final String MOB1ATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    public static final String MOB2WALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    public static final String MOB2ATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    public static final String MOB3WALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    public static final String MOB3ATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    public static final String MOB4WALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    public static final String MOB4ATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";

    /**
     * instantiates the game name and then creates a
     * new instance of each of the states in the game.
     * MainMenu starts the order and New Single menu
     * leads to <code>TestStateBasicBeing</code>
     */
    public Project2() {
        super(NAME);
//       TEST STATES
        this.addState(new TestStateHero(TESTSTATEHERO));
//        this.addState(new TestStateBasicBeing(TESTSTATEBASICBEING));

//      official states
        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new NewSingleMenu(NEWSINGLEMENUSTATE));
        this.addState(new NewMultiMenu(NEWMULTIMENUSTATE));
        this.addState(new OptionMenuState(OPTIONMENUSTATE));
        this.addState(new GamePlayState(GAMEPLAYSTATE));
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
        Input input = gameContainer.getInput();
        input.initControllers();
        input.clearControlPressedRecord();

        ResourceManager.loadImage(MOB1WALKINGSHEETRSC);
        ResourceManager.loadImage(MOB1ATTACKINGSHEETRSC);
        ResourceManager.loadImage(MELEEHEROATTACKINGSHEETRSC);
        ResourceManager.loadImage(MELEEHEROWALKINGSHEETRSC);
        ResourceManager.loadImage(RANGEDHEROWALKINGSHEETRSC);
        ResourceManager.loadImage(RANGEDHEROATTACKINGSHEETRSC);
//        test states init
//        this.getState(TESTSTATEBASICBEING).init(gameContainer,this);
        this.getState(TESTSTATEHERO).init(gameContainer,this);

//        official state init
        this.getState(MAINMENUSTATE).init(gameContainer, this);
        this.getState(NEWSINGLEMENUSTATE).init(gameContainer, this);
        this.getState(NEWMULTIMENUSTATE).init(gameContainer, this);
        this.getState(OPTIONMENUSTATE).init(gameContainer, this);
        this.getState(GAMEPLAYSTATE).init(gameContainer, this);


    }

    public static PlaySettings getSettings() {
        return settings;
    }

}
