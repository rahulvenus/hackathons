package testPrep;

class Continue_1 {

	  public static void main( String args[] ) {
	    
		  int n = 0;

	label1:
		   while ( n < 6 )  {
			System.out.println("1. n == " + n);
		
	                while ( n < 4 )  { 
	                        n++;
				System.out.println("	2.  n == " + n);
				if ( n > 2 )
					continue label1;
				System.out.println("	3.  n == " + n + "--------");
	                }
	                n++;
	        }
	  }
	}
