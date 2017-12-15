package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HeroSelect extends BasicGameState {
    int stateId = Integer.MIN_VALUE;
    private MenuItem meleeItem;
    private MenuItem rangedItem;
    private MenuItem backItem;
    private static final String meleeOff = "testAssets/melee_1.png";
    private static final String meleeOn = "testAssets/melee_2.png";
    private static final String rangedOff = "testAssets/ranged_1.png";
    private static final String rangedOn = "testAssets/ranged_2.png";
    private static final String backOffRsc = "testAssets/grey_back.png";
    private static final String backOnRsc = "testAssets/white_back.png";

    private InputManager.InputCommands inputCommand;

    public int getStateId() {
        return stateId;
    }

    private enum NewMultiMenuChoices {melee, ranged, back}
    NewMultiMenuChoices menuChoice = NewMultiMenuChoices.melee;
    public HeroSelect(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public int getID() {
        return stateId;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        ResourceManager.loadImage(meleeOff);
        ResourceManager.loadImage(meleeOn);
        ResourceManager.loadImage(rangedOff);
        ResourceManager.loadImage(rangedOn);
        ResourceManager.loadImage(backOffRsc);
        ResourceManager.loadImage(backOnRsc);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        meleeItem = new MenuItem(new Vector(640f, 250f), meleeOff, meleeOn, "melee");
        rangedItem = new MenuItem(new Vector(640f, 300f), rangedOff, rangedOn, "ranged");
        backItem = new MenuItem(new Vector(635f,360f), backOffRsc, backOnRsc,"back-button");
        meleeItem.setItemOn();
        menuChoice = NewMultiMenuChoices.melee;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        meleeItem.renderItem(graphics);
        rangedItem.renderItem(graphics);
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
                    case melee:
                        meleeItem.setItemOff();
                        rangedItem.setItemOff();
                        backItem.setItemOn();
                        menuChoice = NewMultiMenuChoices.back;
                        break;
                    case ranged:
                        meleeItem.setItemOn();
                        rangedItem.setItemOff();
                        backItem.setItemOff();
                        menuChoice = NewMultiMenuChoices.melee;
                        break;
                    case back:
                        meleeItem.setItemOff();
                        rangedItem.setItemOn();
                        backItem.setItemOff();
                        menuChoice = NewMultiMenuChoices.ranged;
                        break;
                }
                break;
            case down:
                switch(menuChoice){
                    case melee:
                        meleeItem.setItemOff();
                        rangedItem.setItemOn();
                        backItem.setItemOff();
                        menuChoice = NewMultiMenuChoices.ranged;

                        break;
                    case ranged:
                        meleeItem.setItemOff();
                        rangedItem.setItemOff();
                        backItem.setItemOn();
                        menuChoice = NewMultiMenuChoices.back;
                        break;
                    case back:
                        meleeItem.setItemOn();
                        rangedItem.setItemOff();
                        backItem.setItemOff();
                        menuChoice = NewMultiMenuChoices.melee;
                        break;
                }
                break;
            case enter:
                switch (menuChoice) {
                    case melee:
                        Project2.settings.setRanged(false);
                        stateBasedGame.enterState(Project2.TESTGAMECLIENT);
                        break;
                    case ranged:
                        Project2.settings.setRanged(true);
                        stateBasedGame.enterState(Project2.TESTGAMECLIENT);
                        break;
                    case back:
                        stateBasedGame.enterState(Project2.MAINMENUSTATE);
                        break;
                }
        }
    }
}
