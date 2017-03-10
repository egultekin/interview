package sort;

public class InsertionSort {

	public static int[] swap(int[] arr, int i, int j) {
		if (i < 0 || i >= arr.length || j < 0 || j >= arr.length) return arr;
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return arr;
	}
	
	public static int[] sort(int[] arr) {
		for (int i = 1; i < arr.length; i++)
			for (int j = i; j > 0 && arr[j] < arr[j-1]; j--) arr = swap(arr, j, j-1);
		return arr;
	}
	
	public static boolean equal(int[] arr1, int[] arr2) {
		if (arr1.length != arr2.length) return false;
		for (int i = 0; i < arr1.length; i++) if (arr1[i] != arr2[i]) return false;
		return true;
	}
	
	public static void main(String[] args) {
		int[] input1 = { 1 };
		int[] input2 = { };
		int[] input3 = {3, 0, -1};
		
		int[] o1 = sort(input1);
		int[] o2 = sort(input2);
		int[] o3 = sort(input3);
		
		System.out.println(equal(o1, new int[] { 1 }));
		System.out.println(equal(o2, new int[] { }));
		System.out.println(equal(o3, new int[] { -1, 0, 3 }));

	}

}
