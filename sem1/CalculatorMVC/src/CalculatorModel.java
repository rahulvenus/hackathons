import java.util.Enumeration;
import java.util.Vector;

public class CalculatorModel {
	
	public double calcExpression(String calcExpression){
		
		double result = 0;
		// Below vector stores the expression
		Vector expression = new Vector(3);
		String aChar ="";
		String temp = "";
		MyStringStack tempStack = new MyStringStack();
		// Takes each character one by one and stores it into
		//the vector
		for (int index = 0; index < calcExpression.length();
			index++) {
			aChar = "";
			aChar += (calcExpression.charAt(index));
			
			if((aChar == "(") || (aChar == ")")){
				if(!(temp.isEmpty()))
					expression.addElement(temp);
				expression.addElement(aChar);
				temp = "";
		}			
		else if((Character.isDigit(aChar.charAt(0)))){
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
			//System.out.print("Elements in vector:");
			while(vEnum.hasMoreElements())
				//System.out.print(vEnum.nextElement() + " ");
			System.out.println();
			
			//Calc.postFixExpression.addAll(Calc.infixToPostFixConv(expression));
			 tempStack = infixToPostFixConv(expression);
			
			// The postFixExpression is evaluated and the result is the output		
			
			 result = evalPostFixExp(tempStack);
		
		
		return result;
		
	} 
	/**
       * This is the method which converts the infix expression to postfix.
    * 
       *
       * @param       infixExp  Any infix expression is passed to this function   
       * @return      Stack < String >   It returns the postfix expression as a stack
       */
      	
      	private  MyStringStack infixToPostFixConv(Vector infixExp){
      		String postFixExp = "";
      		String temp = new String();
      		String operInStack;
      		String tempOperator = "";
      		int ignorePrecedence = 0;
      		
      		MyStringStack operandStack = new MyStringStack();
      		MyStringStack operatorStack = new MyStringStack();
      		MyStringStack tempStack = new MyStringStack();
      		MyStringStack postFixStack = new MyStringStack();
      		
      		infixExp.toString();
      		Enumeration vEnum = infixExp.elements();
      		String token = "";
      	    while(vEnum.hasMoreElements())
      	    {
      	      token = vEnum.nextElement().toString();
      		  
      	      //Based on the brackets in the expression the precedence is 
      	      //changed here
      	      
      	      if((token.equals(")")) || (token.equals("("))){
      			//it is used to form a number having multiple digits eg. 92
      			if(token.equals("(")){
      				ignorePrecedence = ignorePrecedence + 1;
      				if(!(tempOperator.isEmpty())){
      			    	operatorStack.push(tempOperator);
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
      				operatorStack.push(token);	
      			}
      			else{
      				//if operator stack is not empty then
      				//it will compare with the previous operators	
      			 	//if ignorePresence is ON ,there is no use of checking priority
      				//Have to push the operator to the stack . That is done below
      				if(ignorePrecedence == 0)
      			 	{
      			 		while(!operatorStack.isEmpty()){	
      			 			operInStack = operatorStack.pop();
      			 			// change the below line to change the precedence
      			 			if(operatorPreced1(operInStack.charAt(0)) >
      			 				operatorPreced1(token.charAt(0))){
      			 				operandStack.push(operInStack);
      			 			}
      			 			else{
      			 				operatorStack.push(operInStack);
      			 				operatorStack.push(token);
      			 				break;
      			 			}	
      			 		}
      			 		if(operatorStack.isEmpty()){
      			 			operatorStack.push(token);
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
      			tempStack.push((operatorStack.pop()));
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
      	
      	private  float evalPostFixExp(MyStringStack postFixExpressionStack){
      		float result = 0;
      		float operand1;
      		float operand2;
      		String temp;
      		MyStringStack evalStack = new MyStringStack();
      		while(!(postFixExpressionStack.isEmpty())){
      			temp = postFixExpressionStack.pop();
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
      
      /** Below is the implementation of the stack */
      class MyStringStack
      {
      	//initial size 
      	private int size = 5;
      	//initial position
      	private int top = -1;
      	//variable holding the elements
      	private String elements[];
      	
      	//constructor
      	public MyStringStack(){
      		elements = new String[size];
      	}
      	
      	//Stack push method
      	public void push(String S)
      	{
      		//System.out.println("top = " + top + " size = " + size + " String " + S );
      		top++;
      		if(top < size)
      		{
      			elements[top] = S;
      		}
      		else 
      			allocMoreSpace(S);   //allocate more space if needed
      		//System.out.println("Push top = " + top + " S = " + S );
      	}
      	
      	//Stack pop method
      	public String pop()
      	{
      		String temp = new String();
      		if(top == -1)
      			return "";
      		else{
      			temp = elements[top];
      			top--;
      		}
      		return temp;
      	}
      	
      	//to check if the stack is empty
      	public boolean isEmpty()
      	{
      		if(top == -1)
      			return true;
      		else
      			return false;
      	}
      	
      	//to allocate more space to the stack to occupy the added elements
      	public void allocMoreSpace(String S)
      	{
      		int newSize = size + 5;
      		String[] tempElements = new String[newSize];
      		for(int i = 0; i < top ; i++){
      			tempElements[i] = elements[i];
      		}
      		tempElements[top] = S;
      		elements = tempElements;
      		size = newSize;
      		//System.out.println("Allocated more space , new size = " + size );
      		
      	}
      		
      	
      }
      