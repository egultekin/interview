package cci;

import java.util.HashSet;
import java.util.Set;

import commontypes.BTreeNode;

public class FirstCommonAncestorBTree {
	private static BTreeNode<Integer> findCommonAncestor(
			BTreeNode<Integer> node, BTreeNode<Integer> n1, BTreeNode<Integer> n2, Set<BTreeNode<Integer>> s) {
		BTreeNode<Integer> res = null;
		if (null == node) return res;
		Set<BTreeNode<Integer>> set1 = new HashSet<>();
		Set<BTreeNode<Integer>> set2 = new HashSet<>();

		res = findCommonAncestor(node.l(), n1, n2, set1);
		if (null == res) res = findCommonAncestor(node.r(), n1, n2, set2);
		if (null == res) {
			if (set1.size() + set2.size() == 2) res = node;

			s.addAll(set1);
			s.addAll(set2);

			if (n1 == node || n2 == node) s.add(node);
		}
		
		return res;
	}
	
	public static BTreeNode<Integer> ancestor(BTreeNode<Integer> node, BTreeNode<Integer> n1, BTreeNode<Integer> n2) {
		BTreeNode<Integer> found = findCommonAncestor(node, n1, n2, new HashSet<>());
		if (null == found) System.out.println("No common ancestor exists.");
		else System.out.println(found.value());
		return found;
	}
	
	public static void main(String[] args) {
		BTreeNode<Integer> n1 = new BTreeNode<Integer>(1);
		BTreeNode<Integer> n2 = new BTreeNode<Integer>(2);
		BTreeNode<Integer> n4 = new BTreeNode<Integer>(4);
		BTreeNode<Integer> n3 = new BTreeNode<Integer>(3, n2, n4);
		BTreeNode<Integer> n5 = new BTreeNode<Integer>(5, n1, n3);
		BTreeNode<Integer> n9 = new BTreeNode<Integer>(9);
		BTreeNode<Integer> n12 = new BTreeNode<Integer>(12, n9, null);
		BTreeNode<Integer> n8 = new BTreeNode<Integer>(8, n5, n12);
		
		ancestor(n8, n3, n9);
	}
}
