package cci;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Given an array of integers, find the contiguous sequence
 * with the largest sum and return sum.
 * 
 * Time complexity: O(n)
 * Space complexity: O(2n*sizeof(Integer))
 */
public class ContiguousSequence {

	public static int maxSum(int[] arr) {
		int[] best = new int[arr.length];
		Arrays.fill(best, Integer.MIN_VALUE);
		best[0] = arr[0];
		int max = Integer.MIN_VALUE;
		for (int i=1; i < arr.length; i++) {
			best[i] = Math.max(best[i-1]+arr[i], arr[i]);
			if (best[i] > max) max = best[i];
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int samples = sc.nextInt();
		while (samples-- > 0) {
			int size = sc.nextInt();
			int[] arr = new int[size];
			for (int i = 0; i < size; i++) arr[i] = sc.nextInt();
			System.out.printf("Max contiguous sequence sums to %d.\n", ContiguousSequence.maxSum(arr));
		}
		sc.close();
		

	}
}


//2
//6 2 -8 3 -2 4 -10
//6 -10 -2 -5 -7 -8 -9