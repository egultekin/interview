package careercup;

import java.util.LinkedList;

public class TreeOrGraph {
	static class Node {
		int ind;
		LinkedList<Node> adj;
		
		public Node(int i) {
			ind = i;
			adj = new LinkedList<>();
		}
	}
	
	public boolean isTree(Node[] nodes) {
		boolean[] marked = new boolean[nodes.length];
		LinkedList<Node> queue = new LinkedList<>();
		int count = 0;
		marked[nodes[0].ind] = true;
		queue.add(nodes[0]);
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			count++;
			
			for (Node child : cur.adj)
				if (!marked[child.ind]) {
					marked[child.ind] = true;
					queue.add(child);
				} else return false;
		}
		
		return count == nodes.length;
	}

	public static void main(String[] args) {
		Node node0 = new Node(0);
		Node node1 = new Node(1);
		
		node0.adj.add(node1);
		System.out.printf("First set of nodes is a tree: %s\n", new TreeOrGraph().isTree(new Node[] { node0, node1}) == true);
		
		Node node2 = new Node(2);
		node1.adj.add(node2);
		node2.adj.add(node0);
		
		System.out.printf("Second set of nodes is not a tree: %s\n", new TreeOrGraph().isTree(new Node[] { node0, node1, node2}) == false);
	}

}
