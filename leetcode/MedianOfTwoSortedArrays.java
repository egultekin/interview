package leetcode;

import java.util.Scanner;

public class MedianOfTwoSortedArrays {

	static class TestCase {
		int[] arr1, arr2;
		double result;
		int number;
		
		public TestCase(int n) {
			number = n;
		}
		
		public TestCase a(int[] a) {
			arr1 = a;
			return this;
		}
		
		public TestCase b(int[] b) {
			arr2 = b;
			return this;
		}
		
		public TestCase r(double res) {
			result = res;
			return this;
		}
		
		public void check(double against) {
			if (result == against) System.out.printf("Test %d passed.\n", number);
			else System.out.printf("Test %d failed.Expected: %.1f Found: %.1f\n", number, result, against);
		}
	}
	
	public static double median(int[] arr1, int[] arr2) {
		int[] a = arr1, b = arr2;
		if (arr1.length > arr2.length) {
			a = arr2;
			b = arr1;
		}
		int n = a.length;
		int m = b.length;

		if (n+m == 0) throw new IllegalArgumentException("Empty lists");
		
		boolean even = (n+m)%2==0;
		int s = even ? (n+m-1)/2 : (n+m)/2;

		int n1, n2;
		if (n == 0) {
			n1 = b[s];
			if (n > s+1) n2 = b[s+1]; else n2 = n1;
		} else {
			int alo = 0, ahi = Math.max(n-1, 0), blo = 0, bhi = Math.max(m-1, 0);

			int ai = (alo+ahi)/2;
			int bi = s-ai;
			while (alo < ahi-1 || blo < bhi-1) {
				if (ai >= n) {
					blo = bi+1;
					bi = (blo+bhi)/2;
					ai = s-bi;
				} else if (bi >= m) {
					alo = ai+1;
					ai = (alo+ahi)/2;
					bi = s-ai;
				} else if (a[ai] == b[bi]) 
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
			}
			
			int a1 = a[alo], a2 = a[ahi], b1 = b[blo], b2 = b[bhi];
			if (alo == ahi && blo < bhi) a2 = Integer.MAX_VALUE;
			if (blo == bhi && alo < ahi) b2 = Integer.MAX_VALUE;
			if (a1 >= b1 && a2 <= b2) {
				n1 = a1; n2 = a2;
			} else if (a1 >= b1) {
				n1 = a1; n2 = b2;
			} else if (a2 <= b2) {
				n1 = b1; n2 = a2;
			} else {
				n1 = b1; n2 = b2;
			}
		}
		
		if (!even) n2 = n1;
		return ((double)n1+n2)/2;
	}

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int asize = sc.nextInt();
//		int[] a = new int[asize];
//		for (int i = 0; i < asize; i++) a[i] = sc.nextInt();
//		int bsize = sc.nextInt();
//		int[] b = new int[bsize];
//		for (int i = 0; i < bsize; i++) b[i] = sc.nextInt();
//		sc.close();
		
		TestCase tc = new TestCase(1).a(new int[] { 1, 5, 7 })
				.b(new int[] {8, 10, 12, 14, 20, 25, 30})
				.r(11.0);
		tc.check(MedianOfTwoSortedArrays.median(tc.arr1, tc.arr2));
	}

}
