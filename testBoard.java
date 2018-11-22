public class testBoard
{	
	public static void main(String[] args)
	{
		int[] code = {1,2,2,2};
		
		int[] guess = {1,1,1,1};
		
		System.out.println(toString(code));
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[3] = 2;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[2] = 2;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[1] = 2;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[0] = 2;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[3] = 1;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[2] = 1;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[1] = 1;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[3] = 2;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[2] = 2;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
		guess[1] = 2;
		guess[2] = 1;
		
		System.out.println(toString(guess));
		System.out.println(checkGuess(code, guess));
		
	}
	
	private static boolean checkGuess(int[] code, int[] guess)
	{
		int correctNum = 0;
		int correctPlace = 0;
		int guessValue = 0;
		boolean[] valueIsPresent = new boolean[code.length];
		EntryStack valuesToCheck = new EntryStack();
		
		for(int i = 0; i < 4; i++)
		{
			if(code[i] == guess[i])
			{
				correctPlace++;
				valueIsPresent[i] = true;
			}
			else
			{
				System.out.print("{" + guess[i] + "}");
				valuesToCheck.push(guess[i]);
			}
		}
		
		System.out.println();
		
		if(correctPlace == 4)
		{
			return true;
		}
		
		valuesToCheck = testBoard.removeDuplicateValues(valuesToCheck);
		
		while(!valuesToCheck.isEmpty())
		{
			guessValue = valuesToCheck.pop();
			System.out.print("(" + guessValue + ")");
			
			for(int i = 0; i < 4; i++)
			{
				if(!valueIsPresent[i])
				{
					if(guessValue == code[i])
					{
						correctNum++;
						break;
					}
				}
			}
		}

		System.out.println();
		System.out.println(correctNum);
		System.out.println(correctPlace);
		
		return false;
	}
	
	public static EntryStack removeDuplicateValues(EntryStack a)
	{
		boolean[] valueIsPresent = new boolean[10];
		EntryStack noRepeatedValues = new EntryStack();
		int poppedValue = 0;
		
		for(int i = 0; i < 10; i++)
		{
			valueIsPresent[i] = false;
		}
		
		while(!a.isEmpty())
		{
			poppedValue = a.pop();
			
			for(int i = 0; i < 10; i++)
			{
				if(i == poppedValue && valueIsPresent[i] == false)
				{
					noRepeatedValues.push(i);
					valueIsPresent[i] = true;
				}
			}
		}
		
		return noRepeatedValues;
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