package leetcode;

import java.util.Scanner;

public class MedianOfTwoSortedArrays3 {

	public static double median(int[] a, int[] b) {
		int n = a.length;
		int m = b.length;
		boolean even = (n+m) %2 == 0;
		int s = even ? (n+m)/2-1 : (n+m)/2;
		int alo = 0, ahi = n-1, blo = 0, bhi = m-1;
		int ai = (ahi+alo)/2, bi = s-ai;
		while (ai < n && bi < m && (alo < ahi-1 || blo < bhi-1))
			if (a[ai] == b[bi]) 
				return a[ai];
			else if (a[ai] > b[bi]) {
				ahi = ai;
				blo = bi;
				ai = (alo+ahi)/2;
				bi = s-ai;
			} else {
				bhi = bi;
				alo = ai;
				bi = (blo+bhi)/2;
				ai = s-bi;
			}
		if (ai >= n) if (even) return ((double)b[s-n]+b[s-n+1])/2; else return b[s-n];
		else if (bi >= m) if (even) return ((double)b[s-m]+b[s-m+1])/2; else return a[s-m];
		if (a[alo] >= b[blo]) return ((double)a[alo]+b[bhi])/2;
		else return ((double)b[blo]+a[ahi])/2;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int asize = sc.nextInt();
		int[] a = new int[asize];
		for (int i = 0; i < asize; i++) a[i] = sc.nextInt();
		int bsize = sc.nextInt();
		int[] b = new int[bsize];
		for (int i = 0; i < bsize; i++) b[i] = sc.nextInt();
		sc.close();
		
		System.out.println(MedianOfTwoSortedArrays3.median(a, b));

	}

}

//3 1 5 7
//7 8 10 12 14 20 25 30
//11.0

//4 1 5 7 10
//4 1 5 5 16
//5.0

//4 1 5 7 10
//4 1 4 6 16
//5.5

//3 1 5 7
//9 8 10 12 14 16 20 25 30 36
//13.0

//3 1 5 7
//8 8 10 12 14 16 20 25 30
//12.0

//3 1 15 27
//10 6 8 9 13 14 15 20 22 26 29
//15.0