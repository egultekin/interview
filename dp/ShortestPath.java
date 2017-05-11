package dp;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Arrays;

class ShortestPath {
  static class Edge implements Comparable<Edge> {
    int v, w;

    public Edge(int vertex, int weight) {
      v = vertex;
      w = weight;
    }

	@Override
	public int compareTo(Edge o) {
		if (w < o.w) return -1;
		else if (w > o.w) return 1;
		return 0;
	}
  }

  public void add(int i, int v, int w) {
    Vector<Edge> vec = adj.get(i);
    vec.add(new Edge(v, w));
  }

  public ShortestPath(int size) {
    s = size+1;
    adj = new ArrayList<Vector<Edge>>(s);
    for (int i=0; i < s; i++) adj.add(i, new Vector<Edge>());
  }

  public int[] shortest(int st) {
    boolean[] marked = new boolean[s];
    int[] min = new int[s];
    Arrays.fill(min, Integer.MIN_VALUE);
    min[st] = 0;
    PriorityQueue<Edge> minPQ = new PriorityQueue<Edge>();
    for (Edge e : adj.get(st)) minPQ.add(e);
    while (!minPQ.isEmpty()) {
      Edge c = minPQ.poll();
      if (!marked[c.v]) {
        marked[c.v] = true;
        min[c.v] = c.w;
        for (Edge e : adj.get(c.v)) if (!marked[e.v]) minPQ.add(new Edge(e.v, min[c.v]+e.w));
      }
    }

    return min;
  }

  private int s;
  private ArrayList<Vector<Edge>> adj;
  
  public static void main(String[] args) {
	  Scanner sc = new Scanner(System.in);
	  int size = sc.nextInt();
	  ShortestPath sp = new ShortestPath(size);
	  while (true) {
		  int v1 = sc.nextInt();
		  if (v1 == -1) break;
		  int v2 = sc.nextInt();
		  int w = sc.nextInt();
		  sp.add(v1, v2, w);
		  sp.add(v2, v1, w);
	  }
	  
	  int samples = sc.nextInt();
	  int[] min = sp.shortest(1);
	  while (samples-- > 0) {
		  int q = sc.nextInt();
		  System.out.printf("Node %d is %d steps far away from %d\n", q, min[q], 1);
	  }
	  
	  sc.close();
  }
}

//6
//1 2 2
//2 3 4
//1 3 7
//1 5 5
//3 4 7
//3 6 6
//5 6 9
//-1
//5 2 3 4 5 6