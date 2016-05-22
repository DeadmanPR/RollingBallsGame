package com.uprm.ProfileTools;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.uprm.Game.GameScreenManager;
import com.uprm.GameSounds.Sounds;
import com.uprm.TitleScreen.TitleScreenManager;

/**
 * Contains several properties related to the management of profiles.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class ProfileManagement {
	private static int size = 0;
	private static ArrayList<Player> profiles = new ArrayList<Player>();
	private static Player currentPlayer1;
	private static Player currentPlayer2;
	private static File profileFile = new File("Profiles.txt");


	/**
	 * Adds a new profile.
	 * @param name the player's name
	 * @param code the player's password
	 */
	public static void addNewProfile(String name, String code, int highScore){
		profiles.add(new Player(name, code, highScore));
		size++;

	}

	/**
	 * Creates a new profile.
	 * @return -1 if the operation was canceled
	 * @return 0 if the operation was successful
	 */
	public static int createNewProfile(){
		String name, code;

		//Asks the name of the new profile.
		name = JOptionPane.showInputDialog(TitleScreenManager.getFrame(), "Please enter your name.", 
				"Name Selection" , JOptionPane.PLAIN_MESSAGE);
		if(name == null)
			return -1;


		//Asks for the name as long as the name entered is already used.
		while (isNameUsed(name)){
			Sounds.error();
			JOptionPane.showMessageDialog(TitleScreenManager.getFrame(), "The name you entered is already in use!", 
					"Error", JOptionPane.ERROR_MESSAGE);
			name = JOptionPane.showInputDialog(TitleScreenManager.getFrame(), "Please enter your name.", 
					"Name Selection" , JOptionPane.PLAIN_MESSAGE);
			if(name == null)
				return -1;
		}

		JOptionPane.showMessageDialog(TitleScreenManager.getFrame(), "You will be prompted to enter your secret code"
				+ " (has to be 4 characters long).", 
				"", JOptionPane.INFORMATION_MESSAGE);

		//Asks for the password.
		JPasswordField pf = new JPasswordField(4);
		JOptionPane.showConfirmDialog(TitleScreenManager.getFrame(), pf, "Enter the secret code to use", 
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		code = String.valueOf(pf.getPassword());

		if(code.equals(""))
			return -1;

		//Repeats ifthe password is longer or shorter than 4 characters.
		while(code.length()!=4){
			Sounds.error();
			pf.setText("");
			JOptionPane.showMessageDialog(TitleScreenManager.getFrame(), "You entered an invalid code! (not 4 characters long)", 
					"Error", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showConfirmDialog(TitleScreenManager.getFrame(), pf, "Enter your secret code", 
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			code = String.valueOf(pf.getPassword());

			if (code.equals(""))
				return -1;
		}
		//Adds the Player to the ArrayList, and saves the profiles to the file.
		addNewProfile(name, code, 0);
		saveProfilesToFile();
		return 0;
	}


	/**
	 * Saves the profile to a file.
	 */
	public static void saveProfilesToFile(){
		try{
			PrintWriter output = new PrintWriter(profileFile);
			output.println(size);
			for(int i=0; i<size; i++)
				output.println(profiles.get(i).getName() + ' ' + profiles.get(i).getCode() + ' ' + profiles.get(i).getHighScore());

			output.close();
		}catch (FileNotFoundException e){
			System.out.println("Error: The file was not found.");
		}
	}

	/**
	 * Loads the profile from a file.
	 */
	public static void loadProfilesFromFile(){
		if(profileFile.exists()){
			try{
				Scanner input = new Scanner(profileFile);
				if(input.hasNextLine()){
					int numberOfProfiles = input.nextInt();
					input.nextLine();
					for(int i = 0; i<numberOfProfiles ; i++){
						String name = input.next();
						String code = input.next();
						int highScore = input.nextInt();
						addNewProfile(name, code, highScore);
					}
				}
				input.close();
			}catch (FileNotFoundException e){
				System.out.println("Error: The file was not found.");
			}
		}

	}

	/**
	 * Checks if a name is already used.
	 * @param nameToCheck the name to search for
	 * @return true if the name is already used
	 */
	public static boolean isNameUsed(String nameToCheck){
		for (int i=0 ; i<size; i++){
			if(nameToCheck.equals(profiles.get(i).getName()))
				return true;
		}
		return false;
	}

	/**
	 * Gets the index of a certain player.
	 * @param name the name of the player
	 * @return the index of the player in the profiles ArrayList
	 */
	private static int getIndex(String name){
		for(int i=0;i<size;i++)
			if(profiles.get(i).getName().equals(name))
				return i;

		return -1;
	}

	/**
	 * Selects the players for the current session.
	 * @return -1 if the operation was canceled
	 * @return 0 if the operation was successful
	 */
	public static int selectPlayers(){
		int[] sortedIndexes = Sorter.sortByName(profiles);
		Object[] players = new Object[size];
		String password, password2;

		//Sorts the profiles by alphabetical order.
		for(int i=0;i<size;i++)
			players[i] = profiles.get(sortedIndexes[i]).getName();

		//Shows the list of registered Players.
		String playerSelected = (String) JOptionPane.showInputDialog(TitleScreenManager.getFrame(), 
				"Player 1, please select your name.", "Player Selection",
				JOptionPane.PLAIN_MESSAGE, null, players, players[0]);

		if(playerSelected == null)
			return -1;

		int index = getIndex(playerSelected);

		//Confirms the password of said Player.
		JPasswordField pf = new JPasswordField();
		JOptionPane.showConfirmDialog(TitleScreenManager.getFrame(), pf, "Enter your secret code", 
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		password = String.valueOf(pf.getPassword());

		if (password.equals(""))
			return -1;

		while (!password.equals(profiles.get(index).getCode())){
			Sounds.error();
			pf.setText("");
			JOptionPane.showMessageDialog(TitleScreenManager.getFrame(), "Incorrect code. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showConfirmDialog(TitleScreenManager.getFrame(), pf, "Enter your secret code", 
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			password = String.valueOf(pf.getPassword());

			if (password.equals(""))
				return -1;
		}

		//Sets the Player 1
		currentPlayer1 = profiles.get(index);

		//Same thing with Player 2
		String player2Selected = (String) JOptionPane.showInputDialog(TitleScreenManager.getFrame(), 
				"Player 2, please select your name.", "Player Selection", JOptionPane.PLAIN_MESSAGE, 
				null, players, players[0]);


		while(player2Selected == playerSelected){
			Sounds.error();
			JOptionPane.showMessageDialog(TitleScreenManager.getFrame(), "Player 1 has already taken this name!",
					"Error", JOptionPane.ERROR_MESSAGE);

			player2Selected = (String) JOptionPane.showInputDialog(TitleScreenManager.getFrame(), 
					"Player 2, please select your name.",
					"Player Selection",	JOptionPane.PLAIN_MESSAGE, null, players, players[0]);
		}

		if(player2Selected == null)
			return -1;

		int index2 = getIndex(player2Selected);

		pf.setText("");
		JOptionPane.showConfirmDialog(TitleScreenManager.getFrame(), pf, "Enter your secret code", 
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		password2 = String.valueOf(pf.getPassword());

		if (password2.equals(""))
			return -1;

		while (!password2.equals(profiles.get(index2).getCode())){
			Sounds.error();
			pf.setText("");
			JOptionPane.showMessageDialog(TitleScreenManager.getFrame(), "Incorrect code. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showConfirmDialog(TitleScreenManager.getFrame(), pf, "Enter your secret code", 
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			password2 = String.valueOf(pf.getPassword());

			if (password2.equals(""))
				return -1;
		}

		currentPlayer2 = profiles.get(index2);
		TitleScreenManager.getInstance().close();
		GameScreenManager.getInstance().run();
		return 0;		
	}

	/**
	 * Checks if the players have been selected.
	 * @return true if the players have been selected
	 */
	public static boolean arePlayersSelected(){
		return !(currentPlayer1 == null || currentPlayer2 == null);

	}

	/**
	 * Checks if there are a minimum of 2 profiles created in order to play the game.
	 * @return true if there are 2 or more profiles created
	 */
	public static boolean areProfilesCreated(){
		return profiles.size() >= 2;
	}

	/**
	 * Returns the current Player 1.
	 * @return the current Player 1
	 */
	public static Player getCurrentPlayer1(){
		return currentPlayer1;
	}

	/**
	 * Returns the current Player 2
	 * @return the current Player 2
	 */
	public static Player getCurrentPlayer2(){
		return currentPlayer2;
	}

	/**
	 * Returns an ArrayList containing the game profiles.
	 * @return the profiles regiestered
	 */
	public static ArrayList<Player> getProfiles(){
		return profiles;
	}


}
