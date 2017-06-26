package careercup.fb;

class OddIndicesGreaterEvenIndices {
	public static int[] order(int[] arr) {
		int n = arr.length;
		for (int i=1; i < n; i++)
			if ((i%2 == 0 && arr[i] > arr[i-1]) || (i%2 == 1 && arr[i] < arr[i-1]))
				arr = swap(arr, i, i-1);
		return arr;
	}

	private static int[] swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return arr;
	}
	
	public static void print(int[] arr) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i=0; i < arr.length; i++)
			if (sb.length() == 1) sb.append(arr[i]);
			else sb.append(',').append(arr[i]);
		sb.append(']');
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		print(order(new int[] {5, 4, 3, 2, 1, 0, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5} ));
	}
}