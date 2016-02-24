
public class Thread_2 extends Thread	{

	private String info;

	public Thread_2 (String info) {
		this.info = info;
	}

	public void run () {
		long sleep = (int)(Math.random() * 10000);
		System.out.println(info + " sleeps for " + sleep );
		try {
			sleep(sleep);
		}
		catch (  InterruptedException e ) {
			e.getMessage();
		}
	}

	public static void main (String args []) {
		int count = 0;
		if (args != null)
		for (int n = 0; n < args.length; ++ n)	{
			Thread_2 aT1  = new Thread_2(args[n]);
			if ( n % 2 == 0 )
				aT1.setPriority(Thread.MIN_PRIORITY);
			aT1.start();
		}
			
		while ( count != 1 )	{
			try {
				count = activeCount();
				System.out.println("activeCount() = " +
					count );
				sleep(500);
			}
			catch (  InterruptedException e ) {
				e.getMessage();
			}
		}
		
	}
}