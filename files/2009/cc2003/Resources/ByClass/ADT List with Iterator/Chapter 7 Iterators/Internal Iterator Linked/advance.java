public boolean advance(){	boolean result = false;	if (hasCurrent())	{		priorNode = currentNode;		currentNode = currentNode.next; 		result = hasCurrent();	}	return result;} // end advance