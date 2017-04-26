package cci;

import java.util.Scanner;

class DivingBoard {
	static int[] allPossibleLengths(int k, int p_short, int p_large) {
		if (k < 1) throw new IllegalArgumentException("k must be more than 0.");
		int[] result = new int[k+1];
		int total = k*p_short;
		result[0] = total;
		int i = 1;
		while (i <= k) {
			total = total-p_short+p_large;
			result[i++] = total;
		}
		
		return result;
	}
	
	static void print(int[] result) {
		if (result.length == 0) return;
		StringBuilder builder = new StringBuilder();
		builder.append("{").append(result[0]);
		for (int i = 1; i < result.length; i++) builder.append("-").append(result[i]);
		builder.append("}");
		System.out.println(builder.toString());
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int samples = sc.nextInt();
		while (samples-- > 0) {
			int k = sc.nextInt();
			int p_short = sc.nextInt();
			int p_large = sc.nextInt();
			DivingBoard.print(DivingBoard.allPossibleLengths(k, p_short, p_large));
		}
		sc.close();
	}
}