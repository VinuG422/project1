package view;

import javax.swing.*;
import java.awt.*;

// Hangman window canvas class
public class HangmanCanvas extends JPanel {
    // canvas size constants
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private final HangmanGamePanel panel;

    // game health level
    private int health;

    public HangmanCanvas(HangmanGamePanel panel) {
        this.panel = panel;
        // setting canvas size
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    // overriden drawing method
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // ready to play

        Graphics2D g2 = (Graphics2D) g;
        // if current state is PLAYING, writing 'Health Level' and drawing health level as squares
        if (panel.getGameState() == HangmanGamePanel.GameState.PLAYING) {
            // setting color to blue
            g2.setColor(Color.blue);
            // setting font
            g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
            // writing string
            g2.drawString("Health Level", 50, 50);
            // drawing blue squares. As much as health level is
            for (int i = 0; i<health; i++) {
                g2.fillRect(50 + i*52, 60, 50, 50);
            }
        }
        else {
            // state is not PLAYING
            // writing "Press new to start" line
            g2.setColor(Color.blue);
            g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
            g2.drawString("Press <New> to Start", 50, 200);

            // if state is GAMEOVER, writing "you lost" line
            if (panel.getGameState() == HangmanGamePanel.GameState.GAMEOVER) {
                g2.setColor(Color.red);
                g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
                g2.drawString("YOU LOST!!!", 50, 100);
            }
            // if state is WIN, writing "you won" line
            else if (panel.getGameState() == HangmanGamePanel.GameState.WIN) {
                g2.setColor(Color.red);
                g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
                g2.drawString("YOU WON!!!", 50, 100);
            }
        }
    }

    // setter method forhealth field
    public void setHealth(int health) {
        this.health = health;
    }
}
