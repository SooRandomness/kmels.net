import java.util.*;public class Driver {	public static void main(String[] args) 	{		testQueueOperations();		System.out.println("\n\nDone.");	}  // end main	public static void testQueueOperations()	{		System.out.println("Create a queue: ");		QueueInterface myQueue = new LinkedQueue();		System.out.println("\n\nisEmpty() returns " + myQueue.isEmpty() + "\n");				System.out.println("Add to queue to get\n" +		                   "joe jess jim jill jane jerry\n");		myQueue.enqueue("joe");		myQueue.enqueue("jess");		myQueue.enqueue("jim");		myQueue.enqueue("jill");		myQueue.enqueue("jane");		myQueue.enqueue("jerry");		System.out.println("\nisEmpty() returns " + myQueue.isEmpty() + "\n");		System.out.println("\n\nTesting getFront and dequeue:\n");		while (!myQueue.isEmpty())		{			String front = (String) myQueue.getFront();			System.out.println(front + " is at the front of the queue.");						front = (String) myQueue.dequeue();			System.out.println(front + " is removed from the front of the queue.\n");		} // end while		System.out.print("\nThe queue should be empty: ");		System.out.println("isEmpty() returns " + myQueue.isEmpty() + "\n\n");		System.out.println("Add to queue to get\n" +		                   "joe jess jim\n");		myQueue.enqueue("joe");		myQueue.enqueue("jess");		myQueue.enqueue("jim");				System.out.println("\nTesting clear:\n");		myQueue.clear();					System.out.println("\nisEmpty() returns " + myQueue.isEmpty() + "\n\n");		System.out.println("Add to queue to get\n" +		                   "joe jess jim\n");		myQueue.enqueue("joe");		myQueue.enqueue("jess");		myQueue.enqueue("jim");		while (!myQueue.isEmpty())		{			String front = (String) myQueue.getFront();			System.out.println(front + " is at the front of the queue.");						front = (String) myQueue.dequeue();			System.out.println(front + " is removed from the front of the queue.\n");		} // end while		System.out.print("\n\nThe queue should be empty: ");		System.out.println("isEmpty() returns " + myQueue.isEmpty() + "\n");				System.out.println("myQueue.getFront() returns " +  myQueue.getFront());		System.out.println("myQueue.dequeue() returns " +  myQueue.dequeue() + "\n");			} // end testQueueOperations}  // end Driver