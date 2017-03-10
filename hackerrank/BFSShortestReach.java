package hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class BFSShortestReach {

	static int sanitize(int val) {
		if (val == Integer.MAX_VALUE) return -1;
		return val;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int samples = sc.nextInt();
		
		for (int i = 0; i < samples; i++) {
			int nodes = sc.nextInt();
			int edges = sc.nextInt();
			HashMap<Integer, LinkedList<Integer>> node = new HashMap<Integer, LinkedList<Integer>>();
			int[] cost = new int[nodes+1];
			boolean[] marked = new boolean[nodes+1];
			
			Arrays.fill(cost, Integer.MAX_VALUE);
			
			for (int j = 0; j < edges; j++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				
				LinkedList<Integer> adjFrom = node.get(from);
				LinkedList<Integer> adjTo = node.get(to);
				
				if (null == adjFrom) {
					adjFrom = new LinkedList<Integer>();
					node.put(from, adjFrom);
				}
				
				if (null == adjTo) {
					adjTo = new LinkedList<>();
					node.put(to, adjTo);
				}
				
				adjFrom.add(to);
				adjTo.add(from);
			}
			
			int start = sc.nextInt();
			cost[start] = 0;
			marked[start] = true;
			LinkedList<Integer> queue = new LinkedList<>();
			queue.add(start);
			
			while (!queue.isEmpty()) {
				int c = queue.pollFirst();
				
				if (!node.containsKey(c)) continue;
				for (int next : node.get(c)) {
					if (cost[next] > cost[c] + 6) cost[next] = cost[c] + 6;
					if (!marked[next]) {
						queue.addLast(next);
						marked[next] = true;
					}
				}
			}
			
			StringBuilder result = new StringBuilder();
			for (int j = 1; j <= nodes; j++)
				if (j != start) 
					if (j < nodes) result.append(sanitize(cost[j])).append(" ");
					else result.append(sanitize(cost[j]));
			
			System.out.println(result.toString());
			
		}
		sc.close();
	}

}
