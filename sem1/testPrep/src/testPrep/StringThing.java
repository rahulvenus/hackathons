package testPrep;

class StringThing
{
   public static void main(String args[])
   {
      String aString;

      aString = new String("Last Stop Wonderland! ");
      System.out.println( aString.length() );
      System.out.println( aString.toUpperCase() );
      System.out.println( aString.toUpperCase() + ".");
      System.out.println( aString.length() + 1 );
      System.out.println( 1 + aString.length()  );
      System.out.println( 1 + aString + 1 );
      System.out.println( aString + ( 1 + 1 )  );
   }
}
