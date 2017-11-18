package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import static Project2.InputManager.InputCommands;
import static Project2.InputManager.InputCommands.*;

public class TestStateBasicBeing extends BasicGameState{
    private double x, y;
    private int mapX, mapY;
    private int stateId;
    private int n;
    private BasicBeing being1;
    private InputCommands inputCommand;

    public TestStateBasicBeing(int state_id) {
        this.stateId = state_id;
    }

    private final String WALKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";
    private final String ATTACKINGSHEETRSC = "resources/Characters/CrystalBuddy.png";

    public TiledMap mapTest = null;
    public TiledMap map1 = null;
    private final String TESTLEVELRSC = "resources/Levels/testlevel.tmx";
    private final String LEVEL1RSC = "resources/Levels/Level1Remake.tmx";
    private final String TILESHEETRSC = "resources/Levels";

    @Override
    public int getID() {
        return this.stateId;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        ResourceManager.loadImage(WALKINGSHEETRSC);
        ResourceManager.loadImage(ATTACKINGSHEETRSC);
//        mapTest = new TiledMap(TESTLEVELRSC,TILESHEETRSC);
        map1 = new TiledMap(LEVEL1RSC, TILESHEETRSC);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        System.out.println(container.getWidth());
        System.out.println(container.getHeight());
        being1 = new BasicBeing(new Vector(container.getWidth()/2,container.getHeight()/2), ResourceManager.getSpriteSheet(WALKINGSHEETRSC,32,32),
                ResourceManager.getSpriteSheet(ATTACKINGSHEETRSC,32,32));
        mapX = 90;
        mapY = 104;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        map1.render((int)x - 32, (int)y - 32, mapX, mapY, mapX + 45, mapY + 30);
        graphics.drawString("hello Test Basic Being " + n, 640,360);
        being1.RenderBeing(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        if (input.isKeyDown(Input.KEY_W))
            y += i/3.0f;
        if (input.isKeyDown(Input.KEY_S))
            y  -= i/3.0f;
        if (input.isKeyDown(Input.KEY_D))
            x -= i/3.0f;
        if (input.isKeyDown(Input.KEY_A))
            x += i/3.0f;

        if (x < 0) {
            mapX++;
            x = 32;
        }
        if (x > 32) {
            mapX --;
            x = 0;
        }
        if (y < 0) {
            mapY++;
            y = 32;
        }
        if (y > 32) {
            mapY --;
            y = 0;
        }
        inputCommand = InputManager.ProcessInput(input, stateId);
//
        ProcessInputCommand(inputCommand);
    }

    private void ProcessInputCommand(InputCommands inputCommand) {
        // other things to do in playstate for input various things will need this input?
        //  pass the inputCommand to the Being and it will then react to its command.
        //  they will check their own next move for collision and will set the next move in
        //  preparation for their update call.
        being1.GenerateNextMove(inputCommand);
        //  update the beings position and health inside of
        //  private methods.
//        being1.UpdateBeingPosition();
    }
}
