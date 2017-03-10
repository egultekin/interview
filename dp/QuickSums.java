package dp;

class TestCase {
	int sum;
	String numbers;
	String name;
	
	public TestCase(int order, String n, int s) {
		numbers = n;
		sum = s;
		name = "Test case " + order;
	}
	
	public int sum() {
		return sum;
	}
	
	public String name() {
		return name;
	}
	
	public String numbers() {
		return numbers;
	}
}

public class QuickSums {
	private static final int M = 100;
	
	public int minSums(String numbers, int sum) {
		if (null == numbers  || numbers.length() == 0 || sum < 1 || sum > 100)
			throw new IllegalArgumentException("Invalid parameter found.");
		
		int n =  numbers.length();
		int[][] min = new int[M+1][n+1];
		init(min, Integer.MAX_VALUE);
		
		min[0][0] = -1;
		for (int index=1; index < n+1; index++)
			for (int s=1; s < M+1; s++) {
				int len=1;
				int stv=0;
				while (index >= len && sum >= stv) {
					stv = strVal(numbers, index-len, len);
					if (s >= stv) min[s][index] = Math.min(incr(min[s-stv][index-len]), min[s][index]);
					len++;
				}
			}
		if (min[sum][numbers.length()] == Integer.MAX_VALUE) return -1;
		return min[sum][numbers.length()];					
	}
	
	private void init(int[][] arr, int value) {
		if (null != arr) {
			for (int i=0; i<arr.length; i++)
				for (int j=0; j<arr[i].length; j++)
					arr[i][j] = value;
		}
	}
	
	private int strVal(String str, int start, int length) {
		if (null == str || str.length() < start+length)
			throw new IllegalArgumentException("Invalid parameter found in strVal.");
		
		return Integer.parseInt(str.substring(start, start+length));
	}
	
	private int incr(int value) {
		if (value == Integer.MAX_VALUE) return Integer.MAX_VALUE;
		return value+1;
	}
	
	public static void main(String[] args) {
		TestCase tc1 = new TestCase(1, "99999", 45);
		TestCase tc2 = new TestCase(2, "1110", 3);
		TestCase tc3 = new TestCase(3, "0123456789", 45);
		TestCase tc4 = new TestCase(4, "99999", 100);
		TestCase tc5 = new TestCase(5, "382834", 100);
		TestCase tc6 = new TestCase(6, "9230560001", 71);
		
		TestCase[] tc = new TestCase[] { tc1, tc2, tc3, tc4, tc5, tc6 };
		for (TestCase testCase : tc) {
			System.out.format("%s: Minimum number of additions required for sum %d is %d.\n", 
					testCase.name(),
					testCase.sum(), 
					new QuickSums().minSums(testCase.numbers(), testCase.sum()));
		}
	}

}
