
public class thread{

	thread1[] t;
	thread1 ct;
	public static Object obj1 = new Object();
	public thread() {
		// TODO Auto-generated constructor stub
		t = new thread1[10];
		for(int i = 0; i< 10; i++){
			t[i] = new thread1();
		}
		thread1.set(this);
		
		
	}
	public static void main(String[] args) {
		
		thread T = new thread(); 
		for(int i = 0; i< 10; i++){
			T.t[i].start();
		}
		for(int i = 0; i< 10; i++){
			synchronized (obj1) {
				try {
					obj1.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				T.ct.x = i;
				T.ct.notify();
			}
		}
	}
	
	
	
	
}

class thread1 extends Thread{
	
	int x;
	public static thread T;
	public static Object obj = new Object();
	@Override
	public void run() {
		while(true){
			synchronized (obj) {
				T.ct = this;
				T.obj1.notify();
			
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(" my x = "+x);
			
		}
	}
	
	public static void set(thread q){
		T = q;
	}
	
	
}