package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Character {
    protected int x,y;
    protected int health;
    protected int speed;
    protected boolean alive=true;
    protected int damageTaken = 0;

    public Character(int x, int y, int health, int speed) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.speed = speed;
    }

    public void move(int dx, int dy){
        this.x += dx*speed;
        this.y += dy*speed;

    }

    public void takeDamage(int damage) {
        if (damage > 0) {
            this.health -= damage;
            this.damageTaken += damage;
            if (health <= 0) {
                alive = false;
                health = 0;
            }
        }
    }

    public boolean isAlive(){
        return alive;
    }

    public abstract void render(Graphics g);

    public abstract void attack(Character target);

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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }


}
