/* 
 * Competition.java 
 * 
 * @version: $Id: Competition.java,v 1.70 2015/09/28 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/28 12:00:00 
 */

// Interface provided for fastCompetition.

public interface Competition<E>	{
	// Appends the specified element to this storage.
	// Returns true if the element could be added to this storage
	boolean	add(E e);
	// Returns true if this storage contains the specified element.
	boolean	contains(E o);	
	// Removes the first occurrence of the specified element in this storage.
	// If the storage does not contain the element, it is unchanged.
	// Returns true if the element could be removed from this storage
	boolean	remove(E o);	
	// Returns the component at the specified index.
	E elementAt(int index);	
	// Sorts the storage
	// Returns the sorted storage
	Competition<E>	sort();	
	// Returns the number of components in this storage.
	int size();
}