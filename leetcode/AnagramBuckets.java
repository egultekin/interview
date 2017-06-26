package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class AnagramBuckets {
	private static int hash(String word) {
		char[] chs = word.toCharArray();
		int hash = 0;
		int start = Math.min('a', 'A');
		for (int i=0; i < chs.length; i++)
			hash = hash + chs[i] - start;
		return hash;
	}
	
	private static boolean containsAnagrams(List<String> bucket, String word) {
		if (bucket.isEmpty()) return true;
		String first = bucket.get(0);
		Map<Character, Integer> map = new HashMap<>();
		char[] chs = first.toCharArray();
		for (int i=0; i < chs.length; i++)
			if (!map.containsKey(chs[i])) map.put(chs[i], 1);
			else map.put(chs[i], map.get(chs[i])+1);
		char[] wordChrs = word.toCharArray();
		for (int i=0; i < wordChrs.length; i++) {
			if (!map.containsKey(wordChrs[i])) return false;
			map.put(wordChrs[i], map.get(wordChrs[i])-1);
			if (map.get(wordChrs[i]) == 0) map.remove(wordChrs[i]);
		}
		if (!map.isEmpty()) return false;
		return true;
	}
	
	private static void printBucket(List<String> bucket) {
		StringBuilder sb = new StringBuilder();
		for (String word : bucket)
			if (sb.length() == 0) sb.append(word);
			else sb.append(',').append(word);
		System.out.println(sb.toString());
	}
	
	public static void printAnagrams(List<String> list) {
		Map<Integer, List<List<String>>> map = new HashMap<>();
		for (String word : list) {
			int hash = hash(word);
			List<List<String>> buckets = map.get(hash);
			if (null == buckets) {
				buckets = new Vector<List<String>>();
				map.put(hash, buckets);
			}
			List<String> bucket = null;
			Iterator<List<String>> it = buckets.iterator();
			while (it.hasNext()) {
				bucket = it.next();
				if (!containsAnagrams(bucket, word)) bucket = null;
				else break;
			}
			if (null == bucket) {
				bucket = new Vector<String>();
				buckets.add(bucket);
			}
			bucket.add(word);
		}
		Iterator<List<List<String>>> it = map.values().iterator();
		while (it.hasNext()) {
			List<List<String>> buckets = it.next();
			for (List<String> bucket : buckets) printBucket(bucket);
		}
	}

	public static void main(String[] args) {
		printAnagrams(Arrays.asList(new String[] { "rats", "star", "arts", "cie", "ice" }));
	}

}
