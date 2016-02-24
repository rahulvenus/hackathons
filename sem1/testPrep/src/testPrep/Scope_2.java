package testPrep;

//see http://docs.oracle.com/javase/specs/jls/se7/html/jls-6.html#jls-6.3 example 6.4-1
public class  Scope_2	{

String aString = "hii";

public void test()	{
	String aString = new String("set in test");
	//if ( true )	
	{
		//String aString = new String("set in test");
	}
}
public static void main(String args[] )	{
	new Scope_2().test();
}
}