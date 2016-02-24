package testPrep;

class While_1
{
   public static void main(String args[]) {
	int index = 1;
	while ( ++index < 4 ) {
		index++;
		System.out.println("index = " + index );
	}
	System.out.println("index = " + index );
   }
}