import java.io.PrintStream;
import java.util.NoSuchElementException;

public class List
{
	
	public ListNode first;
	public ListNode last;
	public String empty_list;
	public int size=0;
	
	/*
	 * Constructor without arguments.
	 * Calls the second constructor 
	 * and creates a list with the name
	 * "list."
	 */
	public List()
	{
		this("list");
	}
 
 
	/*
	 * Constructor with a string as an argument.
	 * Creates a list with the name "listName".
	 */
	public List (String listName)
	{
		empty_list = listName;
		first = last = null;
	}
 
 
	/*
	 * Checks if the list is empty and returns the result.
	 */
	
	public boolean isEmpty() 
	{
		if (first == last & first == null)
		{
			return true;
		}//end if
		else 
		{
			return false;
		}//end else
	}//end isEmpty

 
	/*
	 * Inserts a suspect to the list.
	 */
	
	public void put(Suspect item) 
	{
		ListNode newnode = new ListNode(item);
		if(isEmpty())
		{
			first = last = newnode;
		}//end if
		else
		{
			size++;
			last.next = newnode;
			last = newnode;
		}//end else
	}//end put
 

	/*
	 * Removes and returns the oldest item of the list.
	 * Throws exception if the list is empty. 
	 */
	
	public Suspect get() throws NoSuchElementException 
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("List is empty, we can't get item.");
		}//end if
		else
		{	
			size--;
			Suspect removedItem =  first.getElement();
			if (first == last)
			{
				first = last = null;
			}//end if
			else
			{
				first = first.next;
			}//end else
			return removedItem; 
		}//end else
	}//end get
 

	/*
	 * Prints the elements of the list, starting from the oldest item.
	 */
	
	public void printList(PrintStream stream)
	{
		if(isEmpty())
		{
			stream.println("List is empty, no items to print");
		}//end if
		else
		{
			ListNode temp = first;
			while(temp != last)
			{
				stream.println(temp.getElement());
				temp = temp.next;
			}//end while
			stream.println(last.getElement());
		}//end else
	}//end printQueue
 
 
	/*
	 * Returns the size of the list.
	 */
	
	public int size() 
	{
		return size;
	}//end size
	
}//end class