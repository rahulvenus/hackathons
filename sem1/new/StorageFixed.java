class Node<E,V>{
		E eObj;
		V vObj;
		NodeLinkList bridgeList;
		boolean isUsed;

		Node(){
			eObj = null;
			vObj = null;
			bridgeList = null;
			isUsed = false;
		}
		Node(E e){
			eObj = e;
			vObj = null;
			bridgeList = null;
			isUsed = true;
		}
		Node(E e, V v){
			eObj = e;
			vObj = v;
			bridgeList = null;
			isUsed = true;
		}
	}

class NodeLinkList{
	int index;
	NodeLinkList nextNode;
	Node curNode;
	NodeLinkList prevNode;
	
	NodeLinkList(int i){
		index = i;
		System.out.println(index);
		nextNode = null;
		prevNode = null;
		curNode = null;
		
	}
}

public class StorageFixed<E , V> implements Storage<E, V>{
	
	public int maxCapacity;
	private int currCapacity,lastIndex;

	private Node<E,V>[] nodeObjList;
	private NodeLinkList headFreeNodeObj;
	private Node<E,V> firstNode,LastNode;
	
	public StorageFixed(int cap){
		headFreeNodeObj = null;
		maxCapacity = cap;
		nodeObjList = new Node[maxCapacity];
		NodeLinkList tempLink,newTempLink;
		tempLink = new NodeLinkList(0);
		for(int i =0;i<maxCapacity;i++){
			nodeObjList[i] = new Node<E,V>();
			if(i == 0){
				nodeObjList[i].bridgeList = tempLink;
				headFreeNodeObj = tempLink;			
			}
			else
			{
				System.out.println(tempLink.index);
				System.out.println(i);
				newTempLink = new NodeLinkList(i);
				nodeObjList[i].bridgeList = newTempLink;
				tempLink.nextNode = newTempLink;
				newTempLink.prevNode = tempLink;
				tempLink = newTempLink;	
				//newTempLink.nextNode = null;
			}
		}	
		currCapacity = 0;
		lastIndex = 0;
		tempLink =	headFreeNodeObj;
		while(tempLink != null){		
			System.out.print(" --> "+tempLink.index);
			tempLink =	tempLink.nextNode;
		}
	
	}
	
	
	public boolean	add(E e){
		boolean addSuccess = false;
		int indexToAdd = -1;
		Node<E, V> tempNode = new Node<E, V>(e);
		if(currCapacity < maxCapacity){
			currCapacity++;
			indexToAdd = headFreeNodeObj.index;
			headFreeNodeObj = headFreeNodeObj.nextNode;
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
		boolean isAdded = false;
		if(nodeObjList[index].isUsed == false){
			nodeObjList[index].eObj = element;
			nodeObjList[index].bridgeList.prevNode.nextNode = 
					nodeObjList[index].bridgeList.nextNode;
			isAdded = true;
			nodeObjList[index].isUsed = true;
			if(index>lastIndex)
				lastIndex = index;
			System.out.println("Added element sucss");
		}	
	}

	// Adds the specified component to the end of this storage, increasing its size by one.
	public void	addElement(E obj){
		Node<E, V> tempNode = new Node<E, V>(obj);
		int indexToAdd = -1;
		if(currCapacity < maxCapacity){
			currCapacity++;
			indexToAdd = headFreeNodeObj.index;
			headFreeNodeObj = headFreeNodeObj.nextNode;
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
			headFreeNodeObj = headFreeNodeObj.nextNode;
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
		NodeLinkList tempLink,newTempLink;
		tempLink = new NodeLinkList(0);
		for(int i =0;i<maxCapacity;i++){
			nodeObjList[i] = new Node<E,V>();
			if(i == 0){
				nodeObjList[i].bridgeList = tempLink;
				headFreeNodeObj = tempLink;			
			}
			else
			{
				newTempLink = new NodeLinkList(i);
				tempLink.nextNode = newTempLink;
				newTempLink.prevNode = tempLink;
				nodeObjList[i].bridgeList = newTempLink;
				tempLink = newTempLink;				
			}
		}
		currCapacity = 0;
	}
	
	public void	display(){
		
		for(int i =0;i<maxCapacity;i++){
			System.out.println(i+"  "+nodeObjList[i].eObj);
		}	
	}

	// Returns a clone of this storage.
	public Object	clone(){
		return null;
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
}
