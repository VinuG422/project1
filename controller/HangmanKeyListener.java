package controller;

import model.HangmanGame;
import view.HangmanGamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Key listener class for handling button pushing events
public class HangmanKeyListener implements ActionListener {
    private final HangmanGamePanel panel;
    private final HangmanGame hangman;

    // Constructor, accepting hangman game panel and model object
    public HangmanKeyListener(HangmanGamePanel panel, HangmanGame hangman) {
        this.panel = panel;
        this.hangman = hangman;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // getting button pushed
        JButton button = (JButton) e.getSource();
        if (button == panel.getNewButton()) {
            // if "new" button was pushed, reseting model
            panel.reset();
        }
        else {
            // letter button was pushed
            char c = button.getText().charAt(0);
            // disabling this button - user can not push the same letter later
            button.setEnabled(false);
            // try opening letter in model object
            hangman.openLetter(c);
            // check, if game was over
            if (hangman.getHealth() == 0 || !hangman.getUserGuess().contains(".")) {
                // if game is over, disabling all letter buttons
                for (JButton letterButton : panel.getLetterButtons()) {
                    letterButton.setEnabled(false);
                }
                // if health == 0, setting state to gameover
                if (hangman.getHealth() == 0) {
                    panel.setGameState(HangmanGamePanel.GameState.GAMEOVER);
                }
                // if no '.' are in user guess, key is found, setting state to win
                else {
                    panel.setGameState(HangmanGamePanel.GameState.WIN);
                }
            }
            // updating textfields
            panel.getGameKeyField().setText(hangman.getKey());
            panel.getGuessField().setText(hangman.getUserGuess());
            // updating health value
            panel.getCanvas().setHealth(hangman.getHealth());
            // repainting canvas
            panel.getCanvas().repaint();
        }
    }
}
