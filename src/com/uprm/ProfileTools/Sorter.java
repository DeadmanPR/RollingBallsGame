package com.uprm.ProfileTools;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Contains operations related to sorting the profiles in a specific manner.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class Sorter {

	/**
	 * Sorts the profiles ArrayList by name. 
	 * @param players the ArrayList to be sorted
	 * @return the sorted indexes of the entered ArrayList
	 */
	public static int[] sortByName(ArrayList<Player> players){
		ArrayList<String> names = new ArrayList<String>();
		int[] sortedIndexes  = new int[players.size()];

		//Gets the player's names.
		for(int i=0; i<players.size(); i++)
			names.add(players.get(i).getName());

		//Sorts the names.
		Collections.sort(names, new Comparator<String>(){

			@Override
			public int compare(String arg0, String arg1) {
				// TODO Auto-generated method stub
				return arg0.compareToIgnoreCase(arg1);
			}

		});

		//Fills the array that contains the sorted order of the indexes.
		for(int i=0; i<players.size() ; i++)
			for(int j=0; j<players.size(); j++)
				if(players.get(j).getName().equals(names.get(i)))
					sortedIndexes[i]=j;

		return sortedIndexes;
	}

	/**
	 * Sorts the profiles ArrayList by high score. 
	 * @param players the ArrayList to be sorted
	 * @return the sorted indexes of the ArrayList entered
	 */

	public static int[] sortByHighScore(ArrayList<Player> players){
		ArrayList<Integer> scores = new ArrayList<Integer>();
		int[] sortedIndexes = new int[players.size()];

		//Gets the player's scores.
		for(int i=0; i<players.size(); i++)
			scores.add(players.get(i).getHighScore());

		//Sorts by high score
		Collections.sort(scores, new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}

		});

		//Fills the array that contains the sorted order of the indexes.
		for(int i=0; i<players.size() ; i++)
			for(int j=0; j<players.size(); j++)
				if(players.get(j).getHighScore().equals(scores.get(i)))
					sortedIndexes[i]=j;

		return sortedIndexes;
	}
}
