package careercup.fb;

import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;

class DetectCycleDirectedGraph {
	private Map<Integer, Set<Integer>> graph;
	private boolean directedGraph;
	private int[] ids;
	private int[] size;
	private int nVertices;
	
	public DetectCycleDirectedGraph(boolean directed) {
		graph = new HashMap<>();
		directedGraph = directed;
		nVertices = 0;
	}
	
	public boolean isDirected() {
		return directedGraph;
	}
	
	public void insertEdge(int from, int to) {
		Set<Integer> edges = null;
		if (!graph.containsKey(to)) nVertices++;
		if (!graph.containsKey(from)) {
			nVertices++;
			edges = new HashSet<Integer>();
			graph.put(from, edges);
		} else edges = graph.get(from);
		edges.add(to);
		
		if (!isDirected()) {
			if (!graph.containsKey(to)) {
				edges = new HashSet<Integer>();
				graph.put(to, edges);
			} else edges = graph.get(to);
			edges.add(from);
		}
	}
	
	private void makeSet(int vertex) {
		ids[vertex] = vertex;
		size[vertex] = 1;
	}
	
	private int find(int vertex) {
		while (vertex != ids[vertex]) vertex = ids[vertex];
		return vertex;
	}
	
	private void union(int compVertex, int compEdge) {
		if (size[compVertex] > size[compEdge]) {
			ids[compEdge] = compVertex;
			size[compVertex] = size[compVertex]+size[compEdge];
		} else {
			ids[compVertex] = compEdge;
			size[compEdge] = compVertex;
		}
	}
	
	public boolean isCyclic() {
		ids = new int[nVertices+1];
		size = new int[nVertices+1];
		Iterator<Integer> vertexIterator = graph.keySet().iterator();
		while (vertexIterator.hasNext()) {
			int vertex = vertexIterator.next();
			makeSet(vertex);
		}
		
		vertexIterator = graph.keySet().iterator();
		while (vertexIterator.hasNext()) {
			int vertex = vertexIterator.next();
			Iterator<Integer> edgeIter = graph.get(vertex).iterator();
			while (edgeIter.hasNext()) {
				int edge = edgeIter.next();
				int compVertex = find(vertex);
				int compEdge = find(edge);
				if (compVertex != compEdge) union(compVertex, compEdge); 
				else {
					System.out.printf("Found a cycle between vertex %d and vertex %d.\n", vertex, edge);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String parse = null;
		try {
			DetectCycleDirectedGraph dc = new DetectCycleDirectedGraph(true);
			while ( null != (parse = sc.nextLine()) && !parse.equals("")) {
				String[] vertices = parse.split(" ");
				if (vertices.length != 2) throw new Exception("Invalid graph format detected.");
				dc.insertEdge(Integer.parseInt(vertices[0]), Integer.parseInt(vertices[1]));
			}
			
			System.out.println(dc.isCyclic());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sc.close();
		}
	}
	
}