package testprep1;

public class Circle extends Area {
	  private int radius;	
	  public Circle(int _radius)	{
		type = "Circle";
		radius = _radius;
	  }

	  public int area()	{
		return (int)(Math.PI * radius * radius);
	  }
	 //  /*
	  public int perimeter()	{
		return (int)(Math.PI * radius * radius);
	  }
	  // */
	}