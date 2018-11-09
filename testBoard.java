public class testBoard
{	
	public static void main(String[] args)
	{
		int[] code = {1,2,2,2};
		
		int[] guess = {2,2,1,1};
		
		System.out.println(toString(code));
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[0] = 1;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[3] = 2;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[0] = 2;
		guess[2] = 2;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[0] = 1;
		guess[1] = 1;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
	}
	
	private static boolean checkGuess(int[] code, int[] guess)
	{
		int correctNum = 0;
		int correctPlace = 0;
		int index = 1;
		int[] trimmedGuess = new int[guess.length];
		EntryStack valuesToCheck = new EntryStack();
		
		for(int i = 0; i < 4; i++)
		{
			if(code[i] == guess[i])
			{
				correctPlace++;
			}
			else
			{
				valuesToCheck.push(i);
			}
		}
		
		trimmedGuess[0] = valuesToCheck.pop();
		
		while(!valuesToCheck.isEmpty())
		{	
			for(int i = 0; i < trimmedGuess.length; i++)
			{
				if(valuesToCheck.peek() != trimmedGuess[i])
				{
					trimmedGuess[index] = valuesToCheck.pop();
					break;
				}
			}
			
			index++;
		}
		
		for(int i = 0; i < trimmedGuess.length; i++)
		{
			for(int j = 0; j < code.length; j++)
			{
				if(trimmedGuess[i] == code[j])
				{
					correctNum++;
					break;
				}
			}
		}

		System.out.println(correctNum);
		System.out.println(correctPlace);
		
		if(correctPlace == 4)
		{
			return true;
		}
		
		return false;
	}
	
	private static String toString(int[] a)
	{
		StringBuilder result = new StringBuilder("{");
		
		for(int i = 0; i < a.length - 1; i++)
		{
			result.append(a[i] + ",");
		}
		
		result.append(a[a.length - 1] + "}");
		
		return new String(result);
	}
}