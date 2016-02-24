import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
	

    static byte[] receiveData = new byte[1024]; 
    byte[] sendData  = new byte[1024]; 
    public static int TCPPORT = 8888;
    public static int UDPPORT = 9000;
    
	public static void main(String[] args) throws IOException {
		if(args[0].equals("UDP")){
			System.out.println("Connection Type UDP !!");
			udpTesting();
		}
		else if(args[0].equals("tcp")){
			System.out.println("Connection Type tcp !!");
			tcpTesting();
		}
		else {
			System.out.println("Enter connection type !! Invalid Argument !!");
		}

	}
	
	public static void udpTesting(){
			try
		     { 
		      DatagramSocket serverSocket = new DatagramSocket(UDPPORT);
		      
		      while(true){
		    	  receiveData = new byte[1024]; 

		          DatagramPacket receivePacket = 
		             new DatagramPacket(receiveData, receiveData.length); 
		          //System.out.println ("Waiting for datagram packet");
		          try {
					serverSocket.receive(receivePacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
		          String messageFromClient = new String(receivePacket.getData());
		          System.out.println("messageFromClient ----- " + messageFromClient);
		          if(messageFromClient.startsWith("END_OF_FILE")){
		        	  System.out.println("File ended ");
		        	  String response = "REQUEST_FOR_TIME";
		        	  byte[] sendData = new byte[1024];
		        	  sendData = response.getBytes();
		        	  System.out.println("going to send data to calcluate time");
		              DatagramPacket reply = new DatagramPacket(sendData, sendData.length, 
		            		  						receivePacket.getAddress(), receivePacket.getPort());
		              try {
						serverSocket.send(reply);
		              } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
		              }
		        	  break;
		          }
		          else{
		        	  String response = "NEXT_DATA_REQUEST";
		        	  byte[] sendData = new byte[1024];
		        	  sendData = response.getBytes();
		        	  //System.out.println("going to send data to client for next data");
		              DatagramPacket reply = new DatagramPacket(sendData, sendData.length, 
		            		  						receivePacket.getAddress(), receivePacket.getPort());
		              try {
						serverSocket.send(reply);
		              } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
		              }
		          }
		          
		      }
		      System.out.println("Server ending ... ");
		      

		      receiveData = new byte[1024]; 

		      DatagramPacket receivePacket = 
		             new DatagramPacket(receiveData, receiveData.length); 
		      System.out.println ("Waiting for time calculated !!");
		      try {
		    	  serverSocket.receive(receivePacket);
		      } catch (IOException e) {
		    	  e.printStackTrace();
		      }
		      String messageFromClient = new String(receivePacket.getData());
		      System.out.println("Time elapsed for UDP process ----- " + messageFromClient);
		      serverSocket.close();
		     }
		     catch (SocketException ex) {
		        System.out.println("UDP Port 9876 is occupied.");
		        System.exit(1);
		      }
			
		
	}
	
	public static void tcpTesting() throws IOException{
		
		ServerSocket listener = new ServerSocket(TCPPORT); 
		Socket socket = listener.accept();
		BufferedReader input = null;
	    PrintWriter output = null;
	    String messageFromClient = new String();
	    
	    try {
            input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
	    }
	    catch (IOException e) {
            e.printStackTrace();
        }
	      
	      while(true){
	    	  messageFromClient = input.readLine();
	          System.out.println("messageFromClient ----- " + messageFromClient);
	          if(messageFromClient.equals("END_OF_FILE")){
	        	  System.out.println("File ended ");
	        	  String response = "REQUEST_FOR_TIME";
	        	  output.println(response);
	        	  break;
	          }
	          else{
	        	  String response = "NEXT_DATA_REQUEST";
	        	  output.println(response);
	          }
	          
	      }
	      System.out.println("Server ending ... ");
	     
	      messageFromClient = input.readLine();
	      System.out.println("Time elapsed for TCP process ----- " + messageFromClient);
	      listener.close();
	}

}
