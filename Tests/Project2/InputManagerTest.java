package Project2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputManagerTest {

    @Test
    void testEnumClassCompare() {
        InputManager.InputDirections dir = InputManager.InputDirections.down;

        InputManager.InputCommands cmd = InputManager.InputCommands.up;

        if(cmd.equals(InputManager.InputCommands.down)
                || cmd.equals(InputManager.InputCommands.up)
                || cmd.equals(InputManager.InputCommands.left)
                || cmd.equals(InputManager.InputCommands.right)
                || cmd.equals(InputManager.InputCommands.dlDiag)
                || cmd.equals(InputManager.InputCommands.drDiag)
                || cmd.equals(InputManager.InputCommands.ulDiag)
                || cmd.equals(InputManager.InputCommands.urDiag))
            System.out.println("They are direction save as direction");
    }
}