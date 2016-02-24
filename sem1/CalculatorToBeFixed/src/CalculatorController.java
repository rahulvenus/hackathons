/* 
 * CalculatorController.java
 * 
 * @version: $Id: CalculatorController.java,v 1.70 2015/10/05 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */

import java.util.Scanner;

/* The below mentioned is the controller class which basically
 * links the model and the view or is responsible for interaction
 * between model and view classes
 */

public class CalculatorController {

	CalculatorModel calcModel;
	CalculatorView calcView;
	String calcExpression;
	double calcExpressionValue;

	public CalculatorController(CalculatorModel model, CalculatorView view) {
		calcModel = model;
		calcView = view;
	}

	public void startCalc() {

		calcExpression = calcView.getExpressionFromUser();

		calcExpressionValue = calcModel.calcExpression(calcExpression);

		calcView.display(calcExpressionValue);

	}

}