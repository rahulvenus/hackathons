import java.util.Scanner;

public class CalculatorController {
	
	CalculatorModel calcModel;
	CalculatorView calcView;
	String calcExpression;
	double calcExpressionValue;
	
	public CalculatorController(CalculatorModel model, CalculatorView view){
		calcModel = model;
		calcView = view;
	}
	
	public void startCalc(){
		
		calcExpression = calcView.getExpressionFromUser();
		
		calcExpressionValue = calcModel.calcExpression(calcExpression);
		
		calcView.display(calcExpressionValue);
	
	}
	
}
