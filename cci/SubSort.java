package cci;

import java.util.*;

class SubSort {
  static class Segment {
    int min, max;

    public Segment(int n, int x) {
      min = n;
      max = x;
    }
  }

  private static int greaterOrEqual(int[] arr, int lo, int hi, int v) {
    while (hi > lo+1) {
      int mid = (lo+hi)/2;
      if (arr[mid] >= v) hi = mid;
      else lo = mid+1;
    }
    if (arr[lo] > v) return lo;
    return hi;
  }
  
  private static int less(int[] arr, int lo, int hi, int v) {
	  while (hi > lo+1) {
		  int mid = (hi+lo)/2;
		  if (arr[mid] >= v) hi = mid-1;
		  else lo = mid;
	  }
	  if (arr[hi] < v) return hi;
	  return lo;
  }

  public static int[] find(int[] arr) {
    int lower = arr.length-1, higher = arr.length-1;
    if (arr.length < 1) throw new IllegalArgumentException("Array is empty!");
    int hi = 0, lo = 0, min = 0, max = -1, i=1;
    LinkedList<Segment> s = new LinkedList<>();
    while (i < arr.length) {
      if (arr[i] > arr[i-1]) {
        hi = i;
        if (lo == i-1 && arr[lo] < arr[min]) min = lo;
      } else if (arr[i] < arr[i-1]) {
        if (hi == i-1 && (max == -1 || arr[hi] > arr[max])) {
          max = hi;
          s.addLast(new Segment(lo, hi));
          if (min == 0) min = max;
        }
        lo = i;
      }
      i++;
    }
    if (arr[i-1] < arr[min]) min = i-1;
    while (!s.isEmpty() && min > s.peekFirst().max && arr[s.peekFirst().min] > arr[min] && arr[s.peekFirst().max] < arr[min]) s.pollFirst();
    if (!s.isEmpty() && min > s.peekFirst().max) lower = greaterOrEqual(arr, s.peekFirst().min, s.peekFirst().max, arr[min]);
    else if (min > 0) lower = 0;
    if (max > -1 && arr[max] < arr[hi]) higher = less(arr, lo, hi, arr[max]);
    return new int[] {lower, higher};
  }
  
  public static void main(String[] args) {
	  Scanner sc = new Scanner(System.in);
	  int samples = sc.nextInt();
	  while (samples-- > 0) {
		  int size = sc.nextInt();
		  int[] arr = new int[size];
		  for (int i = 0; i < size; i++) arr[i] = sc.nextInt();
		  int[] bounds = SubSort.find(arr);
		  System.out.printf("Subarray between index %d and %d shall be sorted at minimum.\n", bounds[0], bounds[1]);
	  }
	  sc.close();
  }
}


//10
//11 -5 0 5 -2 10 8 6 9 12 18 16
//3 9 12 9
//4 9 12 9 15
//5 9 12 9 10 15
//3 9 12 3
//2 5 4
//2 5 5
//2 5 7
//8 0 5 3 -5 -2 2 4 8
//7 -5 5 3 -5 -2 5 9