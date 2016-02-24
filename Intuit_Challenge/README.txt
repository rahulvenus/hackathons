READ ME :
**************
 Created by Rahul Venugopala Pillai


Files needed for the application to work:
	
	1. IntelligentMoneySaverMain.java   - The main file which contains the main method which talks to user
	2. IntelligentMoneySaver.java  - The file which contains the logic and as well as prints the recommendations
	3. TransactionDataInfoKey.txt  - The file which contains the info to identify the transaction based on the name


1. To start the application :    
				Steps  :
					1) javac * .java
					2) java IntelligentMoneySaverMain
					3) Give the file names containing the transactions of the two persons


Summary
***********

Once the application starts  ,  it reads the transaction data from both the users  and store it .

It will identify the transactions based on the name using the keys from TransactionDataInfoKey.txt

It will group the data based on the transaction type.

Finally , the best choices are given to the user based on least avg. expenditure .

In case of restaurants , the user are provided with 3 choices of cheapest  restaurants.
In the same way for most of the transaction types choices are given to the user.

In addition to this , based on the taxi and gas usage , the user is advised to buy a car or  rent a  taxi.

Based on the frequency of the restaurant transactions , user is advised to cook food so that he can save more money

And finally based on the no of market transactions , user is advised to prefer online shopping.

The threshold values for all these conditions are set based on my assumptions about daily spendings.
It can be changed though .  I could not find a web service or api to identify the transaction and so i have used a key file
“TransactionDataInfoKey.txt  “ . The transaction types of unknown transactions can be added to this file and it is mentioned 
in the file how to add it.

Thank you!  Wish you a nice day!!


Contact Info :

email : rahulvenus@gmail.com
mob   : 585-642-8756

