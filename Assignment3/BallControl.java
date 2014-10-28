import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import acm.graphics.GObject;
import acm.graphics.GOval;

/**
 * Creates, controls and detect collisions on ball.
 * 
 * @author Andrew Baliushin
 *
 */
public class BallControl {
	
	private static final int SPEED = 10;

	private Breakout gameObj;

	private int diameter;

	private GOval ball;
	
	/* ball vertical and horizontal speed */
	private double dx = 0;
	private double dy = 0;

	public BallControl(Breakout gameObj, int radius) {
		this.diameter = 2 * radius;
		this.gameObj = gameObj;

		drawBall();

	}
	
	public void drawBall() {
		ball = new GOval((gameObj.getWidth() / 2), (gameObj.getHeight() / 2),
				diameter, diameter);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		ball.setFillColor(Color.BLACK);
		gameObj.add(ball);
	}
	
	/**
	 * Sets random velocity.
	 */
	public void throwBall () {
		Random rd = new Random();
		dx = rd.nextDouble();
		dy = 1;
		
	}
	
	/**
	 * Make one step and check for collisions.
	 */
	public void moveBall() {
		for (int i = 0; i < SPEED; i++) {
			ball.move(dx, dy);
			collisionCheck();
		}
	}
	
	private void collisionCheck() {

		Map<Double, Double> corners = new HashMap<Double, Double>();
		corners.put(ball.getX(), ball.getY()); 						//left-top
		corners.put(ball.getX(), ball.getY() + diameter);			//left-bottom
		corners.put(ball.getX() + diameter, ball.getY());			//right-top
		corners.put(ball.getX() + diameter, ball.getY() + diameter);//right-bottom
		
		for (Map.Entry<Double, Double> corner : corners.entrySet()) {
			
			//bounce of vertical walls
			if ((corner.getKey() <= 0) || (corner.getKey() >= gameObj.getWidth())) {
				invertXspeed();
				break;
			}
			
			//bounce from top
			if ((corner.getValue() <= 0)) {
				invertYspeed();
				break;
			}
			
			//ball hit bottom
			if ((corner.getValue() >= gameObj.getHeight())) {
				gameObj.remove(ball);
				gameObj.ballUnderFloorAction();
				break;
			}
			
			GObject obj = gameObj.getElementAt(corner.getKey(), corner.getValue());
			if (obj != null) {
				/* Check if collision happened in upper half. */
				if (corner.getValue() < (gameObj.getHeight() / 2)) {
					//it's a brick
					gameObj.removeBrick(obj);
				} 
				invertYspeed();
				break;
			}
		}
	}
	
	private void invertXspeed() {
		dx = -dx;
	}
	
	private void invertYspeed() {
		dy = -dy;
	}
}
