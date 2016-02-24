/* 
 * HelloC.java 
 * 
 * @version: $Id: HelloC.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

import java.rmi.*;

public class HelloC {
	
	static {
		System.setProperty("java.security.policy",
				System.getProperty("user.dir")+ "client.policy");
	}

	public static void localRemoteTest(Object obj)	{
		try {
			if(obj.getClass().getCanonicalName().contains("$Proxy")){
				System.out.println("Sever object !!!");
				
			}
			else{
				System.out.println("local object !!!");
				
			}
			String temp = ((HelloInterface) obj).test("test");
			System.out.println(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[] ) {
		
		if(args.length == 1 ){
			
			String serverAddress = args[0];
			try {
				localRemoteTest( (HelloInterface)Naming.lookup("//"+serverAddress+"/thisOne") );
				localRemoteTest(  new HelloImplementation());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Wrong usage!");
			System.out.println("Correct Usage : HelloC <serveraddress>");
		}
		

     }
}
