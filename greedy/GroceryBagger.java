package greedy;

import java.util.Hashtable;

class TestCase {
	String[] itemTypes;
	int strength;
	int id;
	
	public TestCase(int pId, int pStrength, String[] pTypes) {
		this.id = pId;
		this.strength = pStrength;
		this.itemTypes = pTypes;
	}
}

public class GroceryBagger {

	private Hashtable<String, Integer> cache;
	
	public int minimumBags(int strength, String[] itemType) {
		if (null == itemType || itemType.length == 0) return 0;
		int bags = 0;
		cache = new Hashtable<String, Integer>(50);
		
		for (String item : itemType)
			if (cache.containsKey(item)) {
				Integer value = cache.get(item);
				if (value == strength - 1) cache.remove(item);
				else cache.put(item, ++value);
			} else { 
				cache.put(item, 1);
				bags++;
			}
		
		return bags;
	}
	
	public static void main(String[] args) {
		TestCase tc1 = new TestCase(1, 3, new String[] {"DAIRY",
				 "DAIRY",
				 "PRODUCE",
				 "PRODUCE",
				 "PRODUCE",
				 "MEAT"});
		
		TestCase tc2 = new TestCase(2, 10, new String[] {});
		
		TestCase tc3 = new TestCase(3, 5, new String[] {"CANNED",   "CANNED",  "PRODUCE",
				 "DAIRY",    "MEAT",    "BREAD",
				 "HOUSEHOLD","PRODUCE", "FROZEN",
				 "PRODUCE", "DAIRY"});
		
		TestCase tc4 = new TestCase(4, 2, new String[] {"CANNED",   "CANNED",  "PRODUCE",
				 "DAIRY",    "MEAT",    "BREAD",
				 "HOUSEHOLD","PRODUCE", "FROZEN",
				 "PRODUCE", "DAIRY"});
		
		TestCase[] testCases = new TestCase[] { tc1, tc2, tc3, tc4 };
		
		for (TestCase tc : testCases)
		System.out.format("TestCase %d : %d\n", tc.id, new GroceryBagger().minimumBags(tc.strength, tc.itemTypes));

	}

}
