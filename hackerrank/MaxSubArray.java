package hackerrank;

import java.util.Scanner;

public class MaxSubArray {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			long nonContiguous = 0;
			for (int j = 0; j < N; j++) {
				arr[j] = sc.nextInt();
				if (arr[j] > 0) nonContiguous+=arr[j];
			}
			long[] best = new long[N];
			best[0] = arr[0];
			long max = best[0];
			for (int j = 1; j < N; j++) { 
				if (best[j-1] > 0) best[j] = best[j-1]+arr[j];
				else best[j] = arr[j];
				if (best[j] > max) max = best[j];
			}
			
			if (nonContiguous == 0) nonContiguous = max;
			System.out.printf("%d %d\n", max, nonContiguous);
		}
		sc.close();
	}

}
