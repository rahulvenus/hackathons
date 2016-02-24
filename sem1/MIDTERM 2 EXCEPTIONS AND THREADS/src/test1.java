
public class test1 extends Thread{
	
	String s;
	public test1(String s){
		this.s =s;
	}
	public  synchronized void test(){
		System.out.println("in test"+s);
		
		try {
			Thread.sleep(2000);
			test1();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("exit test"+s);
	}
	
	public  synchronized void test1(){
		System.out.println("in test1"+s);
	}
	
	public void run(){
		test();
	}

	public static void main(String[] args) {
		  
		//new test1("a").start();
		 //new test1("b").start();
		 
		test1 t = new test1("a");
		t.start();
		System.out.println("call in run");
		t.test1();
		 

	}

}
