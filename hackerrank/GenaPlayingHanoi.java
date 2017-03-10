package hackerrank;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

public class GenaPlayingHanoi {

	static class Node {
		Vector<Stack<Integer>> vs;
		int moves;
		int from, to;
		int hash;
		int[] hashes;
		
		public Node(Node node) {
			this();
			if (null == node)
				for (int i = 0; i < 4; i++) vs.add(new Stack<Integer>());
			else {
				for (Stack<Integer> s : node.vs) vs.add((Stack<Integer>)s.clone());
				moves = node.moves;
				hash = node.hash;
				hashes = node.hashes.clone();
			}
		}
		
		private Node() {
			vs = new Vector<Stack<Integer>>();
			moves = 0;
			from = -1;
			to = -1;
			hash = 0;
			hashes = new int[4];
		}
		
		@Override
		public boolean equals(Object obj) {
//			if (null == obj) return false;
//			if (obj.getClass() != getClass()) return false;
//			Node other = (Node) obj;
//			if (!Arrays.deepEquals(vs.toArray(), other.vs.toArray())) return false;
//			return true;
			return hashCode() == obj.hashCode();
		}
		
		@Override
		public int hashCode() {
			return hash;
		}
		
		void push(int stack, int val) {
			vs.get(stack).push(val);
			int prev = hashes[stack];
			hashes[stack] = 5*prev+val;
			//hash = hash + Math.round(Math.pow(5.0f, stack))* (hashes[stack] - prev);
		}
		
		int pop(int stack) {
			int val = vs.get(stack).pop();
			int prev = hashes[stack];
			hashes[stack] = (prev-val)/5;
			hash = hash + hashes[stack]-prev;
			return val;
		}
		
		int peek(int stack) {
			return vs.get(stack).peek();
		}
		
		boolean isEmpty(int stack) {
			return vs.get(stack).isEmpty();
		}
	}
	
	static void move(Node node) {
		if (node.from == -1 || node.to == -1) return;
		if (node.vs.get(node.from).isEmpty()) throw new IllegalArgumentException();
		node.moves++;
		int pop = node.vs.get(node.from).pop();
		node.vs.get(node.to).push(pop);
	}
	
	static Iterable<Node> validMoves(Node node) {
		LinkedList<Node> valid = new LinkedList<GenaPlayingHanoi.Node>();
		for (int from = 0; from < 4; from++)
			for (int to = 0; to < 4; to++)
				if (from != to && (from != node.to || to != node.from) 
						&& !node.isEmpty(from)
						&& (node.isEmpty(to) || node.peek(from) < node.peek(to))) { 
					Node n = new Node(node);
					n.from = from;
					n.to = to;
					n.moves++;
					n.push(to, n.pop(n.from));
					valid.add(n);
				}
		return valid;
	}
		
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int a[] = new int[N];
        for(int a_i=0; a_i < N; a_i++){
            a[a_i] = in.nextInt()-1;
        }
        
        in.close();
        

        Node node = new Node(null);
        for (int i = N-1; i >= 0; i--)
        	node.push(a[i], i);
        LinkedList<Node> queue = new LinkedList<GenaPlayingHanoi.Node>();
        Set<Node> marked = new HashSet<GenaPlayingHanoi.Node>();
        queue.add(node);
        
        Node c = null;
        int min = Integer.MAX_VALUE;
        int max = 0;
        int iterations = 0;
        while (!queue.isEmpty()) {
        	c = queue.pollFirst();

        	marked.add(c);
        	iterations++;
        	//move(c);

        	if (null != c && c.vs.get(0).size() == N && c.peek(0) == 0 && c.moves < min) {
        		min = c.moves;
        		break;
        	}
        	for (Node next : validMoves(c))
        		if (!marked.contains(next)) queue.add(next);
        	if (queue.size() > max) max = queue.size();
        }
        
        System.out.println(iterations);
        System.out.println(max);
        System.out.println(min);
    }

}
