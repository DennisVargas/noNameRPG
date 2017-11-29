package Project2;


import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class JoinGameMenu extends BasicGameState {
    private MenuItem backItem;
    private static final String backOffRsc = "testAssets/grey_back.png";
    private static final String backOnRsc = "testAssets/white_back.png";

    private InputManager.InputCommands inputCommand;

    private enum MenuChoices {back}
    MenuChoices menuChoice = MenuChoices.back;

    @Override
    public int getID() {
        return Project2.JOINGAMESTATE;
    }


    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        ResourceManager.loadImage(backOffRsc);
        ResourceManager.loadImage(backOnRsc);
    }


    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        backItem = new MenuItem(new Vector(640f,360f), backOffRsc, backOnRsc,"back-button");
        backItem.setItemOn();
        menuChoice = MenuChoices.back;
    }



    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        backItem.renderItem(graphics);
    }



    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        inputCommand = InputManager.ProcessInput(input, Project2.JOINGAMESTATE);
        ProcessInputCommand(inputCommand, stateBasedGame);
    }


    private void ProcessInputCommand(InputManager.InputCommands inputCommand, StateBasedGame stateBasedGame) {
        switch (inputCommand) {
            case up:
            case down:
            case enter:
                stateBasedGame.enterState(Project2.NEWMULTIMENUSTATE);
                break;
        }
    }
}
