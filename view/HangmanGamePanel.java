package view;

import controller.HangmanKeyListener;
import model.HangmanGame;

import javax.swing.*;
import java.awt.*;

public class HangmanGamePanel {
    // game states enum
    public enum GameState{
        READY, PLAYING, GAMEOVER, WIN
    }

    // window java widgets
    private final JFrame window;
    private JTextField gameKeyField;
    private JTextField guessField;
    private HangmanCanvas canvas;
    private JButton[] letterButtons;
    private JButton newButton;

    // game model
    private HangmanGame hangman;

    // game state
    private GameState gameState = GameState.READY;

    public HangmanGamePanel(JFrame window) {
        this.window = window;
    }

    // public method fo reseting current game state to initial playing state
    public void reset() {
        // set state to PLAYING
        gameState = GameState.PLAYING;
        // enabling letter buttons
        for (JButton letterButton : letterButtons) {
            letterButton.setEnabled(true);
        }
        // reseting model object
        hangman.reset();

        // updating text fields
        gameKeyField.setText(hangman.getKey());
        guessField.setText(hangman.getUserGuess());

        // updating canvas's health field
        canvas.setHealth(hangman.getHealth());

        // redrawing canvas
        canvas.repaint();
    }

    // public init method for configuring game components
    public void init() {
        // creating new model object
        hangman = new HangmanGame();

        Container cp = window.getContentPane();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,2));

        // adding textfields to NORTH
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 16);
        gameKeyField = new JTextField();
        gameKeyField.setEditable(false);
        gameKeyField.setBackground(Color.white);
        gameKeyField.setForeground(Color.red);
        gameKeyField.setFont(font);
        northPanel.add(gameKeyField);

        guessField = new JTextField();
        guessField.setEditable(false);
        guessField.setBackground(Color.white);
        guessField.setFont(font);
        northPanel.add(guessField);
        cp.add(BorderLayout.NORTH, northPanel);

        // adding canvas to CENTER
        canvas = new HangmanCanvas(this);
        cp.add(BorderLayout.CENTER, canvas);

        // adding buttons to SOUTH
        JPanel southPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(4,7);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        southPanel.setLayout(gridLayout);

        // creating key listener object
        HangmanKeyListener keyListener = new HangmanKeyListener(this, hangman);

        letterButtons = new JButton[26];
        // iterating over all letters from 'a' to 'z'
        for(char c = 'a'; c <= 'z'; c++){
            letterButtons[c - 'a'] = new JButton(Character.valueOf(c).toString());
            southPanel.add(letterButtons[c - 'a']);
            letterButtons[c - 'a'].addActionListener(keyListener);
            letterButtons[c - 'a'].setEnabled(false);
        }

        // creating 'New' button
        newButton = new JButton("New");
        southPanel.add(newButton);
        newButton.addActionListener(keyListener);

        cp.add(BorderLayout.SOUTH, southPanel);

        // repainting canvas
        canvas.repaint();
    }

    // getter method for gameKey textfield
    public JTextField getGameKeyField() {
        return gameKeyField;
    }

    // getter method for guess textfield
    public JTextField getGuessField() {
        return guessField;
    }

    // getter method for new button
    public JButton getNewButton() {
        return newButton;
    }

    // getter method for canvas field
    public HangmanCanvas getCanvas() {
        return canvas;
    }

    // getter method letter buttons array
    public JButton[] getLetterButtons() {
        return letterButtons;
    }

    // getter method for game state
    public GameState getGameState() {
        return gameState;
    }

    // setter method for game state
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
