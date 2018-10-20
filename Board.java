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
		buildBoard();
		
		int[] guess = new int[code.length];
		int numGuess = 0;
		String guessInput;
		boolean temp;
		
		System.out.println("You have " + attempts + " attempts to guess a " + code.length + " digit long code.");
		
		
		while(numGuess < attempts)
		{	
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
					System.out.println("You've cracked the code!");
					numGuess = attempts;
				}
				else if(!temp && numGuess == attempts)
				{
					System.out.println("Game Over.");
				}
			} 
			else if(!temp)
			{
				System.out.println("Not a valid code.");
			}
			
		}
		
	}
	
	private boolean checkGuessSyntax(String guess)
	{
		if(guess.length() == code.length)
		{
			for(int i = 0; i < code.length; i++)
			{
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
		int rightNum = 0;
		int rightNumPlace = 0;
		
		for(int i = 0; i < code.length; i++)
		{
			for(int j = 0; j < code.length; j++)
			{
				if(code[i] == guess[j] && i == j)
				{
					rightNumPlace++;
				}
				else if(code[i] == guess[j])
				{
					rightNum++;
				}
			}
		}
		
		if(rightNumPlace == code.length) 
		{
			return true;
		}
		
		System.out.println("Correct numbers: " + rightNum);
		System.out.println("Correct places: " + rightNumPlace);
		
		return false;
	}
	
	private void buildBoard()
	{
		for(int i = 0; i < code.length; i++)
		{
			code[i] = value.nextInt(10);
		}
	}
	
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
}