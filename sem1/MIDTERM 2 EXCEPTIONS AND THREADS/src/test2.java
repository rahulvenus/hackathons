import javax.management.MalformedObjectNameException;

public class test2 {

	public void add(){
		int a = 1;
		 
		try{
			a = a/0;
		}
		catch(Exception E){
			System.out.println("reached ");
			System.exit(1);
		}
		finally{
			System.out.println("finally reached ");
		}
	}
	public static void main(String[] args) {
		new test2().add();
	}
}
