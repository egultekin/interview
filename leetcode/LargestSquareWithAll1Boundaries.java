package leetcode;

public class LargestSquareWithAll1Boundaries {

	public static int length(int[][] matrix) {
		if (null == matrix || matrix.length == 0) return -1;
		int nr = matrix.length;
		int nc = matrix[0].length;
		int[][] ltor = new int[nr][nc];
		for (int r=0; r < nr; r++)
			for (int c = nc-1; c >= 0; c--) {
				ltor[r][c] = matrix[r][c] == 0 ? 0 
						: (c == nc-1) ? 1 : 1+ltor[r][c+1];
			}
		
		int[][] ttob = new int[nr][nc];
		for (int r=nr-1; r >= 0; r--)
			for (int c = 0; c < nc; c++) {
				ttob[r][c] = matrix[r][c] == 0 ? 0
						: (r == nr-1) ? 1 : 1+ttob[r+1][c];
			
			}
		
		int maxR = -1, maxC = -1;
		int max = Integer.MIN_VALUE;
		for (int r=0; r < nr; r++)
			for (int c=0; c < nc; c++) {
				int squareSize = max(ltor, ttob, r, c, nr, nc);
				if (squareSize > max) {
					max = squareSize;
					maxR = r;
					maxC = c;
				}
			}
		
		System.out.printf("Max square has a side length of %d and is located at (%d,%d)\n", max, maxR, maxC);
		return max;
	}
	
	private static int max(int[][] ltor, int[][] ttob, int r, int c, int nr, int nc) {
		if (ltor[r][c] == 0 || ttob[r][c] == 0) return 0;
		int min = Math.min(ltor[r][c], ttob[r][c]);
		int k = min;
		for (; k >= 2; k--) {
			if (r+k-1 < nr && c+k-1 < nc && ltor[r+k-1][c] >= k && ttob[r][c+k-1] >= k) break;
		}
		return k;
	}
	
	public static void main(String[] args) {
		LargestSquareWithAll1Boundaries.length(new int[][] {
			{0, 1, 0, 1, 1},
			{0, 1, 1, 1, 0},
			{0, 1, 1, 1, 1},
			{1, 1, 0, 1, 1},
			{1, 1, 1, 1, 1}
		});
	}

}
