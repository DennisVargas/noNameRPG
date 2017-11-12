package Project2;

import org.newdawn.slick.Input;

public class InputManager {

    public enum InputCommands {up, down, left, right, enter, idle, attack, back}

    private static boolean buttonBeenPressed;

    public static InputCommands ProcessInput(Input input, int stateId) {
//      if the stateId is equal to any of the menu states then ProcessMenuInput
        if (stateId == Project2.MAINMENUSTATE || stateId == Project2.OPTIONMENUSTATE
                || stateId == Project2.NEWSINGLEMENUSTATE|| stateId == Project2.NEWMULTIMENUSTATE) {
            return ProcessMenuInput(input);
        }
//      if currently in the game state
        else if(stateId == Project2.GAMEPLAYSTATE){
            return ProcessGamePlayInput(input);
        }
        else
            return InputCommands.idle;
    }

    private static InputManager.InputCommands ProcessGamePlayInput(Input input) {
        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            return InputCommands.back;
        }
        return InputCommands.idle;
    }

    private static InputCommands ProcessMenuInput(Input input){
        // if input key is down
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            return InputCommands.down;
        } else if (input.isKeyPressed(Input.KEY_UP)) {
            return InputCommands.up;
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
                return InputCommands.up;}

        } else if (input.isControllerDown(0)) {
            if(!buttonBeenPressed){
                buttonBeenPressed = true;
                return InputCommands.down;}
        }else
            buttonBeenPressed = false;
        return InputCommands.idle;
    }
}


