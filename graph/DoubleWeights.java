package graph;

import java.util.LinkedList;
import java.util.List;

public class DoubleWeights {
	private LinkedList<Integer> queue;
	private boolean[] marked;
	private static final char EMPTY = '.';
	
	public DoubleWeights() {
		this.queue = new LinkedList<Integer>();
	}
	
	public int minimalCost(String[] w1, String[] w2) {
		int N = w1.length;
		int[] minCost = new int[N];
		int[] cost1 = new int[N];
		int[] cost2 = new int[N];
		for (int i=1; i < N; i++) {
			minCost[i] = Integer.MAX_VALUE;
			cost1[i] = Integer.MAX_VALUE;
			cost2[i] = Integer.MAX_VALUE;
		}
		minCost[0] = 0;
		
		marked = new boolean[N];
		marked[0] = true;
		queue.addLast(0);
		
		while (!queue.isEmpty()) {
			Integer c = queue.poll();
			for (Integer adj : adj(c, w1, w2)) {
				int c1 = cost(w1, c, adj);
				int c2 = cost(w2, c, adj);
				if (minCost[adj] > mult(sum(cost1[c],c1), sum(cost2[c], c2))) {
					cost1[adj] = sum(cost1[c],c1);
					cost2[adj] = sum(cost2[c], c2);
					minCost[adj] = mult(cost1[adj], cost2[adj]);
				}
				
				if (!marked[adj]) {
					queue.addLast(adj);
					marked[adj] = true;
				}
			}
		}
		
		if (minCost[1] == Integer.MAX_VALUE) return -1;
		return minCost[1];
	}
	
	private int cost(String[] w, int i, int j) {
		if (null != w && i >= 0 && i < w.length && j >= 0 && j < w[i].length() && w[i].charAt(j) != EMPTY) 
			return digit(w[i].charAt(j));
		return Integer.MAX_VALUE;
	}
	
	private int digit(char c) {
		return c-'0';
	}
	
	private int sum(int a, int b) {
		if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE) return Integer.MAX_VALUE;
		return a+b;
	}
	
	private int mult(int a, int b) {
		if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE) return Integer.MAX_VALUE;
		return a*b;
	}
	
	private List<Integer> adj(Integer node, String[] w1, String[] w2) {
		LinkedList<Integer> result = new LinkedList<Integer>();
		for (int i=0; i < w1[node].length(); i++)
			if (i != node && w1[node].charAt(i) != EMPTY && w2[node].charAt(i) != EMPTY) result.add(i);
		return result;
	}

	public static void main(String[] args) {
		String[] w1 = new String[] {".4...1",
				 "4.1...",
				 ".1.1..",
				 "..1.1.",
				 "...1.1",
				 "1...1."};
		
		String[] w2 = new String[] {".4...1",
				 "4.1...",
				 ".1.1..",
				 "..1.1.",
				 "...1.1",
				 "1...1."}
;
		
		System.out.println(new DoubleWeights().minimalCost(w1, w2));

	}

}
