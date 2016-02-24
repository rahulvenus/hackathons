package testprep1;

public class S {

	  static public int intS;
	  int[] x =null;

	  public S ()	{
		System.out.println("in S constructor");
	  }
	  
	  public void create(int size){
		  x =new int[size];
	  }

	  static {
		System.out.println("S:Static 1");
	  }

	  static {
		System.out.println("S: Static 2");
	  }

	  public static void main(String args[])	{
		System.out.println("new S()     " +  new S());
	  }
	}
