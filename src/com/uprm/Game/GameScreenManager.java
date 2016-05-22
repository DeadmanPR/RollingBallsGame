package com.uprm.Game;

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.OverlayLayout;

import com.uprm.TitleScreen.TitleScreenManager;

/**
 * Manages the actual Game Window, and contains several of its properties.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class GameScreenManager {
	private static GameScreenManager uniqueInstance = null;;
	private static JFrame gameFrame;
	private int width=950, height=750;

	/**
	 * Constructs a new Manager.
	 */
	private GameScreenManager(){
		//Creates the JFrame
		gameFrame = new JFrame("The Rolling Balls Game");

		//Sets the frame's properties
		gameFrame.setSize(width, height);
		LayoutManager overlay = new OverlayLayout(gameFrame.getContentPane());
		gameFrame.setLayout(overlay);
		gameFrame.setResizable(false);
	}

	/**
	 * Checks if there is an unique instance of the game window, and returns it.
	 * @return the unique instance of the game window.
	 */
	public static GameScreenManager getInstance(){
		//Checks if there is an instance running already.
		if(uniqueInstance == null)
			uniqueInstance = new GameScreenManager();
		return uniqueInstance;
	}

	/**
	 * Returns the frame in which the game is located.
	 * @return the game's frame
	 */
	public static JFrame getFrame(){
		//Makes sure the frame is created before returning it.
		getInstance();
		return gameFrame;

	}

	/**
	 * Facilitates the addition of Components to the frame.
	 * @param comp the component to add
	 */
	public static void addComponent(Component comp){
		gameFrame.add(comp);
	}

	/**
	 * Facilitates the removal of Components from the frame
	 * @param comp the component to remove
	 */
	public static void removeComponent(Component comp){
		gameFrame.remove(comp);
	}


	/**
	 * Runs the game window.
	 */
	public void run(){
		gameFrame.add(new Buttons());
		//Check if the selection of the number of balls has been canceled.
		int checkIfCanceled = ObjectManager.setNumberOfBalls();
		if(checkIfCanceled == 0){
			//Adds everything to the frame, sets it visible and starts the music.
			gameFrame.add(new GameScreenPanel());
			gameFrame.add(new GameScreenInfoPanel());
			gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gameFrame.setVisible(true);

			CollisionManager.setCollisionManager();
			TurnController.player1Turn();

		}

		else
			//If the selection of balls was canceled, it returns to the TitleScreen
			TitleScreenManager.getInstance().run();
	}

	/**
	 * Closes the game window.
	 */
	public static void close(){
		//Hides the frame, and stops the music.
		gameFrame.setVisible(false);
		gameFrame.dispose();
		ShootingOptions.close();
		uniqueInstance=null;
	}


}
