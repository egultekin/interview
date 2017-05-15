package leetcode;

import java.util.Scanner;

public class MedianOfTwoSortedArrays {

	public static double median(int[] a, int[] b) {
		int n = a.length;
		int m = b.length;
		int alo = 0, ahi = n-1, blo = 0, bhi = m-1;
		boolean even = (n+m)%2==0;
		int s = even ? (n+m)/2-1 : (n+m)/2;
		int ai = (alo+ahi)/2;
		int bi = s-ai;
		while (ai < n && bi < m && alo < ahi-1 && blo < bhi-1)
			if (a[ai] == b[bi]) return a[ai];
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
		int n1, n2;
		if (ai >= n) {
			n1 = b[s-n];
			n2 = even ? b[s-n+1] : n1;
		} else if (bi >= m) {
			n1 = a[s-m];
			n2 = even ? b[s-m+1] : n1;
		} else {		
			int a1, a2, b1, b2;
			if (alo == ahi-1) {
				b2 = b[s-alo]; 
				b1 = b[s-ahi];
				a1 = a[alo];
				a2 = a[ahi];
			} else {
				b1 = b[blo];
				b2 = b[bhi];
				a2 = a[s-blo];
				a1 = a[s-bhi];
			}
			
			if (a1 >= b1 && b2 <= a2) {
				n1 = a1; n2 = b2;
			} else if (a1 >= b1) {
				n1 = a1; n2 = a2;
			} else if (a1 < b1 && a2 <= b2) {
				n1 = b1; n2 = a2;
			} else {
				n1 = b1; n2 = b2;
			}				
		}
		
		return ((double)n1+n2)/2;
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
		
		System.out.println(MedianOfTwoSortedArrays.median(a, b));
	}

}
