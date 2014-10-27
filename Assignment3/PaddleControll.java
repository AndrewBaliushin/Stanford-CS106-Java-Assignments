import java.awt.Color;

import acm.graphics.GRect;

/**
 * Creates and controls paddle.
 * 
 * @author Andrew Baliushin
 *
 */
public class PaddleControll {

	private Breakout gameObj;

	private int pWidth;
	private int pHeight;
	private int pYoffsetBottom;

	private GRect paddle;

	/**
	 * Creates and move paddle.
	 * 
	 * @param gameObj -- ref to main game object
	 * @param pWidth
	 * @param pHeight
	 * @param pYoffset
	 */
	public PaddleControll(Breakout gameObj, int pWidth, int pHeight,
			int pYoffset) {
		this.pWidth = pWidth;
		this.pHeight = pHeight;
		this.pYoffsetBottom = gameObj.getHeight() - pYoffset;
		this.gameObj = gameObj;

		drawPaddle((gameObj.getWidth() - pWidth) / 2);

	}

	public void drawPaddle(int xCoord) {
		paddle = new GRect(xCoord, pYoffsetBottom, pWidth, pHeight);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		paddle.setFillColor(Color.BLACK);
		gameObj.add(paddle);
	}

	/**
	 * Moves paddle towards destination by one step at the time.
	 * @param toX -- destination
	 * @return -- true if reached toX
	 */
	public boolean movePaddle(int toX) {

		double leftBorder = paddle.getX();
		double middle = leftBorder + (pWidth / 2);
		double rightBorder = leftBorder + pWidth;

		if (middle > toX && leftBorder > 0) {
			paddle.move(-1, 0);
		} else if (middle < toX && rightBorder < gameObj.getWidth()) {
			paddle.move(1, 0);
		}

		return (paddle.getX() == toX || paddle.getX() == 0 || paddle.getX() == gameObj
				.getWidth());

	}

	public int getX() {
		return (int) paddle.getX();
	}

}
