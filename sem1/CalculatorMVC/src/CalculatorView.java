import java.util.Scanner;

public class CalculatorView {
	
	public String getExpressionFromUser(){
		
		Scanner myscanner = new Scanner(System.in);
		System.out.println("Enter Your expression : ");
		
		String calcExpression = myscanner.nextLine();
		System.out.println("Entered expression is "+calcExpression);
		
		return calcExpression;
		
	}

	public void display(double calcExpressionValue){
		
		System.out.println("\n\nThe value of the expression is " + calcExpressionValue);
	}
}
