package dp;

import java.util.HashSet;

public class AvoidRoads {
	private long[][] ways;
	private boolean[][] marked;
	private HashSet<String> blocked;
	private int[] moveX = { 0, -1 };
	private int[] moveY = { -1, 0 };
	
	public long numWays(int width, int height, String[] bad) {
		blocked = blocked(bad);
		ways = new long[height+1][width+1];
		marked = new boolean[height+1][width+1];
		ways[0][0] = 1;
		
		for (int i=0; i < width+height; i++) {
			for (int j=0; j <= height; j++)
				for (int k=0; k <= width; k++) {
					if (!marked[j][k]) {
						marked[j][k] = true;
						for (int move=0; move < 2; move++)
							if (available(j, k, moveY[move], moveX[move]) && marked[j+moveY[move]][k+moveX[move]]) {
								ways[j][k] += ways[j+moveY[move]][k+moveX[move]];
							}
					}
				}
		}
		
		return ways[height][width];
	}
	
	private HashSet<String> blocked(String[] bad) {
		HashSet<String> result = new HashSet<String>(bad.length);
		for (int i=0; i < bad.length; i++) {
			String[] split = bad[i].split(" ");
			StringBuilder builder = new StringBuilder();
			if (split[0].compareTo(split[2]) > 0 || split[1].compareTo(split[3]) > 0) {
				builder.append(split[2]);
				builder.append(split[3]);
				builder.append(split[0]);
				builder.append(split[1]);
			} else {
				builder.append(split[0]);
				builder.append(split[1]);
				builder.append(split[2]);
				builder.append(split[3]);
			}
			result.add(builder.toString());
		}
		return result;
	}
	
	private boolean available(int r, int c, int moveY, int moveX) {
		int row = r+moveY;
		int col = c+moveX;
		if (row >= 0 && row < ways.length && col >=0 && col < ways[row].length) return !blocked.contains(blocked(c, r, moveX, moveY));
		return false;
	}
	
	private String blocked(int c, int r, int moveX, int moveY) {
		StringBuilder builder = new StringBuilder();
		builder.append(c+moveX);
		builder.append(r+moveY);
		builder.append(c);
		builder.append(r);
		return builder.toString();
	}

	public static void main(String[] args) {
		String[] blocked = {};
		
		System.out.println(new AvoidRoads().numWays(2, 2, blocked));

	}

}
