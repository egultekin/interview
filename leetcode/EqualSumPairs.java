package leetcode;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

public class EqualSumPairs {
	static class Pair {
		int a, b;
		public Pair(int ind1, int ind2) { a = ind1; b = ind2; }
	}
	
	Map<Integer, Vector<Pair>> map;
	
	public EqualSumPairs(int[] a) {
		map = new HashMap<Integer, Vector<Pair>>();
		for (int i=0; i < a.length; i++)
			for (int j=i+1; j < a.length; j++) {
				int s = a[i]+a[j];
				Vector<Pair> v = map.get(s);
				if (v == null) {
					v = new Vector<Pair>();
					map.put(s, v);
				}
				v.add(new Pair(i, j));
			}
	}
	
	public List<List<Integer>> allQuadruples() {
		List<List<Integer>> res = new Vector<List<Integer>>();
		Iterator<Vector<Pair>> it = map.values().iterator();
		while (it.hasNext()) {
			Vector<Pair> v = it.next();
			for (int i=0; i < v.size(); i++)
				for (int j=i+1; j < v.size(); j++) {
					List<Integer> l = new Vector<Integer>();
					Pair x = v.get(i), y = v.get(j);
					l.add(x.a);
					l.add(x.b);
					l.add(y.a);
					l.add(y.b);
					res.add(l);
				}
		}
		return res;
	}
	
	List<List<Integer>> equivalentPairsOf4(int[] arr) {
		HashMap<Integer, List<Pair>> map = new HashMap<>();
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				int sum = arr[i] + arr[j];
				if (!map.containsKey(sum))
					map.put(sum, new Vector<Pair>());
				map.get(sum).add(new Pair(i, j));
			}
		}

		List<List<Integer>> result = new Vector<>();
		for (List<Pair> v : map.values()) {
			if (v.size() > 1) {
				for (Pair p : v) {
					result.add(new Vector<Integer>());
					result.get(result.size() - 1).add(p.a);
					result.get(result.size() - 1).add(p.b);
				}
			}
		}
		return result;
	}
	
	public static void print(List<List<Integer>> list) {
		StringBuilder sb = new StringBuilder();
		for (List<Integer> n : list) {
			Iterator<Integer> it = n.iterator();
			if (it.hasNext()) sb.append(it.next());
			while (it.hasNext()) sb.append(',').append(it.next());
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		EqualSumPairs esp = new EqualSumPairs(new int[] {1, 5, 7, 2, 6, 3, 4});
		//EqualSumPairs esp = new EqualSumPairs(new int[] {2, 2, 2, 2, 2});
		print(esp.allQuadruples());
		print(esp.equivalentPairsOf4(new int[] {1, 5, 7, 2, 6, 3, 4}));
	}
}
