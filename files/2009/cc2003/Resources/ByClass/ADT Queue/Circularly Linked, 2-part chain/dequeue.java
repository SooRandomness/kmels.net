public Object dequeue(){	Object front = null;	if (!isEmpty())	{		front = queueNode.getData();		queueNode.setData(null);		queueNode = queueNode.getNextNode();	} // end if	return front;} // end dequeue