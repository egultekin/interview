package hackerrank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Vector;

public class SynchronousShopping {

    static int N;
    static int M;
    static int K;
    static Vector<HashMap<Integer, Edge>> neighbor;
    static TreeSet<State> states;
    static boolean[] marked;
    static int[] fishTypes;
    static int[][] cost;
    
    static class StateComparator implements Comparator<State> {

		@Override
		public int compare(State arg0, State arg1) {
			if (arg0.edge == arg1.edge && arg0.fishes == arg1.fishes && arg0.cost == arg1.cost) return 0;
			else if (arg0.cost > arg1.cost) return 1;
			else return -1;
		}
    }
    
    static class Edge {
    	int from, to;
    	int distance;
    	
    	public Edge(int from, int to, int d) {
			this.from = from;
			this.to = to;
			this.distance = d;
		}
    }
    
    static class State {
    	int edge, fishes, cost;

    	public State(int e, int f, int c) {
			this.edge = e;
			this.fishes = f;
			this.cost = c;
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
         states = new TreeSet<SynchronousShopping.State>(new StateComparator());
         
         states.add(new State(0, fishTypes[0], 0));
         
         while (!states.isEmpty()) {
        	 State current = states.pollFirst();
        	 
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
