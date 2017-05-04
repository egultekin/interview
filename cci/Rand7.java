package cci;

import java.util.*;

class Rand7 {
	
	public static int rand5() {
		return (int)(Math.random()*(float)5);
	}
  public static int rand() {
    int random = rand5()*5+rand5();
    while (random > 20) random = rand5()*5+rand5();
    return random%7;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int samples = sc.nextInt();
    if (samples <= 0) return;
    int[] count = new int[7];
    while (samples-- > 0) count[Rand7.rand()]++;
    sc.close();
    
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < 7; i++) builder.append(i).append(':').append(count[i]).append('\n');
    System.out.println(builder.toString());

  }
}
