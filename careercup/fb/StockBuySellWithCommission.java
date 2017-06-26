package careercup.fb;

public class StockBuySellWithCommission {
	
	public static int maxProfit(int[] prices, int commission) {
		int n = prices.length;
		int[] profit = new int[n+1];
		profit[0] = profit[1] = 0;
		for (int i=1; i < n; i++) {
			int best = profit[i];
			for (int j=i-1; j >= 0; j--)
				best = Math.max(best, profit[j]+prices[i]-prices[j]-commission);
			profit[i+1] = best;
		}
		return profit[n];
	}

	public static void main(String[] args) {
		
		if (maxProfit(new int[] {1, 2, 3, 4}, 3) != 0) System.out.println("Test case #1 failed.");
		if (maxProfit(new int[] {1, 4, 2, 5}, 3) != 1) System.out.println("Test case #2 failed.");
		if (maxProfit(new int[] {5, 4, 2, 1}, 2) != 0) System.out.println("Test case #3 failed.");
		if (maxProfit(new int[] {1, 4, 1, -5, -3, -1}, 3) != 1) System.out.println("Test case #4 failed.");
		if (maxProfit(new int[] {5, 5, 5}, 0) != 0) System.out.println("Test case #5 failed.");
		if (maxProfit(new int[] {1, 4, 2, 5}, 0) != 6) System.out.println("Test case #6 failed.");
		if (maxProfit(new int[] {1, 3, 7, 5, 10, 3}, 3) != 6) System.out.println("Test case #7 failed.");
		if (maxProfit(new int[] {1, 3, 7, 4, 10, 3}, 3) != 6) System.out.println("Test case #8 failed.");
		if (maxProfit(new int[] {1, 3, 7, 3, 6}, 3) != 3) System.out.println("Test case #9 failed.");
		if (maxProfit(new int[] {1, 3, 7, 3, 3}, 3) != 3) System.out.println("Test case #10 failed.");
		if (maxProfit(new int[] {1, 3, 7, 3, 8, 10}, 3) != 7) System.out.println("Test case #11 failed.");
		if (maxProfit(new int[] {9,8,7,1,2,3,0,20}, 3) != 17) System.out.println("Test case #12 failed.");
		if (maxProfit(new int[] {2,3,4,1,8,0,9}, 3) != 10) System.out.println("Test case #13 failed.");
		if (maxProfit(new int[] { 10, 12, 8, 5, 6, 4, 9, 9, 11, 11, 10, 13, 17 }, 3) != 10) System.out.println("Test case #14 failed.");
		
		System.out.println("All tests are completed.");
	}

}
