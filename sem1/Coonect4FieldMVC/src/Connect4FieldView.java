import java.util.Scanner;

public class Connect4FieldView {

	public void getInputFromUser(Connect4FieldController connect4FieldController) {
		
		System.out.println("Enter your option :\n\t\t\t1.Single Player\n\t\t\t2.Two Player");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		
		while(true){
			if(num == 2){
				System.out.println("You selected Two player");
				System.out.println("Enter player1 name:");
				connect4FieldController.player1Name = in.nextLine();
				connect4FieldController.player1Name = in.nextLine();
				connect4FieldController.aPlayer.playerName = connect4FieldController.player1Name;
				System.out.println("Enter player2 name:");
				connect4FieldController.player2Name = in.nextLine();
				connect4FieldController.bPlayer.playerName = connect4FieldController.player2Name;
				break;
			}
			else if(num == 1){
				System.out.println("You selected Single player");
				System.out.println("Enter player1 name:");
				connect4FieldController.player1Name = in.nextLine();
				connect4FieldController.player1Name = in.nextLine();
				connect4FieldController.aPlayer.playerName = connect4FieldController.player1Name;
				connect4FieldController.player2Name = "Computer";
				connect4FieldController.bPlayer.playerName = connect4FieldController.player2Name;
				break;
			}
			else{
				System.out.println("Enter correct option:");
				
			}
		}
			
	}

	public void printTheBoard(Connect4Field aConnect4Field) {
		System.out.println(aConnect4Field);
	}

}
