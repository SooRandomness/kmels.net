public Object dequeue(){	Object front = null;	if (!isEmpty())	{		front = queue[frontIndex];		queue[frontIndex] = null;		frontIndex = (frontIndex + 1) % queue.length;	} // end if	return front;} // end dequeue