/* 
 * MyCompressTest.java 
 * 
 * @version: $Id: StringZipOutput.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
// import java.util.zip.StringZipInputStream;
// import java.util.zip.StringZipOutputStream;

public class MyCompressTest	{
    final  int MAX = 1024;
    String inputFileName 	= "words.txt";
    String outputFileName 	= "words.compress";
    String uncompressed 	= "words.uncompress";
    
    void compress()	{	
	try {
		String aWord;

		BufferedReader input = new BufferedReader(new FileReader(inputFileName));
		StringZipOutputStream aStringZipOutputStream = new StringZipOutputStream( new FileOutputStream(outputFileName));
		
		/** TEST **/
		//System.out.println("Going to break!!");
		//System.exit(1);
		/** TEST **/
		
		while (  ( aWord = input.readLine() )  != null ) {
				System.out.println("write:	" + aWord);
				aStringZipOutputStream.write(aWord);
		}
		aStringZipOutputStream.close();
		input.close();
		
	} catch ( Exception e )	{
		e.printStackTrace();
		System.exit(1);
	}
    }
    void unCompress()	{	
	try {
		String aWord;
		byte[] buffer = new byte[MAX];

		BufferedWriter uncompress = new BufferedWriter(new FileWriter(uncompressed));
		StringZipInputStream aStringZipInputStream = new StringZipInputStream( new FileInputStream(outputFileName));
		String theWord;

		while (  ( theWord = aStringZipInputStream.read() ) != null ) {
			uncompress.write(theWord, 0, theWord.length());
			//break;
		}
		aStringZipInputStream.close();
		uncompress.close();
		
	} catch ( Exception e )	{
		e.printStackTrace();
		System.exit(1);
	}
    }
    public static void main( String args[] ) {
	MyCompressTest aMyCompressTest = new MyCompressTest();
	aMyCompressTest.compress();
	aMyCompressTest.unCompress();
    }
}