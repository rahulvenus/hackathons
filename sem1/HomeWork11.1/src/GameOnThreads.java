/* 
 * GameOnThreads.java 
 * 
 * @version: $Id: GameOnThreads.java,v 1.70 2015/09/08 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/08 12:00:00 
 */


/* The main class which enables to test the game 
 * 
 */
public class GameOnThreads {
	

public static void main(String[] args) {
	GameOnThreadsModel gameModel = new GameOnThreadsModel();
	GameOnThreadsView gameView = new GameOnThreadsView();
	GameOnThreadsController gameController = new GameOnThreadsController(gameModel, gameView);

	gameController.startGame();
}
}
