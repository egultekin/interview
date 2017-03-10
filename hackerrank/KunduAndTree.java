package hackerrank;

import java.util.LinkedList;
import java.util.List;

public class KunduAndTree {

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
		// number of red nodes encountered after this node during post order tree traversal
		int nRed;
		// number of black nodes encountered after this node during post order tree traversal
		int nBlack;
		
		public Node(int idx) {
			this.id = idx;
			edges = new LinkedList<>();
			nRed = 0;
			nBlack = 0;
		}
	}
	
	private static boolean[] marked;
	private static Node[] nodes;
	private static int N;
	private static long mod = (long)7+1000000000;
	
	static long countRedPaths(Node node, int nRedsBefore, int nBlacksBefore, boolean[] marked) {
		long count = 0;
		int redsToNext, blacksToNext;
		marked[node.id] = true;
		for (Edge e : node.edges)
			if (!marked[e.index]) {
				if (e.red) {
					redsToNext = node.nRed+nRedsBefore+nBlacksBefore+node.nBlack-nodes[e.index].nBlack-nodes[e.index].nRed;
					blacksToNext = 0;
				} else { 
					redsToNext = node.nRed+nRedsBefore-nodes[e.index].nRed-1;
					blacksToNext = node.nBlack+nBlacksBefore-nodes[e.index].nBlack;
				}
				count += countRedPaths(nodes[e.index], redsToNext, blacksToNext, marked);
			}
		
		return (count+(nRedsBefore*node.nRed)%mod)%mod;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
