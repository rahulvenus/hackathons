package testPrep;

//see http://docs.oracle.com/javase/specs/jls/se7/html/jls-6.html#jls-6.3 example 6.4-1
public class  Scope_4	{

String aString = null;

public static void test_2()	{
	int k = 0;
	for (int index = 0; index < 10 ; index ++ ) {
		int k = 3;
	}
}
public static void test()	{
	int i;
	int k = 0;
	switch (k) {
		case 1: 	{
			int i=1;
			System.out.println("1: i == " + i);
			}
			break;
		default:
			System.out.println("something went wrong!");
			break;
	}
}

public static void main(String args[] )	{
	test();
}
}