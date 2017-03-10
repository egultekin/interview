package btbf;

import java.util.ArrayList;
import java.util.Arrays;

import dtypes.TravelGroup;

public class BridgeCrossing {
	private int minTime = Integer.MAX_VALUE;
	
		private int minTimeRec(int[] source, int[] target, int accumulatedTime) {
		if (null != source && source.length == 2) return new TravelGroup(source[0], source[1]).slower() + accumulatedTime;
		
		for (TravelGroup direction1 : possible(source, 2)) {
			int timeInDirection1 = direction1.slower();
			int timeInDirection2 = 0;
			int[] afterDeparture = direction1.departs(source);
			int[] afterArrival = direction1.arrives(target);

			for (TravelGroup direction2 : possible(afterArrival, 1)) {
				timeInDirection2 = direction2.slower();
				int candidate = minTimeRec(direction2.arrives(afterDeparture), direction2.departs(afterArrival), timeInDirection1 + timeInDirection2 + accumulatedTime);
				if (candidate < minTime) minTime = candidate;
			}
		}
		
		return Integer.MAX_VALUE;
	}
	
	public int minTime(int[] times) {
		int[] source = Arrays.copyOf(times, times.length);
		int[] target = new int[0];
		
		minTimeRec(source, target, 0);
		if (times.length == 1) minTime = times[0];
		
		return minTime;
	}
	
	private Iterable<TravelGroup> possible(int[] group, int groupSize) {
		if (null == group) return new ArrayList<TravelGroup>();
		if (groupSize < 1 || groupSize > 2 || group.length < 1) throw new IllegalArgumentException("Group size shall be either 1 or 2.");
		ArrayList<TravelGroup> resultList = new ArrayList<TravelGroup>();
		
		Arrays.sort(group);
		if (groupSize == 1) {
			for (int next : group) resultList.add(new TravelGroup(next));
		} else {
			for (int i = 0; i < group.length - 1; i++)
				for (int j = i+1; j < group.length; j++)
					resultList.add(new TravelGroup(group[i], group[j]));
		}
		
		return resultList;
	}

	public static void main(String[] args) {
		int[] times = {100, 2, 3, 50, 99, 1};
		
		System.out.println(new BridgeCrossing().minTime(times));
	}

}
