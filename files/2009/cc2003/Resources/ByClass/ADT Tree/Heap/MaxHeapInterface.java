// 24.32public interface MaxHeapInterface{	/** Task: Adds a new entry to the heap. 	 * @param newEntry  an object */	public void add(Comparable newEntry);		/** Task: Removes and returns the largest item in the heap,	 *        or returns null if the heap was empty. */	public Comparable removeMax();		/** Task: Returns either the largest item in the heap,	 *        or null if the heap is empty. */	public Comparable getMax();		/** Task: Determines whether the heap is empty. */	public boolean isEmpty();		/** Task: Gets the size of the heap. */	public int getSize();		/** Task: Removes all entries from the heap. */	public void clear();} // end MaxHeapInterface