/** Task: Returns a reference to the node at a given position. *  Precondition: List is not empty; 1 <= givenPosition <= length. */private Node getNodeAt(int givenPosition){	Node currentNode = firstNode;	// traverse the list to locate the desired node	for (int counter = 1; counter < givenPosition; counter++)		currentNode = currentNode.next;	return currentNode;} // end getNodeAt