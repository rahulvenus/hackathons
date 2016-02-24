/**
 *
 *
 * @version   $Id: OsName.java,v 1.0 2015/08/31 12:00:00  rvp$
 *
 *
 * Revisions:
 *
 *      Revision 1.00  2015/08/31 12:00:00  rvp
 *      Initial revision
 *
 */

/**
 * This program displays the name of the OS.
 *
 * @author      Rahul Venugopala Pillai
 */

class OsName {
	
	/**
	   * The main program.
	   *
	   * @param    args    command line arguments (ignored)
	   */
	
    public static void main (String args []) { 
        //Get the system Property : Name of the OS and will print it
    	System.out.println("OS: " + System.getProperty("os.name"));
    }
}
