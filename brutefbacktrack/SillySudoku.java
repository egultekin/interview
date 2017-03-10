package brutefbacktrack;

import java.util.HashSet;

public class SillySudoku {
	private static final char MISSING = '-';
	private static final int UPPER = 4;
	
	private int ways(String[] board, int row, int col) {
		if (row == Integer.MAX_VALUE && col == Integer.MAX_VALUE) 
			return 1;
		if (!valid(board, row, col)) return 0;
		int nextRow = nextRow(UPPER, row, col);
		int nextCol = nextCol(UPPER, row, col);
		int ways = 0;
		if (board[row].charAt(col) == MISSING)
			for (int i=1; i <= UPPER; i++) {
				board[row] = replaceCharAt(board[row], col, String.valueOf(i).charAt(0));
				if (valid(board, row, col)) {
					int res = ways(board, nextRow, nextCol);
					if (res > 0) ways += res;
				}
				board[row] = replaceCharAt(board[row], col, MISSING);
			}
		else return ways(board, nextRow, nextCol);
		return ways;
	}
	
	private boolean valid(String[] board, int row, int col) {
		if (board[row].charAt(col) == MISSING) return true;
		return validGroup(board, row, col) && validRow(board, row) && validColumn(board, col);
	}
	
	private boolean validRow(String[] board, int row) {
		if (board == null || row < 0 || row > board.length-1) return false;
		HashSet<Character> unique = new HashSet<>();
		for (int col = 0; col < board[row].length(); col++) {
			Character key = board[row].charAt(col);
			if (key != MISSING)
				if (unique.contains(key)) return false;
				else unique.add(key);
		}
			
		return true;
	}
	
	private boolean validColumn(String[] board, int col) {
		if (board == null || col < 0 || col > board[0].length()-1) return false;
		HashSet<Character> unique = new HashSet<>();
		for (int row = 0; row < board.length; row++) {
			Character key = board[row].charAt(col);
			if (key != MISSING)
				if (unique.contains(key)) return false;
				else unique.add(key);
		}
			
		return true;
	}
	
	private boolean validGroup(String[] board, int row, int col) {
		int groupSize = UPPER/2;
		int horGroup = col/groupSize;
		int verGroup = row/groupSize;
		int cRow = verGroup*groupSize;
		int cCol = horGroup*groupSize;
		HashSet<Character> unique = new HashSet<>();
		for (int i=0; i < groupSize*groupSize; i++) {
			if (i > 0)
				if (i%groupSize == 0) {
					cRow++;
					cCol = horGroup*groupSize;
				} else cCol++;
			Character key = board[cRow].charAt(cCol);
			if (key != MISSING)
				if (unique.contains(key)) return false;
				else unique.add(key);
		}
		
		return true;
	}
	
	private int nextRow(int boardSize, int row, int col) {
		if (row == boardSize-1 && col == boardSize-1) return Integer.MAX_VALUE;
		if (col == boardSize-1) return row+1;
		return row;
	}
	
	private int nextCol(int boardSize, int row, int col) {
		if (row == boardSize-1 && col == boardSize-1) return Integer.MAX_VALUE;
		if (col == boardSize-1) return 0;
		return col+1;
	}
	
	private String replaceCharAt(String str, int at, char newChar) {
		StringBuilder build = new StringBuilder();
		if (at > 0) build.append(str.substring(0, at));
		build.append(newChar);
		if (str.length() > at+1) build.append(str.substring(at+1));
		return build.toString();
	}
	
	@SuppressWarnings("unused")
	public int countWays(String[] board) {
		if (UPPER > 9) throw new IllegalArgumentException("Sudoku is limited to the first 9 digits.");
		if (board == null || board.length != UPPER) throw new IllegalArgumentException("Invalid parameters.");
		return ways(board, 0, 0);
	}

	public static void main(String[] args) {
		String[] board = {"1---", 
				 "----", 
				 "----", 
				 "----"};
		
		System.out.format("Number of ways to solve this sudoku is %d.\n", new SillySudoku().countWays(board));

	}

}
