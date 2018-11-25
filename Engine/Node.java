package Engine;

public class Node 
{
	int item;
	Node next;
	
	/*
	 * Initializes a Node object when no parameters are given.
	*/
	Node()
	{
		item = -1;
		next = null;
	}

	/*
	 * Initializes a Node object with the item passed.
	 * Param: item the String passed to the Node.
	*/
	Node(int item)
	{
		this.item = item;
		next = null;
	}
	
	/*
	 * Initializes a Node with both an item and the next Node address.
	 * Param: item the String passed to be stored in the Node.
	 * Param: next the address of the next Node.
	*/
	Node(int item, Node next)
	{
		this.item = item;
		this.next = next;
	}
}