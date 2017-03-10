package hackerrank;

import java.util.LinkedList;
import java.util.Scanner;

public class CutTheTree {
	static class Node {
		int index;
		int value;
		LinkedList<Node> adj;
		
		public Node(int index, int val) {
			this.index = index;
			this.value = val;
			this.adj = new LinkedList<CutTheTree.Node>();
		}
		
		public void add(Node node) {
			this.adj.add(node);
		}
		
		public Iterable<Node> adjacent() {
			return adj;
		}
		
		public int childs() {
			return this.adj.size();
		}
	}
	
	static int min = Integer.MAX_VALUE;
	static boolean[] marked; 
	
	static int dfs(Node node, int sum) {
		int childSum = 0;
		marked[node.index] = true;
		for (Node adj : node.adj) {
			if (!marked[adj.index]) childSum += dfs(adj, sum);
		}
		
		int total = childSum+node.value;
		if (Math.abs(2*total-sum) < min) min = Math.abs(2*total-sum);
		return total;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int sum = 0;
		Node[] arr = new Node[N];
		marked = new boolean[N];
		for (int i = 0; i < N; i++) {
			Node node = new Node(i, sc.nextInt());
			sum += node.value;
			arr[i] = node;
		}

		for (int i = 0; i < N-1; i++) {
			int e1 = sc.nextInt()-1;
			int e2 = sc.nextInt()-1;
			arr[e1].add(arr[e2]);
			arr[e2].add(arr[e1]);
		}
		sc.close();

		dfs(arr[0], sum);
		System.out.println(min);
	}

}
