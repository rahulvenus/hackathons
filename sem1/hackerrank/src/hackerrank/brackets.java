package hackerrank;

import java.util.Scanner;
import java.util.Stack;

public class brackets {
	
	public static boolean CheckParentesis(String str)
	{
	    if (str.isEmpty())
	        return true;

	    Stack<Character> stack = new Stack<Character>();
	    for (int i = 0; i < str.length(); i++)
	    {
	        char current = str.charAt(i);
	        if (current == '{' || current == '(' || current == '[')
	        {
	            stack.push(current);
	        }


	        if (current == '}' || current == ')' || current == ']')
	        {
	            if (stack.isEmpty())
	                return false;

	            char last = stack.peek();
	            if (current == '}' && last == '{' || current == ')' && last == '(' || current == ']' && last == '[')
	                stack.pop();
	            else 
	                return false;
	        }

	    }

	    return stack.isEmpty();
	}
	
	
	public static String[] Braces(String[] values)
	{
		String[] result = new String[values.length];
		String str;
		for (int k = 0; k < values.length; k++) {
			str = values[k];
			result[k] = "YES";
			Stack<Character> stack = new Stack<Character>();
			for (int i = 0; i < str.length(); i++)
			{
			    char current = str.charAt(i);
			    if (current == '{' || current == '(' || current == '[')
			    {
			        stack.push(current);
			    }
			    else if (current == '}' || current == ')' || current == ']')
			    {
			        if (stack.isEmpty()){
			        	result[k] = "NO";
			        	break;
			        }
			        else{
			            char last = stack.peek();
			            if (current == '}' && last == '{' || current == ')' && last == '(' || current == ']' && last == '[')
			                stack.pop();
			            else{
			            	result[k] = "NO";
			            	break;
			            }
			        }
			    }
			}
			if(!stack.empty())
				result[k] = "NO";
		}
		return result;
	}
	
	public static void main(String[] args) {
	/*	Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		int num = Integer.parseInt(s);
		for (int i = 0; i < num ; i++) {
			s = in.nextLine();
			System.out.println(CheckParentesis(s));
		}	
	*/
		String input[] = {"((((((((([[[<>]]])))))))))(" ,"[[[[]]()]]","((({<()>}[])))","(())[<[>]])"};
		String result[] = Braces(input);
		
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

}
