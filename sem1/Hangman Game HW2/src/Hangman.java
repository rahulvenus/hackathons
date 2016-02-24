/* 
 * Hangman.java 
 * 
 * @version: $Id: Hangman.java,v 1.70 2015/09/14 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/14 12:00:00 
 */

/**
 * This program simulates a simple hangman game 
 * @author Rahul Venugopala Pillai
 * @author Abhilash Vimal
*/ 

import java.util.Random;
import java.util.Scanner;
import java.io.*;

/** 
 * This class has details regarding each player and 
 * helps each player to play the hangman game and 
 * display the score based on the rank.
 * @author  Rahul Venugopala Pillai
 * @author Abhilash Vimal
 */

public class Hangman {
	
	//No of players in the game
	static private int num = -1;
	//filename for finding words
	static private String fileName= new String();
	private static int numLines = 0;
	
	//Player details
	private String name = new String();
	private int score;
	private int rank = 0;
	
	//Game word
	private String wordToFind = new String();
	
	//main function
	public static void main(String[] args) {
	
		//To retrieve arguments and conver it into filename and player details
		Scanner in = new Scanner(System.in);
		String[] temp = new String[20];
        for (String s: args) {
        	if(num == -1){
        		//To retrieve file name
        		fileName = s;
        		System.out.println("File Name : " + fileName);
        	}
        	else{
        		//To retrieve strings other than filename
            	temp[num] = s;
            }
        	num++;
         }
        
        //Create objects dynamically based on the number of players
        Hangman hangmanpPlayers[] = new Hangman[num];
        for(int i=0; i<num;i++){
        	hangmanpPlayers[i] = new Hangman();
        	//Assign name to each player based on the arguments retrieved.
        	hangmanpPlayers[i].name = temp[i];
        }    
        
        System.out.println("The no of players : " + num);    

        // To count the no of words in the file
        countNoWordsinFile();
        
        // Game is on below for num no of players
        for(int i=0; i<num;i++){
        	System.out.println("\n\t\tPlayer "+ (i+1)+ " : " + hangmanpPlayers[i].name); 
        	//Calls the play function to play hangman
        	hangmanpPlayers[i].playHangman();
        }    
        
        //Print the leader board based on the scores obtained by each player
        printLeaderBoard(hangmanpPlayers);     
	}

/**	Function to play hangman game for each player
*   @returns void
*/		
	private  void playHangman(){
		
		Scanner input = new Scanner(System.in);
		
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

        //To get character each time for 
        char c;

        String temp = new String();
        String s;
        
        guessCorrect = false;
        guessCompleted = false;
		wonGame = false;
		
		//Random word is obtained here
        wordToFind = getRandomWord();  
        length = wordToFind.length();
        
        System.out.println("Word to predict : " + wordToFind);  
        //System.out.print(round+" "+tempLength+ " "+length );
        
        //Game is played for 8 rounds here
        //Checks whether the round is valid and word is left to complete
        while((round <= 8 ) && (tempLength <= (length))){
        		System.out.println("\n*** ROUND "+ round +" ***");
             	System.out.print("Predicted Word : ");
             	
             	//prints the predicted characters till now
             	for(int j=0; j<wordToFind.length(); j++){
             		if(check[j] == 1){
             			System.out.print(wordToFind.charAt(j));
             		}
             		else
             			System.out.print("-");
             	}
             	
             	//ask for the next character
             	while(true){
	             	System.out.print("\nPredict a character : ");
	                s = input.nextLine(); 
	                try{
	                	c = s.charAt(0);
	                	guessCompleted = true;
	                	break;
	                }
	                catch(StringIndexOutOfBoundsException E)
	                {
	                	System.out.println("Enter a valid character !!");
	                }
	             }
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
             		System.out.println("Round " + round + " Over!!!");
             		//load the hangman based on the round
             		loadHangman(round);
             		round++;
             		guessCorrect = false;
             		wonGame = false;
             	}
             	else
             	{
             		//load the hangman based on the round
             		loadHangman((round-1));
             	}
            }

        System.out.println("\nGame Word : "+wordToFind);
        if(!(wonGame)){
        	score = 0;
        	System.out.println("Game Over !!!\n\nRound = 8\t\tScore:" + score);
        }
        else
        {
        	 score = returnScore(round);
        	 System.out.println("\nWon Game!!\tRound="+round+"\t\tScore:" + score);
        }
    }
	
/**	Function to find the score of the player based on the round
 *  @return int score
*/		
	
	private static int  returnScore(int round){
		return (((1000/8))*(8-round+1));
	}
	
