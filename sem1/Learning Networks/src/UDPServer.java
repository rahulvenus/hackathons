
import java.io.*;
import java.net.*;

import javax.xml.stream.events.StartDocument;

class UDPServer extends Thread
{
	DatagramSocket serverSocket;
    byte[] receiveData = new byte[1024];
    byte[] sendData = new byte[1024];
    
    public UDPServer(DatagramSocket D) {
    	serverSocket = D;
	}
	@Override
		public void run() {
		while(true)
	        {
	           DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	           try {
				serverSocket.receive(receivePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           String sentence = new String( receivePacket.getData(),receivePacket.getOffset(),receivePacket.getLength());;
	           System.out.println("RECEIVED: " + sentence);
	           InetAddress IPAddress = receivePacket.getAddress();
	           
	           int port = receivePacket.getPort();
	           System.out.println("RECEIVED: " + IPAddress+port);
	           String capitalizedSentence = sentence.toUpperCase();
	           sendData = capitalizedSentence.getBytes();
	           DatagramPacket sendPacket =
	           new DatagramPacket(sendData, sendData.length, IPAddress, port);
	           try {
				serverSocket.send(sendPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           
	        }

		}
   public static void main(String args[]) throws Exception
      {
	   	UDPServer thread1 = new UDPServer(new DatagramSocket(9777));
	   	UDPServer thread2= new UDPServer(new DatagramSocket(9776));
	   thread1.start();
       thread2.start();
            
      }
}
