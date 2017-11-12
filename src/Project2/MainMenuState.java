package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {
    private int stateId;

    private InputManager.InputCommands inputCommand;

//  MenuItem objects are a vector position and a animation
    private MenuItem newSingleItem; private MenuItem quitItem;
    private MenuItem newMultiItem; private MenuItem optionItem;

    public int getStateId() {
        return stateId;
    }

    /**
     * MainMenuChoices in main menu
     */
    private enum MainMenuChoices {NewSingle, NewMulti, Options, Quit}
    MainMenuChoices menuChoice;

//  image resource file paths that will be passed to MenuItem() constructor
    private static String singlePlayerOffImageRsc = "testAssets/new_game3.png";
    private static String singlePlayerOnImageRsc = "testAssets/new_game4.png";
    private static String multiPlayerOffImageRsc = "testAssets/new_game3.png";
    private static String multiPlayerOnImageRsc = "testAssets/new_game4.png";
    private static String optionsOffImageRsc = "testAssets/options3.png";
    private static String optionsOnImageRsc = "testAssets/options4.png";
    private static String quitOffImageRsc = "testAssets/quit3.png";
    private static String quitOnImageRsc = "testAssets/quit4.png";

    public MainMenuState(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public int getID() {
        return stateId;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        ResourceManager.loadImage(singlePlayerOffImageRsc);
        ResourceManager.loadImage(singlePlayerOnImageRsc);
        ResourceManager.loadImage(multiPlayerOffImageRsc);
        ResourceManager.loadImage(multiPlayerOnImageRsc);
        ResourceManager.loadImage(optionsOffImageRsc);
        ResourceManager.loadImage(optionsOnImageRsc);
        ResourceManager.loadImage(quitOffImageRsc);
        ResourceManager.loadImage(quitOnImageRsc);
        menuChoice = MainMenuChoices.NewSingle;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        newSingleItem = new MenuItem(new Vector(640f, 100f), singlePlayerOffImageRsc, singlePlayerOnImageRsc,"new-game-item");
        newSingleItem.setItemOn();
        newMultiItem = new MenuItem(new Vector(640f, 150f), multiPlayerOffImageRsc, multiPlayerOnImageRsc,"quit-item");
        newMultiItem.setItemOff();
        optionItem = new MenuItem(new Vector(640f, 200f), optionsOffImageRsc, optionsOnImageRsc,"quit-item");
        optionItem.setItemOff();
        quitItem = new MenuItem(new Vector(640f, 250f), quitOffImageRsc, quitOnImageRsc,"quit-item");
        quitItem.setItemOff();
        menuChoice = MainMenuChoices.NewSingle;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        newSingleItem.renderItem(graphics);
        newMultiItem.renderItem(graphics);
        optionItem.renderItem(graphics);
        quitItem.renderItem(graphics);
        graphics.drawString(String.valueOf(menuChoice), 200,125);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        inputCommand = InputManager.ProcessInput(input, getStateId());
        ProcessInputCommand(inputCommand, stateBasedGame);
    }

    /**
     * ProcessInputCommand takes a string and evaluates
     * which menuitem to switch on and off based on the
     * currently selected menuChoice. Once the MenuItem is
     * toggled On or Off the menuChoice is iterated to the
     * new choice. stateBasedGame is passed so this function
     * can transition to another state if the command is "enter"
     * @param inputCommand
     * @param stateBasedGame
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
                    case Options:
                        newMultiItem.setItemOn();
                        optionItem.setItemOff();
                        menuChoice = MainMenuChoices.NewMulti;
                        break;
                    case Quit:
                        quitItem.setItemOff();
                        optionItem.setItemOn();
                        menuChoice = MainMenuChoices.Options;
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
                        optionItem.setItemOn();
                        menuChoice = MainMenuChoices.Options;
                        break;
                    case Options:
                        optionItem.setItemOff();
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
                        stateBasedGame.enterState(Project2.NEWSINGLEMENUSTATE);
                        break;
                    case NewMulti:
                        stateBasedGame.enterState(Project2.NEWMULTIMENUSTATE);
                        break;
                    case Options:
                        stateBasedGame.enterState(Project2.OPTIONMENUSTATE);
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
