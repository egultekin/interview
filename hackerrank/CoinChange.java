package hackerrank;

import java.util.Arrays;
import java.util.Scanner;

public class CoinChange {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[M];
		for (int i = 0; i < M; i++)	arr[i] = sc.nextInt();
		sc.close();

		//Arrays.sort(arr);
		long[][] result = new long[N+1][M];
		Arrays.fill(result[0], 1);
		for (int i = 1; i <= N; i++)
			for (int j = 0; j < M; j++) {
				if (j > 0) result[i][j] = result[i][j-1];
				if (arr[j] == i) result[i][j] += 1;
				else if (arr[j] < i) result[i][j] += (j > 0 ? result[i-arr[j]][j-1] : 0)+((i % arr[j]) == 0 ? 1 : 0);
			}
		
		System.out.println(result[N][M-1]);
	}

}
