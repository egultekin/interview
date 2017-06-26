package careercup.fb;

import java.util.List;
import java.util.Vector;
import java.util.LinkedList;
import java.util.ListIterator;

class SlidingPuzzle {
	static class Point {
		int r, c, cost;
		
		public Point(int row, int column) {
			r = row;
			c = column;
			cost = 0;
		}
	}
	
	static class Board {
		Point cur, prev;
		int nRows, nCols, cost;
		int[][] numbers;
		LinkedList<Point> stack;
		
		public Board(int[][] board) {
			cost = -1;
			nRows = board.length;
			nCols = board[0].length;
			numbers = new int[nRows][nCols];
			stack = new LinkedList<>();
			cur = new Point(nRows-1, nCols-1);
			prev = null;
			for (int i=0; i < nRows; i++)
			for (int j=0; j < nCols; j++)
				numbers[i][j] = board[i][j];
			cur.cost = computeCost();
			stack.addFirst(cur);
		}
		
		public int computeCost() {
			if (cost < 0 ) {
				cost = 0;
				for (int i=0; i < nRows; i++)
					for (int j=0; j < nCols; j++)
						if (i != cur.r || j != cur.c) {
							int row = (numbers[i][j]-1) / nCols;
							int col = (numbers[i][j]-1) % nRows;
							cost = cost + Math.abs(i - row) + Math.abs(j - col);
						}
			}
			return cost;
 		}
		
		public int transition(Point to) {
			int tRow = (numbers[to.r][to.c]-1) / nCols;
			int tCol = (numbers[to.r][to.c]-1) % nRows;
			int costT = Math.abs(tRow - to.r) + Math.abs(tCol - to.c);
			int costC = Math.abs(tRow - cur.r) + Math.abs(tCol - cur.c);
			return costC - costT;
		}
 		
 		public List<Point> next() {
 			int[] dR = {-1, -1, -1, 0, 0, 1, 1, 1};
 			int[] dC = {-1, 0, 1, -1, 1, -1, 0, 1};
 			List<Point> nextPoints = new Vector<Point>();
 			for (int i=0; i < dR.length; i++) {
 				int nextRow = cur.r + dR[i];
 				int nextCol = cur.c + dC[i];
 				if (nextRow >= 0 && nextRow < nRows && nextCol >= 0 && nextCol < nCols)
 					if (prev == null || prev.r != nextRow || prev.c != nextCol)
 						nextPoints.add(new Point(nextRow, nextCol));
 			}
 			return nextPoints;
 		}
 		
 		public void printMoves() {
 			StringBuilder sb = new StringBuilder();
 			ListIterator<Point> it = stack.listIterator(0);
 			while (it.hasNext()) {
 				Point point = it.next();
 				if (sb.length() == 0) sb.append(String.format("{%d, %d}", point.r, point.c));
 				else sb.append(',').append(String.format("{%d, %d}", point.r, point.c));
 			}
 			
 			sb.append("\n");
 			for (int i=0; i < nRows; i++)
 				for (int j=0; j < nCols; j++)
 					if (j == nCols-1) sb.append(numbers[i][j]).append('\n');
 					else sb.append(numbers[i][j]).append(',');
 			System.out.println(sb.toString());
 		}
 		
 		public Point move(Point to, int delta) {
 			Point previous = prev;
 			prev = cur;
 			int temp = numbers[cur.r][cur.c];
 			numbers[cur.r][cur.c] = numbers[to.r][to.c];
 			numbers[to.r][to.c] = temp;
 			cur = new Point(to.r, to.c);
 			cost = cost+delta;
 			cur.cost = cost;
 			stack.addFirst(cur);
 			return previous;
 		}
 		
 		public void undoMove(Point previous) {
 			Point point = stack.pollFirst();
 			cur = stack.peekFirst();
 			if (null != cur) { 
 				cost = cur.cost;

 				int temp = numbers[point.r][point.c];
 				numbers[point.r][point.c] = numbers[cur.r][cur.c];
 				numbers[cur.r][cur.c] = temp;
 			}
 			prev = previous;
 		}
 		
	}
	
	private Board board;
	private boolean finished = false;
	
	private void backtrack() {
		if (board.cost == 0) {
			board.printMoves();
			finished = true;
		} else {
			for (Point next : board.next()) {
				int tc = board.transition(next);
				if (tc <= 0) {
					Point prev = board.move(next, tc);
					backtrack();
					board.undoMove(prev);
					if (finished) break;
				}
			}
		}
	}
	
	public void solve(int[][] puzzle) {
		board = new Board(puzzle);
		if (board.computeCost() == 0) 
			System.out.println("No need to make any moves. Puzzle is already solved.");
		else backtrack();
	} 
	
	public static void main(String[] args) {
		SlidingPuzzle sp = new SlidingPuzzle();
		sp.solve(new int[][] {
			{ 6, 5, 1 },
			{ 2, 8, 7 },
			{ 4, 3, 0 }
		});
//		sp.solve(new int[][] {
//			{ 3, 2 },
//			{ 1, 0 }
//		});
	}
	
}