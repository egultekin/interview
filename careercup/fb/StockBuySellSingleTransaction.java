package careercup.fb;

public class StockBuySellSingleTransaction {
	public static int maxProfit(int[] prices) {
		int n = prices.length;
		int[] profit = new int[n];
		profit[0] = 0;
		for (int i=1; i < n; i++) {
			int best = profit[i-1];
			for (int j=i-1; j >= 0; j--)
				best = Math.max(best, prices[i]-prices[j]);
			profit[i] = best;
		}
		return profit[n-1];
	}

	public static void main(String[] args) {
		System.out.println(maxProfit(new int[] { 10, 12, 8, 5, 6, 4, 9, 9, 11, 11, 10, 13, 17 }));
	}

}
