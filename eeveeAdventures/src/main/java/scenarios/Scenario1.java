package scenarios;

import entities.Boss;
import entities.MainCharacter;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Scenario1 extends Scenario {
    private Boss boss;
    private boolean completed;
    private BufferedImage backgroundImage;

    public Scenario1(){
        this.boss= new Boss(600,300,200,2,"src/main/resources/images/Boss/Boss1/1.png");
        this.completed=false;

        try{
            backgroundImage = ImageIO.read(new File("src/main/resources/images/scenarios/scenario01.png"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Failed to load background image for Scenario1");
        }
    }

    @Override
    public void render(Graphics g) {
        if (backgroundImage != null){
            g.drawImage(backgroundImage,0,0,null);
        } else {
            g.setColor(new Color(135,206,250));
            g.fillRect(0,0,1200,675);
        }

        if (boss.isAlive()){
            boss.render(g);
        } else {
            g.setColor(Color.RED);
            g.drawString("Boss defeated! Move to the next scenario", 200,100);
        }

    }

    @Override
    public boolean checkCompletion(MainCharacter character) {
        if (!boss.isAlive() && character.getX() >750){
            completed=true;
        }
        return completed;
    }

    public void update(MainCharacter character){
        if (boss.isAlive()){
            boss.update();

            if (Math.abs(character.getX() - boss.getX()) <50){
                boss.attack(character);
                character.attack(boss);

                }
            }
        }

}
