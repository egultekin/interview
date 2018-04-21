package cci;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import commontypes.BSTNode;

public class BSTSequence {
	private static String value(BSTNode<Integer> node) {
		if (null == node) return "";
		return String.valueOf(node.value());
	}
	
	private static StringBuilder checkEmptyAndAppend(StringBuilder sb,  String s) {
		if (!s.equals("")) {
			if (sb.length() > 0) sb.append(",");
			sb.append(s);
		}
		return sb;
	}
	
	private static String format(String s1, String s2, String s3, String s4) {
		StringBuilder sb = new StringBuilder();
		sb.append(s1);
		checkEmptyAndAppend(sb, s2);
		checkEmptyAndAppend(sb, s3);
		checkEmptyAndAppend(sb, s4);
		return sb.toString();
	}
	
	private static void bstSequence(BSTNode<Integer> n, Map<Integer, Set<String>> map) {
		if (null == n) return;
		bstSequence(n.l(), map);
		bstSequence(n.r(), map);
		Set<String> ml = (null != n.l()) ? map.get(n.l().value()) : null;
		Set<String> mr = (null != n.r()) ? map.get(n.r().value()) : null;
		if (null == ml) ml = new HashSet<>(Arrays.asList(new String[] {""}));
		if (null == mr) mr = new HashSet<>(Arrays.asList(new String[] {""}));
		Set<String> res = new HashSet<>();
		for (String cl : ml)
			for (String cr : mr) {
				res.add(format(value(n.l()), value(n.r()), cl, cr));
				res.add(format(value(n.l()), value(n.r()), cr, cl));
				res.add(format(value(n.l()), cl, value(n.r()), cr));
				res.add(format(value(n.r()), value(n.l()), cr, cl));
				res.add(format(value(n.r()), value(n.l()), cl, cr));
				res.add(format(value(n.r()), cr, value(n.l()), cl));
			}
		map.put(n.value(), res);
	}
	
	public static void printSequence(BSTNode<Integer> root) {
		if (null == root) {
			System.out.println("Empty sequence!");
			return;
		}
		
		Map<Integer, Set<String>> map = new HashMap<>();
		bstSequence(root, map);
		Set<String> strlist = map.get(root.value());
		for (String s : strlist) 
			System.out.println(String.format("%s,%s", value(root), s));
	}
	
	public static void main(String[] args) {
		BSTNode<Integer> q = new BSTNode<Integer>(6);
		BSTNode<Integer> s = new BSTNode<Integer>(9);
		BSTNode<Integer> r = new BSTNode<Integer>(8, q, s);
		BSTNode<Integer> c = new BSTNode<Integer>(1);
		BSTNode<Integer> l = new BSTNode<Integer>(3, c, null);
		BSTNode<Integer> root = new BSTNode<Integer>(5, l, r);
		//printSequence(root);
		
		BSTNode<Integer> a = new BSTNode<Integer>(1);
		BSTNode<Integer> b = new BSTNode<Integer>(3);
		BSTNode<Integer> d = new BSTNode<Integer>(2, a, b);
		printSequence(d);
	}
}
