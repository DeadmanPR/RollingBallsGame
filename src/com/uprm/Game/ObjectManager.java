package com.uprm.Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import com.uprm.GameObjects.GameObject;
import com.uprm.GameObjects.HappyFace;
import com.uprm.GameObjects.Mine;
import com.uprm.GameObjects.MovingBall;
import com.uprm.GameObjects.Obstacle;
import com.uprm.ProfileTools.ProfileManagement;
import com.uprm.TitleScreen.TitleScreenManager;

/**
 * Sets the objects inside the game area.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class ObjectManager {
	private static Random rng = new Random();
	private static ArrayList<MovingBall> player1Balls;
	private static ArrayList<MovingBall> player2Balls;
	private static ArrayList<Obstacle> obstacles;
	private static ArrayList<HappyFace> happyFaces;
	private static ArrayList<Mine> mines;
	private static boolean removedByMine = false;

	/**
	 * Sets the number of objects that are going to be present in the game.
	 * @param numberOfPlayer1Balls the number of balls Player 1 chooses
	 * @param numberOfPlayer2Balls the number of balls Player 2 chooses
	 */
	public static void setGameObjects(int numberOfPlayer1Balls, int numberOfPlayer2Balls){
		//Sets the number of Mines, Happy Faces, and Obstacles to a random number between 5 and 10.
		int numberOfMines = 5+rng.nextInt(6);
		int numberOfHappyFaces = 5+rng.nextInt(6);
		int numberOfObstacles = 5+rng.nextInt(6);
		int index;
		int numberOfObjects = numberOfPlayer1Balls + numberOfPlayer2Balls +
				numberOfHappyFaces + numberOfMines + numberOfObstacles;

		player1Balls = new ArrayList<MovingBall>(numberOfPlayer1Balls);
		player2Balls = new ArrayList<MovingBall>(numberOfPlayer2Balls);
		obstacles = new ArrayList<Obstacle>(numberOfObstacles);
		happyFaces = new ArrayList<HappyFace>(numberOfHappyFaces);
		mines = new ArrayList<Mine>(numberOfMines);

		//Creates a GameObject array list.
		ArrayList<GameObject> objects = new ArrayList<GameObject>();

		//Adds the desired amount of Moving Balls, Mines, etc. to the array.
		for(int i=0; i<numberOfObjects; i++){
			if(i<numberOfPlayer1Balls)	
				objects.add(new MovingBall(Color.green, ProfileManagement.getCurrentPlayer1()));
			else
				if(i<numberOfPlayer1Balls+numberOfPlayer2Balls)
					objects.add(new MovingBall(new Color(74,134,232), ProfileManagement.getCurrentPlayer2()));
				else
					if(i<numberOfPlayer1Balls+numberOfPlayer2Balls+numberOfMines)
						objects.add(new Mine());
					else
						if(i<numberOfPlayer1Balls+numberOfPlayer2Balls+numberOfMines+numberOfHappyFaces)
							objects.add(new HappyFace());
						else
							objects.add(new Obstacle());
		}

		//Separates objects that are close to each other
		objects = spreadObjects(objects);

		//Adds the corresponding Player 1 Balls to the game.
		for(index=0; index<numberOfPlayer1Balls; index++){
			player1Balls.add((MovingBall)objects.get(index));
			GameScreenManager.addComponent(player1Balls.get(index));
		}

		//Adds the corresponding Player 2 Balls to the game.
		for(int i= index, j=0; j<numberOfPlayer2Balls; i++, j++){
			player2Balls.add((MovingBall)objects.get(i));
			GameScreenManager.addComponent(player2Balls.get(j));
			index++;
		}

		//Adds the corresponding mines to the game.
		for(int i = index, j=0; j<numberOfMines; i++, j++){
			mines.add((Mine)objects.get(i));
			GameScreenManager.addComponent(mines.get(j));
			index++;
		}

		//Adds the corresponding happy faces to the game.
		for(int i = index, j=0; j<numberOfHappyFaces; i++, j++){
			happyFaces.add((HappyFace)objects.get(i));
			GameScreenManager.addComponent(happyFaces.get(j));
			index++;
		}

		//Adds the corresponding obstacles to the game.
		for(int i = index, j=0; j<numberOfObstacles; i++, j++){
			obstacles.add((Obstacle)objects.get(i));
			GameScreenManager.addComponent(obstacles.get(j));
			index++;
		}
	}

	/**
	 * Separates the objects that are closer than 1.5 times their diameter.
	 * @param array the array that contains the game objects (not separated)
	 * @return the array that contains the separated objects
	 */
	private static ArrayList<GameObject> spreadObjects(ArrayList<GameObject> array){
		//Creates a new GameObject array
		ArrayList<GameObject> spread = new ArrayList<GameObject>();

		//Copies the content of array to the new array
		for(int i=0; i<array.size(); i++)
			spread.add(array.get(i));


		//Goes through each element in the new array and compares the distance with the other elements.
		for(int j=0; j<spread.size(); j++){
			for(int k=0; k<array.size(); k++){
				if(k!=j){
					if(spread.get(j).distanceTo(array.get(k))<=1.5*20){
						spread.get(j).generatePosition();
						k=0;
					}
				}	
			}
		}

		//Returns the separated array.
		return spread;
	}

	/**
	 * Sets the number of balls for each player.
	 * @return -1 if the operation was canceled
	 * @return 0 is the operation was successful
	 */
	public static int setNumberOfBalls(){
		Integer player1Balls, player2Balls;
		Object[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		//Sets up the dialog for selecting the number of balls Player 1 will use
		player1Balls = (Integer) JOptionPane.showInputDialog(GameScreenManager.getFrame(), 
				"Please select the number of balls that each player will use in this round."
				, "", JOptionPane.PLAIN_MESSAGE, null, numbers, numbers[0]);

		if(player1Balls == null)
			return -1;

		player2Balls = player1Balls;

		//Sets the desired amount of objects.
		setGameObjects(player1Balls, player2Balls);
		return 0;


	}

	/**
	 * Removes all objects from the game screen.
	 */
	public static void removeAllBalls(){
		player1Balls=null;
		player2Balls=null;
		mines=null;
		obstacles=null;
		happyFaces=null;
	}

	/**
	 * Removes the specified object from the game panel.
	 * @param go the object to remove
	 * @return -1 if the object wasn't found
	 * @return 0 if the object was found and removed
	 */
	public static int remove(GameObject go){
		//Searches the balls belonging to player 1.
		for(int i=0; i<player1Balls.size(); i++)
			if(go.equals(player1Balls.get(i))){
				GameScreenManager.removeComponent(player1Balls.get(i));
				player1Balls.remove(i);

				//Checks if player 1 ran out of balls (not removed by mines).
				if(player1Balls.size() == 0 && !removedByMine){
					GameScreenManager.close();
					new ResultsScreen(ProfileManagement.getCurrentPlayer1());
					TitleScreenManager.getInstance().run();
				}

				//Updates the collision tracker.
				CollisionManager.setCollisionManager();
				return 0;
			}

		//Searches the balls belonging to player 2.
		for(int i=0; i<player2Balls.size(); i++)
			if(go.equals(player2Balls.get(i))){
				GameScreenManager.removeComponent(player2Balls.get(i));
				player2Balls.remove(i);

				//Checks if player 2 ran out of balls.
				if(player2Balls.size() == 0 && !removedByMine){
					GameScreenManager.close();
					new ResultsScreen(ProfileManagement.getCurrentPlayer2());
					TitleScreenManager.getInstance().run();
				}

				//Updates the collision tracker.
				CollisionManager.setCollisionManager();
				return 0;
			}

		//Searches for mines to be deleted.
		for(int i=0; i<mines.size(); i++)
			if(go.equals(mines.get(i))){
				GameScreenManager.removeComponent(mines.get(i));
				mines.remove(i);
				CollisionManager.setCollisionManager();
				return 0;
			}

		//Searches for happy faces to be deleted.
		for(int i=0; i<happyFaces.size(); i++)
			if(go.equals(happyFaces.get(i))){
				GameScreenManager.removeComponent(happyFaces.get(i));
				happyFaces.remove(i);
				CollisionManager.setCollisionManager();
				return 0;
			}

		//Searches for obstacles to be deleted.
		for(int i=0; i<obstacles.size(); i++)
			if(go.equals(obstacles.get(i))){
				GameScreenManager.removeComponent(obstacles.get(i));
				obstacles.remove(i);
				CollisionManager.setCollisionManager();
				return 0;
			}

		return -1;

	}


	/**
	 * Removes the objects close to an exploding mine.
	 * @param m the mine that exploded
	 * @param ball the moving ball that hit the Mine
	 */
	public static void removeObjectsAroundMine(Mine m, MovingBall ball){
		removedByMine = true;

		//If a object is closer than 25 units from the mine, they will be removed.
		for(int i =0; i<player1Balls.size(); i++)
			if(player1Balls.get(i).distanceTo(m)<=25.0)
				remove(player1Balls.get(i));

		for(int i =0; i<player2Balls.size(); i++)
			if(player2Balls.get(i).distanceTo(m)<=25.0)
				remove(player2Balls.get(i));

		for(int i =0; i<obstacles.size(); i++)
			if(obstacles.get(i).distanceTo(m)<=25.0)
				remove(obstacles.get(i));

		for(int i =0; i<happyFaces.size(); i++)
			if(happyFaces.get(i).distanceTo(m)<=25.0)
				remove(happyFaces.get(i));

		for(int i =0; i<mines.size(); i++)
			if(!mines.get(i).equals(m))
				if(mines.get(i).distanceTo(m)<=25.0)
					mines.get(i).collisionAction(ball);

		remove(m);

		//If both players ran out of balls, checks the conditions to see which player won.
		if(player1Balls.size() == 0 && player2Balls.size() ==0){
			GameScreenManager.close();
			if(ProfileManagement.getCurrentPlayer1().getDistance() > ProfileManagement.getCurrentPlayer2().getDistance())
				new ResultsScreen(ProfileManagement.getCurrentPlayer2());
			else
				if(ProfileManagement.getCurrentPlayer1().getDistance() < ProfileManagement.getCurrentPlayer2().getDistance())
					new ResultsScreen(ProfileManagement.getCurrentPlayer1());
				else
					new ResultsScreen();
			TitleScreenManager.getInstance().run();
		}
		else
			if(player1Balls.size() == 0){
				GameScreenManager.close();
				new ResultsScreen(ProfileManagement.getCurrentPlayer1());
				TitleScreenManager.getInstance().run();
			}
			else
				if(player2Balls.size() == 0){
					GameScreenManager.close();
					new ResultsScreen(ProfileManagement.getCurrentPlayer2());
					TitleScreenManager.getInstance().run();
				}

		removedByMine=false;

	}

	/**
	 * Returns an ArrayList containing references to the Player 1 balls.
	 * @return an ArrayList containing references to the Player 1 balls
	 */
	public static ArrayList<MovingBall> getPlayer1Balls(){
		return player1Balls;
	}

	/**
	 * Returns an ArrayList containing references to the Player 2 balls.
	 * @return an ArrayList containing references to the Player 2 balls
	 */
	public static ArrayList<MovingBall> getPlayer2Balls(){
		return player2Balls;
	}

	/**
	 * Returns an ArrayList containing references to the Mines.
	 * @return an ArrayList containing references to the Mines
	 */
	public static ArrayList<Mine> getMines(){
		return mines;
	}

	/**
	 * Returns an ArrayList containing references to the Happy Faces.
	 * @return an ArrayList containing references to the Happy Faces
	 */
	public static ArrayList<HappyFace> getHappyFaces(){
		return happyFaces;
	}

	/**
	 * Returns an ArrayList containing references to the Obstacles.
	 * @return an ArrayList containing references to the Obstacles
	 */
	public static ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}
}
