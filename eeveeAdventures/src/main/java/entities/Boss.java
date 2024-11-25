package entities;

import utils.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Boss extends Character {
    private BufferedImage[] sprite;
    private int attackCooldown=0; // time between attacks
    private int damage = 20; //damage per attack
    private int animationFrame= 0;
    private int moveRange;
    private int baseX;
    private boolean visible= false;

    public Boss(int x, int y, int health, int speed, String spritePath) {
        super(x, y, health, speed);
        sprite = SpriteLoader.loadSprites("src/main/resources/images/Boss/Boss1/left");
        baseX= x;
        moveRange=300;
    }

    @Override
    public void render(Graphics g) {
        if (sprite !=null && sprite.length>0){
            g.drawImage(sprite[animationFrame],x,y,null);
        }else {
            g.setColor(Color.RED);
            g.fillRect(x,y,50,50);
        }

    }

    @Override
    public void attack(Character target) {
        if (visible) {
            if (attackCooldown == 0) {
                target.takeDamage(damage);
                attackCooldown = 100; // Reset cooldown
            }
        }
    }



    public void update(MainCharacter character) {
        if (attackCooldown > 0) {
            attackCooldown--;
        }

        int disctanceToCharacter= Math.abs(character.getX() - this.x);

        if (disctanceToCharacter < moveRange && disctanceToCharacter > 50){
            if (character.getX() > this.x){
                move(1,0);
            } else if (character.getX()<this.x) {
                move(-1,0);

            }
            updateAnimation();
        }
        if (disctanceToCharacter <=50){
            attack(character);
        }
    }

    public void updateAnimation(){
        animationFrame =(animationFrame+1)% sprite.length;
    }

    public boolean isVisible(){
        return visible;
    }
}
