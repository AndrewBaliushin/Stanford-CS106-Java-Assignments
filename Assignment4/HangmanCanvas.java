/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;
import java.awt.Font; 

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	
	/* Coords between head and rope */
	private int xStartPosition;
	private int yStartPosition;
	
	/* ref for label with word */
	private GLabel wordLabel;
	
	/* Counter for drawBodyParts. Show how many have been already drawn.*/
	private int bodyPartsCounter = 0;

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		removeAll();
		displayWord("");
		bodyPartsCounter = 0;
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* Assign start point and draw static scaffold */
		xStartPosition = getWidth() / 2;
		yStartPosition = getHeight() / 8;
		drawScaffold();
		
		drawWord(word);
		
		
		
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
		drawBodyParts();
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	
	
	private void drawScaffold() {
		int xStart = xStartPosition;
		int yStart = yStartPosition;
		
		int nextXpoint = xStart;
		int nextYpoint = yStart - ROPE_LENGTH;
		
		GLine rope = new GLine(xStart, yStart, nextXpoint, nextYpoint);
		add(rope);

		xStart = nextXpoint;
		yStart = nextYpoint;
		
		nextXpoint = xStart - BEAM_LENGTH;
		nextYpoint = yStart;
		
		GLine beam = new GLine(xStart, yStart, nextXpoint, nextYpoint);
		add(beam);
		
		xStart = nextXpoint;
		yStart = nextYpoint;
		
		nextXpoint = xStart;
		nextYpoint = nextYpoint + SCAFFOLD_HEIGHT;
		
		GLine scaffold = new GLine(xStart, yStart, nextXpoint, nextYpoint);
		add(scaffold);
	}
	
	
	
	/**
	 * Straightforward method to draw body parts. 
	 * Uses counter bodyPartsCounter;
	 */
	private void drawBodyParts() {
		
		int x0, y0; //helper coords
		
		switch (++bodyPartsCounter) {
		case 1:
			//head
			GOval head = new GOval((xStartPosition - HEAD_RADIUS), yStartPosition,
					HEAD_RADIUS * 2, HEAD_RADIUS * 2);
			add(head);
			break;
			
		case 2:
			//body
			x0 = xStartPosition;
			y0 = yStartPosition + HEAD_RADIUS * 2;
			GLine body = new GLine(x0, y0, x0, y0 + BODY_LENGTH);
			add(body);
			break;
			
		case 3:
			//left arm
			x0 = xStartPosition;
			y0 = yStartPosition + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
			GLine leftArmUpper = new GLine(x0, y0, x0 - UPPER_ARM_LENGTH, y0);
			GLine leftArmLower = new GLine(x0 - UPPER_ARM_LENGTH, y0,
					x0 - UPPER_ARM_LENGTH, y0 + LOWER_ARM_LENGTH);
			add(leftArmUpper);
			add(leftArmLower);
			break;
			
		case 4:
			//right arm
			x0 = xStartPosition;
			y0 = yStartPosition + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
			GLine rightArmUpper = new GLine(x0, y0, x0 + UPPER_ARM_LENGTH, y0);
			GLine rightArmLower = new GLine(x0 + UPPER_ARM_LENGTH, y0,
					x0 + UPPER_ARM_LENGTH, y0 + LOWER_ARM_LENGTH);
			add(rightArmLower);
			add(rightArmUpper);
			break;
			
		case 5: 
			//left leg
			x0 = xStartPosition;
			y0 = yStartPosition + HEAD_RADIUS * 2 + BODY_LENGTH;
			GLine leftHip = new GLine(x0, y0, x0 - HIP_WIDTH, y0);
			GLine leftLeg = new GLine(x0 - HIP_WIDTH, y0, x0 - HIP_WIDTH, y0 + LEG_LENGTH);
			GLine leftFoot = new GLine(x0 - HIP_WIDTH, y0 + LEG_LENGTH,
					x0 - HIP_WIDTH - FOOT_LENGTH, y0 + LEG_LENGTH);
			add(leftHip);
			add(leftLeg);
			add(leftFoot);
			break;
			
		case 6: 
			//right leg
			x0 = xStartPosition;
			y0 = yStartPosition + HEAD_RADIUS * 2 + BODY_LENGTH;
			GLine rightHip = new GLine(x0, y0, x0 + HIP_WIDTH, y0);
			GLine rightLeg = new GLine(x0 + HIP_WIDTH, y0, x0 + HIP_WIDTH, y0 + LEG_LENGTH);
			GLine rightFoot = new GLine(x0 + HIP_WIDTH, y0 + LEG_LENGTH,
					x0 + HIP_WIDTH + FOOT_LENGTH, y0 + LEG_LENGTH);
			add(rightHip);
			add(rightLeg);
			add(rightFoot);
			break;

		default:
			break;
		}
	} //end of drawBodyParts
	
	private void drawWord(String str) {
		if (wordLabel != null) {
			remove(wordLabel);
		}
		
		wordLabel = new GLabel(str);
		wordLabel.setColor(Color.BLACK);
		wordLabel.setFont(new Font("Serif", Font.PLAIN, 44));
		add(wordLabel, ((getWidth() - wordLabel.getWidth()) / 2), ((getHeight() * 0.8) + wordLabel.getHeight()));
	}

}
