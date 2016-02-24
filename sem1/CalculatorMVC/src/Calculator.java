/* 
 * Calculator.java 
 * 
 * @version: $Id: Calculator.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

import java.util.Enumeration;
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

public class Calculator {
	
/**
 * This is the main method which gets an expression and evaluates it.
 * 
 *
 * @param       args    
 * @return      void   It does not return any value and simply returns
 */

	
	public static void main(String args[]){
		
		CalculatorModel calcModel = new CalculatorModel();
		CalculatorView calcView = new CalculatorView();
		CalculatorController calcController = new CalculatorController(calcModel,calcView);
		
		calcController.startCalc();
	}

}



