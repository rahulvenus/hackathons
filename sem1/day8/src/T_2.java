/* 
 * T_2.java 
 * 
 * @version: $Id: T_2.java,v 1.70 2015/10/19 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/10/19  12:00:00 
 */

public class T_2 extends Thread    {
    int id = 1;
    static String  theValue  = "1";
    T_2(int id)       {
        this.id = id;
    }
    public void run () {
    	
        if ( id == 1 )
                theValue = "3";
        else
                theValue = "2";
    }      
        
    public static void main (String args []) {
        new T_2(1).start();;
        new T_2(2).start();;
            
        System.out.println("theValue = " + theValue );
        System.out.println("theValue = " + theValue );
    }       
}  

/* Below are the possible outputs :

1) theValue = 1
   theValue = 2
   
2) theValue = 1
   theValue = 1 

2) theValue = 2
   theValue = 2
   
3) theValue = 3
   theValue = 2
 
  
4) theValue = 3
   theValue = 3
   
5) theValue = 1
    theValue = 3
   
   
 In this program too we cannot predict when a thread runs and hence
 the output varies depending upon the order in which the three threads run.
   
Here also the threads are running in parallel.
So control can go to each thread at any point.
Based on this , the value changes even if one thread set it because of 
another thread changing the same value.

   
   */

