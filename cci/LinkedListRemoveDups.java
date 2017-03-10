package cci;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LinkedListRemoveDups {

	public LinkedListNode removeDuplicates(LinkedListNode head) {
		if (null == head) return head;
		LinkedListNode last = head;
		LinkedListNode node = head.next;
		Set<Integer> set = new HashSet<>();
		set.add(head.val);
		while (null != node) {
			if (!set.contains(node.val)) {
				set.add(node.val);
				last = node;
			} else last.next = node.next;
			node = node.next;
		}
		return head;
	}
	
	public LinkedListNode removeDuplicatesNoBuffer(LinkedListNode head) {
		if (null == head) return head;
		LinkedListNode to = head, compare = null;
		while (null != to) {
			compare = to;
			while (null != compare.next) {
				if (compare.next.val == to.val) compare.next = compare.next.next;
				else compare = compare.next;
			}
			to = to.next;
		}
		
		return head;
	}
	
	public LinkedListNode kthToTheLast(LinkedListNode head, int k) {
		if (null == head) return head;
		LinkedListNode frontier = head, kth = head;
		for (int i = 0; i < k && null != frontier.next; i++) frontier = frontier.next;
		while (null != frontier.next) {
			kth = kth.next;
			frontier = frontier.next;
		}
		return kth;		
	}
	
	public void removeNode(LinkedListNode remove) {
		if (null == remove) return;
		while (null != remove.next) {
			remove.val = remove.next.val;
			if (null == remove.next.next) remove.next = null;
			else remove = remove.next;
		}
	}
	
	public LinkedListNode partition(LinkedListNode head, int partitioner) {
		LinkedListNode less = null, greater = null, l = null, g = null;
		while (null != head) {
			LinkedListNode next = head.next;
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
	
	public boolean isPalindrome(LinkedListNode head) {
		LinkedListNode fast = head, slow = head;
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
	
	public LinkedListNode intersects(LinkedListNode list1, LinkedListNode list2) {
		LinkedListNode l1 = list1, l2 = list2, longer = null, shorter = null, result = null;
		int s1 = 0, s2 = 0, diff = 0;
		while (null != l1) {
			s1++;
			l1 = l1.next;
		}
		while (null != l2) {
			s2++;
			l2 = l2.next;
		}
		if (s1 > s2) {
			longer = list1;
			shorter = list2;
			diff = s1-s2;
		} else {
			longer = list2;
			shorter = list1;
			diff = s2-s1;
		}
		
		while (diff-- > 0) longer = longer.next;
		while (longer != null && shorter != null && result == null)
			if (longer == shorter) result = longer;
			else {
				longer = longer.next;
				shorter = shorter.next;
			}
		return result;
	}
	
	public LinkedListNode findLoop(LinkedListNode head) {
		if (head == null || head.next == head) return head;
		if (head.next == null) return null;
		LinkedListNode fast = head.next.next, slow = head.next;
		while (fast != null && fast.next != null && fast.next.next != null 
				&& slow != null && slow.next != null && fast != slow) {
			fast = fast.next.next;
			slow = slow.next;
		}
		if (fast != slow) return null;
		while (head != slow) {
			head = head.next;
			slow = slow.next;
		}
		return slow;
	}
	
	public static void main(String[] args) {
		int nodes = 1, values = 8;
		LinkedListRemoveDups remover = new LinkedListRemoveDups();
		LinkedListNode head = LinkedListNode.createLinkedList(nodes, values);
//		LinkedListNode middle = head.getRandomNode(nodes-1);
		
		if (null != head) head.print();
//		System.out.println(middle.val);
//		remover.removeNode(middle);
//		if (null != head) head.print();
		
		//LinkedListNode removed = remover.removeDuplicates(head);
		//LinkedListNode kth = remover.kthToTheLast(head, 3);
		
		//LinkedListNode removedNoBuf = remover.removeDuplicatesNoBuffer(head);
		
		
		//if (null != removed) removed.print();
		//if (null != removedNoBuf) removedNoBuf.print();
		//if (null != kth) System.out.println(kth.val);
		
//		LinkedListNode partitioned = remover.partition(head, 3);
//		if (null != partitioned) partitioned.print();
//		else System.out.println("Partition returned null.");
		
		System.out.println(remover.isPalindrome(head));
		
		LinkedListNode list1 = LinkedListNode.createLinkedList(20, 5);
		LinkedListNode list2 = LinkedListNode.createLinkedList(10, 10);
		LinkedListNode list3 = list1.createIntersectingList(5);
		LinkedListNode intersect1 = remover.intersects(list1, list2);
		LinkedListNode intersect2 = remover.intersects(list1, list3);
		if (null == intersect1) System.out.println("List1 and List2 do not intersect.");
		else System.out.printf("List1 and List2 intersect at node with val %d\n", intersect1.val);
		list1.print();
		list2.print();
		if (null == intersect2) System.out.println("List1 and List3 do not intersect.");
		else System.out.printf("List1 and List3 intersect at node with val %d\n", intersect2.val);
		list1.print();
		list3.print();
		
		LinkedListNode list4 = LinkedListNode.createCircularList(9, 100);
		list4.print(20);
		LinkedListNode loop = remover.findLoop(list4);
		if (null != loop) System.out.printf("List4 is a circular list with a loop starting with value: %d", loop.val);
		else System.out.println("List4 is not a circular list.");

	}

}
