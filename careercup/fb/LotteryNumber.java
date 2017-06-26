package careercup.fb;

import java.util.HashSet;
import java.util.Set;

public class LotteryNumber {
	
	public static boolean isLotteryNumber(String str, int n, Set<Integer> sol) {
		boolean result = false;
		if (str.equals("") && n == 0) return true;
		else if ((n > 0 && str.equals("")) || (n == 0 && !str.equals(""))) return false;
		
		String numStr1 = str.substring(0, 1);
		int num1 = Integer.parseInt(numStr1);
		if (num1 >= 1 && num1 <= 9) {
			result =  isLotteryNumber(str.substring(1), n-1, sol);
			if (result && !sol.contains(num1)) sol.add(num1);
			else result = false;
		}
		
		if (!result && str.length() > 1) {
			String numStr2 = str.substring(0, 2);
			if (numStr2.charAt(0) != numStr2.charAt(1)) {
				int num2 = Integer.parseInt(numStr2);
				if (num2 >= 10 && num2 <= 59)
					result = isLotteryNumber(str.substring(2), n-1, sol);
					if (result && !sol.contains(num2)) sol.add(num2);
					else result = false;
			}
		}
		return result;
	}
	
	public static String getSolString(Set<Integer> sol) {
		StringBuilder sb = new StringBuilder();
		for (Integer i : sol) {
			if (sb.length() == 0) sb.append(i);
			else sb.append('-').append(i);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String[] arr = new String[] {
				"4938532894754",
				"1634616512",
				"1122334"
		};
		
		for (int i=0; i < arr.length; i++) {
			Set<Integer> sol = new HashSet<Integer>();
			boolean result = isLotteryNumber(arr[i], 7, sol);
			System.out.printf("Is %s a lottery number. Result: %s (%s)\n", arr[i], result, getSolString(sol));
		}
	}

}
