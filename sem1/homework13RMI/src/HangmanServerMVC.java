/* 
 * HangmanServerMVC.java 
 * 
 * @version: $Id: HangmanServerMVC.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */


import java.io.IOException;
import java.util.Scanner;

/* The main class which enables to run the server to connect the clients to run  the game 
 * 
 */

public class HangmanServerMVC {
	
	public static void main(String[] args) {
		
		int numPLayers = 0;

		Scanner scan;
		scan = new Scanner(System.in);
		while(true){
			System.out.println("Enter the no of players(2 or 4) ?");
			numPLayers = scan.nextInt();
			if(numPLayers ==2 || numPLayers ==4)
				break;
			else
				System.out.println("Renter choice !!");
		}
		
		HangmanServerModel serverModel = new HangmanServerModel(numPLayers);
		HangmanServerController serverController = new HangmanServerController(serverModel);

		try {
			serverController.startGameServer(numPLayers);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		scan.close();
	}
	
	

}
