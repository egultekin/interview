package hackerrank;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.TreeSet;

public class DijkstraShortReach {

	static int sanitize(int val) {
		if (val == Integer.MAX_VALUE) return -1;
		return val;
	}
	
	static int sum(int a, int b) {
		if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE) return Integer.MAX_VALUE;
		return a+b;
	}
	
	static class Pair<K extends Comparable<? super K>> implements Comparable<Pair<K>> {
		K first, second;
		
		public Pair(K f, K s) {
			first = f;
			second = s;
		}

		@Override
		public int compareTo(Pair<K> o) {
			if (o.first.equals(first) && o.second.equals(second)) return 0;
			else if (second.compareTo(o.second) > 0) return 1;
			else return -1;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Pair<?>)) return false;
			@SuppressWarnings("unchecked")
			Pair<K> pair = (Pair<K>) obj;
			return this.first == pair.first && this.second == pair.second;
		}
	}
	
	static class FastReader {
		InputStream in;
		byte[] buf = new byte[1024];
		int chars = 0;
		int cChar = 0;
		
		public FastReader(InputStream s) {
			this.in = s;
		}
		
		private int read() {
			if (chars < 0) throw new InputMismatchException();
			if (cChar >= chars) {
				cChar = 0;
				try {
					chars = in.read(buf);
					if (chars == -1) return -1;
				} catch (IOException e) {
					throw new InputMismatchException();
				}
			}
			
			return buf[cChar++];
		}
		
		public int nextInt() {
			int ch = read();
			while (isSpaceChar(ch)) ch = read();
			int sign = 1;
			if (ch == '-') {
				sign = -1;
				ch = read();
			}
			
			int res = 0;
			do {
				if (ch < '0' || ch > '9') throw new InputMismatchException();
				int digit = ch - '0';
				res = res*10 + digit;
				ch = read();
			} while (!isSpaceChar(ch));
			
			return res*sign;
		}
		
		private String readString() {
			int ch = read();
			while (isSpaceChar(ch)) ch = read();
			StringBuilder build = new StringBuilder();
			
			do {
				build.appendCodePoint(ch);
				ch = read();
			} while (!isSpaceChar(ch));
			
			return build.toString();
		}
		
		public String next() {
			return readString();
		}
		
		public void close() {
			try {
				in.close();
			} catch (IOException e) {}
			finally {
				in = null;
			}
		}
		
		private boolean isSpaceChar(int ch) {
			return ch == '\t' || ch == '\n' || ch == -1 || ch == ' ' || ch == '\r';
		}
	}
	public static void main(String[] args) {
		FastReader sc = new FastReader(System.in);
		int samples = sc.nextInt();
		
		for (int i = 0; i < samples; i++) {
			int nodes = sc.nextInt();
			int edges = sc.nextInt();
			HashMap<Integer, Set<Integer>> node = new HashMap<Integer, Set<Integer>>();
			int[] cost = new int[nodes+1];
			int[] prev = new int[nodes+1];
			int[][] distance = new int[nodes+1][nodes+1];
			boolean[] marked = new boolean[nodes+1];
			
			for (int j = 0; j <= nodes; j++) Arrays.fill(distance[j], Integer.MAX_VALUE);
			Arrays.fill(cost, Integer.MAX_VALUE);
			Arrays.fill(prev, -1);
			
			for (int j = 0; j < edges; j++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				int dist = sc.nextInt();
				
				if (distance[from][to] > dist) {
					distance[from][to] = dist;
					distance[to][from] = dist;
				}
			}
			
			for (int j = 1; j <= nodes; j++)
				for (int k = 1; k <= nodes; k++) {
					if (j == k || distance[j][k] == Integer.MAX_VALUE) continue;
					Set<Integer> adjFrom = node.get(j);
					Set<Integer> adjTo = node.get(k);
					
					if (null == adjFrom) {
						adjFrom = new HashSet<Integer>();
						node.put(j, adjFrom);
					}
					
					if (null == adjTo) {
						adjTo = new HashSet<>();
						node.put(k, adjTo);
					}
					
					adjFrom.add(k);
					adjTo.add(j);
				}
			
			int start = sc.nextInt();
			cost[start] = 0;
			marked[start] = true;
			TreeSet<Pair<Integer>> queue = new TreeSet<Pair<Integer>>();
			queue.add(new Pair<Integer>(start, 0));
			
			while (!queue.isEmpty()) {
				Pair<Integer> c = queue.pollFirst();
				for (Integer adj : node.get(c.first)) {
					int sum = sum(cost[c.first], distance[c.first][adj]);
					if (cost[adj] > sum) {
						queue.remove(new Pair<Integer>(adj, cost[adj]));
						cost[adj] = sum;
						prev[adj] = c.first;
						queue.add(new Pair<Integer>(adj, sum));
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
