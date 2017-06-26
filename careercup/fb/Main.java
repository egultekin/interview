package careercup.fb;

import java.util.List;
import java.util.Vector;

class Main {
  private List<Integer> getDigits(int number) {
    List<Integer> digits = new Vector<Integer>();
    while (number > 0) {
      digits.add(number%10);
      number = number/10;
    }
    return digits;
  }
  
  private int maxWithDigits(int digits) {
    if (digits > 1) return (int)(Math.pow(10, digits)-1);
    return 9;
  }
  
  private int minWithDigits(int digits) {
    if (digits > 1) return maxWithDigits(digits-1)+1;
    return 0;
  }
  
  private boolean oneDigitMissing(int n1, int n2) {
    List<Integer> n1Digits = getDigits(n1);
    List<Integer> n2Digits = getDigits(n2);
    int miss = 1;
    int i = 0, j = 0;
    while (i < n1Digits.size() && j < n2Digits.size()) {
      int digit1 = n1Digits.get(i);
      int digit2 = n2Digits.get(j);
      if (digit1 == digit2) j++;
      else {
        if (miss == 0) return false;
        else miss--;
      }
      i++;
    }
    if (i < n1Digits.size()) return false;
    return true;
  }
  
  public List<List<Integer>> getNumber(int sum) {
    List<List<Integer>> list = new Vector<List<Integer>>();
    List<Integer> sumDigits = getDigits(sum);
    int n = sumDigits.size();
    int low = 0, high = 9;
    if (n > 2) {
      if (sum > maxWithDigits(n-1)+maxWithDigits(n-2)) {
        low = minWithDigits(n-2);
        high = sum-minWithDigits(n-1);
      } else {
        low = minWithDigits(n-2);
        high = maxWithDigits(n-2);
      }
    }
    
    for(int n2=low; n2 <= high; n2++) {
      int n1 = sum-n2;
      if (oneDigitMissing(n1, n2)) {
        List<Integer> pairs = new Vector<Integer>();
        pairs.add(n1);
        pairs.add(n2);
        list.add(pairs);
      }
    }
    
    return list;
  }
  
  public static void main(String[] args) {
    Main m = new Main();
    List<List<Integer>> list = m.getNumber(134);
    for (List<Integer> pairs : list) System.out.printf("%d, %d\n", pairs.get(0), pairs.get(1));
  }
}