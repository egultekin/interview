package interview;

import java.util.ArrayDeque;

class Square {
	int row;
	int col;
	
	Square(int iRow, int iCol){
		row = iRow;
		col = iCol;
	}
	
	int R() { return row; }
	int C() { return col; }
	Square[] getDirections() { return new Square[] { 
			new Square(row-1, col), 
			new Square(row+1, col), 
			new Square(row, col+1), 
			new Square(row, col-1) 
			}; }
}

public class MinCostTraversalChessBoard {
	private ArrayDeque<Square> queue;
	private int rows;
	private int cols;
	private int[][] costs;
	private int[][] minCost;
	private boolean[][] visited;
	
	public MinCostTraversalChessBoard(int[][] iCosts, int iRow, int iCol) {
		this.costs = iCosts;
		this.rows = iRow;
		this.cols = iCol;
		this.queue = new ArrayDeque<>(this.rows*this.cols);
		this.minCost = new int[this.rows][this.cols];
		this.visited = new boolean[this.rows][this.cols];
		
		for(int i=0; i<this.rows; i++)
			for(int j=0; j<this.cols; j++)
				this.minCost[i][j] = Integer.MAX_VALUE;
	}
	
	private boolean visited(Square sqr) {
		return visited[sqr.R()][sqr.C()];
	}
	
	private void visit(Square sqr) {
		visited[sqr.R()][sqr.C()] = true;
	}
	
	private boolean valid(Square sqr) {
		if (null == sqr) return false;
		if (sqr.R() < 0 || sqr.R() > rows - 1 || sqr.C() < 0 || sqr.C() > cols - 1) return false;
		return true;
	}
	
	private int cost(Square sqr) {
		return costs[sqr.R()][sqr.C()];
	}
	
	private int minCost(Square sqr) {
		return minCost[sqr.R()][sqr.C()];
	}
	
	private void setMinCost(Square sqr, int cost) {
		this.minCost[sqr.row][sqr.col] = cost;
	}
	
	private int getMinCostTraversing(Square from, Square to) {
		if (minCost(from) == Integer.MAX_VALUE) return Integer.MAX_VALUE;
		
		return minCost(from) + cost(to);
	}
	
	public int getMinCost(Square from, Square to) {
		if (null == from || null == to) return Integer.MAX_VALUE;
		
		setMinCost(from, cost(from));
		queue.add(from);
		visit(from);
		while (!queue.isEmpty()) {
			Square current = queue.poll();
			
			//visit(current);
			for (Square next : current.getDirections()) {
				if (valid(next)) {
					if (minCost(next) > getMinCostTraversing(current, next)) 
						setMinCost(next, getMinCostTraversing(current, next));
					
					if (!visited(next)) {
						visit(next);
						queue.add(next);
					}
				}
			}	
		}
		
		return minCost(to);
	}
	
	public static void main(String[] args) {
		int[][] costs = new int[][] { 
				{ 1, 2, 3, 4 },
				{ 10, 11, 14, 15 },
				{ 9, 12, 13, 16 }, 
				{ 5, 6, 7, 8 }
		};
		Square from = new Square(0, 0);
		Square to = new Square(3, 3);
		
		MinCostTraversalChessBoard cb = new MinCostTraversalChessBoard(costs, costs.length, costs[0].length);
		System.out.format("Min cost of traversing from {%d,%d} to {%d,%d} is %d.\n", 
				from.R(), from.C(),
				to.R(), to.C(),
				cb.getMinCost(from, to));
	}
}
