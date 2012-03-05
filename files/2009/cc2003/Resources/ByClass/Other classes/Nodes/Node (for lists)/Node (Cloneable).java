private class Node implements Cloneable{	private Listable data;	private Node     next;	private Node(Listable dataPortion)	{		data = dataPortion;		next = null;	} // end constructor	private Node(Listable dataPortion, Node nextNode)	{		data = dataPortion;		next = nextNode;	} // end constructor		private Object getData()	{		return data;	} // end getData		private void setData(Listable newData)	{		data = newData;	} // end setData		private Node getNextNode()	{		return next;	} // end getNextNode		private void setNextNode(Node nextNode)	{		next = nextNode;	} // end setNextNode		public Object clone()	{		Node theCopy = null;		try		{			theCopy = (Node)super.clone();		}		catch (CloneNotSupportedException e)		{			throw new Error(e.toString());		} // end try/catch		theCopy.data = (Listable)data.clone();		theCopy.next = null; // don't clone link; it's set later		return theCopy;	} // end clone} // end Node