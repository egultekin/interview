package bfs;

import java.util.LinkedList;
import java.util.Vector;

class TestCase {
	int sum;
	String knight;
	String queen;
	String rook;
	
	public TestCase(String k, String q, String r) {
		knight = k;
		queen = q;
		rook = r;
	}
	
	public String queen() { return queen; }
	public String rook() { return rook; }
	public String knight() { return knight; }
	public String name() { return "Test Case"; }
}

public class CaptureThemAll {
	class Cell {
		int row;
		int col;
		
		public Cell(int r, int c) {
			this.row = r;
			this.col = c;
		}
		
		public Cell(String cr) {
			this.col = cr.charAt(0)-'a';
			this.row = cr.charAt(1)-'1';
		}
		
		public int R() {
			return this.row;
		}
		
		public int C() {
			return this.col;
		}
		
		public Vector<Cell> next() {
			Vector<Cell> v = new Vector<Cell>(8);
			int r = this.row;
			int c = this.col;
			
			if (this.isValid(r-2, c-1)) v.add(new Cell(r-2, c-1));
			if (this.isValid(r-2, c+1)) v.add(new Cell(r-2, c+1));
			if (this.isValid(r-1, c-2)) v.add(new Cell(r-1, c-2));
			if (this.isValid(r-1, c+2)) v.add(new Cell(r-1, c+2));
			if (this.isValid(r+1, c-2)) v.add(new Cell(r+1, c-2));
			if (this.isValid(r+1, c+2)) v.add(new Cell(r+1, c+2));
			if (this.isValid(r+2, c-1)) v.add(new Cell(r+2, c-1));
			if (this.isValid(r+2, c+1)) v.add(new Cell(r+2, c+1));
			
			return v;
		}
		
		@Override
		public boolean equals(Object obj) {
			try {
				Cell cell = (Cell) obj;
				return cell.C() == this.col && cell.R() == this.row;
			} catch (Exception e) {
				return false;
			}
		}
		
		private boolean isValid(int row, int col) {
			if (row >= 0 & row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE)
				return true;
			return false;
		}
	}
	
	private final static int BOARD_SIZE = 8;
	private LinkedList<Cell> queue;
	private boolean[][] marked;
	
	
	private int sanitizeSum(int n1, int n2) {
		if (n1 == Integer.MAX_VALUE || n2 == Integer.MAX_VALUE) return Integer.MAX_VALUE;
		return n1+n2;
	}
	
	private int minMoves(Cell from, Cell to) {
		queue = new LinkedList<Cell>();
		marked = new boolean[BOARD_SIZE][BOARD_SIZE];
		int moves = 0;
		int atSameLevel = 0;
		marked[from.C()][from.R()] = true;
		queue.addLast(from);
		
		while (!queue.isEmpty()) {
			if (atSameLevel == queue.size()) moves++;
			Cell c = queue.poll();
			if (atSameLevel > 0) atSameLevel--;
			
			if (c.equals(to)) return moves;
			
			for (Cell next:c.next()) {
				if (!marked[next.C()][next.R()]) {
					marked[next.C()][next.R()] = true;
					queue.addLast(next);
				}
			}
			
			if (atSameLevel == 0) atSameLevel = queue.size();
		}
		
		return Integer.MAX_VALUE;
	}

	public int fastKnight(String knight, String queen, String rook) {
		Cell k = new Cell(knight);
		Cell q = new Cell(queen);
		Cell r = new Cell(rook);

		return sanitizeSum(minMoves(q,r), Math.min(minMoves(k, q), minMoves(k,r)));
	}
	
	
	public static void main(String[] args) {
		Vector<TestCase> testCases = new Vector<TestCase>();
		testCases.add(new TestCase("a1", "c5", "b3"));
		testCases.add(new TestCase("b1", "a3", "c3"));
		testCases.add(new TestCase("a1", "b2", "a2"));
		testCases.add(new TestCase("a5", "e4", "b7"));
		testCases.add(new TestCase("h8", "d2", "e2"));
		
		for (int i=0; i < testCases.size(); i++) {
			TestCase tc = testCases.elementAt(i);
			System.out.format(
				"Min number of moves to capture %s and %s with knight at %s is %d.\n",
				tc.rook(), tc.queen(), tc.knight(), 
				new CaptureThemAll().fastKnight(tc.knight(), tc.queen(), tc.rook()));
		}

	}

}
