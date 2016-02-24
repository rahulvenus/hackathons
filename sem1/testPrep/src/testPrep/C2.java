package testPrep;

public class C2 extends C1  {

	  public C2()	{
		System.out.println("    in C2");
	  }
	  public C2(int x)	{
		//super(x);
		System.out.println("    in C2!int x");
	  }

	  public static void main(String args[])	{
		System.out.println("new C1() ... ");
		new C1();
		System.out.println("new C2() ... ");
		new C2();
		System.out.println("new C2(int x) ... ");
		new C2( 3 );
	  }

	}