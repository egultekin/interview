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
  
  private BiNode head = null, tail = null;
  private Stack<BiNode> s;
  
  public ConvertBSTIntoDoubleLinkedList() {
	  s = new Stack<>();
	  head = null;
	  tail = null;
  }
  
  private void addTail(BiNode node) {
	  node.node1 = tail;
	  if (null != tail) tail.node2 = node;
	  tail = node;
	  if (null == head) head = tail; 
  }
  
  private BiNode traverseLeft(BiNode node) {
	  if (null == node) return null;
	  BiNode t = node.node1;
	  node.node1 = null;
	  s.push(node);
	  return t;
  }

  public void toDoubleLinkedListRecursive(BiNode node) {
	  if (null == node) return;
	  toDoubleLinkedListRecursive(node.node1);
	  addTail(node);
	  toDoubleLinkedListRecursive(node.node2);
  }
  
  public void toDoubleLinkedListIterative(BiNode node) {
    if (null == node) return;
	       
    while (!s.isEmpty() || null != node.node2 || null != node.node1) {
      if (null != node.node1) node = traverseLeft(node);
      else {    	  
        addTail(node);
        if (null != node.node2) node = node.node2;
        else if (!s.isEmpty()) node = s.pop();
      }
    }
    
    addTail(node);
  }

  public static void main(String[] args) {
    ConvertBSTIntoDoubleLinkedList.BiNode node1 = new ConvertBSTIntoDoubleLinkedList.BiNode(1);
    ConvertBSTIntoDoubleLinkedList.BiNode node2 = new ConvertBSTIntoDoubleLinkedList.BiNode(2);
    ConvertBSTIntoDoubleLinkedList.BiNode node3 = new ConvertBSTIntoDoubleLinkedList.BiNode(3);
    node1.node2 = node2;
    node2.node1 = node3;

    ConvertBSTIntoDoubleLinkedList sol = new ConvertBSTIntoDoubleLinkedList();
    sol.toDoubleLinkedListIterative(node1);
    sol.head.print();
    
    ConvertBSTIntoDoubleLinkedList.BiNode node11 = new ConvertBSTIntoDoubleLinkedList.BiNode(15);
    ConvertBSTIntoDoubleLinkedList.BiNode node12 = new ConvertBSTIntoDoubleLinkedList.BiNode(4);
    ConvertBSTIntoDoubleLinkedList.BiNode node13 = new ConvertBSTIntoDoubleLinkedList.BiNode(2);
    ConvertBSTIntoDoubleLinkedList.BiNode node14 = new ConvertBSTIntoDoubleLinkedList.BiNode(12);
    ConvertBSTIntoDoubleLinkedList.BiNode node15 = new ConvertBSTIntoDoubleLinkedList.BiNode(7);
    ConvertBSTIntoDoubleLinkedList.BiNode node16 = new ConvertBSTIntoDoubleLinkedList.BiNode(14);
    
    node11.node1 = node12;
    node12.node1 = node13;
    node12.node2 = node14;
    node14.node1 = node15;
    node14.node2 = node16;
    
    ConvertBSTIntoDoubleLinkedList sol2 = new ConvertBSTIntoDoubleLinkedList();
    sol2.toDoubleLinkedListIterative(node11);
    sol2.head.print();
    
    BiNode node17 = new BiNode(5);
    BiNode node18 = new BiNode(19);
    node17.node2 = node18;
    ConvertBSTIntoDoubleLinkedList sol3 = new ConvertBSTIntoDoubleLinkedList();
    sol3.toDoubleLinkedListIterative(node17);
    sol3.head.print();
    
    BiNode node19 = new BiNode(29);
    BiNode node20 = new BiNode(35);
    node19.node2 = node20;
    ConvertBSTIntoDoubleLinkedList sol4 = new ConvertBSTIntoDoubleLinkedList();
    sol4.toDoubleLinkedListIterative(node19);
    sol4.head.print();

  }
}

