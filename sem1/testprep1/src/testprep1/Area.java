package testprep1;

/**
 * Abstract class
 * @version   $Id$
 *
 * @author    hp bischof
 *
 * Revisions:
 *	$Log$
 */

abstract class Area extends Object {

  String type;

  public String getType()	{
	return type;
  }
  
  public abstract int area();
  public abstract int perimeter();
}