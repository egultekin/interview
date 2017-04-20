package cci;

import java.util.*;

class KthMultiple {
  public long find(int k) {
	if (k <= 0) throw new IllegalArgumentException();
    Vector<Long> multiples = new Vector<Long>();
    LinkedList<Long> threes = new LinkedList<Long>();
    LinkedList<Long> fives = new LinkedList<Long>();
    LinkedList<Long> sevens = new LinkedList<Long>();

    threes.add((long)3);
    fives.add((long)5);
    sevens.add((long)7);
    
    multiples.add((long)1);

    long limitThrees = 0, limitFives = 0; 
    while (multiples.size() < k) {
      if (threes.peekFirst() < fives.peekFirst() && threes.peekFirst() < sevens.peekFirst()) {
        long three = threes.pollFirst();
        if (three*3 > limitThrees) threes.add(three*3);
        if (three*5 > limitThrees) threes.add(three*5);
        if (three*7 > limitThrees) {
        	threes.add(three*7);
        	limitThrees = three*7;
        }

        multiples.add(three);
      } else if (fives.peekFirst() < threes.peekFirst() && fives.peekFirst() < sevens.peekFirst()) {
        long five = fives.pollFirst();
        if (five*5 > limitFives)fives.add(five*5);
        if (five*7 > limitFives) {
        	fives.add(five*7);
        	limitFives = five*7;
        }
        multiples.add(five);
      } else {
        long seven = sevens.pollFirst();
        sevens.add(seven*7);
        multiples.add(seven);
      }
    }

    return multiples.get(k-1);
  }

  public static void main(String[] args) {
    KthMultiple kMult = new KthMultiple();
    for (int i = 1; i < 20; i++) System.out.printf("Multiple %d: %d\n", i, kMult.find(i));
  }
}
