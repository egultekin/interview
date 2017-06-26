package leetcode;

import java.util.List;
import java.util.ArrayList;

public class AmazingNumber {
	static class Pair {
		int start, end;
		public Pair(int s, int e) {
			start = s;
			end = e;
		}
	}
	
	public int findIndexMaximizingAmazingNumbers(int[] arr) {
		int start = -1, end = -1, n = arr.length;
		List<Pair> pairs = new ArrayList<Pair>();
		for (int i=0; i < n; i++) {
			int number = arr[i];
			if (number < n) {
				if (number <= 0) {
					start = 0;
					end = n-1;
				} else {
					start = (i+1)%n;
					end = (i-number+n)%n;
				}
				
				if (start > end) {
					pairs.add(new Pair(start, n-1));
					pairs.add(new Pair(0, end));
				} else pairs.add(new Pair(start, end));
			}
		}
		
		int[] available = new int[n];
		for (Pair p : pairs) {
			available[p.start]++;
			if (p.end < n-1) available[p.end+1]--;
		}
		
		int sum = 0, max = 0, maxI = -1;
		for (int i=0; i < n; i++) {
			sum = sum+available[i];
			if (sum > max) {
				max = sum;
				maxI = i;
			}
		}
		
		System.out.printf("Amazing number count can be maximized to %d if started indexing at %d.\n", max, maxI);
		return maxI;
	}

	public static void main(String[] args) {
		new AmazingNumber().findIndexMaximizingAmazingNumbers(new int[] {1, 5, -7, -1, 9, 2, 6, 0});
	}

}
