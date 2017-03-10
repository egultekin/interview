package cci;

public class LinkedListNode {
	int val;
	LinkedListNode next;
	
	public LinkedListNode(int v) {
		val = v;
		next = null;
	}
	
	public void print() {
		StringBuilder builder = new StringBuilder();
		builder.append(val);
		LinkedListNode n = next;
		while (null != n) {
			builder.append("-");
			builder.append(n.val);
			n = n.next;
		}
		System.out.println(builder.toString());
	}
	
	public void print(int cNodes) {
		if (cNodes < 1) return;
		StringBuilder builder = new StringBuilder();
		builder.append(val);
		cNodes--;
		LinkedListNode next = this.next;
		while (cNodes-- > 0 && next != null) {
			builder.append("-").append(next.val);
			next = next.next;
		}
		System.out.println(builder.toString());
	}
	
	public static LinkedListNode createLinkedList(int length, int max) {
		LinkedListNode head = null, next = null;
		while (length-- > 0) {
			int val = (int)Math.floor(Math.random()*max);
			if (null == head) {
				head = new LinkedListNode(val);
				next = head;
			} else {
				next.next = new LinkedListNode(val);
				next = next.next;
			}
		}
		return head;
	}
	
	public LinkedListNode createIntersectingList(int atNode) {
		LinkedListNode head = this;
		int counter = atNode;
		while (counter-- > 0 && null != head.next) head = head.next;
		LinkedListNode result = createLinkedList(atNode, 10);
		LinkedListNode next = result;
		while(null != next.next) next = next.next;
		next.next = head;
		return result;
	}
	
	public static LinkedListNode createCircularList(int length, int max) {
		LinkedListNode head = createLinkedList(length, max);
		LinkedListNode last = head;
		while (last.next != null) last = last.next;
		last.next = head.getNode(length/2);
		return head;
	}
	
	public LinkedListNode getRandomNode(int length) {
		int index = (int) Math.floor(Math.random()*length);
		LinkedListNode node = this;
		while (index-- > 0) node = node.next;
		return node;
	}
	
	public LinkedListNode getNode(int at) {
		LinkedListNode node = this;
		while (at-- > 0 && node != null) node = node.next;
		return node;
	}
}
