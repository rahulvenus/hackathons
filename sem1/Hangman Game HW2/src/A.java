
/* 
 * A.java 
 * 
 * @version: $Id: A.java,v 1.7 2015/09/11 13:17:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00  2015/09/11 13:17:00
 */


public class A {
 
         int aInt = 1;// a variable aInt is initialized here with value 1
 
 /* This is the default constructor the base class or class A*/   
         
         A() {
               aInt = 11;
         }
         
  /*The intPLusPlus function pre-increments the value of the variable aInt*/
         
         public int intPlusPlus()      {
               return ++aInt;
        }
         
  /*If you want to represent any object as a string, toString() 
   * method comes into existence*/
         
        public String toString()      {
              return this.getClass().getName() + ": " + aInt;
        }

        public static void main(String args[]) {
              A aA = new A();
              aA.intPlusPlus();
              System.out.println(aA);
        }
      }

