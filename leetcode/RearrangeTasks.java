package leetcode;

import java.util.*;

/*
 * Given a set of tasks like [A, A, B], and int k, which is the waiting time between two same tasks. 
 * Calculate the min total execution time if you are allowed to rearrange the tasks. Assume the execution for each individual task is 1.
 * In above example
 * A A B, k = 1, without rearrangement, the execution time would be 4:
 * 
 * A wait A B
 * 1  1   1 1
 * 
 * with rearrangement, the execution time would be 3:
 * A B A
 * 1 1 1
 * 
 *  https://discuss.leetcode.com/topic/28289/rearrange-tasks
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 */

class RearrangeTasks {
  static class Task {
    // frequency
    int f;
    char id;

    public Task(int frequency, char identifier) {
      f = frequency;
      id = identifier;
    }
  }

  public int minTime(String tasks, int wait) {
    char[] t = tasks.toCharArray();
    HashMap<Character, Task> tSet = new HashMap<Character, Task>();
    for (int i = 0; i < t.length; i++) {
      Task nt = tSet.get(t[i]);
      if (null == nt) nt = new Task(0, t[i]);
      nt.f++;
      tSet.put(t[i], nt);
    }
    
	PriorityQueue<Task> pq = new PriorityQueue<Task>(1, new Comparator<Task>() {
      @Override
      public int compare(final Task a, final Task b) {
        return b.f - a.f;
      }
    });
    
    Iterator<Task> it = tSet.values().iterator();
    while (it.hasNext()) pq.add(it.next());
    int time = 0;
    StringBuilder sb = new StringBuilder();
    while (!pq.isEmpty()) {
      Task b = pq.poll();
      Task l = (!pq.isEmpty()) ? pq.poll() : new Task(0,'a');
      while ((pq.isEmpty() && l.f > 0) || (!pq.isEmpty() && l.f >= pq.peek().f)) {
        b.f--;
        l.f--;
        time += 2;
        sb.append(b.id).append(l.id);
      }
      if (pq.isEmpty() && b.f > 0) {
        time++;
        b.f--;
        sb.append(b.id);
      }
      while (pq.isEmpty() && b.f > 0) {
        time += wait;
        time++;
        b.f--;
        sb.append(b.id);
      }

      if (b.f > 0) pq.add(b);
      if (l.f > 0) pq.add(l);
    }

    System.out.printf("New arrangement: %s\n", sb.toString());
    return time;
  }
  
  public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int wait = sc.nextInt();
	String tasks = sc.next();
	sc.close();
	
	System.out.printf("Min time for completing tasks %s is %d.\n", tasks, new RearrangeTasks().minTime(tasks, wait));
  }
}
