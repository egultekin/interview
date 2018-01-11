import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

class PalindromeChecker {
  static class Interval implements Comparable<Interval> {
    int l, r;

    public Interval(int left, int right) {
      l = left;
      r = right;
    }

    @Override
    public int compareTo(Interval to) {
      if (r-l > to.r-to.l) return -1;
      if (r-l < to.r-to.l) return 1;
      return 0;
    }
  }

  public static boolean isPalindromable(String s, int k) {
    System.out.printf("Checking string %s\n", s);
    char[] cArr = s.toCharArray();
    int n = cArr.length;
    Map<Character, List<Integer>> indexMap = new HashMap<>();
    for (int i=0; i < n; i++) {
      List<Integer> list = indexMap.get(cArr[i]);
      if (null == list) {
        list = new ArrayList<>();
        indexMap.put(cArr[i], list);
      }
      list.add(i);
    }
    PriorityQueue<Interval> pq = new PriorityQueue<>();
    for (Character c : indexMap.keySet()) {
      List<Integer> list = indexMap.get(c);
      int lastInd = list.size()-1;
      if (lastInd > 0) {
        int last = list.get(lastInd);
        int first = list.get(0);
        list.remove(lastInd);
        if (lastInd > 0) list.remove(0);
        pq.add(new Interval(first, last));
        System.out.printf("A new interval (%d,%d) is created for character %c with length\n", first, last, c, last-first+1);
      }
    }

    int l=0;
    int r=n-1;
    int dels = 0;
    while (!pq.isEmpty() && l < r) {
      Interval ival = pq.poll();
      if (ival.l >= l && ival.r <= r) {
        // process Interval
        System.out.printf("Processing interval (%d,%d)\n", ival.l, ival.r);
        dels += ival.l-l;
        dels += r-ival.r;
        System.out.printf("Processing deleted %d characters\n", r+ival.l-l-ival.r);
        l = ival.l+1;
        r = ival.r-1;
      }
    }

    if (r > l) dels += r-l+1;
    System.out.printf("Total number of chars deleted for %s is %d\n", s, dels);
    return k >= dels;
  }

  public static void main(String[] args) {
    System.out.println(isPalindromable("kotaaalotku", 5));
    System.out.println(isPalindromable("kotaaalotku", 4));
    System.out.println(isPalindromable("abcdcbkt", 4));
    System.out.println(isPalindromable("abcdcb", 1));
    System.out.println(isPalindromable("yktaaabcku", 3));
  }
}
