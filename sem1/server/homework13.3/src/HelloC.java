import java.rmi.*;

public class HelloC {

	public static void localRemoteTest(Object obj)	{
		try {
			if(obj.getClass().getCanonicalName().contains("$Proxy")){
				System.out.println("Sever object !!!");
				((HelloInterface) obj).test("test");
			}
			else{
				System.out.println("local object !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[] ) {

		try {
			localRemoteTest( (HelloInterface)Naming.lookup("//localhost/thisOne") );
			localRemoteTest(  new HelloImplementation());
		} catch (Exception e) {
			e.printStackTrace();
		}

        }
}