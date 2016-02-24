
public class factZeroes {

	public static void main(String[] args){
		
		int  num2 =0;
		int  num5 =0;
		
		int  num = 1000;
	
		int temp=num;
		
		while(temp>0){
			int newTemp = temp;
		/*	while((newTemp>0) && (newTemp%2==0)){
				num2++;
				newTemp = newTemp/2;
			}
			newTemp = temp;*/
			while((newTemp>0) && (newTemp%5==0)){
				num5++;
				newTemp = newTemp/5;
			}

			temp--;
			//break;
		}
	
		//System.out.println("Num2 ="+num2+" Num5="+num5);
		System.out.println("NUM OF ZEROS="+num5);//Math.min(num2,num5));		
		
	}
}
