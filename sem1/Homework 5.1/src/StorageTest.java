/* 
 * StorageTest.java 
 * 
 * @version: $Id: StorageTest.java,v 1.70 2015/09/28 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/28 12:00:00 
 */

import java.util.InputMismatchException;
import java.util.Scanner;


//This is the test class to test the Fixed and Dynamic Storages
public class StorageTest {

	@SuppressWarnings("unchecked")
	public static void main(String args[])	{
    	int choice;
    	Scanner scanner;
    	while(true){
        	System.out.println("Enter the storage you need\n\t1.Fixed Storage\n\t2.Dynamic Storage ");
        	scanner = new Scanner(System.in);
    		try{
    			choice = scanner.nextInt();
    		
	    		if((choice == 1) || (choice ==2)){
	    			break;
	    		}
	    		else
	    		{
	    			System.out.println("Wrong choice!!! ");
	    		}
    		}
    		catch(InputMismatchException I){
    			System.out.println("Wrong input!!! ");
    		}
    		scanner.close();
    	}
    	
    	if(choice == 1){
    		
    		int capacity;
			System.out.print("Enter the size of the storage (enter no > 9 ):");
			scanner = new Scanner(System.in);
			capacity = scanner.nextInt();
			Storage<String, String> aStorageString = new StorageFixed<String, String>(capacity);
			Storage<String, String> aStorageStringClone;
			//Storage<Integer, String> aStorageInteger = new StorageFixed<Integer, String>();
			
			System.out.println("\nEnter your elements : ");
			Scanner scan;
			for(int i =0;i< 5; i++){
				System.out.print("\n@ position "+i+" : ?  ");
				scan= new Scanner(System.in);
				aStorageString.add(scan.nextLine());
			}
			
			aStorageString.add(7,"rahul");
			aStorageString.add(8,"aravind");
			((StorageFixed<String, String>) aStorageString).display();
			aStorageString.clear();
			((StorageFixed<String, String>) aStorageString).display();
			aStorageString.add(7,"rahul");
			aStorageString.add(5,"vinay");
			((StorageFixed<String, String>) aStorageString).display();
			System.out.println("\n\nCurrent Storage Used Capacity : " + aStorageString.capacity());

			aStorageStringClone = (StorageFixed<String, String>)aStorageString.clone();
			
			System.out.println("\n\nGoing to dsiplay the cloned storage ");
			
			((StorageFixed<String, String>) aStorageStringClone).display();
    	}
    	else{
    		Storage<String, String> aStorageString = new StorageDynamic<String, String>();
    		Storage<String, String> aStorageStringCloned ;
    		((StorageDynamic<String, String>) aStorageString).display();
    		aStorageString.addElement("rahul");
    		((StorageDynamic<String, String>) aStorageString).display();
    		aStorageString.addElement("ajith");
    		aStorageString.addElement("midhun");
    		((StorageDynamic<String, String>) aStorageString).display();
    		aStorageString.add(5 ,"aravind");
    		((StorageDynamic<String, String>) aStorageString).display();
    		aStorageString.add(2 ,"villan");
    		((StorageDynamic<String, String>) aStorageString).display();
    		aStorageString.add("ramvillan");
    		((StorageDynamic<String, String>) aStorageString).display();
    		
    		System.out.print("\n\nGetting element at index 3: ");
    		System.out.print(aStorageString.get(3));
    		
    		System.out.println("\n\nGoing to clone the storage");
    		
    		aStorageStringCloned =(Storage<String, String>) aStorageString.clone();
    		System.out.println("\nCloned Storage is ");
    		((StorageDynamic<String, String>)aStorageStringCloned).display();
    		
    		System.out.println("\n\nFirst Element in the storage: "+ aStorageString.firstElement());
    		System.out.println("\n\nLast Element in the storage: "+ aStorageString.lastElement());
    		
    		
    	}
    }
}