/**	Function to print draw the hangman diagram based on the user round
 *	
 * @return void
 */	
	
	private static void loadHangman(int round){
		//System.out.println("round =  " + round);
	/*	System.out.println("\n\n\n");
		System.out.println(" *   *     *    **   * ******* **       **     **    **   * *");
		System.out.println(" *   *    * *   * *  * *       * *     ***    * *    * *  * *");
		System.out.println(" *****   ****** *  * * * ***** *  *   * **   *****   *  * * *");
		System.out.println(" *  **  *     * *   ** *     * *   * *   *  *     *  *   **  ");
		System.out.println(" *  ** *       **    * ******* *    *    * *       * *    * *");
		*/
		switch(round){
			
			case 8 :  {
						
				System.out.println();
						System.out.println("      **************************");
						System.out.println("      *   *           *         ");
						System.out.println("      * *             *         ");
						System.out.println("      *        *     ***    * 	");
						System.out.println("      *         *   *   *  *    ");
						System.out.println("      *           *  ***  *     ");
						System.out.println("      *             *   *       ");
						System.out.println("      *            *     *      ");
						System.out.println("      *             *   *       ");
						System.out.println("      *              ***        ");
						System.out.println("   *     *         *     *      ");
						System.out.println(" *         *     *         *    ");
						System.out.println();
						break;
			}
			
					case 7 :  {
						System.out.println();
						System.out.println("      **************************");
						System.out.println("      *   *           *         ");
						System.out.println("      * *             *         ");
						System.out.println("      *              ***      	");
						System.out.println("      *             *   *       ");
						System.out.println("      *              ***        ");
						System.out.println("      *             *   *       ");
						System.out.println("      *            *     *      ");
						System.out.println("      *             *   *       ");
						System.out.println("      *              ***        ");
						System.out.println("   *     *         *     *      ");
						System.out.println(" *         *     *         *    ");
						System.out.println();
						break;
					 }
					case 6 :  {
						System.out.println();
						System.out.println("      **************************");
						System.out.println("      *   *           *         ");
						System.out.println("      * *             *         ");
						System.out.println("      *              ***      	");
						System.out.println("      *             *   *       ");
						System.out.println("      *              ***        ");
						System.out.println("      *             *   *       ");
						System.out.println("      *            *     *      ");
						System.out.println("      *             *   *       ");
						System.out.println("      *              ***        ");
						System.out.println("   *     *                      ");
						System.out.println(" *         *                    ");
						System.out.println();
						break;
				 }
				case 5 :  {
						System.out.println();
						System.out.println("      **************************");
						System.out.println("      *   *           *         ");
						System.out.println("      * *             *         ");
						System.out.println("      *              ***       	");
						System.out.println("      *             *   *       ");
						System.out.println("      *              ***        ");
						System.out.println("      *             *   *       ");
						System.out.println("      *            *     *      ");
						System.out.println("      *             *   *       ");
						System.out.println("      *                         ");
						System.out.println("   *     *                      ");
						System.out.println(" *         *                    ");
						System.out.println();
						break;
					 }
			
				case 4 :  {
						System.out.println();
						System.out.println("      **************************");
						System.out.println("      *   *           *         ");
						System.out.println("      * *             *         ");
						System.out.println("      *              ***        ");
						System.out.println("      *             *   *       ");
						System.out.println("      *              ***        ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("   *     *                      ");
						System.out.println(" *         *                    ");
						System.out.println();
						break;
					 }
				case 3 :  {
						System.out.println();
						System.out.println("      **************************");
						System.out.println("      *   *           *         ");
						System.out.println("      * *             *         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("   *     *                      ");
						System.out.println(" *         *                    ");
						System.out.println();
						break;
					 }
				case 2 :  {
						System.out.println();
						System.out.println("      **************************");
		 				System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("   *     *                      ");
						System.out.println(" *         *                    ");
						System.out.println();
						 break;
					 }
				case 1 :  {
						System.out.println();
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("      *                         ");
						System.out.println("   *     *                      ");
						System.out.println(" *         *                    ");
						System.out.println();
						break;
					 }
				case 0 :  {
					/*	System.out.println();
						System.out.println("                                ");
						System.out.println("                                ");
						System.out.println("                                ");
						System.out.println("                                ");
						System.out.println("                                ");
						System.out.println("                                ");
						System.out.println("                                ");
						System.out.println("                                ");
						System.out.println("                                ");
						System.out.println("                                ");
						System.out.println("   *     *                      ");
						System.out.println(" *         *                    ");
						System.out.println();
						break; */
					 }
		}
		
	}
	
/**	Function to print the leader board based on the scores of the players.
 * The scores will be sorted and first 4 highest scores will be displayed. 
 *	
 * @return void
 */	
		
	private static void printLeaderBoard(Hangman hangmanPlayers[]){
		
		int largest;
		Hangman temp = new Hangman();
		
		System.out.println("\n\n\t\t\t\t******LEADER BOARD*******"); 
		
		//Sorting is done here. Player with highest score will be first element 
		//as per this sorting
		for(int i=0; i<(num-1); i++){
			largest = hangmanPlayers[i].score;
			for(int j=i+1; j<num; j++){
				if(largest < hangmanPlayers[j].score){
					temp = hangmanPlayers[i];
					hangmanPlayers[i] = hangmanPlayers[j];
					hangmanPlayers[j] = temp;
				}
			}
		}
		
		if(num < 4)
			largest = num;
		else
			largest = 4;
		System.out.println("\t\tThe top "+largest+" players are:");
		for(int i=0; i<num; i++){
			System.out.println("\t\t\t\t\t"+ hangmanPlayers[i].name+ ":" + hangmanPlayers[i].score);
		}
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
}
