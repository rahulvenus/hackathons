package testPrep;

public class S3 {

	  public int instanceV = 1;		

	  public void set(int value)	{
		instanceV = value;
	  }
	  public String toString()	{
		return "S3: " + instanceV;
	  }

	  public static void main(String args[]) {
		System.out.println(new S3());
	  }
	}
