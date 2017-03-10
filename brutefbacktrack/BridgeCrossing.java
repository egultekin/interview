package brutefbacktrack;

import java.util.Vector;

public class BridgeCrossing {
	
	private int min = Integer.MAX_VALUE;
	
	public int minTime(int[] times) {
		Vector<Integer> passed = new Vector<Integer>();
		
		Vector<Integer> waiting = new Vector<Integer>();
		for (int i=0; i<times.length; i++)
			waiting.add(new Integer(times[i]));
		
		helper(0, passed, waiting);
		return min;
	}
	
	private Vector<Integer[]> choose(Vector<Integer> waiting) {
		Vector<Integer[]> chosen = new Vector<Integer[]>();
		for (int i=0; i<waiting.size()-1; i++) 
			for (int j=i+1; j<waiting.size(); j++)  
				chosen.add(new Integer[] { waiting.get(i), waiting.get(j) });
		return chosen;
	}
	
	private int helper(int time, Vector<Integer> passed, Vector<Integer> waiting) {
		if (waiting.size() == 0) return 0;
		if (waiting.size() == 1) return waiting.get(0);
		int timeChosen = 0;
		
		for (Integer[] chosen : choose(waiting)) {
				Integer first = chosen[0];
				Integer second = chosen[1];
			
				Vector<Integer> tempPassed = new Vector<Integer>(passed);
				Vector<Integer> tempWaiting = new Vector<Integer>(waiting);
				tempPassed.add(first);
				tempPassed.add(second);
				tempWaiting.remove(first);
				tempWaiting.remove(second);
				timeChosen = Math.max(first, second);
				
				if (tempWaiting.size() > 0) {
					Integer back = removeMin(tempPassed);
					tempWaiting.add(back);
					timeChosen+=back;
				}
				
				timeChosen += helper(time+timeChosen, tempPassed, tempWaiting);
				if (timeChosen + time < min) { min = timeChosen + time; }
		}
		
		return timeChosen;
	}
	
	private Integer removeMin(Vector<Integer> v) {
		int min = 0;
		for (int i = 1; i < v.size(); i++) {
			if (v.get(i) < v.get(min)) min = i;
		}
		
		Integer result = v.get(min);
		v.remove(min);
		return result;
	}

	public static void main(String[] args) {
		int[] times = { 1, 2, 3, 50, 99, 100 };
		
		System.out.format("Passes in %d steps\n", new BridgeCrossing().minTime(times));
	}

}
