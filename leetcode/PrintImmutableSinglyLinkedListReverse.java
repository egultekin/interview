package leetcode;

import java.util.Scanner;

public class PrintImmutableSinglyLinkedListReverse {
	
	static class Node {
		int v;
		Node n;
		
		public Node(int val) {
			v = val;
			n = null;
		}
		
		public Node append(int val) {
			Node node = new Node(val);
			n = node;
			return n;
		}
		
		private void print(Node node, StringBuilder sb) {
			if (null == node) return;
			print(node.n, sb);
			sb.append(node.v).append('-');
		}
		
		public void print() {
			StringBuilder sb = new StringBuilder();

			print(n, sb);
			sb.append(v);
			System.out.println(sb.toString());
		}
	}
	
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Node root = new Node(sc.nextInt());
		Node node = root;
		while (N-- > 1) node = node.append(sc.nextInt());
		sc.close();
		
		root.print();
	}

}
