public class Join extends Thread	{
	private String info;
	Join aT1;

	public Join (String info) {
		this.info = info;
	}

	public void run () {
		System.out.println(info + " is running");
		try {
			sleep(10000);
		}
		catch (  InterruptedException e ) {
			System.err.println("Interrupted!");
			try {
				sleep(100);
			} catch (  Exception ee ) {}
		}
		System.out.println(info + ": exit run");

	}
	public static void main (String args []) {
		Join aT1  = new Join("first");
		
		aT1.start();
		try { sleep(100); } catch (Exception e ) { e .printStackTrace(); }
		aT1.interrupt();
		if ( Thread.interrupted() ) 
			System.err.println("A thread got interrupted");
		try {
		       	aT1.join();
			System.err.println("Got it");
		}
		catch (  InterruptedException e ) {
			e.printStackTrace();
		}
		System.err.println("main end");
	}
}