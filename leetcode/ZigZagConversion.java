package leetcode;

import java.util.Scanner;

public class ZigZagConversion {
    public String convert(String s, int numRows) {
		if (s.equals("") || numRows < 2) return s;
        char[] ca = s.toCharArray();
        int coe = numRows-1;
		StringBuilder sb = new StringBuilder();
		for (int i=0; i < numRows; i++) {
			int la = Integer.MIN_VALUE;
			for (int j=0; 2*j*coe-i < ca.length; j++) {
				int head = 2*j*coe;
				int tr = head-i;
				if (tr >= 0 && tr > la && tr < ca.length) {
					sb.append(ca[tr]);
					la = tr;
				}
				tr = head+i;
				if (i > 0 && tr < ca.length) {
					sb.append(ca[tr]);
					la = tr;
				}
			}
		}
		
		return sb.toString();
    }
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numRows = sc.nextInt();
		String s = sc.next();
		sc.close();
		
		ZigZagConversion zg = new ZigZagConversion();
		System.out.println(zg.convert(s, numRows));

	}

}
