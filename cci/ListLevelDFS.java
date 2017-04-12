package cci;

import java.util.*;

class ListLevelDFS {
  Vector<LinkedList<Node>> result;

  static class Node {
    Node left, right;
    int val;

    public Node(int value) {
      val = value;
      left = null;
      right = null;
    }
  }
  
  public ListLevelDFS() {
    result = new Vector<LinkedList<Node>>();
  }

  void addNode(Node n, int level) {
    if (null == n) return;
    if (level > result.size() || level < 0) throw new IndexOutOfBoundsException("Cannot add node to index due to invalid range.");
    if (level == result.size()) result.add(new LinkedList<Node>());
    result.get(level).add(n);
  }

  void dfs(Node n, int level) {
    if (null == n) return;
    addNode(n, level);
    dfs(n.left, level+1);
    dfs(n.right, level+1);
  }

  void print() {
    StringBuilder builder = new StringBuilder();
    for (LinkedList<Node> list : result) {
      Iterator<Node> iter = list.iterator();
      if (iter.hasNext()) builder.append(iter.next().val);
      while (iter.hasNext()) builder.append("->").append(iter.next().val);
      builder.append("\n");
    }

    System.out.print(builder.toString());
  }

  public static void main(String[] args) {
    ListLevelDFS.Node root = new ListLevelDFS.Node(0);
    ListLevelDFS.Node left = new ListLevelDFS.Node(1);
    ListLevelDFS.Node right = new ListLevelDFS.Node(2);
    root.left = left;
    root.right = right;
    left.left = new ListLevelDFS.Node(3);
    left.right = new ListLevelDFS.Node(4);
    right.left = new ListLevelDFS.Node(5);

    ListLevelDFS sol = new ListLevelDFS();
    sol.dfs(root, 0);
    sol.print();
  }
}
