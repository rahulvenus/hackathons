/* 
 * AllSubSets.java 
 * 
 * @version: $Id: AllSubSets.java,v 1.00 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */


public class AllSubSets {

	//No of persons attending the party
	private int numOfPersons;

	//Constructoir initializing the number
	AllSubSets(int n){
		numOfPersons = n;
	}
	//Function to print all the subsets
	private void printAllSubSets(){
		//Total no of subsets = 2 to the power n elements
		int totalNoInPowerSet = (int) Math.pow(2, numOfPersons);
		char temp;
		//printing the null set by default
		System.out.print(""+ "{ {}");
		//Looping from value 1 to power(2,n) to find
		//all possible sets
		for(int i = 1 ; i <totalNoInPowerSet ; i++){
			System.out.print(",");
			//converting the integer to binary
			String num1Bin=Integer.toBinaryString(i);
			int length = num1Bin.length();
			System.out.print("  {");
			for ( int j = length - 1 ; j >= 0 ; j-- ){
				temp = num1Bin.charAt(j);
				//based on the bit set , currsponding digit is
				//print
				if( temp == '1')
					System.out.print((length-j));
			}
			System.out.print("}");
		}
		System.out.print(" } ");
	}
	
	//main function
	public static void main(String[] args){
		int numOfPeople;
		numOfPeople = Integer.parseInt(args[0]);
		AllSubSets allSubSets = new AllSubSets(numOfPeople);
		allSubSets.printAllSubSets();
		}
}
