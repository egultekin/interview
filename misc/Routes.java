package interview;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Routes {
	
	class Node {
		int from, to;
		int val;
		
		public Node(int f, int t, int d) {
			from = f;
			to = t;
			val = d;
		}
	}

	private static final int NODES = 26;
	private static final String DELIMETER = ", ";
	private int[][] adj;

	private static int indexOf(char ch) {
		int index = ch-'A';
		if (index < 0 || index >= NODES) throw new IllegalArgumentException();
		return ch-'A';
	}

	public String getDistance(String usingRoute) {
		if (null == usingRoute || usingRoute.length() == 0 || null == adj) return "NO SUCH ROUTE";
		int sum = 0;
		int from = indexOf(usingRoute.charAt(0));

		for (int i = 1; i < usingRoute.length(); i++) {
			int to = indexOf(usingRoute.charAt(i));
			if (adj[from][to] < Integer.MAX_VALUE) {
				sum += adj[from][to];
				from = to;
			} else return "NO SUCH ROUTE";
		}

		return String.valueOf(sum);
	}

	public int numberOfTripsMaxStops(String fromTo, int maxStops) {
		int total = 0;
		for (int i = 1; i <= maxStops; i++)
			total += numberOfTripsExactStops(fromTo, i);

		return total;
	}

	public int numberOfTripsExactStops(String fromTo, int exactStops) {
		// Assumption: Nodes are indexed in the [A..Z] range
		if (null == fromTo || fromTo.length() == 0 || exactStops < 1) return 0;
		int trips = 0;

		try {
			int from = indexOf(fromTo.charAt(0));
			int to = indexOf(fromTo.charAt(1));
			
			LinkedList<Node> queue = new LinkedList<Node>();
			for (int i = 0; i < NODES; i++) 
				if (adj[from][i] < Integer.MAX_VALUE) queue.add(new Node(from, i, 1));
			
			while (!queue.isEmpty()) {
				Node current = queue.poll();
				
				if (current.to == to && current.val == exactStops) trips++;
				if (current.val < exactStops)
					for (int i = 0; i < NODES; i++)
						if (adj[current.to][i] < Integer.MAX_VALUE)
							queue.add(new Node(current.to, i, current.val+1));
			}

			return trips;
		} catch (IllegalArgumentException iae) {
			System.out.println("Node indexing is not in the [A..Z] range.");
		}

		return 0;
	}

	public int shortestRoute(String fromTo) {
		// Assumption: Nodes are indexed in the [A..Z] range
		// Returns Integer.MAX_VALUE if there is no route between the first and last node

		if (null == fromTo || fromTo.length() != 2) return Integer.MAX_VALUE;
		int[][] min = new int[NODES][NODES];

		for (int i = 0; i < NODES; i++)
			for (int j = 0; j < NODES; j++)
				min[i][j] = Math.min(Integer.MAX_VALUE, adj[i][j]);

		try {
			int from = indexOf(fromTo.charAt(0));
			int to = indexOf(fromTo.charAt(1));

			for (int k = 0; k < NODES; k++)
				for (int i = 0; i < NODES; i++)
					for (int j = 0; j < NODES; j++)                    
						if (min[i][k] < Integer.MAX_VALUE && adj[k][j] < Integer.MAX_VALUE)
							min[i][j] = Math.min(min[i][j], min[i][k]+adj[k][j]);

			return min[from][to];
		} catch (IllegalArgumentException iae) {
			System.out.println("Node indexing is not in the [A..Z] range.");
		}

		return Integer.MAX_VALUE;
	}
	
	public int numberOfTripsMaxDistance(String fromTo, int maxDistance) {
		// Assumption: Nodes are indexed in the [A..Z] range
		if (null == fromTo || fromTo.length() == 0 || maxDistance < 1) return 0;
		LinkedList<Node> queue = new LinkedList<Node>();
		int trips = 0;
				
		try {
			int from = indexOf(fromTo.charAt(0));
			int to = indexOf(fromTo.charAt(1));
			
			for (int i = 0; i < NODES; i++) 
				if (adj[from][i] < Integer.MAX_VALUE && adj[from][i] < maxDistance) 
					queue.add(new Node(from, i, adj[from][i]));
			
			while (!queue.isEmpty()) {
				Node current = queue.poll();
				
				if (current.to == to) trips++;
				
				for (int i = 0; i < NODES; i++)
					if (adj[current.to][i] < Integer.MAX_VALUE 
							&& (adj[current.to][i]+current.val) < maxDistance) {
						queue.add(new Node(current.to, i, adj[current.to][i]+current.val));
					}
			}

			return trips;
		} catch (IllegalArgumentException iae) {
			System.out.println("Node indexing is not in the [A..Z] range.");
		}
		
		return 0;
	}

	public void loadFile(String inputFileName) throws NumberFormatException, SecurityException, IOException {
		// assumption: City identifiers are uppercase English letters i.e. [A..Z]
		adj = new int[NODES][NODES];
		BufferedReader reader = null;

		// initialize all routes to Infinity
		for (int i = 0; i < NODES; i++)
			Arrays.fill(adj[i], Integer.MAX_VALUE);

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
			String line;
			while (null != (line = reader.readLine())) {
				String[] tokens = line.split(DELIMETER);
				for (String token : tokens) {
					// Assumption: Distance between each city is in Integer boundaries
					int distance = Integer.parseInt(token.substring(2));
					adj[indexOf(token.charAt(0))][indexOf(token.charAt(1))] = distance;
				}
			}
		}
		finally {
			try {
				if (null != reader) {reader.close();
				reader = null;
				}
			} catch (IOException io) {
				System.out.println("Error occurred while closing the input file!");
			}
		}
	}
	
	public static void printResult(int testNo, String result) {
		System.out.printf("Output #%d: %s\n", testNo, result);
	}

	public static void main(String[] args) {
		if (args.length != 1) System.out.println("USAGE: Routes <inputFile>");
		Routes sol = new Routes();
		try {
			sol.loadFile(args[0]);
			printResult(1, sol.getDistance("ABC"));
			printResult(2, sol.getDistance("AD"));
			printResult(3, sol.getDistance("ADC"));
			printResult(4, sol.getDistance("AEBCD"));
			printResult(5, sol.getDistance("AED"));
			printResult(6, String.valueOf(sol.numberOfTripsMaxStops("CC", 3)));
			printResult(7, String.valueOf(sol.numberOfTripsExactStops("AC", 4)));
			printResult(8, String.valueOf(sol.shortestRoute("AC")));
			printResult(9, String.valueOf(sol.shortestRoute("BB")));
			printResult(10, String.valueOf(sol.numberOfTripsMaxDistance("CC", 30)));
		}  catch (SecurityException e) {
			System.out.println("Cannot access input file for security reasons!");
		}  catch (NumberFormatException e) {
			System.out.println("Unexpected token encountered in input file!");
		} catch (IllegalArgumentException iae) {
			System.out.println("Node indexing is not in [A..Z] range in the input file!");
		} catch (FileNotFoundException fne) {
			System.out.println("Input file not found!");
		} catch (IOException e) {
			System.out.println("Error occurred while reading from input file!");
		}
	}
}