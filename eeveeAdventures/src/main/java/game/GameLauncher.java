package game;

import javax.swing.*;

public class GameLauncher {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Eevee Adventures");
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.setSize(1200,675);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
