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
	}
	
	private boolean checkGuessSyntax(int[] guess)
	{
		return false;
	}
	
	private boolean checkGuess(int[] guess)
	{
		return false;
	}
	
	private void buildBoard()
	{
		for(int i = 0; i < code.length; i++)
		{
			code[i] = value.nextInt(10);
		}
	}
	
	public String toString()
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