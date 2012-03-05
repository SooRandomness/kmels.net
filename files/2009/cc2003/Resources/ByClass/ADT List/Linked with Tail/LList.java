public class LList implements ListInterface{	private Node firstNode; // reference to first node	private Node lastNode;	// reference to last node	private int  length;    // number of entries in list	public LList()	{		firstNode = null;		lastNode = null;		length = 0;	} // end default constructor	public final void clear()	{		firstNode = null;		lastNode = null;		length = 0;	} // end clear		public boolean add(Object newEntry)	{		Node newNode = new Node(newEntry);		if (isEmpty())			firstNode = newNode;		else			lastNode.next = newNode;		lastNode = newNode;		length++;		return true;	}  // end add	public boolean add(int newPosition, Object newEntry) 	{		boolean isSuccessful = true;		if ((newPosition >= 1) && (newPosition <= length+1)) 		{			Node newNode = new Node(newEntry);			if (isEmpty())			{				firstNode = newNode;				lastNode = newNode;			}			else if (newPosition == 1)			{				newNode.next = firstNode;				firstNode = newNode;			}			else if (newPosition == length + 1)			{				lastNode.next = newNode;				lastNode = newNode;			} // end if			else			{				Node nodeBefore = getNodeAt(newPosition-1);				Node nodeAfter = nodeBefore.next;				newNode.next = nodeAfter;				nodeBefore.next = newNode;			} // end if			length++;		}		else			isSuccessful = false;		return isSuccessful;	} // end add		public Object remove(int givenPosition)	{		Object result = null; // return value		if (!isEmpty() && (givenPosition >= 1) && (givenPosition <= length))		{			if (givenPosition == 1)			{				result = firstNode.data; 				firstNode = firstNode.next;				if (length == 1)				lastNode = null; // solitary entry was removed			}			else			{				Node nodeBefore = getNodeAt(givenPosition-1);				Node nodeToRemove = nodeBefore.next;				Node nodeAfter = nodeToRemove.next;				nodeBefore.next = nodeAfter; // disconnect node to be removed				result = nodeToRemove.data;  // save entry to be removed				if (givenPosition == length)					lastNode = nodeBefore; // last node was removed			} // end if			length--;		} // end if		return result;	} // end remove	public boolean replace(int givenPosition, Object newEntry)	{		boolean isSuccessful = true;		if (!isEmpty() && (givenPosition >= 1) && (givenPosition <= length))		{			Node desiredNode = getNodeAt(givenPosition);			desiredNode.data = newEntry;		} 		else			isSuccessful = false;		return isSuccessful;	} // end replace	public Object getEntry(int givenPosition)	{		Object result = null;  // result to return		if (!isEmpty() && (givenPosition >= 1) && (givenPosition <= length))			result = getNodeAt(givenPosition).data;		return result;	} // end getEntry	public boolean contains(Object anEntry)	{		boolean found = false;		Node currentNode = firstNode;		while (!found && (currentNode != null))		{			if (anEntry.equals(currentNode.data))				found = true;			else				currentNode = currentNode.next;		} // end while		return found;	} // end contains	public int getLength()	{		return length;	} // end getLength	public boolean isEmpty()	{		return length == 0;	} // end isEmpty		public boolean isFull()	{		return false;	} // end isEmpty		//  STUDENT EXCERCISE	public void display()	{		for (Node current = firstNode; current != null; current = current.next)			System.out.println(current.data);	} // end display	// ---------------private!----------------------------- 	/** Task: Returns a reference to the node at a given position.	 *  Precondition: List is not empty; 1 <= givenPosition <= length. */	private Node getNodeAt(int givenPosition)	{		Node currentNode = firstNode;		// traverse the list to locate the desired node		for (int counter = 1; counter < givenPosition; counter++)			currentNode = currentNode.next;		return currentNode;	} // end getNodeAt	private class Node // private inner class	{		private Object data; // data portion		private Node   next; // link to next node		private Node(Object dataPortion)		{			data = dataPortion;			next = null;		} // end constructor		private Node(Object dataPortion, Node nextNode)		{			data = dataPortion;			next = nextNode;		} // end constructor	} // end Node} // end LList