/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private final static int SENTINEL_VALUE = 0;
	
	private static int largest = 0;
	private static int smallest = Integer.MAX_VALUE;
	
	public void run() {
		/* You fill this in */

		while (true) {
			print("Input int:");
			int current = readInt();

			if (current == 0) {
				println("Largest: " + largest);
				println("Smallest: " + smallest);

				largest = 0;
				smallest = Integer.MAX_VALUE;

				continue;

			}
			if (current > largest) {
				largest = current;
			}
			if (current < smallest) {
				smallest = current;
			}

		}

	}
}
