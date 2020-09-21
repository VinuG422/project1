package model;

import java.util.ArrayList;
import java.util.Random;

// Hangman game model class. Stores current user guess state and health level
public class HangmanGame {
    // word pool to choose game key from
    private static final ArrayList<String> wordPool;

    static {
        // initializing word pool
        wordPool = new ArrayList<>();
        // adding words to word pool
        wordPool.add("communication");
        wordPool.add("science");
        wordPool.add("programming");
        wordPool.add("language");
        wordPool.add("difficulty");
        wordPool.add("artificial");
        wordPool.add("intelligence");
        wordPool.add("attempts");
        wordPool.add("screenshot");
        wordPool.add("baseball");
        wordPool.add("windows");
        wordPool.add("learning");
        wordPool.add("electronics");
        wordPool.add("beautiful");
        wordPool.add("internet");
        wordPool.add("database");
        wordPool.add("organization");
        wordPool.add("application");
        wordPool.add("network");
        wordPool.add("friendly");
        wordPool.add("validation");
        wordPool.add("attempts");
        wordPool.add("statistics");
        wordPool.add("physics");
        wordPool.add("chemistry");
        wordPool.add("engineering");
        wordPool.add("school");
        wordPool.add("industry");
        wordPool.add("revolution");
        wordPool.add("progress");
        wordPool.add("characters");
        wordPool.add("heavily");
        wordPool.add("graphics");
    }

    private String key;
    private String userGuess;

    private int health;

    public HangmanGame() {
        // calling model reset method
        reset();
    }

    public void reset() {
        // generating new game key
        this.key = generateKey();
        // adding dots to user guess string
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<key.length(); i++) {
            builder.append('.');
        }
        this.userGuess = builder.toString();
        // setting health to 5
        this.health = 5;
    }

    // getter method for key field
    public String getKey() {
        return key;
    }

    // getter method for userGuess field
    public String getUserGuess() {
        return userGuess;
    }

    // getter method for health field
    public int getHealth() {
        return health;
    }

    // method for generating random word from wordpool
    private static String generateKey() {
        Random random = new Random();
        // returning random element from wordpool
        return wordPool.get(random.nextInt(wordPool.size()));
    }

    // main logic method for trying to open a letter in userGuess
    public void openLetter(char c) {
        // setting initial success variable state to false
        boolean success = false;
        // getting userGuess as char array
        char[] guessChars = userGuess.toCharArray();
        for (int i = 0; i<key.length(); i++) {
            // letter is opened, if letter is in key and it was not opened yet
            if (c == key.charAt(i) && guessChars[i] != c) {
                // updating userGuess char for newly opened letter
                guessChars[i] = c;
                // changing success to true. If at least on letter is opened, it is success
                success = true;
            }
        }
        // updating userGuess field with updated char array
        userGuess = new String(guessChars);
        // refucing health if it is not success
        if (!success) {
            health--;
        }
    }
}
