package testprep1;

 class ProgrammerInterview  {
	 public void read() {
	  System.out.println("Programmer Interview!");
	 }
	}

public	class Website {
	/*  This creates an anonymous inner class: */
	ProgrammerInterview pInstance = new    ProgrammerInterview() {
	  public void read() {
	   System.out.println("anonymous ProgrammerInterview");
	  }
	 };
	 public static void main(String[] args) {
		 Website w = new Website();
		 w.pInstance.read(); 
	 }
	}
	
