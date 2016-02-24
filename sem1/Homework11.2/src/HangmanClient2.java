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
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/* Class which enables the client2 to play the game
 * 
 */


public class HangmanClient2 extends Thread{

    public static int PORT = 8902;
    public Socket socket;
    public BufferedReader in;
    public PrintWriter out;
    
    
	public HangmanClient2(){
		// Setup networking
		String serverAddress = "localhost";
		try {
			socket = new Socket(serverAddress,PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void playGame() throws IOException {
		String response = new String();
		try {
			response = in.readLine();
			if(response.startsWith("WELCOME")){
				
				System.out.println("Server Accepted the connection");
			}
			
			while(true){
				response = in.readLine();
				if(response == null){
					
				}
				else if(response.startsWith("ROUND")){
					System.out.print("\n Round:" + response.substring(5));
				}
				else if(response.startsWith("CHANCE")){
					System.out.print(" Chance:" + response.substring(6));
				}
				else if(response.startsWith("PREDICTED")){
					System.out.print("\n Predicted Word:" + response.substring(9));
				}
				else if(response.startsWith("RIGHT_PREDICTION")){
					System.out.print("\n Right Prediction !!!!");
				}
				else if(response.startsWith("WORD_PREDICTED")){
					System.out.print("\n Word Predicted !!!! Nice PLay!!!");
				}
				else if(response.startsWith("WRONG_PREDICTION")){
					System.out.print("\n Wrong Prediction !!!! Going to next round !!!!");
				}		
				else if(response.startsWith("SCORE")){
					System.out.print("\n Score:" + response.substring(5));
				}
				else if(response.startsWith("GET_NEXT_CHAR")){
					
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
			     	
			      out.println("PREDICTED_CHAR"+preditedChar);
			      System.out.println(" Predicted Char= "+preditedChar);
					
				}
				else if(response.startsWith("MESSAGE")){
					System.out.println("\nMessage : " + response.substring(8));
				}
				else if(response.startsWith("LEADER_BOARD")){
					System.out.println("\n\n\t LEADER BOARD : ");
				}
				else if(response.startsWith("Rank")){
					System.out.println("\n\n\t"+response);
				}
				else if(response.startsWith("CLOSECLIENT")){
					break;
				}
				else{
					
				}
			}   
		}
		finally {
			socket.close();
		} 
	}
	
	@Override
	public synchronized void start() {
		try {
			playGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

			new HangmanClient2().start();
		
	}
}









