/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import java.util.Scanner;

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		
		println("Input A:");
		double a = readDouble();
		println("Input B:");
		double b = readDouble();
		
		double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		println("C is equals: " + c);
	}
}
