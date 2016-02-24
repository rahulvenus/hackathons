/* 
 * Connect4Field.java 
 * 
 * @version: $Id: Connect4Field.java,v 1.00 2015/09/21 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/21 12:00:00 
 */

public class Connect4Field implements Connect4FieldInterface{
	
	private  int rows = 6;
	private  int coloumns = 10;
	private  char[][] board= new char[rows][coloumns];
	private int totalSlots = 0;
	private int freeSlots = 0;
	Player player1,player2; 
	Player playerWon,playerCurrent;
	private int lastColumn,lastRow;
	
	/* Constructor for Connect4Field 
	 * Board is created here 
	 * Cannot be used slots is initialized to '#' and others to 'O'*/
	Connect4Field(){

		 for(int i=0;i<rows;i++){
			 for(int j=0;j<coloumns;j++){
				 board[i][j] = '#';
			 }
		 }
		
		 for(int i=0;i<rows;i++){
			 for(int j=i;j<(coloumns-i);j++){
				 board[i][j] = 'O';
				 totalSlots++;
			 }
		 }
		 
		 freeSlots = totalSlots;
		 System.out.println(" totalSlots ="+totalSlots+" freeSlots ="+freeSlots);
		}
	 
	/**
	 * This is the main method to check if pieces Can Be DroppedIn	to the
	 * mentioned column in the board * 
	 *
	 * @param       column    
	 * @return      boolean   Returns True if can be dropped in
	 */

	 public boolean checkIfPiecedCanBeDroppedIn(int column){
		 boolean checkIfCanBeDropped = false;
		 if((column >= 0)&& (column<coloumns)){
			 for(int i= 0 ; i<rows ; i++){
					 if(board[i][column] == 'O'){
						 checkIfCanBeDropped = true;
						 
						 break;
					 }
			 }
		 }
		 return checkIfCanBeDropped;
		 
	 }
	 
	 /**
		 * This is the method to drop pieces to the mentioned column in the board * 
		 *
		 * @param       column , gamePiece   
		 * @return      void   
		 */

	 public void dropPieces(int column, char gamePiece){
		 if((column >= 0)&& (column<coloumns)){
			 for(int i= rows-1 ; i>=0 ; i--){
				 if(board[i][column] == 'O'){
					 board[i][column] = gamePiece;
					 freeSlots--;
					 break;
				 }
			 }
		}		 
	 }
	 
	 /**
		 * This is the method to check if last move made user to win the game * 
		 *
		 * @param       none   
		 * @return      true if won   
		 */
	 
