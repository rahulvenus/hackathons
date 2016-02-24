/* 
 * StorageTest.java 
 * 
 * @version: $Id: StorageTest.java,v 1.70 2015/09/28 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/28 12:00:00 
 */


//Node is used to store the generic objects in both fixed
//and dynamic storage
class Node<E,V>{
		E eObj;
		V vObj;
		//To link between the linked list and array we use
		//bridgeLink
		NodeLinkList<E,V> bridgeLink;
		//to check whether the slot is free
		boolean isUsed;

		Node(){
			eObj = null;
			vObj = null;
			bridgeLink = null;
			isUsed = false;
		}
		Node(E e){
			eObj = e;
			vObj = null;
			bridgeLink = null;
			isUsed = false;
		}
		Node(E e, V v){
			eObj = e;
			vObj = v;
			bridgeLink = null;
			isUsed = false;
		}
		Node(Node<E,V> N){
			eObj = (E)N.eObj;
			vObj = (V)N.vObj;	
			bridgeLink = null;
			isUsed = false;
		}
		
	}