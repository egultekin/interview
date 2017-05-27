package leetcode;

public class ContainerWithMostWater {

	public int maxArea(int[] height) {
		int[][] area = new int[height.length][height.length];
		int max = 0;
		for (int l=2; l < height.length; l++)
			for (int j=0; j+l < height.length; j++)
				if (height[j] > height[j+1] && height[j+l] >= height[j]) {
					area[j][l] = area[j+1][l-1]+(l-1)*(height[j]-height[j+1]);
					if (max < area[j][l]) max = area[j][l];
				}
		
		return max;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
