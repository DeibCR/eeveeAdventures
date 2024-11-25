package entities;

import utils.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainCharacter extends Character {
    private BufferedImage[] walkRightSprites;
    private BufferedImage[] walkLeftSprites;
    private BufferedImage[] attackRightSprites;
    private BufferedImage[] attackLeftSprites;
    private int currentFrame=0;
    private boolean facingRight= true;
    private  boolean moving= false;
    private boolean attacking= false;
    private int attackFrame=0;
    private int attackCooldown=0;

    public MainCharacter(int x, int y, int health, int speed) {
        super(x, y, health, speed);
        walkRightSprites = SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/right");
        walkLeftSprites= SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/left");
        attackRightSprites= SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/attack/right");
        attackLeftSprites= SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/attack/left");
    }


    @Override
    public void render(Graphics g) {


        BufferedImage currentSprite;

        if (attacking){
            currentSprite=facingRight ? attackRightSprites[attackFrame] : attackLeftSprites[attackFrame];
        }else{
            currentSprite = facingRight ? walkRightSprites[currentFrame]: walkLeftSprites[currentFrame];
        }
        g.drawImage(currentSprite,x,y,null);

    }

    public void moveRight(){
        if (!attacking) {
            facingRight = true;
            moving = true;
            move(1, 0);
            updateAnimation();
        }
    }

    public void moveLeft(){
        if (!attacking) {
            facingRight = false;
            moving = true;
            move(-1, 0);
            updateAnimation();
        }
    }

    public void stopMoving(){
        if (!attacking) {
            moving = false;
            currentFrame = 0;
        }
    }

    public void startAttack(){
        if (!attacking && attackCooldown ==0){
            attacking=true;
            attackFrame=0;
        }
    }


    public void updateAnimation(){
        if (moving){
            currentFrame= (currentFrame+1) %walkRightSprites.length;
        }
    }

    public void update(){
        if (attacking){
            attackFrame++;
            if (attackFrame >= attackRightSprites.length){
                attacking=false;
                attackCooldown=30;
            }
        } else if (attackCooldown >0) {
            attackCooldown--;
        }
    }

    @Override
    public void attack(Character target) {
        if (attacking && attackFrame ==1) {
            target.takeDamage(10);
        }

    }

}
