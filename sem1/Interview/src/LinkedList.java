	class Node{
		int a;
		Node next;
		Node(int a){
			this.a = a;
			next = null;
		}
	}


public class LinkedList {
	
	public static void main(String[] args){
		
	 Node head = new Node(0);
	 Node temp = head;
	 for(int i=1;i<5;i++){
		 Node T = new Node(i);
		 temp.next = T;
		 temp = temp.next;
	 }
	 
	 temp = head;
	 while(temp != null){
		 System.out.print(temp.a + " --->");
		 temp = temp.next;
	 }
	 
	 //reverse

	 Node oldNode = null;
	 Node leftoutNode = null;
	 temp = head;
	 while(temp != null){
		 
		 leftoutNode = temp.next;
		 temp.next = oldNode;
		 oldNode = temp;
		 temp = leftoutNode;
	 }
	 
	 head = oldNode;
	 System.out.println();
	 temp = head;
	 while(temp != null){
		 System.out.print(temp.a + " --->");
		 temp = temp.next;
	 }
		
	}

}
