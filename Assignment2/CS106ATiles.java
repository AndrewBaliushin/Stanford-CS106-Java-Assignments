/*
 * File: CS106ATiles.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the CS106ATiles problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class CS106ATiles extends GraphicsProgram {

	/** Amount of space (in pixels) between tiles */
	private static final int TILE_SPACE = 20;

	private static final int TILE_WIDTH = 100;
	private static final int TILE_HEIGH = 50;
	
	private static final String TILE_TEXT = "CS106A";

	private static final int ROWS = 2;
	private static final int COLS = 2;

	public void run() {
		/* You fill this in. */

		setSize((COLS + 2) * (TILE_WIDTH + TILE_SPACE), 
				(ROWS + 2) * (TILE_HEIGH + TILE_SPACE));

		int startPointX = TILE_WIDTH + TILE_SPACE;
		int startPointY = TILE_HEIGH + TILE_SPACE;

		for (int i = 1; i <= ROWS; i++) {

			int yCoord = startPointY * i;

			for (int j = 1; j <= COLS; j++) {

				int xCoord = j * startPointX;

				GRect rect = new GRect(xCoord, yCoord, TILE_WIDTH, TILE_HEIGH);
				add(rect);

				GLabel label = new GLabel(TILE_TEXT);

				int labelX = xCoord
						+ ((TILE_WIDTH - (int) label.getWidth()) / 2);
				int labelY = yCoord + (TILE_HEIGH / 2) + ((int)label.getHeight() / 2);
				add(label, labelX, labelY);

			}

		}

	}
}
