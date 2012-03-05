import java.lang.*; // UnsupportedOperationExceptionimport java.util.*; // Iteratorpublic class HashedMapSeparateChaining implements DictionaryInterface{	private Node[] hashTable;							// dictionary entries	private int currentSize;                 			// current number of entries	private static final int DEFAULT_SIZE = 101; 		// default size of hash table - must be prime 	private static final double MAX_LOAD_FACTOR = 0.9;  // fraction of hash table that can be filled		public HashedMapSeparateChaining()	{		hashTable = new Node[DEFAULT_SIZE];		currentSize = 0;	} // end default constructor	public HashedMapSeparateChaining(int maxSize)	{		// ensure that table is prime size at least as big as user wants		// if maxsize is prime, do not change it		int primeSize = getNextPrime(maxSize);				hashTable = new Node[primeSize];		currentSize = 0;	} // end constructor// -------------------------// We've added this method to display the hash table for illustration and testing// -------------------------   	public void display()     	{		for (int index = 0; index < hashTable.length; index++)		{	      	if (hashTable[index] == null)	      		System.out.println("null ");	       	else	       	{	       		Node currentNode = hashTable[index];	      		while (currentNode != null)	      		{	      			System.out.print(currentNode.getKey() + " " 	      			                 + currentNode.getValue() + ", ");	      			currentNode = currentNode.getNextNode();	      		} // end while	      			      		System.out.println();	      	} // end if		} // end for    	System.out.println();   	} // end display	public Object add(Object key, Object value)	{		// assumes key and value are not null		Object oldValue = null; // value to return				if (isHashTableTooFull())		{			rehash();		} // end if		int index = getHashIndex(key);		// index always in range due to mod of hash fn		if (hashTable[index] == null)		{			// key not found, so insert new entry						hashTable[index] = new Node(key, value);			currentSize++;		}		else // search chain beginning at hashTable[index] for a node that contains key		{			Node currentNode = hashTable[index];			Node nodeBefore = null;						while ( (currentNode != null) && !key.equals(currentNode.getKey()) )			{				nodeBefore = currentNode;				currentNode = currentNode.getNextNode();			} // end while			if (currentNode == null)			{				// key not in chain; add new entry to end of chain				Node newNode = new Node(key, value);				nodeBefore.setNextNode(newNode);				currentSize++;			}			else			{				// key found; get old value for return and then replace it				oldValue = currentNode.getValue();				currentNode.setValue(value);			} // end if		} // end if		return oldValue;	} // end add	public Object remove(Object key)	{		Object removedValue = null;		int index = getHashIndex(key);		// search chain beginning at hashTable[index] for a node that contains key		Node nodeBefore = null;		Node currentNode = hashTable[index];				while ( (currentNode != null) && !key.equals(currentNode.getKey()) )		{			nodeBefore = currentNode;			currentNode = currentNode.getNextNode();		} // end while		if (currentNode != null)		{			// key found; get value for return and then remove entry			removedValue = currentNode.getValue();						if (nodeBefore == null)			{				// remove first node				 hashTable[index] = currentNode.getNextNode();			}			else			{				nodeBefore.setNextNode(currentNode.getNextNode());			}						currentSize--;		} // end if		// else removedValue is null if key not found				return removedValue;	} // end remove	public Object getValue(Object key)	{	Object result = null;		int index = getHashIndex(key);		// search chain beginning at hashTable[index] for a node that contains key		Node currentNode = hashTable[index];				while ( (currentNode != null) && !key.equals(currentNode.getKey()) )		{			currentNode = currentNode.getNextNode();		} // end while		if (currentNode != null)		{			// key found; get value for return			result = currentNode.getValue();		} // end if		// else not found; result is null				return result;	} // end getValue	public boolean contains(Object key)	{		return getValue(key) != null; 	} // end contains	public boolean isEmpty()	{		return currentSize == 0;	} // end isEmpty	public boolean isFull()	{		return false;	} // end isFull	public int getSize()	{		return currentSize;	} // end getSize	public final void clear()	{		currentSize = 0;	} // end clear		private int getHashIndex(Object key)	{		int hashIndex = key.hashCode() % hashTable.length;		if (hashIndex < 0)			hashIndex = hashIndex + hashTable.length;		return hashIndex;	} // end getHashIndex   // Post: Returns true if lambda > MAX_LOAD_FACTOR for hash table ;   //       otherwise returns false.	private boolean isHashTableTooFull()	{		return currentSize > MAX_LOAD_FACTOR * hashTable.length;	} // end isHashTableTooFull	private int getNextPrime(int integer)	{		// if even, add 1 to make odd  	 	if (integer % 2 == 0)  	 	{        	integer++;		} // end if				// test odd integers    	while(!isPrime(integer))    	{    		integer = integer + 2;    	} // end while		return integer;	} // end getNextPrime	private boolean isPrime(int integer)	{		boolean result;		boolean done = false;				// 2 and 3 are prime		if ( (integer == 2) || (integer == 3) )		{			result = true;		}				// 1 and even numbers are not prime		else if ( (integer == 1) || (integer % 2 == 0) )	  	{			result = false; 		}				// Assertion: integer is odd and >= 5.		else		{			// a prime is odd and not divisible by any odd integer up to its square root			result = true; // assume prime			for (int divisor = 3; !done && (divisor * divisor <= integer); divisor = divisor + 2)			{		   	if (integer % divisor == 0)	      	{					result = false; // divisible; not prime					done = true;				} // end if			} // end for		} // end if	   			return result;	} // end isPrime	// Purpose: Increase the size of the hash table to a prime >= twice its old size.	private void rehash()	{ 		Node[] oldTable = hashTable; 		int oldSize = oldTable.length;     		int newSize = getNextPrime(oldSize + oldSize);		hashTable = new Node[newSize];    // increase size of array		// reset size of dictionary		currentSize = 0; 		// rehash dictionary entries from old array to new, bigger array.		for (int index = 0; index < oldSize; ++index)		{			// rehash chain in old table			Node currentNode = oldTable[index];			while (currentNode != null) // skip empty lists			{				add(currentNode.getKey(), currentNode.getValue());				currentNode = currentNode.getNextNode();			} // end while		} // end for	} // end rehash	public Iterator getKeyIterator()	{ 		return new KeyIterator();	} // end getKeyIterator		public Iterator getValueIterator()	{			return new ValueIterator();	} // end getValueIterator	private class Node	{		private Object entryKey;		private Object entryValue;		private Node	next;  // link to next node		private Node(Object key, Object value)		{			entryKey = key;			entryValue = value;			next = null;			} // end constructor				private Node(Object key, Object value, Node nextPortion)		{			entryKey = key;			entryValue = value;			next = nextPortion;			} // end constructor				private Object getKey()		{			return entryKey;		} // end getKey				private Object getValue()		{			return entryValue;		} // end getValue		// no setKey!!		private void setValue(Object newValue)		{			entryValue = newValue;		} // end setValue		private Node getNextNode()		{			return next;		} // end getNextNode				private void setNextNode(Node nextNode)		{			next = nextNode;		} // end setNextNode	} // end Node	private class KeyIterator implements Iterator	{		private Node currentNode;		private int currentIndex; // location in hash table				private KeyIterator()		{			currentIndex = -1;			currentNode = getNextChain(); // set to first non-empty chain		} // end default constructor				private Node getNextChain()		{			while ( (currentNode == null) && (currentIndex < hashTable.length - 1) ) // skip empty lists			{				currentIndex++;				currentNode = hashTable[currentIndex];			} // end while						return currentNode;		} // end getNextChain		// methods are public to match the interface Iterator 		public boolean hasNext() 		{			return currentNode != null;		} // end hasNext				public Object next()		{			Object result = null;						if (hasNext())			{				result = currentNode.getKey();				currentNode = currentNode.getNextNode(); // follow chain								if (currentNode == null) // if at end of chain				{					currentNode = getNextChain();				} // end if 			}			else			{				throw new NoSuchElementException(); // in java.util			}					return result;		} // end next				public void remove()		{			throw new UnsupportedOperationException(); // in java.lang		} // end remove	} // end KeyIterator	private class ValueIterator implements Iterator	{		private Node currentNode;		private int currentIndex; // location in hash table				private ValueIterator()		{			currentIndex = -1;			currentNode = getNextChain(); // set to first non-empty chain		} // end default constructor				private Node getNextChain()		{			while ( (currentNode == null) && (currentIndex < hashTable.length - 1) ) // skip empty lists			{				currentIndex++;				currentNode = hashTable[currentIndex];			} // end while						return currentNode;		} // end getNextChain		// methods are public to match the interface Iterator 		public boolean hasNext() 		{			return currentNode != null;		} // end hasNext				public Object next()		{			Object result = null;						if (hasNext())			{				result = currentNode.getValue();				currentNode = currentNode.getNextNode();				if (currentNode == null)				{					currentNode = getNextChain();				} // end if 			}			else			{				throw new NoSuchElementException(); // in java.util			}					return result;		} // end next				public void remove()		{			throw new java.lang.UnsupportedOperationException(); // in java.lang		} // end remove	} // end getValueIterator	} // end HashedMapSeparateChaining