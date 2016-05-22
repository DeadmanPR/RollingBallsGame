package com.uprm.Game;

import java.util.ArrayList;

import com.uprm.GameObjects.GameObject;
import com.uprm.GameObjects.HappyFace;
import com.uprm.GameObjects.Mine;
import com.uprm.GameObjects.MovingBall;
import com.uprm.GameObjects.Obstacle;

/**
 * Contains operations related to the tracking and handling of collisions.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class CollisionManager {
	private static ArrayList<MovingBall> player1Balls;
	private static ArrayList<MovingBall> player2Balls;
	private static ArrayList<Mine> mines;
	private static ArrayList<HappyFace> happyFaces;
	private static ArrayList<Obstacle> obstacles;
	private static ArrayList<MovingBall> ballsMoving = new ArrayList<MovingBall>();

	/**
	 * Loads the objects present in the game to this class, so that the collisions can be tracked.
	 */
	public static void setCollisionManager(){
		player1Balls = ObjectManager.getPlayer1Balls();
		player2Balls = ObjectManager.getPlayer2Balls();
		mines = ObjectManager.getMines();
		happyFaces = ObjectManager.getHappyFaces();
		obstacles = ObjectManager.getObstacles();
	}

	/**
	 * Adds a moving ball to the collision tracker.
	 * @param mo the moving ball that's moving
	 */
	public static void setCollisionTracker(MovingBall mo){
		ballsMoving.add(mo);
	}

	/**
	 * Goes through all the moving balls and executes their move() method, in order to make them move.
	 */
	public static void move(){
		for(int i=0; i<ballsMoving.size(); i++)
			ballsMoving.get(i).move();
	}

	/**
	 * Removes the MovingBalls (that finished moving) from the collision tracker.
	 */
	public static void movementFinished(){
		for(int i =0 ; i<ballsMoving.size() ; i++)
			ballsMoving.remove(i);
	}

	/**
	 * Tracks the collisions between the balls that are moving and the balls that aren't moving.
	 * @return the object that the moving ball hit (if any), returns null if it hasn't hit any object
	 */
	public static GameObject trackCollisions(){
		//Compares the position of the moving ball with every ball that belongs to Player 1.
		for(int i=0; i < player1Balls.size() ; i++){
			for(int j = 0 ; j < ballsMoving.size() ; j++){
				if(!ballsMoving.get(j).equals(player1Balls.get(i)))
					if(ballsMoving.get(j).distanceTo(player1Balls.get(i)) < ballsMoving.get(j).getDiameter())
						return player1Balls.get(i);
			}
		}

		//Compares the position of the moving ball with every ball that belongs to Player 2.
		for(int i=0; i < player2Balls.size() ; i++){
			for(int j = 0 ; j < ballsMoving.size() ; j++){
				if(!ballsMoving.get(j).equals(player2Balls.get(i)))
					if(ballsMoving.get(j).distanceTo(player2Balls.get(i)) < ballsMoving.get(j).getDiameter())
						return player2Balls.get(i);
			}
		}
		//Compares the position of the moving ball with every mine.
		for(int i=0; i < mines.size() ; i++){
			for(int j = 0 ; j < ballsMoving.size() ; j++){
				if(ballsMoving.get(j).distanceTo(mines.get(i)) < ballsMoving.get(j).getDiameter())
					return mines.get(i);
			}
		}

		//Compares the position of the moving ball with every Happy Face.
		for(int i=0; i<happyFaces.size(); i++)
			for(int j = 0 ; j < ballsMoving.size() ; j++){
				if(ballsMoving.get(j).distanceTo(happyFaces.get(i)) < ballsMoving.get(j).getDiameter())
					return happyFaces.get(i);
			}

		//Compares the position of the moving ball with every obstacle.
		for(int i=0; i<obstacles.size(); i++)
			for(int j = 0 ; j < ballsMoving.size() ; j++){
				if(ballsMoving.get(j).distanceTo(obstacles.get(i)) < ballsMoving.get(j).getDiameter())
					return obstacles.get(i);
			}

		return null;
	}

}
