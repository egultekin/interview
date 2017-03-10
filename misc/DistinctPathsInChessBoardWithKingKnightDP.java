package misc;

import java.util.ArrayDeque;

public class DistinctPathsInChessBoardWithKingKnightDP {
	class IndexPair {
		int row;
		int column;
		
		public IndexPair(int iRow, int iCol) {
			this.row = iRow;
			this.column = iCol;
		}
		
		public int R() { return this.row; }
		public int C() { return this.column; }
	}
	
	private ArrayDeque<IndexPair> queue;
	private int height;
	private int width;
	// ending at index with total number of moves so far
	private long[][] pathCount;
	private boolean[][] marked;

	public DistinctPathsInChessBoardWithKingKnightDP() {
	}
	
	private int index(int row, int col) {
		if (!this.valid(row, col)) throw new IllegalArgumentException("Row or column specified is out of board.");
		return row*this.width+col;
	}
	
	private int index(IndexPair pair) {
		return this.index(pair.R(), pair.C());
	}
	
	private void mark(int row, int col) {
		if (valid(row, col))
			marked[row][col] = true;
	}
	
	private boolean marked(int row, int col) {
		if (!valid(row, col)) throw new IllegalArgumentException("Row or column specified is out of board.");
		return marked[row][col];
	}
	
	private boolean valid(int row, int col) {
		if (row < 0 || row > this.height-1 || col < 0 || col > this.height-1) return false;
		return true;
	}
	
	private boolean valid(IndexPair pair) {
		return this.valid(pair.R(), pair.C());
	}
	
	private IndexPair[] getMoves(IndexPair c) {
		return new IndexPair[] {
			new IndexPair(c.R()-2, c.C()-1), // upper-left L
			new IndexPair(c.R()-2, c.C()+1),	// upper-right L
			new IndexPair(c.R()+2, c.C()-1),	// down L
			new IndexPair(c.R()+2, c.C()+1),	// down L
			new IndexPair(c.R()-1, c.C()-2),	// left L
			new IndexPair(c.R()+1, c.C()-2),	// left L
			new IndexPair(c.R()+1, c.C()+2),	// right L
			new IndexPair(c.R()-1, c.C()+2),	// right L
			new IndexPair(c.R()-1, c.C()-1),
			new IndexPair(c.R()-1, c.C()),
			new IndexPair(c.R()-1, c.C()+1),
			new IndexPair(c.R(), c.C()-1),
			new IndexPair(c.R(), c.C()+1),
			new IndexPair(c.R()+1, c.C()-1),
			new IndexPair(c.R()+1, c.C()),
			new IndexPair(c.R()+1, c.C()+1)
		};
	}
	
	private void initialize(int size) {
		this.height = size;
		this.width = size;
		this.marked = new boolean[this.height][this.width];
		this.queue = new ArrayDeque<IndexPair>(height*width);
		this.pathCount = new long[this.height*this.width][this.height+this.width];
		
		for (int i=0; i<this.height*this.width; i++)
			for (int j=0; j<this.height+this.width; j++)
				this.pathCount[i][j] = 0;
	}
	
	public long howMany(int size, int[] start, int[] end, int moves) {
		if (null == start || null == end || size <= 0 || start.length != 2 || end.length != 2 || moves <= 0) return 0;
		initialize(size);
		
		IndexPair startPosition = new IndexPair(start[0], start[1]);
		IndexPair endPosition = new IndexPair(end[0], end[1]);
		int move = 0;
		pathCount[index(startPosition)][0] = 1;
		mark(startPosition.R(), startPosition.C());
		queue.add(startPosition);
		int stepsToNextMove = queue.size();
		
		while(!queue.isEmpty()) {
			IndexPair current = queue.poll();
			--stepsToNextMove;
			
			for (IndexPair next : this.getMoves(current)) {
				if (valid(next)) {
					pathCount[index(next)][move+1] += pathCount[index(current)][move];
					if (!marked(next.R(), next.C())) {
						mark(next.R(), next.C());
						queue.add(next);
					}
				}
			}
			
			if (stepsToNextMove == 0) {
				stepsToNextMove = queue.size();
				move++;
			}
		}
		
		return this.pathCount[index(endPosition)][moves];
	}
	
	public static void main(String[] args) {
		int size = 100;
		int[] start = new int[] { 0, 0 };
		int[] end = new int[] { 0, 99 };
		int moves = 50;
		DistinctPathsInChessBoardWithKingKnightDP solution = new DistinctPathsInChessBoardWithKingKnightDP();
		
		System.out.format("There are %d ways for kingknight to get from {%d, %d} to {%d, %d} in %d steps.", 
				solution.howMany(size, start, end, moves),
				start[0], start[1],
				end[0], end[1],
				moves);
	}
}
