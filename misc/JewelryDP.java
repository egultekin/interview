package interview;

import java.util.Arrays;
import java.util.Comparator;

public class JewelryDP {

	private static final int MAX_JEWELS = 30;
	private static final int MAX_VALUE = 10;
	private static final int MAX_SUM = MAX_JEWELS * MAX_VALUE;
	// long[S][i] : distinct ways of distributing jewels with indices [0..i] totaling a sum S
	private long[][] waysBelow;
	// long[S][i] : distinct ways of distributing jewels with indices (i..MAX_JEWELS) totaling a sum S
	private long[][] waysAbove;
	
	public long findWays(Integer[] jewels) {
		
		
		
		return 0;
	}
	
	public long[][] waysUpTo(Integer[] jewels) {
		int sumsUpperBound = jewels.length*MAX_VALUE;
		long[][] result = new long[sumsUpperBound+1][jewels.length+1];
		for (int i=0; i<sumsUpperBound+1; i++)
			for (int j=0; j<jewels.length+1; j++)
				result[i][j] = 0;
		
		result[0][0] = 1;
		for (int i=0; i<sumsUpperBound+1; i++)
			for (int j=1; j<jewels.length+1; j++) {
				result[i][j] = result[i][j-1];
				if (i >= jewels[j-1]) result[i][j] += result[i-jewels[j-1]][j-1];
			}
		
		return result;
	}
	
	private int[][] choose(Integer[] jewels) {
		int[][] result = new int[jewels.length+1][jewels.length+1];
		result[0][0] = 1;
		for (int j=1; j < jewels.length+1; j++) result[0][j]=0;
		for (int i=1; i < jewels.length+1; i++) {
			result[i][0]=1;
			for (int j=1; j < jewels.length+1; j++)
				result[i][j] = result[i-1][j-1]+result[i-1][j];
		}
		
		return result;
	}
	
	public long ways(Integer[] jewels) {
		int sumUpperBound = jewels.length*MAX_VALUE;
		Integer[] ascending = Arrays.copyOf(jewels, jewels.length);
		Arrays.sort(ascending, ascending());
		Integer[] descending = Arrays.copyOf(jewels, jewels.length);
		Arrays.sort(descending, descending());
		
		int[][] choose = choose(jewels);
		long[][] waysBelow = waysUpTo(ascending);
		long[][] waysAbove = waysUpTo(descending);
//		long[][] ways = new long[sumUpperBound+1][jewels.length+1];
		long total = 0;
		
//		for (int i=0; i < jewels.length+1; i++)
//			for (int sum=0; sum<sumUpperBound+1; sum++)
//				ways[sum][i] = 0;
		
		int done=0;
		while (done < jewels.length) {
			int lastGroupIndex=done;
			while (lastGroupIndex < jewels.length && ascending[lastGroupIndex] == ascending[done]) lastGroupIndex++;
			int groupSize = lastGroupIndex - done;
			for (int g=1; g <= groupSize; g++) {
				int groupSum = g*ascending[done];
				for (int sum=groupSum; sum < sumUpperBound+1; sum++)
					total += waysBelow[sum-groupSum][done] * waysAbove[sum][jewels.length-done-g] * choose[groupSize][g];
			}
			done=lastGroupIndex;
		}
		
//		for (int i=1; i < jewels.length+1; i++)
//			for (int sum=1; sum<sumUpperBound+1; sum++)
////				if (sum >= ascending[i-1]) ways[sum][i] = waysBelow[sum-ascending[i-1]][i-1]*waysAbove[sum][jewels.length-i];
//				if (sum >= ascending[i-1]) total += waysBelow[sum-ascending[i-1]][i-1]*waysAbove[sum][jewels.length-i];
		
//		for (int sum=1; sum<sumUpperBound+1; sum++) 
//			for (int i=1; i < jewels.length+1; i++) {
//				if (ways[sum][i] > 0) {
//					System.out.format("There are %d ways for sum %d.\n", ways[sum][i], sum);
//					total += ways[sum][i];
//			}
//		}
		
		return total;
	}
	

	

	
	public static void main(String[] args) {
		Integer[] jewels = new Integer[] {1,2,5, 3,4, 5};

		JewelryDP sol = new JewelryDP();
		System.out.println("Total number of ways is: " + sol.ways(jewels));
//		Arrays.sort(jewels, ascending());
//		long[][] result = sol.waysUpTo(jewels);
//		print(result);
	}
	
	public static void print(long[][] result) {
		if (null == result) throw new IllegalArgumentException("Result shall not be empty.");
		for (int i=1; i < result[1].length; i++) {
			System.out.format("For index %d:\n", i);
			for (int sum=1; sum < result.length; sum++) 
				if (result[sum][i] > 0) System.out.format("Sum %d in %d ways.\n", sum, result[sum][i]);
		}
	}
	
	public static Comparator<Integer> descending() {
		return new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		};
	}
	
	public static Comparator<Integer> ascending() {
		return new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
	}

}
