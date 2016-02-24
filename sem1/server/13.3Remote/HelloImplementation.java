import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImplementation extends UnicastRemoteObject implements HelloInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static {
		System.setProperty("java.security.policy",
				"file:/Users/rahul/Documents/workspace/server/13.3Remote/server.policy");
	}
	
	public HelloImplementation() throws IOException{
		
		try 
	    { 
	        // Bind this object instance to the name "HelloServer" 
	        Naming.rebind("thisOne", this); 
	    } 
	    catch (Exception e) 
	    { 
	        System.out.println("Player construction error: " + e.getMessage()); 
	        e.printStackTrace(); 
	    }
	}
	
	public void test(String S) throws RemoteException{
		System.out.println(" String  = "+S);
	}
	
	public static void main(String[] args) {
		try {
			HelloImplementation H = new HelloImplementation();
			H.test("serverTested");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
