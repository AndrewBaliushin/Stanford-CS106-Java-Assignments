/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	private static int rowStartPositionX = 0;
	private static int rowStartPositionY = (BRICK_HEIGHT * BRICKS_IN_BASE);
	
	public void run() {

		//sets size of window
		setSize((BRICK_WIDTH * (BRICKS_IN_BASE + 1)),
				(BRICK_HEIGHT * (BRICKS_IN_BASE + 2)));

		for (int i = BRICKS_IN_BASE; i > 0; i--) {

			rowStartPositionX += BRICK_WIDTH / 2;

			for (int j = 0; j < i; j++) {
				GRect brick = new GRect(
						(rowStartPositionX + (j * BRICK_WIDTH)),
						rowStartPositionY, BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);
			} //end of j

			rowStartPositionY -= BRICK_HEIGHT;

		} //end of i

	}

}

