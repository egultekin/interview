package leetcode;

import java.util.Scanner;

class PalindromeNumber {
  public boolean isPalindrome(int x) {
    x = (int)Math.abs(x);
    int a = 1;
    while (a <= Integer.MAX_VALUE/10 && a*10 <= x) a = a*10;
    int q = x/a;
    int r = x%10;
    while (q == r && x > 0 && a > 1) {
      x -= q*a+r;
      x = x/10;
      a = a/100;
      while (x < a && x%10 == 0) {
        a = a/100;
        x = x/10;
      }
      if (a > 0) {
        q = x/a;
        r = x%10;
      }
    }

    return x >= 0 && x < 10;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    PalindromeNumber pn = new PalindromeNumber();
    while (t-- > 0) {
      int x = sc.nextInt();
      System.out.printf("%d: %s\n", x, pn.isPalindrome(x));
    }
    sc.close();
  }
}
