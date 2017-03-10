package floodfill;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

public class grafixMask {
    class Cell {
        int r;
        int c;
        public Cell(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        public int R() { return this.r; }
        public int C() { return this.c; }
        
        public Vector<Cell> next() {
        	Vector<Cell> cells = new Vector<Cell>(4);
        	if (isValid(this.r-1, this.c)) cells.add(new Cell(this.r-1, this.c));
        	if (isValid(this.r, this.c-1)) cells.add(new Cell(this.r, this.c-1));
        	if (isValid(this.r, this.c+1)) cells.add(new Cell(this.r, this.c+1));
        	if (isValid(this.r+1, this.c)) cells.add(new Cell(this.r+1, this.c));
        	
        	return cells;
        }
        
        private boolean isValid(int row, int column) {
        	if (row >=0 && row < HEIGHT && column >=0 && column < WIDTH) return true;
        	return false;
        }
    }
    
    private static final int HEIGHT = 400;
    private static final int WIDTH = 600;
    boolean[][] marked;
    boolean[][] blocked;
    
    public int[] sortedAreas(String[] rectangles) {
        blocked = this.blocked(rectangles);
        marked = new boolean[HEIGHT][WIDTH];
        Vector<Integer> holes = new Vector<Integer>();
        for (int i=0; i<HEIGHT; i++)
            for (int j=0; j<WIDTH; j++) {
            	if (!marked[i][j] && !blocked[i][j])
                    holes.add(dfs(i,j));
            }
        
        int[] result = new int[holes.size()];
        for (int i=0; i < holes.size(); i++) result[i] = holes.elementAt(i);
        Arrays.sort(result);
        
        return result;
    }
    
    private int dfs(int row, int column) {
    	LinkedList<Cell> queue = new LinkedList<Cell>();
    	int count = 0;
    	marked[row][column] = true;
    	queue.addLast(new Cell(row, column));
    	
    	while(!queue.isEmpty()) {
    		Cell c = queue.poll();
    		count++;
    		
    		for (Cell next:c.next()) {
    			if (!marked[next.R()][next.C()] && !blocked[next.R()][next.C()]) {
    				marked[next.R()][next.C()] = true;
    				queue.addLast(next);
    			}
    		}
    	}
    	
    	return count;       
    }
    
    private boolean[][] blocked(String[] rectangles) {
        if (null == rectangles || rectangles.length <= 0) throw new IllegalArgumentException("Invalid parameter.");
        boolean[][] blocked = new boolean[HEIGHT][WIDTH];
        for (String rectangle : rectangles) {
            Cell[] cells = this.rectangle(rectangle);
            for (int row=cells[0].R(); row<=cells[1].R(); row++)
                for (int col=cells[0].C(); col<=cells[1].C(); col++)
                blocked[row][col] = true;
        }
        
        return blocked;
    }
    
    private Cell[] rectangle(String rectangle) {
        Cell[] result = new Cell[2];
        String[] coords = rectangle.split(" ");
        // top-left
        result[0] = new Cell(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
        // bottom-right
        result[1] = new Cell(Integer.parseInt(coords[2]), Integer.parseInt(coords[3]));
        return result;
    }
}