package exam;


import java.util.HashSet;
import java.util.Set;

public class HashSetEx_1 {

    private Set<Integer> universe;
    
    private Set<Integer> fill(int soMany) {
	Set<Integer> universe = new HashSet<Integer>();
	for ( int index = 0; index < soMany; index ++ )
		universe.add(new Integer(9999999 * index));
	return universe;
    }
    public static void main(String args[])	{
    	Set<Integer> universe = null;
	HashSetEx_1 aHashSetEx_1 = new HashSetEx_1();
	universe = aHashSetEx_1.fill(5);
	System.out.println("1: " + universe );

	universe.remove( new Integer(29999997) );
	System.out.println("2: " + universe );

	universe.remove( new Integer(1) );
	System.out.println("3: " + universe );
	universe.add( new Integer(1) );
	System.out.println("3: " + universe );
	
	
    }
}
