package brutefbacktrack;

public class RatInMaze {
	private int[] moveX = { 1, 0 };
	private int[] moveY = { 0, 1 };
	
	private boolean findWays(int[][] maze, int row, int col, int[][] sol) {
		if (row == maze.length-1 && col == maze[row].length - 1) return true;
		for (int i = 0; i < moveX.length; i++) {
			int nextRow = row + moveY[i];
			int nextCol = col + moveX[i];
			if (valid(maze, nextRow, nextCol)) {
				sol[nextRow][nextCol] = 1;
				if (!findWays(maze, nextRow, nextCol, sol)) sol[nextRow][nextCol] = 0;
				else return true;
			}
		}
		
		return false;
	}
	
	private void printSolution(int[][] sol) {
		for (int i=0; i < sol.length; i++)
			for (int j=0; j < sol[i].length; j++)
				if (j == sol[i].length - 1) System.out.println(sol[i][j]);
				else System.out.format("%d ", sol[i][j]);
	}
	
	
	private boolean valid(int[][] sol, int row, int col) {
		if (null == sol || row < 0 || row > sol.length - 1 || col < 0 || col > sol[row].length - 1) return false;
		return sol[row][col] == 1;
	}
	
	public void start(int[][] maze) {
		int[][] sol = new int[maze.length][maze[0].length];
		sol[0][0]=1;
		if (findWays(maze, 0, 0, sol)) printSolution(sol);
		else System.out.println("No solution found.");
		
	}

	public static void main(String[] args) {
		int[][] maze = new int[][] {
				{ 1, 0, 0, 0 }, 
				{ 1, 1, 1, 1 },
				{ 0, 1, 0, 1 },
				{ 1, 1, 1, 0 },
				{ 1, 0, 1, 1 }};
		new RatInMaze().start(maze);
	}

}
