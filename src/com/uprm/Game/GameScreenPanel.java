package com.uprm.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.uprm.GameObjects.MovingBall;
import com.uprm.ProfileTools.ProfileManagement;

/**
 * Contains the area where the balls are located.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class GameScreenPanel extends JPanel implements MouseListener{
	private int width = 600;
	private int height = 600;
	private ArrayList<MovingBall> player1Balls = ObjectManager.getPlayer1Balls();
	private ArrayList<MovingBall> player2Balls = ObjectManager.getPlayer2Balls();

	/**
	 * Creates a game area panel.
	 */
	public GameScreenPanel(){
		//Sets the layout to null in order to manually locate things.
		setLayout(null);
		ProfileManagement.getCurrentPlayer1().resetNumbers();
		ProfileManagement.getCurrentPlayer2().resetNumbers();
		addMouseListener(this);
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);

		//Sets the size and location of the panel.
		setSize(width+1,height+1);
		setLocation(5,5);

		//Draws the game area.
		g.drawRect(0, 0, width, height);

		//Draws the lines inside the game area.
		g.setColor(Color.lightGray);
		for(int i=1; i<25 ; i++)
			g.drawLine(i*width/25, 0, i*width/25, height);

		for(int i=1 ; i<25 ; i++)
			g.drawLine(0, i*height/25, width, i*height/25);

		update();
	}


	public void update(){
		//***May not be needed***
	}

	/**
	 * Returns the game area height.
	 * @return the panel's height
	 */
	public int getPanelHeight(){
		return height;
	}

	/**
	 * Returns the game area width.
	 * @return the panel's width
	 */
	public int getPanelWidth(){
		return width;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		boolean ballFound = false;
		for(int i=0; i<player1Balls.size() && !ballFound; i++){
			if(e.getX()>player1Balls.get(i).getXPosition() && e.getX()<=(player1Balls.get(i).getXPosition()+player1Balls.get(i).getDiameter())
					&& e.getY()>player1Balls.get(i).getYPosition() && e.getY()<=(player1Balls.get(i).getYPosition()+player1Balls.get(i).getDiameter())){
				ballFound=true;
				player1Balls.get(i).clicked();
			}
		}
		for(int i=0; i<player2Balls.size() && !ballFound; i++){
			if(e.getX()>player2Balls.get(i).getXPosition() && e.getX()<=(player2Balls.get(i).getXPosition()+player2Balls.get(i).getDiameter())
					&& e.getY()>player2Balls.get(i).getYPosition() && e.getY()<=(player2Balls.get(i).getYPosition()+player2Balls.get(i).getDiameter())){
				ballFound=true;
				player2Balls.get(i).clicked();
			}
		}
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
