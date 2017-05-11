package dp;

import java.util.PriorityQueue;
import java.util.Vector;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

class ShortestPathConstrained {
  static class Edge implements Comparable<Edge> {
    int v, w, m;

    public Edge(int vertex, int weight, int money) {
      v = vertex;
      w = weight;
      m = money;
    }

    @Override
    public int compareTo(Edge o) {
      if (w < o.w) return -1;
      else if (w > o.w) return 1;
      return 0;
    }
  }

  public ShortestPathConstrained(Vector<Vector<Edge>> graph) {
    g = graph;
  }

  public int[] shortest(int st, int mon) {
    int sz = g.size();
    PriorityQueue<Edge> minPQ = new PriorityQueue<>();
    boolean[][] marked = new boolean[sz][mon+1];
    int[] min = new int[sz];
    Arrays.fill(min, Integer.MAX_VALUE);
    min[st] = 0;
    marked[st][mon] = true;
    Iterator<Edge> ie = adj(st);
    while (ie.hasNext()) {
      Edge e = ie.next();
      if (mon >= e.m) {
        if (min[e.v] > min[st]+e.w) min[e.v] = min[st]+e.w;
        minPQ.add(new Edge(e.v, e.w, mon-e.m));
      }
    }

    while (!minPQ.isEmpty()) {
      Edge e = minPQ.poll();
      marked[e.v][e.m] = true;
      ie = adj(e.v);
      while (ie.hasNext()) {
        Edge n = ie.next();
        if (e.m >= n.m) {
          if (min[n.v] > min[e.v]+n.w) min[n.v] = min[e.v]+n.w;
          if (!marked[n.v][e.m-n.m]) minPQ.add(new Edge(n.v, e.w+n.w, e.m-n.m));
        }
      }
    }

    return min;
  }

  private Iterator<Edge> adj(int v) {
    return g.get(v).iterator();
  }

  private Vector<Vector<Edge>> g;

  public static boolean invalidVertex(int v, int sz) {
    if (v < 1 || v > sz) {
      System.out.println("invalid argument detected. skipping.");
      return true;
    }
    return false;
  }

  public static boolean invalidWeight(int w) {
    if (w <= 0) {
      System.out.println("invalid weight detected. skipping.");
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Vector<Vector<Edge>> graph = new Vector<Vector<Edge>>();
    int sz = sc.nextInt();
    int st = sc.nextInt();
    if (invalidVertex(st, sz)) throw new IllegalArgumentException("Invalid vertex specified.");
    int mon = sc.nextInt();
    while (sz -- >= 0) graph.add(new Vector<Edge>());
    while (true) {
      int v = sc.nextInt();
      if (v == -1) break;
      if (invalidVertex(v, sz)) continue;
      int d = sc.nextInt();
      if (invalidVertex(d, sz)) continue;
      int w = sc.nextInt();
      if (invalidWeight(w)) continue;

      graph.get(v).add(new Edge(d, w, mon));
      graph.get(d).add(new Edge(v, w, mon));
    }
    sc.close();

    ShortestPathConstrained sp = new ShortestPathConstrained(graph);
    int[] min = sp.shortest(st, mon);
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < min.length; i++) sb.append(String.format("%d=%d\n", i, min[i]));
    System.out.print(sb.toString());
  }
}
