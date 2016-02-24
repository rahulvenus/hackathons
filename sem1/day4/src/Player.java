/* 
 * Player.java 
 * 
 * @version: $Id: Player.java,v 1.00 2015/09/21 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/21 12:00:00 
 */

import java.util.Scanner;

/**
 * This class  implements PlayerInterface 
 */


public class Player implements PlayerInterface{
	
	
	 /**
	  * Below are the variables to store the details of the player *
	  */
	
	private String playerName = new String();
	private char symbol;
	private Connect4Field oConnect4Field;
	
	/**
	 * This is the player constructor to initialize the players and the board* 
	 *
	 * @param       args    
	 * @return      void   It does not return any value and simply returns
	 */

	Player(Connect4Field aConnect4Field ,String playerNam, char sym){
		
		this.playerName = playerNam;
		this.symbol = sym;
		this.oConnect4Field = aConnect4Field;
	}
	
	/**
	 * Function to return the game piece of the player * 
	 *
	 * @param       none    
	 * @return      char   return the game piece of the player
	 */
	 public char getGamePiece(){
		 return symbol;
		 
	 }
	 
	/**
	 * Function to return the name of the player * 
	 *
	 * @param       none    
	 * @return      String   return the name of the player
	 */
	 
	 public String getName(){
		 
		 return playerName;
		 
	 }
	 
	/**
	 * Function to make the next move of the player * 
	 *
	 * @param       none    
	 * @return      int   return the next move of the player
	 */
	 public int  nextMove(){
		 int lastMove = -1;
		 while(true){
			 System.out.println(oConnect4Field);
			 System.out.println("Player "+ playerName +" :Enter your move (column no ):");
			 Scanner in = new Scanner(System.in);
			 int num = in.nextInt();
			 System.out.println("Your move in :" + num);
			 if(oConnect4Field.checkIfPiecedCanBeDroppedIn(num)){
				 oConnect4Field.dropPieces(num,symbol);
				 System.out.println(oConnect4Field);
				 lastMove = num;
				 break;
			 }
			 else{
				 System.out.println("Your move is not possible !!! \n");
				 System.out.println("Enter Again!!");
			 }
			 
		 }
		return lastMove;
	 }

}
