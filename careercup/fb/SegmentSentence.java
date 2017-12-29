/*
* Segment a given string into a number of strings of length k
* Find the number of strings needed n
*
* https://www.careercup.com/question?id=5718859091804160
*/

class SegmentString {
  public int numberOfCutsRequired(String s, int k) {
    int cuts = 0;
    int remains = s.length();
    int digits = 4;
    int trial = 1;
    while (remains > 0 && k >= digits+trial) {
      int floor = 10;
      remains = s.length();
      for (int i=0; i<=trial; i++) {
        if (remains > (k-digits-trial)*(floor-1)) {
          remains -= (k-digits-trial)*(floor-1);
          cuts += floor-1;
          floor *= 10;
        } else {
          remains = 0;
          cuts += remains / (k-digits-trial);
        }
      }
    }

    return cuts;
  }
}
