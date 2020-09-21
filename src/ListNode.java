public class ListNode
{
	// package access members; List can access these directly
	protected Suspect element;
	protected ListNode next;

	/**
	 * Constructor. It initializes data and sets next node to null
	 * @param element a reference to node's data
	*/
	ListNode( Suspect element )
	{
		this(element, null);

	} // end ListNode one-argument constructor

	/**
	 * constructor creates ListNode with passed data and next node
	 * @param element the reference to node's data
	 * @param node the next node in the list
	*/
	ListNode( Suspect element, ListNode node )
	{
		this.element = element;
		this.next = node;

	} // end ListNode two-argument constructor

	/**
	 * Returns this node's data
	 * @return the reference to node's data
	*/
	Suspect getElement()
	{
		return this.element;

	} // end method getObject

	/**
	 * Get reference to next node
	 * @return the next node
	*/
	ListNode getNext()
	{
		return this.next;

	} // end method getNext
} // end class ListNode
