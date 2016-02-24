/* 
 * OnceOrMany.java 
 * 
 * @version: $Id: OnceOrMany.java,v 1.00 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

import java.util.ArrayList;
import java.util.List;

public class Factorization {
	
	private int num;
	List<Integer> primeList = new ArrayList<Integer>();

	public void getListOfPrime(){
		int flag = 0;
		primeList.add(2);
		for(int i = 3 ; i <= num ;i++ ){
			if(checkPrime(i) == 0){
				primeList.add(i);
				}
			}
		}
	
	
	public int checkPrime(int num){
		int checkPrim = -1;
		for(int i=2;i<num;i++)
		{
			if((num%i) == 0)
			{
				checkPrim = -1;
				//System.out.println("i%2=="+ (i%2));
				break;
			}
			else
				checkPrim = 0;
		}
		return checkPrim; 
	}
	
	
	Factorization(int n){
		num = n;
	}
	
	
	private void listFactors(){
		int flag = 0;
		int tempNum = num;
		getListOfPrime();
		System.out.print(num + " = ");
			
		
		for (int i = 0; i < primeList.size(); i++) {

			if ( tempNum % primeList.get(i) == 0){
				
				 if(flag == 1){
					 System.out.print(" * ");
				 }
			     flag = 1; 
				 System.out.print(primeList.get(i));
				
				 tempNum = tempNum / primeList.get(i);
				 i--;
			 }
			 
		}
		
		
	}

	public static void main(String[] args) {
		int retval;
		int inputNo;
		inputNo = Integer.parseInt(args[0]);
		Factorization fact = new Factorization(inputNo);
		fact.listFactors();
	}
		
}