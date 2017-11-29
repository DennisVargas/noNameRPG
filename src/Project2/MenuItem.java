package Project2;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

public class MenuItem {
    private Animation selectionHighlight;
    private String itemName = "defaultName";
    Vector position = null;
    MenuItem(Vector position, String notSelectedImage, String selectedImage, String itemName){
        //  set menu item position
        setPosition(position);
        setName(itemName);
        //  set menu item off and on images
        InitAnimation(notSelectedImage, selectedImage);
        ResourceManager.loadImage(notSelectedImage);
        ResourceManager.loadImage(selectedImage);
        //
    }

    private void setPosition(Vector position) {
        this.position = position;
    }

    private void InitAnimation(String notSelectedImage, String selectedImage) {
        this.selectionHighlight = new Animation(false);
        this.selectionHighlight.addFrame(ResourceManager.getImage(notSelectedImage),1);
        this.selectionHighlight.addFrame(ResourceManager.getImage(selectedImage),1);
    }

    public void setName(String name) {
        this.itemName = name;
    }

    public void updateAnimation(){
        selectionHighlight.update(1);
    }

    public void setItemOff(){
        selectionHighlight.setCurrentFrame(0);
    }

    public void setItemOn(){
        selectionHighlight.setCurrentFrame(1);
    }

    public void renderItem(Graphics g){
        selectionHighlight.draw(position.getX(),position.getY());
    }
}
