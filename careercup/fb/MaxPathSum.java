import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

class MaxPathSum {
  static class Node {
    Node left, right;
    int value;

    public Node(int v) {
      value = v;
      left = null;
      right = null;
    }
  }

  int maxSum;
  List<Integer> maxSumPath;

  public MaxPathSum() {
  }

  int runningSum(Node root, List<Integer> path) {
    if (null == root) return 0;
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    int rsl = runningSum(root.left, left);
    int rsr = runningSum(root.right, right);
    int rs = root.value;

    if (rsl > 0 && rsl >= rsr) {
      rs += rsl;
      path.addAll(left);
    }

    path.add(root.value);
    if (rsr > 0 && rsr > rsl) {
      rs += rsr;
      path.addAll(right);
    }

    if (rs > maxSum) {
      maxSum = rs;
      maxSumPath = path;
    }

    if (root.value + rsl + rsr > maxSum) {
      maxSum = root.value+rsl+rsr;
      List<Integer> mp = new ArrayList<>();
      mp.addAll(left);
      mp.add(root.value);
      mp.addAll(right);
      maxSumPath = mp;
    }

    return rs;
  }

  public List<Integer> findMaxPath(Node root) {
    maxSum = Integer.MIN_VALUE;
    maxSumPath = null;
    List<Integer> path = new ArrayList<>();
    int rs = runningSum(root, path);
    StringBuilder sb = new StringBuilder();
    Iterator<Integer> it = maxSumPath.iterator();
    if (it.hasNext()) sb.append(it.next());
    while (it.hasNext()) sb.append(".").append(it.next());
    System.out.println(sb.toString());
    return maxSumPath;
  }

  public static void main(String[] args) {
    Node n1 = new Node(1);
    Node n11 = new Node(2);
    Node n12 = new Node(3);
    n1.left = n11;
    n1.right = n12;
     MaxPathSum mps = new MaxPathSum();
    mps.findMaxPath(n1);

    Node n2 = new Node(1);
    Node n21 = new Node(-5);
    Node n22 = new Node(4);
    n2.left = n22;
    n2.right = n21;
    mps.findMaxPath(n2);

    Node n3 = new Node(-5);
    Node n31 = new Node(-2);
    Node n32 = new Node(3);
    Node n322 = new Node(-3);
    n3.left = n31;
    n3.right = n32;
    n32.right = n322;
    mps.findMaxPath(n3);
  }
}
