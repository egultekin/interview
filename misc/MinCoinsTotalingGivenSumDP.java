package interview;

public class MinCoinsTotalingGivenSumDP {

	private int sumWithMinCoins(int[] coins, int targetSum) {
		int[][] S = new int[targetSum+1][coins.length+1];
		for (int i=0; i<targetSum+1; i++)
			for (int j=0; j<coins.length+1; j++) 
				S[i][j] = Integer.MAX_VALUE;
		
		S[0][0] = 0;
		for (int i=0; i<coins.length; i++) S[coins[i]][1] = 1;
		
		for (int withCoins=2; withCoins < coins.length+1; withCoins++)
			for (int w=2; w <= targetSum; w++)
				for (int coin=0; coin<coins.length; coin++)
					if (w-coins[coin] >= 0)
						S[w][withCoins] = min(useCoin(S, w-coins[coin], withCoins), S[w][withCoins]);
		
		int min = Integer.MAX_VALUE;
		for (int i=1; i < coins.length+1; i++)
			if (min > S[targetSum][i]) min = S[targetSum][i];
		
		return min;
	}
	
	private int min(int a, int b) {
		if (a < b) return a;
		return b;
	}
	
	private int useCoin(int[][] S, int remainingSum, int withCoins) {
		int remainingCoins = withCoins-1;
		
		if (S[remainingSum][remainingCoins] == Integer.MAX_VALUE) return Integer.MAX_VALUE;
		return S[remainingSum][remainingCoins] + 1;
	}
	
	public static void main(String[] args) {
		MinCoinsTotalingGivenSumDP solution = new MinCoinsTotalingGivenSumDP();
		int targetSum = 20;
		int[] coins = new int[] { 1, 9, 2, 3, 5, 7};
		
		System.out.format("Minimum number of coins to get to sum %d is %d.", targetSum, solution.sumWithMinCoins(coins, targetSum));

	}

}
