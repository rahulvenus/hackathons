
public class pq {
	
	public static void main(String[] args){
		
		
		long num =(long) Math.pow(10, 9);
		int temp =2;
		int p = -1,n =1;
		int q = 0;
		boolean flag = false;
		System.out.println(num);
		while(temp<num){
			p = temp;
			n = 1;
			q = 0;
			while(n < num){
				n = n * p;
				q++;
				if(n == num){
					flag = true;
					break;
				}
				
			}
			if(flag)
				break;
			temp++;
			
		}
		
		
		if(flag == true){
			System.out.println("p ="+p+" q="+q);
		}
		else
			System.out.println("Not possible");
	}

}
