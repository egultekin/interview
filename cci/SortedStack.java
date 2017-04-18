package cci;

import java.util.*;

class SortedStack {
  private LinkedList<Integer> sorted, temp;
  private int size;

  public SortedStack() {
    sorted = new LinkedList<Integer>();
    temp = new LinkedList<Integer>();
    size = 0;
  }

  public void push(Integer v) {
    while (!sorted.isEmpty() && v > sorted.peek()) temp.add(sorted.pollLast());
    sorted.add(v);
    while (!temp.isEmpty()) sorted.add(temp.pollLast());
    size++;
  }

  public Integer pop() {
    if (size == 0) return null;
    Integer result = sorted.pollLast();
    size--;
    return result;
  }

  public Integer peek() {
    if (size == 0) return null;
    return sorted.peek();
  }

  public boolean isEmpty() { return size == 0; }
  
}
