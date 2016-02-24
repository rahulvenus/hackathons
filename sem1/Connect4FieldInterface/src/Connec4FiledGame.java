
public class Connec4FiledGame {
	
	public Connect4Field aConnect4Field = new Connect4Field();
	public Player aPlayer = new Player(aConnect4Field, "A", '+');
    public Player bPlayer = new Player(aConnect4Field, "B", '@');
	
	public void startGame(){
		
		aConnect4Field.init(aPlayer,bPlayer);
		System.out.println(aConnect4Field);
		aConnect4Field.playTheGame();
	}
	
	public static void main(String args[]){
		new Connec4FiledGame().startGame();
	}
}