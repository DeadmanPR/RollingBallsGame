package com.uprm.GameObjects;

/**
 * Used for objects that are sensitive (do something) when collided with.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public interface CollisionSensitive {
	/**
	 * Sets what happens when this object gets hit by a MovingBall
	 * @param ball the ball that collided
	 */
	void collisionAction (MovingBall ball);
}
