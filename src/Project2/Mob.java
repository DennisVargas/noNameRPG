package Project2;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.SpriteSheet;

public class Mob extends BasicBeing {
    private int mobType;
    private static final SpriteSheet mob1Walking = ResourceManager.getSpriteSheet(Project2.MOB1WALKINGSHEETRSC,32,32);
    private static final SpriteSheet mob1Attacking = ResourceManager.getSpriteSheet(Project2.MOB1ATTACKINGSHEETRSC,32,32);
    private static final SpriteSheet mob2Walking = ResourceManager.getSpriteSheet(Project2.MOB2WALKINGSHEETRSC,32,32);
    private static final SpriteSheet mob2Attacking = ResourceManager.getSpriteSheet(Project2.MOB2ATTACKINGSHEETRSC,32,32);
    private static final SpriteSheet mob3Walking = ResourceManager.getSpriteSheet(Project2.MOB3WALKINGSHEETRSC,32,32);
    private static final SpriteSheet mob3Attacking = ResourceManager.getSpriteSheet(Project2.MOB3ATTACKINGSHEETRSC,32,32);
    private static final SpriteSheet mob4Walking = ResourceManager.getSpriteSheet(Project2.MOB4WALKINGSHEETRSC,32,32);
    private static final SpriteSheet mob4Attacking = ResourceManager.getSpriteSheet(Project2.MOB4ATTACKINGSHEETRSC,32,32);

    public Mob(Vector worldPosition, int mobType, String name) {
        super(worldPosition, worldPosition, mob1Walking, mob1Attacking);
        this.setName(name);
        setMobType(mobType);
    }

    public int getMobType(){
        return this.mobType;
    }

    private void setMobType(int mobType) {
        switch(mobType){
            case 1:
                InitAnimations(mob2Walking, mob2Attacking);
                this.setRanged(false);
                break;
            case 2:
                InitAnimations(mob2Walking, mob2Attacking);
                this.setRanged(false);
                break;
            case 3:
                InitAnimations(mob3Walking, mob3Attacking);
                this.setRanged(true);
                break;
            case 4:
                InitAnimations(mob4Walking, mob4Attacking);
                this.setRanged(true);
                break;
        }
        this.mobType = mobType;
    }


}
