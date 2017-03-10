package hackerrank;

import java.util.Scanner;

public class QuicksortInPlace {
	int[] arr;

	public QuicksortInPlace(int[] a) {
		arr = a;
	}
	
	int partition(int lo, int hi) {
		int pivot = arr[hi];
		int swap = lo;
		for (int i = lo; i < hi; i++)
			if (arr[i] <= pivot) {
				swap(swap, i);
				swap++;
			}
		
		swap(swap, hi);
		StringBuilder build = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			build.append(arr[i]);
			if (i < arr.length-1) build.append(" ");
		}
		System.out.println(build.toString());
		return swap;
	}
	
	void sort() {
		quick(0, arr.length-1);
	}
	
	void quick(int lo, int hi) {
		if (lo < hi) {
			int p = partition(lo, hi);
			quick(lo, p-1);
			quick(p+1, hi);
		}
	}
	
	void swap(int f, int s) {
		int temp = arr[f];
		arr[f] = arr[s];
		arr[s] = temp;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
		new QuicksortInPlace(arr).sort();
		sc.close();	

	}

}
