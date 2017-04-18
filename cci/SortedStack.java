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
    while (!sorted.isEmpty() && v > sorted.peekLast()) temp.add(sorted.pollLast());
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
    return sorted.peekLast();
  }

  public boolean isEmpty() { return size == 0; }
  
  public void print() {
	  StringBuilder builder = new StringBuilder();
	  if (!isEmpty()) builder.append(pop());
	  while (!isEmpty()) builder.append("->").append(pop());
	  System.out.println(builder.toString());
  }
  
  public static void main(String[] args) {
	  SortedStack stack = new SortedStack();
	  stack.push(5);
	  stack.push(3);
	  stack.push(12);
	  stack.push(7);
	  if (stack.pop() == 3) System.out.println("Pop seems to work.");
	  else System.out.println("Pop does not work.");
	  stack.push(18);
	  stack.push(2);
	  stack.print();
  }
}
