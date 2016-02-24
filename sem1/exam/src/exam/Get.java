package exam;



 import java.lang.reflect.*;

 class Get {
         public int publicVar = 42;
         public int privateVar;
         static int staticVar;

         static void getValue(Object o) {
                 Class c = o.getClass();
                 Integer value;
                 try {
                         Field publicVarField = c.getField("publicVar");
                         value = (Integer) publicVarField.get(o);
                         System.out.println("value: " + value);
                         
                         publicVarField.set(o,45);
                         value = (Integer) publicVarField.get(o);
                         System.out.println("value: " + value);
                         
                 } catch (NoSuchFieldException e) {
                         System.out.println(e);
                         } catch (SecurityException e) {
                         System.out.println(e);
                         } catch (IllegalAccessException e) {
                         System.out.println(e);
                         }
         }

         public static void main(String[] args) {
                 Get aG = new Get();

                 System.out.println("Get: ");
                 getValue(aG);
         }
 }