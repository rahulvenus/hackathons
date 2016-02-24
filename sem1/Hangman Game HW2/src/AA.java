/* 
 * AA.java 
 * 
 * @version: $Id: AA.java,v 1.7 2015/09/11 13:17:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00  2015/09/11 13:17:00
 */
public class AA extends A {
 
         int aInt = 1;
 
         AA() {
               aInt = 11;
         }
        public int intPlusPlus()      {
              return ++aInt;
        }
        public String toString()      {
              return this.getClass().getName() + ": " + aInt;
        }

        public static void main(String args[]) {
              AA aAA = new AA();
//In the above line we are creating an object of type AA and value also of AA
//In the below line we are creating object of type A but value of type AA. 
              A   aA = (A)aAA;
//As intplusplus is overriden the call is made based on the value of object 
//and not type. So line 35 and 36 will increase the value of AA class 
//from 11->12->13
              
              aAA.intPlusPlus();
              aA.intPlusPlus();
//When we try to print the object the to string method is called in this 
//case even tostring is overridden so the call is made to the value 
//class(AA) so the output will be 13 twice
              System.out.println(aA);
              System.out.println(aAA);
//In the below line we are trying to access the variable and not method 
//so value will be based on type of object and not value which is A so 
//it will print 11 as A class is not incremented
              
              System.out.println("aA: " + aA.aInt);
        }
      }
