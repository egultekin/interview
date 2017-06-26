package careercup.fb;

import java.util.List;
import java.util.Vector;

public class TravelGasStations {
	
	public static List<Integer> canCompleteCircuit(int[] gas, int[] cost) {
		int n = gas.length;
		List<Integer> result = new Vector<Integer>();
		int[] lowest = new int[n];
		lowest[n-1] = gas[n-1]-cost[n-1];
		for (int i=n-2; i >= 0; i--)
			lowest[i] = lowest[i+1]+gas[i]-cost[i];
		for (int i=0; i < n; i++)
			if (gas[i] >= cost[i] && lowest[i] >= 0)
				result.add(i);
		return result;
	}
	
	public static void print(List<Integer> list) {
		StringBuilder sb = new StringBuilder();
		for (int i : list) {
			if (sb.length() == 0) sb.append(i);
			else sb.append(',').append(i);
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
		print(canCompleteCircuit(new int[] {5, 4, 6, 3, 2}, new int[] {7,4,2,5,3} ));
		print(canCompleteCircuit(new int[] {10, 2, 3, 5}, new int[] {5,6,2,6} ));
		print(canCompleteCircuit(new int[] {4,2,7,5}, new int[] {5,2,8,3} ));
	}

}
