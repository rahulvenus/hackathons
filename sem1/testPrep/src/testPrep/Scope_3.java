package testPrep;
//see http://docs.oracle.com/javase/specs/jls/se7/html/jls-6.html#jls-6.3 example 6.4-1
public class  Scope_3	{

String aString = null;

public void test()	{
	int i;
	
	class Test {
		int i;
	}

	for (int index = 0; index < 10; index ++ )	{
		System.out.println("index = " + index );
	}
}
public static void main(String args[] )	{
	int i;
	new Scope_3().test();
}
}