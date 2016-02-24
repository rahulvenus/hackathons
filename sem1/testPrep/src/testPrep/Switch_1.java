package testPrep;

class Switch_1 {
	  private void itWasA(char c) {
		switch( c )	{
		case 'a':	return("A");	// break??
		case 'b':	return("B");	// break??
		case 'c':	return("C");	// break??
		case 100:	return("D");	// break??
		case 101:	return("E");	// break??
		default:	return("no clue, but not an [a-e]");
						// What happens if
						// we delete this line?
		}	
	  }
	  
	  private String itWasB(String c) {
			switch( c )	{
			case "a":	return("A");	// break??
			case "b":	return("B");	// break??
			case "c":	return("C");	// break??
			case "100":	return("D");	// break??
			case "101":	return("E");	// break??
			default:	return("no clue, but not an [a-e]");
							// What happens if
							// we delete this line?
			}	
		  }


	  public static void main( String args[] ) {
	      char theChar;

	      theChar = 'd';
	      System.out.println("You typed in an '" + 
			new Switch_1().itWasA(theChar) + "'");
	      
	      String s;

	      s = "100";
	      System.out.println("You typed in an '" + 
			new Switch_1().itWasB(s) + "'");

	      System.exit(0);	// beware of ...
	  }
	}