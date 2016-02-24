import java.rmi.*;

public interface HelloInterface extends java.rmi.Remote{

	public String test(String S) throws RemoteException;
}
