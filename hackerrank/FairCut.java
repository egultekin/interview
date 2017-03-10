package hackerrank;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class FairCut {
	
	private static final BigInteger INF = new BigInteger(1, 
			new byte[] { (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255 });
	
	public static int times(int given, int i, int k, int n) {
		int countLiBefore = given;
		int countLuBefore = i-1-given;
		int countLiAfter = k-given;
		int countLuAfter = n-countLiBefore-countLiAfter-countLuBefore;
		
		return countLiBefore*countLuAfter+countLiAfter*countLuBefore;
	}
	
	public static BigInteger BI(long number) {
		return BigInteger.valueOf(number);
	}
	
	public static BigInteger minDiff(int[] a, int k) {
		Arrays.sort(a);
		int n = a.length;
		BigInteger[] interval = new BigInteger[n];
		for (int i = 1; i < n; i++)
			interval[i] = BI(a[i]-a[i-1]);
		
		BigInteger[][] min = new BigInteger[n+1][k+1];
		for (int i = 0; i < n+1; i++) Arrays.fill(min[i], INF);
		min[1][0] = BI(0);
		min[1][1] = BI(0);
		for (int consider = 2; consider <= n; consider++)
			for (int given = 0; given <= k && given < consider; given++) {
				if (min[consider-1][given].compareTo(INF) < 0) {
					int times = times(given, consider, k, n);
					if (times > 0) {
						BigInteger temp = min[consider-1][given].add(interval[consider-1].multiply(BI((long)times)));
						if (given < k) {
							// considered number is given to Li
							min[consider][given+1] =
							(min[consider][given+1].compareTo(temp) < 0) ?
									min[consider][given+1] : temp;
						}

						// considered number is given to Lu
						min[consider][given] = 
								(min[consider][given].compareTo(temp) < 0) ?
								min[consider][given] : temp;
					}
				}
			}
		
		return min[n][k];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) a[i] = sc.nextInt();
	
		System.out.println(minDiff(a, k));
		sc.close();
	}

}
