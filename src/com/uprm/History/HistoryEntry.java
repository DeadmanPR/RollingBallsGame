package com.uprm.History;

import java.util.Date;

import com.uprm.ProfileTools.Player;

/**
 * Contains operations related to the entries for the history of games played.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class HistoryEntry {
	private Player winner, loser;
	private String nameWinner, nameLoser;
	private int winnerScore, loserScore;
	private Date date;


	/**
	 * Creates a blank HistoryEntry.
	 */
	public HistoryEntry(){

	}

	/**
	 * Creates a new HistoryEntry with the data provided.
	 * @param winner winner of the game
	 * @param loser loser of the game
	 */
	public HistoryEntry(Player winner, Player loser){

		//Sets the players of the game, and the date.
		this.winner = winner;
		this.loser = loser;
		date = new Date();

		//Sets the player's info.
		nameWinner=winner.getName();
		nameLoser=loser.getName();
		winnerScore=winner.getScore();
		loserScore=loser.getScore();

		//Checks if any high score has been beaten.
		if(winnerScore > winner.getHighScore())
			winner.setHighScore(winnerScore);

		if(loserScore > loser.getHighScore())
			loser.setHighScore(loserScore);

		//Adds the entry to the history.
		History.addNewEntry(this);
	}

	/**
	 * Sets the winner of the game.
	 * @param winner the winner of the game
	 */
	public void setWinner(Player winner) {
		this.winner = winner;
	}


	/**
	 * Sets the loser of the game.
	 * @param loser the loser of the game
	 */
	public void setLoser(Player loser) {
		this.loser = loser;
	}


	/**
	 * Sets the winner's name.
	 * @param nameWinner the winner's name
	 */
	public void setNameWinner(String nameWinner) {
		this.nameWinner = nameWinner;
	}

	/**
	 * Sets the loser's name.
	 * @param nameLoser the loser's name
	 */
	public void setNameLoser(String nameLoser) {
		this.nameLoser = nameLoser;
	}

	/**
	 * Sets the winner's score.
	 * @param winnerScore the winner's score
	 */
	public void setWinnerScore(int winnerScore) {
		this.winnerScore = winnerScore;
	}

	/**
	 * Sets the loser's score.
	 * @param loserScore the loser's score.
	 */
	public void setLoserScore(int loserScore) {
		this.loserScore = loserScore;
	}

	/**
	 * Sets the date of the game.
	 * @param date the date of the game
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Returns the winner's name.
	 * @return the winner's name
	 */
	public String getNameWinner() {
		return nameWinner;
	}

	/**
	 * Returns the loser's name.
	 * @return the loser's name
	 */
	public String getNameLoser() {
		return nameLoser;
	}

	/**
	 * Returns the winner's score.
	 * @return the winner's score
	 */
	public int getWinnerScore() {
		return winnerScore;
	}

	/**
	 * Returns the loser's score.
	 * @return the loser's score
	 */
	public int getLoserScore() {
		return loserScore;
	}

	/**
	 * Returns the date of the game.
	 * @return the date of the game
	 */
	public Date getDate() {
		return date;
	}

}
