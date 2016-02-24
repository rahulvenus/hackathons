import java.util.Scanner;

public class StorageTest {
    public static void main(String args[])	{
		
		int capacity;
		System.out.println("Enter the size of the storage :");
		Scanner scan = new Scanner(System.in);
		capacity = (scan.nextInt());
		Storage<String, String> aStorageString = new StorageFixed<String, String>(capacity);
		//Storage<Integer, String> aStorageInteger = new StorageFixed<Integer, String>();
		
		System.out.println("Enter your elements : ");
		for(int i =0;i< 5; i++){
			System.out.println(i);
			scan = new Scanner(System.in);
			aStorageString.add(scan.nextLine());
		}
		
		aStorageString.add(7,"rahul");
		aStorageString.add(8,"aravind");
		aStorageString.display();
		aStorageString.clear();
		aStorageString.add(7,"rahul");
		aStorageString.display();
		System.out.println(aStorageString.capacity());
	
    };
}