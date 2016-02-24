package testPrep;

class X_1 {

	  public static String giveMeThree()	{
		return new Integer(3).toString();
	  }
	  public static boolean singelton(String literal, String aNewString)	{
		return ( literal == aNewString );
	  }
	  public static void main( String args[] ) {
	    System.out.println("1: " + singelton("123", "123"));
	    System.out.println("2: " + singelton("123", "x2" + giveMeThree() ));
	    System.out.println("3: " + singelton("1" + "2" + "3", "123"));
	    System.out.println("4: " + singelton("1" + "2" + new String("3"), "123"));
	    System.out.println("6: " + singelton("1" + ( "2" + "3"), "123"));
	  }
	}