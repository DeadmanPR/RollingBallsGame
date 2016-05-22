package com.uprm.TitleScreen;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Creates the tutorial screen.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class Tutorial  extends JFrame{
	public Tutorial(){
		setSize(600,600);

		JTextArea text = new JTextArea();

		text.setText("Before the game starts, you need to decide the number of balls that each player will have. "
				+ "\nAfter selecting the number of balls, you will be presented with the game screen."
				+ "\nThe objective of the game is simple: Get all your opponents' balls while still having your own."
				+ "\nBut it's not that easy!"
				+ "\n\nEach player has a quantity called distance. This distance represents the accumulated distance that you \ncan move your balls."
				+ "\nPlayer 1 will start the game. In order to make your move, you need to select your ball by clicking on it \n(by the way,"
				+ "choose wisely, as you can't change your ball once you selected one). After that, you will \nbe prompted to enter the angle"
				+ " and distance that you want the ball to move. When you think you're done, \nclick the Shoot! button."
				+ "\n\nAs your ball moves, be careful with what it collides, because it can have a nice effect, or pretty bad one!"
				+ "\n\nThe black balls are Obstacles. They won't serve any purpose other than rebounding your ball when it hits."
				+ "\n\nThe red balls are Mines. Be careful with these, as they will explode on contact, taking out with it the ball \nthat"
				+ " hit it, and any objects close to it! You will lose 50 points (distance) if you hit one of these."
				+ "\n\nThe Happy Faces act like Obstacles, but have a nice bonus: You gain 50 points (distance)! Isn't that nice?"
				+ "\n\nIf you hit an opponent's ball,  you gain 100 points (distance) and your opponent loses 100 points!"
				+ "\n\nThe game ends when one of these happen"
				+ "\n	- A player's distance reaches 0."
				+ "\n	- A player runs out of balls."
				+ "\n\nGo ahead and have some fun!"); 
		text.setEditable(false);

		JScrollPane pane = new JScrollPane(text);

		add(pane);

		setVisible(true);
	}

}
