class TreeRotations{	public static BinaryNode rotateRight(BinaryNode nodeN)	{		BinaryNode nodeC = (BinaryNode)nodeN.getLeftChild();		nodeN.setLeftChild(nodeC.getRightChild());		nodeC.setRightChild(nodeN);		return nodeC;	} // end rotateRight	public static BinaryNode rotateLeft(BinaryNode nodeN)	{		BinaryNode nodeC = (BinaryNode)nodeN.getRightChild();		nodeN.setRightChild(nodeC.getLeftChild());		nodeC.setLeftChild(nodeN);		return nodeC;	} // end rotateLeft	public static BinaryNode rotateRightLeft(BinaryNode nodeN)	{		BinaryNode nodeC = (BinaryNode)nodeN.getRightChild();		nodeN.setRightChild(rotateRight(nodeC));		return rotateLeft(nodeN);	} // end rotateRightLeft	public static BinaryNode rotateLeftRight(BinaryNode nodeN)	{		BinaryNode nodeC = (BinaryNode)nodeN.getLeftChild();		nodeN.setLeftChild(rotateLeft(nodeC));		return rotateRight(nodeN);	} // end rotateLeftRight} // end TreeRotations