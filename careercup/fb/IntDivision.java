package careercup.fb;

import java.util.Scanner;

public class IntDivision {
	
	public static int divide(int a, int b) {
		if (b == 0) throw new ArithmeticException("Division by zero.");
		int count = 0;
		int sign = 1;
		if (a < 0) {
			a = 0-a;
			sign = sign*-1;
		}
		if (b < 0) {
			b = 0-b;
			sign = sign*-1;
		}
		while (a >= b) {
			a = a-b;
			count++;
		}
		return count*sign;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		while (N-- > 0) try {
			System.out.println(divide(sc.nextInt(), sc.nextInt()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		sc.close();

	}

}
