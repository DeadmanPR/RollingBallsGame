package com.uprm.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.uprm.GameObjects.GameObject;
import com.uprm.GameObjects.MovingBall;
import com.uprm.TitleScreen.TitleScreenManager;

/**
 * Contains the buttons used in the game, and their actions.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class Buttons extends JPanel{
	private static JButton buttonPlayer1;
	private static JButton buttonPlayer2;
	private static JButton buttonAbortGame;
	private static int turnMarkerX=3;
	private static int turnMarkerY=0;

	/**
	 * Constructs the buttons to be used in the game.
	 */
	public Buttons(){
		//Sets the layout to null in order to manually locate things
		setLayout(null);

		//Creates the buttons
		buttonPlayer1 = new JButton("Shoot!");
		buttonPlayer2 = new JButton("Shoot!");
		buttonAbortGame = new JButton("Abort Game");

		//Sets each button's color
		buttonPlayer1.setBackground(Color.green);
		buttonPlayer2.setBackground(new Color(74,134,232));
		buttonAbortGame.setBackground(Color.yellow);

		//Sets each button's size
		buttonPlayer1.setSize(100,50);
		buttonPlayer2.setSize(100,50);
		buttonAbortGame.setSize(150,50);

		//Locates the buttons inside the JPanel
		buttonPlayer1.setLocation(5, 3);
		buttonPlayer2.setLocation(245, 3);
		buttonAbortGame.setLocation(540, 3);

		//Action when Player 1 "Shoot!" button is pressed
		buttonPlayer1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(GameObject.isABallSelected()){
					ShootingOptions.close();
					CollisionManager.move();
					TurnController.player2Turn();
				}
			}

		});

		//Action when Player 2 "Shoot!" button is pressed
		buttonPlayer2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(GameObject.isABallSelected()){
					ShootingOptions.close();
					CollisionManager.move();
					TurnController.player1Turn();
				}
			}

		});

		//Action when the "Abort Game" button is pressed
		buttonAbortGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int action;

				//Shows the Confirmation Dialog
				action = JOptionPane.showConfirmDialog(GameScreenManager.getFrame(), 
						"Are you sure you want to quit the game? The game will not be saved!", 
						"Abort Game", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

				//If the user chose yes, the game exits to the Title Screen
				if(action == JOptionPane.YES_OPTION){
					GameScreenManager.getInstance().close();
					TitleScreenManager.getInstance().run();
				}
			}

		});

		//Adds the buttons to the JPanel
		add(buttonPlayer1);
		add(buttonPlayer2);
		add(buttonAbortGame);
	}


	public void paintComponent(Graphics g){
		//Sets the size and location on the JPanel
		setSize(760,60);
		setLocation(150,650);

		//Draws the black rectangle around the button, indicating whose turn is it
		g.setColor(Color.black);
		g.fillRect(turnMarkerX,turnMarkerY,105,55);

		update();
	}


	public void update(){
		GameScreenManager.getFrame().repaint();
	}

	/**
	 * Sets the turn marker to indicate that it is Player 1's turn
	 */
	public static void player1Turn(){
		turnMarkerX=3;
		turnMarkerY=0;
		buttonPlayer1.setEnabled(true);
		buttonPlayer2.setEnabled(false);
	}

	/**
	 * Sets the turn marker to indicate that it is Player 2's turn
	 */
	public static void player2Turn(){
		turnMarkerX=243;
		turnMarkerY=0;
		buttonPlayer1.setEnabled(false);
		buttonPlayer2.setEnabled(true);
	}
}
