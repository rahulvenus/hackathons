package testPrep;

public class X_4 extends X_3 {

  public int instanceV = 11;		

  public String toString()	{
	return "X_4: " + instanceV;
  }
  public void m()    {
        instanceV = 22;
  }
  public int g()    {
        return super.instanceV;
  }

  public static void main(String args[]) {
	X_3 aX_3 = new X_3();
	X_4 aX_4 = new X_4();

	aX_3.m();			// 
	aX_4.m();			// 
	System.out.println(aX_3);	// 3
	System.out.println(aX_4);	// 4

	aX_3 = (X_3)aX_4;		// 5a
	// aX_4 = (X_4)aX_3;		// 5b

	aX_3.m();			// 
	aX_4.m();			// 
	System.out.println(aX_3);	// 8
	System.out.println(aX_4);	// 9
	System.out.println(aX_4.g());	// 10

  }
}
