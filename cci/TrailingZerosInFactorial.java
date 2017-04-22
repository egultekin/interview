package cci;

import java.util.Scanner;

public class TrailingZerosInFactorial {
	
	private int fives(int n) {
		int count = 0;
		while (n % 5 == 0) {
			count++;
			n = n / 5;
		}
		return count;
	}

	public int trailingZeros(int factorial) {
		if (factorial < 5) return 0;
		int count = 0;
		for (int i = 5; i <= factorial; i++) count += fives(i);
		return count;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int samples = sc.nextInt();
		TrailingZerosInFactorial tz = new TrailingZerosInFactorial();
		while(samples-- > 0) System.out.println(tz.trailingZeros(sc.nextInt())); 
		sc.close();
	}

}
