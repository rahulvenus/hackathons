/* 
 * IntelligentMoneySaverMain.java 
 * 
 * @version: $Id: IntelligentMoneySaverMain.java,v 1.00 2016/02/01 12:00:00 
 * 
 * @author Rahul Venugopala Pillai
 *
 * Description : The main class of the application. Process starts from here.
 * 				This application helps to you save more money based on the transactions of
 * 				two different persons.
 * 
 */

import java.util.Scanner;


public class IntelligentMoneySaverMain {

		public static void main(String[] args) {
			
			System.out.println("\n\t\t********** INTELLIGENT MONEY SAVER**********\n\n");
			String filename1 , filename2;
			Scanner in = new Scanner(System.in);
			IntelligentMoneySaver intelligentMoneySaver = new IntelligentMoneySaver();
			System.out.print("Please enter the file name of the first person:");
			filename1 = in.nextLine();
			System.out.print("\nPlease enter the file name of the second person:");
			filename2 = in.nextLine();
			in.close();
			
			intelligentMoneySaver.start(filename1, filename2);
			
		}

}
