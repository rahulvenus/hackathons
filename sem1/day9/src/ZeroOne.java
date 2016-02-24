/* 
 * ZeroOne.java 
 * 
 * @version: $Id: ZeroOne.java,v 1.70 2015/10/26 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/10/26 12:00:00 
 */

public class ZeroOne extends Thread	{
	private String info;
	int counterVal = 0;
	static int counter = 0;
	static public Object[] syncObject;
	//LIMIT IS SET to 99 threads
	final static int limit = 99;
	static{
		// objects are created to manage each thread
		syncObject = new Object[ZeroOne.limit];
		for(int i = 0; i<ZeroOne.limit;i++)
			syncObject[i] = new Object();
	}
					     
	public ZeroOne (String info) {
		this.info    = info;
		counterVal = Integer.parseInt(info);
	}
	
	public void run () {
		while ( true )	{
			
				synchronized(syncObject[counterVal]) {
					try {
						syncObject[counterVal].wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				counter++;
				if(counter == ZeroOne.limit)
					counter = 0;
				System.out.print(info);
				
				try {
					sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized(syncObject[counter]){
					syncObject[counter].notify();
				}
			}	
	}
	
	public static void main (String args []) {
		
		ZeroOne[] Z;
		Z = new ZeroOne[ZeroOne.limit];
		for(int i =0 ; i< ZeroOne.limit ; i++){
			Z[i] = new ZeroOne(Integer.toString(i));
		}

		for(int i =0 ; i< ZeroOne.limit ; i++){
			Z[i].start();
		}
		
		synchronized(syncObject[0]) {
			syncObject[0].notify();
		}
	}

}