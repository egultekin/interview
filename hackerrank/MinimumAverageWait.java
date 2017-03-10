package hackerrank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MinimumAverageWait {

	public static class Customer implements Comparable<Customer> {
		public int comes;
		public int takes;
		public boolean served;
		public int index;
		
		public Customer(int c, int t) {
			comes = c;
			takes = t;
			served = false;
		}

		@Override
		public int compareTo(Customer o) {
			return ((Integer)this.takes).compareTo((Integer)o.takes);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Customer[] customers = new Customer[N];
		for (int i = 0; i < N; i++) {
			int timeComes = sc.nextInt();
			int lengthOrder = sc.nextInt();
			customers[i] = new Customer(timeComes, lengthOrder);
		}
		sc.close();

		Arrays.sort(customers, new Comparator<Customer>() {

			@Override
			public int compare(Customer o1, Customer o2) {
				return ((Integer)o1.comes).compareTo((Integer)o2.comes);
			}
		});
		
		long total = 0;
		long now = 0;
		PriorityQueue<Customer> minPQ = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			Customer next = customers[i];
			next.index = i;
			if (minPQ.isEmpty()) {
				minPQ.add(next);
				now = next.comes;
			}
			else {
				while (!minPQ.isEmpty() && Math.max(now, (long) minPQ.peek().comes)+minPQ.peek().takes < next.comes) {
					Customer min = minPQ.poll();
					now = Math.max(now, (long) min.comes);
					now += min.takes;
					total += now-min.comes;
				}
				
				if (!minPQ.isEmpty() 
						&& next.takes < minPQ.peek().takes 
						//&& (long) next.comes-Math.max(now, minPQ.peek().comes)+next.takes > (long) Math.max(now, minPQ.peek().comes)+minPQ.peek().takes-next.comes) {
						&& (long) Math.max(now, (long) next.comes)-minPQ.peek().comes+next.takes > (long) Math.max(now, minPQ.peek().comes)+minPQ.peek().takes-next.comes
						&& (long) Math.max(now, (long) minPQ.peek().comes)+minPQ.peek().takes-minPQ.peek().comes + (long) Math.max(now, (long) minPQ.peek().comes)+minPQ.peek().takes+next.takes-next.comes 
						< (long) Math.max(now, (long) next.comes)+next.takes-next.comes + (long) Math.max(now, (long) next.comes)+next.takes+minPQ.peek().takes-minPQ.peek().comes) {
				
					Customer min = minPQ.poll();
					now = Math.max(now, (long) min.comes);
					now += min.takes;
					total += now-min.comes;
				}
				
				minPQ.add(next);
			}
		}
		
		while (!minPQ.isEmpty()) {
			Customer min = minPQ.poll();
			now = Math.max(now, (long) min.comes);
			now += min.takes;
			total += now-min.comes;
		}
		
		System.out.println(total);
		System.out.println(total/(long)N);

	}

}
