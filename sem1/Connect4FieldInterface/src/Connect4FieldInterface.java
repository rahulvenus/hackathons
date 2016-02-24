/* 
 * Connect4FieldInterface.java 
 * 
 * @version: $Id: Connect4FieldInterface.java,v 1.00 2015/09/21 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/21 12:00:00 
 */

public interface Connect4FieldInterface {
	public boolean checkIfPiecedCanBeDroppedIn(int column);
	public void dropPieces(int column, char gamePiece);
	boolean didLastMoveWin();
	public boolean isItaDraw();
	public void init( PlayerInterface playerA, PlayerInterface playerB );
	public String toString();
	public void playTheGame();
}