package hackerrank;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FindTheRunningMedian {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		PriorityQueue<Integer> minPQ = new PriorityQueue<>();
		PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		
		for (int i = 0; i < N; i++) {
			int next = sc.nextInt();
			if (minPQ.isEmpty()) maxPQ.add(next);
			else if (next <= maxPQ.peek()) maxPQ.add(next);
			else if (minPQ.isEmpty() || next >= minPQ.peek()) minPQ.add(next);
			else maxPQ.add(next);
			
			if (minPQ.size() > maxPQ.size()) maxPQ.add(minPQ.poll());
			else if (maxPQ.size() - minPQ.size() > 1) minPQ.add(maxPQ.poll());
			
			if (maxPQ.size() == minPQ.size()) System.out.printf("%.1f\n", ((float)maxPQ.peek()+minPQ.peek())/2);
			else System.out.printf("%.1f\n", (float)maxPQ.peek());
		}
		
		sc.close();

	}

}
