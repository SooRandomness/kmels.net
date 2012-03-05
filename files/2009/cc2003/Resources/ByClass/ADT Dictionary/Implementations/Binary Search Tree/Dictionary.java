import java.util.Iterator;public class Dictionary implements DictionaryInterface, java.io.Serializable{	private SearchTreeInterface bst;		public Dictionary()	{		bst = new BinarySearchTree();	} // end default constructor	public Object add(Object key, Object value)	{		Entry newEntry = new Entry(key, value);		Entry returnedEntry = (Entry)bst.add(newEntry);		Object result = null;		if (returnedEntry != null)			result = returnedEntry.getValue();					return result;	} // end add	public Object remove(Object key)	{		Entry findEntry = new Entry(key, null);		Entry returnedEntry = (Entry)bst.remove(findEntry);		Object result = null;		if (returnedEntry != null)			result = returnedEntry.getValue();					return result;	} // end remove	public Object getValue(Object key)	{		Entry findEntry = new Entry(key, null);		Entry returnedEntry = (Entry)bst.getEntry(findEntry);				Object result = null;		if (returnedEntry != null)			result = returnedEntry.getValue();					return result;	} // end getValue		public boolean contains(Object key)	{		Entry findEntry = new Entry(key, null);				return bst.contains(findEntry);	} // end contains	public Iterator getKeyIterator()	{		return new KeyIterator();	} // end getKeyIterator	public Iterator getValueIterator()	{		return new ValueIterator();	} // end getValueIterator	public boolean isEmpty()	{		return bst.isEmpty();	} // end isEmpty		public boolean isFull()	{		return false;	} // end isFull	public int getSize()	{		return bst.getNumberOfNodes();	} // end getSize	public void clear()	{		bst.clear();	} // end clear	private class KeyIterator implements Iterator	{		Iterator localIterator;				public KeyIterator()		{			localIterator = bst.getInorderIterator();		} // end default constructor				public boolean hasNext()		{			return localIterator.hasNext();		} // end hasNext				public Object next()		{			return ((Entry)localIterator.next()).getKey();		} // end next				public void remove()		{			throw new UnsupportedOperationException();		} // end remove	} // end KeyIterator		private class ValueIterator implements Iterator	{		Iterator localIterator;				public ValueIterator()		{			localIterator = bst.getInorderIterator();		} // end default constructor				public boolean hasNext() 		{			return localIterator.hasNext();		} // end hasNext				public Object next()		{			return ((Entry)localIterator.next()).getValue();		} // end next				public void remove()		{			throw new UnsupportedOperationException();		} // end remove	} // end ValueIterator		private class Entry implements Comparable, java.io.Serializable 	{		private Object key;		private Object value;		private Entry(Object searchKey, Object dataValue)		{			key = searchKey;			value = dataValue;		} // end constructor		private Object getKey()		{			return key;		} // end getKey		private Object getValue()		{			return value;		} // end getValue		private void setValue(Object dataValue)		{			value = dataValue;		} // end setValue				public int compareTo(Object other)		{			Comparable cKey = (Comparable)key;			return cKey.compareTo(((Entry)other).key);		} // end compareTo				public boolean equals(Object other)		{			Comparable cKey = (Comparable)key;			return cKey.compareTo(((Entry)other).key) == 0;		} // end equals	} // end Entry} // end Dictionary