package ds.linkedlist;

public class ReverseLinkedList {

	static class Node {
		public int value;
		public Node next;
		
		public Node(int v) {
			value = v;
			next = null;
		}
		
		public Node append(Node n, int v) {
			if (null == n.next) {
				Node node = new Node(v);
				n.next = node;
			} else append(n.next, v);
			
			return this;
		}
		
		public void print() {
			StringBuilder str = new StringBuilder();
			str.append(value);
			Node iter = next;
			while (iter != null) {
				str.append("->").append(iter.value);
				iter = iter.next;
			}
			
			System.out.println(str.toString());
		}
	}
	
	public static Node reverse(Node head) {
		if (null == head) return head;
		Node next = head.next;
		Node prev = null;
		while (next != null) {
			head.next = prev;
			prev = head;
			head = next;
			next = head.next;
		}
		
		head.next = prev;
		return head;
	}
	
	public static void main(String[] args) {
		ReverseLinkedList.Node head = new ReverseLinkedList.Node(3);
		head.append(head, 5).append(head, 7).append(head, 8);
		head.print();
		
		head = reverse(head);
		head.print();

	}

}
