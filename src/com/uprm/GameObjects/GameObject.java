package com.uprm.GameObjects;
import java.util.Random;
import javax.swing.JPanel;

/**
 * Contains operations related to the different objects in the game.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class GameObject extends JPanel implements Ball{
	private double xPosition, yPosition;
	private Random rng = new Random();
	private final int DIAMETER = 20;
	private static boolean ballSelected=false, collisioninProgress=false;

	/**
	 * Constructs a new GameObject.
	 */
	public GameObject(){
		setSize(DIAMETER,DIAMETER);
		generatePosition();
	}

	@Override
	public void setXPosition(double x) {
		// TODO Auto-generated method stub
		xPosition =x;
	}

	@Override
	public void setYPosition(double y) {
		// TODO Auto-generated method stub
		yPosition =y;
	}

	@Override
	public double getXPosition() {
		// TODO Auto-generated method stub
		return xPosition;
	}

	@Override
	public double getYPosition() {
		// TODO Auto-generated method stub
		return yPosition;
	}

	/**
	 * Returns the diameter of the objects.
	 * @return the diameter of the object
	 */
	public int getDiameter(){
		return DIAMETER;
	}

	/**
	 * Notifies the program that a ball has been selected.
	 * @param b true if a ball has been selected, false otherwise
	 */
	public static void setBallSelected(boolean b){
		ballSelected=b;
	}

	/**
	 * Returns whether a ball has been selected or not.
	 * @return true if a ball has been selected
	 */
	public static boolean isABallSelected(){
		return ballSelected;
	}


	/**
	 * Randomly generates a new position for the object.
	 */
	public void generatePosition(){
		xPosition=20+(540*rng.nextDouble());
		yPosition=20+(540*rng.nextDouble());
	}

	/**
	 * Calculates the distance between two objects.
	 * @param o the object to measure the distance to
	 * @return the distance between the objects
	 */
	public double distanceTo(GameObject o){
		return Math.sqrt((Math.pow((getXPosition()-o.getXPosition()),2)+
				Math.pow((getYPosition()-o.getYPosition()),2)));
	}

	/**
	 * Notifies the program that a collision is in progress.
	 * @param c true if a collision is happening at the moment, false otherwise
	 */
	public static void setCollisionInProgress(boolean c){
		collisioninProgress=c;
	}

	/**
	 * Returns the current state of collisions, if any.
	 * @return true if there is a collision in progress, false otherwise
	 */
	public static boolean isACollisionInProgress(){
		return collisioninProgress;
	}

}
