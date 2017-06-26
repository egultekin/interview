package leetcode;

import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import java.util.HashMap;

public class RandomIndicesMatchingGivenNumber {

	Map<Integer, Vector<Integer>> map;
	
	public RandomIndicesMatchingGivenNumber() {
		map = new HashMap<Integer, Vector<Integer>>();
	}
	
	public void initMap(int[] a) {
		for (int i=0; i < a.length; i++) {
			Vector<Integer> v = map.get(a[i]);
			if (null == v) {
				v = new Vector<Integer>();
				map.put(a[i],  v);
			}
			v.add(i);
		}
	}
	
	public int index(int a) {
		Vector<Integer> v = map.get(a);
		if (null == v) return -1;
		int ind = (int) (((double)v.size())*Math.random());
		return v.get(ind);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] a = {1, 1, 2, 3, 3, 3};
		RandomIndicesMatchingGivenNumber rim = new RandomIndicesMatchingGivenNumber();
		rim.initMap(a);
		while (N-- > 0) System.out.println(rim.index(sc.nextInt()));
		sc.close();
	}

}
