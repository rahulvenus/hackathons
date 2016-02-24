class A{
int x =10;	
}

class B extends A{
	
}
public class test {


public static void main(String[] args){
	
	B b = new B();
	A a =b;
	
	A a1 = new A();
	//B b1 = a1;
	
	
	A[] x = new A[10];
	x[0] = null;
	x[0] = a1;
	System.out.println(x[0].x);
	
	a = null;
	a = a1;
	}

}
