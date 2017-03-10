package hackerrank;

import java.util.Scanner;

public class Candies {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] ranks = new int[N];
		for (int i = 0; i < N; i++) ranks[i] = sc.nextInt();
		sc.close();
		
		int[] increasing = new int[N];
		int[] decreasing = new int[N];
		for (int i = 1, j = N-1-i; i < N && j >= 0; i++, j--) {
			if (ranks[i] > ranks[i-1]) increasing[i] = increasing[i-1]+1;
			if (ranks[j] > ranks[j+1]) decreasing[j] = decreasing[j+1]+1;
		}
		
		long sum = 0;
		for (int i = 0; i < N; i++) sum += Math.max(increasing[i], decreasing[i])+1;
		System.out.println(sum);

	}

}
