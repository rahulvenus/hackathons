/* 
 * T_1.java 
 * 
 * @version: $Id: T_1.java,v 1.70 2015/10/19 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/10/19  12:00:00 
 */

public class T_1 extends Thread	{
	static int x = 1;
	String info = "";

	public T_1 (String info) {
		this.info = info;
		x++;
	}

	public void run () {
		x++;
		String output = x + " " + info + ": " + x;
		System.out.println(output);
	}

	public static void main (String args []) {
		new T_1("a").start();
		new T_1("b").start();
	}
}

/* Below are the possible outputs in this program.
 
 
1) 4 a: 4
   5 b: 5
2) 4 a: 5
   5 b: 5
3) 5 b: 5
   4 a: 4
4) 5 b: 5
   4 a: 5

5) 4 b: 4
   5 a: 5
   
6) 4 b: 5
   5 a: 5
 
7) 5 a: 5
   4 b: 4

8) 5 a: 5
   4 b: 5
   
9) 5 a: 5
   5 b: 5 

10) 5 b: 5
    5 a: 5 
   
  
 In this program , we cannot predict when a thread runs and hence
 the output varies depending upon the order in which the three threads run.
 
This is because two threads are created in parallel
And any of the thread can get scheduled by the system 
and based on the execution output varies. Also threads run in parallel
and hence control of the system can go to other thread randomly during 
execution.

   
*/