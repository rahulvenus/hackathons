Author - Rahul Venugopala Pillai


How to use 
---------

1. Compile the json library and SalaryPrediction.java using the below command.

	javac -cp json-simple-1.1.1.jar SalaryPrediction.java 

2. Run the executable using the below command

	java -cp .:json-simple-1.1.1.jar SalaryPrediction

3. Give the below inputs.

	Desired Job and City of preference.


Public APIs Used:
-----------------

1. Glassdoor API

	Link --> https://www.glassdoor.com/developer/index.htm
	Used to predict the salary of the desired job

2. Wikipedia API 

	Link --> https://www.mediawiki.org/wiki/API:Main_page
    Used to know the description of the desired job

3. Openweathermap API

	Link --> http://openweathermap.org/api
    Used to know the weather conditions of the desired city of employment.

Sample Input and Output
-----------------------
 Welcome to salary prediction !!!

 Enter the job name you want to predict the salary for:
Software engineer

 Enter the city of your preference for employment:
California

 Search is success...

 In this profession, predicted salary range is as mentioned below
 Minimum Salary =66907.25 USD
 Maximum Salary =132476.9 USD
 Average Salary =95194.85 USD

 Various positions that can be reached in this domain are as listed below:
				
			senior software engineer----> Median Salary: 100000 USD
				
			software developer----> Median Salary: 75000 USD
				
			programmer----> Median Salary: 60000 USD
				
			consultant----> Median Salary: 77000 USD
				
			programmer analyst----> Median Salary: 60000 USD
				
			web developer----> Median Salary: 64500 USD
				
			project manager----> Median Salary: 85000 USD
				
			developer----> Median Salary: 70491 USD
				
			intern----> Median Salary: 40000 USD
				
			research assistant----> Median Salary: 34000 USD
				
			systems engineer----> Median Salary: 70000 USD
				
			senior consultant----> Median Salary: 96000 USD
				
			team lead----> Median Salary: 72000 USD
				
			senior developer----> Median Salary: 95000 USD
				
			software consultant----> Median Salary: 60000 USD



Description of the ROLE --- Software engineer
---------------------------------------
A software engineer is a person who applies the principles of software engineering to the design, development, maintenance, testing, and evaluation of the software and systems that make computers or anything containing software work.




Current weather conditions of your preferred city:
-------------------------------------------------

Humidity =88
Maximum Temperature =294.15
Minimum Temperature =289.15

