package hackerrank;

import java.util.Scanner;

public class ConnectedCellInGrid {

	static int max = 0;
	static boolean[][] marked;
	static int[][] grid;
	static int rows, cols;
	static int[][] dir = { {-1, -1, -1, 0, 0, 1, 1, 1}, {-1, 0, 1, -1, 1, -1, 0, 1} };
	
	static int dfs(int row, int col) {
		marked[row][col] = true;
		int size = 0;
		
		for (int i = 0; i < 8; i++) {
			int dr = dir[0][i];
			int dc = dir[1][i];
			if (row + dr >= 0 && row+dr < rows && col+dc >= 0 && col+dc < cols && !marked[row+dr][col+dc] && grid[row+dr][col+dc] > 0)
				size += dfs(row+dr, col+dc);
		}
		
		if (grid[row][col] == 1) size++;
		if (size > max) max = size;
		
		return size;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		rows = sc.nextInt();
		cols = sc.nextInt();
		grid = new int[rows][cols];
		marked = new boolean[rows][cols];
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++) grid[r][c] = sc.nextInt();
		sc.close();

		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++)
				if (!marked[r][c]) dfs(r,c);
		
		System.out.println(max);
	}

}
