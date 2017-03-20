package careercup.amazon;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * https://www.careercup.com/question?id=5639848137326592
 * 
 * Find longest repeating substring of a given string.
 * Assuming only ASCII characters and unique hashes due to large MODULUS coefficient
 * the solution is reduced to comparing hashes of substrings of the selected length. 
 * Longest repeating length is computed by binary approximation starting with interval [1, s.length-1]
 * 
 * Time complexity: O(NlogN)
 * Space complexity: O(NlogN)
 */
public class LongestRepeatingSubstring {
	public static final int MODULUS = (int)Math.pow(2, 23)-15;
	// number of ASCII characters
	public static final int CHARS = 256;
	public static final int ASCII_START = 33;
	
	public static int adjustHigh(int number, char remove, int length) {
		int ch = remove-ASCII_START;
		while (length-- > 1) ch = (ch*CHARS)%MODULUS;
		return (number-ch)%MODULUS;
	}
	
	public static int[] hashes(char[] chars, int length) {
		int hash[] = new int[chars.length-length+1];
		hash[0] = chars[0]-ASCII_START;
		for (int i=1; i < length; i++)
			hash[0] = ((CHARS*hash[0])%MODULUS-ASCII_START+chars[i])%MODULUS;
		for (int i=1; i < hash.length; i++)
			hash[i] = (CHARS*adjustHigh(hash[i-1], chars[i-1], length))%MODULUS-ASCII_START+chars[i+length-1];
		return hash;
	}
	
	public static boolean isRepeating(int[] hash) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < hash.length; i++) 
			if (set.contains(hash[i])) return true;
			else set.add(hash[i]);
		return false;
	}
	
	public static int findLongest(String s) {
		char[] chars = s.toCharArray();
		int hi = chars.length-1;
		int lo = 1, mid = (hi+lo)/2;
		
		while (hi >= lo) {
			mid = (hi+lo)/2;
			if (isRepeating(hashes(chars, mid))) lo = mid+1;
			else hi = mid-1;
		}
		
		return lo-1;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		sc.close();

		System.out.println(findLongest(input));
	}

}
