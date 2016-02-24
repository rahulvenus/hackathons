package exam;

import java.util.*;

public class Sort {
    public static void main(String args[]) {
	args = new String[4];
	args[0] =  "z"; args[1] =  "x";
	args[2] =  "a"; args[3] =  "a";
        List l = Arrays.asList(args);
         // Collections.sort(l);
        //HpCollections_remove.sort(l);
        l.add("r");
        
        //List l1 = new List();
        
        //l1.add("r");
        HpOKCollections.sort(l);
        System.out.println(l);
    }
}
