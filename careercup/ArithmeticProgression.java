package careercup;

import java.util.Scanner;

public class ArithmeticProgression {

	public int findMissing(int[] nums) {
		int dx = Math.min(nums[1]-nums[0], nums[2]-nums[1]);
		int left = 0, right = nums.length-1;
		while (left < right) {
			int mid = (left+right)/2;
			int leftDiff = nums[mid]-nums[left];
			if (leftDiff > dx*(mid-left)) {
				if (mid - left == 1) return (nums[mid]+nums[left])/2;
				right = mid;
				continue;
			}
			
			int rightDiff = nums[right]-nums[mid];
			if (rightDiff > dx*(right-mid)) {
				if (right - mid == 1) return (nums[right]+nums[mid])/2;
				left = mid;
				continue;
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		sc.close();
		String[] strings = line.split(" ");
		if (strings.length <= 3) {
			System.out.println("There should be at least 3 numbers.");
			return;
		}
		
		int[] nums = new int[strings.length];
		for (int i = 0; i < strings.length; i++) nums[i] = Integer.parseInt(strings[i]);
		System.out.println(new ArithmeticProgression().findMissing(nums));
	}

}
