package leetcode;

import java.util.Scanner;

public class ReverseBinaryRepresentation {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		sc.close();
		
		int org = a;
		int bits = 0;
		while (a >> bits > 0) bits++;
		int b = a&1;
		a = a >> 1;
		for (int i=0; i < bits-1; i++) {
			b = b << 1;
			b = b | (a&1);
			a = a >> 1;
		}

		
		System.out.printf("Reverse of %d is %d.\n", org, b);

	}

}
