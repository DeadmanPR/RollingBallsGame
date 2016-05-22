package com.uprm.GameSounds;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Sets up sounds that can be heard in-game.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class Sounds {
	private static File errorFile = new File("Error.wav");
	private static File explosionFile = new File("Explosion.wav");
	private static File hitObstacleFile = new File("HitObstacle.wav");
	private static File happyFaceFile = new File("HappyFace.wav");
	private static File hitOpponentFile = new File("HitOpposingBall.wav");
	private static File results = new File("Results.wav");
	private static Clip backgroundMusic;

	/**
	 * Starts the given sound.
	 * @param file the file that contains the sound.
	 */
	private static void startSound(File file){
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);

			Clip clip = AudioSystem.getClip();
			backgroundMusic=clip;

			backgroundMusic.open(audioIn);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		backgroundMusic.start();
	}

	/**
	 * Plays the error sound.
	 */
	public static void error(){
		startSound(errorFile);
	}

	/**
	 * Plays the explosion sound.
	 */
	public static void explosion(){
		startSound(explosionFile);
	}

	/** 
	 * Plays the sound heard when hitting an obstacle.
	 */
	public static void hitObstacle(){
		startSound(hitObstacleFile);
	}

	/**
	 * Plays the sound heard when hitting a happy face.
	 */
	public static void hitHappyFace(){
		startSound(happyFaceFile);
	}

	/**
	 * Plays the sound heard when hitting an opponent's ball.
	 */
	public static void hitOpponentBall(){
		startSound(hitOpponentFile);
	}

	/**
	 * Plays the tune for the results screen
	 */
	public static void results(){
		startSound(results);
	}


}
