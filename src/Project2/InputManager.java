package Project2;

import org.newdawn.slick.Input;

public class InputManager {

    public static String ProcessInput(Input input, int stateId) {
        if (stateId == Project2.MAINMENUSTATE || stateId == Project2.OPTIONMENUSTATE
                || stateId == Project2.NEWSINGLEMENUSTATE|| stateId == Project2.NEWMULTIMENUSTATE) {
            // if input key is down
            if (input.isKeyPressed(Input.KEY_DOWN)) {
                return "down";
            } else if (input.isKeyPressed(Input.KEY_UP)) {
                return "up";
            } else if (input.isKeyPressed(Input.KEY_ENTER)) {
                return "enter";
            }
            return "none";
        }else
            return "none";
    }
}
