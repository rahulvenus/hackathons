/* 
 * GameOnThreadsController.java 
 * 
 * @version: $Id: GameOnThreadsController.java,v 1.70 2015/09/08 12:00:00 
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

/* The controller class for the game running different players on multiple threads 
 * 
 */
public class GameOnThreadsController {
	
	GameOnThreadsModel gameModel;
	GameOnThreadsView gameView;

	public static boolean gameIsOver = false;
	
	static private String fileName = "words.txt";
    static private int numLines = 0;
	//Game word

	public GameOnThreadsController(GameOnThreadsModel gameModel, GameOnThreadsView gameView){
	
		this.gameModel =gameModel;
		this.gameView = gameView;
		

		 // To count the no of words in the file
        countNoWordsinFile();
   
		
	}
	
	/* Function to make the user to play the next round  
	 * 
	 */
	public static int playNextRound(){
		int score = 0;
		//System.out.println("Start playNextRound .....");
		score = playHangman();
		//System.out.println("End playNextRound .....");
		return score;
	}
	
	/* Function to start the game   
	 * 
	 */
	public void startGame(){
		

		String playerName[] = gameView.getPlayerNamesFromUser(gameModel.noOfPlayers);
		
		int count = 0;

		gameModel.player1.name = playerName[0];
		gameModel.player2.name = playerName[1];
		
		System.out.println("Player1 Name ="+gameModel.player1.name );
		System.out.println("Player2 Name ="+gameModel.player2.name );
		
		
		while(!gameIsOver){
			
			
			if(gameModel.totalOveralRounds > gameModel.totalOveralCompletedRounds)
				gameModel.totalOveralCompletedRounds++;
			else{
				System.out.println("\n\nRounds are done!!!");
				gameIsOver = true;
				break;
			}
			
			System.out.println("*********************** Round = "+gameModel.totalOveralCompletedRounds);
			
	        gameModel.wordToFind = getRandomWord();  
	        
	        System.out.println("\n\nplayer1 Game :" + gameModel.player1.name);
	        //view print player 1 details 
			
	        synchronized (gameModel.player1.indicationToStart) {
				gameModel.player1.playTheGame = true;
				gameModel.player1.finishedTheGame = false;
				gameModel.player1.indicationToStart.notify();
			}
			
			synchronized (gameModel.player1.indicationGameFinished){
				while(!gameModel.player1.finishedTheGame){
					//WAIT TILL PLAYER1 finishes
					try {
						gameModel.player1.indicationGameFinished.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			System.out.println("\n\n\nplayer2 Game :" + gameModel.player2.name);
			//view print player 2 details 
			
			synchronized (gameModel.player2.indicationToStart) {
				gameModel.player2.playTheGame = true;
				gameModel.player2.finishedTheGame = false;
				gameModel.player2.indicationToStart.notify();		
			}
			
			synchronized (gameModel.player2.indicationGameFinished){
				while(!gameModel.player2.finishedTheGame){
					//WAIT TILL PLAYER1 finishes
					try {
						gameModel.player2.indicationGameFinished.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
	
			printLeaderBoard();
			
		}
		
		synchronized (gameModel.player1.indicationToStart) {
			gameModel.player1.playTheGame = true;
			gameModel.player1.indicationToStart.notify();
		}
		
		synchronized (gameModel.player2.indicationToStart) {
			gameModel.player2.playTheGame = true;
			gameModel.player2.indicationToStart.notify();		
		}
		
		printLeaderBoard();
		printWinner();
		System.out.println("Exit of game");
	}
	
	/**	Function to count the words in the file 
	 *	
	 * @return void
	 */	
		
		private static void countNoWordsinFile(){
	        
			String line = null;
	        FileReader fileReader = null;

	  
	        try{
	            FileReader fin = new FileReader(fileName);	        
	            Scanner sc = new Scanner(fin);
	            sc.useDelimiter("\n");
		        
		        while (sc.hasNext())
		        {
		        	String data = sc.nextLine();
		        	numLines++;
		        }
	            
		    }
	        catch(FileNotFoundException f)
	        {
	        	System.out.println("Exception !!!  Could not found the file");  
	        }
	        //System.out.println("The no of lines in the file : " + numLines); 
		}
		
		
		/* Function to print the leader board  
		 * 
		 */
		public void printLeaderBoard(){
			
			gameView.printDetails(this.gameModel.player1);
			gameView.printDetails(this.gameModel.player2);
				
		}
		
		/* Function to print the winner   
		 * 
		 */
		public void printWinner(){
			
			if(this.gameModel.player1.score == this.gameModel.player2.score)
				gameView.printTie();
			if(this.gameModel.player1.score > this.gameModel.player2.score)
				gameView.printWonDetails(this.gameModel.player1);
			else
				gameView.printWonDetails(this.gameModel.player2);
		}
		
		
		

		/**	Function to play hangman game for each player
		*   @returns void
		*/		
			private static  int playHangman(){
			
				
				//to predict whether the player completed the game
				boolean wonGame;
				//to predict  whether the each time character guess was sucess
		        boolean guessCorrect ;
		      //to predict whether the guess is completed
		        boolean guessCompleted ;
		        
				int round = 1;
		        int length;
		        int tempLength = 1;
		        int[] check = new int[20];
		        int score = 0;

		        //To get character each time for 
		        char c;

		        String temp = new String();
		        String s;
		        
		        guessCorrect = false;
		        guessCompleted = false;
				wonGame = false;
				
				String wordToFind = GameOnThreadsModel.wordToFind; 

		        length = wordToFind.length();
		        
		        System.out.println("Word to predict : " + wordToFind);  
		        //System.out.print(round+" "+tempLength+ " "+length );
		        
		        //Game is played for 8 rounds here
		        //Checks whether the round is valid and word is left to complete
		        while((round <= 3 ) && (tempLength <= (length))){
		        		System.out.println("\n*** Chance "+ round +" ***");
		             	System.out.print("Predicted Word : ");
		             	
		             	//prints the predicted characters till now
		             	for(int j=0; j<wordToFind.length(); j++){
		             		if(check[j] == 1){
		             			System.out.print(wordToFind.charAt(j));
		             		}
		             		else
		             			System.out.print("-");
		             	}
		             	
		             	c= GameOnThreadsView.askUserToPreditTheCharacter(round);
		             	
		             	guessCorrect = false;
		             	guessCompleted = false;
		             	
		             	//finds whther the character is present in the word
		             	for(int j=0; j<wordToFind.length(); j++){
		             		if(check[j] == 0){
		             			if(wordToFind.charAt(j) == c){
		             				check[j] = 1;
		             				guessCorrect = true;
		             				tempLength++;
		             				if((tempLength-1) == length)
		             					guessCompleted = true;
		             			}
		             		}
		             	}       	
		             	
		             	if(guessCompleted){
		             		wonGame = true;
		             		break;
		             	}
		             	else if(!(guessCorrect)) {
		             		//System.out.println("Round " + round + " Over!!!");
		             		//load the hangman based on the round
		             		//loadHangman(round);
		             		round++;
		             		guessCorrect = false;
		             		wonGame = false;
		             	}
		             	else
		             	{
		             		//load the hangman based on the round
		             		//loadHangman((round-1));
		             		//System.out.println("not loading the hangman");
		             	}
		            }

		        if(!(wonGame)){
		        	
		        	score = 0;
		        	System.out.println("\n\nROUND Over !!!\t\tScore:" + score);
		        }
		        else
		        {
		        	 score = returnScore(round);
		        	 
		        }
		        
		        return score;
		    }
			
			/**	Function to get random word from the file 
			 *	
			 * @return String random word
			 */
				private static String getRandomWord(){
					
					FileReader fileReader = null;
					String line = new String();
			        try {
						fileReader = new FileReader(fileName);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			        BufferedReader bufferedReader = new BufferedReader(fileReader);
			        int counter = 0;
			        Random rand = new Random();
			        int min = 0 , max = numLines;
			        int randomNum = (new Random()).nextInt((max - min));
			        //System.out.println(randomNum);  
			        
			        int i = 0;
			        try{
						while((line = bufferedReader.readLine()) != null) {
						counter++;
						if(counter == (randomNum+1))
							break;
						}
					} 
			        catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Error in finding random word");
					}        
			        
			        line= line.toLowerCase();
			        return line;
				}
		
		/**	Function to find the score of the player based on the round
		 *  @return int score
		*/		
			
		private static int  returnScore(int round){
			return (((1000/8))*(8-round+1));
		}
		
		
}
