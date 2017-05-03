package cci;

import java.util.HashMap;
import java.util.Scanner;

class SumSwap {
  public static int[] swap(int[] a, int[] b) {
    int sumA = 0, sumB = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < a.length; i++) {
    	sumA += a[i];
    	map.put(a[i],i);
    }
    for (int i = 0; i < b.length; i++) sumB += b[i];
    int i = 0;
    int diff = (sumB-sumA)/2;
    while (i < b.length) {
      if (map.containsKey(b[i]-diff)) break;
      i++;
    }

    if (i == b.length) throw new IllegalArgumentException("There is no solution.");
    return new int[] {map.get(b[i]-diff), i};
  }

 
  public static void main(String[] args) {
	  Scanner sc = new Scanner(System.in);
	  int samples = sc.nextInt();
	  while (samples-- > 0) {
		  int sizeA = sc.nextInt();
		  int[] a = new int[sizeA];
		  for (int i = 0; i < sizeA; i++) a[i] = sc.nextInt();
		  int sizeB = sc.nextInt();
		  int[] b = new int[sizeB];
		  for (int i=0; i < sizeB; i++) b[i] = sc.nextInt();
		  int[] sol = SumSwap.swap(a, b);
		  System.out.printf("Array sums can be equalized by swapping %d of A with %d of b\n",sol[0], sol[1]);
	  }
	  sc.close();
	
  }
}

//1
//6 4 1 2 1 1 2
//4 3 6 3 3
