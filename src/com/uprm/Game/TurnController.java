package com.uprm.Game;

import com.uprm.ProfileTools.Player;
import com.uprm.ProfileTools.ProfileManagement;

/**
 * Controls the turns mechanism of the game.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class TurnController {
	private static Player currentTurnHolder;

	/**
	 * Sets up Player 1's turn.
	 */
	public static void player1Turn(){
		//Sets the currentTurnHolder to Player 1, and sets the turn markers (outline rectangles) on the button and the info panel.
		currentTurnHolder = ProfileManagement.getCurrentPlayer1();
		GameScreenInfoPanel.player1Turn();
		Buttons.player1Turn();
	}

	/**
	 * Sets up Player 2's turn.
	 */
	public static void player2Turn(){
		//Sets the currentTurnHolder to Player 2, and sets the turn markers (outline rectangles) on the button and the info panel.
		currentTurnHolder = ProfileManagement.getCurrentPlayer2();
		GameScreenInfoPanel.player2Turn();
		Buttons.player2Turn();
	}

	/**
	 * Returns the current turn holder.
	 * @return the turn's holder
	 */
	public static Player whoseTurnIsIt(){
		return currentTurnHolder;
	}
}
