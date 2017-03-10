package dp;

import java.util.Arrays;

public class PlayGame {

	public int saveCaptures(int[] you, int[] computer) {
		int high=you.length-1;
		int max = 0;
		Arrays.sort(you);		
		Arrays.sort(computer);
		
		for (int i=computer.length-1; i >= 0; i--)
			if (computer[i] < you[high]) {
				max += you[high];
				high--;
			}
		
		return max;
	}
	
	public static void main(String[] args) {
		int[] you = new int[] {651, 321, 106, 503, 227, 290, 915, 549, 660, 115,
				 491, 378, 495, 789, 507, 381, 685, 530, 603, 394,
				 7, 704, 101, 620, 859, 490, 744, 495, 379, 781,
				 550, 356, 950, 628, 177, 373, 132, 740, 946, 609,
				 29, 329, 57, 636, 132, 843, 860, 594, 718, 849};
		int[] computer = new int[] {16, 127, 704, 614, 218, 67, 169, 621, 340, 319,
				 366, 658, 798, 803, 524, 608, 794, 896, 145, 627,
				 401, 253, 137, 851, 67, 426, 571, 302, 546, 225,
				 311, 111, 804, 135, 284, 784, 890, 786, 740, 612,
				 360, 852, 228, 859, 229, 249, 540, 979, 55, 82};
		
		System.out.println(new PlayGame().saveCaptures(you, computer));

	}

}
