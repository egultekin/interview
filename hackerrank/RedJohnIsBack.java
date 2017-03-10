package hackerrank;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class RedJohnIsBack {

	public static void main(String[] args) {	
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] blocks = new int[] { 1 , 4 };
		for (int i = 0; i < T; i++) {
			int N = sc.nextInt();
			int[] ways = new int[N+1];
			ways[0] = 1;
			for (int sum = 1; sum <= N; sum++)
				for (int block = 0; block < blocks.length; block++)
					if (sum >= blocks[block]) ways[sum] += ways[sum-blocks[block]];
			Set<Integer> primes = new TreeSet<>();
			for (int j = 2; j <= ways[N]; j++) primes.add(j);
			for (int k = 2; k <= ways[N]; k++)
				for (int j = 2; k*j <= ways[N]; j++)
					primes.remove(k*j);
			System.out.println(primes.size());
		}
		sc.close();
	}

}
