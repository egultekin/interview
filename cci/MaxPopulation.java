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
			AgeRange val = map.get(key);
			if (val.dead >= born) {
				sum += val.population;
				earliestDead.add(new AgeRange(key, val.dead, val.population));
				map.put(key, new AgeRange(key, born, val.population));
			}
			
		}
		
		earliestDead.add(new AgeRange(born, dead, 1));
		sum++;

		int start = born;
		while (!earliestDead.isEmpty()) {
			AgeRange earliest = earliestDead.poll();
			map.put(start, new AgeRange(start, earliest.dead, sum));
			sum -= earliest.population;
			start = earliest.dead+1;
		}
	}
	
	public int maxPopulation() {
		int max = Integer.MIN_VALUE;
		int year = Integer.MIN_VALUE;
		for (Map.Entry<Integer, AgeRange> item : map.entrySet())
			if (item.getValue().population > max) {
				max = item.getValue().population;
				year = item.getKey(); 
			}
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