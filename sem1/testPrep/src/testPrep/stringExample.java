package testPrep;

public class stringExample {
	
	public static void method(String id,String literal,String aNewString){
		System.out.println(id + "in method");
		System.out.println("literal == aNewString ?" +(literal == aNewString));
	}

	public static void main(String[] args){
		String aString = "xyz";
		System.out.println("1. xyz == aString" + "xyz" == aString);
		System.out.println("2. xyz == aString" + ("xyz" == aString));

		
		String newString = new String("xyz");
		System.out.println("3. xyz == new String(xyz)" + ("xyz" == newString));
		
		method("1" , "xyz" ,"xyz");
		method("2" , "xyz" ,new String("xyz"));
		method("3" , "x"+"y"+"z" ,"xyz");
		method("4" , "x"+"y"+new String("z") ,"xyz");
		method("5" , "x"+( "y"+"z") ,"xyz");
		method("6" , 'x'+( "y"+"z") ,"xyz");
	}
}
