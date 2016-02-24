/* 
 * IntelligentMoneySaver.java 
 * 
 * @version: $Id: IntelligentMoneySaver.java,v 1.00 2016/02/01 12:00:00 
 * 
 * @author Rahul Venugopala Pillai
 *
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;


public class IntelligentMoneySaver {
	
	//HashMap to store the user data based on the transaction name
	private HashMap<String,TransactionData> userData = new HashMap<String,TransactionData>();
	//HashMap to get the transaction type based on the input key file given
	private HashMap<String,Character> typeTransData = new HashMap<String,Character>();
	
	
	//Sets to identify the transaction
	
	//Set of transactions which are ignored
	private HashSet<TransactionData> ignorableTransactions = new HashSet<TransactionData>();
	//Set of unknown transactions
	private HashSet<TransactionData> unknownTransactions = new HashSet<TransactionData>();
	//Tree Set of restaurant transactions in-order to get the order based on less expense
	private TreeSet<TransactionData> restaurantTransactions = new TreeSet<TransactionData>();
	//Tree Set of gas pump transactions in-order to get the order based on less expense
	private TreeSet<TransactionData> gaspumpTransactions = new TreeSet<TransactionData>();
	//Tree Set of market transactions in-order to get the order based on less expense
	private TreeSet<TransactionData> marketTransactions = new TreeSet<TransactionData>();
	//Tree Set of taxi transactions in-order to get the order based on less expense
	private TreeSet<TransactionData> taxiTransactions = new TreeSet<TransactionData>();
	//Tree Set of house rent payment transactions in-order to get the order based on less expense
	private TreeSet<TransactionData> stayTransactions = new TreeSet<TransactionData>();
	
	//File which is provided to identify the transactions based on the name
	private String keyFile = "TransactionDataInfokey.txt";
	//private int keyLength = 5;	
	
	/*
	 * Function to identify the transactions from the transaction name
	 * 
	 * @param  transName  the name of the transaction
 	 * @return type of the transaction ( U - for unknown)
	 */
	private char identifyTheTransactions(String transName){
		for(String transactionKey: typeTransData.keySet()){
			if(transName.startsWith(transactionKey))
				return typeTransData.get(transactionKey);
		}	
		return 'U'; //for Unknown
	}
	
	/*
	 * Function to identify the transaction key to save the transaction data inside the map is
	 * already formed
	 * 
	 * @param  key  the transaction key which is formed from the transaction name
 	 * @return type of the transaction if present , else empty string
	 */
	private String isKeyAlreadyFormed(String Key){
		for(String key : userData.keySet()){
			if(key.startsWith(Key)){
				return key;
			}
		}
		return "";
	}
	
	/*
	 * Function to form the transaction key to save the transaction data  inside the map
	 * 
	 * @param  transaction   the transaction name
 	 * @return transKey the transaction key
	 */
	private String formTransactionKey(String transaction){
		String transKey = "";
		String[] transKeyValues = transaction.split(" ");
		for(String parts : transKeyValues){
			if(parts.matches(".*\\d+.*")){
				break;
			}
			else {
				transKey += parts;
				String existingKey = isKeyAlreadyFormed(transKey);
				if(!existingKey.equals(""))
					return existingKey;
			}
			
		}
		return transKey;
	}

	/*
	 * Function to read the transaction data from the file and store the data inside a 
	 * map
	 * 
	 * @param  hmap   the hashmap to store the data
	 * @param  filename   the name of the file to read the data
	 * 
 	 * @return none
 	 */
	private void readDataFromUserLog(HashMap<String,TransactionData> hmap , String filename){
		
		BufferedReader reader = null;
		
		try{
			reader = new BufferedReader(new FileReader(filename));
			String line;
			while((line = reader.readLine()) != null){
				String transaction = line.split(",")[1];
			/*	String transactionKey = (transaction.length() > keyLength ) ?
						transaction.substring(0,keyLength):
							transaction;*/
				String transactionKey = formTransactionKey(transaction);
				double amount = Double.parseDouble(line.split(",")[2]);
				if(hmap.containsKey(transactionKey)){
					TransactionData d = hmap.get(transactionKey);
					d.addAnotherOccurence(amount);
					hmap.put(transactionKey,d );

				}
				else{
					char type = identifyTheTransactions(transaction);
					TransactionData d =new TransactionData(transaction,amount,type);
					hmap.put(transactionKey,d);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Sorry!! File : "+ filename +" required for processing!!!");
			System.out.println("\n\nPlease try again !!!");
			System.exit(1);
			//e.printStackTrace();
		}
		finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Function to read the data from the file ("keyfile") provided to the application  to 
	 * identify the transaction  based on the transaction name.
	 * 
 	 * @return none
 	 */
	private void readKeyDataToIdentifyTransactions(){
		
		BufferedReader reader = null;
		
		try{
			reader = new BufferedReader(new FileReader(keyFile));
			String line;
			while((line = reader.readLine()) != null){
				if((line.startsWith("#")) || (line.equals("")))
					continue;
				else{
					String transacName = line.split(";")[0];
					char transacType = line.split(";")[1].charAt(0);
					typeTransData.put(transacName,transacType);
					
				}
			}
			reader.close();
		}
		catch (Exception e) {
			System.out.println("Sorry!!  "+ keyFile +" required for processing!!!");
			System.out.println("Please try again !!!");
			System.exit(1);
		}
		
	}
	
	/*
	 * Function to print the recommendations / tips / strategies to the user based on
	 * the data read already from the transaction files given to the application by the user.
	 * 
 	 * @return none
 	 */
	private void printRecommendation(){
		
		System.out.println( "\n\nRecommendations for you to save money......");
		
		System.out.println( "\n\n\tBest  restaurants ......");
		for(int i = 0 ; i < 3 ; i++){
			if(restaurantTransactions.size() > 0){
				System.out.println( "\t\t"+(i+1) +"."+ restaurantTransactions.pollFirst().getName());
			}
		}
		
		System.out.println( "\n\tBest  markets ......");
		for(int i = 0 ; i < 3 ; i++){
			if(marketTransactions.size() > 0){
				System.out.println( "\t\t"+(i+1) +"."+ marketTransactions.pollFirst().getName());
			}
		}
		
		System.out.println( "\n\tBest  housing options ......");
		for(int i = 0 ; i < 3 ; i++){
			if(stayTransactions.size() > 0){
				System.out.println( "\t\t"+(i+1) +"."+ stayTransactions.pollFirst().getName().substring(0,42));
			}
		}
		
		System.out.println( "\n\tBest  gas pump stations ......");
		for(int i = 0 ; i < 3 ; i++){
			if(gaspumpTransactions.size() > 0){
				System.out.println( "\t\t"+(i+1) +"."+ gaspumpTransactions.pollFirst().getName());
			}
		}
		
		System.out.println("\nTIPS and Strategies for you to save more money........");
		
		int counter = 1;
		
		float totalAmountTaxi = 0;
		int numTimesToMarket = 0;
		int numTimesToRestaurant = 0;
		
		
		for(String transactionKey: userData.keySet()){
			
			if(userData.get(transactionKey).getCategory() == 'T'){
				totalAmountTaxi += userData.get(transactionKey).getTotalAmountSpend();
			}
			else if(userData.get(transactionKey).getCategory() == 'M'){
				numTimesToMarket += userData.get(transactionKey).getFrequency();
			}
			else if(userData.get(transactionKey).getCategory() == 'R'){
				numTimesToRestaurant += userData.get(transactionKey).getFrequency();
			}
			
		}
		
		if(totalAmountTaxi > 1500){
			System.out.println( "\n\t" + counter++ +".Plan to buy a vehicle."
					+ "\n\n\t  It would be better if you buy a car for the time being"
					+ "\n\t  as the average expense for taxis are more."
					+ "Also once you are done with the trip you\n\t  can sell it off.");
		}
		
		if(totalAmountTaxi < 1500)
		{		System.out.println( "\n\t" + counter++ +".Prefer taxi over buying a car."
					+ "\n\n\t  It would be better if you prefer taxi instead of buying a car"
					+ "\n\t  as the average expense for travel are comparitively less.");
		}
		
		if(numTimesToMarket > 48){
			System.out.println( "\n\t" + counter++ +".Switch to online shopping."
					+ "\n\n\t  It would be better if you prefer online shopping "
					+ "instead of going market\n\t  which saves time also for you");
		}
		
	    if(numTimesToRestaurant > 300){
		   System.out.println( "\n\t" + counter++ +".Prefer cooking instead going restaurants."
					+ "\n\n\t  It would be better if you prefer cooking  "
					+ "instead of going resturants / pubs\n\t  which saves more money for you");
		
	    }
	    
	    System.out.println("\n\n\nGenerating file \"Report_Unknown_Transactions.txt\" "
	    		+ "for unknown transactions .... Please check....");
	    
	    try{
	    	PrintWriter writer = new PrintWriter("Report_Unknown_Transactions.txt", "UTF-8");

	    	 writer.println("\n\nList of the unknown transactions .. \n\n");
		    for(TransactionData unknownData :unknownTransactions ){
				writer.println(unknownData.getName());
			}
		    writer.println("\n\nPlease check README/TransactionDataInfokey.txt to know how can you"+
		    			" help me to identify the unknown transactions .. ");
		    writer.close();
	    }
	    catch(Exception e){
	    	System.out.println("Unable to create the report . Please check permission "
	    			+ "or ensure disk space..");
	    }
		
		
	}
	
	
	/*
	 * Test Function to print the transaction data from the files .
	 * 
 	 * @return none
 	 */
	@SuppressWarnings("unused")
	private void printData(HashMap<String,TransactionData> data){
	
		for(String s :data.keySet()){
			TransactionData trans = data.get(s);
			trans.printData();
		}
	}
	
	/*
	 * Function to categorize the transaction data from the files .
	 * I - Ignored Transactions 
	 * R - Restaurant/ Pub Transactions 
	 * G - Gas pump Transactions 
	 * M - Market Transactions 
	 * T - Taxi Transactions 
	 * S - Housing/Stay Transactions 
	 * 
 	 * @return none
 	 */
	private void categorizeData(){
		for(String s :userData.keySet()){
			TransactionData trans = userData.get(s);
			if(trans.getCategory() == 'I'){
				ignorableTransactions.add(trans);
			}
			else if(trans.getCategory() == 'R'){
				restaurantTransactions.add(trans);
			}
			else if(trans.getCategory() == 'G'){
				gaspumpTransactions.add(trans);
			}
			else if(trans.getCategory() == 'M'){
				marketTransactions.add(trans);
			}
			else if(trans.getCategory() == 'T'){
				taxiTransactions.add(trans);

			}
			else if(trans.getCategory() == 'S'){
				stayTransactions.add(trans);
			}
			if(trans.getCategory() == 'U'){
				unknownTransactions.add(trans);
			}
		}
	}
	
	/*
	 * The main function to read and  categorize the transaction data from the files.
	 * And then prints the recommendations based on the transactions.
	 * 
	 * @param filenameA The file containing the transactions of person A
	 * @param filenameB The file containing the transactions of person B
	 *  
 	 * @return none
 	 */
	public void start(String filenameA , String filenameB){
		
		// READ DATA
		readKeyDataToIdentifyTransactions();
		readDataFromUserLog(userData,filenameA);
		readDataFromUserLog(userData,filenameB);
		
		//Enable the below  to print the data read from the files
		//printData(userData);
				
		// ADD  DATA TO RESPECTIVE SETS
		categorizeData();		
			
		// GIVE RECOMMENDATION
		printRecommendation();
	}
	
	
}
