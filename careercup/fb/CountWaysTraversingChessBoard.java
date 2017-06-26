package careercup.fb;

import java.util.Scanner;

public class CountWaysTraversingChessBoard {
	
	public static int count(int N) {
		int[][] A = new int[N][N];
		A[0][0] = 1;
		for (int i=0; i < N; i++)
			for (int j=0; j < N; j++) {
				if (i == 0 && j == 0) continue;
				int top = 0, left = 0;
				if (i > 0) top = A[i-1][j];
				if (j > 0) left = A[i][j-1];
				A[i][j] = top+left;
			}
		return A[N-1][N-1];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while (T-- > 0) System.out.println(count(sc.nextInt()));
		sc.close();

	}

}
