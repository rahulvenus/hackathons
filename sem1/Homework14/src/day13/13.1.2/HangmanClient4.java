/* 
 * HangmanClient1.java 
 * 
 * @version: $Id: HangmanClient1.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Scanner;

/* Class which enables the client1 to play the game
 * 
 */

public class HangmanClient4 {

    public static int PLAYER_NUM = 3;
    public BufferedReader in;
    static String serverAddress;
    
	static {
		System.setProperty("java.security.policy",
				System.getProperty("user.dir")+ "/client.policy");
	}
	
	public HangmanClient4(){
		// Setup networking
		serverAddress = "localhost";
	
	}
	
	public static void main(String[] args) {
		if(args.length == 1){
			serverAddress = args[0];

			try {
				playGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Wrong usage!");
			System.out.println("Correct Usage : HangmanClient4 <serveraddress>");
		}
			
	
	}
	
	private static void playGame() throws IOException {
		
		
		 System.out.println("playGame");
		// I download server's stubs ==> must set a SecurityManager 
        System.setSecurityManager(new RMISecurityManager());
        System.out.println("reached");

        try 
        { 
           PlayTheGame client = (PlayTheGame) Naming.lookup( "//" + 
        		   serverAddress + 
                "/client4");         //objectname in registry 
           System.out.println("reached");
           
           System.out.println(client.connected());
           System.out.println("Server Accepted the connection");
           while(!client.gameIsOver()){
				
        	   	System.out.print("\n Round:" + client.getRound());
	
				System.out.print(" Chance:" + client.getChance());
				
				//client.setWordToPredict();

				System.out.print("\nword to predict :" + client.getWordToPredict());
				System.out.print("\n Predicted Word:" + client.getPredictedWord());
				
					
				Scanner in = new Scanner(System.in);
				String s;
				char preditedChar;
				System.out.print("\n Predict a character : ");
				//ask for the next character
			     while(true){
			    	 //System.out.print("enter");
			         s = in.next(); 
			         
				     try{
				     	preditedChar = s.charAt(0); 	
				     	break;
				     }
				     catch(StringIndexOutOfBoundsException E)
				     {
				     	System.out.println("Enter a valid character !!");	
				     }	
			     }
			    client.nextInputCharFromUser(preditedChar);
			    System.out.println(" Predicted Char= "+preditedChar);
				
				
				if(client.checkPrediction()){
					System.out.print("\n Right Prediction !!!!");
				}
				else{
					System.out.print("\n Wrong Prediction !!!!");
				}
           
				if(client.wordPredicted()){
					System.out.print("\n Word Predicted !!!! Nice PLay!!!");
					System.out.print("\n Score:" + client.getScore());
				}
           }
           
           System.out.println("\n Going to print scoreboard");
           if(client.gameOverForAll()){
        	   String scoreBoardFromServer[] = client.scoreBoard();
        	   int lineCounter = 0;
        	   while((lineCounter < scoreBoardFromServer.length) &&
        			   (scoreBoardFromServer[lineCounter] != null)){
        		   System.out.println(scoreBoardFromServer[lineCounter++]);
        	   }
           }
				
        } 
        catch (Exception e) 
        { 
           System.out.println("HangmanClient1 exception: " + e.getMessage()); 
           e.printStackTrace(); 
        }
	} 
		
}









