/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {
	
	private final static int WIN_SIZE = 216;
	
	private final static double BIG_C_SIZE = 72;
	private final static double MID_C_SIZE = BIG_C_SIZE * 0.65;
	private final static double SMALL_C_SIZE = BIG_C_SIZE * 0.3;
	
	
	
	public void run() {
		/* You fill this in. */
		
		setSize(WIN_SIZE, WIN_SIZE);
		//setBackground(Color.black);

		GOval bigRedOne = new GOval(((getWidth() / 2) - (BIG_C_SIZE / 2)),
				((getHeight() / 2) - (BIG_C_SIZE / 2)), BIG_C_SIZE, BIG_C_SIZE);
		bigRedOne.setFilled(true);
		bigRedOne.setFillColor(Color.red);
		add(bigRedOne);

		GOval whiteOne = new GOval(((getWidth() / 2) - (MID_C_SIZE / 2)),
				((getHeight() / 2) - (MID_C_SIZE / 2)), MID_C_SIZE, MID_C_SIZE);
		whiteOne.setFilled(true);
		whiteOne.setFillColor(Color.white);
		add(whiteOne);
		
		GOval smallOne = new GOval(((getWidth() / 2) - (SMALL_C_SIZE / 2)),
				((getHeight() / 2) - (SMALL_C_SIZE / 2)), SMALL_C_SIZE, SMALL_C_SIZE);
		smallOne.setFilled(true);
		smallOne.setFillColor(Color.red);
		add(smallOne);
		
	}
}
