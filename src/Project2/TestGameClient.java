package Project2;

import Networking.Client;
import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import static Project2.InputManager.InputCommands;
import static Project2.InputManager.InputCommands.*;

public class TestGameClient extends BasicGameState{
    private int stateId;
    private int n;
    private BasicBeing being1;
    private InputCommands inputCommand;
    private static Client client;

    public TestGameClient(int state_id) {
        this.stateId = state_id;
    }

    private final String WALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    private final String ATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";

    @Override
    public int getID() {
        return this.stateId;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        ResourceManager.loadImage(WALKINGSHEETRSC);
        ResourceManager.loadImage(ATTACKINGSHEETRSC);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        String serverName = "localhost";
        int port = 1234;

        System.out.println("Connecting to " + serverName + " on port " + port);

        client = new Client(serverName, port);
        client.connect();
//        being1 = new BasicBeing(new Vector(1260,680), ResourceManager.getSpriteSheet(WALKINGSHEETRSC,32,32),
                ResourceManager.getSpriteSheet(ATTACKINGSHEETRSC,32,32);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString("Test Game Client " + n, 640,360);
        being1.RenderBeing(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        inputCommand = InputManager.ProcessInput(input, stateId);

        ProcessInputCommand(inputCommand);
    }

    private void ProcessInputCommand(InputCommands inputCommand) {
        // other things to do in playstate for input various things will need this input?
        //  pass the inputCommand to the Being and it will then react to its command.
        //  they will check their own next move for collision and will set the next move in
        //  preparation for their update call.
//        being1.GenerateNextMove(inputCommand);
        //  update the beings position and health inside of
        //  private methods.
//        being1.UpdateBeing();
    }

}
