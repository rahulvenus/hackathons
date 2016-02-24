import java.rmi.RMISecurityManager; 
import java.rmi.Naming; 
import java.rmi.RemoteException;
public class HelloClient 
{ 
	static {
		System.setProperty("java.security.policy",
				"file:/Users/rahul/Documents/workspace/homework13RMI/src/client.policy");
	}
	
	
    public static void main(String arg[]) 
    { 
        String message = "blank";

        // I download server's stubs ==> must set a SecurityManager 
        System.setSecurityManager(new RMISecurityManager());

        try 
        { 
           Hello obj = (Hello) Naming.lookup( "//" + 
                "localhost" + 
                "/HelloServer");         //objectname in registry 
           System.out.println(obj.sayHello()); 
        } 
        catch (Exception e) 
        { 
           System.out.println("HelloClient exception: " + e.getMessage()); 
           e.printStackTrace(); 
        } 
    } 
} 