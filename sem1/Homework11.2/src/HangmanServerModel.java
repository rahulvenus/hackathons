/* 
 * HangmanServerModel.java 
 * 
 * @version: $Id: HangmanServerModel.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */


/* Class which acts as the model for the server and saves all the data
 * 
 * 
 */
public class HangmanServerModel {
	
	public static int noOfPlayers = 1;
	static Player[] player;
	public static String[] wordToFind;
	public boolean gameIsOver = false;
	
	public static int totalOveralRounds = 2;

	
	public String fileName = "words.txt";
	public static int numLines = 0;
	
	public HangmanServerModel(int n){
		
		noOfPlayers = n;
		player = new Player[noOfPlayers];
		wordToFind = new String[totalOveralRounds];
	}
}
