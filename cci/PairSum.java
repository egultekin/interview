package cci;

import java.util.*;

class PairSum {
  static class Index {
    int ind;
    Index n;

    public Index(int idx) {
      ind = idx;
      n = null;
    }
  }

  private HashMap<Integer, Index> map;

  public PairSum() {
    map = new HashMap<>();
  }

  private void add(int number, int idx) {
    if (!map.containsKey(number)) {
      map.put(number, new Index(idx));
      return;
    }

    Index head = map.get(number);
    while (null != head.n) head = head.n;
    head.n = new Index(idx);
  }

  public void printPairs(int sum, int[] arr) {
    for (int i = 0; i < arr.length; i++) add(arr[i], i);
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      if (map.containsKey(sum-arr[i])) {
        Index head = map.get(sum-arr[i]);
        while (head != null) {
          if (head.ind > i) b.append(String.format("{%d, %d}\n", i, head.ind));
          head = head.n;
        }
      }
    }
    if (b.length() == 0) System.out.printf("No pairs with sum %d exist.\n", sum);
    else System.out.print(b.toString());
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int sum = sc.nextInt();
    int size = sc.nextInt();
    int[] arr = new int[size];
    for (int i = 0; i < size; i++) arr[i] = sc.nextInt();
    PairSum ps = new PairSum();
    ps.printPairs(sum, arr);
    sc.close();
  }
}

//10 8
//3 4 7 5 -8 -2 2 9

//10 8
//3 4 7 1 1 -2 9 9
