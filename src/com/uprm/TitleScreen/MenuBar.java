package com.uprm.TitleScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.uprm.Game.GameScreenManager;
import com.uprm.History.History;
import com.uprm.ProfileTools.ProfileManagement;

/**
 * Contains the menu bar seen in the Title Screen.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class MenuBar extends JMenuBar implements ActionListener{
	private JMenu menu = new JMenu("Play A New Game");
	private JMenuItem currentPlayers = new JMenuItem("Current Players");
	private JMenuItem newPlayers = new JMenuItem("Select New Players");

	private JMenu firstTimePlayer = new JMenu("First Time Player");
	private JMenuItem newProfile = new JMenuItem("Create a New Profile");

	private JMenu history = new JMenu("History");
	private JMenuItem viewHistoryByPlayer = new JMenuItem("View each Player's High Score");
	private JMenuItem viewAllHistory = new JMenuItem("View all History");

	private JMenu help = new JMenu("Help");
	private JMenuItem tutorial = new JMenuItem("How to Play");

	private JMenu exit = new JMenu("Exit");
	private JMenuItem saveAndExit = new JMenuItem("Save and Exit");


	/**
	 * Constructs the menu bar.
	 */
	public MenuBar(){
		//Sets the action listeners for the menus.
		currentPlayers.addActionListener(this);
		newPlayers.addActionListener(this);
		newProfile.addActionListener(this);
		viewHistoryByPlayer.addActionListener(this);
		viewAllHistory.addActionListener(this);
		tutorial.addActionListener(this);
		saveAndExit.addActionListener(this);

		//Sets the tooltip text to each menu items, and adds them to their corresponding menus.
		currentPlayers.setToolTipText("Start the game with the currently selected players");
		menu.add(currentPlayers);

		newPlayers.setToolTipText("Select the players and start the game");
		menu.add(newPlayers);

		newProfile.setToolTipText("Create a new Player profile");
		firstTimePlayer.add(newProfile);

		viewHistoryByPlayer.setToolTipText("View each Player's high score");
		history.add(viewHistoryByPlayer);

		viewAllHistory.setToolTipText("View all history of games played");
		history.add(viewAllHistory);

		tutorial.setToolTipText("Learn how to play the game");
		help.add(tutorial);

		saveAndExit.setToolTipText("Quit the game (while saving the players' progress)");
		exit.add(saveAndExit);

		//Adds all the menus to the MenuBar.
		add(menu);
		add(firstTimePlayer);
		add(history);
		add(help);
		add(exit);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		//Executed if the Current Players option was selected
		if(e.getSource() == currentPlayers){
			if(!ProfileManagement.arePlayersSelected()){
				if(ProfileManagement.areProfilesCreated()){
					ProfileManagement.selectPlayers();

				}
				else{
					JOptionPane.showMessageDialog(TitleScreenManager.getFrame(),
							"There are less than 2 profiles created. You will be prompted to create a new profile.",
							"Error", JOptionPane.ERROR_MESSAGE);
					ProfileManagement.createNewProfile();
				}
			}
			else{
				TitleScreenManager.getInstance().close();
				GameScreenManager.getInstance().run();
			}
		}



		//Executed when the Select New Players option is selected.
		if (e.getSource()==newPlayers){
			if(ProfileManagement.areProfilesCreated())
				ProfileManagement.selectPlayers();
			else{
				JOptionPane.showMessageDialog(TitleScreenManager.getFrame(), 
						"There are less than 2 profiles created. You will be prompted to create a new profile.", 
						"Error", JOptionPane.ERROR_MESSAGE);
				ProfileManagement.createNewProfile();
			}
		}

		//Executed when the Create a new Profile option is selected.
		if(e.getSource()==newProfile){
			ProfileManagement.createNewProfile();
		}

		//Executed when the View each Player's High Score option is selected.
		if(e.getSource() == viewHistoryByPlayer)
			History.showHistoryWithHighestScore();

		//Executed when the View all History option is selected.
		if(e.getSource() == viewAllHistory)
			History.showAllHistory();
		
		//Executed when the How To Play option is selected.
		if(e.getSource() == tutorial)
			new Tutorial();

		//Executed when the Save and Exit option is selected.
		if(e.getSource()==saveAndExit){
			ProfileManagement.saveProfilesToFile();
			System.exit(0);
		}

	}
}





