/*
* https://careercup.com/question?id=6218506243670016
* Check if the given list corresponds to a preorder traversal
*
*/

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Arrays;

class PreorderList {
  static class Node {
    int id, parent;
    public Node(int iden, int par) {
      id = iden;
      parent = par;
    }
  }

  public static boolean isPreOrder(List<Node> list) {
    int n = list.size();
    if (n < 1) {
      System.out.println("Invalid list.");
      return false;
    }
    if (n == 1) return true;
    LinkedList<Node> stack = new LinkedList<>();
    Iterator<Node> it = list.iterator();
    stack.addLast(it.next());
    while (it.hasNext()) {
      Node next = it.next();
      while (!stack.isEmpty() && stack.peekLast().id != next.parent)
        stack.pollLast();
      if (stack.isEmpty()) return false;
      stack.addLast(next);
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println(true == isPreOrder(Arrays.asList(new Node(1,0), new Node(5,1), new Node(2,5), new Node(3,5), new Node(2,1))));
    System.out.println(false == isPreOrder(Arrays.asList(new Node(1,0), new Node(5,1), new Node(2,5), new Node(2,1), new Node(3,5))));
  }
}
