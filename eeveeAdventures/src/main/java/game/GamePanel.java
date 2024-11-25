package game;

import entities.MainCharacter;
import scenarios.Scenario;
import scenarios.Scenario1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable,KeyListener {
    private MainCharacter character;
    private Scenario currentScenario;
    private boolean running= true;
    private Thread gameThread;

    public GamePanel(){
        this.character= new MainCharacter(50,370,100,25);
        this.currentScenario= new Scenario1();

        setPreferredSize(new Dimension(1200,675));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);

        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        while (running){
            update();
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    private void update(){
       if (currentScenario instanceof Scenario1){
           ((Scenario1) currentScenario).update(character);
       }
       if (currentScenario.checkCompletion(character)){
           System.out.println("Scenario Completed!");
           running=false;
       }


    }

    @Override
    protected  void paintComponent(Graphics g){
        super.paintComponent(g);

        currentScenario.render(g);

        character.render(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT: character.moveRight();
            break;
            case KeyEvent.VK_LEFT: character.moveLeft();
            break;
            case KeyEvent.VK_SPACE: character.attack();
            break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
                character.stopMoving();
                break;
            case KeyEvent.VK_SPACE:
                character.attack();
                break;
        }

    }
}
