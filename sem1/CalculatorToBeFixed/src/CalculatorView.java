/* 
 * CalculatorView.java 
 * 
 * @version: $Id: CalculatorView.java,v 1.70 2015/10/05 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */


import java.util.Scanner;

/*The view renders the contents of a model. It specifies exactly 
 * how the model data should be presented. 
 * If the model data changes, the view must update its presentation as needed.
 */

public class CalculatorView {

	public String getExpressionFromUser() {

		Scanner myscanner = new Scanner(System. in );
		System.out.println("Enter Your expression : ");

		String calcExpression = myscanner.nextLine();
		System.out.println("Entered expression is " + calcExpression);

		return calcExpression;

	}

	public void display(double calcExpressionValue) {

	System.out.println("\n\nThe value of the expression is " + calcExpressionValue);
	}
}