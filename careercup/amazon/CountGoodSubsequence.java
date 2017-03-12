package careercup.amazon;

import java.util.Scanner;
import java.util.Vector;

/*
 * https://www.careercup.com/question?id=5724699377008640
 * 
 * Time complexity: MAX(Math.sqr(number of 'b' characters), N)
 * Space complexity: (2*N+number of 'b' characters)*sizeof(int)
 */

public class CountGoodSubsequence {
	public static final int MOD = 1000000007;

	public static int allSubsets(int count) {
		if (count < 0) throw new IllegalArgumentException("Invalid argument.");
		int result = 1;
		while (count-- > 0) result = 2*result%MOD;
		return result;
	}
	
	public static int allNonEmptySubsets(int count) {
		return allSubsets(count)-1;
	}
	
	/*
	 * Clever solution of another coder.
	 */
	public static int CountSeq(String input){	
		int acount=0, bcount=0, ccount=0;
		char[] chars = input.toCharArray();
		for(int i=chars.length-1; i>=0;i--){
			if(chars[i]=='c'){
				ccount++;
			}else if(chars[i]=='b'){
        		bcount=((int)Math.pow(2,ccount)-1)+(bcount*2);   		
        	}else if(chars[i]=='a'){
        		acount=bcount+(2*acount);
        	}
		}
		return acount;
	}
	
	/*
	 * Compute number of 'a' characters before an index at A
	 * Compute number of 'c' characters after an index at C (in order to do it in one pass, actual count is found by adding total number of 'c's to C)
	 * Compute total number of 'a' characters in the string at nA
	 * Compute total number of 'b' characters in the string at nB
	 * Compute total number of 'c' characters in the string at nC 
	 */
	public static int countGoodSubsequence(String s) {
		int nA = 0, nB = 0, nC = 0;
		int N = s.length();
		int[] A = new int[N], C = new int[N];
		Vector<Integer> iB = new Vector<>();
		for (int i = 0; i < N; i++) {
			A[i] = nA;
			if (s.charAt(i) == 'a') { 
				nA++;
			}
			else if (s.charAt(i) == 'c') nC--;
			else if (s.charAt(i) == 'b') {
				nB++;
				iB.add(i);
			}
			C[i] = nC;
		}
		nC *= -1;
		int count = 0;
		// for each 'b' character, sum all possible combinations of 'a' characters
		// before index i and all possible combinations of 'c' characters after index i
		for (int i = 0; i < nB; i++) {
			int ind = iB.get(i);
			count = (count+(allNonEmptySubsets(A[ind])*allNonEmptySubsets(nC+C[ind]))%MOD)%MOD;
		}
		
		// for each 'b' pair, sum all possible combinations of 'a' characters before the first
		// 'b' and all possible combinations of 'c' characters after the last 'b' character
		for (int i = 0; i < nB; i++)
			for (int j = i+1; j < nB; j++) {
				int combB = allSubsets(j-i-1);
				int iA = iB.get(i), iC = iB.get(j);
				count = (count+((allNonEmptySubsets(A[iA])*allNonEmptySubsets(nC+C[iC]))%MOD*combB)%MOD)%MOD;
			}
		
		return count;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		sc.close();
		
		System.out.println(countGoodSubsequence(s));
		System.out.println(CountSeq(s));

	}

}
