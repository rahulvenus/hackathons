/* 
 * HelloInterface.java 
 * 
 * @version: $Id: HelloInterface.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

import java.rmi.*;

public interface HelloInterface extends java.rmi.Remote{

	public String test(String S) throws RemoteException;
}
