package hackerrank;

import java.util.Arrays;
import java.util.Scanner;

public class CoinChange {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] input = new int[M];
		for (int i = 0; i < M; i++) input[i] = sc.nextInt();
		sc.close();
		
		long[][] r = new long[N+1][M+1];
		Arrays.fill(r[0], 1);
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++) { 
				r[i][j] += r[i][j-1];
				for (int k = 1; i >= k*input[j-1]; k++)
					r[i][j] += r[i-k*input[j-1]][j-1];
			}

		System.out.println(r[N][M]);
	}
}
