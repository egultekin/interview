package brutefbacktrack;

import java.util.Vector;

public class BCBridgeCrossing {
	
	class State {
		Vector<Integer> source, target;
		boolean asPair;
		int timeSpent;
		
		public State(int[] times) {
			source = new Vector<>();
			target = new Vector<Integer>();
			
			for (int i = 0; i < times.length; i++) source.add(new Integer(times[i]));
			asPair = true;
			timeSpent = 0;
		}
		
		public State(State prev) {
			source = new Vector<>(prev.source);
			target = new Vector<>(prev.target);
			asPair = !prev.asPair;
			timeSpent = prev.timeSpent;			
		}
	}
	
	public int minTime(int[] times) {
		return bt(new State(times), Integer.MAX_VALUE);
	}
	
	private int bt(State c, int minTime) {
		if (c.source.size() == 0)  {
			if (c.timeSpent < minTime) return c.timeSpent;
			else return minTime;
		}
		
		if (c.asPair) {
			int best = minTime;
			for (int i = 0; i < c.source.size()-1; i++)
				for (int j = i + 1; j < c.source.size(); j++) {
					State ns = new State(c);
					int second = ns.source.remove(j);
					int first = ns.source.remove(i);
					ns.target.add(new Integer(first));
					ns.target.add(new Integer(second));
					ns.timeSpent = ns.timeSpent + ((first > second) ? first : second);
					
					int accumulatedCost = bt(ns, minTime);
					if (accumulatedCost < minTime) minTime = accumulatedCost;					
				}
			
			return minTime;
		} else {
			int best = minTime;
			for (int i = 0; i < c.target.size(); i++) {
				State ns = new State(c);
				int first = ns.target.remove(i);
				ns.source.add(new Integer(first));
				ns.timeSpent += first;
				
				int accumulatedCost = bt(ns, minTime);
				if (accumulatedCost < minTime) minTime = accumulatedCost;	
			}
			
			return minTime;
		}
	}

	public static void main(String[] args) {
		int[] times = {1, 2, 25, 50, 99, 100};
		
		System.out.println(new BCBridgeCrossing().minTime(times));
	}
}
