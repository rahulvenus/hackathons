/* 
 * HashSetNew.java 
 * 
 * @version: $Id: HashSetNew.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */


import java.util.*;

//the class HashSetNew which is the hash table created
public class HashSetNew extends HashSet<Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int size = 0;
	final static int capacity = 250000;
	private  HashNode[] hashNodeArray = new HashNode[capacity];
	
	//the HashNode class  which is used to store the data as an object in the table
	private class HashNode{
		HashNode previous;
		HashNode next;
		Object val;
		
		public HashNode(Object e) {
			
			previous = null;
			next = null;
			val = e;
		}
	}
	
	/* Function getHashIndex to create a hashcode for the object newly added
	 * The hash code is then used to get the index to store the object
	 * 
	 * returns index
	 */
	private int getHashIndex(int hashCode){
		//System.out.println(Math.abs(hashCode) %  capacity);
		return ((Math.abs(hashCode))%capacity);
	}
	
	/* Function add to add the new object requested
	 * 
	 * return true if added else false
	 */
	@Override
	public boolean add(Object e) {
		
		int hashIndex = getHashIndex(e.hashCode());
		if(hashNodeArray[hashIndex] == null){
			hashNodeArray[hashIndex] = new HashNode(e);
			size++;
			return true;
		}
		else
		{
			//System.out.println("not null");
			HashNode temp = hashNodeArray[hashIndex];
			if(temp.val.equals(e)){
				return false;
			}
			else{
				while( temp.next != null){
					if(temp.val.equals(e)){
						return false;
					}
					temp = temp.next;
				}
				temp.next = new HashNode(e);
				temp.next.previous = temp;
				//System.out.println("EMpty");
				size++;
				return true;
			}
		}
	}
	
	/* Function contains to check if the object exists in the hashtable
	 * 
	 * return true if exists else false
	 */
	@Override
	public boolean contains(Object e) {
		
		int hashIndex = getHashIndex(e.hashCode());
		if(hashNodeArray[hashIndex] == null){
			return false;
		}
		else
		{
			//System.out.println("not null");
			HashNode temp = hashNodeArray[hashIndex];
			do{
				if(temp.val.equals(e)){
					return true;
				}
				temp = temp.next;
			}while(temp != null);
			return false;
		}

	}

	/* Function remove to remove  the object from the hashtable
	 * 
	 * return true if able to remove else false
	 */
	@Override
	public boolean remove(Object o) {
		
		int hashIndex = getHashIndex(o.hashCode());
		if(hashNodeArray[hashIndex] == null){
			return false;
		}
		else
		{
			HashNode prev = null;
			//System.out.println("not null");
			HashNode temp = hashNodeArray[hashIndex];
			if(temp.val.equals(o)){
				hashNodeArray[hashIndex] = temp.next;
				size--;
				return true;
			}
			else{
				while( temp.next != null){
					if(temp.val.equals(o)){
						prev.next = temp.next;
						prev.next.previous = prev;
						size--;
						return true;
					}
					prev = temp;
					temp = temp.next;
				}
				return false;
			}
		}
	}
	
	/* Function size to get the current size of  the hashtable
	 * 
	 * return size(integer)
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	/* Function isEmpty to check if the hashtable is empty
	 * 
	 * return true if empty else false
	 * 	 
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(size == 0)
			return true;
		return false;
	}
	
	/* Function clear to clear the hashtable
	 * 
	 * 
	 * 	 
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i = 0; i < hashNodeArray.length; i++) {
			hashNodeArray[i] = null;
		}
		size = 0;
	}  
	  
	/* Function Iterator to return an iterator for the hashtable
	 * 
	 * 
	 * 	 returns the iterator for the hashtable
	 */
	public Iterator<Object> iterator() {
	       
		/* New iterator is created and it is returned every time
		 */
		Iterator<Object> it = new Iterator<Object>() {
				
				//the index used to get the next object locally
				int currentIndex = 0;
	            private HashNode hashNodePointer = hashNodeArray[currentIndex] ;

	            @Override
	            public boolean hasNext() {
	                return currentIndex < size ;
	            }

	            @Override
	            public Object next() {
	            	Object o;
	            	while(true){
		            	if(hashNodePointer != null){
		            		o = hashNodePointer;
		            		hashNodePointer = hashNodePointer.next;
		            		break;
		            	}
		            	else{
		            		hashNodePointer = hashNodeArray[++currentIndex] ;
		            	}
	            	}
	            	return o;
	            }

	            public void remove(Object O) {
	            	remove(O);
	            }

				@Override
				public void remove() {
					//the remove function
				}
	        };
	        return it;
	    }
	
	
	//the test function
	public static void main(String args[] )	{

		HashSetNew h = new HashSetNew();
		h.add("the");
		System.out.println(h.size);
		h.add("the");
		System.out.println(h.size);
		System.out.println(h.contains("the"));
		//aDriver.testOneKind();
		h.remove("the");
		System.out.println(h.size);
		System.out.println(h.contains("the"));
		//aDriver.testOneKind();
		System.exit(0);
   }
	
	
}