package Engine;

public class EntryStack
 {

	public Node head;
	int size = 0;

	/*
	 * Default constructor assigns null to the head
	 * indicating an empty stack.
	*/
	EntryStack()
	{
		head = null;
	}
	
	/*
	 * Checks to see if the head is null. If it is
	 * the stack is empty and the method returns true.
	 * No parameters.
	*/
	public boolean isEmpty() 
	{
		if(head == null)
		{
			return true;
		}
		
		return false;
	}

	/*
	 * Pops the item at the top of the stack. First checks to 
	 * see if the stack is empty before trying to pop.
	 * No parameters.
	 * Throws StackEmptyException.
	*/
	public int pop()throws StackEmptyException 
	{
		if(isEmpty())
		{
			throw new StackEmptyException("Stack is empty.");
		}
		
		int top = head.item;
		
		head = head.next;
		
		size--;
		
		return top;
	}

	/*
	 * Returns the value at the top of the stack without
	 * removing it.
	 * No parameters.
	 * Throws StackEmptyException.
	*/
	public int peek()throws StackEmptyException
	{
		if(isEmpty())
		{
			throw new StackEmptyException("Stack is empty.");
		}
	
		return head.item;
	}

	/*
	 * Takes a String item and pushes it to the top of 
	 * the stack. Contains a special case where the head
	 * is null. The Node constructor with one String parameter is
	 * used so that the next address in the stack is null.
	 * Param: item is used to create a new Node object to
	 * be added to the stack.
	 * Param item: String passed to be stored in the stack.
	*/
	public void push(int item) 
	{
		if(head == null)
		{
			head = new Node(item);
		}
		else
		{
			head = new Node(item, head);
		}
		
		size++;
	}

	/*
	 * Empties the stack by assigning the head a 
	 * value of null.
	 * No parameters.
	*/
	public void popAll() 
	{
		head = null;
	}
	
	public int getSize()
	{
		return size;
	}
}
