package cci;

import java.util.*;

public class LRUCache {

  static class Node {
    int val;
    String key;
    Node prev, next;

    public Node(String k, int value) {
    	key = k;
    	val = value;
    	prev = null;
    	next = null;
    }
  }

  Node head, tail;
  HashMap<String, Node> map;
  int capacity, size;

  public LRUCache(int max) {
	  capacity = max;
	  size = 0;
	  map = new HashMap<String, Node>();
	  head = tail = null;
  }

  public Integer get(String key) {
	  if (!map.containsKey(key)) return null;
	  Node n = map.get(key);
	  use(n);
	  return n.val;
  }

  private void use(Node node) {
	  if (head == node) return;
	  node.prev.next = node.next;
	  if (node.next != null) node.next.prev = node.prev;
	  if (tail == node) tail = node.prev;
	  node.prev = null;
	  node.next = head;
	  head.prev = node;
	  head = node;
  }

  public void insert(String key, Integer value) {
	  if (size == capacity) {
		  map.remove(tail.key);
		  if (head != tail) {
			  Node remove = tail;
			  remove.prev.next = tail.next;
			  tail = tail.prev;
			  remove.prev = null;
		  } else {
			  head = null;
			  tail = null;
		  }
		  size--;
	  }
	  Node ins = new Node(key, value);
	  map.put(key, ins);
	  ins.next = head;
	  if (head != null) head.prev = ins;
	  head = ins;
	  if (null == head.next) tail = head;
	  size++;
  }
  
  public void print() {
	  if (null == head) return;
	  Node n = head;
	  StringBuilder builder = new StringBuilder();
	  builder.append(String.format("%s:%d", n.key, n.val));
	  while (null != n.next) {
		  n = n.next;
		  builder.append("->").append(String.format("%s:%d", n.key, n.val));
	  }
	  
	  System.out.println(builder.toString());
  }
  
  public static void main(String[] arg) {
	  LRUCache cache = new LRUCache(1);
	  cache.insert("key1", 5);
	  cache.print();
	  cache.insert("key2", 7);
	  cache.print();
	  cache.get("key1");
	  cache.print();
	  cache.insert("key3", 1);
	  cache.insert("key4", 9);
	  cache.insert("key5", 8);
	  cache.print();
	  cache.get("key3");
	  cache.insert("key6", 4);
	  //cache.insert("key7", 6);
	  cache.print();
  }
}
