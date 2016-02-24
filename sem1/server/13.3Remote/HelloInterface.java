import java.rmi.RemoteException;

public interface HelloInterface extends java.rmi.Remote{

	public void test(String S) throws RemoteException;
}
