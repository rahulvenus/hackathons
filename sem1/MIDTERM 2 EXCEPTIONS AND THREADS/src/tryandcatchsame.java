
public class tryandcatchsame {
	
	public static void myfun() throws Exception{
		throw new Exception("I am an exception");
	}
	public static void main(String[] args) {
		try{
			myfun();
		}
		catch(Exception e){
			myfun();s
		}
		
	}

}
