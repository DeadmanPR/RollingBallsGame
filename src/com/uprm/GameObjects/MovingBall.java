package com.uprm.GameObjects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import com.uprm.Game.CollisionManager;
import com.uprm.Game.GameScreenManager;
import com.uprm.Game.ObjectManager;
import com.uprm.Game.ResultsScreen;
import com.uprm.Game.ShootingOptions;
import com.uprm.Game.TurnController;
import com.uprm.GameSounds.Sounds;
import com.uprm.ProfileTools.Player;
import com.uprm.TitleScreen.TitleScreenManager;

/**
 * Contains several properties of the moving balls in the game.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class MovingBall extends GameObject implements CollisionSensitive{
	private Color color;
	private Player player;
	private boolean selected = false, moving=false;
	private int outlineMarker=0;
	private static Integer angle;
	private static Integer distance;
	private ShootingOptions so;
	private double dx=0;
	private double dy=0;

	/**
	 * Constructs a MovingBall 
	 * @param color the ball's color
	 */
	public MovingBall(Color color, Player player){
		this.color=color;
		this.player=player;
		angle=0;
		distance=0;


	}

	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;

		//Draws the ball.
		g2.setColor(color);
		Ellipse2D circle = new Ellipse2D.Double(super.getXPosition()+dx, super.getYPosition()-dy, super.getDiameter(), super.getDiameter());
		g2.fill(circle);

		//Draws the ball's outline when selected.
		g2.setColor(Color.black);
		Ellipse2D outline = new Ellipse2D.Double(super.getXPosition()+dx, super.getYPosition()-dy, outlineMarker, outlineMarker);
		g2.draw(outline);


		update();
	}

	public void update(){
		if(moving){

			//Updates the position and the distance of the ball.
			super.setXPosition(super.getXPosition()+dx);
			super.setYPosition(super.getYPosition()-dy);
			distance--;
			player.setDistance(player.getDistance()-1);

			//Checks if the distance ran out.
			if(distance==0)
				movementFinished();

			//If the total distance ran out, the game ends.
			if(player.getDistance()==0){
				GameScreenManager.close();
				new ResultsScreen(player);
				TitleScreenManager.getInstance().run();
			}

			//Checks the angle at which the ball hits the horizontal bounds, and reflects the angle accordingly.
			if(super.getXPosition() < 15 || super.getXPosition() > 585){
				if(angle<90 && angle>0)
					angle = 180-(90-angle);
				else
					if(angle<=359 && angle>270)
						angle=180+(360-angle);
					else
						if(angle>90 && angle<180)
							angle=180-angle;
						else
							if(angle>180 && angle<270)
								angle=360-(angle-180);
							else
								if(angle==0)
									angle=180;
								else
									if(angle==180)
										angle=0;
									else
										if(angle==90)
											angle=270;
										else
											if(angle==270)
												angle=90;
				setMovement();
			}

			//Checks the angle at which the ball hits the vertical bounds, and reflects the angle accordingly.
			if(super.getYPosition() < 20 || super.getYPosition() > 580){
				if(angle<90 && angle>0)
					angle = 360-angle;
				else
					if(angle<=359 && angle>270)
						angle=180-angle;
					else
						if(angle>90 && angle<180)
							angle=180-(90-angle);
						else
							if(angle>180 && angle<270)
								angle=angle-90;
							else
								if(angle==0)
									angle=180;
								else
									if(angle==180)
										angle=0;
									else
										if(angle==90)
											angle=270;
										else
											if(angle==270)
												angle=90;
				setMovement();
			}

			//Tracks the collisions between this ball and other objects, and if so, executes the corresponding collisionAction method.
			if(CollisionManager.trackCollisions()!=null && !GameObject.isACollisionInProgress()){
				if(CollisionManager.trackCollisions() instanceof MovingBall){
					MovingBall ballHit = (MovingBall)CollisionManager.trackCollisions();
					ballHit.collisionAction(this);
				}

				if(CollisionManager.trackCollisions() instanceof Obstacle){
					Obstacle ballHit = (Obstacle)CollisionManager.trackCollisions();
					ballHit.collisionAction(this);
				}

				if(CollisionManager.trackCollisions() instanceof HappyFace){
					HappyFace ballHit = (HappyFace)CollisionManager.trackCollisions();
					ballHit.collisionAction(this);
				}

				if(CollisionManager.trackCollisions() instanceof Mine){
					Mine ballHit = (Mine)CollisionManager.trackCollisions();
					ballHit.collisionAction(this);
				}
			}
		}

		//Repaints the panel.
		GameScreenManager.getFrame().repaint();

		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Action when the ball is clicked.
	 */
	public void clicked(){
		//Only does the corresponding action when the player clicks their ball on their turn.
		if(TurnController.whoseTurnIsIt().equals(player) && !GameObject.isABallSelected()){
			GameObject.setBallSelected(true);
			so = new ShootingOptions(player);
			selected=true;
			outlineMarker=super.getDiameter();
			CollisionManager.setCollisionTracker(this);
		}
	}

	/**
	 * Ends the movement of the ball.
	 */
	public void movementFinished(){
		angle=0;
		distance=0;
		dx=0;
		dy=0;
		outlineMarker=0;
		selected=false;
		moving=false;
		GameObject.setBallSelected(false);
		CollisionManager.movementFinished();
	}

	/**
	 * Sets the movement of the ball, according to its angle.
	 */
	public void setMovement(){
		dx = Math.cos(angle*(Math.PI/180));
		dy = Math.sin(angle*(Math.PI/180));
	}

	/**
	 * Sets everything for the movement of the ball.
	 */
	public void move(){
		setMovement();
		moving=true;
	}

	/**
	 * Checks if the ball is moving.
	 * @return true if the ball is moving, false otherwise
	 */
	public boolean isMoving(){
		return moving;
	}

	/**
	 * Sets the movement direction for the ball.
	 * @param angleSelected the angle at which the ball will move
	 * @param distanceSelected the distance that the ball will travel
	 */
	public static void setMovementDirection(int angleSelected, int distanceSelected){
		angle=angleSelected;
		distance = distanceSelected;
	}

	/**
	 * Returns the angle at which the ball is moving.
	 * @return the angle at which the ball is moving
	 */
	public int getAngle(){
		return angle;
	}

	/**
	 * Returns the distance left for the ball.
	 * @return the distance left
	 */
	public int getDistance(){
		return distance;
	}

	/**
	 * Sets the angle for the ball.
	 * @param angle the angle to set
	 */
	public void setAngle(int angle){
		this.angle=angle;
	}

	/**
	 * Sets the distance for the ball.
	 * @param distance the distance to set
	 */
	public void setDistance(int distance){
		this.distance=distance;
	}

	/**
	 * Returns the ball's owner.
	 * @return the ball's owner
	 */
	public Player getPlayer(){
		return player;
	}

	@Override
	public void collisionAction(MovingBall ball) {
		// TODO Auto-generated method stub
		GameObject.setCollisionInProgress(true);

		//When the ball hitting this ball is from the other player, executes the proper action.
		if(!ball.getPlayer().equals(this.player)){
			Sounds.hitOpponentBall();
			ObjectManager.remove(this);

			//Checks that the distance isn't lower than 0.
			if(player.getDistance()-100 > 0){
				player.setDistance(player.getDistance()-100);
				ball.getPlayer().setDistance(ball.getPlayer().getDistance()+100);
			}
			else
			{
				//Ends the game if the distance reached 0.
				ball.setDistance(player.getDistance());
				player.setDistance(0);
				GameScreenManager.close();
				new ResultsScreen(player);
				TitleScreenManager.getInstance().run();
			}
		}
		//		else{
		//			if(ball.getAngle()<90 && ball.getAngle()>0){
		//				if(ball.getXPosition()<super.getXPosition()){
		//					ball.setAngle(180-ball.getAngle());
		//					setAngle(90-ball.getAngle());
		//					}}
		//				
		//							this.setDistance(ball.getDistance()/2);
		//							ball.setDistance(ball.getDistance()/2);
		//							System.out.println(getDistance());
		//							CollisionManager.setCollisionTracker(this);
		//							CollisionManager.move();
		//			}
		GameObject.setCollisionInProgress(false);


	}

}
