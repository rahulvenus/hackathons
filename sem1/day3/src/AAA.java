/* 
 * AAA.java 
 * 
 * @version: $Id: X.java,v 1.7 2015/09/11 13:17:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00  2015/09/11 13:17:00
 */
  public class AAA extends AA {
 
         int aInt = 1;
 
         AAA() {
               aInt = 11;
         }
         public int intPlusPlus()      {
               return ++aInt;
        }

        public static void main(String args[]) {
//same as above i.e AA.Java initialization
              AAA aAAA = new AAA();
              AA   aAA = (AA)aAAA;
              A     aA = (A)aAA;
//same as it is overridden AAA value is incremented thrice 11->12->13->14
              aAAA.intPlusPlus();
              aAA.intPlusPlus();
              aA.intPlusPlus();

/*As tostring() is not present in AAA so when we try to print it takes the value 
from base class so it pints as 11 instead of 14
but in the last sop we are trying to access value directly from AAA 
so it prints 14*/
              
              System.out.println("aA:        "  + aA);
              System.out.println("aAA:       " + aAA);
              System.out.println("aAAA:      " + aAA);
              System.out.println("aAAA:.aInt " + aAAA.aInt);
        }
  }
