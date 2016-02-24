/* 
 * Connec4FieldGame.java 
 * 
 * @version: $Id: Connec4FieldGame.java,v 1.00 2015/09/21 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/21 12:00:00 
 */

import java.util.Scanner;

public class Connec4FieldGame {
		
	public static void main(String args[]){
		Connect4FieldView connect4FieldView = new Connect4FieldView();
		Connect4FieldModel connect4FieldModel = new Connect4FieldModel();
		Connect4FieldController connect4FieldController = 
				new Connect4FieldController(connect4FieldView,connect4FieldModel);
		
		connect4FieldController.startGame();
	}
}