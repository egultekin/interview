class NearestNumberBST {
  static class Node {
    Node left, right;
    int value;
    public Node(int val) {
      value = val;
      left = right = null;
    }
  }

  private Node root;
  public NearestNumberBST(Node r) {
    root = r;
  }

  public void printNearest(int number) {
    System.out.printf("Running for number %d\n", number);
    int min = Integer.MIN_VALUE;
    int max = Integer.MAX_VALUE;
    Node next = root;
    while (next != null) {
      if (next.value == number) {
        max = greater(next);
        min = lesser(next);
        break;
      } else if (next.value > number) {
        if (next.value < max) max = next.value;
        next = next.left;
      } else {
        if (next.value > min) min = next.value;
        next = next.right;
      }
    }

    if (min != Integer.MIN_VALUE) System.out.printf("Lesser number is %d\n", min);
    if (max != Integer.MAX_VALUE) System.out.printf("Greater number is %d\n", max);
  }

  private int lesser(Node n) {
    if (null == n.left) return n.value;
    Node next = n.left;
    while (next.right != null) next = next.right;
    return next.value;
  }

  private int greater(Node n) {
    if (null == n.right) return n.value;
    Node next = n.right;
    while (next.left != null) next = next.left;
    return next.value;
  }

  public static void main(String[] args) {
    Node n17 = new Node(17);
    Node n9 = new Node(9);
    Node n14 = new Node(14);
    Node n12 = new Node(12);
    Node n23 = new Node(23);
    Node n19 = new Node(19);
    n14.left = n12;
    n9.right = n14;
    n17.left = n9;
    n23.left = n19;
    n17.right = n23;
    new NearestNumberBST(n17).printNearest(14);
    new NearestNumberBST(n17).printNearest(13);
    new NearestNumberBST(n17).printNearest(18);
    new NearestNumberBST(n17).printNearest(23);
    new NearestNumberBST(n17).printNearest(26);
    new NearestNumberBST(n17).printNearest(3);
  }
}
