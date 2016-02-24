/* 
 * Connec4FieldGame.java 
 * 
 * @version: $Id: Connec4FieldGame.java,v 1.00 2015/09/21 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/21 12:00:00 
 */

import java.util.Scanner;

public class Connec4FieldGame {
	
	public String player1Name = new String();
	public String player2Name = new String();
	public Connect4Field aConnect4Field = new Connect4Field();
	public Player aPlayer = new Player(aConnect4Field, "A", '+');
    public Player bPlayer = new Player(aConnect4Field, "B", '@');
	
	public void startGame(){
	
		System.out.println("Enter your option :\n\t\t\t1.Single Player\n\t\t\t2.Two Player");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		
		while(true){
			if(num == 2){
				System.out.println("You selected Two player");
				System.out.println("Enter player1 name:");
				player1Name = in.nextLine();
				player1Name = in.nextLine();
				aPlayer.playerName = player1Name;
				System.out.println("Enter player2 name:");
				player2Name = in.nextLine();
				bPlayer.playerName = player2Name;
				break;
			}
			else if(num == 1){
				System.out.println("You selected Single player");
				System.out.println("Enter player1 name:");
				player1Name = in.nextLine();
				player1Name = in.nextLine();
				aPlayer.playerName = player1Name;
				player2Name = "Computer";
				bPlayer.playerName = player2Name;
				break;
			}
			else{
				System.out.println("Enter correct option:");
				
			}
		}
			
		aConnect4Field.init(aPlayer,bPlayer);
		System.out.println(aConnect4Field);
		aConnect4Field.playTheGame();
	}
	
	public static void main(String args[]){
		new Connec4FieldGame().startGame();
	}
}