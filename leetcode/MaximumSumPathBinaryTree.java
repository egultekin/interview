package leetcode;

public class MaximumSumPathBinaryTree {
	
	static class Node {
		int v;
		Node l, r;
		
		public Node(int val) {
			v = val;
			l = r = null;
		}
	}
	
	public static int maxSum(Node n) {
		if (null == n) return 0;
		int vl = Math.max(maxSum(n.l), 0);
		int vr = Math.max(maxSum(n.r), 0);
		return n.v+vl+vr;
	}

	public static void main(String[] args) {
		Node n8 = new Node(8);
		Node n81 = new Node(1);
		Node n82 = new Node(2);
		Node n815 = new Node(5);
		Node n826 = new Node(6);
		n81.l = n815;
		n82.l = n826;
		n8.l = n81;
		n8.r = n82;
		
		System.out.println(maxSum(n8));
		
		Node n10 = new Node(10);
		Node n103 = new Node(-3);
		Node n1035 = new Node(-5);
		Node n10357 = new Node(-8);
		Node n10310 = new Node(10);
		Node n103104 = new Node(4);
		Node n1031010 = new Node(10);
		
		n1035.l = n10357;
		n10310.l = n103104;
		n10310.r = n1031010;
		n103.l = n1035;
		n103.r = n10310;
		n10.l = n103;
		
		System.out.println(maxSum(n10));
		
	}

}
