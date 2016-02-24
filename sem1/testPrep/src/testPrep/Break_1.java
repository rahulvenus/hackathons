package testPrep;

class Break_1 {

	  public static void main( String args[] ) {
	    
	        int n = 0;
	        //int i+= 9;

	   here:	{
	        while ( true )  {
			System.out.println("1. n == " + n);

	                while ( n < 100 )  { 	// while ( true ) --> which problem
	                        n++;
				System.out.println("	2. n == " + n);
				if ( n > 2 )
					break here;
	                }
			System.out.println("3.  n == " + n);
	           }
	        }
	  }
	}
