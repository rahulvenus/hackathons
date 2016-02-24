import java.util.Scanner;

public class Connect4FieldController {
	public String player1Name = new String();
	public String player2Name = new String();
	public Connect4Field aConnect4Field = new Connect4Field();
	public Player aPlayer = new Player(aConnect4Field, "A", '+');
    public Player bPlayer = new Player(aConnect4Field, "B", '@');
    Connect4FieldView connect4FieldView;
	Connect4FieldModel connect4FieldModel;
	
    public Connect4FieldController(Connect4FieldView view,Connect4FieldModel model ){
    	
    	connect4FieldView = view;
    	connect4FieldModel = model;
    	
    }
    
	public void startGame(){
		
		connect4FieldView.getInputFromUser(this);			
		aConnect4Field.init(aPlayer,bPlayer);
		connect4FieldView.printTheBoard(aConnect4Field);
		connect4FieldModel.startTheGame(aConnect4Field);
	}

}
