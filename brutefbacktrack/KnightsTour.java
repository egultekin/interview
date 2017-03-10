package brutefbacktrack;

public class KnightsTour {
	
	private static final int N = 8;
	private int[] moveX = { -2, -2, -1, -1, 1, 1, 2, 2 };
	private int[] moveY = { -1, 1, -2, 2, -2, 2, -1, 1 };
	private int found = 0;
	
	private boolean valid(int[][] sol, int row, int col) {
		if (row < 0 || row > N-1 || col < 0 || col > N-1) return false;
		return sol[row][col]==-1;
	}
	
	private boolean solveRec(int step, int row, int col, int[][] sol) {
		if (step == N*N) return true;
		for (int i=0; i < N; i++) {
			int nextRow = row+moveY[i];
			int nextCol = col+moveX[i];
			if (valid(sol, nextRow, nextCol)) {
				sol[nextRow][nextCol] = step;
				if(solveRec(step+1, nextRow, nextCol, sol)) 
					return true;
				else sol[nextRow][nextCol] = -1;
			}
		}
		
		return false;
	}
	
	private void printSolution(int[][] sol) {
		System.out.format("Solution %d:\n", ++found);
		for(int i=0; i < sol.length; i++)
			for(int j=0; j < sol[i].length; j++) 
				if (j == sol[i].length - 1) System.out.format(" %2d\n", sol[i][j]);
				else if (j == 0) System.out.format("%2d", sol[i][j]);
				else System.out.format(" %2d", sol[i][j]);
		System.out.println();
	}
	
	public void solve() {
		int[][] sol = new int[N][N]; 
		for (int i=0; i < N; i++)
			for (int j=0; j < N; j++)
				sol[i][j] = -1;
		
		sol[0][0]=0;
		if (solveRec(1, 0, 0, sol)) printSolution(sol);
		else System.out.println("No solution found.");
	}

	public static void main(String[] args) {
		System.out.format("Knights tour in a %dX%d square:\n", N, N);
		new KnightsTour().solve();
	}

}
