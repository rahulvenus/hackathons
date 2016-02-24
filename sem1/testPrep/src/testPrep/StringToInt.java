package testPrep;

class  StringToInt
{
   public static void main(String args[])
   {
      int i;
      Integer aInt = new Integer("433rr");
      i = aInt.intValue();
      System.out.println("a ="+i);
      i = Integer.parseInt("4ww");
      System.out.println("a ="+i);
   }
}