package misc;

public class ZigZagDP {

	private int[] increasing,  decreasing;
	
	public int longestZigZag(int[] input) {
		if (null == input || input.length <= 0) return 0;
		
		increasing = new int[input.length];
		decreasing = new int[input.length];
		
		for (int i=0; i < input.length; i++) decreasing[i] = increasing[i] = 1;
		
		for (int current=1; current < input.length; current++)
			for (int j=0; j < current; j++) {
				if (input[current] > input[j])
					// increasing				
					increasing[current] = max(decreasing[j]+1, increasing[current]);
				else if (input[current] < input[j])
					//decreasing
					decreasing[current] = max(increasing[j]+1, decreasing[current]);
			}
		
		int maxDecreasing = Integer.MIN_VALUE;
		int maxIncreasing = Integer.MIN_VALUE;
		for (int i=0; i < input.length; i++) {
			maxIncreasing = max(maxIncreasing, increasing[i]);
			maxDecreasing = max(maxDecreasing, decreasing[i]);
		}
		
		return max(maxIncreasing, maxDecreasing);
	}
	
	private int max(int i, int j) {
		if (i > j) return i;
		return j;
	}
	
	public static void main(String[] args) {
		int[] input = new int[] {
				1,7,4,9,2,5
		};
		
		int[] input1 = new int[] {
				1,17,5,10,13,15,10,5,16,8
		};
		
		int[] input2 = new int[] {
				44
		};
		
		int[] input3 = new int[] {
				1,2,3,4,5,6,7,8,9
		};
		
		int[] input4 = new int[] {
				70,55,13,2,99,2,80,80,80,80,100,19,7,5,5,5,1000,32,32
		};
		
		int[] input5 = new int[] {
				374,40,854,203,203,156,362,279,812,955,
				600,947,978,46,100,953,670,862,568,188,
				67,669,810,704,52,861,49,640,370,908,
				477,245,413,109,659,401,483,308,609,120,
				249,22,176,279,23,22,617,462,459,244
		};
		
		ZigZagDP sol = new ZigZagDP();
		
		System.out.format("Longest zigzag sequence input is %d long.\n", sol.longestZigZag(input));
		System.out.format("Longest zigzag sequence input1 is %d long.\n", sol.longestZigZag(input1));
		System.out.format("Longest zigzag sequence input2 is %d long.\n", sol.longestZigZag(input2));
		System.out.format("Longest zigzag sequence input3 is %d long.\n", sol.longestZigZag(input3));
		System.out.format("Longest zigzag sequence input4 is %d long.\n", sol.longestZigZag(input4));
		System.out.format("Longest zigzag sequence input5 is %d long.\n", sol.longestZigZag(input5));
	}

}
