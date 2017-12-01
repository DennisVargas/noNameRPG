package Project2;

import org.newdawn.slick.Input;

import static Project2.InputManager.InputCommands.*;

/**
 * Receives input and returns an InputCommands enumeration. This enumeration
 * definition is inherited by other classes and thus the translation
 * of Slick2d <code>Input</code> is made.
 */
public class InputManager {

    /**
     * defines the output that will become the movement language for the
     * BasicBeing objects in the game. AI will have to emit the very same
     * InputCommands to drive the BasicBeings.
     */
    public enum InputCommands {up, down, left, right, enter, idle, attack, back, hit,
                                ulDiag, dlDiag, urDiag, drDiag, hitLt, hitRt, death}

//  boolean that stores the value of when a GamePad button
//  has been pressed once so that it will not repeat.
    private static boolean buttonBeenPressed;

    /**
     * Based on the stateID current input key pressed returns a <code>InputCommands</code>.
     * In menu states the controls must only acknowledge one press at a time while inside of
     * the game buttons can be held and movement will be acknowledged. This is the reasoning behind
     * needing the stateID argument in order know the difference between a game play state or
     * a menu state.
     * @param input - Slick2d Input type passed in from some Update method.
     * @param stateId - the stateId sending the input message.
     *                  !!! When a new state requiring Input is added this method must be updated!!!
     * @return based on the stateID returns Menu or GamePlay <code>InputCommands</code>.
     */
    public static InputCommands ProcessInput(Input input, int stateId) {
//      if the stateId is equal to any of the menu states then ProcessMenuInput
        if (stateId == Project2.MAINMENUSTATE || stateId == Project2.OPTIONMENUSTATE
                || stateId == Project2.NEWSINGLEMENUSTATE || stateId == Project2.NEWMULTIMENUSTATE
                || stateId == Project2.JOINGAMESTATE) {
            return ProcessMenuInput(input);
//      if currently in the game state
        } else{
            return ProcessGamePlayInput(input);
        }
    }

    /**
     * Process input in game play state using <code>isKeyPressed()</code>;
     * method allows for keys to be held down and Commands be issued.
     * @param input slick2d Input type passed in from ProcessInput
     * @return InputCommands enumeration
     */
    private static InputCommands ProcessGamePlayInput(Input input) {
        InputCommands curCommand = InputCommands.idle;

        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            return back;
        }else {
//            test the y direction for input
            if (input.isKeyDown(Input.KEY_W)
                    || input.isControllerUp(0)) {
                curCommand = up;
            } else if (input.isKeyDown(Input.KEY_S)
                    || input.isControllerDown(0)) {
                curCommand = down;
            }
//            test the x direction for input
            if (input.isKeyDown(Input.KEY_A)
                    || input.isControllerLeft(0)) {
                if(curCommand == up)
                    curCommand = ulDiag;
                else if(curCommand == down)
                    curCommand = dlDiag;
                else
                    curCommand = left;
            } else if (input.isKeyDown(Input.KEY_D)
                    || input.isControllerRight(0)) {
                if(curCommand == up)
                    curCommand = urDiag;
                else if(curCommand == down)
                    curCommand = drDiag;
                else
                    curCommand = right;
            }
//            test for the attack action which stops movement.
            if (input.isKeyDown(Input.KEY_UP)
                    || input.isButton3Pressed(0)) {
                curCommand = attack;}

            return curCommand;
        }
    }

    /**
     * Process input in menu with allowance for only one
     * button press per command.
     * @param input slick2d Input type
     * @return enumerated <code>InputCommands</code> type
     */
    private static InputCommands ProcessMenuInput(Input input){
        // if input key is down
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            return InputCommands.down;
        } else if (input.isKeyPressed(Input.KEY_UP)) {
            return up;
        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
            return InputCommands.left;
        } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            return InputCommands.right;
        } else if (input.isKeyPressed(Input.KEY_ENTER)) {
            return InputCommands.enter;
        }
        if (input.isButton1Pressed(0)) {
            if(!buttonBeenPressed){
                buttonBeenPressed = true;
                return InputCommands.enter;
            }
        } else if (input.isControllerUp(0)) {
            if(!buttonBeenPressed){
                buttonBeenPressed = true;
                return up;}

        } else if (input.isControllerDown(0)) {
            if(!buttonBeenPressed){
                buttonBeenPressed = true;
                return InputCommands.down;}
        }else
            buttonBeenPressed = false;
        return InputCommands.idle;
    }
}
