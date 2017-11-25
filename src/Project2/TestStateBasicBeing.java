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
import static Project2.MovementCalc.CalcDirection;
import static Project2.MovementCalc.CalcTranslation;
import static Project2.MovementCalc.CalcWorldPosition;

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
        mapX = 90;
        mapY = 104;
        being1 = new BasicBeing(new Vector(container.getWidth()/2,container.getHeight()/2), new Vector(mapX, mapY), ResourceManager.getSpriteSheet(WALKINGSHEETRSC,32,32),
                ResourceManager.getSpriteSheet(ATTACKINGSHEETRSC,32,32));
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

//        map1.render((int)x - 32, (int)y - 32, mapX, mapY, mapX + 45, mapY + 30);
//        System.out.println("player x: "+x+" player y: "+y);
//        System.out.println("map x: "+mapX+" map y: "+mapY);
        float displaceX, displaceY, worldPosX, worldPosY;
        worldPosX = (int)being1.getWorldPositionX(); worldPosY = (int)being1.getWorldPositionY();
        displaceX = (being1.getWorldPositionX()-worldPosX)*-32; displaceY = (being1.getWorldPositionY()-worldPosY)*-32;

        //  render the map using the client displacement from tile center
        //  and current world position.

        map1.render((int)displaceX, (int)displaceY,
                (int)worldPosX, (int)worldPosY, (int)worldPosX+45, (int)worldPosY+30 );

        graphics.drawString("displaceX: "+displaceX*-1
                +" displaceY:"+displaceY*-1, 200,200);
        graphics.drawString("worldX: "+being1.getWorldPositionX()
                +"      worldY:"+being1.getWorldPositionY(), 200,230);
        graphics.drawString("screenX: "+being1.getScreenPositionX()
                +" screenY:"+being1.getScreenPositionY(), 200,260);
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
        being1.GenerateNextMove(inputCommand);
        //  update the beings position and health inside of
        //  private methods.
        being1.UpdateBeingPosition();
    }
}
