package com.uprm.TitleScreen;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Manages the Title Screen window.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class TitleScreenManager {
	private static TitleScreenManager uniqueInstance = null;
	private static JFrame frame = new JFrame("The Rolling Balls Game");
	private static JPanel panel = new JPanel();

	/**
	 * Constructs a new manager.
	 */
	private TitleScreenManager(){

		ImageIcon image = new ImageIcon("TitleScreen.jpg");
		JLabel label = new JLabel("" , image, JLabel.CENTER);

		panel.add(label);

		frame.setTitle("The Rolling Balls Game");
		frame.setSize(950,750);
		frame.setResizable(false);
		frame.setJMenuBar(new MenuBar());

		frame.add(panel);
	}

	/**
	 * Checks if there is an instance running, and returns it.
	 * @return the current instance of TitleScreenManager
	 */
	public static TitleScreenManager getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new TitleScreenManager();
		return uniqueInstance;
	}

	/**
	 * Returns the title screen frame.
	 * @return the title screen frame
	 */
	public static JFrame getFrame(){
		getInstance();
		return frame;

	}

	/**
	 * Executes the Title Screen.
	 */
	public void run(){
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Closes the Title Screen.
	 */
	public void close(){
		frame.setVisible(false);
	}


}
