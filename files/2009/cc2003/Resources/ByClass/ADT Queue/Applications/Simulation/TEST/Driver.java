import java.util.*;public class Driver {	public static void main(String[] args) 	{		WaitLine customerLine = new WaitLine();		customerLine.simulate(20, 0.5, 5); // Seg. 22.9		customerLine.displayResults();		System.out.println("\n\nDone.");	}  // end main}  // end Driver