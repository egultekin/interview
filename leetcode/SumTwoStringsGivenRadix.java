package leetcode;

import java.util.Scanner;

public class SumTwoStringsGivenRadix {
	public static String sum(String a, String b, int radix) {
		char[] s1 = a.toCharArray();
		char[] s2 = b.toCharArray();
		int offset = 0;
		StringBuilder sb = new StringBuilder();
		int carry = 0;
		while (offset < s1.length || offset < s2.length || carry > 0) {
			int x = 0, y = 0;
			if (offset < s1.length) x = s1[s1.length-1-offset]-'0';
			if (offset < s2.length) y = s2[s2.length-1-offset]-'0';
			int s = x+y+carry;
			if (s >= radix) {
				s -= radix;
				carry = 1;
			} else carry = 0;
			sb.append(s);
			offset++;
		}
		
		return reverse(sb.toString());
	}
	
	public static String reverse(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length()-1; i >= 0; i--) sb.append(s.charAt(i));
		return sb.toString();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();
		int radix = sc.nextInt();
		sc.close();

		System.out.printf("Sum of %s and %s in radix %d is %s.\n", a, b, radix, sum(a, b, radix));
	}

}
