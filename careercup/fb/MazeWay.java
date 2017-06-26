package careercup.fb;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

class MazeWay {
	static class Point {
		int r, c;
		public Point(int row, int col) {
			r = row;
			c = col;
		}
		
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return super.hashCode();
		}

		List<Point> neighbours(char[][] maze, char forbid, boolean[][] marked) {
			List<Point> list = new Vector<Point>();
			if (null != maze) {
				int[] dr =  {-1, 0, 0, 1};
				int[] dc =  {0, -1, 1, 0};
				int height = maze.length;
				int width = maze[0].length;
				for (int i=0; i < dr.length; i++) {
					int nRow = dr[i] + r;
					int nCol = dc[i] + c;
					if (nRow >= 0 && nRow < height && nCol >= 0 && nCol < width 
							&& maze[nRow][nCol] != forbid && !marked[nRow][nCol]) {
						Point p = new Point(nRow, nCol); 
						list.add(p);
					}
				}
			}
			return list;
		}
	}

	public boolean isThereAWay(char[][] maze, int sRow, int sCol, char stop) {
		LinkedList<Point> queue = new LinkedList<>();
		boolean[][] marked = new boolean[maze.length][maze[0].length];
		Point start = new Point(sRow, sCol);

		marked[sRow][sCol] = true;
		queue.addLast(start);
		while(!queue.isEmpty()) {
			Point current = queue.pollFirst();
			if (maze[current.r][current.c] == stop) return true;
			for (Point next : current.neighbours(maze, 'X', marked)) {
				if (!marked[next.r][next.c]) {
					queue.add(next);
					marked[next.r][next.c] = true;
				}
			}	
		} 
		return false;
	}
	
	public static void main(String[] args) {
		MazeWay mw = new MazeWay();
		char[][] maze = new char[][] {
			new char[] { 'X', '_', '_', 'R', '_', 'X' },
			new char[] { 'X', '_', 'X', 'X', 'X', '_' },
			new char[] { '_', '_', '_', '_', '_', '_' },
			new char[] { '_', 'X', 'X', '_', 'X', 'X' },
			new char[] { 'X', 'T', '_', '_', 'X', '_' }
		};
		System.out.println(mw.isThereAWay(maze, 0, 3, 'T'));
	}
}

