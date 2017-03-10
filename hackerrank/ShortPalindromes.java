package hackerrank;

import java.util.Arrays;
import java.util.Scanner;

public class ShortPalindromes {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int N = s.length();
		sc.close();
		
		int[] str = new int[N];
		// count of char j before the index i (excluding the one at index)
		int[][] fc = new int[N][26];
		// count of char j after the index i (excluding the one at index)
		int[][] bc = new int[N][26];
		// index of next char j after the index i
		int[][] next = new int[N][26];
		// count of 2-char substrings (str.charAt(i), j)
		int[][] subs = new int[N][];
		int mod = 1000000007;
		
		long total = 0;
		for (int i = 0; i < N; i++) {
			str[i] = s.charAt(i)-'a';
			Arrays.fill(next[i], Integer.MAX_VALUE);
		}
		
		for (int i = 1; i < N; i++) {
			int chNi = str[N-i];
			int chI = str[i-1];
			for (int j = 0; j < 26; j++) {
				fc[i][j] = fc[i-1][j]+((chI == j) ? 1 : 0);
				bc[N-i-1][j] = bc[N-i][j]+((chNi == j) ? 1 : 0);
				next[N-i-1][j] = ((chNi == j) ? N-i : next[N-i][j]);
			}			
		}
		
		for (int i = 1; i < N; i++) {
			//subs[N-i] = new int[26*26];
			subs[N-i-1] = Arrays.copyOf(subs[N-i], subs[N-i].length);
			int indexBase = (str[N-i-1])*26;			
			for (int j = 0; j < 26; j++) 
				subs[N-i-1][indexBase+j] = (bc[N-i-1][j]%mod+subs[N-i][indexBase+j]%mod)%mod;
		}
		
		for (int i = 1; i < N; i++) {
			long cc = 0;
			int ch = str[i];
			int nextIndex = next[i][ch];
			if (nextIndex != Integer.MAX_VALUE)
				for (int j = 0; j < 26; j++) 
					cc = (cc%mod + ((fc[i][j]%mod)*(subs[nextIndex][26*ch+j]%mod)%mod))%mod;
			total = (total%mod + cc%mod)%mod;
		}
		
		System.out.println(total);
	}

}
