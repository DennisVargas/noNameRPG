package Project2;

import Networking.Server;
import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

import static Project2.InputManager.InputCommands;
import static Project2.InputManager.InputCommands.*;

public class TestGameServer extends BasicGameState{
    private int stateId;
    private int n;
    private BasicBeing being1;
    private InputCommands inputCommand;
    private static Server server;
    private ArrayList<BasicBeing> Players;
    private static int PlayerCount = 2;

    public TestGameServer(int state_id) {
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
        Players = new ArrayList<BasicBeing>(PlayerCount);
        ResourceManager.loadImage(WALKINGSHEETRSC);
        ResourceManager.loadImage(ATTACKINGSHEETRSC);
        System.out.println("Initializing Networking...");
        int port = 1234;
        server = new Server(port);
        server.start();
        addPlayer("ipaddress", 1);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
    }

    public void addPlayer(String playerID, int ClassID) {
        // TODO: Add playerID and ClassID to Basic Being constructor or player constructor, whatever gets used here
//        being1 = new BasicBeing(new Vector(1260,680), ResourceManager.getSpriteSheet(WALKINGSHEETRSC,32,32),
                ResourceManager.getSpriteSheet(ATTACKINGSHEETRSC,32,32);
        Players.add(being1);
    }

    /** Game Server will eventually not have a render loop, leaving it in for testing */
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString("Test Game Server " + n, 640,360);
//        being1.RenderBeing(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        inputCommand = InputManager.ProcessInput(input, stateId);

        // TODO: need to hand ProcessInputCommand the player IP
        // ProcessInputCommand will probably be called by the server, not the update loop
        ProcessInputCommand("being1", inputCommand);
    }

    private void ProcessInputCommand(String playerID, InputCommands inputCommand) {
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
