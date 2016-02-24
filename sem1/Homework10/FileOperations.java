/* 
 * FileOperations.java 
 * 
 * @version: $Id: FileOperations.java,v 1.70 2015/11/02 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/* Non Blocking IO class */

class NonBlockingIO extends Thread{
	
	
	FileOperations fileOperations;
	public static Object startNonBlockingIORead = new Object();
	boolean endOfFileReached = false;
	boolean bufferIsFull = false;
	FileInputStream fileInput;
	boolean closeIscalled = false;
	
	public NonBlockingIO(FileOperations fileOperations){
		this.fileOperations = fileOperations;	
	}
	
	/* Close Operation Function*/
	public void close(){
		try {
			if(fileOperations.fileIsOpenedCheck	)
				fileInput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* Run Method which basically opens and read file in a thread*/
	public void run(){
		
		try {
			fileInput = new FileInputStream(fileOperations.fileName);
			fileOperations.fileIsOpenedCheck = true;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("FILE NOT FOUND....");
			fileOperations.fileIsOpenedCheck = false;
		}
		//System.out.println("File is opened");
		synchronized (fileOperations.fileIsOpened) {
			
			fileOperations.fileIsOpened.notify();
		}
		
		while((fileOperations.fileIsOpenedCheck) & !endOfFileReached){
				synchronized (startNonBlockingIORead) {
					try {
						System.out.println("WaitingForIO Read notification from read()+"+fileOperations.bufferIsEmpty);
						while(!fileOperations.bufferIsEmpty)
							startNonBlockingIORead.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(closeIscalled)
					break;
				int position = 0;			
				System.out.println("position:"+position+"fileOperations.buffer[position : "+fileOperations.buffer[position]);			
				int characterVal = 0;
				try {
					while ((position < 5120)  
							&&(characterVal = fileInput.read()) != -1){
						char charRead = (char) characterVal;
						//System.out.println("character read"+charRead);
						if(fileOperations.buffer[position] == '\u0000'){
							fileOperations.buffer[position] = charRead;
							position++;
						}
						else{
							fileOperations.bufferIsEmpty = false;
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//System.out.println("characterVal ="+characterVal+"position="+position);
				if(characterVal == -1){
					System.out.println("\n\nEnd of file reached !!!!");
					endOfFileReached = true;
					if(position == 0){
						fileOperations.bufferIsEmpty = true;
					}
					else
						fileOperations.bufferIsEmpty =false;
					//System.out.println("Notifying Reader!!!!");
					synchronized (fileOperations.readerCanReadNow) {
						fileOperations.readerCanReadNow.notify();
					}
					break;
				}
				else{
					fileOperations.bufferIsEmpty = false;
					System.out.println("Space not available in buffer!!!!");
					//System.out.println("Notifying Reader!!!!");
					synchronized (fileOperations.readerCanReadNow) {
						fileOperations.readerCanReadNow.notify();
					}
				}
			}
		System.out.println("Exit of NON IO READ");
		close();
	}
}


/* File Operation Class to provide user a open and read method*/
public class FileOperations extends Thread{
	

	public boolean bufferIsEmpty = true;
	// Construct a 10K buffer to hold bytes 
	//1 char = 2 Bytes , 10k=1024 * 10 bytes = 5120 char
	public Character[] buffer = new Character[5120];
	NonBlockingIO nonBlockingIO;
	public Object fileIsOpened = new Object();
	public boolean fileIsOpenedCheck = false;
	public static Object readerCanReadNow = new Object();
	public boolean fileOpened = false;
	public int nextReadPositionInBuffer = 0;
	
	String fileName;
	
	public FileOperations(){
		nonBlockingIO = new NonBlockingIO(this);
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = new Character('\u0000');
  		}
	}
	/* Open  method -
	 * Opens a file and return 1 if failed , 0 if success */
	public int open(String filename){
		this.fileName = filename;
		synchronized(fileIsOpened){
			nonBlockingIO = new NonBlockingIO(this);
			nonBlockingIO.start();
			try {
					//System.out.println("Waiting for opened notifcation from IO");
					fileIsOpened.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		if(!this.fileIsOpenedCheck){
			System.out.println(" You have to reenter the file name !!!");
			return 1;
		}
		return 0;
			
		
	}
	
	/* Close  method -
	 * Closes the file */
	public void close(){
		synchronized (nonBlockingIO.startNonBlockingIORead) {
			nonBlockingIO.closeIscalled = true;
			nonBlockingIO.startNonBlockingIORead.notify();
		}
		nonBlockingIO.close();
	}
	
	/* Read  method -
	 * Read from the buffer and print it on screen
	 * Maximum of 1k data at a time  */
	
	public void read(){
		
		System.out.println("Enter read()");
		if(!this.fileIsOpenedCheck){
			System.out.println(" Error!!! File is not opened to read !!!");
			return;
		}
		
		
		synchronized (nonBlockingIO.startNonBlockingIORead) {
			System.out.println("Indicating non IO to read .. ");
			bufferIsEmpty = true;
			nonBlockingIO.startNonBlockingIORead.notify();
		}
		
		while(!(bufferIsEmpty && nonBlockingIO.endOfFileReached))  {
			
			synchronized (readerCanReadNow) {
				
				try {
					
					//System.out.println("bufferIsEmpty"+bufferIsEmpty);
					while(bufferIsEmpty && !nonBlockingIO.endOfFileReached){
						System.out.println("\n\nRead --- waiting for notification from NONBLOCKING IO");
						readerCanReadNow.wait();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("\n\n\n Maximum of only 1k can be processed  !!! by reader");
				int byteCounter = 0;
				while((nextReadPositionInBuffer < 5120) &&
						(buffer[nextReadPositionInBuffer] != '\u0000') && (byteCounter < 512)){
					System.out.print((Character)buffer[nextReadPositionInBuffer]);
					buffer[nextReadPositionInBuffer] = '\u0000';
					nextReadPositionInBuffer++;
					byteCounter++;
				}
				
				
				
				if((nextReadPositionInBuffer < 5120) && (buffer[nextReadPositionInBuffer] == '\u0000')){
					//System.out.println("breaking....");
					break;
				}
				//System.out.println("reached here...."+nonBlockingIO.endOfFileReached+" "+bufferIsEmpty);
				if(nextReadPositionInBuffer >= 5120){
					synchronized (NonBlockingIO.startNonBlockingIORead) {
						System.out.println(" Notifying NON IO again since buffer is read");
						flushBuffer();
						nextReadPositionInBuffer = 0;
						NonBlockingIO.startNonBlockingIORead.notify();
					}
				}
				//System.out.println("reached here...."+nonBlockingIO.endOfFileReached+" "+bufferIsEmpty);
				try {
					Random r = new Random();
					int randomSleep = r.nextInt(40);
					if(randomSleep < 10 )
						randomSleep+=10;
					//System.out.println("sleep="+randomSleep);
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}
		System.out.println("\n\n End of read");	
		
	}
	
	public void flushBuffer(){
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = '\u0000';
		}
		bufferIsEmpty = true;
	}
	

	
	/* Main  method -
	 * Test environment to test the open and read method
	 */
	public static void main(String[] args){

		
		int retval = 1;
		FileOperations fileOperations = new FileOperations();
		
		System.out.println("Trying to call read method without open()\n");
		fileOperations.read();
		
		while(retval == 1){
			Scanner input = new Scanner(System.in);
	
			System.out.println("\n\nEnter your fileName : ?");
		    String file = input.nextLine();
		    input.close();
			
			retval = fileOperations.open(file);
		}
		
		fileOperations.read();
	}

}
