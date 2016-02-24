package testPrep;

public class S1 {

	  public static int staticV = 0;
	  public int instanceV = 1;		

	  public static void printsStatic()	{
		System.out.println("S1: staticV: " + staticV );
	  }
	  public void printInstance()	{
		System.out.println("S1: instanceV: " + instanceV );
	  }

	  public static void main(String args[]) {
			printsStatic();
			new S1().printInstance();
	  }
	}
