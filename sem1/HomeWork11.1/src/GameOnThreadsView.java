/* 
 * GameOnThreadsView.java 
 * 
 * @version: $Id: GameOnThreadsView.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

import java.util.Scanner;


/* The view class for the game 
 * 
 */

public class GameOnThreadsView {
	

	public String[] getPlayerNamesFromUser(int noOfPlayers){
		
		String names[] = new String[2];
		
		Scanner scan = new Scanner(System.in);
		
		int count = 0;
		
		while(count < noOfPlayers){
			System.out.println(" Enter the name of player " + (count+1));
			names[count] = scan.nextLine();
			count++;
		}
		
		count = 0;
		while(count < noOfPlayers){
			System.out.println("Name of players :"+names[count]);
			count++;
		}
		return names;
	}
	
	public static char askUserToPreditTheCharacter(int round ){
		
		Scanner in = new Scanner(System.in);
		String s;
		char preditedChar;
		System.out.print("\nRound:"+round+" Predict a character : ");
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

     	return preditedChar;
	}
	
	/**	Function to print the leader board based on the scores of the players.
	 * The scores will be sorted and first 4 highest scores will be displayed. 
	 *	
	 * @return void
	 */	
			
		private static void printLeaderBoard(Player players[],int num){
			
			int largest;
			Player temp;
			
			System.out.println("\n\n\t\t\t\t******LEADER BOARD*******"); 
			
			//Sorting is done here. Player with highest score will be first element 
			//as per this sorting
			for(int i=0; i<num-1; i++){
				largest = players[i].score;
				for(int j=i+1; j<num; j++){
					if(largest < players[j].score){
						temp = players[i];
						players[i] = players[j];
						players[j] = temp;
					}
				}
			}
			
			if(num < 4)
				largest = num;
			else
				largest = 4;
			System.out.println("\t\tThe top "+largest+" players are:");
			for(int i=0; i<num; i++){
				System.out.println("\t\t\t\t\t"+ players[i].name+ ":" + players[i].score);
			}
		}
		
		public void printDetails(Player p){
			System.out.println("\nPlayer Name :"+p.name + " Score = "+p.score);
		}
		
		public void printWonDetails(Player p){
			System.out.println("\n\n Won by Player Name :"+p.name + " Score = "+p.score);
		}
		public void printTie(){
			System.out.println("\n\n Game is tie ... all are winners ");
		}
}
