package exam;

import java.util.*;

public class HpCollections_remove {

  public static void sort(List aList) {
	Object anArray[] = aList.toArray();

    	for (int index=0; index<anArray.length - 1; index++)     {
            for (int walker=0; walker<anArray.length - index - 1; walker++)  {
		Comparable left = (Comparable) anArray[walker];
		Comparable right = (Comparable) anArray[walker+1];
                if ( left.compareTo( right ) > 0 )        {
                        Object tmp = anArray[walker];
                        anArray[walker] = anArray[walker + 1];
                        anArray[walker+1] = tmp;
                }
            }
    	}

    	//List mList = 
    	for (Object o: anArray )
		System.out.println("anArray: " + o );
    	for (int index=0; index<anArray.length ; index++)     	{
		//aList.remove(index );
		aList.add(anArray[index] );
	}
  }

}
