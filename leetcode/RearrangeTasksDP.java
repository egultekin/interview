package leetcode;

import java.util.HashMap;
import java.util.Map;

public class RearrangeTasksDP {
	private Map<Character, Integer> leftCharsCount = null;
	private char[] chs = null;
	
	public int minCompletion(String tasks, int wait) {
		int n = tasks.length();
		chs = tasks.toCharArray();
		leftCharsCount = new HashMap<>();
		for (int i=0; i < n; i++) {
			if (!leftCharsCount.containsKey(chs[i])) 
				leftCharsCount.put(chs[i], 1);
			else 
				leftCharsCount.put(chs[i], leftCharsCount.get(chs[i])+1);
		}
		int[] minCompletion = new int[n];
		minCompletion[0] = 1;
		for (int i=1; i < n; i++) {
			int best = minCompletion[i-1]+1;
			if (chs[i] == chs[i-1] && !makesSwap(i)) {
				best = best+1;
				decreaseCount(chs[i]);
			}
			minCompletion[i] = best;
		}
		print(chs);
		return minCompletion[n-1];
	}
	
	private void print(char[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i < arr.length; i++)
			if (sb.length() == 0) sb.append(arr[i]);
			else sb.append(',').append(arr[i]);
		System.out.println(sb.toString());
	}
	
	private void decreaseCount(char ch) {
		if (leftCharsCount.get(ch) > 0) leftCharsCount.put(ch, leftCharsCount.get(ch)-1);
	}
	
	private boolean makesSwap(int index) {
		int swapIndex = getSwapIndex(index);
		if (swapIndex < index) return false;
		char temp = chs[index];
		chs[index] = chs[swapIndex];
		chs[swapIndex] = temp;
		decreaseCount(chs[index]);
		return true;
	}
	
	private int getSwapIndex(int index) {
		char ch = chs[index];
		int size = 0;
		Character key = null;
		for (Character next : leftCharsCount.keySet()) {
			if (ch != next && leftCharsCount.get(next) > size) {
				size = leftCharsCount.get(next);
				key = next;
			}
		}
		if (null == key || size == 0) return -1; 
		for (int i = index+1; i < chs.length; i++)
			if (chs[i] == key) return i;
		
		return -1;
	}

	public static void main(String[] args) {
		RearrangeTasksDP tasks = new RearrangeTasksDP();
		System.out.println(tasks.minCompletion("AAABBBCCCCC", 1));
	}

}
