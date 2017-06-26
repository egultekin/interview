package leetcode;

public class BuySellStockWithFee {
	
	public int maxProfit(int[] p, int fee) {
		int profit = 0;
		int b = 0;
		int s = 0;
		StringBuilder sb = new StringBuilder();
		int t = 1;
		
		for (int i=1; i < p.length; i++) {
			if (p[i] > p[i-1] && p[i]-p[b] >= fee) {
				s = i;
			}
			else if (p[i] < p[i-1] && (p[s]-p[i] >= fee || p[b] > p[i])) {
				if (s > b) {
					profit += p[s]-p[b]-fee;
					sb.append(String.format("Step#%d : Buy when %d and sell when %d.\n", t++, p[b], p[s]));
				}
				b = i;
				s = i;
			}
		}
		if (s > b) { 
			if (p[p.length-1] > p[s]) {
				profit += p[p.length-1]-p[b]-fee;
				sb.append(String.format("Step#%d : Buy when %d and sell when %d.\n", t++, p[b], p[p.length-1]));
				
			} else {
				profit += p[s]-p[b]-fee;
				sb.append(String.format("Step#%d : Buy when %d and sell when %d.\n", t++, p[b], p[s]));
			}
		}
		
		System.out.print(sb.toString());
		return profit;
	}

	public static void main(String[] args) {
		BuySellStockWithFee bs = new BuySellStockWithFee();
		if (bs.maxProfit(new int[] {1, 2, 3, 4}, 3) != 0) System.out.println("Test case #1 failed.");
		if (bs.maxProfit(new int[] {1, 4, 2, 5}, 3) != 1) System.out.println("Test case #2 failed.");
		if (bs.maxProfit(new int[] {5, 4, 2, 1}, 2) != 0) System.out.println("Test case #3 failed.");
		if (bs.maxProfit(new int[] {1, 4, 1, -5, -3, -1}, 3) != 1) System.out.println("Test case #4 failed.");
		if (bs.maxProfit(new int[] {5, 5, 5}, 0) != 0) System.out.println("Test case #5 failed.");
		if (bs.maxProfit(new int[] {1, 4, 2, 5}, 0) != 6) System.out.println("Test case #6 failed.");
		if (bs.maxProfit(new int[] {1, 3, 7, 5, 10, 3}, 3) != 6) System.out.println("Test case #7 failed.");
		if (bs.maxProfit(new int[] {1, 3, 7, 4, 10, 3}, 3) != 6) System.out.println("Test case #8 failed.");
		if (bs.maxProfit(new int[] {1, 3, 7, 3, 6}, 3) != 3) System.out.println("Test case #9 failed.");
		if (bs.maxProfit(new int[] {1, 3, 7, 3, 3}, 3) != 3) System.out.println("Test case #10 failed.");
		if (bs.maxProfit(new int[] {1, 3, 7, 3, 8, 10}, 3) != 7) System.out.println("Test case #11 failed.");
		if (bs.maxProfit(new int[] {9,8,7,1,2,3,0,20}, 3) != 17) System.out.println("Test case #12 failed.");
		if (bs.maxProfit(new int[] {2,3,4,1,8,0,9}, 3) != 10) System.out.println("Test case #13 failed.");
		
		System.out.println("All tests are completed.");
	}

}
