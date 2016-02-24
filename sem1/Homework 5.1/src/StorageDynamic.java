/* 
 * StorageDynamic.java 
 * 
 * @version: $Id: StorageDynamic.java,v 1.70 2015/09/28 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/28 12:00:00 
 */

//This is the implementation for Dynamic storage.
//Class inherits from Storage

public class StorageDynamic<E , V> implements Storage<E, V> {
	
	//The starting the link for the list is stored 	here
	NodeLinkList<E,V> storingListHead;
	//Current used capacity  of the list
	int currCapacity;
	//First and last nodes of the link 
	Node<E,V> firstNode,LastNode;
	
	//Constructor
	
	StorageDynamic(){
		storingListHead = new NodeLinkList<E,V>();
		firstNode = storingListHead.dataNode;
		LastNode = storingListHead.dataNode;
		currCapacity = 0;
	}
	
	//Element is added using the below.
	//Returns true if added.
	public boolean	add(E e){
		boolean addSuccess = true;
		Node<E,V> tempNode = new Node<E,V>(e);
		
		if(currCapacity == 0){
			storingListHead.dataNode = tempNode;
			storingListHead.index = 0;
		}
		else{
			NodeLinkList<E,V> tempList = storingListHead;
			while(tempList.nextLink != null){
				tempList = tempList.nextLink;
			}
			NodeLinkList<E,V> newElementList = new NodeLinkList<E,V>();
			newElementList.dataNode = tempNode;
			newElementList.index = 	tempList.index + 1;
			tempList.nextLink = newElementList;
			newElementList.prevLink = tempList;
		}
		currCapacity++;
		System.out.println("\n\nAdded element ("+e+")!!");
		return addSuccess;
		
	}

	// Inserts the specified element at the specified position in this Storage.
	// retursn true, if the element could be added at position index, else false 
	public void add(int index, E element){
		
		Node<E,V> tempNode = new Node<E,V>(element);
		NodeLinkList<E,V> list = storingListHead;
		System.out.println("\n\n Adding element "+element+" at index " + index);
		for(int i = 1; i < index;i++){
			if(list.nextLink == null){
				NodeLinkList<E,V> tempListElement= new NodeLinkList<E,V>();
				list.nextLink = tempListElement;
				tempListElement.prevLink = list;
				tempListElement.index = i;
			}
				list = list.nextLink;
		}
		
		if(list.index == (index-1)){
			//System.out.println("Inside matched index..Checking whther any element is present after..");
			if(list.nextLink != null)
			{
				//System.out.println("elements present after index");
				NodeLinkList<E,V> listNewElement = new NodeLinkList<E,V>();
				
				listNewElement.dataNode = tempNode;
				listNewElement.index = list.index;
				listNewElement.nextLink = list;
				listNewElement.prevLink = list.prevLink;
				listNewElement.prevLink.nextLink = listNewElement;
				list.prevLink = listNewElement;
				
				while(list != null){
					list.index = list.index+1;
					//System.out.println(" list.index =" +list.index);
					list = list.nextLink;
				}			
			}
			else{
				list.dataNode = tempNode;
			}
			currCapacity++;
		}
	}

	// Adds the specified component to the end of this storage, increasing its size by one.
	public void	addElement(E obj){
		@SuppressWarnings("unused")
		boolean isAdded = false;
		isAdded = add(obj);
		//System.out.println("isAdded ="+isAdded);
		
	}

	// Adds the specified component to the end of this storage, increasing its size by one.
	public void	addElement(E obj, V elem){
		
		Node<E,V> tempNode = new Node<E,V>(obj,elem);
		
		if(currCapacity == 0){
			storingListHead.dataNode = tempNode;
			storingListHead.index = 0;
		}
		else{
			NodeLinkList<E,V> tempList = storingListHead;
			while(tempList.nextLink != null){
				tempList = tempList.nextLink;
			}
			NodeLinkList<E,V> newElementList = new NodeLinkList<E,V>();
			newElementList.dataNode = tempNode;
			newElementList.index = 	tempList.index + 1;
			newElementList.prevLink = tempList;
			tempList.nextLink = newElementList;
		}
		currCapacity++;
		
	}

	// Returns the current capacity of this storage.
	public int	capacity(){
		return currCapacity;
		
	}

	// Removes all of the elements from this storage.
	public void	clear(){
		NodeLinkList<E,V> list = storingListHead;
		list.nextLink = null;
		list.dataNode = null;
		currCapacity = 0;
		
	}

	// Returns a clone of this storage.
	public Object clone(){
		StorageDynamic<E,V> newObj = new StorageDynamic<E,V>();
		NodeLinkList<E,V> newObjList = newObj.storingListHead;
		NodeLinkList<E,V> list = storingListHead;
		Node <E,V>tempNode = new Node<E,V>(list.dataNode);
		newObjList.dataNode = tempNode;
		newObjList.index = 0;
		while(list.nextLink != null){
			list = list.nextLink;
			//newObjList = newObjList.nextLink;
			Node<E,V> tempNewNod;
			if(list.dataNode != null){
				tempNewNod = new Node<E,V>(list.dataNode);
			}
			else{
				tempNewNod = new Node<E,V>();
			}
			NodeLinkList<E,V> newElementList = new NodeLinkList<E,V>();
			
			newObjList.nextLink = newElementList;
			newElementList.dataNode = tempNewNod;
			newElementList.index =  list.index;
			newElementList.prevLink = newObjList;	
			newObjList = newObjList.nextLink;
			
			newObj.currCapacity++;
			
		}
		
		return newObj;
	}

	// Returns the first component (the item at index 0) of this storage.
	public E	firstElement(){
		NodeLinkList<E,V> list = storingListHead;
		return ((E)list.dataNode.eObj);
	}

	// Returns the element at the specified position in this storage.
	public E	get(int index){
		NodeLinkList<E,V> list = storingListHead;
		if(index == 0){
			return ((E)list.dataNode.eObj);
		}
		else{
			int flag =0;
			while(list.nextLink != null){
				list = list.nextLink;
				if(list.index == (index-1)){
					flag = 1;
					break;
				}
			}
			
			if(flag == 1){
				//System.out.println("Item found at index"+index);
				return ((E)list.dataNode.eObj);
			}
			else{
				//System.out.println("Item not found at index"+index);
				return null;
			}
		}
		
	}

	// Returns the last component of the storage.
	public E	lastElement(){
		NodeLinkList<E,V> list = storingListHead;
		while(list.nextLink != null){
			list = list.nextLink;
		}
		//System.out.println("Item  at last found");
		return ((E)list.dataNode.eObj);
	}
	
	//Display the current storage
	public void display(){
		NodeLinkList<E,V> list = storingListHead;
		System.out.print("\n\n\tItems :  ");
		while(list != null){
			if(list.dataNode !=null){
				if(list.dataNode.eObj !=null){
					System.out.print(list.dataNode.eObj +" , ");
				}
			}
			list = list.nextLink;
			
		}	
		System.out.println("\n Current Capacity : " + currCapacity);
	}
}
