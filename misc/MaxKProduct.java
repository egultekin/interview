package misc;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Calculates the maximum product of any K numbers in a given set of N positive or negative numbers
 * Time complexity is O(NlogN)
 * Space complexity is 2N+K
 */
public class MaxKProduct {
	
	// keep the product of numbers selected so far from the sorted array
	// as well as the lower and upper bounds of remaining numbers
	// in this class
	static class Product {
		long product;
		int lo;
		int hi;

		Product(long p, int l, int h) {
			product = p;
			lo = l;
			hi = h;
		}
	}

	public static void main(String[] args) {
		int zeros = 0;
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) { 
			int read = sc.nextInt();
			if (read == 0) zeros++;
			else numbers[i-zeros] = read;
		}
		sc.close();
		
		if (N - zeros < K) {
			System.out.println(0);
			return;
		}
		
		N-=zeros;
		numbers = Arrays.copyOf(numbers, N);

		Arrays.sort(numbers);
		Product[] states = new Product[K+1];
		// base state where none of the numbers are selected yet
		states[0] = new Product(1, 0, N-1);
		if (K >= 1) {
			// single number with a max product should be the maximum number in array 
			states[1] = new Product(numbers[N-1], 0, N-2);

			// for multiple selections of numbers, iterate based on previous max products
			// best i numbers can be selected either by:
			// 	a. selecting lowest 2 negative numbers after selecting best i-2 numbers
			//	b. selecting the highest positive number after selecting best i-1 numbers
			for (int i = 2; i <= K; i++) {
				int lo, nlo = states[i-2].lo, plo = states[i-1].lo;
				int hi, nhi = states[i-2].hi, phi = states[i-1].hi;
				long product;
				long negatives = Long.MIN_VALUE;
				long positive = Long.MIN_VALUE;

				if (states[i-2].hi > states[i-2].lo) {
					// when the max product achievable selecting i-2 numbers is already negative
					// highest 2 negative numbers shall be selected rather than lowest to maximize product 
					if (states[i-2].product < 0) {
						nlo = states[i-2].lo;
						nhi = states[i-2].hi-2;
						negatives = numbers[nhi+1]*numbers[nhi+2];
					} else {
						nlo = states[i-2].lo+2;
						nhi = states[i-2].hi;
						negatives = numbers[nlo-1]*numbers[nlo-2];
					}

					negatives *= states[i-2].product;
				}


				if (states[i-1].hi >= states[i-1].lo) {
					// when the max product achievable selecting i-1 numbers is already negative
					// lowest positive number shall be selected rather than the highest to maximize product
					if (states[i-1].product < 0) {
						plo = states[i-1].lo+1;
						phi = states[i-1].hi;
						positive = numbers[plo-1];
					} else {
						plo = states[i-1].lo;
						phi = states[i-1].hi-1;
						positive = numbers[phi+1];
					}

					positive *= states[i-1].product;
				}

				if (negatives > positive) {
					product = negatives;
					hi = nhi;
					lo = nlo;
				} else {
					product = positive;
					hi = phi;
					lo = plo;
				}

				// set the best achievable state for selecting i numbers
				states[i] = new Product(product, lo, hi);
			}

			if (states[K].product < 0 && zeros > 0) System.out.println(0);
			else System.out.println(states[K].product);
		} else System.out.println(0);
	}

}
