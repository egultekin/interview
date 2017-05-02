package cci;

import java.util.Scanner;

/*
 * Given two strings a pattern and a value, determine whether the value matches the pattern.
 * Pattern is made up of a sequence of only 'a' and 'b' letters.
 * 
 * Time complexity: O(N^2)
 * Space complexity: O(value.length());
 */

class PatternMatching {
	public static boolean matches(String value, String pattern) {
		if (pattern.length() == 0 || value.length() == 0) return false;
		char[] pArr = pattern.toCharArray();
		char[] vArr = value.toCharArray();
		int cA = 0, cB = 0;
		for (int i = 0; i < pArr.length; i++) if (pArr[i] == 'a') cA++; else cB++;
		int max = Math.max(cA, cB);
		int min  = Math.min(cA, cB);
		int i = 1, v = 0;
		int remainder = vArr.length-(i*min);
		while (remainder >= 0 && v > -1) {
			if (remainder % max == 0) {
				int sizeA = (cA == max) ? remainder / max : i;
				int sizeB = (cA == max) ? i : remainder/max;
				String tryA = "", tryB = "";
				int p = 0;
				while (p < pArr.length && v > -1) {
					if (pArr[p] == 'a')
						if (tryA.equals("")) {
							tryA = value.substring(v, v+sizeA);
							v += sizeA;
						} else v = match(value, v, tryA);
					else 
						if (tryB.equals("")) {
							tryB = value.substring(v, v+sizeB);
							v += sizeB;
						} else v = match(value, v, tryB);
					p++;
				}
				if (v > -1) return true;
			}
			if (min == 0) break;
			i++;
			remainder = vArr.length-(i*min);
		}
		return false;
	}
	
	public static int match(String match, int i, String with) {
		char[] m = match.toCharArray();
		char[] w = with.toCharArray();
		if (i+w.length > m.length) return -1;
		int iter = 0;
		while (iter < w.length && w[iter] == m[i]) {
			i++;
			iter++;
		}
		if (iter == w.length) return i;
		return -1;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int samples = sc.nextInt();
		while (samples-- > 0) {
			String value = sc.next();
			String pattern = sc.next();
			if (PatternMatching.matches(value, pattern))
				System.out.printf("%s matches the pattern %s\n", value, pattern);
			else 
				System.out.printf("%s does not match the pattern %s\n", value, pattern);
		}
		sc.close();
	}
}

//5
//catcatgocatgo aabab
//catcagocatgo aabab
//catgo aa
//catgocatgo bb
//catcatgocatgo a