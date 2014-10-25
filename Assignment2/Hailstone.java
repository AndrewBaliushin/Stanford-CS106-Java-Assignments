/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */

		//window size
		setSize(700, 400);
		
		print("Enter the start point:");
		int value = readInt();

		int stepCounter = 0;

		while (value > 1) {
			
			StringBuilder sr = new StringBuilder();
			
			sr.append(++stepCounter + ") ");
			sr.append(value + " is ");
			
			if ((value % 2) == 0) {
				sr.append("even. "+ value + " / 2 = ");
				value = value / 2;				
			} else {
				sr.append("odd. (" + value + " * 3) + 1 = ");
				value = (value * 3) +1;
			}
			
			sr.append(value);
			
			println(sr.toString());
		}
	}
}
