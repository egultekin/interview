package cci;

import java.util.Scanner;

public class Rand9 {

	private static int Rand3() {
		return (int)((float)3*Math.random());
	}
	
	public static int rand() {
		return 3*Rand3()+Rand3();
	}
	
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int samples = sc.nextInt();
	    if (samples <= 0) return;
	    int[] count = new int[9];
	    while (samples-- > 0) count[Rand9.rand()]++;
	    sc.close();
	    
	    StringBuilder builder = new StringBuilder();
	    for (int i = 0; i < 9; i++) builder.append(i).append(':').append(count[i]).append('\n');
	    System.out.println(builder.toString());

	}

}
