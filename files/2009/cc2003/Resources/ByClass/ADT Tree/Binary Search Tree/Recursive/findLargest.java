/** Task: Finds the node containing the largest entry in a *  given tree *  @param rootNode the root node of the tree *  @return the node with the largest entry */private BinaryNode findLargest(BinaryNode rootNode){ 	if (rootNode.hasRightChild())		rootNode = findLargest((BinaryNode)rootNode.getRightChild());	return rootNode;} // end findLargest