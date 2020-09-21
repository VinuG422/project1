import javax.swing.*;

import view.HangmanGamePanel;

public class Main {
    public static void main(String[] args) {
        // creating JFrame window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocation(400, 0);

        // creating nad initializing hangman game panel
        HangmanGamePanel game = new HangmanGamePanel(window);
        game.init();

        window.pack();
        window.setVisible(true);
    }
}


