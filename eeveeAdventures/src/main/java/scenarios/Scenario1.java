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
    private BufferedImage [] backgroundISections;
    private int screenWidth= 1200;
    private int screenHeight=675;
    private int currentSectionIndex=0;


    public Scenario1(){
        int bossXInSection= screenWidth +800;
        this.boss= new Boss(bossXInSection,370,200,7,"src/main/resources/images/Boss/Boss1/left");
        this.completed=false;


        backgroundISections= new BufferedImage[2];
        try{
            backgroundISections[0] = ImageIO.read(new File("src/main/resources/images/scenarios/01/01.png"));
            backgroundISections[1]= ImageIO.read(new File("src/main/resources/images/scenarios/01/02.png"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Failed to load background image for Scenario1");
        }
    }

    @Override
    public void render(Graphics g) {
        if (backgroundISections[currentSectionIndex] != null){

            g.drawImage(backgroundISections[currentSectionIndex],0,0,screenWidth,screenHeight,null);
        } else {
            g.setColor(new Color(135,206,250));
            g.fillRect(0,0,1200,675);
        }


        if (currentSectionIndex==1 && boss.isAlive()){
            int bossRenderX = boss.getX() - currentSectionIndex *screenWidth;
            boss.render(g);
        } else if (!boss.isAlive()) {
            g.setColor(Color.RED);
            g.drawString("Boss defeated! Move to the next scenario", 200,100);
        }

    }

    @Override
    public boolean checkCompletion(MainCharacter character) {
        int charX= character.getX();
        int totalX= charX +(currentSectionIndex*screenWidth);

        if (currentSectionIndex == 1 && totalX >1150 && !boss.isAlive()){
            completed=true;
        }
        return completed;
    }

    public void update(MainCharacter character) {
        if (currentSectionIndex ==1 && boss.isAlive()) {
            boss.update(character);

            if (Math.abs(character.getX() - boss.getX()) < 50) {
                boss.attack(character);
                character.attack(boss);

            }
        }
        updateBackgroundSection(character);

    }

    private void updateBackgroundSection(MainCharacter character){
        int charX= character.getX();
       if (charX >=(currentSectionIndex+1)*screenWidth){
           currentSectionIndex++;
           character.setX(0);
       }

       if (currentSectionIndex >= backgroundISections.length){
           currentSectionIndex=backgroundISections.length-1;
       }


    }
}
