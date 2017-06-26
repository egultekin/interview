package leetcode;

import java.util.Scanner;

public class CountNumbersSortedArray {
	private static int count(int[] a, int v, int lo, int hi) {
		int count = 0;
		while (lo < hi) {
			int mid = (lo+hi)/2;
			if (a[mid] < v) lo = mid+1;
			else hi = mid;
		}
		while (lo < a.length && a[lo] <= v) {
			if (a[lo] == v) count++;
			lo++;
		}
		return count;
	}
	
	public static int count(int[] a, int v) {
		return count(a, v, 0, a.length-1);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] a = new int[] { 3, 5, 5, 5, 5, 7, 8, 8};
		while (N-- > 0) {
			System.out.println(count(a, sc.nextInt()));
		}
		sc.close();
	}

}
