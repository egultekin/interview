package cci;

import java.util.*;

class ConvertBSTIntoDoubleLinkedList {

  static class BiNode {
    BiNode node1, node2;
    int data;

    public BiNode(int d) {
      data = d;
      node1 = null;
      node2 = null;
    }

    public void print() {
      StringBuilder builder = new StringBuilder();
      builder.append(data);
      BiNode node = node2;
      while (node != null) {
        builder.append("<->").append(node.data);
        node = node.node2;
      }

      System.out.println(builder.toString());
    }
  }

  public BiNode toDoubleLinkedList(BiNode node) {
    BiNode head = null, tail = null, parent = null;
    if (null == node) return node;
    Stack<BiNode> s = new Stack<>();
    if (null == node.node1) head = tail = node;
    else {
      s.push(node);
      node = node.node1;
    }
    while (!s.isEmpty() || null != node.node2) {
      if (null != node.node1) {
        s.push(node);
        node = node.node1;
      } else {
        if (null != tail) {
          tail.node2 = node;
          node.node1 = tail;
          tail = node;
        } else tail = node;
        if (null == head) head = tail;
        if (null != node.node2) node = node.node2;
        else if (!s.isEmpty()) {
          parent = s.pop();
          if (null != tail) tail.node2 = parent;
          parent.node1 = tail;
          tail = parent;
        }
      }
    }

    return head;
  }

  public static void main(String[] args) {
    ConvertBSTIntoDoubleLinkedList.BiNode node1 = new ConvertBSTIntoDoubleLinkedList.BiNode(1);
    ConvertBSTIntoDoubleLinkedList.BiNode node2 = new ConvertBSTIntoDoubleLinkedList.BiNode(2);
    ConvertBSTIntoDoubleLinkedList.BiNode node3 = new ConvertBSTIntoDoubleLinkedList.BiNode(3);
    node1.node2 = node2;
    node2.node1 = node3;

    node1.print();
    ConvertBSTIntoDoubleLinkedList sol = new ConvertBSTIntoDoubleLinkedList();
    ConvertBSTIntoDoubleLinkedList.BiNode dll = sol.toDoubleLinkedList(node1);
    dll.print();
  }
}

