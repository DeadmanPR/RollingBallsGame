package com.uprm.GameObjects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import com.uprm.GameSounds.Sounds;

/**
 * Contains properties for the obstacles in the game.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class Obstacle extends FixedObject implements CollisionSensitive{

	@Override
	public void collisionAction(MovingBall ball) {
		// TODO Auto-generated method stub
		GameObject.setCollisionInProgress(true);
		Sounds.hitObstacle();

		//Checks the angle at which the ball is coming from, and reflects the direction accordingly.
		if(ball.getAngle()<90 && ball.getAngle()>0){
			if(ball.getXPosition()<super.getXPosition())
				ball.setAngle(180-ball.getAngle());
			else
				ball.setAngle(360-ball.getAngle());
		}else
			if(ball.getAngle()<180 && ball.getAngle()>90){
				if (ball.getXPosition()>super.getXPosition())
					ball.setAngle(180-ball.getAngle());
				else
					ball.setAngle(360-ball.getAngle());
			}else
				if(ball.getAngle()<270 && ball.getAngle()>180){
					if(ball.getXPosition()<super.getXPosition())
						ball.setAngle(360-ball.getAngle());
					else
						ball.setAngle(540-ball.getAngle());
				}else
					if(ball.getAngle()<=359 && ball.getAngle()>270){
						if(ball.getXPosition()<super.getXPosition())
							ball.setAngle(540-ball.getAngle());
						else
							ball.setAngle(360-ball.getAngle());
					}else
						if(ball.getAngle()==0)
							ball.setAngle(180);
						else
							if(ball.getAngle()==180)
								ball.setAngle(0);
							else
								if(ball.getAngle()==90)
									ball.setAngle(270);
								else
									if(ball.getAngle()==270)
										ball.setAngle(90);

		//Updates the movement direction.
		ball.setMovement();
		GameObject.setCollisionInProgress(false);
	}

	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;

		//Draws the obstacle.
		g2.setColor(Color.black);
		Ellipse2D obstacle = new Ellipse2D.Double(super.getXPosition(), super.getYPosition(), super.getDiameter(), super.getDiameter());
		g2.fill(obstacle);
	}

}
