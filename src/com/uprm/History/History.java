package com.uprm.History;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.uprm.ProfileTools.Player;
import com.uprm.ProfileTools.ProfileManagement;
import com.uprm.ProfileTools.Sorter;

/**
 * Contains several operations related to the history of games played.
 * @author Jose Antonio Rodriguez Rivera
 *
 */
public class History {
	private static ArrayList<HistoryEntry> history = new ArrayList<HistoryEntry>();
	private static int size=0;
	private static ArrayList<Player> profiles;

	private static File historyFile = new File("History.txt");

	/**
	 * Adds a new HistoryEntry.
	 * @param he the HistoryEntry to add
	 */
	public static void addNewEntry(HistoryEntry he){
		history.add(he);
		size++;
	}

	/**
	 * Saves the history to the file.
	 */
	public static void saveHistoryToFile(){
		try {
			PrintWriter out = new PrintWriter(historyFile);
			out.println(size);

			for(int i=0; i<size; i++){
				out.print(history.get(i).getNameWinner()+ " ");
				out.print(history.get(i).getWinnerScore() + " ");
				out.print(history.get(i).getNameLoser() + " ");
				out.print(history.get(i).getLoserScore()+ " ");
				out.println(history.get(i).getDate().getTime());
			}

			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: The file was not found.");
		}

	}

	/**
	 * Loads the history from the History.txt file.
	 */
	public static void loadHistoryFromFile(){
		if(historyFile.exists()){
			try{
				Scanner input = new Scanner(historyFile);

				//Reads the file for the data.
				if(input.hasNextLine()){
					int numberOfEntries = input.nextInt();
					input.nextLine();
					for(int i = 0; i<numberOfEntries ; i++){
						String nameWinner = input.next();
						int winnerScore = Integer.parseInt(input.next());
						String nameLoser = input.next();
						int loserScore = Integer.parseInt(input.next());
						long date = Long.parseLong(input.next());

						//Creates the HistoryEntry to be added to the ArrayList
						HistoryEntry he = new HistoryEntry();
						he.setNameWinner(nameWinner);
						he.setWinnerScore(winnerScore);
						he.setNameLoser(nameLoser);
						he.setLoserScore(loserScore);
						he.setDate(new Date(date));

						addNewEntry(he);
					}
				}
				input.close();
			}catch (FileNotFoundException e){
				System.out.println("Error: The file was not found.");
			}
		}
	}

	/**
	 * Searches for the corresponding HistoryEntry.
	 * @param player the specified player to be searched for
	 * @param highScore the high score said player got in the game
	 * @return the index of the HistoryEntry, returns -1 if not found.
	 */
	private static int findDataHistory(Player player, int highScore){
		for(int i=0; i<history.size(); i++){
			if(history.get(i).getNameLoser().equals(player.getName())){
				if(history.get(i).getLoserScore() == player.getHighScore())
					return i;
			}
			else
				if(history.get(i).getNameWinner().equals(player.getName())){
					if(history.get(i).getWinnerScore() == player.getHighScore())
						return i;
				}
		}
		return -1;

	}

	/**
	 * Shows the history by user high score.
	 */
	public static void showHistoryWithHighestScore(){
		JFrame frame = new JFrame("History");

		//Sorts the profiles by alphabetical order.
		profiles = ProfileManagement.getProfiles();
		int[] sortedByName = Sorter.sortByName(profiles);

		//Creates the headers and the data arrays for the upcoming table.
		String[] headers= {"Name", "Date", "Score Achieved", "Opponent", "Opponent's Score"};
		Object[][] data = new Object[profiles.size()][5];

		//Fills the data table.
		for(int i=0; i<profiles.size(); i++){		
			int index = findDataHistory(profiles.get(sortedByName[i]), profiles.get(sortedByName[i]).getHighScore());
			if(index == -1){
				data[i][0] = profiles.get(sortedByName[i]).getName();
				data[i][1] = "-";
				data[i][2] =  "-";
				data[i][3] =  "-";
				data[i][4] =  "-";
			}
			else
				if(history.get(index).getNameWinner().equals(profiles.get(sortedByName[i]).getName())){
					data[i][0] = history.get(index).getNameWinner();
					data[i][1] = history.get(index).getDate();
					data[i][2] = history.get(index).getWinnerScore();
					data[i][3] = history.get(index).getNameLoser();
					data[i][4] = history.get(index).getLoserScore();
				}
				else{
					data[i][0] = history.get(index).getNameLoser();
					data[i][1] = history.get(index).getDate();
					data[i][2] = history.get(index).getLoserScore();
					data[i][3] = history.get(index).getNameWinner();
					data[i][4] = history.get(index).getWinnerScore();
				}		

		}

		//Creates the table.
		JTable table = new JTable(data, headers);

		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		//		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		//		table.setRowSorter(sorter);
		//		sorter.setComparator(1, new DateComparator());

		//Sets the width of the columns.
		TableColumn column = null;
		for (int i = 0; i < 5; i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 1) {
				column.setPreferredWidth(100); 
			} else {
				column.setPreferredWidth(25);
			}
		}

		//Creates the scroll pane that contains the table, and adds it to the frame.
		JScrollPane window = new JScrollPane(table);

		frame.add(window);
		frame.setSize(700,400);
		frame.setVisible(true);

	}

	/**
	 * Shows  all the games played.
	 */
	public static void showAllHistory(){
		JFrame frame = new JFrame("History");

		//Sorts the players alphabetically.
		profiles = ProfileManagement.getProfiles();
		int[] sortedByName = Sorter.sortByName(profiles);

		//Creates the column headers for the upcoming table.
		String[] headers= {"Name", "Date", "Score Achieved", "Opponent", "Opponent's Score"};
		Object[][] data = new Object[history.size()][5];

		//Fills the table.
		for(int i=0; i<history.size(); i++){		
			data[i][0] = history.get(i).getNameWinner();
			data[i][1] = history.get(i).getDate();
			data[i][2] = history.get(i).getWinnerScore();
			data[i][3] = history.get(i).getNameLoser();
			data[i][4] = history.get(i).getLoserScore();
		}

		//Creates the table.
		JTable table = new JTable(data, headers);

		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		//		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		//		table.setRowSorter(sorter);
		//		sorter.setComparator(1, new DateComparator());

		//Sets the column width.
		TableColumn column = null;
		for (int i = 0; i < 5; i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 1) {
				column.setPreferredWidth(100); 
			} else {
				column.setPreferredWidth(25);
			}
		}

		//Creates the scroll pane with the table, and adds it to the frame.
		JScrollPane window = new JScrollPane(table);

		frame.add(window);
		frame.setSize(700,400);
		frame.setVisible(true);
	}


}
