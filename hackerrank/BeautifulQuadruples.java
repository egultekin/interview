package hackerrank;

import java.util.Arrays;
import java.util.Scanner;

public class BeautifulQuadruples {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] limits = new int[4];
		for (int i = 0; i < 4; i++) limits[i] = sc.nextInt();
		sc.close();
		
		Arrays.sort(limits);
		int[] allPairs = new int[3001];
		for (int i = 1; i <= limits[0]; i++)
			for (int j = i; j <= limits[1]; j++)
				allPairs[j]++;
		for (int i = 2; i <= 3000; i++)
			allPairs[i] += allPairs[i-1];
		
		int[][] allXOR = new int[3001][4096];
		for (int i = 1; i < limits[0]; i++)
			for (int j = i; j < limits[1]; j++)
				allXOR[j][i^j]++;
		
		for (int i = 1; i <= 3000; i++)
			for (int j = 0; j < 4096; j++)
				allXOR[i][j]+=allXOR[i-1][j];

		long res = 0;
		for (int i = 1; i <= limits[2]; i++)
			for (int j = i; j <= limits[3]; j++)
				res += allPairs[i] - allXOR[i][i^j];
		
		System.out.println(res);
	}

}
