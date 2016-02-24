/* 
 * SieveOfEratosthenes.java 
 * 
 * @version: $Id: SieveOfEratosthenes.java,v 1.70 2015/10/19 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/10/19  12:00:00 
 */

public class SieveOfEratosthenes {

    final static int FIRSTpRIMEuSED = 2;
    static int MAX;
    final boolean[] numbers;
    
    static int numMaxThreads;
    static int numMinThreads;
    static int curThreadCount;
    static multiplierThread[] mt;
    
    //Class multiplierThread creates new thread and based on request 
    //process each no and find its multiples and set them to false 
    
    private class multiplierThread implements Runnable{
    	public Thread myThread;
    	private String threadName;
    	public int index;
    	
    	public multiplierThread(int counter) {
			
    		threadName = Integer.toString(counter);
		}
    	
		@Override
		public void run() {
			//System.out.println("Runnig in thread");
			if ( numbers[index] )	{				// this is the part for the parallel part
				int counter = 2;				// this is the part for the parallel part
				while ( index * counter < MAX )	{		// this is the part for the parallel part
					numbers[index * counter] = false;	// this is the part for the parallel part
					counter++;				// this is the part for the parallel part
				}						// this is the part for the parallel part
			}
			
		}
    	
		public void start(int index){
			if(myThread == null){
				this.index = index;
				myThread = new Thread (this, threadName);

			}
			else{
				/*	try {
						myThread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} */
						
					while(myThread.isAlive()){
						//System.out.println("is alive");
					}
					//System.out.println("dead,so creating new");
					this.index = index;
					myThread = new Thread (this, threadName);

			}
			myThread.start ();
		}
    }
    
    
    public SieveOfEratosthenes(int max) {
	numbers = new boolean[max];
	this.MAX = max;
	curThreadCount = -1;
	mt = new multiplierThread[numMaxThreads];
    }
    public void determinePrimeNumbers()	{
	for (int index = 1; index < MAX; index ++ )	{
		numbers[index] = true;
	}
	
	int limit = (MAX > 10 ? (int)Math.sqrt(MAX) + 1 : 3);
	
	for (int index = 2; index < limit; index ++ )	{// this is the part for the parallel part
		if(curThreadCount == -1){
			//System.out.println("Triggering thread 0");
			++curThreadCount;
			mt[curThreadCount] = new multiplierThread(curThreadCount);
			mt[curThreadCount].start(index);
			numMinThreads = 0;
		}
		else{
			if(curThreadCount == numMaxThreads - 1){
				//System.out.println("Triggering thread 0");
				curThreadCount = 0;
				mt[curThreadCount].start(index);
			}
			else{
				
				curThreadCount++;
				//System.out.println("Triggering thread "+curThreadCount);
				if(mt[curThreadCount] == null){
					mt[curThreadCount] = new multiplierThread(curThreadCount);
					numMinThreads++;
				}
				mt[curThreadCount].start(index);
			}
		}
	}
	
	//wait for all the threads to finish for the final request
	for(int i =0;i <= numMinThreads ; i++){
		try {
			//System.out.println("Waiting for thread "+i+" to finsih");
			mt[i].myThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    }
    public void testForPrimeNumber()	{
       	
    	
	int[] test = { 2, 3, 4, 7, 13, 17, 18,MAX - 1, MAX};
	for (int index = 0; index < test.length; index ++ )	{
		if ( test[index] < MAX )	{
			System.out.println(test[index] + " = " + numbers[test[index]]);
		}
	}
    }

    public static void main( String[] args ) {
   
    numMaxThreads = Integer.parseInt(args[0]);
    System.out.println("Maximum no of threads : " + numMaxThreads);

	SieveOfEratosthenes aSieveOfEratosthenes = new SieveOfEratosthenes(100);
	aSieveOfEratosthenes.determinePrimeNumbers();
    System.out.println("Minimum no of threads : " + (numMinThreads+1));
	aSieveOfEratosthenes.testForPrimeNumber();
	System.exit(0);
    }
}

