package leetcode;

import java.util.Scanner;

public class MedianOfTwoSortedArrays {

	static class TestCase {
		int[] a, b;
		int order;
		double expected;
		
		public TestCase(int o) {
			order = o;
		}
		
		public TestCase a(int[] arr) {
			a = arr;
			return this;
		}
		
		public TestCase b(int[] arr) {
			b = arr;
			return this;
		}
		
		public TestCase expect(double e) {
			expected = e;
			return this;
		}
		
		public boolean check(double test) {
			if (test == expected) {
				System.out.printf("Test %d passed. Expected: %.1f Found: %.1f\n", order, expected, test);
				return false;			
			}
			System.out.printf("Test %d failed. Expected: %.1f Found: %.1f\n", order, expected, test);
			return false;
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
		int n1, n2;
		
		boolean even = (n+m)%2==0;
		int s = even ? (n+m-1)/2 : (n+m)/2;
		if (n == 0 && m == 0) throw new IllegalArgumentException("Empty arrays.");
		else if (n == 0) {
			n1 = b[s];
			if (even) n2 = b[s+1]; else n2 = n1;
		} else {
			int alo = 0, ahi = Math.max(n-1, 0), blo = 0, bhi = Math.max(m-1, 0);
			int ai = (alo+ahi)/2;
			int bi = s-ai;
			
			while (ai < n && bi < m && alo+blo < s)
				if (a[ai] == b[bi]) return a[ai];
				else if (a[ai] < b[bi] 
						&& ((ai < n-1 && bi < m-1 && a[ai+1] <= b[bi])
						|| (bi > 0 && ai > 0 && a[ai] <= b[bi-1]))) {
					bhi = bi;
					alo = ai+1;
					bi = (blo+bhi)/2;
					ai = s-bi;
				} else if (a[ai] > b[bi] 
						&& ((bi < m-1 && ai < n-1 && b[bi+1] <= a[ai])
						|| (ai > 0 && bi > 0 && b[bi] <= a[ai-1]))) {
					ahi = ai;
					blo = bi+1;
					ai = (alo+ahi)/2;
					bi = s-ai;
				} else {
					alo = ai;
					blo = bi;
				}
			
			if (ai >= n) {
				n1 = b[s-n];
				n2 = m > s-n+1 ? b[s-n+1] : n1;
			} else if (bi >= m) {
				n1 = a[s-m];
				n2 = n > s-m+1 ? a[s-m+1] : n1;
			} else {
				if (alo >= n) {
					n1 = b[blo];
					n2 = m > blo+1 ? b[blo+1] : n1;
				} else if (blo >= m) {
					n1 = a[alo];
					n2 = n > alo+1 ? a[alo+1] : n1;
				} else {
					if (a[alo] <= b[blo]) {
						n1 = a[alo];
						n2 = b[blo];
						if (alo < n-1) n2 = Math.min(a[alo+1], n2);
					} else {
						n1 = b[blo];
						n2 = a[alo];
						if (blo < m-1) n2 = Math.min(b[blo+1], n2);
					}
				}
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
		
		TestCase t1 = new TestCase(1);
		t1.a(new int[] {1, 5, 7})
		.b(new int[] {8, 10, 12, 14, 20, 25, 30})
		.expect(11.0)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(2);
		t1.a(new int[] {1, 5, 7, 10})
		.b(new int[] {1, 5, 5, 16})
		.expect(5.0)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(3);
		t1.a(new int[] {1, 5, 7, 10})
		.b(new int[] {1, 4, 6, 16})
		.expect(5.5)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(4);
		t1.a(new int[] {1, 5, 7})
		.b(new int[] {8, 10, 12, 14, 16, 20, 25, 30, 36})
		.expect(13.0)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(5);
		t1.a(new int[] {1, 5, 7})
		.b(new int[] {8, 10, 12, 14, 16, 20, 25, 30})
		.expect(12.0)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(6);
		t1.a(new int[] {1, 15, 27})
		.b(new int[] {6, 8, 9, 13, 14, 15, 20, 22, 26, 29})
		.expect(15.0)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(7);
		t1.a(new int[] {2, 5, 7})
		.b(new int[] {})
		.expect(5.0)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(8);
		t1.a(new int[] {2})
		.b(new int[] {})
		.expect(2.0)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(9);
		t1.a(new int[] {2, 3})
		.b(new int[] {})
		.expect(2.5)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(10);
		t1.a(new int[] {3})
		.b(new int[] {1, 4})
		.expect(3)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(11);
		t1.a(new int[] {3})
		.b(new int[] {1, 4, 4})
		.expect(3.5)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(12);
		t1.a(new int[] {4})
		.b(new int[] {1, 4, 4})
		.expect(4)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(13);
		t1.a(new int[] {})
		.b(new int[] {2})
		.expect(2.0)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(14);
		t1.a(new int[] {})
		.b(new int[] {2, 5})
		.expect(3.5)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(15);
		t1.a(new int[] {3, 4})
		.b(new int[] {3, 5})
		.expect(3.5)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(16);
		t1.a(new int[] {3})
		.b(new int[] {1, 2})
		.expect(2)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(17);
		t1.a(new int[] {3})
		.b(new int[] {1, 4})
		.expect(3)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));

		t1 = new TestCase(18);
		t1.a(new int[] {3})
		.b(new int[] {4, 5})
		.expect(4)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(19);
		t1.a(new int[] {3, 8})
		.b(new int[] {4, 5})
		.expect(4.5)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(20);
		t1.a(new int[] {3, 6})
		.b(new int[] {7, 8})
		.expect(6.5)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(21);
		t1.a(new int[] {7, 8})
		.b(new int[] {3, 6})
		.expect(6.5)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
		
		t1 = new TestCase(22);
		t1.a(new int[] {4, 5})
		.b(new int[] {3, 8})
		.expect(4.5)
		.check(MedianOfTwoSortedArrays.median(t1.a, t1.b));
//		System.out.println(MedianOfTwoSortedArrays.median(a, b));
	}

}
