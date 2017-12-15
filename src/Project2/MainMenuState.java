package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import static Project2.InputManager.InputCommands;

/**
 * Provides a user interface for transitioning
 * into the single player menu, multi player menu,
 * options menu, and quitting the game.
 */
public class MainMenuState extends BasicGameState {
    private int stateId;

    private InputCommands inputCommand;

    //  MenuItem objects are a vector position and a animation
    private MenuItem newSingleItem; private MenuItem quitItem;
    private MenuItem newMultiItem;

    public int getStateId() {
        return stateId;
    }

    /**
     * defines the menu choices available.
     * useful when switching the current
     * selection from off to on.
     */
    private enum MainMenuChoices {NewSingle, NewMulti, Quit}
    MainMenuChoices menuChoice;

    //  image resource file paths that will be passed to MenuItem() constructor
    private static String singlePlayerOffImageRsc = "testAssets/new_game3.png";
    private static String singlePlayerOnImageRsc = "testAssets/new_game4.png";
    private static String multiPlayerOffImageRsc = "testAssets/multi_1.png";
    private static String multiPlayerOnImageRsc = "testAssets/multi_2.png";
    private static String quitOffImageRsc = "testAssets/quit3.png";
    private static String quitOnImageRsc = "testAssets/quit4.png";


    /**
     * constructor sets the stateId of the MainMenu instance.
     * The stateId passed from Project2.java is constant but
     * needs to be passed down so the ID is common and global.
     * @param stateId int variable that is used to
     *                designate that this is a MenuState
     */
    public MainMenuState(int stateId) {
        this.stateId = stateId;
    }

    /**
     * returns the current state id variable.
     * @return int value of the stateID.
     */
    @Override
    public int getID() {
        return stateId;
    }

    /**
     * Initializes the <code>MainMenuState</code>; loads image resources,
     * sets the initial menu choice.
     */
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        ResourceManager.loadImage(singlePlayerOffImageRsc);
        ResourceManager.loadImage(singlePlayerOnImageRsc);
        ResourceManager.loadImage(multiPlayerOffImageRsc);
        ResourceManager.loadImage(multiPlayerOnImageRsc);
        ResourceManager.loadImage(quitOffImageRsc);
        ResourceManager.loadImage(quitOnImageRsc);
        menuChoice = MainMenuChoices.NewSingle;
    }

    /**
     * upon entering the state the MenuItem objects are instantiated.
     * Each menu choice is given a position, off image,
     * on image and a name. These are arguments to the <code>MenuItem</code>
     * constructor.
     */
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        newSingleItem = new MenuItem(new Vector(640f, 250f), singlePlayerOffImageRsc, singlePlayerOnImageRsc,"new-game-item");
        newSingleItem.setItemOn();
        newMultiItem = new MenuItem(new Vector(640f, 300f), multiPlayerOffImageRsc, multiPlayerOnImageRsc,"quit-item");
        newMultiItem.setItemOff();
        quitItem = new MenuItem(new Vector(635f, 350f), quitOffImageRsc, quitOnImageRsc,"quit-item");
        quitItem.setItemOff();
        menuChoice = MainMenuChoices.NewSingle;
    }


    /**
     * calls the <code>MenuItem.renderItem()</code> method for each of the
     * items in main menu. A string is drawn to screen showing the accurate
     * menu selection as opposed to the png currently being used which are
     * placeholders till menu art is complete.
     */
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        newSingleItem.renderItem(graphics);
        newMultiItem.renderItem(graphics);
        quitItem.renderItem(graphics);
//        graphics.drawString(String.valueOf(menuChoice), 200,125);
    }


    /**
     * processes input with InputManager then uses the results in the
     * <code>ProcessInputCommand()</code>.
     */
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        inputCommand = InputManager.ProcessInput(input, getStateId());
        ProcessInputCommand(inputCommand, stateBasedGame);
    }

    /**
     * Swaps the MenuItem image and sets the current menu choice with up and down input commands,
     * confirms selection with 'hit' commands.
     */
    private void ProcessInputCommand(InputManager.InputCommands inputCommand, StateBasedGame stateBasedGame) {
        switch(inputCommand){
            case up:
                // menuChoice gets set in Input Process this is wrong.
                switch(menuChoice){
                    case NewSingle:
                        newSingleItem.setItemOff();
                        quitItem.setItemOn();
                        menuChoice = MainMenuChoices.Quit;
                        break;
                    case NewMulti:
                        newSingleItem.setItemOn();
                        newMultiItem.setItemOff();
                        menuChoice = MainMenuChoices.NewSingle;
                        break;
                    case Quit:
                        quitItem.setItemOff();
                        newMultiItem.setItemOn();
                        menuChoice = MainMenuChoices.NewMulti;
                        break;
                    default:
                        break;
                }
                break;
            case down:
                switch(menuChoice){
                    case NewSingle:
                        newSingleItem.setItemOff();
                        newMultiItem.setItemOn();
                        menuChoice = MainMenuChoices.NewMulti;
                        break;
                    case NewMulti:
                        newMultiItem.setItemOff();
                        quitItem.setItemOn();
                        menuChoice = MainMenuChoices.Quit;
                        break;
                    case Quit:
                        quitItem.setItemOff();
                        newSingleItem.setItemOn();
                        menuChoice = MainMenuChoices.NewSingle;
                        break;
                    default:
                        break;
                }
                break;
            case enter:
                switch(menuChoice){
                    case NewSingle:
                        Project2.settings.setHosting(false);
                        Project2.settings.setJoining(false);
                        Project2.settings.setserverIP("localhost");
//                        stateBasedGame.enterState(Project2.CONGRATULATIONS);
                        stateBasedGame.enterState(Project2.HEROSELECTSTATE);
                        break;
                    case NewMulti:
                        stateBasedGame.enterState(Project2.NEWMULTIMENUSTATE);
                        break;
                    case Quit:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
