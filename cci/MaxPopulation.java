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

	private TreeMap<Integer, AgeRange> map;
	
	public MaxPopulation() {
		map = new TreeMap<Integer, AgeRange>();
	}
	
	public void newPerson(int born, int dead) {
		NavigableMap<Integer, AgeRange> alreadyBorn = map.headMap(dead, true);
		int now = born;
		boolean modified = false;
		Collection<AgeRange> keys = alreadyBorn.values();
		Set<AgeRange> modifications = new HashSet<AgeRange>();
		for (AgeRange registered : keys) {
			Integer key = registered.born;
			if (registered.dead >= born) {
				// if the new population counts for the selected term
				modified = true;
				if (key < born) {
					map.put(key, new AgeRange(key, born-1, registered.population));
					now = born;
				} else now = key;
				
				modifications.add(new AgeRange(now, Math.min(registered.dead, dead), registered.population+1));
				if (registered.dead > dead)
					modifications.add(new AgeRange(dead+1, registered.dead, registered.population));
				else 
					modifications.add(new AgeRange(registered.dead+1, dead, 1));
			}
		}
		if (!modified) map.put(born, new AgeRange(born, dead, 1));
		for (AgeRange modification : modifications) map.put(modification.born, modification);
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