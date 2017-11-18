package Project2;

import org.newdawn.slick.Input;

import static Project2.InputManager.InputCommands.*;

/**
 * Utility class with the main function of receiving input
 * and returning a InputCommands enumeration. This enumeration
 * definition is inherited by other classes and thus the translation
 * of Slick2d <code>Input</code> is made.
 */
public class InputManager {

    public enum InputCommands {up, down, left, right, enter, idle, attack, back, hit,
                                ulDiag, dlDiag, urDiag, drDiag, hitLt, hitRt, death}

    private static boolean buttonBeenPressed;

    public static InputCommands ProcessInput(Input input, int stateId) {
//      if the stateId is equal to any of the menu states then ProcessMenuInput
        if (stateId == Project2.MAINMENUSTATE || stateId == Project2.OPTIONMENUSTATE
                || stateId == Project2.NEWSINGLEMENUSTATE|| stateId == Project2.NEWMULTIMENUSTATE) {
            return ProcessMenuInput(input);
        }
//      if currently in the game state
        else if(stateId == Project2.GAMEPLAYSTATE || stateId == Project2.BASICBEINGTESTSTATE){
            return ProcessGamePlayInput(input);
        }
        else
            return InputCommands.idle;
    }

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

    private static InputCommands ProcessMenuInput(Input input){
        // if input key is down
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            return InputCommands.down;
        } else if (input.isKeyPressed(Input.KEY_UP)) {
            return up;
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
