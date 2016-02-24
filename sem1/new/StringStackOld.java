// this implementation implements the methods,
// but the methods are null methods;
public class StringStackOld implements StackInterfaceOld {
    
    public void push( Object item )	{	       }
    public Object pop() 		{ return null; }
    public Object peek() 		{ return "hi"; }
    public boolean isEmpty() 		{ return true; }

    public static void main(String args[])	{
	StackInterfaceOld aStackInterfaceOld = new StringStackOld();
	aStackInterfaceOld.push("hello");	// why is here no warning?
	/* Here there is no warning because the object you are being passed is a String type ,
	 * which is again a subclass of Object . Base class object can be referred using a parent 
	 * class object without casting. Hence no error.
	 */
	String aString = (String)aStackInterfaceOld.pop();
	/* Here as the output of pop() , we get an object of Object Class .
	 * And its being tried to store the reference in String object. This is not possible without casting because 
	 * String class is a subclass of Object Class. And parent class object reference can be stored in a subclass reference 
	 * only through casting.Hence the error.
	 */
    }
/*
javac StringStackOld.java			// explain this error
StringStackOld.java:11: incompatible types	// explain what a cast would do
found   : java.lang.Object			// regarding possible compiler error detection
required: java.lang.String
	String aString = aStackInterfaceOld.pop();
	                                       ^
1 error

*/

}