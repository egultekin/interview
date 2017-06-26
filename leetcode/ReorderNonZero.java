package leetcode;

public class ReorderNonZero {
	
	public static int order(int[] arr) {
		int i=0, n = arr.length, j = n-1;
		while (i < j) {
			if (arr[i] != 0) i++;
			if (arr[j] == 0) j--;
			if (arr[i] == 0 && arr[j] != 0) {
				arr = swap(arr, i, j);
				i++;
				j--;
			} 
		}
		
		print(arr);
		return i;
	}
	
	private static void print(int[] arr) {
		StringBuilder sb = new StringBuilder();
		sb.append(arr[0]);
		for (int i = 1; i < arr.length; i++) sb.append(",").append(arr[i]);
		System.out.println(sb.toString());
	}
	
	public static int[] swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return arr;
	}

	public static void main(String[] args) {
		ReorderNonZero.order(new int[] {0, 0, 0, 1, 0, 3, 4, 0, 0});
	}

}
