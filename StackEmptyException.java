public class StackEmptyException extends RuntimeException
{
	/*
	 * Passes an error message to the super constructor.
	 * Param: msg a message to display when the exception is thrown.
	*/
	public StackEmptyException (String msg)
	{
		super(msg);
	}
	
	/*
	 * Creates a new RuntimeException object using the default
	 * constructor.
	*/
	public StackEmptyException ()
	{
		super();
	}
}