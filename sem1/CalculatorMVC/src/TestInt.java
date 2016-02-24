public class TestInt {
	
	static{
		System.out.println("Static......");
	}
	
	public String excep(){
		try{
		int i = 1/0;
		}
		catch(ArithmeticException E){
			return "catch";
			
		}
		finally{
			return "finally";
		}
	}
	
    public static void main(String[] args) throws Exception
    {
    	
    	TestInt T = new TestInt();
    	TestInt T1 = new TestInt();
    	TestInt T2 = new TestInt();
    	
    	String S = T.excep();		
        System.out.println(S);  
    }    
}