import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;

class FindLongestPath {
  static class Node {
    int id;
    Map<Integer, Integer> edges;

    public Node(int iden) {
      id = iden;
      edges = new HashMap<>();
    }

    public void addEdge(int id, int wei) {
      edges.put(id, wei);
    }
  }

  private Map<Integer, Node> nodes;
  private LinkedList<Integer> list;
  private int longest;

  public FindLongestPath() {
    longest = Integer.MIN_VALUE;
    list = new LinkedList<>();
    nodes = new HashMap<>();
  }

  public void addNode(Node node) {
    nodes.put(node.id, node);
  }

  private boolean dfs(Node root, int pathSum) {
    boolean isLongest = false;
    for (Map.Entry<Integer, Integer> edgeEntry : root.edges.entrySet()) {
      Node cNode = nodes.get(edgeEntry.getKey());
      int edgeW = edgeEntry.getValue();
      isLongest = dfs(cNode, pathSum+edgeW) || isLongest;
    }

    if (pathSum > longest) {
      list.clear();
      longest = pathSum;
      isLongest = true;
      list.add(root.id);
    } else if (isLongest) list.add(root.id);

    return isLongest;
  }

  public void find(Node root) {
    dfs(root, 0);
    StringBuilder sb = new StringBuilder();
    Integer next = list.pollLast();
    sb.append(next);
    while (!list.isEmpty()) {
      next = list.pollLast();
      sb.append('-').append(next);
    }
    System.out.printf("Longest path is %s with length %d.\n", sb.toString(), longest);
  }

  public static void main(String[] args) {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);
    Node n7 = new Node(7);
    Node n8 = new Node(8);
    Node n9 = new Node(9);

    n1.addEdge(2, 3);
    n1.addEdge(3, 25);
    n1.addEdge(4, 9);
    n1.addEdge(5, 12);
    n2.addEdge(6, 7);
    n2.addEdge(7, 6);
    n2.addEdge(8, 2);
    n2.addEdge(9, 1);

    FindLongestPath flp = new FindLongestPath();
    flp.addNode(n1);
    flp.addNode(n2);
    flp.addNode(n3);
    flp.addNode(n4);
    flp.addNode(n5);
    flp.addNode(n6);
    flp.addNode(n7);
    flp.addNode(n8);
    flp.addNode(n9);

    flp.find(n1);
  }
}
