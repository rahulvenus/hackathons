package testprep1;
class X_2 extends X_1 {

	public X_2()    {
		// super();	// default
		System.out.println("	in X_2!X_2()");
	  }
	  public X_2(int x)    {
		// super();	// default
		//super(x);
		System.out.println("	in X_2!X_2(int x)");
	  }

	  public X_2(int x, int y)    {
		// super();	// default
		System.out.println("	in X_2!X_2(int x, int y)");
	  }

	  public static void main(String args[])
	  {
		X_2 aX_2 = new X_2();
		X_2 aaX_2 = new X_2(3);
		X_2 aaaX_2 = new X_2(3, 3);
	  }
	}
