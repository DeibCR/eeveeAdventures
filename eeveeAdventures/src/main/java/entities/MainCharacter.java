package entities;

import utils.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainCharacter extends Character {
    private BufferedImage[] walkRightSprites;
    private BufferedImage[] walkLeftSprites;
    private int currentFrame=0;
    private boolean facingRight= true;
    private  boolean moving= false;

    public MainCharacter(int x, int y, int health, int speed) {
        super(x, y, health, speed);
        walkRightSprites = SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/right");
        walkLeftSprites= SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/left");
    }


    @Override
    public void render(Graphics g) {
        BufferedImage currentSprite = facingRight ? walkRightSprites[currentFrame]: walkLeftSprites[currentFrame];
        g.drawImage(currentSprite,x,y,null);

    }

    public void moveRight(){
        facingRight=true;
        moving=true;
        move(1,0);
        updateAnimation();
    }

    public void moveLeft(){
        facingRight=false;
        moving=true;
        move(-1,0);
        updateAnimation();
    }

    public void stopMoving(){
        moving =false;
        currentFrame=0;
    }

    public void updateAnimation(){
        if (moving){
            currentFrame= (currentFrame+1) %walkRightSprites.length;
        }
    }

    @Override
    public void attack(Character target) {
        target.takeDamage(10);

    }

    @Override
    public void attack() {

    }
}
