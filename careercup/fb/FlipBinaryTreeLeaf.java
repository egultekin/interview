package careercup.fb;

public class FlipBinaryTreeLeaf {
	static class DoubleNode {
		DoubleNode l, r, p;
		int v;
		
		public DoubleNode(int val) {
			v = val;
			l = r = p = null;
		}
		
		public void print() {
			StringBuilder sb = new StringBuilder();
			print(this, sb);
			System.out.println(sb.toString());
		}
		
		private void print(DoubleNode n, StringBuilder sb) {
			if (null == n) return;
			if (sb.length() == 0) sb.append(n.v); else sb.append('-').append(n.v);
			print(n.l, sb);
			print(n.r, sb);
		}
	}
	
	static class Node {
		Node l, r;
		int v;
		
		public Node(int val) {
			v = val;
		}
		
		public void print() {
			StringBuilder sb = new StringBuilder();
			print(this, sb);
			System.out.println(sb.toString());
		}
		
		private void print(Node n, StringBuilder sb) {
			if (null == n) return;
			if (sb.length() == 0) sb.append(n.v); else sb.append('-').append(n.v);
			print(n.l, sb);
			print(n.r, sb);
		}
	}
	
	public static DoubleNode invert(DoubleNode n) {
		if (n.v == 0) n.v = 1; else n.v = 0;
		return check(n.p);
	}
	
	private static DoubleNode check(DoubleNode n) {
		int left = 0, right = 0;
		if (null != n.l) left = n.l.v;
		if (null != n.r) right = n.r.v;
		n.v = left & right;
		if (n.p != null) return check(n.p);
		return n;
	}
	
	public static Node invert(Node n, Node r) {
		if (n.v == 0) n.v = 1; else n.v = 0;
		return check(r);
	}
	
	private static Node check(Node root) {
		if (null == root.l && null == root.r) return root;
		Node l = check(root.l);
		Node r = check(root.r);
		int left = 0, right = 0;
		if (null != l) left = l.v;
		if (null != r) right = r.v;
		root.v = left & right;
		return root;
	}

	public static void main(String[] args) {
		Node n1 = new Node(0);
		Node n2 = new Node(0);
		Node n3 = new Node(1);
		Node n4 = new Node(0);
		Node n5 = new Node(1);
		Node n6 = new Node(1);
		Node n7 = new Node(1);
		
		n1.l = n2;
		n1.r = n3;
		n2.l = n4;
		n2.r = n5;
		n3.l = n6;
		n3.r = n7;
		
		Node n = invert(n6, n1);
		n.print();
		
		DoubleNode d1 = new DoubleNode(0);
		DoubleNode d2 = new DoubleNode(0);
		DoubleNode d3 = new DoubleNode(1);
		DoubleNode d4 = new DoubleNode(0);
		DoubleNode d5 = new DoubleNode(1);
		DoubleNode d6 = new DoubleNode(1);
		DoubleNode d7 = new DoubleNode(1);
		
		d1.l = d2;
		d2.p = d1;
		d1.r = d3;
		d3.p = d1;
		d2.l = d4;
		d4.p = d2;
		d2.r = d5;
		d5.p = d2;
		d3.l = d6;
		d6.p = d3;
		d3.r = d7;
		d7.p = d3;
		
		DoubleNode d = invert(d6);
		d.print();
		
	}

}
