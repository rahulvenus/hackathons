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

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/* Class which enbales the user to play the game
 * 
 */
public class Player  extends UnicastRemoteObject implements PlayTheGame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static {
		System.setProperty("java.security.policy",
				System.getProperty("user.dir")+ "/server.policy");
	}
	
	
	static int numInitializedPlayers = 0;
	private String name;
	public int playerNo;
	private int score;
	static int finishedNumPLayers = 0;
	
	
	public boolean playTheGame = false;
	public boolean finishedTheGame = false;
	public static boolean gameIsOver = false;
	
	public static Object finishedGameNotification = new Object();
	
	
	//For RMI
	//to predict whether the player completed the game
	boolean wonGame = false;
	//to predict  whether the each time character guess was sucess
    boolean guessCorrect = false;
    //to predict whether the guess is completed
    boolean guessCompleted = false;
    //to check for individual player
    boolean roundOverForPlayer = false;
    
	String wordToFind;
	int round = 1;
	int chances = 1;
	char character;
	
    int length;
    int tempLength = 1;
    int[] check = new int[20];
    
	public void updateRound(){
		
		round++;
		for(int i=0;i<20;i++){
			check[i] = 0;
		}
		tempLength = 1;
		chances = 1;
		wonGame = false;
		guessCorrect = false;
		guessCompleted = false;
	}
    
	public int getRound(){
		
		System.out.println("getround="+wonGame);
		if((roundOverForPlayer == true) || (wonGame) || (chances == 3)){
			updateRound();
			if(round > 2){
				roundOverForPlayer = true;
			}
			else
				setWordToPredict();
		}
		if(round<2)
			setWordToPredict();
		return round;
	}
	
	public int getChance(){
		return chances;
	}
	
	public String getPredictedWord(){
		
        length = wordToFind.length();
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
     	return tempS;
	}
	
	public void nextInputCharFromUser(char c){
		character = c;
	}
	
	public boolean checkPrediction(){
		 try {
			playHangMan();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!(guessCorrect)) {
     		//System.out.println("Round " + round + " Over!!!");
     		//load the hangman based on the round
     		//loadHangman(round);
     		//System.out.println("WRONG_PREDICTION");
     		chances++;
     		guessCorrect = false;
     		if(chances > 3)
     			System.out.println("Chances >3");
     	}
     	else
     	{
     		System.out.println("RIGHT_PREDICTION");
     		//load the hangman based on the round
     		//loadHangman((round-1));
     		//System.out.println("not loading the hangman");
     	}
		return guessCorrect;
	}
	
	public boolean wordPredicted(){
		
		//System.out.println("wonGame="+wonGame);
		if((wonGame))
			return true;
		else
			return false;
	}
	
	public int getScore(){
		return score;
		
	}
	
	public void setWordToPredict(){
		
		System.out.println("setWordToPredict rount = "+round);
		wordToFind = HangmanServerModel.wordToFind[round-1]; 
	}
	public String getWordToPredict(){
		return wordToFind;
	}
	
	public boolean gameIsOver(){
		
		if(((round+1) > HangmanServerModel.totalOveralRounds) && (chances == 3)  || 
		   ((round+1) > HangmanServerModel.totalOveralRounds) && (wonGame == true))
				
	{
			System.out.println("Game is over");
			finishedNumPLayers++;
			synchronized(finishedGameNotification){
				finishedGameNotification.notifyAll();
			}
			return true;
		}			
		return false;
		
	}
	
	public boolean gameOverForAll(){
		while(finishedNumPLayers < HangmanServerModel.noOfPlayers){
			synchronized(finishedGameNotification){
				try {
					finishedGameNotification.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//sendMessgetoClient("MESSAGE "+finishedNumPLayers+" "+HangmanServerModel.noOfPlayers);
		}
	return true;
		
	}
	
	
	public Player() throws RemoteException {}
	
	public Player(String name, int playerNo) throws RemoteException{
       
        this.name = name;
        this.playerNo = playerNo;
        System.out.println("WELCOME "+name+" HangmanClient"+playerNo);
        HangmanServerModel.player[numInitializedPlayers] = this;
        numInitializedPlayers++;
        
        try 
        { 
        	
		Registry registry = LocateRegistry.getRegistry(2999);
            	registry.rebind("client"+playerNo, this);
		//Naming.rebind("client"+playerNo, this);

        } 
        catch (Exception e) 
        { 
            System.out.println("Player construction error: " + e.getMessage()); 
            e.printStackTrace(); 
        }
	}
	
	public String connected(int playerNumber){
		
		return("Player "+playerNumber+" is connected .. ");
		
	}
	public void playTheGame(){
		
		try {
			playHangMan();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void playHangMan() throws IOException{
		
		if(round <= HangmanServerModel.totalOveralRounds){

			        
	        //System.out.println("MESSAGE Word to predict : " + wordToFind);  
	        //System.out.print(round+" "+tempLength+ " "+length );
	        
	        //Game is played for 8 rounds here
	        //Checks whether the round is valid and word is left to complete
	        if(chances <= 3 ){
	        		//System.out.println("\n*** Chance "+ chances +" ***");
	        		//System.out.println("ROUND"+(round+1));
	        		//System.out.println("CHANCE"+chances);	

	        		character = Character.toLowerCase(character);
	    			//System.out.println("Predited char = "+c);
	    			//System.out.println("MESSAGE Predited char = "+character);
	    		
	    			guessCorrect = false;
	             	guessCompleted = false;
	             	
	             	//finds whther the character is present in the word
	             	for(int j=0; j<wordToFind.length(); j++){
	             		if(check[j] == 0){
	             			if(wordToFind.charAt(j) == character){
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
			        	 System.out.println("WORD_PREDICTED");
			        	 score += returnScore(round);
	             	}
	             	
	        }
	     }
	}
	
	public String[] scoreBoard(){
		int lineCounter = 0;
		String scoreb[] =new String[6];
		scoreb[lineCounter++]	=	"LEADER_BOARD";
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
				scoreb[lineCounter++] = ("Rank-"+(i+1)+ " You "+ " Score = "+ this.score);
			}
			else{
				scoreb[lineCounter++] = ("Rank-"+(i+1)+" "+HangmanServerModel.player[i].name + " Score = "+ HangmanServerModel.player[i].score);
			}
			
		}
		return scoreb;
	
	}
	
	/**	Function to find the score of the player based on the round
	 *  @return int score
	*/		
		
	private static int  returnScore(int round){
		return (((1000/8))*(8-round+1));
	}

	@Override
	public String connected() {
		// TODO Auto-generated method stub
		return "connected";
	}
	
}
