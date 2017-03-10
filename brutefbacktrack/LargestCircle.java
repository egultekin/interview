package brutefbacktrack;

import java.util.Vector;

class Cell {
	private int x, y;
	
	public Cell(int x, int y) { this.x = x; this.y = y; }
	public int X() { return this.x; }
	public int Y() { return this.y; }
	
	@Override
	public boolean equals(Object obj) {
		try {
			Cell c = (Cell) obj;
			return c.X() == this.x && c.Y() == this.y;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Cell copy() {
		return new Cell(this.x, this.y);
	}
	
	public Cell move(int dX, int dY) {
		return new Cell(this.x+dX, this.y+dY);
	}
}

public class LargestCircle {
	
	
	
	private final static int BSIZE = 50;
	
	private boolean isValidMove(Cell cell, int dX, int dY, int hSize, int vSize) {
		if (null != cell && cell.X()+dX >= 0 && cell.X()+dX < hSize && cell.Y()+dY >= 0 && cell.Y()+dY < vSize)
			return true;
		return false;
	}

	private Vector<Cell> boundaries(int radius) {
		Vector<Cell> result = new Vector<Cell>();
		if (radius < 1 || radius > BSIZE/2) return result;
		int r2 = radius*radius;
		long dY = Math.round(Math.floor(Math.sqrt(r2-1)));
		for (int dX=1; dY >=0;) {
			int sqrDX = dX*dX;
			if (dX < radius) dY = Math.round(Math.floor(Math.sqrt(r2-sqrDX)));
			result.add(new Cell(dX-1, (int)-dY));
			result.add(new Cell(dX-1, (int)dY+1));
			result.add(new Cell(-dX, (int) dY+1));
			result.add(new Cell(-dX, (int) -dY));
			
			if (dX < radius) dX++;
			if (dX >= radius) dY--;
		}
		
		return result;
	}
	
	public int radius(String[] grid) {
		Vector<Vector<Cell>> boundaries = allBoundaries();
		
		int gridCols = grid[0].length();
		int gridSize = grid.length * gridCols;
		for (int i=gridCols/2; i > 0; i--) {
			Vector<Cell> boundaryMask = boundaries.get(i-1);

			for (int j=0; j < gridSize; j++) {
				int row = j / gridCols;
				int col = j % gridCols;
				Cell c = new Cell(row, col);
				int maskSize = boundaryMask.size();
				for (Cell move : boundaryMask)  {
					Cell validMove = validMove(c, move.X(), move.Y(), gridCols, grid.length);
					if (null == validMove || grid[validMove.Y()].charAt(validMove.X()) == '#') break;
					else maskSize--;
				}
				
				if (maskSize == 0) return i;
			}
		}
		
		return 0;
	}
	
	private Cell validMove(Cell c, int dX, int dY, int hSize, int vSize) {
		if (!isValidMove(c, dX, dY, hSize, vSize)) return null;
		return c.move(dX, dY);
	}
	
	private Vector<Vector<Cell>> allBoundaries() {
		int size = BSIZE/2;
		Vector<Vector<Cell>> result = new Vector<Vector<Cell>>();
		
		for (int i=1; i<=size; i++) {
			result.add(boundaries(i));
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String[] grid = new String[]     	
				{ ".........................#........................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  "..................................................",
				  ".................................................." };

		int result = new LargestCircle().radius(grid);
		
		System.out.format("Largest radius is %d.\n", result);
	}

}
