package leetcode;

import java.util.Scanner;

public class BoggleGame {
	private final int[] dr = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
	private final int[] dc = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };
	private char[][] g;

	private boolean dfs(int r, int c, String s, int i, boolean[][] v) {
		if (g[r][c] != s.charAt(i)) return false;
		if (i == s.length()-1) return true;
		v[r][c] = true;
		boolean found = false;
		for (int ai=0; ai < 8; ai++) {
			int nr = r+dr[ai];
			int nc = c+dc[ai];
			if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && !v[nr][nc]) {
				found = dfs(nr, nc, s, i+1, v);
				if (found) break;
				v[nr][nc] = false;
			}
		}
		return found;
	}
	
	public boolean find(String s, char[][] gr) {
		boolean found = false;
		g = gr;
		if (s == null || s.length() == 0) return false;
		for (int r=0; r < 4; r++)
			for (int c=0; c < 4; c++)
				if (g[r][c] == s.charAt(0)) {
					boolean[][] v = new boolean[4][4];
					found = dfs(r, c, s, 0, v);
					if (found) return true;
				}
		return found;
	}
	
	public static void main(String[] args) {
		char[][] g = new char[][] {
			new char[] { 'T', 'E', 'S', 'T' },
			new char[] { 'E', 'S', 'T', 'Q' },
			new char[] { 'P', 'E', 'N', 'R' },
			new char[] { 'C', 'D', 'F', 'G' }
		};
		
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		sc.close();
		
		BoggleGame bg = new BoggleGame();
		if (bg.find(s, g)) System.out.printf("String %s exists in the game.", s);
		else System.out.println("String not found.");

	}

}
