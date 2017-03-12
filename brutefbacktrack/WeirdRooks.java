package brutefbacktrack;

import java.util.Arrays;
import java.util.Vector;

public class WeirdRooks {
	private int[] columnsInRow;
		
	public String describe(int[] cols) {
		StringBuilder result = new StringBuilder();
		this.columnsInRow = cols;
		int squares = count(cols);
		boolean[] rooks = new boolean[squares];
		
		Vector<Integer> positions = new Vector<Integer>();
		int positionCount = findPositions(positions, rooks, 0);
		
		int rook = 1;
		while (positionCount > 0) {

			Integer[] arr = new Integer[positions.size()];
			arr = positions.toArray(arr);
			Arrays.sort(arr);
			
			for (int i = 0; i < arr.length; i++) {
				result.append(rook-1);
				result.append(",");
				result.append(arr[i]);
				result.append(" ");
			}
			
			positions.clear();
			positionCount = findPositions(positions, rooks, rook++);
		}
		
		return result.toString();
	}
	
	private int findPositions(Vector<Integer> positions, boolean[] rooks, int place) {
		int count = 0;
		if (place == 0) {
			count = countAvailable(rooks);
			if (positions.indexOf(count) == -1) positions.add(count);
			return count;
		}
		else {
			for (int i = 0; i < rooks.length; i++) {
				if (isAvailable(rooks, i)) {
					boolean[] cRooks = rooks.clone();
					cRooks[i] = true;
					count = findPositions(positions, cRooks, place-1);
				}
			}
		}
		return count;
	}
	
	private boolean isAvailable(boolean[] rooks, int square) {
		if (rooks[square]) return false;
		int rowBegins = 0, row = 0;
		while (rowBegins + this.columnsInRow[row] <= square) {
			rowBegins += this.columnsInRow[row];
			row++;
		}
		
		for (int i = square; i < rowBegins+this.columnsInRow[row] && i < rooks.length; i++)
			if (rooks[i]) return false;
		
		for (int i = rowBegins + this.columnsInRow[row]; i < rooks.length && row < columnsInRow.length; ) {
			row++;
			if (row < columnsInRow.length) {
				if (columnsInRow[row] > square-rowBegins && rooks[i+square-rowBegins]) return false;
				i = i + columnsInRow[row];
			}
		}
		
		return true;
	}
	
	private int countAvailable(boolean[] rooks) {
		int c = 0;
		for (int i = 0; i < rooks.length; i++)
			if (isAvailable(rooks, i)) 	c++;
		return c;
	}
	
	private int count(int[] cols) {
		int c = 0;
		for (int i = 0; i < cols.length; i++) c += cols[i];
		return c;
	}

	public static void main(String[] args) {
		int[] cols = {3,3,3};
		
		System.out.println(new WeirdRooks().describe(cols));

	}

}
