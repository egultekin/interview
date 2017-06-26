package careercup.fb;

import java.util.Vector;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class PrimeFactorization {
	
	TreeSet<Integer> primeSet = null;
	
	private void primeFactorization(int number, List<Integer> factors) {
		if (number == 1) return;
		NavigableSet<Integer> primes = getPrimesEqualLower(number);
		Iterator<Integer> it = primes.descendingIterator();
		Integer next = it.next();
		while (number % next > 0 && it.hasNext()) next = it.next();
		if (number % next > 0) next = number;
		factors.add(next);
		primeFactorization(number/next, factors);
	}
	
	private NavigableSet<Integer> getPrimesEqualLower(int number) {
		if (null == primeSet) primeSet = getAllPrimesEqualLower(number);
		return primeSet.headSet(number, true);
	}
	
	private TreeSet<Integer> getAllPrimesEqualLower(int number) {
		boolean[] notPrime = new boolean[number+1];
		for (int factor = 2; factor*factor <= number; factor++)
			if (!notPrime[factor])
				for (int j = factor; j*factor <= number; j++)
					notPrime[factor*j] = true;
		List<Integer> primes = new Vector<Integer>();
		for (int i=2; i < notPrime.length; i++)
			if (!notPrime[i]) primes.add(i);
		return new TreeSet<Integer>(primes);
	}
	
	public String getPrimeFactors(int number) {
		if (number == 1) return "1";
		List<Integer> factors = new Vector<Integer>();
		primeFactorization(number, factors);
		StringBuilder sb = new StringBuilder();
		for (Integer factor : factors) {
			if (sb.length() == 0) sb.append(factor);
			else sb.append('*').append(factor);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		PrimeFactorization pf = new PrimeFactorization();
		System.out.println(pf.getPrimeFactors(258768));
	}

}
