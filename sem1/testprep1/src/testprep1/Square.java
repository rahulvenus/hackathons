package testprep1;


public class Square extends Area {

  private int length;	

  public Square(int _length)	{
	type = "Square";
	length = _length;
  }

  public int area()	{
	return length * length;
  }

  public int perimeter()	{
	return 4 * length;
  }

}