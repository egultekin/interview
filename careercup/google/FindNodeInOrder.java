class FindNodeInOrder {
  static class TreeNode{
      int val;
      int NumberOfchildren;
      TreeNode left;
      TreeNode right;
      public TreeNode(int val){
          this.val = val;
      }

      public void printInOrder() {
        StringBuilder sb = new StringBuilder();
        printInOrder(sb, this);
        System.out.println(sb.toString());
      }

      private void printInOrder(StringBuilder sb, TreeNode node) {
        if (null == node) return;
        printInOrder(sb, node.left);
        if (sb.length() == 0) sb.append(node.val);
        else sb.append('-').append(node.val);
        printInOrder(sb, node.right);
      }
  }

  public static int findKthOfInorder(TreeNode root, int k) {
    int temp = k;
    if (null == root) throw new IllegalArgumentException("Tree is empty.");
    if (k < 1 || k > root.NumberOfchildren+1) throw new IllegalArgumentException("k is out of bounds.");
    while (k != left(root.left)+1) {
      int pivot = left(root.left)+1;
      if (k < pivot) root = root.left;
      else {
        root = root.right;
        k -= pivot;
      }
    }

    System.out.printf("%d th element is %d\n", temp, root.val);
    return root.val;
  }

  private static int left(TreeNode root) {
    if (null == root) return 0;
    return root.NumberOfchildren+1;
  }

  private static TreeNode nullNode() {
    return new TreeNode(-1);
  }

  private static void adjust(TreeNode newNode, TreeNode oldNode) {
    int addChild = left(oldNode.left);
    oldNode.NumberOfchildren -= addChild;
    newNode.left = oldNode.left;
    oldNode.left = null;
    newNode.NumberOfchildren = left(oldNode)+addChild;
    newNode.right = oldNode;
  }

  public static TreeNode insertNthInorder(TreeNode root, int n, int val) {
    int temp = n;
    if (null == root && n > 1) {
      System.out.println("n must be at most 1 if the root is empty");
      return nullNode();
    }

    TreeNode node = null;
    if (null == root && n == 1) {
      node = new TreeNode(val);
      node.NumberOfchildren = 0;
      System.out.printf("%d th node is root.\n", n);
      return node;
    }
    else if (null == root || n > root.NumberOfchildren+2) {
      System.out.println("n is out of bounds.");
      return nullNode();
    }

    TreeNode parent = null;
    TreeNode current = root;
    while (n != left(current.left)+1) {
      parent = current;
      int pivot = left(current.left)+1;
      if (n < pivot) current = current.left;
      else {
        current = current.right;
        n -= pivot;
      }
    }

    if (null == parent) {
      node = new TreeNode(val);
      adjust(node, root);
      root = node;
      System.out.printf("%d th node is root.\n", temp);
    } else {
      node = new TreeNode(val);
      adjust(node, current);
      if (parent.left == current) {
        parent.left = node;
        System.out.println("Node is to the left of its parent.");
      }
      else {
        parent.right = node;
        System.out.println("Node is to the right of its parent.");
      }
    }

    root.printInOrder();
    return root;
  }

  public static void main(String[] args) {
    TreeNode n2 = new TreeNode(3);
    TreeNode n1 = new TreeNode(0);
    TreeNode n3 = new TreeNode(2);
    TreeNode n4 = new TreeNode(1);
    n2.left = n1;
    n2.right = n4;
    n4.left = n3;
    n3.NumberOfchildren = 0;
    n4.NumberOfchildren = 1;
    n1.NumberOfchildren = 0;
    n2.NumberOfchildren = 3;
    findKthOfInorder(n2, 1);
    findKthOfInorder(n2, 2);
    findKthOfInorder(n2, 3);
    findKthOfInorder(n2, 4);
    insertNthInorder(n2, 5, 72);
  }
}
