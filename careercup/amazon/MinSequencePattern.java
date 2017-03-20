package careercup.amazon;

import java.util.Scanner;

/*
 * https://www.careercup.com/question?id=5644221689102336
 * 
 * Given a pattern containing only Is and Ds. I for increasing, D for decreasing.
 * Print the minimum number following that pattern.
 * Digits from 1-9 and digits can't repeat.
 */
public class MinSequencePattern {

	public static void minSequence(String pattern) {
		String[] dS = pattern.split("I", pattern.length()+1);
		int max = 1;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < dS.length; i++) {
			if (dS[i].equals("")) builder.append(max++);
			else {
				int j = dS[i].length();
				while (j >= 0) {
					builder.append(max+j);
					j--;
				}
				max += dS[i].length()+1;
			}
		}
		System.out.println(builder.toString());
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		while (N-- > 0) {
			String pattern = sc.next();
			minSequence(pattern);
		}
		sc.close();	
	}

}
