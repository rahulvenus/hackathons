/* 
 * Calculator.java 
 * 
 * @version: $Id: Calculator.java,v 1.7 2015/08/31 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00  2015/08/31 12:00:00
 */

import java.util.Enumeration;
import java.util.Stack;
import java.util.Vector;

/**
 * This program simulates a simple calculator that evaluates 
 * a given mathematical expression based on priority set.
 * @author Rahul Venugopala Pillai
 * @author Abhilash Vimal
   
*/ 


/** 
 * This class evaluates a given mathematical expression based on
 * the hardcoded order of precedence
 * @author  Rahul Venugopala Pillai
 * @author Abhilash Vimal
 */

public class Calculato {
	
	// postFixExpression used to store converted infix expression
	Stack<String> postFixExpression = new Stack<String>();

/**
 * This is the main method which gets an expression and evaluates it.
 * 
 *
 * @param       args    
 * @return      void   It does not return any value and simply returns
 */

	
	public static void main(String args[]){
		
		// inputExp is the infix expression which is evaluated
		//String inputExp = "3*(2*(2+(3*(2+1))))";
		String inputExp = "3*(2+7)^2";
		//String inputExp = "(3+3)*(7-4)^2";
		System.out.println("Your expression is: "+args[0]);
		int expLength = args[0].length();
		Vector expression = new Vector(3);
		char aChar = ' ';
		String temp = "";
	    for (int index = 0; index < expLength;
				index++) {
	    	aChar = (args[0].charAt(index));
	    	
	    	if((aChar == '(') || (aChar == ')')){
	    		if(!(temp.isEmpty()))
	    			expression.addElement(temp);
	    		expression.addElement(aChar);
	    		temp = "";
			}			
			else if((Character.isDigit(aChar))){
			   	temp = temp + aChar;
			}
		    else{
		    	if(!(temp.isEmpty()))
		    		expression.addElement(temp);
		    	temp = "";
	    		expression.addElement(aChar);
		    }
		}
    	if(!(temp.isEmpty()))
    		expression.addElement(temp);
    	temp = "";
	    
	    Enumeration vEnum = expression.elements();
	    System.out.print("Elements in vector:");
	    while(vEnum.hasMoreElements())
	    	System.out.print(vEnum.nextElement() + " ");
	    System.out.println();
	      
		Calculator Calc = new Calculator();

        // the converted infix expression is added to postFixExpression 
        // Stack
		//System.out.println("Expression given to evaluate  = "+inputExp);
		
		Calc.postFixExpression.addAll(Calc.infixToPostFixConv(expression));
		
		// The postFixExpression is evaluated and the result is the output		
		
		double result = Calc.evalPostFixExp(Calc.postFixExpression);
		System.out.println("Result ="+result);
		return;
	}

/**
 * This is the method which converts the infix expression to postfix.
 * 
 *
 * @param       infixExp  Any infix expression is passed to this function   
 * @return      Stack < String >   It returns the postfix expression as a stack
 */
	
