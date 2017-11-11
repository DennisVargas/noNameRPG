package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TestStateMenuItem extends BasicGameState {
    private int stateId;
    private String inputCommand;

    private MenuItem newSingleItem; private MenuItem quitItem;
    private MenuItem newMultiItem; private MenuItem optionItem;

    private enum MenuChoices {NewSingle, NewMulti, Options, Quit}
    MenuChoices menuChoice;
    private static String offImageRsc = "testAssets/new_game3.png";
    private static String onImageRsc = "testAssets/new_game4.png";

    public TestStateMenuItem(int stateId) {
        this.stateId = stateId;
    }


    @Override
    public int getID() {
        return stateId;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        ResourceManager.loadImage(offImageRsc);
        ResourceManager.loadImage(onImageRsc);
        menuChoice = MenuChoices.NewSingle;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        newSingleItem = new MenuItem(new Vector(640f, 100f), offImageRsc, onImageRsc,"new-game-item");
        newSingleItem.setItemOn();
        newMultiItem = new MenuItem(new Vector(640f, 150f), offImageRsc, onImageRsc,"quit-item");
        newMultiItem.setItemOff();
        optionItem = new MenuItem(new Vector(640f, 200f), offImageRsc, onImageRsc,"quit-item");
        optionItem.setItemOff();
        quitItem = new MenuItem(new Vector(640f, 250f), offImageRsc, onImageRsc,"quit-item");
        quitItem.setItemOff();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        newSingleItem.renderItem(graphics);
        newMultiItem.renderItem(graphics);
        optionItem.renderItem(graphics);
        quitItem.renderItem(graphics);
        graphics.drawString(String.valueOf(menuChoice), 599,245);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        inputCommand = ProcessInput(input);
        ProcessInputCommand(inputCommand);

//        if(menuChoice == MenuChoices.NewSingle)
//            newSingleItem.updateAnimation();
    }

    private void ProcessInputCommand(String inputCommand) {
        switch(inputCommand){
            case "up":
                // menuChoice gets set in Input Process this is wrong.
                switch(menuChoice){
                    case NewSingle:
                        newSingleItem.setItemOff();
                        quitItem.setItemOn();
                        menuChoice = MenuChoices.Quit;
                        break;
                    case NewMulti:
                        newSingleItem.setItemOn();
                        newMultiItem.setItemOff();
                        menuChoice = MenuChoices.NewSingle;
                        break;
                    case Options:
                        newMultiItem.setItemOn();
                        optionItem.setItemOff();
                        menuChoice = MenuChoices.NewMulti;
                        break;
                    case Quit:
                        quitItem.setItemOff();
                        optionItem.setItemOn();
                        menuChoice = MenuChoices.Options;
                        break;
                    default:
                        break;
                }
                break;
            case "down":
                switch(menuChoice){
                    case NewSingle:
                        newSingleItem.setItemOff();
                        newMultiItem.setItemOn();
                        menuChoice = MenuChoices.NewMulti;
                        break;
                    case NewMulti:
                        newMultiItem.setItemOff();
                        optionItem.setItemOn();
                        menuChoice = MenuChoices.Options;
                        break;
                    case Options:
                        optionItem.setItemOff();
                        quitItem.setItemOn();
                        menuChoice = MenuChoices.Quit;
                        break;
                    case Quit:
                        quitItem.setItemOff();
                        newSingleItem.setItemOn();
                        menuChoice = MenuChoices.NewSingle;
                        break;
                    default:
                        break;
                }
            case "none":
                break;
        }
    }

    private String ProcessInput(Input input) {
        // if input key is down
        if(input.isKeyPressed(Input.KEY_DOWN)){
            return "down";
        } else if(input.isKeyPressed(Input.KEY_UP)){
            return "up";
        } else if(input.isKeyPressed(Input.KEY_ENTER)){
            return "enter";
        }
        return "none";
    }
}
