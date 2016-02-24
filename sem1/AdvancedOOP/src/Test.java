/* 
 * Test.java 
 * 
 * @version: $Id: Test.java,v 1.7 2015/09/06 12:00:00 
 * 
 * @author Abhilash Vimal and Rahul Venugopal Pillai
 *
 * Revisions:
 *
 *      Revision 1.00  2015/09/06 12:00:00
 */


/**
 *This program that takes an integer n from the command line 
 *and finds all prime numbers which are factors 
 * @author Abhilash Vimal
 * @author Rahul Venugopal Pillai
   
*/


public class Test {


/** This is the main method of the program
 * @param args
 * @return void
 */
	
public static void main(String[] args){
		
		// This takes string input from user using command line
		int num= Integer.parseInt(args[0]);
		// This converts the input from string to integer
		int primeFactors[]= new int[num];
		
		int count=0;
		
/** In these loops a number is factorized
 * For.eg if we take 32 then first it divides 32 by 2
 * then it does num/2 i.e 16 and divides that by 2.
 * This process keeps on repeating until factorization
 * is complete		
 */
		for(int i = 2; i<=num/2; i++)
		{
			while(num % i == 0){
				primeFactors[count++] =i;
				num=num/i;
			}
		}
		
/**If num>1 then just add the number to the array.
 For example if we take number 13 then it is prime so we
add the number itself to the array
*/		if(num>1)
			primeFactors[count++] =num;
//printing the elements of the array		    
	  		for(int j=0; j<count-1;j++){
	  	System.out.print(""+primeFactors[j]+"*");
	  }
	  
	  System.out.print(primeFactors[count-1]);
	}
}

	

