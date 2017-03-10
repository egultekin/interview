package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Apothecary {
	public int[] balance(int W) {
		return toSortedArray(helper(W, new HashSet<Integer>())); 
	}
	
	private int[] toSortedArray(Set<Integer> set) {
		int[] result = new int[set.size()];
		int i = 0;
		for (Integer o : set) result[i++] = o;
		Arrays.sort(result);
		return result;
	}
	
	private Set<Integer> helper(int W, Set<Integer> s) {
		if (W == 0) return s;
		int sum = 1, coeff = 1;
		while (sum < Math.abs(W)) {
			coeff *= 3;
			sum += coeff;
		}
		
		if (W < 0) coeff *= -1;		
		s.add(coeff);
		return helper(W-coeff, s);
	}
	
	public void print(int[] result) {
		System.out.print("{");
		for (int i = 0; i < result.length; i++)
		{
			System.out.print(result[i]);
			if (i < result.length-1) System.out.print(",");
		}
			
		System.out.print("}");
	}

	public static void main(String[] args) {
		int W = 1000000;
		Apothecary sol = new Apothecary();
		sol.print(sol.balance(W));
	}

}
