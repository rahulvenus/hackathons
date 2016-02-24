/* 
 * PlayerInterface.java 
 * 
 * @version: $Id: PlayerInterface.java,v 1.00 2015/09/21 12:00:00 
 * 
 * @author Rahul Venugopala Pillai and Abhilash Vimal
 *
 * Revisions:
 *
 *      Revision 1.00 2015/09/21 12:00:00 
 */

public interface PlayerInterface {

     // this is how your constructor has to be
     // Player(Connect4FieldInterface theField, String name, char gamePiece)

             public char getGamePiece();
             public String getName();
             public int  nextMove();
     }


