package engine;

import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Board {
    private final static int ENTRY_NUM = 4;
    private final static int GUESS_NUM = 12;

    private int entryNum, guessNum, guessCount;
    private int[] code;
    private Random numGen;

    public Board() {
        entryNum = ENTRY_NUM;
        guessNum = GUESS_NUM;
        guessCount = 0;
        numGen = new Random();
        code = new int[entryNum];

        //Create code.
        for(int i = 0; i < entryNum; i++) {
            code[i] = numGen.nextInt(10);
        }
    }

    public Board(int entryNum, int guessNum) {
        this.entryNum = entryNum;
        this.guessNum = guessNum;
        code = new int[entryNum];

        //Create code.
        for(int i = 0; i < entryNum; i++) {
            code[i] = numGen.nextInt(10);
        }
    }

    //For testing purposes
    Board(int[] setCode) {
        entryNum = ENTRY_NUM;
        guessNum = GUESS_NUM;
        code = setCode;
    }

    public boolean checkGuess(String guess) {
        int hold;

        for(int i = 0; i < entryNum; i++) {
            hold = Character.getNumericValue(guess.charAt(i));
            if(hold != code[i]) {
                return false;
            }
        }

        return true;
    }

    public boolean validateGuess(String guess) {
        Pattern p = Pattern.compile("^([0-9]{4})$");
        Matcher m = p.matcher(guess);

        if(m.matches()) {
            return true;
        }

        return false;
    }

    public String getFeedback(String guessString) {
        int correctNumber, correctPosition;
        int[] guess = new int[guessString.length()];
        boolean[] checkedPositions = new boolean[entryNum];

        for(int i = 0; i < guess.length; i++) {
            guess[i] = Integer.parseInt(guessString.substring(i,i+1));
        }

        //Determine what numbers are in the correct position.
        correctPosition = 0;
        for(int i = 0; i < entryNum; i++) {
            if(code[i] == guess[i]) {
                correctPosition++;
                checkedPositions[i] = true;
            }
        }

        //Determine what numbers are correct but not in the correct position.
        correctNumber = 0;
        boolean[] correctValues = new boolean[10];

        for(int i = 0; i < entryNum; i++) {
            for(int j = 0; j < entryNum; j++) {
                if(guess[i] == code[j] && !checkedPositions[i] && !checkedPositions[j]) {
                    correctValues[guess[i]]= true;
                       break;
                }
            }
        }

        for(boolean i : correctValues) {
            if(i) { correctNumber++; }
        }

        //Increment the guess counter and check if there are any guesses left.
        guessCount++;
        if(guessCount >= guessNum) {
            return correctPosition + ":" + correctNumber + ":!";
        }

        return correctPosition + ":" + correctNumber;
    }

    public int getEntryNum() {
        return entryNum;
    }

    public int getGuessNum() {
        return guessNum;
    }

    public int getGuessCount() {
        return guessCount;
    }

    int[] getCode() {
        return code;
    }
}