/* 
 * DeadlockExample.java 
 * 
 * @version: $Id: DeadlockExample.java,v 1.70 2015/11/02 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

public class DeadlockExample extends Thread{

	private static  Object objectToSync = new Object();

	int choice;
	
	public DeadlockExample(int choice){
		this.choice = choice;
	}
	public void run() {
		System.out.println("Entered run() of thread "+choice);
		synchronized(objectToSync){
			objectToSync.notifyAll(); //TO AVOID DEADLOCK UNCOMMENT THIS LINE
			System.out.println("Entered Synchronized block of thread "+choice);
			try {
				System.out.println("Going to wait on thread "+choice);
				objectToSync.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Out of  wait on thread "+choice);
			System.out.println("Exit on thread "+choice);
			objectToSync.notifyAll();
		}
		

	}
	public static void main(String[] args) {

		DeadlockExample thread1 = new DeadlockExample(1);
		DeadlockExample thread2 = new DeadlockExample(2);
		
		thread1.start();
		thread2.start();

	}

}
