package exam;

import java.util.*;

public class HpOKCollections {

  public static void sort(List list) {
	Object a[] = list.toArray();
	Arrays.sort(a);		/////// this is the trick
	ListIterator i = list.listIterator();
	for (int j=0; j<a.length; j++) {
		i.next();		// this is it
		i.set(a[j]);		// modification of the list
  	}
  }

}
