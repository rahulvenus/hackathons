package testPrep;

public class X_s1 {
	private String info;

	public X_s1 (String info) {
		this.info    = info;
	}
	private String info()	{
		return info;
	}
	public static void main (String args []) {
		X_s1 one 	= new X_s1("a");
		X_s1 two 	= new X_s1("a");
		
		if ( one.info() == "a")				// 1 marked
			System.out.println("1. equal");
		if ( one.info() == two.info() )			// 2 marked
			System.out.println("2. equal");
		if ( one.info().equals(two.info()) )		// 3 marked
			System.out.println("3. equal");
		System.out.println("@@@"+("aa".substring(0,1)));
		if ( one.info() == "aa".substring(0,1) )	// 4 marked
			System.out.println("4. equal");
		if ( one.info().equals("aa".substring(0,1) ) )	// 5 marked
			System.out.println("5. equal");
	}
}