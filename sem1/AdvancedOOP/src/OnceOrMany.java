/* 
 * OnceOrMany.java 
 * 
 * @version: $Id: OnceOrMany.java,v 1.00 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

/**The class OnceOrMany uses the singleton concept.The Singleton's 
 * purpose is to control object creation, 
 * limiting the number of objects to one only.
 */

class OnceOrMany {

  public static boolean singelton(String literal, String aNewString)	{
	return ( literal == aNewString );
  }
  
  /** This is the main function 
   *  @param args
   *  @return It returns no value
   */
  public static void main( String args[] ) {

/** Here a new string aString is defined and the value is assigned*/
	
	String aString = "xyz";
	
/* In the below statement , first and second string gets appended together ,
 * since the new string "1.	xyz == aString:	xyz" is not equal to aString
 * the result is false and it gets printed.
 */
	System.out.println("1.	xyz == aString:	" +     "xyz" == aString   );

/* Here intially comparison is done, since the priority is changed by adding paranthesis
 * Hence the string gets printed and the result of the comparison which is true
 */
	System.out.println("2.	xyz == aString:	" +   ( "xyz" == aString ) );

/** Here a new string object is created and initialized with "xyz"*/
	
	String newString = new String("xyz");
	
/** Here string object is compared with the value "xyz" and hence results in false*/	
	System.out.println("xyz == new String(xyz)\n	" + ("xyz" == newString) );

	System.out.println("1: " + singelton("xyz", "xyz"));
	
/**All the println() where newString is used will return 
 * false because when a new string automatically a new object 
 * is created and the address of this is different
 * from the other strings object*/
	
/** All other println() statements will return true
 * as we are just appending to the existing string	
 */
	
/** Here new string object is created and compared with the string "xyz" and hence 
 * result in false since compared with the address. 	
 */
	System.out.println("2: " + singelton("xyz", new String("xyz") ));
	
/** In the below two cases , strings are appended and compared .
 * Hence it will result true in comparison as the final strings are 
 * the same "xyz"	
 */
	System.out.println("3: " + singelton("xyz", "xy" + "z"));
	System.out.println("4: " + singelton("x" + "y" + "z", "xyz"));

/** Here new string object is created and appended to "xy" and then compared with the
 *  string "xyz" and hence result in false. 	
 */
	System.out.println("5: " + singelton("x" + "y" + new String("z"), "xyz"));
	
/** In the below two cases , strings are appended in priority wise and then compared .
 * Hence it will result true in comparison as the final strings are  
 * the same "xyz"	
 */
	System.out.println("6: " + singelton("x" + ( "y" + "z"), "xyz"));
	System.out.println("7: " + singelton('x' + ( "y" + "z"), "xyz"));
  }
}