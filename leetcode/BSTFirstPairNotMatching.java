package leetcode;

import java.util.List;
import java.util.Vector;
import java.util.Iterator;

public class BSTFirstPairNotMatching {
	static class Node {
		int v;
		Node l, r;
		public Node(int val) {
			v = val;
			l = r = null;
		}
		
		private void preOrder(Node n, StringBuilder sb) {
			if (null == n) return;
			if (sb.length() == 0) sb.append(n.v); else sb.append('-').append(n.v);
			preOrder(n.l, sb);
			preOrder(n.r, sb);
		}
		
		public void preOrder() {
			StringBuilder sb = new StringBuilder();
			preOrder(this, sb);
			System.out.println(sb.toString());
		}
	}
	
	int index;
	
	private Node bst(int[] a, int min, int max) {
		Node n = null;
		if (index < a.length && a[index] >= min && a[index] < max) {
			n = new Node(a[index]);
			int v = a[index];
			index++;
			n.l = bst(a, min, v);
			n.r = bst(a, v, max);
		}
 		return n;
	}
	
	private void leaves(Node n, List<Integer> list) {
		if (null == n) return;
		if (null == n.l && null == n.r) list.add(n.v);
		else {
			leaves(n.l, list);
			leaves(n.r, list);
		}
	}
	
	private List<Integer> diff(List<Integer> l1, List<Integer> l2) {
		List<Integer> res = new Vector<Integer>();
		Iterator<Integer> it1 = l1.iterator(), it2 = l2.iterator();
		while (it1.hasNext() && it2.hasNext()) {
			int t1 = it1.next(), t2 = it2.next();
			if (t1 != t2) {
				res.add(t1);
				res.add(t2);
				return res;
			}
		}
		if (it1.hasNext()) res.add(it1.next());
		if (it2.hasNext()) res.add(it2.next());
		return res;
	}
	
	private void printLeaves(List<Integer> l) {
		StringBuilder sb = new StringBuilder();
		for (int i : l) {
			if (sb.length() == 0) sb.append(i);
			else sb.append('-').append(i);
		}
		System.out.println(sb.toString());
	}
	
	public void printDiff(int[] a, int[] b) {
		List<Integer> l1 = new Vector<Integer>();
		List<Integer> l2 = new Vector<Integer>();
		index = 0;
		Node n1 = bst(a, Integer.MIN_VALUE, Integer.MAX_VALUE);
		n1.preOrder();
		leaves(n1, l1);
		printLeaves(l1);
		index = 0;
		Node n2 = bst(b, Integer.MIN_VALUE, Integer.MAX_VALUE);
		n2.preOrder();
		leaves(n2, l2);
		printLeaves(l2);
		List<Integer> diff = diff(l1, l2);
		Iterator<Integer> it1 = diff.iterator();
		if (it1.hasNext()) System.out.print(it1.next());
		while (it1.hasNext()) System.out.printf("-%d", it1.next());
	}

	public static void main(String[] args) {
		BSTFirstPairNotMatching bst = new BSTFirstPairNotMatching();
		bst.printDiff(new int[] {5, 4, 2, 4, 8, 6, 9}, new int[] {5, 3, 2, 4, 8, 7, 9});

	}

}
