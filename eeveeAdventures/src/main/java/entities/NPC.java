package entities;

import utils.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC {
    private int x,y;
    private int width,height;
    private String[] dialog;
    private boolean isTalking=false;
    private BufferedImage lookLeftSprite;
    private BufferedImage lookRightSprite;
    private boolean facingRight = false;
    private int dialogIndex = 0;


    public NPC(int x, int y, int width, int height, String[] dialog) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dialog = dialog;

       lookLeftSprite = SpriteLoader.loadSprites("src/main/resources/images/npc/01/left")[0];
       lookRightSprite = SpriteLoader.loadSprites("src/main/resources/images/npc/01/right")[0];

    }

    public void render (Graphics g, int playerX){
        facingRight = playerX > x;

        BufferedImage currentSprite = facingRight ? lookRightSprite : lookLeftSprite;

        g.drawImage(currentSprite, x, y, width, height, null);

    }

    public boolean isPlayerClose(int playerX, int playerY){
        int detectionRadius = 100;
        return Math.abs(playerX - x) <detectionRadius && Math.abs(playerY- y) < detectionRadius;
    }

    public String startConversation(){
        isTalking=true;
        dialogIndex=0;
        return dialog[dialogIndex];
    }

    public String continueConversation(){
        if (isTalking) {
            dialogIndex++;
            if (dialogIndex < dialog.length) {
                return dialog[dialogIndex];
            } else {
                stopConversation(); // End conversation when dialog ends
                return "Goodbye!";
            }
        }
        return "";
    }

    public void stopConversation(){
        isTalking=false;
        dialogIndex=0;
    }


    public boolean isTalking() {
        return isTalking;
    }
}
