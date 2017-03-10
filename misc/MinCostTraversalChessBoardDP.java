package misc;

import java.util.ArrayDeque;

public class MinCostTraversalChessBoardDP {

	class Square {
		int x;
		int y;
		
		public Square(int iX, int iY) {
			this.x = iX;
			this.y = iY;
		}
	}
	
	private int n;
	private int[][] minCosts;
	private int[][] cost;
	private boolean[][] marked;
	private ArrayDeque<Square> queue;
	
	public MinCostTraversalChessBoardDP(int iN) {
		this.n = iN;
		this.minCosts = new int[n][n];
		this.marked = new boolean[n][n];
		this.queue = new ArrayDeque<Square>(this.n*this.n);
		
		for (int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				this.minCosts[i][j] = Integer.MAX_VALUE;
	}
	
	public int getMinCost(int[][] iCost, int fromX, int fromY, int toX, int toY) {
		cost = iCost;
		
		marked[fromX][fromY] = true;
		minCosts[fromX][fromY] = cost[fromX][fromY];
		queue.add(new Square(fromX, fromY));
		
		while (!queue.isEmpty()) {
			Square current = queue.poll();
			
			int x = current.x;
			int y = current.y;
			
			if (valid(x, y)) {
				if (valid(x, y+1) && (minCosts[x][y+1] > getMinCostTraversing(x, y, x, y+1))) minCosts[x][y+1] = getMinCostTraversing(x, y, x, y+1);
				if (valid(x+1, y) && (minCosts[x+1][y] > getMinCostTraversing(x, y, x+1, y))) minCosts[x+1][y] = getMinCostTraversing(x, y, x+1, y);
				if (valid(x, y-1) && (minCosts[x][y-1] > getMinCostTraversing(x, y, x, y-1))) minCosts[x][y-1] = getMinCostTraversing(x, y, x, y-1);
				if (valid(x-1, y) && (minCosts[x-1][y] > getMinCostTraversing(x, y, x-1, y))) minCosts[x-1][y] = getMinCostTraversing(x, y, x-1, y);

				if (valid(x, y+1) && !marked[x][y+1]) { marked[x][y+1] = true; queue.add(new Square(x, y+1)); }
				if (valid(x+1, y) && !marked[x+1][y]) { marked[x+1][y] = true; queue.add(new Square(x+1, y)); }
				if (valid(x, y-1) && !marked[x][y-1]) { marked[x][y-1] = true; queue.add(new Square(x, y-1)); }
				if (valid(x-1, y) && !marked[x-1][y]) { marked[x-1][y] = true; queue.add(new Square(x-1, y)); }
			}
			
		}
		
		return minCosts[toX][toY];
	}
	
	private int getMinCostTraversing(int fromX, int fromY, int toX, int toY) {
		if (minCosts[fromX][fromY] == Integer.MAX_VALUE) return Integer.MAX_VALUE;
		return minCosts[fromX][fromY] + cost[toX][toY];
	}
	
	private boolean valid(int x, int y) {
		if (x < 0 || x > this.n-1 || y < 0 || y > this.n-1) return false;
		return true;
	}

	public static void main(String[] args) {
		int[][] costs = new int[][] { 
				{ 1, 2, 3, 4 },
				{ 10, 11, 14, 15 },
				{ 9, 12, 13, 16 }, 
				{ 5, 6, 7, 8 }
		};
		
		int fromX = 0, fromY = 0, toX = 3, toY = 3;
		MinCostTraversalChessBoardDP cb = new MinCostTraversalChessBoardDP(costs.length);
		System.out.format("Min cost of traversing from {%d,%d} to {%d,%d} is %d.\n", 
				fromX, fromY,
				toX, toY,
				cb.getMinCost(costs, fromX, fromY, toX, toY));

	}

}
