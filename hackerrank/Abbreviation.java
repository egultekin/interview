package hackerrank;

import java.util.Scanner;

public class Abbreviation {

	static boolean possible(String a, String b) {
		int lA = a.length();
		int lB = b.length();
		
		int[][] result = new int[lA+1][lB+1];
		for (int i = 0; i < lA+1; i++) result[i][0] = 1;
		for (int i = 1; i < lA+1; i++)
			for (int j = 1; j <= i && j < lB+1; j++)
				if (Character.toUpperCase(a.charAt(i-1)) == b.charAt(j-1) && result[i-1][j-1] > 0) 
					result[i][j] = 1;
				else if (Character.isLowerCase(a.charAt(i-1)) && result[i-1][j] > 0) 
					result[i][j] = result[i-1][j];
			
		return result[lA][lB] > 0;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			String a = sc.next();
			String b = sc.next();
			System.out.println(possible(a,b) ? "YES" : "NO");
		}

		sc.close();
	}

}
