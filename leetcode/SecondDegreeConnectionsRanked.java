package leetcode;

import java.util.*;

public class SecondDegreeConnectionsRanked {
	
	static class UndirectedGraphNode {
		int rank, id;
		List<Integer> neigh;
	
		void setRank(int nr) {
			rank = nr;
		}
	
		int getRank() {
			return rank;
		}
	
		int getId() {
			return id;
		}
	
		public UndirectedGraphNode(int identifier) {
			id = identifier;
			rank = 0;
		}
		
		public void setNeighbours(Integer[] n) {
			neigh = Arrays.asList(n);
		}
		
		Iterator<Integer> adj() {
			return neigh.iterator();
		}
	}

	private UndirectedGraphNode[] nodes;
	
	public SecondDegreeConnectionsRanked(int cNodes) {
		nodes = new UndirectedGraphNode[cNodes];
	}
	
	public void newNode(int id, Integer[] neighbours) {
		UndirectedGraphNode node = new UndirectedGraphNode(id);
		node.setNeighbours(neighbours);
		nodes[id] = node;
	}

	public void updateRank(UndirectedGraphNode node) {
		if (null == node) return;
		Iterator<Integer> it = node.adj();
		while (it.hasNext()) {
			UndirectedGraphNode n = nodes[it.next()];
			n.setRank(n.getRank()+1);
		}
	}
	
	public UndirectedGraphNode getNode(int id) {
		return nodes[id];
	}

	public List<UndirectedGraphNode> findSecDegConnections(UndirectedGraphNode myself) {
		Iterator<Integer> it = myself.adj();
		while (it.hasNext()) updateRank(nodes[it.next()]);
		TreeSet<UndirectedGraphNode> ss = new TreeSet<>(
			new Comparator<UndirectedGraphNode>() {
				@Override
				public int compare(UndirectedGraphNode n1, UndirectedGraphNode n2) {
					if (n1.getId() == n2.getId()) return 0;
					if (n2.getRank() > n1.getRank()) return 1;
					return -1;
				}
			}
		);
		it = myself.adj();
		while (it.hasNext()) {
			UndirectedGraphNode myN = nodes[it.next()];
			Iterator<Integer> itSec = myN.adj();
			while (itSec.hasNext()) {
				Integer cn = itSec.next();
				if(cn != myself.id) ss.add(nodes[cn]);
			}
		}
	
		return new ArrayList<UndirectedGraphNode>(ss);
	}

	public static void main(String[] args) {
		SecondDegreeConnectionsRanked scr = new SecondDegreeConnectionsRanked(10);
		scr.newNode(0, new Integer[] {1, 2, 3});
		scr.newNode(1, new Integer[] {4, 5, 6, 0});
		scr.newNode(2, new Integer[] {7, 8, 0, 4});
		scr.newNode(3, new Integer[] {9, 0});
		scr.newNode(4, new Integer[] {1, 2});
		scr.newNode(5, new Integer[] {1});
		scr.newNode(6, new Integer[] {1});
		scr.newNode(7, new Integer[] {2});
		scr.newNode(8, new Integer[] {2});
		scr.newNode(9, new Integer[] {3});
		
		List<UndirectedGraphNode> ranked = scr.findSecDegConnections(scr.getNode(0));
		StringBuilder sb = new StringBuilder();
		for (UndirectedGraphNode n : ranked) {
			sb.append(n.id).append(":").append(n.rank).append("\n");
		}
		System.out.println(sb.toString());
	}

}