	private  Stack<String>  infixToPostFixConv(Vector infixExp){
		String postFixExp = "";
		String temp = new String();
		Character operInStack;
		String tempOperator = "";
		int ignorePrecedence = 0;
		
		Stack<String> operandStack = new Stack<String>();
		Stack<Character> operatorStack = new Stack<Character>();
		Stack<String> tempStack = new Stack<String>();
		Stack<String> postFixStack = new Stack<String>();
		
		infixExp.toString();
		Enumeration vEnum = infixExp.elements();
		String token = "";
	    while(vEnum.hasMoreElements())
	    {
	      token = vEnum.nextElement().toString();
		  if((token.equals(")")) || (token.equals("("))){
			//it is used to form a number having multiple digits eg. 92
			if(token.equals("(")){
				ignorePrecedence = ignorePrecedence + 1;
				if(!(tempOperator.isEmpty())){
			    	operatorStack.push(tempOperator.charAt(0));
			    	tempOperator = "";
			    }
			}
			else{
				ignorePrecedence = ignorePrecedence - 1;
				if(!(tempOperator.isEmpty())){
			    	operandStack.push(tempOperator);
			    	tempOperator = "";
			    }
			}
		  }			
		  else if(token.matches("\\d+")){
			//it is used to form a number having multiple digits eg. 92
			operandStack.push(token);
		  }
		  else{
		  	// if the operator stack is empty then it will push
			if(operatorStack.isEmpty() && (ignorePrecedence == 0)){
				operatorStack.push(token.charAt(0));	
			}
			else{
				//if operator stack is not empty then
				//it will compare with the previous operators	
			 	if(ignorePrecedence == 0)
			 	{
			 		while(!operatorStack.isEmpty()){	
			 			operInStack = operatorStack.pop();
			 			// change the below line to change the precedence
			 			if(operatorPreced1(operInStack) >
			 				operatorPreced1(token.charAt(0))){
			 				operandStack.push(Character.toString(operInStack));
			 			}
			 			else{
			 				operatorStack.push(operInStack);
			 				operatorStack.push(token.charAt(0));
			 				break;
			 			}	
			 		}
			 		if(operatorStack.isEmpty()){
			 			operatorStack.push(token.charAt(0));
			 		}
			 	}
			 	else
			 	{
			 		tempOperator = token;
			 	}
			 }
		    }	
	    }
	    
		// to reverse operator stack 
		while(!(operatorStack.isEmpty()))
		{
			tempStack.push(Character.toString(operatorStack.pop()));
		}
		// put the operators in the new stack
		while(!(tempStack.isEmpty()))
		{
			postFixStack.push(tempStack.pop());
		}
		// put the operands to the new stack
		while(!(operandStack.isEmpty()))
		{
			postFixStack.push(operandStack.pop());
		}
		
		return postFixStack;
	}

/**
 * This is the method which evaluates the postfix expression obtained
 * 
 *
 * @param   postFixExpressStack   It accepts postfix expression stack and 
 *                                 evaluates it     
 * @return  int                   It returns the value of the evaluated
 *                                expression
 */
	
	private  float evalPostFixExp(Stack<String> postFixExpressionStack){
		float result = 0;
		float operand1;
		float operand2;
		String temp;
		Stack<String> evalStack = new Stack<String>();
		while(!(postFixExpressionStack.isEmpty())){
			temp = postFixExpressionStack.pop();
			System.out.println("  temp " + temp);
            if(temp.matches("[-|+|*|/|%|^]")){
                // converting the string operand into integer            
				operand2 = Float.parseFloat(evalStack.pop());
				operand1 = Float.parseFloat(evalStack.pop());
				switch (temp) {
					case "-":
						result = operand1 - operand2;
						break;
					case "+":
						result = operand1 + operand2;
						break;
					case "*":
						result = operand1 * operand2;
						break;
					case "/":
						result = operand1 / operand2;
						break;
					case "%":
						result = operand1 % operand2;
						break;
					case "^":{
						result = 1;
						for(int i= 0 ;i < operand2; i++){
							result = result * operand1;
						}
						break;
					}
				}
				evalStack.push(Double.toString(result));
			}
			else{
				System.out.println(" pushing evalStack "+ temp);
				evalStack.push(temp);
			}
		}

		//System.out.println("Result ="+ new BigDecimal(result).toPlainString());
		return result;
	}

/**
 * This is the method which returns operator precedence according to rule 1
 * 
 *
 * @param       operator   Accepts any operator 
 * @return      int        It returns the precedence of the operator
 */

	private  int operatorPreced1(Character operator) {
		int precedence = -1;
		switch (operator) {
			case '+':
				{
					precedence = 1;
					break;
				}
			case '-':
				{
					precedence = 2;
					break;
				}
			case '%':
				{
					precedence = 3;
					break;
				}
			case '*':
				{
					precedence = 4;
					break;
				}
			case '/':
				{
					precedence = 5;
					break;
				}
			case '^':
			{
				precedence = 6;
				break;
			}
			default:
				{
					precedence = -1;
					break;
				}
		}
		return precedence;
	}


/**
 * This is the method which returns operator precedence according to rule 2
 * 
 *
 * @param       operator   Accepts any operator 
 * @return      int        It returns the precedence of the operator
 */
	private  int operatorPreced2(Character operator) {
		int precedence = -1;
		switch (operator) {
			case '+':
				{
					precedence = 4;
					break;
				}
			case '-':
				{
					precedence = 5;
					break;
				}
			case '%':
				{
					precedence = 3;
					break;
				}
			case '*':
				{
					precedence = 2;
					break;
				}
			case '/':
				{
					precedence = 1;
					break;
				}
			case '^':
			{
				precedence = 6;
				break;
			}
			default:
				{
					precedence = -1;
					break;
				}
		}
		return precedence;
	}
}


