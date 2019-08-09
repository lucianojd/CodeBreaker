package engine;

public class UnitTests {
    public static void main(String args[]){
        BoardTests();
    }

    static void BoardTests(){
        int[] code = {0, 4, 5, 8};
        String codeString = "0458";
        Board b = new Board(code);
        
        //checkGuess tests.
        if(!b.checkGuess(codeString)) {
            System.out.println("Board.checkGuess 1: FAIL");
        }

        if(b.checkGuess("1234")) {
            System.out.println("Board.checkGuess 2: FAIL");
        }

        //validateGuess tests.
        if(!b.validateGuess(codeString)) {
            System.out.println("Board.validateGuess 1: FAIL");
        }

        if(b.validateGuess("90e21")) {
            System.out.println("Board.validateGuess 2: FAIL");
        }

        if(b.validateGuess("9")) {
            System.out.println("Board.validateGuess 3: FAIL");
        }

        //getFeedback tests.
        if(!(b.getFeedback(codeString).equals("4:0"))) {
            System.out.println("Board.getFeedback 1: FAIL");
        } 

        if(!b.getFeedback("4059").equals("1:2")) {
            System.out.println("Board.getFeedback 2: FAIL");
        }

        if(!b.getFeedback("0445").equals("2:1")) {
            System.out.println("Board.getFeedback 3: FAIL");
        }

        //Check that a ! is received when you run out of guesses.
        for(int i = 0; i < 9; i++) {
            b.getFeedback("0000");
        }

        if(!b.getFeedback("1111").equals("0:0:!")) {
            System.out.println("Board.getFeedback 4: FAIL");
        }

    }
}