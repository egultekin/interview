package hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class JourneyToTheMoon {

	public static void main(String[] args) throws Exception{
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = bfr.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int I = Integer.parseInt(temp[1]);

		Hashtable<Integer, List<Integer>> astronauts = new Hashtable<>();

		for(int i = 0; i < I; i++){
			temp = bfr.readLine().split(" ");
			int a = Integer.parseInt(temp[0]);
			int b = Integer.parseInt(temp[1]);
			
			if (!astronauts.containsKey(a)) astronauts.put(a, new ArrayList<Integer>());
			astronauts.get(a).add(b);
			if (!astronauts.containsKey(b)) astronauts.put(b, new ArrayList<Integer>());
			astronauts.get(b).add(a);
			// Store a and b in an appropriate data structure of your choice
		}

		int[] countries = new int[N];
		Arrays.fill(countries, -1);
		int countrySum = 0;
		for (int i = 0; i < countries.length; i++)
			if (countries[i] == -1) countries = mark(astronauts, i, countries, countrySum++);

		int[] histogram = new int[countrySum];
		for (int i = 0; i < N; i++)
			histogram[countries[i]]++;

		long combinations = (long)(N/2)*(N+1);

		for (int i = 0; i < countrySum; i++) {
			long diff = (long)histogram[i]*(histogram[i]+1)/2;
			combinations -= diff;
		}
		// Compute the final answer - the number of combinations

		System.out.println(combinations);

	}

	static int[] mark(Hashtable<Integer, List<Integer>> g, int country, int[] countries, int next) {
		LinkedList<Integer> queue = new LinkedList<>();

		queue.add(country);
		countries[country] = next;

		while (!queue.isEmpty()) {
			int c = queue.poll();

			List<Integer> adjList = g.get(c);
			if (null != adjList)
				for (int adj : adjList)
					if (countries[adj] == -1) {
						countries[adj] = next;
						queue.add(adj);
					}
		}

		return countries;
	}

}
