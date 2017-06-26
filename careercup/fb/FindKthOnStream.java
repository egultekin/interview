package careercup.fb;

import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;

class FindKthOnStream {
	public static int findNumber(Iterator<Integer> nums, double percent) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int n = 0;
		while (nums.hasNext()) {
			pq.add(nums.next());
			n++;
		}
		
		double delta = (double) 1/n;
		double count = delta;
		int number = pq.poll();
		while (count < percent) {
			number = pq.poll();
			count += delta;
		} 
		
		return number;
	}
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(new Integer[] {1, 3, 2, 5, 4, 6, 7, 9 ,8 ,10});
		System.out.println(findNumber(list.iterator(), 0.6));
		list = Arrays.asList(new Integer[] {8, 10});
		System.out.println(findNumber(list.iterator(), 0.6));
		list = Arrays.asList(new Integer[] { 4 });
		System.out.println(findNumber(list.iterator(), 0.1));
		list = Arrays.asList(new Integer[] {23, 68, -7, 1, 0 ,9, 444, 999, 12, 45});
		System.out.println(findNumber(list.iterator(), 0.71));
	}
}