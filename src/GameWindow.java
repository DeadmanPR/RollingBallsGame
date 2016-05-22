import com.uprm.History.History;
import com.uprm.ProfileTools.ProfileManagement;
import com.uprm.TitleScreen.TitleScreenManager;

public class GameWindow {
	public static void main(String[] args) {
		//Loads the existing profiles(if any)
		ProfileManagement.loadProfilesFromFile();

		//Loads the history file.
		History.loadHistoryFromFile();

		//Runs the title screen
		TitleScreenManager.getInstance().run();




	}




}
