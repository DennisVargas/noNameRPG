package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class NewMultiMenu extends BasicGameState {
    int stateId = Integer.MIN_VALUE;
    private MenuItem hostGame;
    private MenuItem joinGame;
    private MenuItem backItem;
    private static final String hostGameOff = "testAssets/host_1.png";
    private static final String hostGameOn = "testAssets/host_2.png";
    private static final String joinGameOff = "testAssets/join_1.png";
    private static final String joinGameOn = "testAssets/join_2.png";
    private static final String backOffRsc = "testAssets/grey_back.png";
    private static final String backOnRsc = "testAssets/white_back.png";

    private Image background;

    private InputManager.InputCommands inputCommand;

    public int getStateId() {
        return stateId;
    }

    private enum NewMultiMenuChoices {host, join, back}
    NewMultiMenuChoices menuChoice = NewMultiMenuChoices.host;
    public NewMultiMenu(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public int getID() {
        return stateId;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        ResourceManager.loadImage(hostGameOff);
        ResourceManager.loadImage(hostGameOn);
        ResourceManager.loadImage(joinGameOff);
        ResourceManager.loadImage(joinGameOn);
        ResourceManager.loadImage(backOffRsc);
        ResourceManager.loadImage(backOnRsc);
        background = new Image(Project2.BACKGROUND);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        hostGame = new MenuItem(new Vector(640f, 250f), hostGameOff, hostGameOn, "host-game");
        joinGame = new MenuItem(new Vector(635f, 300f), joinGameOff, joinGameOn, "join-game");
        backItem = new MenuItem(new Vector(655f,360f), backOffRsc, backOnRsc,"back-button");
        hostGame.setItemOn();
        menuChoice = NewMultiMenuChoices.host;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background, 0, 0);
        hostGame.renderItem(graphics);
        joinGame.renderItem(graphics);
        backItem.renderItem(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        inputCommand = InputManager.ProcessInput(input, getStateId());
        ProcessInputCommand(inputCommand, stateBasedGame);
    }

    private void ProcessInputCommand(InputManager.InputCommands inputCommand, StateBasedGame stateBasedGame) {
        switch (inputCommand) {
            case up:
                switch(menuChoice){
                    case host:
                        hostGame.setItemOff();
                        joinGame.setItemOff();
                        backItem.setItemOn();
                        menuChoice = NewMultiMenuChoices.back;
                        break;
                    case join:
                        hostGame.setItemOn();
                        joinGame.setItemOff();
                        backItem.setItemOff();
                        menuChoice = NewMultiMenuChoices.host;
                        break;
                    case back:
                        hostGame.setItemOff();
                        joinGame.setItemOn();
                        backItem.setItemOff();
                        menuChoice = NewMultiMenuChoices.join;
                        break;
                }
                break;
            case down:
                switch(menuChoice){
                    case host:
                        hostGame.setItemOff();
                        joinGame.setItemOn();
                        backItem.setItemOff();
                        menuChoice = NewMultiMenuChoices.join;

                        break;
                    case join:
                        hostGame.setItemOff();
                        joinGame.setItemOff();
                        backItem.setItemOn();
                        menuChoice = NewMultiMenuChoices.back;
                        break;
                    case back:
                        hostGame.setItemOn();
                        joinGame.setItemOff();
                        backItem.setItemOff();
                        menuChoice = NewMultiMenuChoices.host;
                        break;
                }
                break;
            case enter:
                switch (menuChoice) {
                    case host:
                        Project2.settings.setHosting(true);
                        Project2.settings.setJoining(false);
                        Project2.settings.setserverIP("localhost");
                        stateBasedGame.enterState(Project2.HEROSELECTSTATE);
                        break;
                    case join:
                        Project2.settings.setHosting(false);
                        Project2.settings.setJoining(true);
                        stateBasedGame.enterState(Project2.JOINGAMESTATE);
                        break;
                    case back:
                        stateBasedGame.enterState(Project2.MAINMENUSTATE);
                        break;
                }
        }
    }
}
