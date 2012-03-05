	private void rehash()	{ 		Node[] oldTable = hashTable; 		int oldSize = oldTable.length;     		int newSize = getNextPrime(oldSize + oldSize);		hashTable = new Node[newSize];    // increase size of array		// reset size of dictionary		currentSize = 0; 		// rehash dictionary entries from old array to new, bigger array.		for (int index = 0; index < oldSize; ++index)		{			// rehash chain in old table			Node currentNode = oldTable[index];			while (currentNode != null) // skip empty lists			{				add(currentNode.getKey(), currentNode.getValue());				currentNode = currentNode.getNextNode();			} // end while		} // end for	} // end rehash