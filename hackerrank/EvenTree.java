package hackerrank;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

public class EvenTree {
	static class Node implements Comparable<Node> {
		public int ind;
		public int child;
		LinkedList<Node> adj;
		
		public Node(int index) {
			ind = index;
			child = 0;
			adj = new LinkedList<>();
		}

		@Override
		public int compareTo(Node o) {
			return ((Integer) ind).compareTo(o.ind);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Node)) return false;
			return ((Node)obj).compareTo(this) == 0;
		}
	}
	
	public static boolean[] marked;
	
	public static void refine(Node head, int N) {
		LinkedList<Node> queue = new LinkedList<>();
		boolean[] marked = new boolean[N+1];
		
		marked[head.ind] = true;
		queue.addLast(head);
		while (!queue.isEmpty()) {
			Node current = queue.pollFirst();
			LinkedList<Integer> toBeRemoved = new LinkedList<>();
			
			for (Node child : current.adj)
				if (!marked[child.ind]) {
					marked[child.ind] = true;
					queue.addLast(child);
				} else toBeRemoved.add(Integer.valueOf(child.ind));
			
			for (Integer remove : toBeRemoved) current.adj.remove(new Node(remove));
			current.child = current.adj.size();
		}
	}
	
	public static void refine(Node head) {
		marked[head.ind] = true;
		if (head.adj.size() > 0) {
			LinkedList<Integer> toBeRemoved = new LinkedList<>();
			int count = 0;
			for (Node child : head.adj) {
				if (marked[child.ind]) toBeRemoved.add(child.ind);
				else {
					refine(child);
					count += child.child;
				}
			}
			
			for (int remove : toBeRemoved) head.adj.remove(new Node(remove));
			head.child = count+head.adj.size();
		}
	}
	
	public static int maxRemove(Node node) {
		if (node.child < 2) return 0;
		int count = 0;
		node.child = 0;
		for (Node n : node.adj) 
			if (!marked[n.ind]) {
				marked[n.ind] = true;
				count += maxRemove(n);
				if (n.child % 2 == 1) count++;
				else node.child += (n.child+1);
			}
		
		return count;
	}

	public static void main(String[] args) {
		Hashtable<Integer, EvenTree.Node> nodes = new Hashtable<>();
		Scanner sc =  new Scanner(System.in);
		
		int N = sc.nextInt();
		int E = sc.nextInt();
		
		for (int i = 0; i < N; i++) nodes.put(i+1, new Node(i+1));
		
		for (int i = 0; i < E; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			
			Node nV1 = nodes.get(v1);
			Node nV2 = nodes.get(v2);
			nV1.adj.add(nV2);
			nV2.adj.add(nV1);
		}
		
		sc.close();

		marked = new boolean[N+1];
		refine(nodes.get(1));
		marked = new boolean[N+1];
		System.out.println(maxRemove(nodes.get(1)));
	}

}
