package com.uprm.GameObjects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import com.uprm.Game.GameScreenManager;
import com.uprm.Game.ObjectManager;
import com.uprm.Game.ResultsScreen;
import com.uprm.GameSounds.Sounds;
import com.uprm.TitleScreen.TitleScreenManager;

/**
 * Contains properties for the mines in the game.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class Mine extends GameObject implements CollisionSensitive {

	@Override
	public void collisionAction(MovingBall ball) {
		// TODO Auto-generated method stub

		//Notifies the collision.
		GameObject.setCollisionInProgress(true);
		Sounds.explosion();

		//Removes the objects around the mine.
		ObjectManager.removeObjectsAroundMine(this, ball);
		
		//Subtracts 50 points to the ball's owner
		if(ball.getPlayer().getDistance()-50>0)
		ball.getPlayer().setDistance(ball.getPlayer().getDistance()-50);
		else{
			ball.getPlayer().setDistance(0);
			GameScreenManager.close();
			new ResultsScreen(ball.getPlayer());
			TitleScreenManager.getInstance().run();
		}

		//Collision Finished.
		GameObject.setCollisionInProgress(false);

		//Ball movement finished.
		ball.movementFinished();
	}

	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;

		//Draws the outline of the mine.
		g2.setColor(Color.black);
		Ellipse2D outline = new Ellipse2D.Double(super.getXPosition(), super.getYPosition(), super.getDiameter(), super.getDiameter());
		g2.fill(outline);

		//Draws the surface of the mine.
		g2.setColor(Color.red);
		Ellipse2D surface = new Ellipse2D.Double(super.getXPosition()+(0.1*super.getDiameter()), 
				super.getYPosition()+(0.1*super.getDiameter()), 
				(0.8*super.getDiameter()), (0.8*super.getDiameter()));
		g2.fill(surface);

	}

}
