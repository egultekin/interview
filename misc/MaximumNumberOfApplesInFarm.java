package interview;

import java.util.ArrayDeque;

class Cell {
	int row;
	int column;
	
	public Cell(int iRow, int iColumn) {
		this.row = iRow;
		this.column = iColumn;
	}
	
	public int R() { return this.row; };
	public int C() { return this.column; };
	
	public Cell[] nextCells() {
		return new Cell[] {
				new Cell(this.row+1, this.column),
				new Cell(this.row, this.column+1)
		};
	}
}

public class MaximumNumberOfApplesInFarm {
	int rows;
	int columns;
	int[][] apples;
	boolean[][] visited;
	int[][] maxApples;
	ArrayDeque<Cell> queue;
	
	public MaximumNumberOfApplesInFarm(int[][] iApples) {
		if (null == iApples) throw new IllegalArgumentException("Number of apples in each cell of farm shall not be empty.");
		
		if (iApples.length == 0) throw new IllegalArgumentException("Invalid number of rows specified.");
		if (iApples[0].length == 0) throw new IllegalArgumentException("Invalid number of columns specified.");
		this.apples = iApples;
		this.rows = iApples.length;
		this.columns = iApples[0].length;
		
		this.visited = new boolean[this.rows][this.columns];
		this.maxApples = new int[this.rows][this.columns];
		this.queue = new ArrayDeque<>(this.rows*this.columns);
		
		for (int i=0; i<this.rows; i++)
			for (int j=0; j<this.columns; j++)
				this.maxApples[i][j] = Integer.MIN_VALUE;
	}
	
	private boolean visited(Cell cell) {
		if (!valid(cell)) throw new IllegalArgumentException("Cell out of bounds.");
		return this.visited[cell.R()][cell.C()];
	}
	
	private void visit(Cell cell) {
		if (!valid(cell)) throw new IllegalArgumentException("Cell out of bounds.");
		this.visited[cell.R()][cell.C()] = true;
	}
	
	private int apples(Cell cell) {
		if (!valid(cell)) throw new IllegalArgumentException("Cell out of bounds.");
		return this.apples[cell.R()][cell.C()];
	}
	
	private int maxApples(Cell at) {
		if (!valid(at)) throw new IllegalArgumentException("Cell out of bounds.");
		return this.maxApples[at.R()][at.C()];
	}
	
	private void setMaxApples(Cell at, int count) {
		this.maxApples[at.R()][at.C()] = count;
	}
	
	private int maxCollectableTraversing(Cell from, Cell to) {
		if (!valid(from) || !valid(to)) throw new IllegalArgumentException("Cell out of bounds.");
		if (this.maxApples(from) == Integer.MIN_VALUE) return Integer.MIN_VALUE;
		return this.maxApples(from) + apples(to);
	}
	
	private boolean valid(Cell cell) {
		if (cell.R() < 0 || cell.R() > rows - 1 || cell.C() < 0 || cell.C() > columns -1)
			return false;
		
		return true;
	}
	
	public int getMaxResult() {
		int max = Integer.MIN_VALUE;
		
		for (int i=0; i<this.rows; i++)
			for (int j=0; j<this.columns; j++)
				if (maxApples[i][j] > max) max = maxApples[i][j];
		
		return max;
	}
	
	public int collectMaxApples(Cell from) {
		if (!valid(from)) throw new IllegalArgumentException("Cell out of bounds.");
		this.queue.add(from);
		this.setMaxApples(from, apples(from));
		visit(from);
		
		while(!queue.isEmpty()) {
			Cell current = queue.poll();
			
			//visit(current);
			for (Cell next : current.nextCells()) {
				if (valid(next)) {
					if (maxApples(next) < maxCollectableTraversing(current,	next))
						setMaxApples(next, maxCollectableTraversing(current, next));
					
					if (!visited(next)) {
						visit(next);
						queue.add(next);
					}
				}
			}
		}
		
		return getMaxResult();
	}

	public static void main(String[] args) {
		int[][] apples = new int[][] {
				{ 1, 6, 23 },
				{ 7, 2, 13 },
				{ 14, 8, 3 },
				{ 22, 21, 9 },
				{ 19, 20, 25}
		};
		
		Cell from = new Cell(0,0);
		
		MaximumNumberOfApplesInFarm maif = new MaximumNumberOfApplesInFarm(apples);
		int maxApplesCollectable = maif.collectMaxApples(from);
		
		System.out.format("Max apples collectable starting at {%d,%d} is %d.", from.R(), from.C(), maxApplesCollectable);
	}

}
