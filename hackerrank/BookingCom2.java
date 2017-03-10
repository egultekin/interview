package hackerrank;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class BookingCom2 {

	static class Pair implements Comparable<Pair>{
		int id, value;

		public Pair(int i, int v) {
			id = i;
			value = v;
		}
		
		@Override
		public int compareTo(Pair o) {
			if (o.value == value && o.id == id) return 0;
			else if (value > o.value) return 1;
			else return -1;
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PriorityQueue<Pair> queue = new PriorityQueue<BookingCom2.Pair>();
		String keys = sc.nextLine();
		String[] keyArr = keys.split(" ");
		Set<String> words = new HashSet<>();
		for (int i = 0; i < keyArr.length; i++) words.add(keyArr[i].toLowerCase());
		
		int reviews = sc.nextInt();
		for (int i = 0; i < reviews; i++) {
			int id = sc.nextInt();
			sc.useDelimiter("\n");
			String review = sc.next();
			review = review.replace(",", "");
			review = review.replace(".", "");
			String[] tokens = review.split(" ");
			
			int count = 0;
			for (int j = 0; j < tokens.length; j++) {
				String token = tokens[j].toLowerCase();
				if (words.contains(token)) count++;
			}
			
			queue.add(new Pair(id, count));
		}
		
		StringBuilder build = new StringBuilder();
		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			if (queue.isEmpty()) build.append(pair.id);
			else build.append(pair.id).append(" ");
		}
		
		sc.close();

	}

}
