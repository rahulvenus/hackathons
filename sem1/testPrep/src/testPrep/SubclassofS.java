package testPrep;

public class SubclassofS extends S {

	  public int intS;

	  public SubclassofS ()	{
		System.out.println("in SubclassOfS constructor");
	  }

	  public S method(int x)	{
	        intS = x;
		System.out.println("in SubclassOfS!method");
		super.method(9);
		System.out.println("4. super: " + super.toString() );
		super.intS = 4;
		System.out.println("5. super: " + super.toString() );
		return (S)this;
	  }
	  public String toString()      {
	        return "SSubclassOfS: " + intS;
	  }


	  public static void main(String args[])	{
		SubclassofS aSubclassOfS = new SubclassofS();
		S  aS =  aSubclassOfS.method(42);
		// System.out.println(aS);
		// System.out.println(aSubclassOfS);
		System.out.println("1. SubclassOfS!intS      = "
				+ aSubclassOfS.intS);
		System.out.println("2. ((S)SubclassOfS)!intS = "
				+ ((S)aSubclassOfS).intS);
		//method(3);		// <--- what is the problem here ...

	  }
	}
