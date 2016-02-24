import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HelloImplementation extends UnicastRemoteObject implements HelloInterface{
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	static {
		System.setProperty("java.security.policy",
				System.getProperty("user.dir")+ "/src/server.policy");
	}
	
	public HelloImplementation() throws IOException{
		
		try 
	    { 
			if (System.getSecurityManager() == null)
				System.setSecurityManager(new RMISecurityManager());	
			Naming.bind("thisOne", this);
	    
	    } 
	    catch (Exception e) 
	    { 
	        System.out.println("construction error: " + e.getMessage()); 
	        e.printStackTrace(); 
	    }
	}
	
	public String test(String S) throws RemoteException{
		return("Remote String  = "+S);
	}
	
	public static void main(String[] args) {
		try {
			HelloImplementation H = new HelloImplementation();
			System.out.println(H.test("serverTested"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
