package careercup.fb;

public class StockBuySellNoCommission {
	public static int maxProfit(int[] prices) {
		int n = prices.length;
		int[] profit = new int[n];
		profit[0] = 0;
		for (int i=1; i < n; i++) {
			int dprice = prices[i]-prices[i-1];
			if (dprice > 0) profit[i] = profit[i-1]+dprice;
			else profit[i] = profit[i-1];
		}
		return profit[n-1];
	}

	public static void main(String[] args) {
		System.out.println(maxProfit(new int[] { 10, 12, 8, 5, 6, 4, 9, 9, 11, 11, 10, 13, 17 }));

	}

}
