private class Entry implements java.io.Serializable {	private Object key;	private Object value;		private Entry(Object searchKey, Object dataValue)	{		key = searchKey;		value = dataValue;	} // end constructor		private Object getKey()	{		return key;	} // end getKey		private Object getValue()	{		return value;	} // end getValue	private void setValue(Object dataValue)	{		value = dataValue;	} // end setValue} // end Entry