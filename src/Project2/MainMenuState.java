package Project2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {
    private int stateId;

    private String inputCommand;

    private MenuItem newSingleItem; private MenuItem quitItem;
    private MenuItem newMultiItem; private MenuItem optionItem;

    private enum MenuChoices {NewSingle, NewMulti, Options, Quit}
    MenuChoices menuChoice;
    
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
        System.out.println("Init Main Menu State "
                + gameContainer.getScreenHeight());
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        System.out.println("Entering Main Menu state");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString(String.valueOf(stateId), 640,360);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
