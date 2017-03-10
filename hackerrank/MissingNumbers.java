package hackerrank;

import java.util.Scanner;
import java.util.TreeMap;

public class MissingNumbers {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) a[i] = sc.nextInt();
		int m = sc.nextInt();
		int[] count = new int[100];
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		for (int i = 0; i < m; i++) {
			int num = sc.nextInt();
			if (map.containsKey(num)) count[map.get(num)]++;
			else {
				count[map.size()]++;
				map.put(num, map.size());
			}
		}
		sc.close();
		
		for (int i = 0; i < n; i++)
			count[map.get(a[i])]--;
		StringBuilder build = new StringBuilder();
		for (Integer index : map.keySet())
			if (count[map.get(index)] > 0) if (build.length() == 0) build.append(index);
			else build.append(" ").append(index);
		
		System.out.println(build.toString());
	}

}
