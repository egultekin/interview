import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

class FindLCANode {
  static class Node {
    int label;
    List<Node> neighbors;

    public Node(int x) {
      this.label = x;
      neighbors = new ArrayList<>();
    }

    @Override
    public boolean equals(Object to) {
      if (to instanceof Node) {
        if (this.label == ((Node)to).label)
          return true;
      }
      return false;
    }
  }

  public static Node lca(Node root, Node n1, Node n2) {
    boolean foundN1, foundN2;
    foundN1 = foundN2 = false;
    Node found = null;
    if (root.equals(n1)) {
      System.out.printf("Found node %d, root %d\n", n1.label, root.label);
      foundN1 = true;
      found = n1;
    } else if (root.equals(n2)) {
      System.out.printf("Found node %d, root %d\n", n2.label, root.label);
      foundN2 = true;
      found = n2;
    }

    Iterator<Node> it = root.neighbors.iterator();
    while (!(foundN1 && foundN2) && it.hasNext()) {
      Node next = it.next();
      Node check = lca(next, n1, n2);
      if (null != check) {
        if (check.equals(n1)) foundN1 = true;
        else if (check.equals(n2)) foundN2 = true;
        found = check;
      }
    }

    if (foundN1 && foundN2) return root;
    return found;
  }

  public static void main(String[] args) {
    Node root = new Node(0);
    Node c1 = new Node(1);
    Node c2 = new Node(2);
    Node c3 = new Node(3);
    Node c31 = new Node(31);
    Node c32 = new Node(32);
    Node c311 = new Node(311);
    Node c321 = new Node(321);
    Node c4 = new Node(4);
    c32.neighbors.add(c321);
    c31.neighbors.add(c311);
    c3.neighbors.add(c31);
    c3.neighbors.add(c32);
    root.neighbors.add(c1);
    root.neighbors.add(c2);
    root.neighbors.add(c3);

    Node n = lca(root, c1, c2);
    System.out.printf("LCA is %d\n", n.label);
  }
}
