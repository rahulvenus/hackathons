
/* 
 * HangmanServerController.java 
 * 
 * @version: $Id: HangmanServerController.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/* Class which acts as the controller for the server and the logic is controlled
 * 
 * 
 */
public class HangmanServerController {

	HangmanServerModel serverModel;
	private Scanner sc;

	public HangmanServerController(HangmanServerModel model) {
		serverModel = model;
		countNoWordsinFile();

		for (int i = 0; i < HangmanServerModel.totalOveralRounds; i++) {
			HangmanServerModel.wordToFind[i] = getRandomWord();
		}
	}

	public void startGameServer(int numOfPlayers) throws IOException {

		System.out.println("GAME SERVER STARTED ....");

		for (int i = 0; i < numOfPlayers; i++) {
			HangmanServerModel.player[i] = new Player("Player" + (i+1),i+1);
		}
		
		System.out.println("GAME SERVER exiting ....");
	}

	/**
	 * Function to count the words in the file
	 * 
	 * @return void
	 */

	private void countNoWordsinFile() {

		try {
			FileReader fin = new FileReader(serverModel.fileName);
			sc = new Scanner(fin);
			sc.useDelimiter("\n");

			while (sc.hasNext()) {
				String data = sc.nextLine();
				HangmanServerModel.numLines++;
			}

		} catch (FileNotFoundException f) {
			System.out.println("Exception !!!  Could not found the file");
		}
		System.out.println("The no of lines in the file : " + HangmanServerModel.numLines);
	}

	/**
	 * Function to get random word from the file
	 * 
	 * @return String random word
	 */
	private String getRandomWord() {

		FileReader fileReader = null;
		String line = new String();
		try {
			fileReader = new FileReader(serverModel.fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader bufferedReader = new BufferedReader(fileReader);
		int counter = 0;
		Random rand = new Random();
		int min = 0, max = serverModel.numLines;
		int randomNum = (new Random()).nextInt((max - min));
		// System.out.println(randomNum);

		int i = 0;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				counter++;
				if (counter == (randomNum + 1))
					break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error in finding random word");
		}

		line = line.toLowerCase();
		return line;
	}

}
