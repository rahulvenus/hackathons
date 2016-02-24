import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
public class HelloImpl extends UnicastRemoteObject implements Hello 
{ 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static {
		System.setProperty("java.security.policy",
				"file:/Users/rahul/Documents/workspace/homework13RMI/src/server.policy");
	}
	
	public HelloImpl() throws RemoteException {}

    public String sayHello() { return "Hello world!"; }

    public static void main(String args[]) 
    { 
        try 
        { 
            HelloImpl obj = new HelloImpl(); 
            // Bind this object instance to the name "HelloServer" 
            Naming.rebind("HelloServer", obj); 
        } 
        catch (Exception e) 
        { 
            System.out.println("HelloImpl err: " + e.getMessage()); 
            e.printStackTrace(); 
        } 
    } 
}