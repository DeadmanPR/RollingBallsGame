package com.uprm.Game;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import com.uprm.ProfileTools.ProfileManagement;

/**
 * Contains the score table used in the game, and its properties.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class GameScreenInfoPanel extends JPanel{
	private static int turnMarkerX=0;
	private static int turnMarkerY=30;

	/**
	 * Creates the Game Information Panel
	 */
	public GameScreenInfoPanel(){
		//Sets the layout to null in order to manually locate things.
		setLayout(null);
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);

		//Sets the size and location of the panel.
		setSize(300,90);
		setLocation(635,285);

		//Draws the header of the table
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, 300, 30);

		//Draws Player 1's row
		g.setColor(Color.green);
		g.fillRect(0,30,300,30);

		//Draws Player 2's row
		g.setColor(new Color(74,134,232));
		g.fillRect(0, 60, 300, 30);

		//Table division
		g.setColor(Color.black);
		g.drawLine(125, 0, 125,90);
		g.drawLine(213,0,213,90);

		//Draws the current info on the table
		g.drawString("PLAYER", 40, 20);
		g.drawString("SCORE", 150, 20);
		g.drawString("DISTANCE", 230, 20);
		g.drawString(ProfileManagement.getCurrentPlayer1().getName(), 5, 50);
		g.drawString(ProfileManagement.getCurrentPlayer2().getName(), 5, 80);
		g.drawString(""+ProfileManagement.getCurrentPlayer1().getScore(), 165, 50);
		g.drawString(""+ProfileManagement.getCurrentPlayer2().getScore(), 165, 80);
		g.drawString(""+ProfileManagement.getCurrentPlayer1().getDistance(), 245, 50);
		g.drawString(""+ProfileManagement.getCurrentPlayer2().getDistance(), 245, 80);

		//Draws the turn marker to indicate whose turn is it
		g.setColor(Color.black);
		g.drawRect(turnMarkerX,turnMarkerY,299,30);

		update();
	}


	public void update(){
		GameScreenManager.getFrame().repaint();
	}

	/**
	 * Sets the turn marker to indicate that it is Player 1's turn
	 */
	public static void player1Turn(){
		turnMarkerX=0;
		turnMarkerY=30;
	}

	/**
	 * Sets the turn marker to indicate that it is Player 2's turn
	 */
	public static void player2Turn(){
		turnMarkerX=0;
		turnMarkerY=59;
	}


}
