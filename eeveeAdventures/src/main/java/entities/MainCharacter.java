package entities;

import utils.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainCharacter extends Character {
    private BufferedImage[] walkRightSprites;
    private BufferedImage[] walkLeftSprites;
    private BufferedImage[] walkUpSprites;
    private BufferedImage[] walkDownSprites;
    private BufferedImage[] attackRightSprites;
    private BufferedImage[] attackLeftSprites;
    private BufferedImage[] jumpRightSprites;
    private BufferedImage[] jumpLeftSprites;


    private boolean movingUp=false;
    private boolean movingDown= false;

    private int currentFrame=0;
    private boolean facingRight= true;
    private  boolean moving= false;
    private boolean attacking= false;
    private boolean jumping= false;

    private int attackFrame=0;
    private int attackCooldown=0;
    private int jumpFrame= 0;
    private int jumpHeight=50;
    private int jumpSpeed= 8;
    private int jumpPeak;
    private int groundY;
    private int originalX;

    public MainCharacter(int x, int y, int health, int speed) {
        super(x, y, health, speed);
        walkRightSprites = SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/right");
        walkLeftSprites= SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/left");
        walkUpSprites= SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/up");
        walkDownSprites=SpriteLoader.loadSprites("src/main/resources/images/spritePrincipal/moves/down");
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
            if (movingUp){
                currentSprite= walkUpSprites[currentFrame];
            } else if (movingDown) {
                currentSprite=walkDownSprites[currentFrame];
            }else {
                currentSprite = facingRight ? walkRightSprites[currentFrame] : walkLeftSprites[currentFrame];
            }
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

    public void moveUp(){
        if (!attacking && !jumping) {
            if (y > 294) {
                y -= 5;
                movingUp = true;
                movingDown=false;
                updateAnimation();
            }
        }
    }

    public void moveDown() {
        if (!attacking && !jumping) {
            if (y < 581) {
                y += 5;
                movingDown = true;
                movingUp=false;
                updateAnimation();
            }
        }
    }

    public void stopMoving(){
        if (!attacking && !jumping) {
            moving = false;
            movingUp=false;
            movingDown=false;
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
            //System.out.println("Character Y Position: " + y);
        }
    }


    public void updateAnimation(){
        if (movingUp){
            currentFrame = (currentFrame+1)% walkUpSprites.length;
        } else if (movingDown) {
            currentFrame= (currentFrame+1) % walkDownSprites.length;

        } else if (facingRight) {
            currentFrame= (currentFrame+1) % walkRightSprites.length;
        }else {
            currentFrame = (currentFrame+1) % walkLeftSprites.length;
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
                jumpSpeed -=1;
                jumpFrame++;
                System.out.println("Character Y Position: " + y);
            } else if (y< groundY) {
                y +=jumpSpeed;
                jumpSpeed +=1;

            }else {
                y =groundY;
                jumping=false;
                jumpSpeed=10;
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
