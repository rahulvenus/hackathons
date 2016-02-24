/* 
 * Player.java 
 * 
 * @version: $Id: Player.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/* Class which enbales the user to play the game
 * 
 */
public class Player extends Thread{
	
	static int numInitializedPlayers = 0;
	private String name;
	private int score;
	static int finishedNumPLayers = 0;
	
	public boolean playTheGame = false;
	public boolean finishedTheGame = false;
	public static boolean gameIsOver = false;
	
	public static Object finishedGameNotification = new Object();
	
	public Socket socket;
	BufferedReader input;
    PrintWriter output;
	
	public Player(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
        try {
            input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME "+name);
            output.println("MESSAGE Waiting for opponents to connect");
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
        this.start();
        HangmanServerModel.player[numInitializedPlayers] = this;
        numInitializedPlayers++;
    }
	
	public Player(String name,int score){
		name = this.name;
		score = this.score;
		//System.out.println("Player "+name+" created !!!");
		this.start();
	}
	
	@Override
	public void run() {
		
		output.println("MESSAGE You are connected");
		try {
			playHangMan();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exit of thread ... ");
		
	}
	
	public void playHangMan() throws IOException{
		
		int counter = 0;
		
		while(counter < HangmanServerModel.totalOveralRounds){

		
			output.println("MESSAGE Going to start Round :"+(counter+1));	
			
			//to predict whether the player completed the game
			boolean wonGame;
			//to predict  whether the each time character guess was sucess
	        boolean guessCorrect ;
	      //to predict whether the guess is completed
	        boolean guessCompleted ;
	        
			int chances = 1;
	        int length;
	        int tempLength = 1;
	        int[] check = new int[20];
	        //int score = 0;

	        //To get character each time for 
	        char c;

	        String temp = new String();
	        String s;
	        String response = new String();
	        
	        guessCorrect = false;
	        guessCompleted = false;
			wonGame = false;
			
			String wordToFind = HangmanServerModel.wordToFind[counter]; 

	        length = wordToFind.length();
	        
	        output.println("MESSAGE Word to predict : " + wordToFind);  
	        //System.out.print(round+" "+tempLength+ " "+length );
	        
	        //Game is played for 8 rounds here
	        //Checks whether the round is valid and word is left to complete
	        while((chances <= 3 ) && (tempLength <= (length))){
	        		//System.out.println("\n*** Chance "+ chances +" ***");
	        		output.println("ROUND"+(counter+1));
	        		output.println("CHANCE"+chances);
	        		
	        		//System.out.print("Predicted Word : ");
	             	String tempS = "";
	             	//prints the predicted characters till now
	             	for(int j=0; j<wordToFind.length(); j++){
	             		if(check[j] == 1){
	             			tempS += wordToFind.charAt(j);
	             		}
	             		else
	             			tempS +="-";
	       
	             	}
	             	
	             	output.println("PREDICTED"+tempS);
	             	
	             	output.println("GET_NEXT_CHAR");
	             	while(true){
	             		
	    				response = input.readLine();
	    				if(response == null){
	    					output.println("MESSAGE NULL");
	    				}
	    				else{
	    					c = response.charAt(14);
	    					c = Character.toLowerCase(c);
	    					//System.out.println("Predited char = "+c);
	    					output.println("MESSAGE Predited char = "+c);
	    					break;
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
	             		//System.out.println("Round " + round + " Over!!!");
	             		//load the hangman based on the round
	             		//loadHangman(round);
	             		output.println("WRONG_PREDICTION");
	             		chances++;
	             		guessCorrect = false;
	             		wonGame = false;
	             	}
	             	else
	             	{
	             		output.println("RIGHT_PREDICTION");
	             		//load the hangman based on the round
	             		//loadHangman((round-1));
	             		//System.out.println("not loading the hangman");
	             	}
	            }

	        if(!(wonGame)){
	        	
	        	output.println("MESSAGE : GAME ROUND OVER...");
	        	
	        }
	        else
	        {
	        	 output.println("WORD_PREDICTED");
	        	 score += returnScore(chances);
	        	 
	        }
	        output.println("SCORE"+score);
			counter++;
		}
		finishedNumPLayers++;
		output.println("SCORE"+score);
		output.println("MESSAGE : GAME FINISHED .. Wait for others to finsh to print the leader board...");

		
		while(finishedNumPLayers < HangmanServerModel.noOfPlayers){
			synchronized(finishedGameNotification){
				try {
					finishedGameNotification.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//output.println("MESSAGE "+finishedNumPLayers+" "+HangmanServerModel.noOfPlayers);
		}
		finishedGameNotification.notifyAll();
		output.println("MESSAGE : GAME FINISHED .. All others finished going to print the leader board...");
		printLeaderBoard();
		output.flush();
		output.println("CLOSECLIENT");
	}
	
	public void printLeaderBoard(){
		output.println("LEADER_BOARD");
		Player temp;
		
		for(int i = 0;i<HangmanServerModel.noOfPlayers;i++){
			for(int j=i+1;j<HangmanServerModel.noOfPlayers;j++){
				if(HangmanServerModel.player[i].score < HangmanServerModel.player[j].score){
					temp = HangmanServerModel.player[i];
					HangmanServerModel.player[i] =HangmanServerModel.player[j];
					HangmanServerModel.player[j] = temp;
				}
			}
		}
		
		for(int i = 0;i<HangmanServerModel.noOfPlayers;i++){
				if(HangmanServerModel.player[i] == this){
					output.println("Rank-"+(i+1)+ " You "+ " Score = "+ this.score);
				}
				else{
					output.println("Rank-"+(i+1)+" "+HangmanServerModel.player[i].name + " Score = "+ HangmanServerModel.player[i].score);
				}
		}
	
	}
	
	/**	Function to find the score of the player based on the round
	 *  @return int score
	*/		
		
	private static int  returnScore(int round){
		return (((1000/8))*(8-round+1));
	}
	
}
