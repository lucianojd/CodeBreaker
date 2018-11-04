import java.util.Random;
import java.util.Scanner;

public class Board
{
	private Scanner input = new Scanner(System.in);
	private Random 	value = new Random();
	private int[] 	code;
	private int attempts;
	
	Board()
	{
		code = new int[4];
		attempts = 12;
	}
	
	Board(int entries, int attempts)
	{
		code = new int[entries];
		this.attempts = attempts;
	}
	
	public void newGame()
	{
	// Retrieves a board for the user.
		buildBoard();
		
	//	guess will hold the users guess as an integer array.
	//	numGuess keeps track of the number of guesses the user has made.
	//	guessInput holds the users input as a String.
	//	temp holds a boolean value for checkGuessSyntax and checkGuess to reduce function calls and writing.
		int[] guess = new int[code.length];
		int numGuess = 0;
		String guessInput;
		boolean temp;
		
		System.out.println("You have " + attempts + " attempts to guess a " + code.length + " digit long code.");
		
	//	Main game executes until the user has made their maximum number of guesses.
		while(numGuess < attempts)
		{	
		//	Promtps the user for their guess and tells them how many attempts they have left.
			System.out.printf("Enter your guess %11s", "(" + numGuess + "/" + attempts + ") : ");
			guessInput = input.nextLine();
			temp = checkGuessSyntax(guessInput);
		
		//	Checks to make sure guess has proper syntax. Otherwise tells the user
		//	the code is invalid.
			if(temp)
			{	
				for(int i = 0; i < code.length; i++)
				{
				//	Uses substrings to build the guess array 
					guess[i] = Integer.parseInt(guessInput.substring(i, i + 1));
				}
			
				numGuess++;
			//	Checks to see if the users guess is correct. Feedback on their
			//	guess is provided by the checkGuess method.
				temp = checkGuess(guess);
				
				if(temp)
				{
				//	If the user guessed the code.
					numGuess = attempts;
					System.out.println("You've cracked the code!");
				}
				else if(!temp && numGuess == attempts)
				{
				// If the user ran out of attempts.
					System.out.println("Game Over.");
					System.out.println("The code was: " + printCode(code));
				}
			} 
			else if(!temp)
			{
			// If the user puts in an invalid code.
				System.out.println("Not a valid code.");
			}
			
		}
		System.out.println("Type \"q\" to quit.");
		input.next();
	}
	
	private boolean checkGuessSyntax(String guess)
	{
	//	If the user enters a string with a length greater or less than
	//	the code length then the input must be invalid.
		if(guess.length() == code.length)
		{
			for(int i = 0; i < code.length; i++)
			{
			// If the parseInt method throw a NumberFormatException then the
			//	user must have entered a code containing non integer values.
				try
				{
					Integer.parseInt(guess);
				} 
				catch(NumberFormatException e)
				{
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	
	private boolean checkGuess(int[] guess)
	{
	//	rightNum is a counter for the amount of correct number.
	//	rightNumPlace is a counter for the correct amount of numbers in the 
	//	correct place.
	//	checkedValues conatins the numbers already present in the code.
		int rightNum = 0;
		int rightNumPlace = 0;
		int[] checkedLocations = new int[code.length];
		int[] checkedValues = new int[code.length];
		
	//	Initializes array so that there are no accidental matches
	//	when comparing values.
		for(int i = 0; i < code.length; i++)
		{
			checkedLocations[i] = -1;
		}
		
		for(int i = 0; i < code.length; i++)
		{
				if(code[i] == guess[i])
				{
				//	Increment rightNumPlace if the value is equal to a value
				//	in the coorresponding location. Add that i value to checkedValues
				//	as it doesn't need to be checked again.
					rightNumPlace++;
					checkedLocations[i] = i;
				}
		}
		
		for(int i = 0; i < code.length; i++)
		{
			if(i != checkedLocations[i])
			{
				for(int j = 0; j < code.length; j++)
				{
					if(code[i] == guess[j])
					{
						rightNum++;
						break;
					}
				}
			}
		}
		
		
		
		if(rightNumPlace == code.length) 
		{
			return true;
		}
		
		System.out.println("Correct number wrong place: " + rightNum);
		System.out.println("Correct place right number: " + rightNumPlace);
		
		return false;
	}
	
//	Builds a board for the user using the Random object methods.
	private void buildBoard()
	{
		for(int i = 0; i < code.length; i++)
		{
			code[i] = value.nextInt(10);
		}
	}

// Prints a formatted version of the code.
	private String printCode(int[] code)
	{
		StringBuilder codeReturn = new StringBuilder(code[0] + " ");
		
		for(int i = 1; i < code.length - 1; i++)
		{
			codeReturn.append(code[i] + " ");
		}
		
		codeReturn.append(code[code.length - 1]);		
		
		return new String(codeReturn);
	}
	
		public static void main(String[] args)
	{
		Board myBoard = new Board();
		
		myBoard.newGame();
	}
}