/* 
 * SalaryPrediction.java 
 * 
 * @version: $Id: SalaryPrediction.java,v 1.00 2016/09/11 12:00:00 
 * 
 * @author Rahul Venugopala Pillai
 *
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


// Main Class for predicting salary and knowing about the preferred city of employment
class SalaryPrediction {
	
	//Inputs from user
	String userInputForJob;
	String userInputForCity;
	
	//Variables to store the salary details of preferred job
	long minimumSalary;
	long maximumSalary;
	long averageSalary;
	
	//Constructor of the main class
	SalaryPrediction(){
		minimumSalary=Long.MAX_VALUE;
		maximumSalary=Long.MIN_VALUE;
	}
	
	/*
	 * Function to get inputs from user.
	 * 
	 * Sample Input
	 * 
	 * Job - software engineer
	 * City - London
	 * 
	 */ 
	 
	public void getUserInputs(){
		
		Scanner input = new Scanner(System.in);
		
		System.out.println(" Welcome to salary prediction !!!");
		
		System.out.println(" Enter the job name you want to predict the salary for:");
     	userInputForJob = input.nextLine();
		userInputForJob = userInputForJob.replace(' ','+');
		
		System.out.println(" Enter the city of your preference for employment:");
		userInputForCity = input.nextLine();
		userInputForCity = userInputForCity.replace(' ','+');
		
		input.close();
		
	}
	
	
	/*
	 * Main Function to predict the salary of the desired job and to know about
	 * the weather conditions of the preferred city of employment
	 * 
	 */
	public void startSalaryPrediction(){
		
		//Initially all the inputs are obtained from the user
		getUserInputs();
		
		//Parser for JSON variables is initialized here.
	    JSONParser parser = new JSONParser();
	    
	    // Salary Prediction using Glassdoor API
	    
	    try {
	    	
	    	// If new keys are generated, the below variables should be updated.
	    	
	    	String glassdoorAPIKey = "93034";
	    	String glassdoorAPIValue = "ecwTC5UQhtG";
	    	
	    	// Reading the data from the API and storing it in the form of JSON.
	     	String jsonString = readUrl("http://api.glassdoor.com/api/api.htm?t.p="
	     			+ glassdoorAPIKey
	     			+ "&t.k="
	     			+ glassdoorAPIValue
	     			+ "&userip=&useragent="
	     			+ "&format=json&v=1&action=jobs-prog&countryId=1&jobTitle="+userInputForJob);
	     	
	     	//System.out.println(jsonString);
	     	
	     	//Parsing the JSON here
	     	Object obj = parser.parse(jsonString);
	     	JSONObject jsonObject = (JSONObject) obj;
	     	
	     	boolean success = (boolean) jsonObject.get("success");
	     	
	     	try{
		     	if(success){
		     		System.out.println(" Search is success...");
	 			    JSONObject response =  (JSONObject)jsonObject.get("response");
	 			    JSONArray resultsArray =  (JSONArray)response.get("results");
	 			    
	 			    // Parsing the JSON to get the lowest, highest and average salaries of that 
	 			    // particular job
	 			    minimumSalary = (Long)response.get("payLow");
	 			    maximumSalary = (Long)response.get("payHigh");
	 			    averageSalary = (Long)response.get("payMedian");
	 			    
	 			    System.out.print(" In this profession, predicted salary range is as mentioned below");
				    System.out.println(" Maximum Salary ="+maximumSalary);
				    System.out.println(" Minimum Salary ="+minimumSalary);
				    System.out.println(" Average Salary ="+averageSalary);
				    
				    
				    //Listing out the possible future jobs by selecting this type of job.
				    System.out.println(" Various positions that can be reached in this domain are as listed below:");
	 			    
	 			    for(int i=0;i<resultsArray.size();i++){
	 			    	JSONObject resultObj = (JSONObject)resultsArray.get(i);
	 			   
	 			    	long medianSalary = (long) resultObj.get("medianSalary");
	 			    	String jobTitle = (String) resultObj.get("nextJobTitle");
	 			   
	 			        System.out.println("\t\t\t\t\t\t\t\t\t\t"+jobTitle+ "----> Median Salary: "+medianSalary);
	 			    }
			         
		     	}
		     	else{
		     		System.out.println(" NO RESULTS TO DISPLAY FOR THE ENTERED SEARCH");
		     	}
	     	}
	     	catch(Exception e){
	     		System.out.println(" Input went bad!! Please retry");
	     	}
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	    
	    
	    // Information about the job using WIKI API
	    try {
	     	String jsonString = readUrl("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&exintro&titles="+
	     			userInputForJob+"&format=json");
	     	
	     	//System.out.println(jsonString);
	     	Object obj = parser.parse(jsonString);
	     	JSONObject jsonObject = (JSONObject) obj;
	     	JSONObject query =  (JSONObject)jsonObject.get("query");
	     	JSONObject pages =  (JSONObject)query.get("pages");
	     	
	     	// The keys can change any time. So getting the value of first key dynamically.
	     	Set<Object> keys = pages.keySet();
	     	String firstKey = (String) (keys.toArray())[0];
	     	JSONObject firstPage =  (JSONObject)pages.get(firstKey);
	     	
	     	// Getting the description of the job here.
	     	
	     	String extract = (String) firstPage.get("extract");
	     	System.out.println("\n\n\nDescription of the ROLE --- "+ userInputForJob.replace("+"," "));
	     	System.out.println("---------------------------------------");
	     	
	     	// Formatting the description for user readable form.
	     	extract = extract.replace("<p>","").replace("</p>", "").replace("<b>","").replace("</b>","");
	     	System.out.println(extract);
	     	
	    }
	    catch(Exception e){
	    	System.out.println(" Description could not be found!! Better luck next time!!");
	    }
	    
	   // Finding the information about the weather of the preferred city of employment
	    
	    try {
	    	
	    	//Generated key is mentioned below.
	    	//Needs to be changed if new key is generated.
	    	String appID = "9371e56716e20213d5a7591498722cb3";
	    	
	     	String jsonString = readUrl("http://api.openweathermap.org/data/2.5/weather?q="
	     			+ userInputForCity
	     			+ "&APPID="
	     			+ appID);
	     	
	     	//System.out.println(jsonString);
	     	Object obj = parser.parse(jsonString);
	     	JSONObject jsonObject = (JSONObject) obj;
	     	JSONObject main =  (JSONObject)jsonObject.get("main");
	     	String humidity = main.get("humidity").toString();
	     	String maxTemp =  main.get("temp_max").toString();
	     	String minTemp =  main.get("temp_min").toString();
	     	
	     	System.out.println("\n\n\nCurrent weather conditions of your preferred city:");
	     	System.out.println("-------------------------------------------------");
	     	System.out.println("\nHumidity ="+humidity);
	     	System.out.println("Maximum Temperature ="+maxTemp);
	     	System.out.println("Minimum Temperature ="+minTemp);
	     
	    }
	    catch(Exception e){
	    	System.out.println(" City could not be found!! Better luck next time!!");
	    }
	    
	}
	
	//  The main function.
	public static void main(String[] args) {
		
		SalaryPrediction salaryPrediction  = new SalaryPrediction();
		salaryPrediction.startSalaryPrediction();
		
	}
	 
	// Read URL function to get the URL data from the public API
	private static String readUrl(String urlString) throws Exception {
	        BufferedReader reader = null;
	        try {
	            URL url = new URL(urlString);
	            reader = new BufferedReader(new InputStreamReader(url.openStream()));
	            StringBuffer buffer = new StringBuffer();
	            int read;
	            char[] chars = new char[1024];
	            while ((read = reader.read(chars)) != -1)
	                buffer.append(chars, 0, read); 

	            return buffer.toString();
	        } finally {
	            if (reader != null)
	                reader.close();
	        }
	}
	 
}
