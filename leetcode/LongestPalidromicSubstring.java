package leetcode;

import java.util.Arrays;
import java.util.Scanner;

public class LongestPalidromicSubstring {
	
    public String longestPalindrome(String s) {
    	char[] ca = s.toCharArray();
    	int[] longest = new int[ca.length];
    	int max = 0;
    	boolean[][] valid = new boolean[ca.length][ca.length+1];
    	Arrays.fill(longest, 1);
    	for (int i=0; i < ca.length; i++) 
    		valid[i][0] = valid[i][1] = true;
    	for (int i=1; i < ca.length; i++)
    		for (int j=i; j < ca.length; j++)
    			if (valid[j-1][i-1] && ca[j-i] == ca[j]) {
    				valid[j][i+1] = true;
    				if (longest[j] < i+1) {
    					longest[j] = i+1;
    					max = j;
    				}
    			}
    	char[] res = Arrays.copyOfRange(ca, max-longest[max]+1, max+1);
    	return String.valueOf(res);
    }

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		LongestPalidromicSubstring lps = new LongestPalidromicSubstring();
		while (t-- > 0) System.out.println(lps.longestPalindrome(sc.next()));
		sc.close();
	}

}
