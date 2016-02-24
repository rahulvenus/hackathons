/* 
 * NodeLinkList.java 
 * 
 * @version: $Id: NodeLinkList.java,v 1.70 2015/09/28 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/28 12:00:00 
 */


//To create a double linked list using the nodes
//we use NodeLinkList
public class NodeLinkList<E,V> {
	Node<E,V> dataNode;
	NodeLinkList<E,V> nextLink;
	NodeLinkList<E,V> prevLink;
	boolean isUsed;
	public int index;
	
	NodeLinkList(){
		dataNode = null;
		nextLink = null;
		//prevLink = null;
		isUsed = false;
		index = 0;
	}
	NodeLinkList(int i){
		dataNode = null;
		nextLink = null;
		//prevLink = null;
		isUsed = false;
		index = i;
	}
}
