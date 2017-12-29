import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class FindAndRemoveCycle {
  static class DirectedGraphNode {
    int label;
    List<DirectedGraphNode> neighbors;

    public DirectedGraphNode(int l) {
      label = l;
      neighbors = new ArrayList<DirectedGraphNode>();
    }

    @Override
    public boolean equals(Object node) {
      if (null == node) return false;
      if (!(node instanceof DirectedGraphNode)) return false;
      return ((DirectedGraphNode)node).label == label;
    }

    @Override
    public int hashCode() {
      return label;
    }
  }

  DirectedGraphNode graph;
  Set<DirectedGraphNode> visited;
  Set<DirectedGraphNode> processed;

  public FindAndRemoveCycle(DirectedGraphNode g) {
    graph = g;
  }

  private boolean hasCycle(DirectedGraphNode node) {
    visited.add(node);
    boolean hasCycle = false;
    for (DirectedGraphNode neighbor : node.neighbors) {
      if (!hasCycle) {
        if (!visited.contains(neighbor)) hasCycle = hasCycle(neighbor);
        else {
          hasCycle = true;
          System.out.printf("Graph has a cycle between nodes %d and %d.\n", node.label, neighbor.label);
        }
      }
    }
    return hasCycle;
  }

  private void removeAllCycles(DirectedGraphNode node) {
    visited.add(node);
    for (int i=0; i < node.neighbors.size(); i++) {
      DirectedGraphNode neighbor = node.neighbors.get(i);
      if (!visited.contains(neighbor)) removeAllCycles(neighbor);
      else {
        System.out.printf("Removing a cycle cutting link between nodes %d and %d.\n", node.label, neighbor.label);
        node.neighbors.remove(i);
      }
    }
  }

  public boolean hasCycle() {
    visited = new HashSet<>();
    processed = new HashSet<>();
    boolean result = hasCycle(graph);
    if (!result) System.out.println("Graph contains no cycle.");
    return result;
  }

  public void removeAllCycles() {
    visited = new HashSet<>();
    processed = new HashSet<>();
    removeAllCycles(graph);
  }

  public static void main(String[] args) {
    DirectedGraphNode n0 = new DirectedGraphNode(0);
    DirectedGraphNode n1 = new DirectedGraphNode(1);
    DirectedGraphNode n2 = new DirectedGraphNode(2);
    DirectedGraphNode n3 = new DirectedGraphNode(3);
    //n0.neighbors.add(n1);
    n0.neighbors.add(n2);
    n2.neighbors.add(n3);
    n3.neighbors.add(n1);
    FindAndRemoveCycle find = new FindAndRemoveCycle(n0);
    find.hasCycle();
    find.removeAllCycles();
    find.hasCycle();
  }

}
