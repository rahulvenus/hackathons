/* 
 * ConstantOrNot.java 
 * 
 * @version: $Id: X.java,v 1.7 2015/09/11 13:17:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00  2015/09/11 13:17:00
 */



 import java.util.Vector;        // what does this line do?
 
/*Imports the "Vector" class. This is sort 
 * of a dynamic array, meaning it can hold element as necessary and hence a 
 * vector wont have a maximum size. 
 */
 
      class ConstantOrNot {

         private final int aInt = 1;
         private final String aString = "abc";
         private final Vector aVector = new Vector();

         public void doTheJob() {
              // aInt = 3; why would this fail?
/*aInt=3 would fail as once a data type (barring collections, 
array etc) has been declared as final they cannot be changed. 
They can only be used as is.*/
        	 
              // aString = aString + "abc"; why would this fail?
 /*same as above but strings work a bit differently.  The point is this 
 statements tries to append the existing string "" with the string 
 "abc".When you declare String s = "a", s points "a" but when you 
 say s = s+ "b", the string s points to a string "a" and and 
 when "b" is appended to "a", java will create a new string "ab" and 
 have s point to it. Since you made it final it can only be used as is.That is 
 s can point to "a" only not other string. So, is if you want to use aString 
 you can only use it to refer the string "" and nothing else*/
        	 
              aVector.add("abc");             // why does this work?
  /*When a Vector is made final it does not restrict 	
  you from adding and deleting or even changing the 
  existing elements themselves but it does however
  precludes you from refering the existing object to an 
  other object*/
              
         }

          public static void main( String args[] ) {
              new ConstantOrNot().doTheJob();

          }
      }