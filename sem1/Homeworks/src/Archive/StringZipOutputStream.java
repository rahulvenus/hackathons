/* 
 * StringZipOutput.java 
 * 
 * @version: $Id: StringZipOutput.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;
	
 
public class StringZipOutputStream {

	String inputFileName 	= "words.txt";
    HashMap charFreqMap;
    static char[] charList;
    static int mapSize;
    static String[] charCode;
    OutputStream outstream;
    BufferedOutputStream bufferedOut;
    static boolean trimZeroBits;
    
    public void pasrseTheFile(){
    	charFreqMap = new  HashMap();
    	try{
    		FileReader file = new FileReader(inputFileName);
	    	
	    	if(!file.ready()){
	    		System.out.println("File doesnot exist!!!!");
	    	}
	    	else{
	    		 int c;
	    		 char key;
	    		 while ((c = file.read()) != -1) {
	            	 //System.out.println(((char)c)+ " @@@");
	            	 key = (char)c;
	            	 Object value =charFreqMap.get(key);
	            	 if (value == null) {
	            		 charFreqMap.put(key, 1);
	            	 } else {
	            		 charFreqMap.put(key, ((int)value+1));
	            	 }
	    		 }
	    	}
	    	
	    	/*** TEST ***
	    	  // Get a set of the entries
	        Set set = charFreqMap.entrySet();
	        // Get an iterator
	        java.util.Iterator i = set.iterator();
	        // Display elements
	        while(i.hasNext()) {
	           Map.Entry me = (Map.Entry)i.next();
	           System.out.print(((char)me.getKey() == '\n')?"newline :":me.getKey() + ": ");
	           System.out.println(me.getValue());
	        }
	        /*****/
    	}
    	catch(FileNotFoundException F){
    		System.out.println("File not found !!");
    	} 
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    /* Sort the map based on the frequency and store it in a char array*/
    	
    	List mapKeys = new ArrayList(charFreqMap.keySet());
    	List mapValues = new ArrayList(charFreqMap.values());
    	Collections.sort(mapValues);
    	
    	java.util.Iterator valueIterator = mapValues.iterator();
    	mapSize = charFreqMap.size();
    	charList = new char[mapSize];
    	
    	
    	Object curValue;
    	int indexToStore = 0;
		//System.out.println("SORTED VALUES");
    	while(valueIterator.hasNext()){
    		
    		curValue = valueIterator.next();
    		//System.out.println(curValue);
    		java.util.Iterator keyIterator = mapKeys.iterator();
    		while(keyIterator.hasNext()){
    			Object tempKey = keyIterator.next();
    			Object tempVal = charFreqMap.get(tempKey);
    			if(curValue.equals(tempVal)){
    				 //System.out.println(tempKey+ " "+ tempVal);
    				 charList[indexToStore] = (char)tempKey;
    				 mapKeys.remove(tempKey);
    				 indexToStore++;
    				 break;
    			}
    		}
    	}
    	

    	
    	/*** TEST  print the sorted map***/
    	//System.out.println("Print the sorted map");
    	int index = 0;
    	while (index < mapSize){
    		System.out.println(((charList[index] == '\n')?"newline":charList[index] )+
    				" "+ charFreqMap.get((char)charList[index]));
    		index++;
    	}
      /*****/
    	
    	System.out.println("GETTING THE HUFFMAN CODE FOR EACH LETTER");
    	
    	getHuffmanCode();
    	

    }
    
    
    void getHuffmanCode(){
    	
    	/** Create a tree using the character list and there frequency **/
    	System.out.println("No of different Characters " + mapSize);
    	
    	/** Creating the no of nodes based on the mapsize **/
    	int totalNodes = 0;
    	Node nodeArray[] = new Node[mapSize*2];
    	Node HEAD = null;
    	
    	while(totalNodes < mapSize){
    		int tempVal = (int) charFreqMap.get(charList[totalNodes]);
    		String tempStr = ""+charList[totalNodes];
    		nodeArray[totalNodes] = new Node(tempVal,tempStr);
    		totalNodes++;
    	}
    	
    	int k=0;
    	while(k < totalNodes){
    		//System.out.println("STR : "+ nodeArray[k].str + "Freq: "+ nodeArray[k].freq);
    		k++;
    	}
    	
    	/** Created nodes now going to form the tree **/
    	
    	
    	int index = 0;
    	do{
    		//System.out.println("Index = "+index+" Total:"+ totalNodes);
    		Node firstSmallest = nodeArray[index];
    		Node secondSmallest = nodeArray[index+1];
    		
    		firstSmallest.binVal = '0';
    		secondSmallest.binVal = '1';
    		
    		Node newNode = new Node(0,"");
    		
    		newNode.str = firstSmallest.str + secondSmallest.str ;
    		newNode.freq = firstSmallest.freq + secondSmallest.freq ;
    		newNode.rightNode = secondSmallest;
    		newNode.leftNode = firstSmallest;
    				
    		//System.out.println("newNode.str : "+ newNode.str);
    		int i=0;
    		/*Insert new node into the list of nodes in a sorted way. */
    		System.out.println("newNode.freq"+newNode.freq + " newNode.str="+newNode.str);
    		for( i = index +2; i < totalNodes ;i++){
    			//System.out.println("i ="+i +"total node="+totalNodes);
    			if(nodeArray[i].freq < newNode.freq){
    				//do nothing
    			}
    			else{
    				for(int j=totalNodes;j > i ;j--){
    					nodeArray[j] = nodeArray[j-1];
    				}
    				nodeArray[i] = newNode;
    				//System.out.println("i="+i);
    				break;		
    			}
    		}
    		if(i==totalNodes)
    			nodeArray[i] = newNode;
    		totalNodes++;
    		index = index + 2;
    		
    	}while(index+1 < totalNodes);
    	
    	for(int i = 0; i < totalNodes ;i++){
    	
    		//System.out.println(nodeArray[i].str+" >>>>"+nodeArray[i].freq);
    	}
    	HEAD = nodeArray[totalNodes-1];
    	HEAD.binVal =  '1';
    	/**End of Tree**/
    	
    	/*** Trying to get the huff code **/
    	charCode = new String[mapSize];
    	String temp = "";
    	Node tempNode = HEAD;
    	for(int i = 0; i < mapSize ;i++){
    		//System.out.println("char to find code =" + charList[i]);
   			temp ="";
   			tempNode = HEAD;
    		while(tempNode!= null){
    			//System.out.println("tempNode ="+tempNode.str);
    			if(tempNode.str.equals(Character.toString(charList[i]))){
    				temp = temp + tempNode.binVal;
    				charCode[i] = temp;	
    				//System.out.println("String rep of "+ charList[i] + " is " + charCode[i] );
    				break;
    			}
    			else{
    				temp = temp + tempNode.binVal;
    				//System.out.println("tempNode.rightNode ="+tempNode.rightNode.str);
    				//System.out.println("tempNode.lefttNode ="+tempNode.leftNode.str);
    				if(tempNode.leftNode.str.contains(Character.toString(charList[i]))){
        				tempNode = tempNode.leftNode;
        			}
    				else{
    					tempNode = tempNode.rightNode;
    				}
    				//System.out.println(temp);
    			}
    		    		
    		}
    	}
    	
    	for(int i = 0; i < mapSize ;i++){
    		System.out.println(((charList[i]=='\n')?"newLine":charList[i]) + " >>>>" + charCode[i]);
    	}
    }
    	
    void writeBufferToFile(String Buffer){
    	
    	int temp;
    	System.out.println("Buffer :"+Buffer);
    	temp = Integer.parseInt(Buffer, 2);
    	
    	System.out.println(" \t\t\t\tInteger written in file"+temp);
    	
    	char myByte = (char)(temp & 0xFF);
    	System.out.println("myByte"+myByte);  	
    	
    	
    	System.out.println("Going to write buffer :"+myByte);
    	//System.exit(1);
    	try {
			outstream.write(myByte);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

	// Creates a new output stream with a default buffer size.
	public StringZipOutputStream(OutputStream out)	{
		pasrseTheFile();
		outstream = out;
		//this.bufferedOut = new BufferedOutputStream(out);
		
		
	}
	
	// Writes aStrign compressed output stream. This method will block until all data is written.
	public void write(String aString) {
		
		System.out.println(aString);
		String tempCode;
		String buffer = "";
		for(int i=0;i<aString.length();i++){
			int j = 0;
			
			for(;j<mapSize;j++){
				if(charList[j] == aString.charAt(i)){
					break;
				}
			}
			
			if(j == aString.length()){
				System.out.println("Character not found!!");
			}
			else{
				System.out.println("buffer = " + buffer + " Character current "+ charList[j]);
				tempCode = charCode[j];
				System.out.println("Character code "+tempCode);
				buffer = buffer+tempCode;
				
				//if buffer length exceeds 8 charcaters write into file as a character
				while(buffer.length() >=8){
					System.out.println("buffer exceeded 7 = " + buffer);
					String tempbuffer=buffer.substring(0,8);
					System.out.println("tempBuffer = " + tempbuffer);
					buffer = buffer.substring(8);
					System.out.println("buffer =" + buffer);				
					writeBufferToFile(tempbuffer);
				}
			}
			
		}
		trimZeroBits = false;
		int lengthOfRemainingBuffer = buffer.length() ;
		if(lengthOfRemainingBuffer > 0){
			System.out.println("buffer = " + buffer + " length ="+lengthOfRemainingBuffer);
			String remainingBuffer = buffer;
			while(remainingBuffer.length() < 8){
				remainingBuffer = '0'+ remainingBuffer;
				System.out.println("Appending 1 zero");
			}
			System.out.println("remainingBuffer = " +remainingBuffer);
			writeBufferToFile(remainingBuffer);
			trimZeroBits = true;
		}
		//System.exit(1);
	}
	// Writes remaining data to the output stream and closes the underlying stream.
	public void close() {
		try {
			outstream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
