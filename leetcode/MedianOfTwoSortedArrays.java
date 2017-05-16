package leetcode;

import java.util.Scanner;

public class MedianOfTwoSortedArrays {

	public static double median(int[] arr1, int[] arr2) {
		int[] a = arr1, b = arr2;
		if (arr1.length > arr2.length) {
			a = arr2;
			b = arr1;
		}
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
		int a1, a2, b1, b2;
		if (ai >= n) {
			n1 = b[s-n];
			n2 = b[s-n+1];
		} else if (bi >= m) {
			n1 = a[s-m];
			n2 = a[s-m+1];
		} else if (alo == ahi && blo == bhi) {
			n1 = a[alo];
			n2 = b[blo];
		} else {
			if (alo == ahi-1) {
				b2 = s-alo < m && ahi > alo ? b[s-alo] : Integer.MAX_VALUE; 
				b1 = b[s-ahi];
				a1 = a[alo];
				a2 = a[ahi];
			} else {
				b1 = b[blo];
				b2 = b[bhi];
				a2 = s-blo < n && ahi > alo ? a[s-blo] : Integer.MAX_VALUE;
				a1 = a[s-bhi];
			}
			
			if (a1 >= b1 && a2 <= b2) {
				n1 = a1; n2 = a2;
			} else if (a1 < b1 && a2 > b2) {
				n1 = b1; n2 = b2;
			} else if (a1 >= b1) {
				n1 = Math.min(a1, b2); n2 = Math.max(a1, b2);
			} else {
				n1 = Math.min(b1, a2); n2 = Math.max(b1, a2);
			}			
		}
		
		if (!even) n2 = n1;
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
