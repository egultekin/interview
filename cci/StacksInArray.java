package cci;

import java.util.*;

class StacksInArray {
  static class Node {
    int prev, data;

    public Node(int p, int d) {
      prev = p;
      data = d;
    }

  }
  private int top[], size, capacity, free;
  private Node[] arr;

  public StacksInArray(int cap, int nStacks) {
    top = new int[nStacks];
    capacity = cap;
    arr = new Node[capacity];
    Arrays.fill(top, -1);
    for (int i = 0; i < capacity-1; i++) arr[i] = new Node(i+1, -1);
    arr[capacity-1] = new Node(-1, -1);
    size = 0;
    free = 0;
  }

  public void push(int stack, int data) {
    if (free == -1) throw new IndexOutOfBoundsException("No space left.");
    if (stack < 0 || stack > top.length) throw new IllegalArgumentException("Invalid stack index.");
    Node freeNode = arr[free];
    int oldTop = top[stack];
    top[stack] = free;
    free = freeNode.prev;
    freeNode.data = data;
    freeNode.prev = oldTop;
    size++;
  }

  public int pop(int stack) {
    if (stack < 0 || stack > top.length) throw new IllegalArgumentException("Invalid stack index.");
    if (size == 0 || top[stack] == -1) throw new EmptyStackException();
    Node node = arr[top[stack]];
    int result = node.data;
    int newTop = node.prev;
    node.data = -1;
    node.prev = free;
    free = top[stack];
    top[stack] = newTop;
    size--;
    return result;
  }

  public void print(int stack) {
    if (stack < 0 || stack > top.length) throw new IllegalArgumentException("Invalid stack index.");
    if (top[stack] == -1) {
      System.out.printf("Stack %d is empty.\n", stack);
      return;
    }

    Node node = arr[top[stack]];
    StringBuilder builder = new StringBuilder();
    builder.append(String.format("Stack %d: ", stack));
    builder.append(node.data);
    int prev = node.prev;
    while (prev != -1) {
      Node prevNode = arr[prev];
      builder.append("->").append(prevNode.data);
      prev = prevNode.prev;
    }

    System.out.println(builder.toString());
  }
  
  public int size() {
	  return size;
  }


  public static void main(String[] args) {
	  StacksInArray stack = new StacksInArray(10, 4);
	  stack.push(0, 5);
	  stack.push(1, 7);
	  stack.push(1, 9);
	  stack.push(1, 2);
	  int val = stack.pop(0);
	  stack.pop(1);
	  System.out.println(val);
	  stack.print(0);
	  stack.print(1);
	  stack.push(1, 8);
	  stack.print(1);
	  stack.print(2);
	  stack.print(3);
	  //stack.print(4);
	  stack.push(0, 12);
	  stack.push(0, 13);
	  stack.push(0, 14);
	  stack.push(0, 15);
	  stack.push(0, 16);
	  stack.push(0, 17);
	  stack.push(0, 18);
	  stack.print(0);
	  System.out.println(stack.pop(0));
	  stack.push(1, 19);
	  stack.print(0);
	  stack.print(1);
	  System.out.println(stack.size());
	  stack.pop(0);
	  System.out.println(stack.size());
	  stack.push(3, 88);
	  stack.print(3);
	  System.out.println(stack.size());
  }
}
