package hackerrank;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Pairs {

	static int pairs(int[] a,int k) {
		HashSet<Integer> set = new HashSet<Integer>();
		Arrays.sort(a);
		int count = 0;
		for (int i = 0; i < a.length; i++) set.add(a[i]);
		for (int i = 0; i < a.length; i++) {
			if (set.contains(a[i]+k)) count++;
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int res;

		String n = in.nextLine();
		String[] n_split = n.split(" ");

		int _a_size = Integer.parseInt(n_split[0]);
		int _k = Integer.parseInt(n_split[1]);

		int[] _a = new int[_a_size];
		int _a_item;
		String next = in.nextLine();
		String[] next_split = next.split(" ");

		for(int _a_i = 0; _a_i < _a_size; _a_i++) {
			_a_item = Integer.parseInt(next_split[_a_i]);
			_a[_a_i] = _a_item;
		}

		in.close();
		res = pairs(_a,_k);
		System.out.println(res);
	}
}
