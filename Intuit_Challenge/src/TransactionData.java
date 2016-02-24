/* 
 * TransactionData.java 
 * 
 * @version: $Id: TransactionData.java,v 1.00 2016/02/01 12:00:00 
 * 
 * @author Rahul Venugopala Pillai
 * 
 * Description : The class used to store each transaction
 *
 *
 */
public class TransactionData implements Comparable<TransactionData>{
	private String transName;
	private int frequency;
	private double totalAmount;
	private double avgAmount;
	private char category;
	
	TransactionData(String transName,double amount,char category ){
		this.transName = transName;
		this.frequency = 1;
		this.totalAmount =  amount;
		this.avgAmount =   amount;
		this.category = category;
	}
	
	public void addAnotherOccurence(double amount){
		this.totalAmount +=  amount;
		this.frequency += 1; 
		this.avgAmount = (this.totalAmount/this.frequency);
	}
	
	public void printData(){
		System.out.println(transName+";"+ totalAmount+";"+frequency +";" + avgAmount +";"+ category);
	}
	
	public void setType(char category){
		this.category = category;
	}
	
	public char getCategory(){
		 return category;	
	}
	public String getName(){
		return transName;	
	}
	public double getTotalAmountSpend(){
		return totalAmount;
	}
	public int getFrequency(){
		return frequency;
	}

	@Override
	public int compareTo(TransactionData o) {
		if(this.avgAmount < o.avgAmount)
			return -1;
		else
			return 1;
	}
	
}
