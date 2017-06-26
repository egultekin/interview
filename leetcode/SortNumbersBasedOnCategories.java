package leetcode;

public class SortNumbersBasedOnCategories {

	public static String getCategory(int n) {
		if (n >= 1 && n <= 3) return "low";
		else if (n >= 4 && n <= 10) return "medium";
		return "high";
	}
	
	public static int[] categorize(int[] input) {
		int cl = 0, cm = 0, ch = 0;
		int[] result = new int[input.length];
		for (int i=0; i < input.length; i++) {
			String cat = getCategory(input[i]);
			if (cat.equals("low")) cl++;
			else if (cat.equals("high")) ch++;
			else cm++;
		}
		
		int il = 0, im = cl, ih = im+cm;
		for (int i=0; i < input.length; i++) {
			String cat = getCategory(input[i]);
			if (cat.equals("low")) result[il++] = input[i];
			else if (cat.equals("high")) result[ih++] = input[i];
			else result[im++] = input[i];
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] categorized = categorize(new int[] {5, 7, 2, 9, 1, 14, 12, 10, 5, 3});
		StringBuilder sb = new StringBuilder();
		sb.append(categorized[0]);
		for (int i=1; i < categorized.length; i++) sb.append('-').append(categorized[i]);
		System.out.println(sb.toString());
	}

}
