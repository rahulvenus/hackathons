/* 
 * Node.java 
 * 
 * @version: $Id: StringZipOutput.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

public class Node{
	
		char binVal;
		int freq;
		String str;
		Node leftNode,rightNode;
		
		Node(int frequency, String stringVal){
			freq = frequency;
			str = stringVal;
			binVal = '0';
			leftNode = null;
			rightNode = null;
		}
	}	