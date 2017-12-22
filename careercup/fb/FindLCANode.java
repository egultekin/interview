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
        if (this.label == to.label)
          return true;
      }
      return false;
    }
  }

  public static Node lca(Node root, Node n1, Node n2) {
    boolean foundN1, foundN2;
    Node found = null;
    if (root.equals(n1)) {
      foundN1 == true;
      found = n1;
    } else if (root.equals(n2)) {
      foundN2 = true;
      found = n2;
    }

    Iterator<Node> it = root.neighbors.iterator();
    while (!(foundN1 && foundN2) && it.hasNext()) {
      Node next = it.next();
      found = lca(next, n1, n2);
      if (null != found) {
        if (found.equals(n1)) foundN1 = true;
        else if (found.equals(n2)) foundN2 = true;
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
    c32.neighbors.add(c321);
    c31.neighbors.add(c311);
    c3.neighbors.add(c31);
    c3.neighbors.add(c32);
    root.neighbors.add(c1);
    root.neighbors.add(c2);
    root.neighbors.add(c3);

    Node n = lca(root, c311, c321);
    System.out.printf("LCA is %d\n", n.label);
  }
}
