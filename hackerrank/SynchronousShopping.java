package hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;

public class SynchronousShopping {

    static int N;
    static int M;
    static int K;
    static Vector<HashMap<Integer, Edge>> neighbor;
    static PriorityQueue<State> states;
    static boolean[] marked;
    static int[] fishTypes;
    static int[][] cost;
    
    static class Edge implements Comparable<Edge> {
    	int from, to;
    	int distance;
    	
    	public Edge(int from, int to, int d) {
			this.from = from;
			this.to = to;
			this.distance = d;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.distance < o.distance) return -1;
			else if (this.distance > o.distance) return 1;
			return 0;
		}
    }
    
    static class State implements Comparable<State> {
    	int edge, fishes, cost;

    	public State(int e, int f, int c) {
			this.edge = e;
			this.fishes = f;
			this.cost = c;
		}
    	
		@Override
		public int compareTo(State o) {
			if (cost > o.cost) return 1;
			else if (cost < o.cost) return -1;
			return 0;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof State)) return false;
			State s = (State) obj;
			return s.cost == this.cost && s.edge == this.edge && s.fishes == this.fishes;
		}
    	
    	
    }
    
    static void checkAndQueue(int edge, int fishes, int c) {
    	if (cost[edge][fishes] <= c) return;
    	
    	states.remove(new State(edge, fishes, cost[edge][fishes]));
    	cost[edge][fishes] = c;
    	states.add(new State(edge, fishes, c));
    }
      
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         N = sc.nextInt();
         M = sc.nextInt();
         K = sc.nextInt();
        
         fishTypes = new int[N];
         cost = new int[N][(1<<K)+10];
         neighbor = new Vector<HashMap<Integer,Edge>>();
         for (int i = 0; i < N; i++) {
               Arrays.fill(cost[i], Integer.MAX_VALUE);
               neighbor.add(new HashMap<Integer, Edge>());
               int T = sc.nextInt();
               for (int j = 0; j < T; j++)
                    fishTypes[i] = fishTypes[i] | 1<<(sc.nextInt()-1);
         }
        
         for (int i = 0; i < M; i++) {
               int first = sc.nextInt()-1;
               int second = sc.nextInt()-1;
               int d = sc.nextInt();
               
               neighbor.get(first).put(second, new Edge(first, second, d));
               neighbor.get(second).put(first, new Edge(second, first, d));
         }
         sc.close();
         
         marked = new boolean[N];
         states = new PriorityQueue<SynchronousShopping.State>();
         
         states.add(new State(0, fishTypes[0], 0));
         
         while (!states.isEmpty()) {
        	 State current = states.remove();
        	 
        	 marked[current.edge] = true;
        	 for (Edge e : neighbor.get(current.edge).values())
        		 checkAndQueue(e.to, current.fishes | fishTypes[e.to], current.cost + e.distance);		 
         }
         
         int min = Integer.MAX_VALUE;
         for (int i = 0; i < (1<<K); i++)
        	 for (int j = i; j < (1<<K); j++)
        		 if ((i | j) == ((1<<K)-1)) min = Math.min(min, Math.max(cost[N-1][i], cost[N-1][j])); 
         
         System.out.println(min);
    }

}
