/* 
 * X.java 
 * 
 * @version: $Id: X.java,v 1.7 2015/09/11 13:17:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00  2015/09/11 13:17:00
 */

public class X {

/* This is the main method*/
	
	public static void main( String args[] ) {
/* Here we are initializing a variable n to 0*/
		
		               int n = 0;
		 
		               here:
		 
		               while ( true )  {
/*The While condition says that either n!=3 or n!=5
 * should hold each time during iteration but n<4 must 
 * hold true all the times		
 */
		            	   
/* Initially n=0 so the while loop conditions hold
 * and n is pre incremented to 1 which is not equal to 0 
 * So the first Print is ignored. Then it proceeds to the else if condition
 * which evaluates to true as n==1 but it prints out value of n as 2 since
 * it is post increment operation.The next else if is ignored and executing
 * break here is printed out.
 * Now value of n==2. It satisifies the while loop condition and the value of
 * n is incremented to 3 before first if statement. Then the else if(n++==1)
 * also is false as 3!=1 and value is incremented now to 4.The second else
 * if is also false and value is incremented to 5 and printed out.
 * After printing 5 it prints executing break here		            	   
 */
		     while ( ( ( n != 3 ) || ( n != 5 ) ) && ( n < 4 ) )  {
		                  if ( ++n == 0 )
		                        System.out.println("a/  n is " + n );
		                  else if ( n++ == 1 )    {
		                        System.out.println("b/  n is " + n );
		                } else if ( n++ == 2 )
		                        System.out.println("c/  n is " + n );
		                  else
		                        System.out.println("d/  n is " + n );
		
		                        System.out.println("    executing break here");
		
		     }
	/*Now it checks if 5 divided by 2==0
	 * which is false hence it then checks if 
	 * 5%3!=0 which is true so it prints out 3	     
	 */
		                      System.out.println( n % 2 == 0 ?
		                                         ( n == 4 ? "=" : "!" )
		                                         : ( n % 3 != 0 ? "3" : "!3" ));
		                      break here;
		              }
		          }
		      }
	
/*The final output of the program is 
 * b/  n is 2
    executing break here
d/  n is 5
    executing break here
3
*/
