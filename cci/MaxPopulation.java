package cci;

import java.util.*;

class MaxPopulation {

	class AgeRange implements Comparable<AgeRange> {
		int born, dead, population;
		
		@Override
		public int compareTo(AgeRange to) {
			if (born < to.born) return -1;
			else if (born > to.born) return 1;
			return 0;
		}
		
		public AgeRange(int b, int d, int p) {
			born = b;
			dead = d;
			population = p;
		}
	}

	private PriorityQueue<AgeRange> earliestDead;
	private TreeMap<Integer, AgeRange> map;
	
	public MaxPopulation() {
		earliestDead = new PriorityQueue<AgeRange>(
			new Comparator<AgeRange>() {
				@Override
				public int compare(AgeRange o1, AgeRange o2) {
					if (o1.dead < o2.dead) return -1;
					else if (o1.dead > o2.dead) return 1;
					return 0;
				}
			}
		);
		map = new TreeMap<Integer, AgeRange>();
	}
	
	public void newPerson(int born, int dead) {
		NavigableMap<Integer, AgeRange> alreadyBorn = map.headMap(dead, true);
		int sum = 0;
		for (Integer key : alreadyBorn.keySet()) {
			AgeRange registered = map.get(key);
			if (registered.dead >= born) {
				// select oldest people who are alive when the new person is born  
				sum += registered.population;
				// order them starting the first to be dead 
				earliestDead.add(new AgeRange(key, registered.dead, registered.population));
				// squeeze the oldest term to [term.born, born)
				map.put(key, new AgeRange(key, born-1, registered.population));
			}
			
		}
		
		// add the term for the new person
		earliestDead.add(new AgeRange(born, dead, 1));
		sum++;

		int endLastTerm = born-1;
		while (!earliestDead.isEmpty()) {
			AgeRange earliest = earliestDead.poll();
			// starting from the birth date of the new person
			// distribute alive population into succeeding terms as people die
			if (earliest.dead > endLastTerm) {
				map.put(endLastTerm+1, new AgeRange(endLastTerm+1, earliest.dead, sum));
				endLastTerm = earliest.dead;
			}
			sum -= earliest.population;
		}
	}
	
	public int maxPopulation() {
		int max = Integer.MIN_VALUE;
		int year = Integer.MIN_VALUE;
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<Integer, AgeRange> item : map.entrySet()) {
			builder.append(String.format("Year: %d-%d Population: %d\n", item.getKey(), item.getValue().dead, item.getValue().population));
			if (item.getValue().population > max) {
				max = item.getValue().population;
				year = item.getKey(); 
			}
		}
		System.out.println(builder.toString());
		return year;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int samples = sc.nextInt();
		MaxPopulation sol = new MaxPopulation();
		while (samples-- > 0) {
			sol.newPerson(sc.nextInt(), sc.nextInt());
		}
		sc.close();
		
		System.out.println(sol.maxPopulation());
	}
}