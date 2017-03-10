package leetcode;

public class MedianOfTwoSortedArrays {

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int len = nums1.length+nums2.length;
		int order = (len+1)/2;
		int i1 = 0, i2 = 0, count = 0, num = Integer.MAX_VALUE;
		while (count < order) {
			num = (i1 < nums1.length) ? nums1[i1] : Integer.MAX_VALUE;
			if (i2 < nums2.length) {
				if (nums2[i2] < num) { 
					num = nums2[i2];
					i2++;
				} else i1++;
			} else i1++;

			count++;
		}
		
		if (num == Integer.MAX_VALUE) return (double) 0;
		else if (len%2==1) return (double)num;
		else return ((double)num+Math.min(i1 < nums1.length ? nums1[i1] : Integer.MAX_VALUE, i2 < nums2.length ? nums2[i2] : Integer.MAX_VALUE))/2;
	}

	public static void main(String[] args) {
		System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[] {2, 4, 5, 6, 8}, new int[] {1, 3, 7}));
	}

}
