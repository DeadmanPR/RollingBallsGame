package com.uprm.ProfileTools;

/**
 * Contains several properties of Players.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class Player {
	private String name;
	private String secretCode;
	private int score=0, distanceRemaining=1500;
	private Integer highScore;

	/**
	 * Constructs a new player.
	 * @param pseudonym the username of the player
	 * @param code the password of said player
	 */
	public Player(String pseudonym, String code, int highScore){
		name=pseudonym;
		secretCode=code;
		this.highScore = highScore;
	}

	/**
	 * Returns the player's name.
	 * @return the player's name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Returns the player's password.
	 * @return the player's password
	 */
	public String getCode(){
		return secretCode;
	}

	/**
	 * Returns the player's current score.
	 * @return the player's score
	 */
	public int getScore(){
		return score;
	}

	/**
	 * Returns the player's high score.
	 * @return the player's high score
	 */
	public Integer getHighScore(){
		return highScore;
	}

	/**
	 * Sets the high score for the player.
	 * @param i the high score
	 */
	public void setHighScore(int i){
		highScore = i;
	}
	/**
	 * Sets the player's score
	 * @param num the player's score
	 */
	public void setScore(int num){
		score=num;
	}

	/**
	 * Sets the name of the player.
	 * @param name the player's name
	 */
	public void setName(String name){
		this.name=name;
	}

	/**
	 * Sets the password for the player.
	 * @param code the player's password
	 */
	public void setCode(String code){
		secretCode=code;
	}

	/**
	 * Returns the distance remaining.
	 * @return the distance remaining
	 */
	public int getDistance(){
		return distanceRemaining;
	}

	/**
	 * Sets the distance remaining.
	 */
	public void setDistance(int distance){
		distanceRemaining=distance;
	}

	/**
	 * Resets the numbers, used when starting a new game.
	 */
	public void resetNumbers(){
		score=0;
		distanceRemaining=1500;
	}
}
