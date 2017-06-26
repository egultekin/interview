package careercup.fb;

import java.util.List;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;
import java.util.ArrayList;

public class MaxPopulationYear {
	static class Person implements Comparable<Person> {
		int birth, death;
		
		@Override
		public int compareTo(Person p) {
			if (this.death < p.death) return -1;
			else if (this.death > p.death) return 1;
			return 0;
		}
		
		public Person(int b, int d) {
			birth = b;
			death = d;
		}
	}
	
	public static int maxPopulation(List<Person> lp) {
		PriorityQueue<Person> alive = new PriorityQueue<>(lp);
		Person[] pa = new Person[lp.size()];
		lp.toArray(pa);
		Arrays.sort(pa, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				if (p1.birth < p2.birth) return -1;
				else if (p1.birth > p2.birth) return 1;
				return 0;
			}
		});
		int i=0, max=0, count=0, maxY=0;
		while (i < pa.length) {
			Person next = pa[i];
			if (!alive.isEmpty() && next.birth > alive.peek().death) {
				Person dead = alive.poll();
				if (count > max) {
					max = count;
					maxY = dead.death;
				}
				count--;
			} else {
				i++;
				count++;
			}
		}
		
		if (!alive.isEmpty() && count > max) {
			max = count;
			maxY = alive.poll().death;
		}
		
		System.out.printf("Max population is at year %d with %d people alive.\n", maxY, max);
		return maxY;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		List<Person> lp = new ArrayList<Person>();
		while (N-- > 0) lp.add(new Person(sc.nextInt(), sc.nextInt()));
		sc.close();
		
		MaxPopulationYear.maxPopulation(lp);
	}

}

