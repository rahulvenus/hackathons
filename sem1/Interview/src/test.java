
public class test {
	
	class Node {
		Node next = null;
		int data;
		public Node(int d) { 
			data = d;
		} 
		void appendToTail(int d) {
			Node end = new Node(d);
			Node n = this;
			while (n.next != null) {
				n = n.next; 
			} 
			n.next = end;
		} 
	}
	
	public static String reverse(String input){
	    char[] in = input.toCharArray();
	    int begin=0;
	    int end=in.length-1;
	    while(end>begin){
	    /*    temp = in[begin];
	        in[begin]=in[end];
	        in[end] = temp;
	        end--;
	        begin++; */
	    	in[begin] =(char) (in[begin] + in[end]);
	    	in[end] =(char) (in[begin] - in[end]);
	    	in[begin] =(char) (in[begin] - in[end]);
	    
	        System.out.println("begin ="+in[begin] + " end = " + in[end]);
	        end--;
	        begin++;
	    }
	    
	    
	    String temp = "Rahul";
	    int i =0;
	    while(i <= 5/2)
	    {
	    
	    
	    }
	    
	    return new String(in);
	    
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Reverse of rahul us " + reverse("Rahul"));
		

	}

}
