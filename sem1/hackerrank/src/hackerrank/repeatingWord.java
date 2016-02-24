package hackerrank;

import java.util.HashMap;

public class repeatingWord {
	
	static String[] firstRepeatWord(String[] input){
		String[] result = new String[input.length];
		
		for (int i = 0; i < input.length; i++) {
			
			String s = input[i];
			s = s.replace('\t',' ');
			s = s.replace(';',' ');
			s = s.replace(':',' ');
			s = s.replace('-',' ');
			s = s.replace('.',' ');
			s = s.replace(',',' ');
			
			//s = s.replaceAll("\\s+"," ").trim();
			s = s.replaceAll(" ","#");
			System.out.println(s);
			//String[] arr = s.split(" ");   
			String[] arr = s.split("#");   
			HashMap hash = new HashMap();
			HashMap<String, Boolean> hashMap= new HashMap<String, Boolean>();
			String repeatedString = "";
			for ( String ss : arr) {
				System.out.println("word="+ss);
				if(hash.containsKey(ss)){
					repeatedString = ss;
					//System.out.println("breaking"+repeatedString);
					break;
				}
				hash.put(ss, true);
					
			}
			result[i] = repeatedString;
		}
		
		return result;
		
	}
	
	public static void main(String[] args) {
		
		String input[] = {"the a","",";:ab\tab;:-.,"," ab ac ac","  ab  ab"};
		String result[] = firstRepeatWord(input);
		
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

}
