package leetcode;

public class ReverseInteger {

	public int reverse(int x) {
		if (x == Integer.MIN_VALUE) return 0;
		int reverse = 0;
		int temp = Math.abs(x);
		while (temp > 0) {
			if (reverse <= (Integer.MAX_VALUE-(temp%10))/10) {
				reverse = reverse*10+temp%10;
			} else return 0;
			temp = temp/10;
		}
		if (x < 0) return reverse*-1;
		return reverse;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
