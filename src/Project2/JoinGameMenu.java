package Project2;


import jig.ResourceManager;
import jig.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class JoinGameMenu extends BasicGameState {
    private TextField textfield;
    private Font font;

    private MenuItem backItem;
    private MenuItem startItem;
    private static final String backOffRsc = "testAssets/grey_back.png";
    private static final String backOnRsc = "testAssets/white_back.png";
    private static final String startOffRsc = "testAssets/start_game_grey.png";
    private static final String startOnRsc = "testAssets/start_game_white.png";

    private InputManager.InputCommands inputCommand;

    private enum MenuChoices {text,start,back}
    MenuChoices menuChoice = MenuChoices.text;

    @Override
    public int getID() {
        return Project2.JOINGAMESTATE;
    }


    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        ResourceManager.loadImage(startOffRsc);
        ResourceManager.loadImage(startOnRsc);
        ResourceManager.loadImage(backOffRsc);
        ResourceManager.loadImage(backOnRsc);
        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SANS_SERIF,java.awt.Font.BOLD , 26), false);
        textfield = new TextField(container, font, 510, 250, 260, 40);
//        textfield.setText("IP ADDRESS");
//        textfield.setBorderColor(Color.darkGray);
        textfield.setMaxLength(15);
        textfield.setBackgroundColor(Color.darkGray);
        textfield.setFocus(true);
    }


    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        startItem = new MenuItem(new Vector(500f,350f), startOffRsc, startOnRsc,"start-button");
        startItem.setItemOff();
        backItem = new MenuItem(new Vector(655f,350f), backOffRsc, backOnRsc,"back-button");
        backItem.setItemOff();
        menuChoice = MenuChoices.text;
    }



    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("Enter the server's IP address", 510,230);
        startItem.renderItem(g);
        backItem.renderItem(g);
        textfield.render(container, g);
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
                switch(menuChoice){
                    case start:
                    case back:
                        textfield.setFocus(true);
                        startItem.setItemOff();
                        backItem.setItemOff();
                        menuChoice = MenuChoices.text;
                        break;
                }
                break;
            case down:
                switch(menuChoice){
                    case text:
                        textfield.setFocus(false);
                        startItem.setItemOn();
                        backItem.setItemOff();
                        menuChoice = MenuChoices.start;
                        break;
                }
                break;
            case left:
                switch(menuChoice){
                    case back:
                        startItem.setItemOn();
                        backItem.setItemOff();
                        menuChoice = MenuChoices.start;
                        break;
                }
                break;
            case right:
                switch(menuChoice){
                    case start:
                        startItem.setItemOff();
                        backItem.setItemOn();
                        menuChoice = MenuChoices.back;
                        break;
                }
                break;
            case enter:
                switch(menuChoice){
                    case start:
                        Project2.settings.setserverIP(textfield.getText());
                        stateBasedGame.enterState(Project2.HEROSELECTSTATE);
                        break;
                    case back:
                        stateBasedGame.enterState(Project2.NEWMULTIMENUSTATE);
                        break;
                }

                break;
        }
    }
}
