import java.util.Iterator;public class BinarySearchTree extends BinaryTree implements SearchTreeInterface{	public BinarySearchTree()	{		super();	} // end default constructor		public BinarySearchTree(Comparable rootEntry)	{		super();		setRootNode(new BinaryNode(rootEntry));	} // end constructor	public void setTree(Object rootData)	{		throw new UnsupportedOperationException();	} // end setTree	public Comparable getEntry(Comparable entry)	{		Comparable result = null;		boolean found = false;				BinaryNode currentNode = getRootNode();		while (!found && (currentNode != null) )		{			Object currentEntry = currentNode.getData();						if (entry.equals(currentEntry))			{				result = (Comparable) currentEntry;				found = true;			}			else if (entry.compareTo(currentEntry) < 0)				currentNode = (BinaryNode)currentNode.getLeftChild(); 			else				currentNode = (BinaryNode)currentNode.getRightChild(); 		} // end while				return result;	} // end getEntry		public boolean contains(Comparable entry)	{		return getEntry(entry) != null;	} // end contains		public Comparable add(Comparable newEntry)	{		Comparable result = null;				if (isEmpty())			setRootNode(new BinaryNode(newEntry)); // method is protected		                                           // in BinaryTree		else // look for newEntry in tree		{			BinaryNode currentNode = getRootNode(); // currentNode != null			BinaryNode parentNode = null;			boolean found = false;			char direction = ' ';						while (!found && (currentNode != null) )			{				Object currentEntry = currentNode.getData();				int comparison = newEntry.compareTo(currentEntry);				if (comparison < 0)				{ // search left					direction = 'L';					parentNode = currentNode;					currentNode = (BinaryNode)currentNode.getLeftChild();				}				else if (comparison > 0)				{ // search right					direction = 'R';					parentNode = currentNode;					currentNode = (BinaryNode)currentNode.getRightChild();				}				else				{ // newEntry matches currentEntry: return and replace				  // currentEntry					result = (Comparable) currentEntry;					currentNode.setData(newEntry);					found = true;				} // end if			} // end while						if (!found)			{ // add new entry as a leaf child of parentNode				if (direction == 'L')					parentNode.setLeftChild(new BinaryNode(newEntry));				else					parentNode.setRightChild(new BinaryNode(newEntry));			} // end if		} // end if				return result;	} // end add		public Comparable remove(Comparable entry)	{		Comparable result = null;		boolean found = false;				// locate node that contains a match for entry		BinaryNode currentNode = getRootNode();		BinaryNode parentNode = null;		while (!found && (currentNode != null) )		{			Object currentEntry = currentNode.getData();			int comparison = entry.compareTo(currentEntry);			if (comparison == 0)			{				found = true;				result = (Comparable)currentEntry;			}			else			{				parentNode = currentNode;				if (comparison < 0)					currentNode = (BinaryNode)currentNode.getLeftChild();				else					currentNode = (BinaryNode)currentNode.getRightChild();			} // end if		} // end while				if (found)		{			// Case 1: current node has two children			if (currentNode.hasLeftChild() && currentNode.hasRightChild())			{				// find node with largest entry in left subtree by				// moving as far right in the subtree as possible				BinaryNode leftSubtreeRoot = (BinaryNode)currentNode.getLeftChild();				BinaryNode rightChild = leftSubtreeRoot;				BinaryNode priorNode = currentNode;								while (rightChild.hasRightChild())				{					priorNode = rightChild;					rightChild = (BinaryNode)rightChild.getRightChild();				} // end while								// copy entry from rightmost node to current node				currentNode.setData(rightChild.getData());								// need to remove the rightmost node, so rename it as currentNode				currentNode = rightChild;				parentNode = priorNode; // parent of currentNode			} // end if						// Assertion: current node is the node to be removed; it has at most			// one child; case 1 has been transformed to case 2			// Case 2: current node has at most one child			BinaryNode childNode;			if (currentNode.hasLeftChild())				childNode = (BinaryNode)currentNode.getLeftChild();			else				childNode = (BinaryNode)currentNode.getRightChild();						// Assertion: if currentNode is a leaf, childNode is null			// remove current node			if (currentNode == getRootNode())				setRootNode(childNode);						// else link the parent of the current node to childNode,			// thereby deleting the current node			else if (parentNode.getLeftChild() == currentNode)				parentNode.setLeftChild(childNode);			else				parentNode.setRightChild(childNode);		} // end if				return result;	} // end remove} // end BinarySearchTree