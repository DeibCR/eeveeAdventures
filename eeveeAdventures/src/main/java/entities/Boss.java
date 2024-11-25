package entities;

import utils.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Boss extends Character {
    private BufferedImage sprite;
    private int attackCooldown=0; // time between attacks
    private int damage = 20; //damage per attack

    public Boss(int x, int y, int health, int speed, String spritePath) {
        super(x, y, health, speed);
        sprite = SpriteLoader.loadSprites("src/main/resources/images/Boss/Boss1")[0];
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite,x,y,null);

    }

    @Override
    public void attack(Character target) {
        if (attackCooldown == 0) {
            target.takeDamage(damage);
            attackCooldown = 100; // Reset cooldown
        }
    }

    @Override
    public void attack() {

    }


    public void update() {
        if (attackCooldown > 0) {
            attackCooldown--;
        }
    }
}
