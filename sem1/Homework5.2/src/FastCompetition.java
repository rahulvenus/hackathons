/* 
 * FastCompetition.java 
 * 
 * @version: $Id: FastCompetition.java,v 1.70 2015/09/28 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/28 12:00:00 
 */

// This the implementation of the competition interface

public class FastCompetition<E extends Comparable<E>> implements Competition<E> {

	//Used to store the list of objects
	Object[] fastCObjects;
	//Maximum and Current used Capacity of the list
	int maxCapacity,curCapacity;
	
	// Appends the specified element to this storage.
	// Returns true if the element could be added to this storage
	public boolean	add(E e){
		boolean isAdded = false;
		if(curCapacity < maxCapacity){
			fastCObjects[curCapacity] = e;
			isAdded = true;
			curCapacity++;
		}
	return isAdded;
	}
	// Returns true if this storage contains the specified element.
	public boolean	contains(E o){
		boolean isContain = false;
		for(int i = 0 ; i < curCapacity ; i++){
			if((fastCObjects[i].equals(o))){
				isContain = true;
				break;
			}
		}
		return isContain;
	}
	// Removes the first occurrence of the specified element in this storage.
	// If the storage does not contain the element, it is unchanged.
	// Returns true if the element could be removed from this storage
	public boolean	remove(E o){
		boolean isRemoved = false;
		int i =0;
		for(i = 0 ; i < curCapacity ; i++){
			if(fastCObjects[i].equals(o)){
				isRemoved = true;
				break;
			}
		}
		while(i<(curCapacity-1)){
			fastCObjects[i] = fastCObjects[i+1];
			i++;
		}
		curCapacity--;
		return isRemoved;
	}
	// Returns the component at the specified index.
	@SuppressWarnings("unchecked")
	public E elementAt(int index){
		if( index < maxCapacity){
			return ((E)fastCObjects[index]);
		}
		else
			return null;
	}
	// Sorts the storage
	// Returns the sorted storage
	@SuppressWarnings("unchecked")
	public Competition<E> sort(){
		for(int i = 0 ; i < curCapacity;i++){
			for(int j = 0 ; j < curCapacity;j++){
				if(((E)fastCObjects[i]).compareTo((E)fastCObjects[j]) < 0){
					Object temp = fastCObjects[j];
					fastCObjects[j] = fastCObjects[i];
					fastCObjects[i] = temp;
				}
			}
		}
		return this;
		
	}
	// Returns the number of components in this storage.
	public int size(){
		return curCapacity;
	}
	
	FastCompetition(int n){
		
		fastCObjects  = new Object[n];
		maxCapacity = n;
		curCapacity = 0;
		
	}
}
