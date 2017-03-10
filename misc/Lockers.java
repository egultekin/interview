package misc;

public class Lockers {
	
	public static void printSolution(int length, int width, int[] xCoord, int[] yCoord) {
		int[][] area = new int[width+1][length+1];
		for (int i = 1; i <= width; i++)
			for (int j = 1; j <= length; j++)
				area[i][j] = Integer.MAX_VALUE;
		
		for (int i = 1; i <= width; i++)
			for (int j = 1; j <= length; j++) {
				for (int locker = 0; locker < xCoord.length; locker++) {
					int dY = Math.abs(yCoord[locker]-i);
					int dX = Math.abs(xCoord[locker]-j);
					if (area[i][j] > dY+dX) area[i][j] = dX+dY;
				}
			}
		
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 1; i <= width; i++) {
			for (int j = 1; j <= length; j++)
				strBuilder.append(area[i][j]);
			strBuilder.append("\n");
		}
				
		System.out.println(strBuilder.toString());
		
	}

	public static void main(String[] args) {
		//printSolution(3, 5, new int[] { 1 } , new int[] { 1 });
		printSolution(7, 5, new int[] { 2, 4 }, new int[] { 3, 7 });
	}

}
