import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	
	
    static byte[] receiveData = new byte[1024]; 
    byte[] sendData  = new byte[1024]; 
    static boolean endOfFileReached = false;
    static FileReader fileReader = null;
    public static String fileName = "words.txt";
    static BufferedReader bufferedReader;
    static DatagramPacket sendDataPacket;
    static DatagramSocket clientSocket; 
    public static int UDPPORT = 9000;
    
    //for tcp
    public static int TCPPORT = 8888;    
    public static Socket socket;
    public static BufferedReader in;
    public static PrintWriter out;

    private static String readNextLineFromFile(){
		
    	//System.out.println("enter readNextLineFromFile");
		
		String line = new String();
    
		try {
			if((line = bufferedReader.readLine()) != null) {
				//System.out.println(line);
			    return line;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;

	} 
public static void main(String[] args) throws SocketException {
		
		try {
			fileReader = new FileReader(fileName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		bufferedReader = new BufferedReader(fileReader);
		
		if(args[0].equals("UDP")){
			System.out.println("UDP !!");
			udpTesting();
		}
		else if(args[0].equals("tcp")){
			System.out.println("tcp !!");
			try {
				tcpTesting();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Enter connection type !! Invalid Argument !!");
		}
		try {
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	
public static void udpTesting() throws SocketException{
		
		long startTime = System.currentTimeMillis();
		clientSocket = new DatagramSocket();
		System.out.println("udp connection");
		try
	     {
	      while(!endOfFileReached){	    	  
	    	  
	    	  //Read line from file into this string
	    	  String dataFromFile = readNextLineFromFile();
	    	  if(dataFromFile == null){
	    		  dataFromFile ="END_OF_FILE";
	    	  }
	    	/*  byte[] dataInBytes = dataFromFile.getBytes();
	    	  InetAddress hostAddress = InetAddress.getByName("localhost");
		
	          int serverPort             = 9000;
	             = new DatagramPacket(dataInBytes, dataInBytes.length, hostAddress, serverPort);
	          clientSocket.send(sendDataPacket);*/
	    	  udpSendDataToServer(dataFromFile);		
	    	  
	          
	          if(dataFromFile.equals("END_OF_FILE")){
	        	  endOfFileReached = true;
	        	  break;
	          }
	         
	          while(true){
	        	  
	        	  //wait for reply from server
		          byte [] inputbuffer = new byte[1024];
		          DatagramPacket receivePacket = new DatagramPacket(inputbuffer, inputbuffer.length);
		          clientSocket.receive(receivePacket);
		          String messageFromServer = new String(receivePacket.getData());
		          
		          if(messageFromServer.startsWith("NEXT_DATA_REQUEST")){
		        	  //System.out.println("Packet recieved.. going to send next data .. ");
		        	  break;
		          }
		          else if(messageFromServer.startsWith("PACKET_NOT RECIEVED")){
		        	  clientSocket.send(null);
		          }
		          else{
		        	  System.out.println("messageFromServer----"+messageFromServer);
		          }
		       }
	          
	      }
	      System.out.println("End of file reached ");
	      while(true){
        	  
        	  //wait for reply from server
	          byte [] inputbuffer = new byte[1024];
	          DatagramPacket receivePacket = new DatagramPacket(inputbuffer, inputbuffer.length);
	          clientSocket.receive(receivePacket);
	          
	          String messageFromServer = new String(receivePacket.getData());
	          
	          if(messageFromServer.startsWith("REQUEST_FOR_TIME")){
	        	  System.out.println("REQUEST_FOR_TIME");
	        	  long estimatedTime = System.currentTimeMillis() - startTime;
	        	  System.out.println(" Elapsed time for UDP : "+estimatedTime);
	        	  String estimatedTimeString = String.valueOf(estimatedTime);
	        	  udpSendDataToServer(estimatedTimeString);		          
	        	  break;
	          }
	          else{
	        	  System.out.println("messageFromServer----"+messageFromServer);
	          }
	       }
	    }
		catch (IOException ex) {
			ex.printStackTrace();
	      }
	}
	
	static void udpSendDataToServer(String message) throws IOException{
		
		if(message!= null){
			byte[] dataInBytes = message.getBytes();

	  	  	InetAddress hostAddress = InetAddress.getByName("localhost");
	  
	        sendDataPacket   = new DatagramPacket(dataInBytes, dataInBytes.length, hostAddress, UDPPORT);
		}
	    clientSocket.send(sendDataPacket);
	     
	}
	
public static void tcpTesting() throws IOException{
		
		long startTime = System.currentTimeMillis();
		System.out.println("tcp connection");
		// Setup networking
		String serverAddress = "localhost";
		try {
			socket = new Socket(serverAddress,TCPPORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	      while(!endOfFileReached){	    	  
	    	  
	    	  //Read line from file into this string
	    	  String dataFromFile = readNextLineFromFile();
	    	  if(dataFromFile == null){
	    		  out.println("END_OF_FILE");	
	    		  endOfFileReached = true;
	    		  break;
	    	  }
	    	
	    	  out.println(dataFromFile);	

	         
	          while(true){
	        	  
		          String messageFromServer = in.readLine();
		          
		          if(messageFromServer.startsWith("NEXT_DATA_REQUEST")){
		        	  //System.out.println("Packet recieved.. going to send next data .. ");
		        	  break;
		          }
		          else{
		        	  System.out.println("messageFromServer----"+messageFromServer);
		          }
		       }
	          
	      }
	      
	      System.out.println("End of file reached ");
        	  
	      String messageFromServer = in.readLine();
	      System.out.println(messageFromServer);
	      
	      if(messageFromServer.startsWith("REQUEST_FOR_TIME")){
	        long estimatedTime = System.currentTimeMillis() - startTime;
	        System.out.println(" Elapsed time for TCP : "+estimatedTime);
	        String estimatedTimeString = String.valueOf(estimatedTime);
	        out.println(estimatedTimeString);		          
	      }
	      else{
	        System.out.println("messageFromServer----"+messageFromServer);
	      }
	    }

	

}