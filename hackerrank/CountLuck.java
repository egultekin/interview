package hackerrank;

import java.util.Scanner;

public class CountLuck {
	static boolean[][] marked;
	static int tR, tC, sR, sC;
	static int rows, cols;
	static int[][] grid;
	static int[][] dir = { {-1, 0, 0, 1}, { 0, -1, 1, 0} };
	static boolean found;
	
	static int dfs(int row, int col, int count) {
		marked[row][col] = true;
		if (row == tR && col == tC) found = true;
		
		if (!found) {
			int ways = 0;
			for (int i = 0; i < 4; i++) {
				int dR = dir[0][i];
				int dC = dir[1][i];
				if (row+dR >= 0 && row+dR < rows && col+dC >= 0 && col+dC < cols && !marked[row+dR][col+dC] && grid[row+dR][col+dC] != -1) {
					ways++;
					if (!found) count = dfs(row+dR, col+dC, count);
					if (found && ways > 1) return count+1;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			rows = sc.nextInt();
			cols = sc.nextInt();
			
			grid = new int[rows][cols];
			for (int r = 0; r < rows; r++) {
				String maze = sc.next();
				for (int c = 0; c < cols; c++) {
					if (maze.charAt(c) == '*') {
						tR = r;
						tC = c;
						grid[r][c] = 2;
					} else if (maze.charAt(c) == 'M') {
						sR = r;
						sC = c;
						grid[r][c] = 0;
					} else if (maze.charAt(c) == 'X') grid[r][c] = -1;
					else grid[r][c] = 1;
				}	
			}
			int K = sc.nextInt();
			marked = new boolean[rows][cols];
			found = false;
			if (dfs(sR, sC, 0) == K) 
				System.out.println("Impressed");
			else System.out.println("Oops!");
		}
		sc.close();
	}

}
