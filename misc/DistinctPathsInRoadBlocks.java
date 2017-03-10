package misc;

import java.util.ArrayDeque;

class Corner {
	int row;
	int column;
	
	public Corner(int iRow, int iCol) {
		this.row = iRow;
		this.column = iCol;
	}
	
	public int R() {
		return this.row;
	}
	
	public int C() {
		return this.column;
	}
	
	public boolean equal(Corner compare) {
		if (this.row == compare.R() && this.column == compare.C())
			return true;
		
		return false;
	}
	
	public Corner[] getNextCorners() {
		return new Corner[] {
				new Corner(this.row+1, this.column),
				new Corner(this.row, this.column+1)
		};
	}	
}

class Block {
	Corner c1;
	Corner c2;
	
	public Block(Corner iC1, Corner iC2) {
		this.c1 = iC1;
		this.c2 = iC2;
	}
	
	public Corner start() { return this.c1; }
	public Corner end() { return this.c2; }
	
	public boolean equal(Block compare) {
		if ((this.c1.equal(compare.start()) || this.c1.equal(compare.end()))
				&& (this.c2.equal(compare.start()) || this.c2.equal(compare.end())))
			return true;
		
		return false;
	}
}

public class DistinctPathsInRoadBlocks {
	private Block[] notAvailable;
	private boolean[][] marked;
	private long[][] distinctPaths;
	private ArrayDeque<Corner> queue;
	int rows;
	int columns;
	
	
	public DistinctPathsInRoadBlocks(int width, int length, String[] bad) {
		this.columns = width+1;
		this.rows =  length+1;
		
		this.queue = new ArrayDeque<>(this.rows*this.columns);
		this.notAvailable = new Block[bad.length];
		this.distinctPaths = new long[this.rows][this.columns];
		this.marked = new boolean[this.rows][this.columns];
		this.initBadList(bad);
		
		for (int i=0; i<this.rows; i++)
			for (int j=0; j<this.columns; j++)
				this.distinctPaths[i][j] = 0;
		
	}
	
	public long numWays(Corner from, Corner to) {
		queue.add(from);
		setDistinctPaths(from, 1);
		mark(from);

		while (!queue.isEmpty()) {
			Corner current = queue.poll();

			for (Corner next : current.getNextCorners()) 
				if(valid(next) && !unavailable(new Block(current, next)))	{
					setDistinctPaths(next, distinctPaths(next) + distinctPaths(current));
					if (!marked(next)) {
						mark(next);
						queue.add(next);
					}
				}
		}
		
		return distinctPaths(to);
	}
	
	private void setDistinctPaths(Corner corner, long count) {
		if (!valid(corner)) throw new IllegalArgumentException("Invalid corner.");
		this.distinctPaths[corner.R()][corner.C()] = count;
	}
	
	private void mark(Corner corner) {
		if (!valid(corner)) throw new IllegalArgumentException("Invalid corner.");
		this.marked[corner.R()][corner.C()] = true;
	}
	
	private long distinctPaths(Corner corner) {
		if (!valid(corner)) throw new IllegalArgumentException("Invalid corner.");
		return this.distinctPaths[corner.R()][corner.C()];
	}
	
	private boolean unavailable(Block b) {
		for (Block block : this.notAvailable)
			if (block.equal(b)) return true;
		
		return false;
	}
	
	private boolean marked(Corner c) {
		if (!valid(c)) throw new IllegalArgumentException("Invalid corner.");
		return this.marked[c.R()][c.C()];
	}
	
	private boolean valid(Corner c) {
		if (null == c) throw new IllegalArgumentException("Corner is null.");
		if (c.R() < 0 || c.R() > this.rows-1 || c.C() < 0 || c.C() > this.columns-1) return false;
		return true;
	}
	
	private void initBadList(String[] bad) {
		int j=0;
		for (int i=0; i<bad.length; i++) {
			String[] corners = bad[i].split(" ");
			if (corners.length == 4) {
				Corner start = new Corner(Integer.parseInt(corners[1]), Integer.parseInt(corners[0]));
				Corner end = new Corner(Integer.parseInt(corners[3]), Integer.parseInt(corners[2]));
				this.notAvailable[j++] = new Block(start, end);
			}
		}
	}

	public static void main(String[] args) {
		int width = 6;
		int height = 6;
		DistinctPathsInRoadBlocks dpirb = new DistinctPathsInRoadBlocks(width, height, 
				new String[] { "0 0 0 1", "6 6 5 6" });
		
		Corner from = new Corner(0,0);
		Corner to = new Corner(height, width);
		System.out.format("Number of distinct ways from {%d, %d} to {%d, %d} is %d.", 
				from.R(), from.C(),
				to.R(), to.C(),
				dpirb.numWays(from, to));
	}

}
