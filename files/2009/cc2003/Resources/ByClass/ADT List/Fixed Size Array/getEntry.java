public Object getEntry(int givenPosition){	Object result = null;  // result to return	if ((givenPosition >= 1) && (givenPosition <= length))		result = entry[givenPosition-1];	return result;} // end getEntry