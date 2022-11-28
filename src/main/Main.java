package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Ciel");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();  // causes the window to be sized to fit the preferred size of the child (gamePanel)

        window.setLocationRelativeTo(null); // puts the window at the center of the screen
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
