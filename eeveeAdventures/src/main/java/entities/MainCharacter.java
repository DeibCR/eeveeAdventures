package entities;

import utils.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainCharacter extends Character {
    private BufferedImage[] walkRightSprites;
    private BufferedImage[] walkLeftSprites;
    private BufferedImage[] attackRightSprites;
    private BufferedImage[] attackLeftSprites;
    private BufferedImage[] jumpRightSprites;
    private BufferedImage[] jumpLeftSprites;

    private int currentFrame=0;
    private boolean facingRight= true;
    private  boolean moving= false;
    private boolean attacking= false;
    private boolean jumping= false;

    private int attackFrame=0;
    private int attackCooldown=0;
    private int jumpFrame= 0;
    private int jumpHeight=200;
    private int jumpSpeed= 4;
    private int jumpPeak;
    private int groundY;
    private int originalX;

    public MainCharacter(int x, int y, int health, int speed) {
        super(x, y, health, speed);
        walkRightSprites = SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/right");
        walkLeftSprites= SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/left");
        attackRightSprites= SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/attack/right");
        attackLeftSprites= SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/attack/left");
        jumpLeftSprites=SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/leftJump");
        jumpRightSprites= SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/rightJump");
        groundY=y;
    }


    @Override
    public void render(Graphics g) {


        BufferedImage currentSprite;

        if (attacking){
            currentSprite=facingRight ? attackRightSprites[attackFrame] : attackLeftSprites[attackFrame];

        } else if (jumping) {
            currentSprite=facingRight ? jumpRightSprites[jumpFrame % jumpRightSprites.length] : jumpLeftSprites[jumpFrame% jumpLeftSprites.length];

        } else{
            currentSprite = facingRight ? walkRightSprites[currentFrame]: walkLeftSprites[currentFrame];
        }
        g.drawImage(currentSprite,x,y,null);

    }

    public void moveRight(){
        if (!attacking && !jumping) {
            facingRight = true;
            moving = true;
            move(1, 0);
            updateAnimation();
        }
    }

    public void moveLeft(){
        if (!attacking && !jumping) {
            facingRight = false;
            moving = true;
            move(-1, 0);
            updateAnimation();
        }
    }

    public void stopMoving(){
        if (!attacking && !jumping) {
            moving = false;
            currentFrame = 0;
        }
    }

    public void startAttack(){
        if (!attacking && attackCooldown ==0){
            attacking=true;
            attackFrame=0;
            originalX= x;
        }
    }

    public void startJump(){
        if (!jumping && !attacking){
            jumping=true;
            jumpFrame=0;
            jumpPeak= groundY-jumpHeight;
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

            if (attackFrame==1){
                x +=facingRight ? 50:50;
            }


            if (attackFrame >= attackRightSprites.length){
                x=originalX;
                attacking=false;
                attackCooldown=30;
            }
        } else if (attackCooldown >0) {
            attackCooldown--;
        }

        if (jumping){
            if (y > jumpPeak && jumpFrame < jumpRightSprites.length){
                y -=jumpSpeed;
                jumpFrame++;
            } else if (y< groundY) {
                y +=jumpSpeed;

            }else {
                y =groundY;
                jumping=false;
            }
        }
    }

    @Override
    public void attack(Character target) {
        if (attacking && attackFrame ==1) {
            target.takeDamage(50);
        }

    }

}
