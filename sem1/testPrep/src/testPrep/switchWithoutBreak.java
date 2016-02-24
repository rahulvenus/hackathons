package testPrep;

public class switchWithoutBreak {

	   public static void main(String args[])
	   {
		   int i = 2;
		   switch(0){
		   default : System.out.println("def");
		   case 1 : System.out.println("1");
  			//break;
		   case 2 : System.out.println("2");
  			//break;
		 
  			break;
			   			
		   }

		   Integer a = 127;
		   Integer b = 127;
		   System.out.println(a == b);

		   a = 128;
		   b = 128;

		   System.out.println(a == b);
		   
		   Byte a1 = 127;
		   Byte b1 = 127;
		   System.out.println(a1 == b1);

		   System.out.println(a1 == b1);
	   }
}
