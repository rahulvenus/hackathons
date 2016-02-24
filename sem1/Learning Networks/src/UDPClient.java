
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {
	  while(true){
		  BufferedReader inFromUser =
	         new BufferedReader(new InputStreamReader(System.in));
	      DatagramSocket clientSocket = new DatagramSocket();
	      InetAddress IPAddress = InetAddress.getByName("localhost");
	      byte[] sendData = new byte[1024];
	      byte[] receiveData = new byte[1024];
	      String sentence = new String(inFromUser.readLine());
	      sendData = sentence.getBytes();

	      System.out.println("sendData.length"+sendData.length+sentence+sentence.length());
	      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9777);
	      clientSocket.send(sendPacket);
	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	      clientSocket.receive(receivePacket);
	      String modifiedSentence = new String(receivePacket.getData());
	      System.out.println("FROM SERVER:" + modifiedSentence);
	      clientSocket.close();
	      sendData = null;
	      sentence = null;
	    }
   }
}