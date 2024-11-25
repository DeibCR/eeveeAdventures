package entities;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Element {
    private ImageIcon image;
    private int x,y;

    public Element(ImageIcon image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g){
        image.paintIcon(null,g,getX(),getY());
    }
}
