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

import java.util.Stack;

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

public class Calculator {
	
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
		
		//Vector v = new Vector(3, 2);
		
		// inputExp is the infix expression which is evaluated
		String inputExp = "3*(2*(2+(3*(2+1))))";
		//String inputExp = "3*(2+7)^2";
		//String inputExp = "(3+3)*(7-4)^2";
		Calculator Calc = new Calculator();

        // the converted infix expression is added to postFixExpression 
        // Stack
		System.out.println("Expression given to evaluate  = "+inputExp);
		Calc.postFixExpression.addAll(Calc.infixToPostFixConv(inputExp));
		
		// The postFixExpression is evaluated and the result is the output		
		
		int result = Calc.evalPostFixExp(Calc.postFixExpression);
		
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
	
	private  Stack<String>  infixToPostFixConv(String infixExp){
		String postFixExp = "";
		String temp = new String();
		Character operInStack;
		String tempOperator = "";
		int ignorePrecedence = 0;
		
		Stack<String> operandStack = new Stack<String>();
		Stack<Character> operatorStack = new Stack<Character>();
		Stack<String> tempStack = new Stack<String>();
		Stack<String> postFixStack = new Stack<String>();
		
        //Takes each character from the string and pushes 
		//it into either stack based on the precedence of the operator
	    for (int index = 0; index < infixExp.length();
				index++) {
			char aChar = infixExp.charAt(index);
			// check whether the character is a digit or not and then based on that it 
			// will form a number and push it to the stack
			
			if((aChar == ')') || (aChar == '(')){
				System.out.println("() Character ="+ aChar + " Operator =" + tempOperator);
				//it is used to form a number having multiple digits eg. 92
				if(aChar == '('){
					ignorePrecedence = ignorePrecedence + 1;
					System.out.println("() tempOperator ="+ tempOperator);
					if(!(tempOperator.isEmpty())){
			    		System.out.println(" tempOperator ="+ tempOperator);
			    		operatorStack.push(tempOperator.charAt(0));
			    		tempOperator = "";
			    	}
				}
				else{
					ignorePrecedence = ignorePrecedence - 1;
					if(!(temp.isEmpty())){
			    		System.out.println("() temp ="+ temp);
			    		operandStack.push(temp);
			    	}
					System.out.println("() tempOperator ="+ tempOperator);
					if(!(tempOperator.isEmpty())){
			    		System.out.println(" tempOperator ="+ tempOperator);
			    		operandStack.push(tempOperator);
			    		tempOperator = "";
			    	}
				/*	System.out.println("() temp ="+ temp + " tempOperator =" + tempOperator);
					operandStack.push(temp);
					operandStack.push(Character.toString(tempOperator)); */
					// clear
					temp = "";
				}
			}			
			else if((Character.isDigit(aChar))){
				System.out.println(" Character ="+ aChar);
				//it is used to form a number having multiple digits eg. 92
			   	temp = temp + aChar;
			}
		    else{
		    	System.out.println(" Operator ="+ aChar);
				// push the previous number to the operand stack
		    	if(!(temp.isEmpty())){
		    		System.out.println(" temp ="+ temp);
		    		operandStack.push(temp);
		    	}
				// clear
				temp = "";
				// if the operator stack is empty then it will push
				if(operatorStack.isEmpty() && (ignorePrecedence == 0)){
					System.out.println(" aChar ="+ aChar);
					operatorStack.push(aChar);	
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
			 					operatorPreced1(aChar)){
			 					System.out.println(" operInStack ="+ operInStack);
			 					operandStack.push(Character.toString(operInStack));
			 				}
			 				else{
			 					System.out.println(" operInStack ="+ operInStack+" aChar =" + aChar);
			 					operatorStack.push(operInStack);
			 					operatorStack.push(aChar);
			 					break;
			 				}	
			 			}
			 			if(operatorStack.isEmpty()){
			 				System.out.println(" aChar ="+ aChar);
			 				operatorStack.push(aChar);
			 			}
			 		}
			 		else
			 		{
			 			tempOperator = tempOperator+aChar;
			 			System.out.println(" tempOperator ="+ tempOperator);
			 			
			 		}
			 	}
		    }	
	    }
		
	    if(!(temp.isEmpty())){
    		System.out.println(" temp ="+ temp);
    		operandStack.push(temp);
    	}
		
/*
		while(!(operandStack.isEmpty()))
		{
			String tempchar = new String();
			tempchar = operandStack.pop();
			System.out.println(" #operandStack# "+ tempchar);
			
		}
		System.out.println(" ----------- ");
		
		while(!(operatorStack.isEmpty()))
		{
			char tempchar;
			tempchar = operatorStack.pop();
			System.out.println(" #operatorStack# "+ tempchar);
			
		}  */
		
		
		/*END OF TEST CODE */
		
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
		
		/*START OF TEST CODE */
/*
		System.out.println("postFixStack");
		while(!(postFixStack.isEmpty()))
		{
			//System.out.println("postFixStack is not empty");
			String tempchar = new String();
			tempchar = postFixStack.pop();
			System.out.println(" ### "+ tempchar);
		} */
		
		/*END OF TEST CODE */
		
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
	
	private  int evalPostFixExp(Stack<String> postFixExpressionStack){
		int result = 0;
		int operand1,operand2;
		String temp;
		Stack<String> evalStack = new Stack<String>();
		while(!(postFixExpressionStack.isEmpty())){
			temp = postFixExpressionStack.pop();
            		if(temp.matches("[-|+|*|/|%|^]")){
                // converting the string operand into integer            
				operand2 = Integer.parseInt(evalStack.pop());
				operand1 = Integer.parseInt(evalStack.pop());
				System.out.println(" operand2 "+ operand2);
				System.out.println("  operand1 "+ operand1 + ", OPERATOR = " + temp);
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
						//result = operand1 ^ operand2;
						break;
					}
				}
				System.out.println(" result "+ result);
				evalStack.push(Integer.toString(result));
			}
			else{
				evalStack.push(temp);
			}
		}

		System.out.println("Result ="+ result);
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
