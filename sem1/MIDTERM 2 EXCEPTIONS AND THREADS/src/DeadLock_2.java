import java.util.*;

public class DeadLock_2 extends Thread	{
	private String info;
	private static Object o = new Object();

	public DeadLock_2 (String info) {
		this.info    = info;
	}

	private void inProtected_1 () {
		System.out.println(info + " tries to enter 1 .... ");
		synchronized ( o )	{
			System.out.println(info + " is in protected_1()");
			inProtected_2();
			System.out.println(info + ": exit inProtected_1");
		}
	}

	private void inProtected_2 () {
		System.out.println(info + " tries to enter 2 .... ");
		synchronized ( o )	{
			System.out.println(info + " is in protected_2()");
			inProtected_1();
			System.out.println(info + ": exit inProtected_2");
		}
	}

	public void run () {
		if ( info.equals("first") )
			inProtected_1();
		else
			inProtected_2();
	}

	public static void main (String args []) {
		new DeadLock_2("second").start();
		try {
			sleep(1000);
		} catch ( Exception e ) {}
		new DeadLock_2("first").start();
	}
}