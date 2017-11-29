package Project2;

import jig.Vector;

public class Object extends BasicBeing {
    int value;
    Object(Vector worldPosition, String name, int value){
        super(new Vector(-5000, -5000), worldPosition);
        this.value = value;
        this.setName(name);
        this.setPosition(MovementCalc.CalcScreenPosition(
                Project2.getSettings().getPlayer().getWorldPosition(),
                this.getWorldPosition()));
    }
}
