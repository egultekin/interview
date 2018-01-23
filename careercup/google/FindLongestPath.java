import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;

class FindLongestPath {
  static class Node {
    int id;
    Map<Integer, Integer> edges;
  }

  private Map<Integer, Node> nodes;
  private LinkedList<Integer> list;

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
    for (Map.Entry<Integer, Integer> edgeEntry : root.edges) {
      Node cNode = nodes.get(edgeEntry.key);
      int edgeW = edgeEntry.value;
      isLongest = isLongest || dfs(cNode, pathSum+edgeW);
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
    System.out.println(sb.toString());
  }
}
