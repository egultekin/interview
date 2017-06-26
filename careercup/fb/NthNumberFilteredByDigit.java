package careercup.fb;

import java.util.Scanner;

public class NthNumberFilteredByDigit {
	private static boolean contains(int number, int digit) {
		if (number < 10) return number == digit;
		return number%10 == digit || contains(number/10, digit);
	}
	
	public static int getNthNumber(int n, int digit) {
		int i = 1, number = 1;
		while (i < n) {
			number++;
			if (!contains(number, digit)) i++;
		}
		return number;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int digit = sc.nextInt();
		int t = sc.nextInt();
		while (t-- > 0) System.out.println(NthNumberFilteredByDigit.getNthNumber(sc.nextInt(), digit));
		sc.close();

	}

}
