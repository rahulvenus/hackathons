/* 
 * CandyBox.java 
 * 
 * @version: $Id: CandyBox.java,v 1.70 2015/10/26 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/10/26 12:00:00 
 */


import java.util.Scanner;

/* The main class which controls all the consumer and producer threads
 * 
 */
public class CandyBox {
	
	static int candyStorageAreaSize;
	static int candyStoreCount;
	static Object candySync = new Object();
	
	static int wrapperStorageAreaSize;
	static int wrapperStoreCount[] = new int[3];
	static Object wrapperSync = new Object();
	
	static int wrappedCandyStorageAreaSize;
	static int wrappedCandyStoreCount;
	static Object wrappedCandySync = new Object();
	
	static int candyBoxStorageSize;
	static int candyBoxStoreCount;
	static Object candyBoxSync = new Object();
	
	static int filledBoxStorageSize;
	static int filledBoxStoreCount;
	
	static boolean jobIsPending = true;
	
	
	CandyBox(int candyStorage,int wrapperStorage,int wrappedCandyStorageArea,int candyBoxStorage,int filledBoxStorage ){
		candyStorageAreaSize = candyStorage;
		wrapperStorageAreaSize = wrapperStorage;
		wrappedCandyStorageAreaSize = wrappedCandyStorageArea;
		candyBoxStorageSize = candyBoxStorage;
		filledBoxStorageSize = filledBoxStorage;
		
		candyStoreCount = 0;
		wrappedCandyStoreCount = 0;
		for (int i = 0; i < wrapperStoreCount.length; i++) {
			wrapperStoreCount[i] = 0;
		}
		wrappedCandyStoreCount = 0;
		candyBoxStoreCount = 0;
		filledBoxStoreCount = 0;
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		boolean flag = true;
		
		//CandyBox factory = new CandyBox(12,4,12,3,2);
		CandyBox factory = new CandyBox(0,0,0,0,0);
		
		System.out.println("Enter the StorageAreaSize for candy ?");
		CandyBox.candyStorageAreaSize = in.nextInt();
		
		System.out.println("Enter the StorageAreaSize for wrapper ?");
		CandyBox.wrapperStorageAreaSize = in.nextInt();
		
		while(CandyBox.wrappedCandyStorageAreaSize <12){
		System.out.println("Enter the StorageAreaSize for Wrapped candy (Should be > 4)?");
		CandyBox.wrappedCandyStorageAreaSize = in.nextInt();
		}
		System.out.println("Enter the StorageAreaSize for candyBox ?");
		CandyBox.candyBoxStorageSize = in.nextInt();
		
		System.out.println("Enter the StorageAreaSize for filled candyBox ?");
		CandyBox.filledBoxStorageSize = in.nextInt();
		
		CandyProducer candyProducer = new CandyProducer();
		candyProducer.start();
		
		CandyWrappingPaperProducer candyWrappingPaperProducer = new CandyWrappingPaperProducer();
		candyWrappingPaperProducer.start();
		
		ConsumerWrapper consumerWrapper = new ConsumerWrapper();
		consumerWrapper.start();
		
		Producer producer = new Producer();
		producer.start();
		
		Consumer consumer = new Consumer();
		consumer.start();
		
		while(jobIsPending){
			//System.out.println("FLAGGGGGG"+flag);
			if(CandyBox.candyStoreCount >0){
				for (int i = 0; i < 3; i++) {
					if(CandyBox.wrapperStoreCount[i] > 0){
						//indicate candywrapper to wrp
						System.out.println("indicating to  produce wrapped candy "+flag);
						synchronized (CandyBox.wrappedCandySync) {
							CandyBox.wrappedCandySync.notify();
						}
					}
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(" CandyBox.filledBoxStoreCount="+CandyBox.filledBoxStoreCount);
			if(CandyBox.filledBoxStoreCount < CandyBox.filledBoxStorageSize){
				if(CandyBox.wrappedCandyStoreCount > 4){
					//indicate consumer to fill the box
					synchronized(CandyBox.candyBoxSync){
						System.out.println("Notifying ... .");
						CandyBox.candyBoxSync.notify();
					}
					
				}
			}
			else{
				
				CandyBox.jobIsPending = false;
				System.out.println("Going to terminate");
					
				synchronized (CandyBox.wrappedCandySync) {
					System.out.println(" notifyinh CandyBox.wrappedcandy to");
					CandyBox.wrappedCandySync.notify();
				}	
				
			}

		}
		
	}
}

/* The producer that produces one piece of candy at a time (candyProducer) and stores 
 * the candy in a fixed length storage area (length=k).
 * 
 */

class CandyProducer extends Thread{
	
	@Override
	public void run() {
		while(CandyBox.jobIsPending){
			// System.out.println(" Enter CandyProducer");
			synchronized (CandyBox.candySync) {
				if(CandyBox.candyStoreCount < CandyBox.candyStorageAreaSize){
					CandyBox.candyStoreCount++;
					System.out.println("CandyProducer Produced a candy , candyStoreCount="
							+CandyBox.candyStoreCount+" max="+CandyBox.candyStorageAreaSize);
				}
			}
		}
		System.out.println(" Exit CandyProducer");
	}
	
}

/* The producer that produces one piece of 3 pieces candy wrapping paper at a time (candyWrappingPaperProducer)
 and stores the wrapping paper in 3 different locations in a fixed length storage area
 * 
 */
class CandyWrappingPaperProducer extends Thread{
	
	@Override
	public void run() {
		while(CandyBox.jobIsPending){
			//System.out.println(" Enter CandyWrappingPaperProducer");
			synchronized (CandyBox.wrapperSync) {
				for (int i = 0; i < CandyBox.wrapperStoreCount.length; i++) {
					if(CandyBox.wrapperStoreCount[i] < CandyBox.wrapperStorageAreaSize){
						CandyBox.wrapperStoreCount[i]++;
						System.out.println("CandyWrappingPaperProducer Produced 3 wrapper , wrapperStoreCount["+i+"]="
								+CandyBox.wrapperStoreCount[i]+" max="+CandyBox.wrapperStorageAreaSize);
					}
				}
			}
		}	
		System.out.println(" Exit CandyWrappingPaperProducer");
	}
  
	
}

/* A consumer wrapper consumes one piece of candy and one piece of wrapping paper and wraps the candy in wrapping paper,
 *  if a candy and wrapping paper exists, and stores the wrapped candy in a fixed length storage area 
 * 
 */
class ConsumerWrapper extends Thread{
	@Override
	public void run() {
		while(CandyBox.jobIsPending){
			//System.out.println(" Enter ConsumerWrapper");
			synchronized (CandyBox.wrappedCandySync) {
				try {
						CandyBox.wrappedCandySync.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(CandyBox.jobIsPending){
					//System.out.println("CandyBox.jobIsPending 2222");
					if(CandyBox.wrappedCandyStoreCount < CandyBox.wrappedCandyStorageAreaSize){
						synchronized (CandyBox.candySync) {
							
							if(CandyBox.candyStoreCount > 0){
								for (int i = 0; i < CandyBox.wrapperStoreCount.length; i++) {
									if(CandyBox.wrapperStoreCount[i] > 0){
										synchronized (CandyBox.wrapperSync) {
											CandyBox.wrappedCandyStoreCount++;
											CandyBox.candyStoreCount--;
											CandyBox.wrapperStoreCount[i]--;
											System.out.println("ConsumerWrapper wrapped a candy , wrapperStoreCount="
												+CandyBox.wrappedCandyStoreCount+" max="+CandyBox.wrappedCandyStorageAreaSize);
										}
										break;
									}
								}
							}
						}
					}
				}
				//else
					//System.out.println("Exiting ConsumerWrapper");
			}
		}
		System.out.println(" Exit ConsumerWrapper");
	}
	
}

/* A producer creates candy boxes, and stores the boxes in a fixed length storage area 
 */

class Producer extends Thread{
	@Override
	public void run() {
		while(CandyBox.jobIsPending){
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(" Enter producer : jobIsPending"+CandyBox.jobIsPending);
			if(CandyBox.candyBoxStoreCount < CandyBox.candyBoxStorageSize){
				synchronized (CandyBox.candyBoxSync) {
					CandyBox.candyBoxStoreCount++;
					System.out.println("Producer created a box , CandyBox.candyBoxStoreCount="
							+CandyBox.candyBoxStoreCount+" max="+CandyBox.candyBoxStorageSize);
				}	
			}
		}
		System.out.println(" Exit producer");
	}
}

/* A consumer fills a box if enough wrapped candy is available to
 *  fill a complete box, and stores the filled boxes in a fixed length storage area 
 *  */
class Consumer extends Thread{
	@Override
	public void run() {
		while(CandyBox.jobIsPending){
			synchronized (CandyBox.candyBoxSync) {
				//System.out.println(" Enter Consumer");
				try {
					CandyBox.candyBoxSync.wait();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("resume");
				
				synchronized(CandyBox.wrappedCandySync){
					
					if((CandyBox.wrappedCandyStoreCount > 4) && (CandyBox.filledBoxStoreCount < CandyBox.filledBoxStorageSize) ){
		
							CandyBox.filledBoxStoreCount++;
							CandyBox.candyBoxStoreCount--;
							CandyBox.wrappedCandyStoreCount-=4;
							System.out.println("\n\nConsumer filled a box , CandyBox.filledBoxStoreCount="
									+CandyBox.filledBoxStoreCount+" max="+CandyBox.filledBoxStorageSize +
									" CandyBox.wrappedCandyStoreCount ="+CandyBox.wrappedCandyStoreCount);
					}
				}
			}
		}
		System.out.println(" Exit Consumer");
	}
}
