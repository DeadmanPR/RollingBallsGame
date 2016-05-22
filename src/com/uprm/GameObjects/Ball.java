package com.uprm.GameObjects;

/**
 * Contains several properties related to the different objects (balls) in the game.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public interface Ball {
	/**
	 * Sets the X position of the ball.
	 * @param x the position in the x axis
	 */
	void setXPosition(double x);

	/**
	 * Sets the Y position of the ball.
	 * @param y the position in the y axis
	 */
	void setYPosition(double y);

	/**
	 * Returns the X position of the ball.
	 * @return the current position in the x axis
	 */
	double getXPosition();

	/**
	 * Returns the Y position of the ball.
	 * @return the current position in the y axis
	 */
	double getYPosition();


}
