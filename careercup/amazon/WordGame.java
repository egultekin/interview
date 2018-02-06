/*
* https://careercup.com/question?id=5718772282294272
* Find all substrings of the input string which is K characters long
* and contains exactly K-1 unique characters
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class WordGame {
	public static List<String> allSubstrings(String inputString, int K) {
		List<String> result = new ArrayList<>();
		int[] frame = new int[27];
		Arrays.fill(frame, 0);
		int l, r, distinct;
		distinct = l = 0;
		r = -1;

		for (int i=0; i<K; i++) {
			r++;
			if (r < inputString.length()) {
				int offset = inputString.charAt(r)-'a';
				if (frame[offset] == 0) distinct++;
				frame[offset]++;
			}	
		}

		while (r < inputString.length()) {
			if (distinct == K-1) result.add(inputString.substring(l, r+1));
			l++;
			r++;
			if (r < inputString.length()) {
				int offsetRemoved = inputString.charAt(l-1)-'a';
				int offsetAdd = inputString.charAt(r)-'a';
				if (frame[offsetRemoved] == 1) distinct--;
				frame[offsetRemoved]--;
				if (frame[offsetAdd] == 0) distinct++;
				frame[offsetAdd]++; 
			}
		}

		printResult(result);
		return result;
	}

	public static void printResult(List<String> list) {
		System.out.printf("Result contains %d elements.\n", list.size());
		for (String s : list) System.out.println(s);
	}

	public static void main(String[] args) {
		allSubstrings("awaglk", 4);
		allSubstrings("awagalk", 4);
		allSubstrings("awag", 4);
		allSubstrings("awgl", 4);
		allSubstrings("awg", 4);
		allSubstrings("", 4);
	}
}