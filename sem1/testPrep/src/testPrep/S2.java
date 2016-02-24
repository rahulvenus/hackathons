package testPrep;

public class S2 extends S1 {

	   //public static int staticV = 2;
	   public int instanceV = 4;		

	  public static void printsStatic()	{
		System.out.println("S2: staticV: " + staticV );
	  }
	  public void printInstance()	{
		  instanceV = super.instanceV;
		System.out.println("S2: instanceV: " + instanceV );
	  }

	  public static void main(String args[]) {
			printsStatic();
			S2 tempS2 = new S2();
			tempS2.printInstance();
			//printInstance();
	  }
	}

