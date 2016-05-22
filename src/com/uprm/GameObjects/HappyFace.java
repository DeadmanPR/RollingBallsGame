package com.uprm.GameObjects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import com.uprm.GameSounds.Sounds;

/**
 * Contains operations related to the Happy Faces that are in the game.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class HappyFace extends FixedObject implements CollisionSensitive{

	@Override
	public void collisionAction(MovingBall ball) {
		// TODO Auto-generated method stub

		//Notifies that a collision is in progress.
		GameObject.setCollisionInProgress(true);
		Sounds.hitHappyFace();

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

		//Adds 50 points to the ball's owner
		ball.getPlayer().setDistance(ball.getPlayer().getDistance()+50);

		//Moves the ball
		ball.move();
		GameObject.setCollisionInProgress(false);
	}


	public void paintComponent(Graphics g){

		//Draws the outline of the happy face.
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		Ellipse2D outline = new Ellipse2D.Double(super.getXPosition(), super.getYPosition(), super.getDiameter(), super.getDiameter());
		g2.fill(outline);

		//Draws the inner yellow circle.
		g2.setColor(Color.yellow);
		Ellipse2D surface = new Ellipse2D.Double(super.getXPosition()+(0.1*super.getDiameter()), 
				super.getYPosition()+(0.1*super.getDiameter()), 
				(0.8*super.getDiameter()), (0.8*super.getDiameter()));
		g2.fill(surface);

		//Draws the smile on the happy face.
		g2.setColor(Color.black);
		Ellipse2D surface2 = new Ellipse2D.Double(super.getXPosition()+(0.15*super.getDiameter()), 
				super.getYPosition()+(0.15*super.getDiameter()), 
				(0.7*super.getDiameter()), (0.65*super.getDiameter()));
		g2.fill(surface2);

		g2.setColor(Color.yellow);
		Ellipse2D surface3 = new Ellipse2D.Double(super.getXPosition()+(0.15*super.getDiameter()), 
				super.getYPosition()+(0.15*super.getDiameter()), 
				(0.7*super.getDiameter()), (0.55*super.getDiameter()));
		g2.fill(surface3);

		//Draws the eyes of the happy face.
		g2.setColor(Color.black);
		Ellipse2D surface4 = new Ellipse2D.Double(super.getXPosition()+(0.25*super.getDiameter()), 
				super.getYPosition()+(0.25*super.getDiameter()), 
				(0.2*super.getDiameter()),(0.2*super.getDiameter()));
		Ellipse2D surface5 = new Ellipse2D.Double(super.getXPosition()+(0.5*super.getDiameter()),
				super.getYPosition()+(0.25*super.getDiameter()), 
				(0.2*super.getDiameter()),(0.2*super.getDiameter()));
		g2.fill(surface4);
		g2.fill(surface5);

	}



}
