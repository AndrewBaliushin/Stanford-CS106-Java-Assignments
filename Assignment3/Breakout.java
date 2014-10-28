/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/**
	 * Width and height of application window in pixels. On some platforms these
	 * may NOT actually be the dimensions of the graphics canvas.
	 */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/**
	 * Dimensions of game board. On some platforms these may NOT actually be the
	 * dimensions of the graphics canvas.
	 */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1)
			* BRICK_SEP)
			/ NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 2;

	/** For bricks */
	private static final Color[] COLORS = { Color.RED, Color.ORANGE,
			Color.YELLOW, Color.GREEN, Color.CYAN };
	
	private PaddleControl paddleCtrl;
	private BallControl ballConroll;
	
	private int lifes = NTURNS;
	
	private int brickCounter = 0;
	
	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */

		setSize(WIDTH, HEIGHT);

		setBrickField();
		
		paddleCtrl = new PaddleControl(this, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_Y_OFFSET);
		ballConroll = new BallControl(this, BALL_RADIUS);
			
		
		addMouseListeners();

		ballConroll.throwBall();
		
		//main loop
		while (lifes > 0 && brickCounter > 0) {
			ballConroll.moveBall();
			pause(34);
		}
		
		if (lifes <= 0) {
			gameOver();
		} else {
			youWin();
		}

	}

	private void setBrickField() {

		int startPositionY = BRICK_Y_OFFSET;

		for (int i = 0; i < NBRICK_ROWS; i++) {
			
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				GRect brick = new GRect((BRICK_SEP / 2) + j * (BRICK_WIDTH + BRICK_SEP),
						startPositionY + i * (BRICK_HEIGHT + BRICK_SEP),
						BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setColor(COLORS[i/2]);
				brick.setFillColor(COLORS[i/2]);
				add(brick);
				brickCounter++;
			}
		}
	} //end setBrickField()
	
	public void mouseMoved(MouseEvent e) {
		movePaddle(e);
	}

	public void mouseExited(MouseEvent e) {
		movePaddle(e);
	}
	
	private void movePaddle(MouseEvent e) {		
		int x = 0;
		boolean movementOver = false;
		while (x++ < WIDTH && !movementOver) {
			//x++ is limiting counter
			movementOver = paddleCtrl.movePaddle(e.getX());
		}
	}
	
	public void ballUnderFloorAction() {
		
		if (--lifes <= 0) {
			gameOver();
			return;
		}
		
		ballConroll.drawBall();
		ballConroll.throwBall();
		
	}
	
	public void gameOver() {
		GLabel label = new GLabel("Game Over");
		label.setColor(Color.RED);
		label.setFont(new Font("Serif", Font.BOLD, 22));
		
		double xPos = (getWidth() - label.getWidth()) / 2;
		
		add(label, xPos, (getWidth() / 1.5));
	}
	
	public void youWin () {
		GLabel label = new GLabel("You Win!");
		label.setColor(Color.RED);
		label.setFont(new Font("Serif", Font.BOLD, 22));
		
		double xPos = (getWidth() - label.getWidth()) / 2;
		
		add(label, xPos, (getWidth() / 1.5));
	}
	
	public void removeBrick(GObject brick) {
		remove(brick);
		brickCounter--;
	}

}
