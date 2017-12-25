import java.util.Map;
import java.util.HashMap;

class FindLongestNRepeating {
  static int findLongest(String s, int k) {
    if (k <= 0 || s.length() == 0) return 0;
    int bigger = 0;
    int l = 0;
    int r = s.length()-1;
    char[] sArr = s.toCharArray();
    Map<Character, Integer> map = new HashMap<>();

    for (int i=0; i<sArr.length; i++) {
      if (!map.containsKey(sArr[i])) map.put(sArr[i], 1);
      else {
        int count = map.get(sArr[i]);
        if (count == k) bigger++;
        map.put(sArr[i], count+1);
      }
    }

    while (l < r && bigger > 0) {
      int cl = map.get(sArr[l]);
      int cr = map.get(sArr[r]);
      if (cl >= cr) {
        map.put(sArr[l], cl-1);
        if (cl == k+1) bigger--;
        l++;
      } else {
        map.put(sArr[r], cr-1);
        if (cr == k+1) bigger--;
        r--;
      }
    }

    if (bigger > 0) return 0;
    return r-l+1;
  }

  public static void main(String[] args) {
    System.out.printf("Test 1 : %s\n", 6==findLongest("abacbacbbc", 2));
    System.out.printf("Test 2 : %s\n", 4==findLongest("aaaaaabbab", 2));
    System.out.printf("Test 3 : %s\n", 10==findLongest("aaaaaabbab", 7));
    System.out.printf("Test 4 : %s\n", 2==findLongest("aaaaaabbab", 1));
    System.out.printf("Test 5 : %s\n", 0==findLongest("ab", 0));
    System.out.printf("Test 6 : %s\n", 0==findLongest("", 2));
    System.out.printf("Test 7 : %s\n", 2==findLongest("ab", 3));
  }
}
