package testPrep;



public class casting extends A{
	/*public String toString(){
		return "B";
	}*/

	
	
	public static void main(String[] args){
		
		casting b = new casting();
		A a = (A)b;
		
		System.out.println(a);
		System.out.println(b);
	}

}
