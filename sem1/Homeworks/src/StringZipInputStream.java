/* 
 * StringZipInputStream.java 
 * 
 * @version: $Id: StringZipInputStream.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StringZipInputStream	{
	
	FileInputStream inputStream;
	byte readBuffer;
	DataInputStream dis;
	static String binData ="";
	
	// Creates a new input stream with a default buffer size.
	public StringZipInputStream(InputStream out)	{
		inputStream = (FileInputStream) out;
		 dis = new DataInputStream(inputStream);
		
	}
	// Reads data into a string. the method will block until some input can be read; otherwise, no bytes are read and null is returned.
	public String read() {
		
		String word ="";
		boolean wordFound = false;
		byte x = -1;
		while(true) {
		try {	
	    	  char curChar;
		      x = (byte) inputStream.read();
		      System.out.println("x="+x);
		      int y =(int)x & 0x00FF;
		      System.out.println("y="+y);
		      String byteString = convertBinary(y);
		      //byteString = String.format("%16s", Integer.toBinaryString(y)).replace(' ', '0');
		      System.out.println("byteString="+byteString);
		      //System.exit(1);
		      binData = binData + byteString;
		      System.out.println(binData);
		      System.out.println(binData.length());

		      int j = 0;
		      for(int i=0;i<binData.length();i++){
		    	  
		    	  String temp = binData.substring(0,i+1);
		    	  System.out.println("binData " +binData+"Temp String = "+ temp);
		    	  String currString = getCharacterFromList(temp);
		    	  if(binData.length() >= 8){
		    		  if(binData.equals("00000000") || binData.contains("00000000") ||binData.contains("11111111") )
		    			  return null;
		    	  }
		    	  
		    	  if(binData.length() == 8){
		    		  temp = trimZerosString(binData);
		    		  currString = getCharacterFromList(temp);
		    		  if(currString != null)
		    			  binData = "";
 		    	  }

		    	  if(currString!= null){
		    		  
		    		  temp="";
		    		  curChar = currString.charAt(0);
		    		  System.out.println("\t\t\t curChar = "+ curChar);
		    		  word = word + curChar;
		    		  if(binData.equals("")){
		    			  wordFound = true;
		    		  }
		    		  else
		    			  binData = binData.substring(i+1);
		    		  System.out.println("binData = " +binData + "Word ="+word);
		    		  if(curChar == ' ' || curChar == '\n'){
		    			  wordFound = true;
		    			  temp="";
		    			  System.out.println("binData = " +binData + "Word ="+word);
		    			  break;  
		    		  }
		    		  i=0;
		    	  }
		      }
		      if(wordFound)
		    	  break;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return word; 
	}
	
String getCharacterFromList(String binString){
	int i =0;
	String charRead = "";
	boolean charFound = false;
	for(i = 0 ; i < StringZipOutputStream.mapSize ; i++){
		if(StringZipOutputStream.charCode[i].equals(binString)){
			charFound = true;
			break;
		}
	}
	
	if(charFound){
		charRead += StringZipOutputStream.charList[i];
		return charRead;
	}
	else
		return null;
	
}
	// Closes this input stream and releases any system resources associated with the stream.
	public void close() {
		
		try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String trimZerosString(String temp){
		int k =0;
		System.out.println("before trim : "+ temp);
		for(int i = 0;i<temp.length();i++){
			if(temp.charAt(i) == '0')
				k = i+1;
			else
				break;
		}
		temp = temp.substring(k);
		System.out.println("trimmed temp =" + temp);
		//System.exit(1);
		return temp;
		
	}
	
	public String convertBinary(int num){
		String binary = "";
		int index = 0;
			
		while(num > 0){
			 if((num%2) == 1)
				 binary = '1' + binary;
			 else{
				 binary = '0' + binary; 
			 }
			 num = num/2;
		}
		 
		while(binary.length() < 8){
			binary = '0' + binary;
		}
		     
		return binary;
	}
} 