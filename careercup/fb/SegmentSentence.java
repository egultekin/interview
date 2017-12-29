/*
* Segment a given string into a number of strings of length k
* Find the number of strings needed n
*
* https://www.careercup.com/question?id=5718859091804160
*/

class SegmentSentence {
  public static int numberOfCutsRequired(String s, int k) {
    int cuts = 0;
    int remains = s.length();
    int digits = 4;
    int trial = 1;
    while (remains > 0 && k >= digits+trial) {
      int floor = 10;
      remains = s.length();
      for (int i=1; i<=trial; i++) {
        if (remains > (k-digits-trial)*(floor-1)) {
          remains -= (k-digits-trial)*(floor-1);
          cuts += floor-1;
          floor *= 10;
        } else {
          cuts += 1+remains / (k-digits-trial);
          remains = 0;
        }
      }
    }

    return cuts;
  }

  private static String print(String s, int k, int cuts) {
    StringBuilder sb = new StringBuilder();
    
    return sb.toString();
  }

  public static void main(String[] args) {
    String s = "This is a good day.";
    System.out.printf("Can be cut in %d steps.", numberOfCutsRequired(s, 10));
  }
}
