


public class InheritanceExample1 {


public class A{
	
	A(){
		System.out.println("A()");
	}
}


public class B extends A{
	
	B(){
		super();
		System.out.println("B()");
	}
}

public static B B1;	
public static void main(String[] args){

		
	B1 = new InheritanceExample1.B();
	}
}
