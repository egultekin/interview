package misc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class ShortestPathUndirectedGraph {
	private ArrayDeque<Integer> queue;
	private int[][] connected;
	private int[] cost;
	private int[] minCost;
	private boolean[] visited;
	private int v;

	public ShortestPathUndirectedGraph(int[][] iConnected, int[] iCost, int iV) {
		this.connected = iConnected;
		this.v = iV;
		this.cost = iCost;
		this.queue = new ArrayDeque<Integer>(this.v);
		
		this.minCost = new int[this.v];
		this.visited = new boolean[this.v];
		for (int i=0; i<this.v; i++) this.minCost[i] = Integer.MAX_VALUE;
	}
	
	public static void main(String[] args) {
		int [][] graph = new int[][] {
				{ 0, 1, 1, 0, 1 },
				{ 1, 0, 1, 0, 0 },
				{ 1, 1, 0, 1, 0 },
				{ 0, 0, 1, 0, 0 },
				{ 1, 0, 0, 0, 0 }
		};
		
		int cost[] = new int[] { 8, 12, 7, 20, 3 };
		int toVertex = 4;
		
		ShortestPathUndirectedGraph spug = new ShortestPathUndirectedGraph(graph, cost, cost.length);
		int shortest = spug.shortestPath(toVertex);
		System.out.format("Shortest path from %d to %d is %d.", 0, toVertex, shortest);
	}
	
	public int shortestPath(int to) {
		queue.addLast(0);
		
		this.minCost[0] = cost(0);
		while(!queue.isEmpty()) {
			int current = queue.poll();
			visit(current);
			
			for (Integer next : adj(current)) {
				if (minCost(next) > minCostTraversing(current, next))
					setMinCost(next, minCostTraversing(current, next));
				
				if (!visited(next)) {
					queue.addLast(next);
				}
			}
		}
		
		return minCost(to);
	}
	
	private void setMinCost(int v, int cost) {
		this.minCost[v] = cost;
	}
	
	private int minCostTraversing(int from, int to) {
		if (minCost(from) == Integer.MAX_VALUE) return Integer.MAX_VALUE;
		
		return minCost(from) + cost(to);
	}
	
	private int cost(int v) {
		return this.cost[v];
	}
	
	private int minCost(int v) {
		return this.minCost[v];
	}
	
	private void visit(int v) {
		this.visited[v] = true;
	}
	
	private boolean visited(int v) {
		return this.visited[v];
	}
	
	private boolean connected(int v1, int v2) {
		return connected[v1][v2] == 1 || connected[v2][v1] == 1;
	}
	
	private List<Integer> adj(int v) {
		ArrayList<Integer> list = new ArrayList<>(v);
		for (int i = 0; i < this.v; i++) 
			if (connected(v,i)) list.add(i);
		
		return list;
	}

}
