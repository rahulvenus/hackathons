/* 
 * HangmanServerController.java 
 * 
 * @version: $Id: HangmanServerController.java,v 1.70 2015/09/08 12:00:00 
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
import java.net.ServerSocket;
import java.util.Random;
import java.util.Scanner;


/* Class which acts as the controller for the server and the logic is controlled
 * 
 * 
 */
public class HangmanServerController {
	
	HangmanServerModel serverModel;
	
	public HangmanServerController(HangmanServerModel model){
		serverModel = model;
		countNoWordsinFile();
		
		for (int i = 0; i < model.totalOveralRounds; i++) {
			model.wordToFind[i] = getRandomWord(); 
		}
	}

	public void startGameServer(int numOfPlayers) throws IOException {
		
		ServerSocket[] listener = new ServerSocket[4]; 
		if(numOfPlayers == 2){
			listener[0] = new ServerSocket(8901);
			listener[1] = new ServerSocket(8902);
		}
		else{
			listener[0] = new ServerSocket(8901);
			listener[1] = new ServerSocket(8902);
			listener[2] = new ServerSocket(8903);
			listener[3] = new ServerSocket(8904);
		}
			
		System.out.println("GAME SERVER STARTED ....");
		
		try {
			
			for(int i=0;i<numOfPlayers;i++){
				serverModel.player[i] = new Player(listener[i].accept(),("Player"+i));
			}
		}
		finally {
			for(int i=0;i<numOfPlayers;i++){
				listener[i].close();
			}
		}
		
	}
	
	/**	Function to count the words in the file 
	 *	
	 * @return void
	 */	
		
		private  void countNoWordsinFile(){
	        
			String line = null;
	        FileReader fileReader = null;

	  
	        try{
	            FileReader fin = new FileReader(serverModel.fileName);	        
	            Scanner sc = new Scanner(fin);
	            sc.useDelimiter("\n");
		        
		        while (sc.hasNext())
		        {
		        	String data = sc.nextLine();
		        	serverModel.numLines++;
		        }
	            
		    }
	        catch(FileNotFoundException f)
	        {
	        	System.out.println("Exception !!!  Could not found the file");  
	        }
	        System.out.println("The no of lines in the file : " + serverModel.numLines); 
		}
	
	/**	Function to get random word from the file 
	 *	
	 * @return String random word
	 */
		private  String getRandomWord(){
			
			FileReader fileReader = null;
			String line = new String();
	        try {
				fileReader = new FileReader(serverModel.fileName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        int counter = 0;
	        Random rand = new Random();
	        int min = 0 , max = serverModel.numLines;
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
