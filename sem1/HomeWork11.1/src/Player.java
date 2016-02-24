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


/* Class player used to create new players and run game on multiple threads 
 * 
 */
public class Player extends Thread{
	
	String name;
	int score;
	public Object indicationToStart = new Object();
	public Object indicationGameFinished = new Object();
	public boolean playTheGame = false;
	public boolean finishedTheGame = false;
	public static boolean gameIsOver = false;
	
	
	
	public Player(String name,int score){
		name = this.name;
		score = this.score;
		//System.out.println("Player "+name+" created !!!");
		this.start();
	}
	
	@Override
	public void run() {
		//System.out.println("started");
		while(true){
			synchronized (indicationToStart) {
				//System.out.println("playTheGame"+playTheGame);
				while(!playTheGame){
					//WAIT TILL CONTROLLER SAYS START
					try {
						//System.out.println("going to wait to get indication .. ");
						indicationToStart.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				
				finishedTheGame = false;
			}
			
			if(GameOnThreadsController.gameIsOver){
				//System.out.println("Game is over .. breaking out from run..to finsih the thread");	
				break;
			}
			
			//System.out.println("indicationToStart");
			this.score += GameOnThreadsController.playNextRound();
				
			
			synchronized (indicationGameFinished) {
				finishedTheGame = true;
				playTheGame = false;
				//System.out.println("indicationGameFinished");
				indicationGameFinished.notify();
			}
		}
		
		//System.out.println("Player Thread .. EXIT ...");
	}
	
}
