
public class Multi extends Thread {
	public void run(){
		for(int i =0;i<5;i++){
		System.out.println(i);
		}
	}
	public static void main(String args[]){
		Multi t1 = new Multi();
		Multi t2 = new Multi();
		t1.start();
		t2.start();
		System.out.println("THE END");
	}

}

