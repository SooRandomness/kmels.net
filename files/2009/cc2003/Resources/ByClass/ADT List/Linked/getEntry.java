public Object getEntry(int givenPosition){	Object result = null;  // result to return	if (!isEmpty() && (givenPosition >= 1) && (givenPosition <= length))		result = getNodeAt(givenPosition).data;	return result;} // end getEntry