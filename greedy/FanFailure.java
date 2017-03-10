package greedy;

import java.util.Arrays;

public class FanFailure {
	
	int[] minRequired;

	public int[] getRange(int[] capacities, int minCooling) {
		if (minCooling < 1) throw new IllegalArgumentException("Invalid minCooling value.");
		int n = capacities.length;
		Arrays.sort(capacities);
		int sum=0, i = 0;
		while (i >=0 && sum < minCooling) {
			sum += capacities[n-i-1];
			i++;
		}
		
		int j=n;
		sum=0;
		for (int k=0; k < n-j; k++) sum+=capacities[k];
		while (sum < minCooling && j > 0) {
			sum+=capacities[n-j];
			j--;
		}
		
		return new int[] { n-i, j };
	}
	
	public static void main(String[] args) {
		int[] capacities = new int[] {1,2,3};
		int minCooling = 2;
		
		int[] result = new FanFailure().getRange(capacities, minCooling);
		System.out.format("{ %d, %d }", result[0], result[1]);

	}

}
