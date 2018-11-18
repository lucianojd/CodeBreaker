public class testBoard
{	
	public static void main(String[] args)
	{
		int[] code = {1,2,2,2};
		
		int[] guess = {1,1,1,1};
		
		System.out.println(toString(code));
		
		EntryStack test1 = new EntryStack();
		
		for(int i = 0; i < 3; i++)
		{
			test1.push(1);
		}
		
		for(int i = 0; i < 3; i++)
		{
			test1.push(5);
		}
		
		test1 = testBoard.removeDuplicateValues(test1);
		
		System.out.println(test1.pop());
		System.out.println(test1.pop());
		
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
		EntryStack valuesToCheck = new EntryStack();
		
		for(int i = 0; i < 4; i++)
		{
			if(code[i] == guess[i])
			{
				correctPlace++;
			}
			else
			{
				valuesToCheck.push(guess[i]);
			}
		}
		
		valuesToCheck = testBoard.removeDuplicateValues(valuesToCheck);

		System.out.println(correctNum);
		System.out.println(correctPlace);
		
		if(correctPlace == 4)
		{
			return true;
		}
		
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