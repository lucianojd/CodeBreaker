public class testBoard
{	
	public static void main(String[] args)
	{
		int[] code = {1,2,2,2};
		
		int[] guess = {2,2,1,1};
		
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
		int tempInt = 0;
		int[] checkedPlaces = new int[4];
		EntryStack valuesToCheck = new EntryStack();
		EntryStack indexToCheck = new EntryStack();
		EntryStack tempStack;
		
		for(int i = 0; i < 4; i++)
		{
			if(code[i] == guess[i])
			{
				correctPlace++;
				checkedPlaces[i] = i;
			}
			else
			{
				indexToCheck.push(i);
			}
		}
		
		for(int i = 0; i < 4; i++)
		{
			if(checkedPlaces[i] == -1)
			{
				valuesToCheck.push(guess[i]);
			}
		}
		
		while(!indexToCheck.isEmpty())
		{
			tempStack = valuesToCheck;
			tempInt = indexToCheck.pop();
			
			for(int i = 0; i < valuesToCheck.getSize(); i++)
			{
				if(guess[tempInt] == tempStack.pop())
				{
					correctNum++;
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