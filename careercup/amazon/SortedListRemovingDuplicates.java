package careercup.amazon;

import commontypes.linkedlist.SingleLinkNode;
import commontypes.linkedlist.SingleLinkedList;

/*
 * https://www.careercup.com/question?id=6298536510488576
 * 
 * Given 3 separate sorted lists of Strings, filter out any String which occurs in different lists
 * and print remaining Strings in an ascending order. 
 * 
 * Time Complexity: O(N); N: sizeof(list1)+sizeof(list2)+sizeof(list3)
 * Space Complexity: (sizeof(list1)+sizeof(list2)+sizeof(list3))*sizeof(String)
 */

public class SortedListRemovingDuplicates {
	static SingleLinkNode<String> i1;
	static SingleLinkNode<String> i2;
	static SingleLinkNode<String> i3;
	
	public static SingleLinkedList<String> min(SingleLinkedList<String> node1, SingleLinkedList<String> node2) {
		SingleLinkedList<String> min = node1;
		if (null == min) min = node2; 
		else if (null != node2 && !node2.isEmpty() && node2.head.val.compareTo(min.head.val) < 0) min = node2;
		return min;
	}
	
	public static int advanceIfEqual(SingleLinkedList<String> list, String compareTo) {
		if (null == list || null == compareTo) return 0;
		if (!list.isEmpty() && list.head.val.compareTo(compareTo) == 0) {
			list.head = list.head.next;
			return 1;
		}
		
		return 0;
	}
	
	public static void printMergedList(SingleLinkedList<String> l1, 
			SingleLinkedList<String> l2, SingleLinkedList<String> l3) {
		i1 = l1.head;
		i2 = l2.head;
		i3 = l3.head;

		StringBuilder result = new StringBuilder();
		
		while (!l1.isEmpty() || !l2.isEmpty() || !l3.isEmpty()) {
			SingleLinkedList<String> min = min(min(l1, l2), l3);
			String compareTo = min.head.val;
			int duplicates = 0;
			duplicates += advanceIfEqual(l1, compareTo);
			duplicates += advanceIfEqual(l2, compareTo);
			duplicates += advanceIfEqual(l3, compareTo);
			if (duplicates < 2) 
				if (result.length() == 0) result.append(compareTo);
				else result.append("-").append(compareTo);
		}
		
		System.out.println(result.toString());
	}

	public static void main(String[] args) {
		SingleLinkedList<String> list1 = new SingleLinkedList<>(new String[] {"aaa", "bbb", "ddd", "xyxz"});
		SingleLinkedList<String> list2 = new SingleLinkedList<>(new String[] {"bbb", "ccc", "ccc", "hkp"});
		SingleLinkedList<String> list3 = new SingleLinkedList<>(new String[] {"ddd", "eee", "ffff", "lmn"});
		
		printMergedList(list1, list2, list3);
	}

}
