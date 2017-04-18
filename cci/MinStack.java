package cci;

import java.util.*;

class MinStack {
  static class Node {
    int val, min;
    Node next;

    public Node(int v) {
      val = v;
      min = Integer.MAX_VALUE;
      next = null;
    }
    
    public void print() {
  	  StringBuilder builder = new StringBuilder();
  	  builder.append(String.format("v:%d,m:%d", val, min));
  	  Node n = next;
  	  while (n != null) {
  		  builder.append("->").append(String.format("v:%d,m:%d", n.val, n.min));
  		  n = n.next;
  	  }
  	  System.out.println(builder.toString());
    }
  }

  private Node top;
  private int capacity, size;

  public MinStack(int cap) { capacity = cap; size = 0; top = null; }
  public int size() { return size; }
  public void push(int v) {
    if (size == capacity) throw new RuntimeException("Stack overflow.");
    Node n = new Node(v);
    n.next = top;
    n.min = v;
    if (null != top && v > top.min) n.min = top.min;
    size++;
    top = n;
  }

  public int pop() {
    if (size == 0) throw new EmptyStackException();
    int result = top.val;
    top = top.next;
    size--;
    return result;
  }

  public int min() {
    if (size == 0) throw new EmptyStackException();
    return top.min;
  }
  
  public Node peek() { return top; }
    
  public static void main(String[] args) {
	  MinStack stack = new MinStack(5);
	  stack.push(9);
	  stack.push(7);
	  stack.push(-1);
	  if (stack.pop() == -1) System.out.println("Pop seems ok.");
	  stack.push(19);
	  System.out.printf("Stack min element is %d\n", stack.min());
	  stack.push(3);
	  System.out.printf("Stack min element is %d\n", stack.min());
	  stack.peek().print();
	  stack.push(7);
	  stack.peek().print();
	  stack.push(8);
  }
}
