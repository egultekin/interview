package hackerrank;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class KunduAndTree3 {
	public static class Edge {
		boolean red;
		int index;
		
		public Edge(int idx, boolean isRed) {
			this.red = isRed;
			this.index = idx;
		}
	}
	
	public static class Node {
		int id;
		List<Edge> edges;
		int component;
		
		public Node(int idx) {
			this.id = idx;
			edges = new LinkedList<>();
		}
	}
	
	private static Node[] nodes;
	private static int N;
	private static long mod = (long)7+1000000000;
	
	static LinkedList<Integer> connectedSetsIterative(Node node) {
		int[] nComponent = new int[N+1];
		boolean[] marked = new boolean[N+1];
		
		LinkedList<Node> queue = new LinkedList<>();
		node.component = node.id;
		queue.add(node);
		marked[node.id] = true;
		
		while (!queue.isEmpty()) {
			Node c = queue.poll();
			nComponent[c.component]++;
			for (Edge e : c.edges)
				if (!marked[e.index]) {
					if (!e.red) nodes[e.index].component = c.component;
					else nodes[e.index].component = e.index;
					
					marked[e.index] = true;
					queue.add(nodes[e.index]);
				}
		}
		
		LinkedList<Integer> others = new LinkedList<>();
		for (int i = 1; i <= N; i++)
			if (nComponent[i] > 1) others.add(nComponent[i]);
		return others;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		nodes = new Node[N+1];
		for (int i = 0; i < N-1; i++) {
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			boolean red = sc.next().equals("r");
			Node node1 = nodes[n1];
			Node node2 = nodes[n2];
			if (null == node1) {
				nodes[n1] = new Node(n1);
				node1 = nodes[n1];
			}
			
			if (null == node2) {
				nodes[n2] = new Node(n2);
				node2 = nodes[n2];
			}
			
			node1.edges.add(new Edge(n2, red));
			node2.edges.add(new Edge(n1, red));
		}
		sc.close();
		
		
		if (N <= 0) throw new IllegalArgumentException();
		else if (N == 1) System.out.println(0);
		else {
			LinkedList<Integer> sets = connectedSetsIterative(nodes[1]);
			//LinkedList<Integer> sets = new LinkedList<>();
			//connectedSets(nodes[1], sets, new boolean[N+1]);
			
			//StringBuilder str = new StringBuilder();
			
			long max = ((((long)N*(N-1))%mod)*(N-2)/6)%mod;
			for (Integer next : sets) {
//				if (str.length() > 0) str.append("-").append(next);
//				else str.append(next);
				if (next > 1) max = ((max+mod)-(((long)(N-next)*next)%mod*(next-1)/2)%mod)%mod;
				if (next > 2) max = ((max+mod)-(((long)next*(next-1))%mod*(next-2)/6)%mod)%mod;
			}
			
//			System.out.println(str.toString());
			System.out.println((max+mod)%mod);
			
		}
	}

}
