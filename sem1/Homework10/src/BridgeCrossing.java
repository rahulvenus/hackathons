/* 
 * BridgeCrossing.java 
 * 
 * @version: $Id: BridgeCrossing.java,v 1.70 2015/11/02 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

import java.util.Random;


/* Truck class which represent the basic truck and its parameters */
class Truck extends Thread{
	public int truckNo = 0;
	public int truckWeight;	
	public Truck nextTruck;
	public String direction;
	public Object directionSync;

	public Truck(int truckWeight,String direction, int truckNum) {
		this.truckNo = truckNum;
		this.truckWeight = truckWeight;
		this.direction = direction;
		truckNo++;		
	}
	
	/* Truck function to cross the bridge */
	public void crossBridge(){	
		synchronized (this) {
				try {
					//System.out.println("Waiting for notification from Bridge to cross");
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
		System.out.println("Truck "+truckNo+ " crossing the bridge in "+ direction+" direction " );
		this.notify();
		}
		
	}

	public void run(){
		crossBridge();
	}
}


/* TruckQueue class to maintain a queue to serve first come first  */
class TruckQueue{
	public Truck frontMost;
	
	public TruckQueue() {
		frontMost = null;
	}
	
	/* addTruck() function adds truck to the queue at the back*/
	public void addTruck(Truck T){
		if(frontMost == null)
			frontMost = T;
		else{
			Truck temp = frontMost;
			while(temp.nextTruck != null){
				temp = temp.nextTruck;
			}
			temp.nextTruck = T;
		}	
	}
	
	/* popTruck() function pops truck from the queue from front*/
	public Truck popTruck(){
		if(frontMost == null){
			System.out.println("Pop truck returning null");
			return null;
		}
		else{
			Truck frontTruck = frontMost;
			frontMost = frontMost.nextTruck;
			return frontTruck;
		}
			
	}
	
	/* isEmpty() function to check the queue is empty*/
	public boolean isEmpty(){
		if(frontMost == null)
			return true;
		return false;
	}
	
}

/* TruckProducer class which produces the truck with random params  */
class TruckProducer extends Thread{
	private static int count;
	private final int maxNumTrucks;
	private Bridge bridge;
	private static Object obj = new Object();
	
	public TruckProducer(int maxNumTrucks,Bridge bridge) {
		this.maxNumTrucks = maxNumTrucks;
		this.bridge = bridge;
	}
	
	/* Thread creating trucks independently */
	@Override
	public void run() {
		Random r = new Random();
		int directionFinder;
		String randomDirection;
		int randomWeight;
		count = 0;
		
		while(count<30){
			synchronized (Bridge.truckQueue) {
				directionFinder = r.nextInt(2);
				if(directionFinder== 0)
					randomDirection = "right";
				else
					randomDirection ="left";
				
				randomWeight = r.nextInt(100000 -100) +100;
				
				Truck temp = new Truck(randomWeight,randomDirection,count);
					
				System.out.println("Adding TRUCK (REG:"+ temp.truckNo +"!!! randomWeight="+randomWeight+
							" randomDirection ="+randomDirection);
				temp.start();	
				
				
				bridge.truckQueue.addTruck(temp);
				
			
				//sSystem.out.println("Added ...");
				count++;
				synchronized (Bridge.truckIndication) {
					//System.out.println("Indicating bridge ...");
					Bridge.truckIndication.notify();
				}
				try {
					this.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	}	
}


/* Bridge class which manages the bridge and schedules the truck based on the queue  */
class Bridge extends Thread{
	
	public static int currentTotalWeight = 0;
	public static int currentNumTruckOnBridge = 0;
	
	public static TruckQueue truckQueue;
	
	public static Object truckIndication = new Object();
	
	public static Object bridgeNotifier = new Object();
	public static Object bridgeVariableProtector = new Object();
	
	public Bridge(){
		truckQueue = new TruckQueue();
	}
	
	public void bridgeScheduler(){
/*		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
*/		while(true){
			synchronized (truckIndication) {
				try {
					//System.out.println("Waiting for some truck to indicate the bridge to open...");
					if(truckQueue.isEmpty())
						truckIndication.wait();
					//System.out.println("Truck has arrived ....... Indication recieved...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			synchronized (truckQueue) {
					while(!truckQueue.isEmpty()){

						System.out.println("\n\n\n NEW SET OF TRUCKS CROSSING THE BRIDGE ... ");
						
						while(!truckQueue.isEmpty()){
							
							if(currentNumTruckOnBridge < 4){
								if(currentTotalWeight+truckQueue.frontMost.truckWeight <200000){
									
									Truck currentTruck = null;
									synchronized (this.truckQueue) {
										currentTruck = this.truckQueue.popTruck();
									}
									
									synchronized (currentTruck) {
										currentTruck.notify();
										try {
											currentTruck.wait();
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									currentNumTruckOnBridge++;
									currentTotalWeight += currentTruck.truckWeight;
								}
								else{
									System.out.println(" Total Truck Number ="+currentNumTruckOnBridge);
									System.out.println(" Total Truck Weight ="+currentTotalWeight+"\n\n");
									currentNumTruckOnBridge = 0;
									currentTotalWeight = 0;
									break;
								}
							}
							else{
								System.out.println(" Total Truck Number ="+currentNumTruckOnBridge);
								System.out.println(" Total Truck Weight ="+currentTotalWeight+"\n\n");
								currentNumTruckOnBridge = 0;
								currentTotalWeight = 0;
								break;
							}
						}
						
						if(truckQueue.isEmpty())
						{
							
							System.out.println(" Total Truck Number ="+currentNumTruckOnBridge);
				
							System.out.println(" Total Truck Weight ="+currentTotalWeight+"\n\n");
							currentNumTruckOnBridge = 0;
							currentTotalWeight = 0;
						}
/*						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
*/					
				}
			}	
		}
	}
	public void run(){
		bridgeScheduler();
	}	
}

/* The main class which simulates the bridge crossing */
public class BridgeCrossing{
	
	private static Bridge bridge;
	public static void main(String[] args) {
		int numTrucks = 10;
		bridge = new Bridge();
		bridge.start();
		
		TruckProducer truckProducer = new TruckProducer(10,bridge);
		truckProducer.start();
	}
}
