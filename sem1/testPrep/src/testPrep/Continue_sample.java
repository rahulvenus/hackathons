package testPrep;

public class Continue_sample {

	 public static void main( String args[] ) {
		    
		  int n = 0;

	
			n++;
			label1:	   while ( n < 6 )  {
			System.out.println("1. n == " + n);
			//n++;
			continue label1;
			}
}
}
