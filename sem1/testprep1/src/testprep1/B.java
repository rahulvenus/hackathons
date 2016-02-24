package testprep1;

public class B  extends A{
	
	
	
	B(){
		System.out.println("B Construvtor");
	}

	public void display()
	{
		System.out.println("B Display");
	}
	
	public void displayOnlyInB()
	{
		System.out.println("B only Display");
	}
	
	public static void main(String args[]){
		
		B b = new B();
		A a =(A)b;
		a.displayOnlyInB();
	}

}
