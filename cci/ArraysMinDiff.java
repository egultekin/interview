package cci;

import java.util.*;

class ArraysMinDiff {
  public static int minDiff(int[] a, int[] b) {
    if (null == a || null == b || a.length == 0 || b.length == 0)
      throw new IllegalArgumentException("Invalid arrays provided.");
    int min = Integer.MAX_VALUE;
    int i1 = 0, i2 = 0, mini1 = 0, mini2 = 0;
    Arrays.sort(a);
    Arrays.sort(b);
    while (i1 < a.length && i2 < b.length && min > 0) {
      boolean incA = false;
      if (a[i1] == b[i2]) incA = true;
      else if (a[i1] < b[i2]) incA = true;
      else incA = false;
      
      int diff = (incA) ? b[i2]-a[i1] : a[i1]-b[i2];
      if (diff < min) {
    	  min = diff;
    	  mini1 = i1; 
    	  mini2 = i2;
      }
      if (incA) i1++; else i2++; 
    }
    
    System.out.printf("Min difference between arrays is %d on pair{%d, %d}", min, a[mini1], b[mini2]);
    return min;
  }

  public static void main(String[] arg) {
    int[] a = new int[] {1, 3, 4, 41, 2};
    int[] b = new int[] {23, 127, 235, 119, 118};

    ArraysMinDiff.minDiff(a, b);
  }
}
