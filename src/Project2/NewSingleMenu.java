package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class NewSingleMenu extends BasicGameState {
    int stateId = Integer.MIN_VALUE;
    private MenuItem backItem;
    private MenuItem startGameItem;
    private static final String backOffRsc = "testAssets/grey_back.png";
    private static final String backOnRsc = "testAssets/white_back.png";
    private static final String startGameOffRsc = "testAssets/start_game_grey.png";
    private static final String startGameOnRsc = "testAssets/start_game_white.png";


    private String inputCommand;

    public int getStateId() {
        return stateId;
    }


    private enum NewSingleMenuChoices {start,back}
    NewSingleMenuChoices menuChoice;

    public NewSingleMenu(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public int getID() {
        return stateId;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        ResourceManager.loadImage(backOffRsc);
        ResourceManager.loadImage(backOnRsc);
        ResourceManager.loadImage(startGameOffRsc);
        ResourceManager.loadImage(startGameOnRsc);

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        backItem = new MenuItem(new Vector(640f,360f), backOffRsc, backOnRsc,"back-button");
        startGameItem = new MenuItem(new Vector(640f,300f), startGameOffRsc, startGameOnRsc,"start-game");
        startGameItem.setItemOn();
        backItem.setItemOff();
        menuChoice = NewSingleMenuChoices.start;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        backItem.renderItem(graphics);
        startGameItem.renderItem(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        inputCommand = InputManager.ProcessInput(input, getStateId());
        ProcessInputCommand(inputCommand, stateBasedGame);
    }

    private void ProcessInputCommand(String inputCommand, StateBasedGame stateBasedGame) {
        switch(inputCommand){
            case "up":
            case "down":
                switch(menuChoice){
                    case start:
                        startGameItem.setItemOff();
                        backItem.setItemOn();
                        menuChoice = NewSingleMenuChoices.back;
                        break;
                    case back:
                        backItem.setItemOff();
                        startGameItem.setItemOn();
                        menuChoice = NewSingleMenuChoices.start;
                        break;
                }
                break;
            case "enter":
                switch(menuChoice){
                    case start:
                        stateBasedGame.enterState(Project2.GAMEPLAYSTATE);
                        break;
                    case back:
                        stateBasedGame.enterState(Project2.MAINMENUSTATE);
                        break;
                }
        }
    }
}
