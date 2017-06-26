package careercup.fb;

import java.util.Scanner;

public class RepeatingDecimalPatternDivision {
	public static String repeating(int n, int div, int decimals) {
		int rem = n%div;
		StringBuilder sb = new StringBuilder();
		sb.append('.');
		while (decimals-- > 0) {
			int q = (rem*10)/div;
			sb.append(q);
			rem = (rem*10)%div;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) System.out.println(RepeatingDecimalPatternDivision.repeating(sc.nextInt(), sc.nextInt(), sc.nextInt()));
		sc.close();

	}

}
