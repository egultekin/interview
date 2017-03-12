package commontypes.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class SingleLinkedList<T> {
	public SingleLinkNode<T> head;
	
	public SingleLinkedList() {
		head = null;
	}
	
	public SingleLinkedList(SingleLinkNode<T> node) {
		head = node;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public SingleLinkedList(T[] arr) {
		head = new SingleLinkNode<T>(arr[0]);
		SingleLinkNode<T> iter = head;
		for (int i = 1; i < arr.length; i++) {
			iter.next = new SingleLinkNode<>(arr[i]);
			iter = iter.next;
		}
	}
	
	public SingleLinkNode<T> intersects(SingleLinkedList<T> list2) {
		SingleLinkNode<T> l1 = head, l2 = list2.head, longer = null, shorter = null, result = null;
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
			longer = head;
			shorter = list2.head;
			diff = s1-s2;
		} else {
			longer = list2.head;
			shorter = head;
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
	
	public SingleLinkNode<T> removeDuplicates() {
		if (null == head) return head;
		SingleLinkNode<T> last = head;
		SingleLinkNode<T> node = head.next;
		Set<T> set = new HashSet<>();
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
	
	public SingleLinkNode<T> removeDuplicatesNoBuffer() {
		if (null == head) return head;
		SingleLinkNode<T> to = head, compare = null;
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
	
	public SingleLinkNode<T> kthToTheLast(int k) {
		if (null == head) return head;
		SingleLinkNode<T> frontier = head, kth = head;
		for (int i = 0; i < k && null != frontier.next; i++) frontier = frontier.next;
		while (null != frontier.next) {
			kth = kth.next;
			frontier = frontier.next;
		}
		return kth;		
	}
	
	public SingleLinkNode<T> findLoop() {
		if (head == null || head.next == head) return head;
		if (head.next == null) return null;
		SingleLinkNode<T> fast = head.next.next, slow = head.next;
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
	
	public void removeNode(SingleLinkNode<T> remove) {
		if (null == remove) return;
		while (null != remove.next) {
			remove.val = remove.next.val;
			if (null == remove.next.next) remove.next = null;
			else remove = remove.next;
		}
	}
	
	public void print() {
		if (null == head) {
			System.out.println("List is empty.");
			return;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(head.val);
		SingleLinkNode<T> n = head.next;
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
		builder.append(head.val);
		cNodes--;
		SingleLinkNode<T> next = head.next;
		while (cNodes-- > 0 && next != null) {
			builder.append("-").append(next.val);
			next = next.next;
		}
		System.out.println(builder.toString());
	}
	
	public SingleLinkNode<T> getRandomNode(int length) {
		int index = (int) Math.floor(Math.random()*length);
		SingleLinkNode<T> node = head;
		while (index-- > 0) node = node.next;
		return node;
	}
	
	public SingleLinkNode<T> getNode(int at) {
		SingleLinkNode<T> node = head;
		while (at-- > 0 && node != null) node = node.next;
		return node;
	}
}
