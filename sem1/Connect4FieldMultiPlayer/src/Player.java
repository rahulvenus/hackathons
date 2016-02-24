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
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class  implements PlayerInterface 
 */
public class Player implements PlayerInterface{
	
	 /**
	  * Below are the variables to store the details of the player *
	  */
	public String playerName = new String();
	private char symbol;
	private char lastGamePiece;
	private Connect4Field oConnect4Field;
	private int myMoves = 0;
	
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
			 System.out.println("Player "+ playerName +" :Enter your move "
			 		+ "(GamePiece = '"+ symbol+"' )(column no ?):");
			 Scanner in = new Scanner(System.in);
			 int num = 0;
			 try{
				 num = in.nextInt();
				 System.out.println("Your move in :" + num);
				 if(oConnect4Field.checkIfPiecedCanBeDroppedIn(num)){
					 oConnect4Field.dropPieces(num,symbol);
					 //System.out.println(oConnect4Field);
					 lastMove = num;
					 break;
				 }
				 else{
					 System.out.println("Your move is not possible !!! \n");
					 System.out.println("Enter Again!!");
				 }
			 }
			 catch(InputMismatchException I){
				 System.out.println("You didnt enter a number !!! \n");
				 System.out.println("Enter Again!!");
			 }
			 
		 }
		 System.out.println(oConnect4Field);
		return lastMove;
	 }
	 
	/**
	* Function to make the computer move * 
	*
	* @param       none    
	* @return      int   return the next move of the player
	*/
	 public int  nextComputerMove(){
		 int lastMove = -1;
		 System.out.println("\nIts now computers turn !!! ");
		 if(myMoves == 0){
			 int count =0;
			 for(int j=0;j<oConnect4Field.coloumns;j++){
				 if(oConnect4Field.board[(oConnect4Field.rows)-1][j] == 'O'){
					 oConnect4Field.dropPieces(j,symbol);
					 myMoves++;
					 lastMove = j;
					 break;
				 }	 
			 }
		 }
		 else{
			 	//System.out.println("Going to calculate move");
				int lastRow = 0;
				int i,j;
				int posScore[][] =new int[oConnect4Field.rows][oConnect4Field.coloumns];
				int oppPosScore[][] =new int[oConnect4Field.rows][oConnect4Field.coloumns];
				char prioritySymbol;
				int bigScore = 0;
				int rowBig = 0;
				int colBig = 0;;
				//To check Vertically
				
				for(j=(oConnect4Field.coloumns)-1 ; j>=0;j--){
					for(i= (oConnect4Field.rows)-1 ; i>=0 ; i--){
	
						if((oConnect4Field.board[i][j] == 'O')){
							 prioritySymbol = symbol;
							 posScore[i][j] = calcScorePosition(i,j,prioritySymbol); 
							 if(posScore[i][j] > bigScore){
								 rowBig = i;
								 colBig = j;
								 bigScore =posScore[i][j];
							 }
							 prioritySymbol = oConnect4Field.player1.getGamePiece();
							 oppPosScore[i][j] = calcScorePosition(i,j,prioritySymbol);
							 if(oppPosScore[i][j] >=bigScore){
								 rowBig = i;
								 colBig = j;
								 bigScore =oppPosScore[i][j];
							 }
							 //@System.out.println("bigScore="+bigScore);
						}
						else{
							posScore[i][j] = 0;
							 oppPosScore[i][j] = 0;							 
						}
						
					}
				}
				
				//@System.out.println("bigScore="+bigScore+" rowBig="+rowBig+" colBig="+colBig);
		 
				
				System.out.println("Computer move in :" + colBig);
				 
				if(oConnect4Field.checkIfPiecedCanBeDroppedIn(colBig)){
					 oConnect4Field.dropPieces(colBig,symbol);
					 //System.out.println(oConnect4Field);
				}
				else{
					 System.out.println("Error!!");
				 }
				lastMove = colBig;
		 }
		return lastMove;
	 }
	 
	/**
	* Function to calculate the score of each position to make the next move * 
	*
	* @param       rowNum,     colNum      prioritySymbol
	* @return      int   return the next move of the player
	*/
	 public int  calcScorePosition(int rowNum,int colNum,char prioritySymbol ){
		 
		 int startRowPos = rowNum;
		 int endColPos = colNum;
		 int i,j;
		 int counter = 0;
		 int scoreVertical = 1;
		 int scoreHorizontal = 1;
		 int scoreDiagonalRight = 1;
		 int scoreDiagonalLeft = 1;
		 int totalScore =0 ;
		 
		 //@System.out.println("prioritySymbol"+prioritySymbol);
		//calculate vertically down
		 for(i=rowNum+1,j=colNum;i<oConnect4Field.rows;i++){
			 if((oConnect4Field.board[i][j] == prioritySymbol)){
				 scoreVertical = 10*scoreVertical;
				 counter++;
				 if(counter == 3)
					 break;
			 }
			 else
				 break;
		 }
		 //@System.out.println("scoreVertical"+scoreVertical);
		 counter = 0;
		//calculate horizontally left
		 for(i=rowNum,j=colNum-1;j>=0;j--){
			 if((oConnect4Field.board[i][j] == prioritySymbol)){
				 scoreHorizontal = 10*scoreHorizontal;
				 counter++;
				 if(counter == 3)
					 break;
			 }
			 else
				 break;
		 }
		 //@System.out.println("scoreHorizontal"+scoreHorizontal);
		//calculate horizontally right		 
		 for(i=startRowPos,j=colNum+1;j<oConnect4Field.coloumns;j++){
			 if((oConnect4Field.board[i][j] == prioritySymbol)){
				 scoreHorizontal = 10*scoreHorizontal;
				 counter++;
				 if(counter == 3)
					 break;
			 }
			 else
				 break;
		 }
		 //@System.out.println("scoreHorizontal"+scoreHorizontal);
		 counter = 0;
		 
		 //calculate diagonally right down
		 for(i=startRowPos+1,j=colNum-1;(j>=0)&&(i<oConnect4Field.rows);j--,i++){
			 if((oConnect4Field.board[i][j] == prioritySymbol)){
				 scoreDiagonalRight = 10*scoreDiagonalRight;
				 counter++;
				 if(counter == 3)
					 break;
			 }
			 else
				 break;
		 }
		 //@System.out.println("scoreDiagonalRight"+scoreDiagonalRight);
		 
		//calculate diagonally right up
		 for(i=startRowPos-1,j=colNum+1;(j<oConnect4Field.coloumns)&&(i>=0);j++,i--){
			 if((oConnect4Field.board[i][j] == prioritySymbol)){
				 scoreDiagonalRight = 10*scoreDiagonalRight;
				 counter++;
				 if(counter == 3)
					 break;
			 }
			 else
				 break;
		 }
		 if((rowNum < (oConnect4Field.rows))&&((colNum < (oConnect4Field.coloumns-1)) &&
				 (oConnect4Field.board[rowNum][colNum+1]) == 'O'))
			 scoreDiagonalRight = scoreDiagonalRight/10;
		 
		 //@System.out.println("scoreDiagonalRight"+scoreDiagonalRight);
		
		 counter = 0;
		
		//calculate diagonally left down
		 for(i=startRowPos+1,j=colNum+1;(j<oConnect4Field.rows)&&(i<oConnect4Field.rows);j++,i++){
			 if((oConnect4Field.board[i][j] == prioritySymbol)){
				 //@System.out.println("match found :scoreDiagonalLeft");
				 scoreDiagonalLeft = 10*scoreDiagonalLeft;
				 counter++;
				 if(counter == 3)
					 break;
			 }
			 else
				 break;
		 }
		 
		 //calculate diagonally left up
		 for(i=rowNum-1,j=colNum-1;(j>=0)&&(i>=0);j--,i--){
			 if((oConnect4Field.board[i][j] == prioritySymbol)){
				 scoreDiagonalLeft= 10*scoreDiagonalLeft;
				 counter++;
				 if(counter == 3)
					 break;
				 else if((i < (oConnect4Field.rows))&& (j >0) &&
						 ((oConnect4Field.board[i][j-1]) == 'O')){
					 scoreDiagonalLeft= scoreDiagonalLeft/10;
				 }
					 
			 }
			 else
				 break;
		 }
		 //@System.out.println("scoreDiagonalLeft"+scoreDiagonalLeft);
		 
		 totalScore  = scoreVertical + scoreHorizontal + scoreDiagonalRight + scoreDiagonalLeft;
		 //@System.out.println("rowNum="+rowNum+"colNum="+colNum+"prioritySymbol="+prioritySymbol+" totalScore="+totalScore);
		 
		 
		 return totalScore;
		 
	 }
}
