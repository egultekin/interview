package cci;

import commontypes.linkedlist.SingleLinkedIntList;
import commontypes.linkedlist.SingleLinkedList;
import commontypes.linkedlist.SingleLinkNode;

public class LinkedListRemoveDups {
	
	public static void main(String[] args) {
		int nodes = 1, values = 8;
		SingleLinkedList<Integer> list = SingleLinkedIntList.createLinkedList(nodes, values);
//		LinkedListNode middle = head.getRandomNode(nodes-1);
		
		list.print();
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
		
		System.out.println(SingleLinkedIntList.isPalindrome(list));
		
		SingleLinkedList<Integer> list1 = SingleLinkedIntList.createLinkedList(20, 5);
		SingleLinkedList<Integer> list2 = SingleLinkedIntList.createLinkedList(10, 10);
		SingleLinkedList<Integer> list3 = SingleLinkedIntList.createIntersectingList(list1, 5);
		SingleLinkNode<Integer> intersect1 = list1.intersects(list2);
		SingleLinkNode<Integer> intersect2 = list1.intersects(list3);
		if (null == intersect1) System.out.println("List1 and List2 do not intersect.");
		else System.out.printf("List1 and List2 intersect at node with val %d\n", intersect1.val);
		list1.print();
		list2.print();
		if (null == intersect2) System.out.println("List1 and List3 do not intersect.");
		else System.out.printf("List1 and List3 intersect at node with val %d\n", intersect2.val);
		list1.print();
		list3.print();
		
		SingleLinkedList<Integer> list4 = SingleLinkedIntList.createCircularList(9, 100);
		list4.print(20);
		SingleLinkNode<Integer> loop = list4.findLoop();
		if (null != loop) System.out.printf("List4 is a circular list with a loop starting with value: %d", loop.val);
		else System.out.println("List4 is not a circular list.");

	}

}
