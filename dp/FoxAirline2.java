package dp;

import java.util.Arrays;

public class FoxAirline2 {
	long[][] ways;
	
	private boolean direct(int[] a, int[] b, int i, int j) {
		for (int k = 0; k < a.length; k++)
			if (a[k] == i && b[k] == j
			|| a[k] == j && b[k] == i)	return true;
		
		return false;
	}
	
	private long[][] copy(long[][] source) {
		long[][] result = new long[source.length][0];
		for (int i = 0; i < source.length; i++)
			result[i] = Arrays.copyOf(source[i], source[i].length);
		return result;
	}
	
	public String isPossible(int n, int[] a, int[] b) {
		ways = new long[n][n];
		for (int i = 0; i < a.length; i++)
			if (direct(a, b, a[i], b[i])) {
				ways[b[i]][a[i]]++;
				ways[a[i]][b[i]]++;
			}
		
		long[][] temp = copy(ways);
		for (int steps = 0; steps < n-2; steps++) {
			for (int i=0; i < n; i++)
				for (int k=0; k < n; k++)
				for (int j=0; j < n; j++)
						if (k != j && i != k && i != j &&
						ways[k][i]*ways[i][j] > 0) temp[k][j] += Math.min(ways[k][i], ways[i][j]);
			ways = copy(temp);
		}
		
//		for (int k = 0; k < n; k++)
//			for (int i=0; i < n; i++)
//				for (int j=0; j < n; j++)
//					if (ways[i][j]*ways[k][i] > 0) 
//						ways[k][j] = Math.max(ways[k][j], ways[i][j]*ways[k][i]);
//						//ways[k][j]+=1;
		
		for (int i=0; i < n-1; i++)
			for (int j=i+1; j < n; j++)
				if (ways[i][j] < 2) return "Impossible";
		
		return "Possible";
	}

	public static void main(String[] args) {
		int[] a = {0,0,0,0,1,1,1,2,2,3,0};
		int[] b = {1,2,3,4,2,3,4,3,4,4,5};
		int n = 6;

		System.out.print("Sample 1: ");
		System.out.println(new FoxAirline2().isPossible(n, a, b));
//
//		int[] a2 = {0,0,0,1,1,2,2,3};
//		int[] b2 = {1,2,4,2,4,3,4,4};
//		int n2 = 5;
//
//		System.out.print("Sample 2: ");
//		System.out.println(new FoxAirline2().isPossible(n2, a2, b2));
////		
//		int[] a3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7, 7, 8};
//		int[] b3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6, 7, 8, 9, 3, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 5, 6, 7, 8, 9, 6, 7, 8, 9, 7, 8, 9, 8, 9, 9};
//		int n3 = 10;
//		
//		System.out.print("Sample 3: ");
//		System.out.println(new FoxAirline2().isPossible(n3, a3, b3));
		
		int[] a4 = {0,1,1};
		int[] b4 = {1,0,0};
		int n4 = 2;

		System.out.print("Sample 4: ");
		System.out.println(new FoxAirline2().isPossible(n4, a4, b4));
	}

}
