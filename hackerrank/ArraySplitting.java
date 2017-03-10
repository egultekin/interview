package hackerrank;

import java.util.Arrays;
import java.util.Scanner;

public class ArraySplitting {
	static int partition(int[] arr, int i, long partitionSum, long allSum) {
		if (arr.length == 1) return 0;
		if (allSum == 0) return arr.length-1;
		int max = 0;
		long sum = partitionSum;
		for (int j = i; j < arr.length; j++) {
			if (arr[j] > 0 && (arr[j] + sum) == (allSum-sum-arr[j]))
				max = Math.max(max,
						Math.max(
								partition(Arrays.copyOf(arr, j+1), 0, 0, sum+arr[j]), 
								partition(Arrays.copyOfRange(arr, j+1, arr.length), 0, 0, allSum-sum-arr[j])
						)+1);
			sum += arr[j];
		}
		
		return max;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			int N = sc.nextInt();
			int[] A = new int[N];
			long sum = 0;
			for (int j = 0; j < N; j++) {
				A[j] = sc.nextInt();
				sum += A[j];
			}
			int points = partition(A, 0, 0, sum);
			System.out.println(points);
		}
		sc.close();

	}

}
