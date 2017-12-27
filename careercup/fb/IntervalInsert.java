import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

class IntervalInsert {
  static class Interval {
    int start, end;
    public Interval(int s, int e) {
      start = s;
      end = e;
    }
  }

  public static List<Interval> insert(List<Interval> list, int val) {
    System.out.printf("Starting with list %s.\n", formatList(list));
    int left = 0;
    int right = list.size()-1;

    while (left < right) {
      int mid = (left+right)/2;
      Interval check = list.get(mid);
      if (check.end < val) left = mid+1;
      else if (check.start > val) right = mid;
      else left = right = mid;
    }

    int insertAt = left;
    if (left == list.size()-1 && list.get(left).end < val) insertAt++;

    Interval newInterval = new Interval(val, val);
    if (insertAt == list.size()) {
      System.out.printf("Appending interval [%d-%d] to end.\n", newInterval.start, newInterval.end);
      list.add(newInterval);
    }
    else {
      System.out.printf("Appending interval [%d-%d] to index %d.\n", newInterval.start, newInterval.end, insertAt);
      list.add(insertAt, newInterval);
    }

    boolean contCheck = true;
    while (contCheck) {
      Interval before = null;
      Interval after = null;
      contCheck = false;

      if (insertAt > 0) before = list.get(insertAt-1);
      Interval interval = list.get(insertAt);
      if (null != before && before.end == interval.start-1) {
        System.out.printf("Merging interval [%d-%d] with [%d-%d]. New list size is %d\n",
          before.start, before.end,
          interval.start, interval.end,
          list.size()-1);
        before.end = interval.end;
        list.remove(insertAt);
        insertAt--;
        contCheck = true;
        interval = before;
      }

      if (insertAt < list.size()-1) after = list.get(insertAt+1);
      if (null != after && after.start == interval.end+1) {
        System.out.printf("Merging interval [%d-%d] with [%d-%d]. New list size is %d\n",
          interval.start, interval.end,
          after.start, after.end,
          list.size()-1);
        after.start = interval.start;
        list.remove(insertAt);
        contCheck = true;
        interval = after;
      }
    }

    System.out.println(formatList(list));
    return list;
  }

  public static String formatList(List<Interval> list) {
    StringBuilder sb = new StringBuilder();
    Iterator<Interval> it = list.iterator();
    if (it.hasNext()) {
      Interval next = it.next();
      sb.append(String.format("[%d-%d]", next.start, next.end));
    }

    while (it.hasNext()) {
      Interval next = it.next();
      sb.append(",").append(String.format("[%d-%d]", next.start, next.end));
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    List<Interval> list = new ArrayList<>();
    Interval i1 = new Interval(1,4);
    Interval i2 = new Interval(6,8);
    list.add(i1);
    list.add(i2);
    list = insert(list, 9);
    list = insert(list, 11);
    list = insert(list, 10);
  }
}
