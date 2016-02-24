package testPrep;

class OpEx
{
   public static void main(String args[])
   {
   char aChar 		= 'b';
   byte aByte		= 2;

   int 	intVar_1	= 1;
   int 	intVar_2	= 2;
   int 	intRes		= 3;
   double	doubleVar_1	= 3.8;
   double	doubleVar_2	= 4.8;
   double	doubleRes	= doubleVar_1 - doubleVar_2;

   System.out.println("1. " + aChar);		// man ascii decimal set
   System.out.println("2. " + aByte);
   System.out.println("3. " + aByte+aChar);
   System.out.println("4. " + aByte+0);
   System.out.println("5. " + aChar+0);

   intRes = 5 / 3;	  System.out.println("6. " + intRes);
   intRes = 5 % 3;	  System.out.println("7. " + intRes);
// intRes = 5 / doubleVar_2; 	// Doesn't work, why?
   intRes = (int)(5 / doubleVar_2); System.out.println("8. " + intRes);

   doubleRes = 5   / doubleVar_2; System.out.println("9. " + doubleRes);
   doubleRes = 5.0 / doubleVar_2; System.out.println("10. " + doubleRes);
   }
}
