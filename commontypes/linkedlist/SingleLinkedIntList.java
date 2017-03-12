package commontypes.linkedlist;

import java.util.Stack;

public abstract class SingleLinkedIntList {

	public static SingleLinkNode<Integer> partition(SingleLinkedList<Integer> list, int partitioner) {
		SingleLinkNode<Integer> less = null, greater = null, l = null, g = null;
		SingleLinkNode<Integer> head = list.head;
		while (null != head) {
			SingleLinkNode<Integer> next = head.next;
			head.next = null;
			if (head.val < partitioner) {
				if (less == null) {
					less = head;
					l = less;
				} else {
					l.next = head;
					l = l.next;
				}
			} else {
				if (greater == null) {
					greater = head;
					g = greater;
				} else {
					g.next = head;
					g = g.next;
				}
			}
			head = next;
		}
		
		if (null != l) l.next = greater;
		else less = greater;
		
		return less;
	}
	
	public static boolean isPalindrome(SingleLinkedList<Integer> list) {
		SingleLinkNode<Integer> head = list.head;
		SingleLinkNode<Integer> fast = head, slow = head;
		Stack<Integer> stack = new Stack<>();
		while (null != fast.next && null != fast.next.next) {
			stack.push(slow.val);
			slow = slow.next;
			fast = fast.next.next;
		}
		if (null != fast.next) stack.push(slow.val);
		while (slow.next != null && !stack.isEmpty()) {
			Integer i = stack.pop();
			if (slow.next.val != i) return false;
			slow = slow.next;
		}
		if (slow.next != null || !stack.isEmpty()) return false;
		return true;
	}
	
	public static SingleLinkedList<Integer> createLinkedList(int length, int max) {
		SingleLinkNode<Integer> head = null, next = null;
		while (length-- > 0) {
			int val = (int)Math.floor(Math.random()*max);
			if (null == head) {
				head = new SingleLinkNode<Integer>(val);
				next = head;
			} else {
				next.next = new SingleLinkNode<Integer>(val);
				next = next.next;
			}
		}
		return new SingleLinkedList<Integer>(head);
	}
	
	public static SingleLinkedList<Integer> createIntersectingList(SingleLinkedList<Integer> list, int atNode) {
		SingleLinkNode<Integer> head = list.head;
		int counter = atNode;
		while (counter-- > 0 && null != head.next) head = head.next;
		SingleLinkedList<Integer> result = createLinkedList(atNode, 10);
		SingleLinkNode<Integer> next = result.head;
		while(null != next.next) next = next.next;
		next.next = head;
		return result;
	}
	
	public static SingleLinkedList<Integer> createCircularList(int length, int max) {
		SingleLinkedList<Integer> list = createLinkedList(length, max);
		SingleLinkNode<Integer> last = list.head;
		while (last.next != null) last = last.next;
		last.next = list.getNode(length/2);
		return list;
	}
}
