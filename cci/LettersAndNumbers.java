package cci;

import java.util.Scanner;

class LettersAndNumbers {
  public boolean diff(char[] arr, int i, int j) {
    if (i >= 0 && i < arr.length && j >=0 && j < arr.length && number(arr[i]) && !number(arr[j])) return true;
    if (i >= 0 && i < arr.length && j >=0 && j < arr.length && !number(arr[i]) && number(arr[j])) return true;
    return false;
  }

  private boolean number(char c) {
    if (c - '0' >= 0 && c - '0' <= 9) return true;
    return false;
  }

  public int longest(char[] arr) {
    int[] lon = new int[arr.length];
    int max = Integer.MIN_VALUE;
    for (int i = 1; i < arr.length; i++) {
      if (diff(arr, i, i-1))
    	  lon[i] = 2;
      else if (lon[i-1] > 0 && diff(arr, i, i-lon[i-1]-1)) 
    	  lon[i] = lon[i-1]+2;
      if (i > lon[i]) lon[i] += lon[i-lon[i]];
      if (lon[i] > max) max = lon[i];
    }

    return max;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int samples = sc.nextInt();
    LettersAndNumbers lan = new LettersAndNumbers();
    while (samples-- > 0) {
      String inp = sc.next();
      System.out.printf("Longest equal subarray of %s is of length %d\n", inp, lan.longest(inp.toCharArray()));
    }
    sc.close();
  }
}
