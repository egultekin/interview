package careercup.fb;

class MaxSumOfKElements {
	public static int max(int[] arr, int k) {
		int n = arr.length;
		int[][] sums = new int[n+1][k+1];
		for (int i=1;i <= n; i++) sums[i][1] = arr[i-1];
		for (int j=2; j <= k; j++)
		for (int i=j; i <= n; i++)
			sums[i][j] = sums[i-1][j-1]+sums[i][1];
		int max = sums[n][k];
		for (int i=n-1; i >= k; i--)
			if(sums[i][k] > max) max = sums[i][k];
		return max;
	}
	
	public static void main(String[] args) {
		int[] arr1 = {-4, -2, 1, -3};
		int[] arr2 = {1, 1, 1, 1, 1, 1 };
		System.out.println(max(arr1, 2));
		System.out.println(max(arr2, 6));
	}
}