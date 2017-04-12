package careercup.amazon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import commontypes.TablePosition;

/*
 * https://www.careercup.com/question?id=5719764526694400
 */
public class LeastMovesFromLocation {
	
	public static final char DOOR = 'D';
	public static final char WALL = 'W';
	
	public static final int[] ROW = { 0, 1, -1, 0 };
	public static final int[] COL = { 1, 0, 0, -1 };
	
	public static int leastMoves(char[][] maze, int row, int col) {
		int rows = maze.length;
		if (rows == 0) return Integer.MAX_VALUE;
		int cols = maze[0].length;
		if (cols == 0) return Integer.MAX_VALUE;
		if (row < 0 || row >= rows || col < 0 || col >= cols) return Integer.MAX_VALUE;
		if (maze[row][col] == WALL) return Integer.MAX_VALUE;
		
		int[][] shortest = new int[rows][cols];
		boolean[][] marked = new boolean[rows][cols];
		LinkedList<TablePosition> queue = new LinkedList<>();
		LinkedList<TablePosition> doors = new LinkedList<>();
		for (int i = 0; i < rows; i++) Arrays.fill(shortest[i], Integer.MAX_VALUE);
		shortest[row][col] = 0;
		marked[row][col] = true;
		queue.add(new TablePosition(row, col));
		while (!queue.isEmpty()) {
			TablePosition c = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nextRow = c.R()+ROW[i];
				int nextCol = c.C()+COL[i];
				if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
					if (maze[nextRow][nextCol] != WALL) {
						shortest[nextRow][nextCol] = Math.min(shortest[nextRow][nextCol], shortest[c.R()][c.C()]+1);
						if (!marked[nextRow][nextCol]) {
							queue.add(new TablePosition(nextRow, nextCol));
							if (maze[nextRow][nextCol] == DOOR) doors.add(new TablePosition(nextRow, nextCol));
						}
					}
					marked[nextRow][nextCol] = true;
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		while (!doors.isEmpty()) {
			TablePosition pos = doors.poll();
			if (shortest[pos.R()][pos.C()] < min) min = shortest[pos.R()][pos.C()];
		}
		
		return min;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int rows = sc.nextInt();
		int cols = sc.nextInt();
		int row = sc.nextInt();
		int col = sc.nextInt();
		char[][] maze = new char[rows][];
		for (int i = 0; i < rows; i++)
			maze[i] = sc.next().toCharArray();
		sc.close();

//		int row = (int)(rows*Math.random());
//		int col = (int)(cols*Math.random());
		System.out.printf("Row: %d Col: %d Moves: %d\n", row, col, leastMoves(maze, row, col));
	}

}
