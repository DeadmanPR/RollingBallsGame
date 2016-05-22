package com.uprm.Game;

import javax.swing.JOptionPane;

import com.uprm.GameSounds.Sounds;
import com.uprm.History.History;
import com.uprm.History.HistoryEntry;
import com.uprm.ProfileTools.Player;
import com.uprm.ProfileTools.ProfileManagement;

/**
 * Contains the results screen, shown at the end of every game.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class ResultsScreen{

	/**
	 * Constructs a result screen, shown when the game has been declared a tie.
	 */
	public ResultsScreen(){
		//Gets the current players.
		Player player1 = ProfileManagement.getCurrentPlayer1();
		Player player2 = ProfileManagement.getCurrentPlayer2();

		String results;

		//The message shown in the results screen.
		results = ("The game has been declared a tie.\n"
				+"\n"
				+ "\n"
				+ "                              Final Scores\n"
				+ "                                 " + player1.getName() + "        " + player2.getName() + "\n"
				+ "Distance Left" + "        " + player1.getDistance() + "                   " + player2.getDistance() + "\n"
				+ "Score" + "                      " + player1.getScore() + "                   " + player2.getScore());

		//Plays the sound.
		Sounds.results();

		//Shows the window.
		JOptionPane.showMessageDialog(GameScreenManager.getFrame(), results, "Results", JOptionPane.PLAIN_MESSAGE); 

		//Creates a new entry in the history file with the finished game's data.
		new HistoryEntry(player1, player2);
		History.saveHistoryToFile();

	}

	/**
	 * Creates a new results screen.
	 * @param loserOfTheGame the player who lost the game
	 */
	public ResultsScreen(Player loserOfTheGame){

		Player winner, loser;

		//Sets which player won and which player lost.
		if(loserOfTheGame.equals(ProfileManagement.getCurrentPlayer1())){
			winner = ProfileManagement.getCurrentPlayer2();
			loser = ProfileManagement.getCurrentPlayer1();
		}
		else{
			winner = ProfileManagement.getCurrentPlayer1();
			loser = ProfileManagement.getCurrentPlayer2();
		}

		//Sets the player's score.
		winner.setScore(winner.getDistance());
		loser.setScore(loser.getDistance());

		String results;

		//The message shown in the results screen.
		results = ("The game has finished.\n"
				+ "The winner is: " + winner.getName() + "!\n"
				+ "\n"
				+ "                              Final Scores\n"
				+ "                                 " + winner.getName() + "        " + loser.getName() + "\n"
				+ "Distance Left" + "        " + winner.getDistance() + "                   " + loser.getDistance() + "\n"
				+ "Score" + "                      " + winner.getScore() + "                   " + loser.getScore());


		Sounds.results();
		JOptionPane.showMessageDialog(GameScreenManager.getFrame(), results, "Results", JOptionPane.PLAIN_MESSAGE); 

		//Creates a new entry with the current game's data.
		new HistoryEntry(winner, loser);
		History.saveHistoryToFile();
	}

}
