package testPrep;
/**
 * "abc" versus new String("abc")`
 */

class StringL {

 public static void method(String id, String literal, String aNewString)	{
	System.out.println(id + " in method");
	System.out.print("\tliteral= aNewString\n	");
	System.out.println( literal == aNewString);
 }
 public static void main( String args[] ) {
	String aString = "abc";
	System.out.print("abc == aString\n	");
	System.out.println("abc" == aString);

	String newString = new String("abc");
	System.out.print("abc == new String(abc)\n	");
	System.out.println("abc" == newString);

	method("1", "abc", "abc");
	method("2", "abc", new String("abc") );
	method("3", "abc", "ab" + "c");
	method("4", "abc", "" + "abc");
 }

}
