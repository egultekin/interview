import java.util.LinkedList;

class RestroomReserve {
  static class Range {
    int min, max;
    public Range(int m, int x) {
      min = m;
      max = x;
    }
  }

  public static int solution(int N, int person) {
    int result = 0;
    if (person < 1 || N < 1) return 0;
    if (N <= person) return N*(N+1)/2;
    LinkedList<Range> queue = new LinkedList<>();
    Range range = new Range(1, N);
    boolean[] marked = new boolean[N+1];
    queue.addLast(range);

    while (!queue.isEmpty() && person > 0) {
      range = queue.pollFirst();
      int mid = (range.min+range.max)/2;
      if (person > 0 && !marked[range.min]) {
        marked[range.min] = true;
        person--;
        result += range.min;
      }

      if (person > 0 && !marked[range.max]) {
        marked[range.max] = true;
        person--;
        result += range.max;
      }

      if (mid > range.min) queue.addLast(new Range(range.min, mid));
      if ((range.max-range.min)%2 == 1) mid++;
      if (mid < range.max) queue.addLast(new Range(mid, range.max));
    }

    queue.clear();
    return result;
  }

  public static void main(String[] args) {
    System.out.println(solution(5, 4) == 11);
    System.out.println(solution(5, 3) == 9);
    System.out.println(solution(5, 2) == 6);
    System.out.println(solution(5, 1) == 1);
    System.out.println(solution(1, 2) == 1);
    System.out.println(solution(2, 2) == 3);
    System.out.println(solution(10, 4) == 22);
  }
}
