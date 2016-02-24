/* 
 * GameOnThreadsModel.java 
 * 
 * @version: $Id: GameOnThreadsModel.java,v 1.70 2015/09/08 12:00:00 
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


/* The model class for the game 
 * 
 */
public class GameOnThreadsModel {
	
	Player player1;
	Player player2;
	public static String wordToFind;
	public int noOfPlayers;
	
	public int totalOveralRounds = 2;
	public int totalOveralCompletedRounds = 0;
	
	public int totalNoOfChancesInEachRound = 8;
	
	public GameOnThreadsModel(){
		player1 = new Player("player1",0);
		player2 = new Player("player2",0);
		noOfPlayers = 2;
	}
}
