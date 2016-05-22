package com.uprm.Game;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.uprm.GameObjects.MovingBall;
import com.uprm.ProfileTools.Player;
import com.uprm.ProfileTools.ProfileManagement;

/**
 * Contains the options shown when selecting and shooting the ball.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class ShootingOptions{

	private static JFrame frame;
	private static JFrame frame2;
	private Player player;
	private static JComboBox<?> angleList;
	private static JComboBox<?> distanceList;

	/**
	 * Creates the windows shows when shooting.
	 * @param player the player controlling the ball
	 */
	public ShootingOptions(Player player){
		this.player=player;

		//Instantiates the frames, and modifies their properties.
		frame = new JFrame("Choose your angle");
		frame.setSize(300,50);
		frame.setLocation(700,450);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);

		frame2 = new JFrame("Choose your distance");
		frame2.setSize(300,50);
		frame2.setLocation(700,510);
		frame2.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame2.setResizable(false);
		frame2.setAlwaysOnTop(true);

		//Creates an array containing the angles.
		Integer[] angle = new Integer[360];
		for(int i=0; i<angle.length; i++)
			angle[i]=i;

		//Creates an array containing the distances.
		Integer[] distance = new Integer[player.getDistance()];
		for(int i=0; i<distance.length;i++)
			distance[i]=i+1;

		//Creates the combo boxes.
		angleList = new JComboBox<Object>(angle);
		distanceList = new JComboBox<Object>(distance);

		//Adds the combo boxes to the frames, and sets them visible.
		frame.add(angleList);
		frame2.add(distanceList);
		frame.setVisible(true);
		frame2.setVisible(true);


	}

	/**
	 * Closes the shooting options.
	 */
	public static void close(){
		//Checks that the frames are open, sets the movement of the selected ball, and closes the frames.
		if(frame != null && frame2 != null){
			MovingBall.setMovementDirection((Integer)angleList.getSelectedItem(), (Integer)distanceList.getSelectedItem());
			frame.setVisible(false);
			frame.dispose();
			frame = null;

			frame2.setVisible(false);
			frame2.dispose();
			frame2 = null;

		}
	}
}
