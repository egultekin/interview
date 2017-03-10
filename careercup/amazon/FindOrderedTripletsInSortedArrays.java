package careercup.amazon;

public class FindOrderedTripletsInSortedArrays {
	
	public static int getHighestLow(int[] arr, int compare, int left, int right) {
		while (left < right) {
			int mid = (left+right)/2;
			if (arr[mid] < compare) left = mid+1;
			else right = mid;
		}
		return right-1;
	}
	
	public static int getLowestHigh(int[] arr, int compare, int left, int right) {
		while (left < right) {
			int mid = (left+right)/2;
			if (arr[mid] > compare) right = mid;
			else left = mid+1;
		}
		return left;
	}
	
	public static long count(int[] arr1, int[] arr2, int[] arr3) {
		long count = 0;
		for (int i = 0; i < arr1.length; i++)
			for (int j = 0; j < arr3.length; j++)
				if (arr1[i] < arr3[j]){
					int l = getLowestHigh(arr2, arr1[i], 0, arr2.length-1);
					int h = getHighestLow(arr2, arr3[j], 0, arr2.length);
					if (l > -1 && h < arr2.length)
						count += Math.pow(2, h-l+1)-1;
				}
		return count;
	}

	public static void main(String[] args) {
		int[] arr1 = {1, 5, 7, 12, 24, 48, 56 };
		int[] arr2 = {1, 2, 4, 8, 45, 98, 127, 3890 };
		int[] arr3 = {1, 50, 200, 400, 800, 1600 };
		int[] arr4 = {3};
		int[] arr5 = {11, 13, 16};
		int[] arr6 = {45};
		
		int result = 0;
		if (getHighestLow(arr1, 2, 0, arr1.length) == 0) result = (result | 1 );
		if (getHighestLow(arr1, 1, 0, arr1.length) == -1) result = (result | (1 << 1));
		if (getHighestLow(arr1, 12, 0, arr1.length) == 2) result = (result | (1 << 2));
		if (getLowestHigh(arr1, 56, 0, arr1.length-1) == 6) result = (result | (1 << 3));
		if (getLowestHigh(arr1, 48, 0, arr1.length-1) == 6) result = (result | (1 << 4));
		
		if (result != 31) {
			System.out.printf("Error!: %d", result);
			return;
		}
		

		System.out.println(count(arr1, arr2, arr3));
		System.out.println(count(arr4, arr5, arr6));

	}

}
