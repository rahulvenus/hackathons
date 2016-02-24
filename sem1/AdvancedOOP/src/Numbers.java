import java.util.ArrayList;
import java.util.List;

public class Numbers {

	static List<Integer> primeList = new ArrayList<Integer>();
	static int range = 100000;
	public static void getListOfPrime(){
		int flag = 0;
		primeList.add(2);
		for(int i = 3 ; i < range ;i++ ){
			if(checkPrime(i) == 0){
				primeList.add(i);
				}
			}
		}
	
	public static void showPrimeList(){
		for (int i = 0; i < primeList.size(); i++) {
			//System.out.println(primeList.get(i));
		}
	}
	
	public static int orderInPrimeList(int num){
		int order = -1;
		order = primeList.indexOf(num) + 1;
		return order;
	}
	
	public static int checkPrime(int num){
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
	
	public static int reverse(int num){
		int reversedNum = 0;
		while((num)>0){
			reversedNum *= 10 ;
			reversedNum += (num%10);
			num /=10;
			
			//System.out.println("reversedNum = " + reversedNum + " num = "+ num);	
			
		}
		return reversedNum;
	}

	public static int checkPalindrome(int num1)
	{
		int checkBinEquals = 0;
		String num1Bin=Integer.toBinaryString(num1);
		String num1RevBin="";
		int length = num1Bin.length();
		for ( int i = length - 1 ; i >= 0 ; i-- )
			num1RevBin = num1RevBin + num1Bin.charAt(i);
		 
		if(num1Bin.equals(num1RevBin))
			checkBinEquals = 1;
		
		//System.out.println("num1 = " + num1);	
		//System.out.println("num1Bin = " + num1Bin + " num1RevBin = "+ num1RevBin);	
		
		return checkBinEquals;		
	}
	
	public static int checkNumIsSuper(int num){
		
		int order1 =-1 ,order2 = -1;
		int reversNum = 0;
		int retval = 0;

		//System.out.println("Entered Number is "+ num);
		
		/** Check whether the number is prime */
			if(checkPrime(num) == 0)
			{
				//System.out.println("Entered Number is prime");	
				reversNum = reverse(num);
				//System.out.println("Entered Number reverse is "+reversNum);	
				if(checkPrime(reversNum) == 0){
					//System.out.println("Entered Number Reverse is prime");
					//showPrimeList();
					order1 = orderInPrimeList(num);
					order2 = orderInPrimeList(reversNum);
					//System.out.println("Order1 ="+order1+" Order2 ="+order2);	
					if(reverse(order1) == order2){
						//System.out.println("Orders are also reverse");
							
						if(checkPalindrome(num) == 1){
							System.out.println("Order1 ="+order1+" Order2 ="+order2);
							//System.out.println("Binary of the number "+ num + "  is palindrome too, Hence Super");	
							retval = 1;
						}
						else{
							//System.out.println("Binary of the number is not palindrome , Hence Not Super");	
			       		}
					}
					else{
						//System.out.println("Orders are not reverse , hence number is not SUPER");	
						
					}
				  }
			
			}
			else{
				//System.out.println("Entered Number is not prime and hence not SUPER");				
			}
			return retval;
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int number = 73;
		long startTime = System.nanoTime();
		
		int retval;
		getListOfPrime();
		for(int i=2; i<range ;i++)
		{	
			//System.out.println("i="+i);	
			
			retval = checkNumIsSuper(i);
			if(retval == 1){
				System.out.println("Binary of the number "+ i + "  is palindrome too, Hence Super");	
				}
			else
			{
				//System.out.println("The number "+ i + "  not Super");	
				
			}
		}
		//code
				long endTime = System.nanoTime();
				System.out.println("Took "+(endTime - startTime) + " ns"); 
	}

}
