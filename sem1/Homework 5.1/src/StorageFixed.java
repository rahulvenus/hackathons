/* 
 * StorageFixed.java 
 * 
 * @version: $Id: StorageFixed.java,v 1.70 2015/09/28 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/28 12:00:00 
 */

//This is the implementation for Fixed storage.
//Class inherits from Storage

public class StorageFixed<E , V> implements Storage<E, V>{
	
	//maximum capacity of the storage
	public int maxCapacity;
	private int currCapacity,lastIndex;

	//Array to store the list of used elements
	private Node<E,V>[] nodeObjList;
	//Linked list to store the list of free elements
	private NodeLinkList<E,V> headFreeNodeObj;

	//Constructor
	@SuppressWarnings("unchecked")
	public StorageFixed(int cap){
		headFreeNodeObj = null;
		maxCapacity = cap;
		nodeObjList = new Node[maxCapacity];
		NodeLinkList<E,V> tempLink,newTempLink;
		tempLink = new NodeLinkList<E,V>(0);
		for(int i =0;i<maxCapacity;i++){
			nodeObjList[i] = new Node<E,V>();
			if(i == 0){
				nodeObjList[i].bridgeLink= tempLink;
				headFreeNodeObj = tempLink;			
			}
			else
			{
				//System.out.println(tempLink.index);
				//System.out.println(i);
				newTempLink = new NodeLinkList<E, V>(i);
				nodeObjList[i].bridgeLink = newTempLink;
				tempLink.nextLink = newTempLink;
				newTempLink.prevLink = tempLink;
				tempLink = newTempLink;	
				//newTempLink.nextLink = null;
			}
		}	
		currCapacity = 0;
		lastIndex = 0;
		tempLink =	headFreeNodeObj;
		while(tempLink != null){		
			//System.out.print(" --> "+tempLink.index);
			tempLink =	tempLink.nextLink;
		}
	
	}
	
	//Used to add the element to the storage.,
	//Returns true if added, else False.
	public boolean	add(E e){
		boolean addSuccess = false;
		int indexToAdd = -1;
		Node<E, V> tempNode = new Node<E, V>(e);
		if(currCapacity < maxCapacity){
			currCapacity++;
			indexToAdd = headFreeNodeObj.index;
			headFreeNodeObj = headFreeNodeObj.nextLink;
			nodeObjList[indexToAdd] = tempNode;
			nodeObjList[indexToAdd].isUsed = true;
			addSuccess = true;
			if(indexToAdd>lastIndex)
				lastIndex = indexToAdd;
		}
		return addSuccess;
	}

	// Inserts the specified element at the specified position in this Storage.
	// returns true, if the element could be added at position index, else false 
	public void add(int index, E element){
		@SuppressWarnings("unused")
		boolean isAdded = false;
		if(nodeObjList[index].isUsed == false){
			nodeObjList[index].eObj = element;
			nodeObjList[index].bridgeLink.prevLink.nextLink = 
					nodeObjList[index].bridgeLink.nextLink;
			isAdded = true;
			nodeObjList[index].isUsed = true;
			if(index>lastIndex)
				lastIndex = index;
			currCapacity++;
			System.out.println("Added element "+element+" @ index "+index);
		}	
	}

	// Adds the specified component to the end of this storage, increasing its size by one.
	public void	addElement(E obj){
		Node<E, V> tempNode = new Node<E, V>(obj);
		int indexToAdd = -1;
		if(currCapacity < maxCapacity){
			currCapacity++;
			indexToAdd = headFreeNodeObj.index;
			headFreeNodeObj = headFreeNodeObj.nextLink;
			nodeObjList[indexToAdd] = tempNode;
			nodeObjList[indexToAdd].isUsed = true;
			if(indexToAdd>lastIndex)
				lastIndex = indexToAdd;
		}
		
	}

	// Adds the specified component to the end of this storage, increasing its size by one.
	public void	addElement(E obj, V elem){
		Node<E, V> tempNode = new Node<E, V>(obj,elem);
		int indexToAdd = -1;
		if(currCapacity < maxCapacity){
			currCapacity++;
			indexToAdd = headFreeNodeObj.index;
			headFreeNodeObj = headFreeNodeObj.nextLink;
			nodeObjList[indexToAdd] = tempNode;
			nodeObjList[indexToAdd].isUsed = true;
			if(indexToAdd>lastIndex)
				lastIndex = indexToAdd;
		}
	}

	// Returns the current capacity of this storage.
	public int	capacity(){
		return currCapacity;

	}

	// Removes all of the elements from this storage.
	public void	clear(){
		NodeLinkList<E, V> tempLink,newTempLink;
		tempLink = new NodeLinkList<E, V>(0);
		for(int i =0;i<maxCapacity;i++){
			nodeObjList[i] = new Node<E,V>();
			if(i == 0){
				nodeObjList[i].bridgeLink = tempLink;
				headFreeNodeObj = tempLink;			
			}
			else
			{
				newTempLink = new NodeLinkList<E, V>(i);
				tempLink.nextLink = newTempLink;
				newTempLink.prevLink = tempLink;
				nodeObjList[i].bridgeLink = newTempLink;
				tempLink = newTempLink;				
			}
		}
		currCapacity = 0;
		System.out.println("\n\nCleared the storage !!\n\n");
	}
	

	// Returns a clone of this storage.
	public Object clone(){
		StorageFixed<E,V> clonedObject = new StorageFixed<E, V>(maxCapacity);
		for(int i =0;i<maxCapacity;i++){
				clonedObject.nodeObjList[i].eObj = nodeObjList[i].eObj;
				clonedObject.nodeObjList[i].vObj = nodeObjList[i].vObj;
				clonedObject.currCapacity++;
		}
		return clonedObject;
	}

	// Returns the first component (the item at index 0) of this storage.
	public E	firstElement(){
		return nodeObjList[0].eObj;
	}

	// Returns the element at the specified position in this storage.
	public E	get(int index){
		return nodeObjList[index].eObj;

	}

	// Returns the last component of the storage.
	public E	lastElement(){
		return nodeObjList[lastIndex].eObj;
	}
	
	//Display the current storage
	public void	display(){
		boolean isEmpty = true;
		System.out.println("\n\t Elements in the Storage : \n");
		for(int i =0;i<maxCapacity;i++){
			if(nodeObjList[i].eObj != null)
			{
				isEmpty = false;
				System.out.print(nodeObjList[i].eObj  + " , ");
			}
		}
		if(isEmpty)
			System.out.println( "\n !!! NO ELEMENTS IN THE STORAGE !!!\n");
	}
}
