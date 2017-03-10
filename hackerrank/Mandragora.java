package hackerrank;

import java.util.Arrays;
import java.util.Scanner;

public class Mandragora {
	static long maxHealth(int[] H, long sum) {
		Arrays.sort(H);
		int strength = 1;
		long max = sum;
		for (int i = 0; i < H.length; i++) {
			if ((strength+1)*(sum-H[i]) > max) max = (strength+1)*(sum-H[i]);
			sum -= H[i];
			strength++;
		}
		
		return max;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			int N = sc.nextInt();
			int[] H = new int[N];
			long sum = 0;
			for (int j = 0; j < N; j++) {
				H[j] = sc.nextInt();
				sum += H[j];
			}
			
			System.out.println(maxHealth(H, sum));
		}
		sc.close();
	}

}