	 public boolean didLastMoveWin(){
		 
		 char gamePiece = playerCurrent.getGamePiece();
		 int counter = 0;
		 int i,j;
		 boolean matchWon = false;
		 
		 i = 0;
		 lastRow = 0;
		 //To check Vertically
		 for(i= rows-1 ; i>=0 ; i--){
			 if(board[i][lastColumn] == 'O'){
				 System.out.println("i="+i+" lastColumn="+lastColumn);
				 lastRow = i+1;
				 break;
			 }
		 }
		 System.out.println("lastRow="+lastRow+" lastColumn="+lastColumn);
		 for(i=lastRow; i<rows;i++){
			if(board[i][lastColumn] == gamePiece) {
				counter++;
				//System.out.println("vertically down : match found");
				if(counter == 4){
					matchWon = true;
					break;
				}
			}
			else
				break;
		 }
		 //System.out.println("counter ="+counter);
		 
		 //System.out.println("checking horizontally right");
		 //To check Horizontally
		 counter = 0;
		 //Towards Right
		 for(j = lastColumn; ((j < coloumns)&&(matchWon == false));j++){
			if(board[lastRow][j] == gamePiece) {
				//System.out.println("horizontally right : match found");
				counter++;
				if(counter == 4){
					matchWon = true;
					break;
			}
			else
				break;
			}
		 }
		 //System.out.println("counter ="+counter);
		 //System.out.println("checking horizontally left");
		 //Towards Left
		 for(j = lastColumn-1; ((j>=0)&&(matchWon == false)) ;j--){
				if(board[lastRow][j] == gamePiece) {
					System.out.println("horizontally left : match found");
					counter++;
					if(counter == 4){
						matchWon = true;
						break;
					}
				}
				else
					break;
			 }
		 //System.out.println("counter ="+counter);
		 
		//To check diagonally
		 counter = 0;
		 //System.out.println("checking diagonally right down");
		 //Towards Right down
		for(j=lastColumn,i=lastRow;((i<rows)&&(j < coloumns)&&(matchWon == false));j++,i++){
			System.out.println("diagonnalyy right down board["+i+"]["+j+"] =");
				if(board[i][j] == gamePiece) {
					System.out.println("diagonnalyy right down  :match found");
					counter++;
					if(counter == 4){
						matchWon = true;
						break;
					}
				}
				else
					break;
		}
			 System.out.println("counter ="+counter);
			 counter--;
			//Towards Right up 
			 for(j=lastColumn,i=lastRow;((i>=0)&&(j >=0)&&(matchWon == false));i--,j--){
				 
					if(board[i][j] == gamePiece) {
						counter++;
						System.out.println("diagonnalyy right up  :match found");
						if(counter == 4){
							matchWon = true;
							break;
						}
					}
					else
						break;
			 }
			 
			 System.out.println("counter ="+counter);
			//To check diagonally
			 counter = 0;
			 //Towards left down
			for(j=lastColumn,i=lastRow;((i<rows)&&(j>=0)&&(matchWon == false));j--,i++){
				System.out.println("i="+i+"j="+j+"rows="+rows);
					if(board[i][j] == gamePiece) {
						counter++;
						System.out.println("diagonnalyy left down  :match found");
						if(counter == 4){
							matchWon = true;
							break;
						}
					}
					else
						break;
			}
			
				 System.out.println("counter ="+counter);
				 counter--;
				//Towards left up 
				 for(j=lastColumn,i=lastRow;((i>=0)&&(j <coloumns)&&(matchWon == false));i--,j++){
					 
						if(board[i][j] == gamePiece) {
							counter++;
							System.out.println("diagonnalyy left up  :match found");
							if(counter == 4){
								matchWon = true;
								break;
							}
						}
						else
							break;
				}
  
				 System.out.println("counter ="+counter);
		System.out.println("Match Won ="+matchWon);
		 return matchWon;
		 
	 }
	 /**
		 * This is the method to check whether the game was draw * 
		 *
		 * @param       none   
		 * @return      true if draw   
		 */
	 
	 public boolean isItaDraw(){
		 boolean isDraw = false;
		 if(freeSlots == 0){
			 isDraw = true;
		 }
		 return isDraw;
	 }
	 
	 /**
		 * This is the method to init the  game with the player details  * 
		 *
		 * @param       none   
		 * @return      true if draw   
		 */
	 
	 public void init( PlayerInterface playerA, PlayerInterface playerB ){
		 
		 player1 = (Player)playerA;
		 player2 = (Player)playerB;
		 
	 }
	 
	 /**
		 * This is the method to display the latest board with the game pieces  * 
		 *
		 * @param       none   
		 * @return      none   
		 */
	 
	 public String toString(){
		 System.out.println("Board");
		

		 System.out.println("");
		 for(int i=0;i<rows;i++){
			 System.out.print(" "+i);
			 for(int j=0;j<coloumns;j++){
				 if(board[i][j] != '#')
					 System.out.print(" "+board[i][j]);
				 else
					 System.out.print(" "+" ");
			 }
			 System.out.println();
		 }
		 System.out.print("  ");
		 for(int j=0;j<coloumns;j++){
			 System.out.print(" "+j);
		 }
		 return " ";
	 }
	 
	 /**
		 * This is the method to play the game between player1 and player2 * 
		 *
		 * @param       none   
		 * @return      none   
		 */
	 public void playTheGame(){
		 while((totalSlots-freeSlots) < 6){
					
			 player1.nextMove();
			 player2.nextMove();
		 }
		 System.out.println("freeSlots " + freeSlots + "totalSlots =" +totalSlots);
		 while(freeSlots >0){
			 lastColumn = player1.nextMove();
			 playerCurrent = player1;
			 if(didLastMoveWin()){
				 playerWon = player1;
				 break;	 
			 }
			 else{
				 lastColumn = player2.nextMove();
				 playerCurrent = player2;
				 if(didLastMoveWin()){
					 playerWon = player2;
					 break;
				 }
			 }
		 }
		 if(playerWon == null){
			 System.out.println("Game is Draw");
		 }
	     else{
	    	 System.out.println("PLAYER WON :"+playerWon.getName());
	     }
			 
	}
	 
}
