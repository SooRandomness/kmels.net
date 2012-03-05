/** Tests iterative sorts */public class Driver{	public static void main(String[] argv) 	{		Name[] items = new Name[25];			items[0] = new Name("Zeke", "Clayton");		items[1] = new Name("Bob", "Clayton");		items[2] = new Name("Rob", "Smith");		items[3] = new Name("Ali", "Babba");		items[4] = new Name("Jamie", "Jones");		items[5] = new Name("Jody", "Jones");		items[6] = new Name("Jim", "Smith");		items[7] = new Name("John", "Clayton");		items[8] = new Name("Bill", "Smith");		items[9] = new Name("Jamie", "Jones"); 		items[10] = new Name("Zeke", "Clayton");		items[11] = new Name("Bob", "Clayton");		items[12] = new Name("Rob", "Smith");		items[13] = new Name("Ali", "Babba");		items[14] = new Name("Jamie", "Jones");		items[15] = new Name("Jody", "Jones");		items[16] = new Name("Jim", "Smith");		items[17] = new Name("John", "Clayton");		items[18] = new Name("Bill", "Smith");		items[19] = new Name("Jamie", "Jones");// ------------ /*	// reverse sorted order		items[19] = new Name("Ali", "Babba");		items[18] = new Name("Ali", "Babba");		items[17] = new Name("Bob", "Clayton");		items[16] = new Name("Bob", "Clayton");		items[15] = new Name("John", "Clayton");		items[14] = new Name("John", "Clayton");		items[13] = new Name("Zeke", "Clayton");		items[12] = new Name("Zeke", "Clayton");		items[11] = new Name("Jamie", "Jones");		items[10] = new Name("Jamie", "Jones");		items[9] = new Name("Jamie", "Jones");		items[8] = new Name("Jamie", "Jones");		items[7] = new Name("Jody", "Jones");		items[6] = new Name("Jody", "Jones");		items[5] = new Name("Bill", "Smith");		items[4] = new Name("Bill", "Smith");		items[3] = new Name("Jim", "Smith");		items[2] = new Name("Jim", "Smith");		items[1] = new Name("Rob", "Smith");		items[0] = new Name("Rob", "Smith");// -------------// sorted order		items[0] = new Name("Ali", "Babba");		items[1] = new Name("Ali", "Babba");		items[2] = new Name("Bob", "Clayton");		items[3] = new Name("Bob", "Clayton");		items[4] = new Name("John", "Clayton");		items[5] = new Name("John", "Clayton");		items[6] = new Name("Zeke", "Clayton");		items[7] = new Name("Zeke", "Clayton");		items[8] = new Name("Jamie", "Jones");		items[9] = new Name("Jamie", "Jones");		items[10] = new Name("Jamie", "Jones");		items[11] = new Name("Jamie", "Jones");		items[12] = new Name("Jody", "Jones");		items[13] = new Name("Jody", "Jones");		items[14] = new Name("Bill", "Smith");		items[15] = new Name("Bill", "Smith");		items[16] = new Name("Jim", "Smith");		items[17] = new Name("Jim", "Smith");		items[18] = new Name("Rob", "Smith");		items[19] = new Name("Rob", "Smith");*/		Name[] array = new Name[25];		for (int count = 20; count > 0; count--)		{			System.out.println(count + " items in array.");			copyArray(array, items);						testSelectionSort(array, count);	//		testInsertionSort(array, count);	//		testShellSort(array, count);		// 	testShellSortNoEven(array, count);	//		testBubbleSort(array, count);	//		testBetterBubbleSort(array, count);	//		testMergeSort(array, count);	//		testHeapSort(array, count);					} // end for				System.out.println("Done");	}  // end main	public static void testSelectionSort(Comparable[] array, int n)	{		System.out.println("\nBefore selection sort:");		display(array, n);		SortArray.selectionSort(array, n);				System.out.println("After selection sort:");		display(array, n);		check(array, n);	} // end testSelectionSort	public static void testInsertionSort(Comparable[] array, int n)	{		System.out.println("\nBefore insertion sort:");		display(array, n);		SortArray.insertionSort(array, n);				System.out.println("After insertion sort:");		display(array, n);		check(array, n);	} // end testInsertionSort		public static void testShellSort(Comparable[] array, int n)	{		System.out.println("\nBefore Shell sort:");		display(array, n);		SortArray.shellSort(array, 0, n-1);				System.out.println("After Shell sort:");		display(array, n);		check(array, n);	} // end testShellSort	public static void testShellSortNoEven(Comparable[] array, int n)	{		System.out.println("\nBefore no-even Shell sort:");		display(array, n);		SortArray.shellSortNoEven(array, 0, n-1);				System.out.println("After no-even Shell sort:");		display(array, n);		check(array, n);	} // end testShellSortNoEven	public static void testBubbleSort(Comparable[] array, int n)	{		System.out.println("\nBefore bubble sort:");		display(array, n);		SortArray.bubbleSort(array, n);				System.out.println("After bubble sort:");		display(array, n);		check(array, n);	} // end testBubbleSort		public static void testBetterBubbleSort(Comparable[] array, int n)	{		System.out.println("\nBefore better bubble sort:");		display(array, n);		SortArray.bubbleSort(array, n);				System.out.println("After better bubble sort:");		display(array, n);		check(array, n);	} // end testBetterBubbleSort	public static void testMergeSort(Comparable[] array, int n)	{		System.out.println("\nBefore merge sort:");		display(array, n);		SortArray.mergeSort(array, n);				System.out.println("After merge sort:");		display(array, n);		check(array, n);	} // end testMergeSort	public static void testHeapSort(Comparable[] array, int n)	{		System.out.println("\nBefore heap sort:");		display(array, n);		SortArray.heapSort(array, n);				System.out.println("After heap sort:");		display(array, n);		check(array, n);	} // end testHeapSort	public static void display(Object[] array, int n)	{		for (int index = 0; index < n; index++)			System.out.println(array[index]);		System.out.println();	} // end display	public static void copyArray(Object[] copy, Object[] array)	{		for (int index = 0; index < array.length; index++)			copy[index] = array[index];	} // end copyArray		public static void check(Comparable[] array, int n)	{		if (isSorted(array, n))			System.out.println("Method works.\n");		else			System.out.println("Method DOES NOT work!!!!!!!!!!!!!!!!!!!!!!!!\n");			} // end check		public static boolean isSorted(Comparable[] array, int n)	{		boolean sorted = true;		for (int index = 0; sorted && (index < n-1); index++)		{			if (array[index].compareTo(array[index+1]) > 0)				sorted = false;		}				return sorted;	} // end isSorted}  // end Driver