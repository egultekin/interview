package dp;

public class ShortPalindromes {

	private String[][] shortest;
	private int[][] min;
	
	public String shortest(String base) {
		if (null == base || base.length() < 1) throw new IllegalArgumentException("Invalid parameter.");
		
		int n = base.length();
		if (n == 1) return base;
		if (n == 2) {
			if (base.charAt(0) > base.charAt(1)) return base.charAt(1) + base;
			return base + base.charAt(0);
		}
		
		shortest = new String[n+1][2*n];
		min = new int[n+1][2*n];
		
		for (int i=0; i < n+1; i++)
			for (int j=1; j < 2*n; j++) {
				if (j == 1) min[i][j] = 0;
				else min[i][j] = Integer.MAX_VALUE;
				
				shortest[i][j] = base;
			}
		
		String c = base;
		int l = c.length();
		for (int len=2; len < l; len++)
			for (int i=0; i < l-len; i++) {
				int start = i;
				int end = i+len-1;
				c = shortest[i][len];
				if (c.charAt(start) == c.charAt(end)) min[i][len] = min[i+1][len-2];
				else if (c.charAt(i) > c.charAt(end)) {
					if (min[i][len] > incr(min[i][len-1])) {
						min[i][len] = incr(min[i][len-1]);
						shortest[i][len] = insert(shortest[i][len-1], i, c.charAt(end)); 
					}
				} else {
					if (min[i][len] > incr(min[i+1][len-1])) {
						min[i][len] = incr(min[i+1][len-1]);
						shortest[i][len] = insert(shortest[i+1][len-1], end+1, c.charAt(i)); 
					}					
				}
			}
		
		return shortest[0][l];
	}
	
	private String insert(String before, int index, char c) {
		if (null == before || index < 0 || index > before.length()-1) 
			throw new IllegalArgumentException("illegal argument at insert.");
		
		StringBuilder after = new StringBuilder();
		if (index > 0) after.append(before.substring(0, index));
		after.append(c);
		after.append(before.substring(index));
		
		return after.toString();
	}
	
	private int incr(int n) {
		if (n == Integer.MAX_VALUE) return Integer.MAX_VALUE;
		return n+1;
	}
	
	public static void main(String[] args) {
		String str = "TOPCODER";
		
		System.out.println(new ShortPalindromes().shortest(str));

	}

}
