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

    public String getFeedback(String guess) {
        int correctNumber, correctPosition;
        boolean[] numPresent = new boolean[10];

        //Determine what numbers are in the correct position.
        correctPosition = 0;
        for(int i = 0; i < entryNum; i++) {
            if(code[i] == Character.getNumericValue(guess.charAt(i))) {
                correctPosition++;
            }
        }

        //Determine what numbers are correct but not in the correct position.
        correctNumber = 0;
        for(int i = 0; i < numPresent.length; i++) {
            numPresent[i] = false;
        }

        for(int i = 0; i < entryNum; i++) {
            numPresent[Character.getNumericValue(guess.charAt(i))] = true;
        }

        for(int i = 0; i < entryNum; i++) {
            if(numPresent[code[i]]) {
                correctNumber++;
                numPresent[i] = false;
            }
        }

        correctNumber = correctNumber - correctPosition;

        //Increment the guess counter if the guess was not correct.
        if(correctNumber != entryNum && correctPosition != entryNum) {
            guessCount++;
            if(guessCount == guessNum) {
                return null;
            }
            return correctPosition + ":" + correctNumber;
        }

        return correctPosition + ":" + correctNumber;
    }

    public int getEntryNum() {
        return entryNum;
    }

    public int getGuessNum() {
        return guessNum;
    }
}