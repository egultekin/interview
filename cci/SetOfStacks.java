package cci;

import java.util.*;

class SetOfStacks {
  private int capacity;
  private Vector<LinkedList<Integer>> set;

  public SetOfStacks(int cap) {
    capacity = cap;
    set = new Vector<LinkedList<Integer>>();
  }

  public void push(Integer v) {
    int stack = set.size()-1;
    if (stack < 0) {
      set.add(new LinkedList<Integer>());
      stack = 0;
    }
    LinkedList<Integer> s = set.get(stack);
    if (s.size() == capacity) {
      s = new LinkedList<Integer>();
      set.add(s);
    }
    s.addLast(v);
  }

  public Integer pop() {
    int stack = set.size()-1;
    if (stack < 0) throw new EmptyStackException();
    LinkedList<Integer> s = set.get(stack);
    Integer result = s.pollLast();
    while (s.size() == 0 && stack >= 0) {
      set.remove(s);
      stack--;
      if (stack >= 0) s = set.get(stack);
    }
    return result;
  }

  public Integer popAt(int index) {
    if (index >= set.size()) throw new IllegalArgumentException("Invalid index.");
    LinkedList<Integer> s = set.get(index);
    return s.pollLast();
  }
  
  public int countStacks() {
	  return set.size();
  }
  
  public void print() {
	  int size = set.size()-1;
	  if (size < 0) {
		  System.out.println("Stack is empty.");
		  return;
	  }
	  StringBuilder builder = new StringBuilder();
	  LinkedList<Integer> s = set.get(size);
	  
	  if (s.size() == 0) builder.append(String.format("Stack %d is empty.", size));
	  else builder.append(String.format("Stack %d: %d", size, s.pollLast()));
		  
	  while (size >= 0 || s.size() > 0) {
		  if (s.size() == 0) {
			  size--;
			  if (size >= 0) {
				  s = set.get(size);
				  if (s.size() > 0) builder.append(String.format("\nStack %d: %d", size, s.pollLast()));
				  else builder.append(String.format("\nStack %d is empty.", size));
			  }
		  } else builder.append("->").append(s.pollLast());
	  }
	  
	  System.out.println(builder.toString());
  }
  
  public static void main(String[] args) {
	  SetOfStacks stacks = new SetOfStacks(3);
	  stacks.push(4);
	  stacks.push(3);
	  stacks.push(8);
	  stacks.push(13);
	  if (stacks.countStacks() == 2) System.out.println("Capacity partitioning seems to work.");
	  if (stacks.popAt(0) == 8) System.out.println("Pop at functionality seems to work.");
	  if (stacks.pop() == 13) System.out.println("Pop seems to work");
	  if (stacks.countStacks() == 1) System.out.println("Set of stacks seems to squeeze properly.");
	  stacks.push(22);
	  stacks.push(18);
	  stacks.push(1);
	  stacks.push(7);
	  stacks.push(15);
	  stacks.popAt(1);
	  stacks.popAt(1);
	  stacks.popAt(1);
	  stacks.print();
  }
}
