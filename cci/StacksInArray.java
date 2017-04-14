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
    if (free == -1) throw new OutOfMemoryException("No space left.");
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
    if (size == 0 || top[stack] == -1) throw new Exception("Stack empty.");
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
      System.out.println("Stack is empty.");
      return;
    }

    Node node = arr[top[stack]];
    StringBuilder builder = new StringBuilder();
    builder.append(node.data);
    int prev = node.prev;
    while (prev != -1) {
      Node prevNode = arr[prev];
      builder.append("->").append(prevNode.data);
      prev = prevNode.prev;
    }

    System.out.println(builder.toString());
  }


}
