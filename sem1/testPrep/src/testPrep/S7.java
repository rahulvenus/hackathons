package testPrep;

public class S7 extends S5 {

	  public int instanceV = 6;		

	  public void onlyInS7()	{
		instanceV = 100;
	  }
	  public void both()	{
		instanceV = 100;
	  }
	  public String toString()      {
	        return "S7: " + instanceV;
	  }
	  public int superA()      {
		  return super.instanceV;
	  }


	  public static void main(String args[]) {
	 	S7 aS7 = new S7();
		S5 aS5 = (S5)aS7;
		
		aS7.both();
		aS5.onlyInS7();

	  }
	}
