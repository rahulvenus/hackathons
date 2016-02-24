package interfaces;

public class A {

	private void print(){
		System.out.println("Parent");
	}
	
	public static void main(String[] args) {
		X x = new X();
		A a =(A)x;
		a.print();
	}
}

class X extends A{
	//@Override
	private void print(){
		System.out.println("Child");
	}
}

