package brutefbacktrack;

import java.util.HashSet;

public class SudokuSolver {

	public static final int N = 9;
	public static final int G = 3;
	public static final char EMPTY = 'X';
	
	private String replace(String input, int replace, char c) {
		if (null == input || replace < 0 || replace > input.length()-1) 
			throw new IllegalArgumentException("Illegal argument.");
		
		StringBuilder builder = new StringBuilder();
		if (replace > 0) builder.append(input.substring(0, replace));
		builder.append(c);
		if (replace < input.length()-1)	builder.append(input.substring(replace+1));
		return builder.toString();
	}
	
	public boolean solve(String[] sudoku, int row, int col) {
		if (row == -1 && col == -1)
			return true;
		
		if (sudoku[row].charAt(col) == EMPTY) {
			for (int i = 1; i <= N; i++) {
				sudoku[row] = replace(sudoku[row], col, String.valueOf(i).charAt(0));
				if (valid(sudoku, row, col))
					if (solve(sudoku, nextRow(row, col), nextCol(row, col))) return true;
				sudoku[row] = replace(sudoku[row], col, EMPTY);
			}
		} else return solve(sudoku, nextRow(row, col), nextCol(row, col));
		
		return false;
	}
	
	public void print(String[] sudoku) {
		for (int i=0; i < sudoku.length; i++)
			for (int j=0; j < sudoku[i].length(); j++)
				if (j > 0 && j%G == 0) System.out.format(" %c", sudoku[i].charAt(j));
				else if (j == sudoku[i].length()-1) System.out.println(sudoku[i].charAt(j));
				else if (i > 0 && j==0 && i%G == 0) System.out.format("\n%c", sudoku[i].charAt(j));
				else System.out.print(sudoku[i].charAt(j));
	}
	
	private int nextRow(int row, int col) {
		if (row == N-1 && col == N-1) return -1;
		if (col == N-1) return row+1;
		return row;
	}
	
	private int nextCol(int row, int col) {
		if (row == N-1 && col == N-1) return -1;
		return (col+1)%N;
	}
	
	private boolean valid(String[] sudoku, int row, int col) {
		if (null == sudoku || row > sudoku.length-1 || col > sudoku[row].length()-1) return false;
		return validGroup(sudoku, row, col) && validRow(sudoku, row) && validCol(sudoku, col);
	}
	
	private boolean validGroup(String[] sudoku, int row, int col) {
		int rowGroup = row/G;
		int colGroup = col/G;
		HashSet<Character> unique = new HashSet<Character>();
		for (int i=0; i < G*G; i++) {
			Character c = sudoku[rowGroup*G+i/G].charAt(colGroup*G+i%G);
			if (!c.equals(EMPTY)) 
				if (unique.contains(c)) return false;
				else unique.add(c);
		}
		return true;
	}
	
	private boolean validCol(String[] sudoku, int col) {
		HashSet<Character> unique = new HashSet<Character>();
		for (int row=0; row < sudoku.length; row++) {
			Character c = sudoku[row].charAt(col);
			if (!c.equals(EMPTY))
				if (unique.contains(c)) return false;
				else unique.add(c);
		}
		return true;
	}
	
	private boolean validRow(String[] sudoku, int row) {
		HashSet<Character> unique = new HashSet<Character>();
		for (int col=0; col < sudoku[row].length(); col++) {
			Character c = sudoku[row].charAt(col);
			if (!c.equals(EMPTY))
				if (unique.contains(c)) return false;
				else unique.add(c);
		}
		return true;
	}
	
	public static void main(String[] args) {
		String[] sudoku = new String[] {
			"XX4XXX2XX",
			"1X3XXXXXX",
			"XXXX13XXX",
			"XXXX4XXX7",
			"81X2XXXX3",
			"XX51X8XXX",
			"5XXX76X2X",
			"X7X39XX6X",
			"XXXXXX1XX"
		};
		
		SudokuSolver sol = new SudokuSolver();
		if (sol.solve(sudoku, 0, 0)) sol.print(sudoku);
		else System.out.println("No solution\n");
	}

}
