package cci;

import java.util.Stack;

public class SortStack {
	
	public static Stack<Integer> sortAscending(Stack<Integer> stack) {
		Stack<Integer> tempStack = new Stack<>();
		while (!stack.isEmpty()) {
			int pick = stack.pop();
			while (!tempStack.isEmpty() && tempStack.peek() > pick) stack.push(tempStack.pop());
			tempStack.push(pick);
		}
		
		while (!tempStack.isEmpty()) stack.push(tempStack.pop());
		return stack;
	}
	
	public static Stack<Integer> random(int length, int bound) {
		Stack<Integer> stack = new Stack<>();
		while (length-- > 0) stack.push((int)Math.floor(Math.random()*bound));
		return stack;
	}
	
	public static void print(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			System.out.println("Empty.");
			return;
		}
		Stack<Integer> temp = new Stack<>();
		StringBuilder builder = new StringBuilder();
		temp.push(stack.pop());
		builder.append(temp.peek());
		while(!stack.isEmpty()) {
			int val = stack.pop();
			builder.append("-").append(val);
			temp.push(val);
		}
		
		while(!temp.isEmpty()) stack.push(temp.pop());
		System.out.println(builder.toString());
	}

	public static void main(String[] args) {
		Stack<Integer> stack = SortStack.random(10, 100);
		SortStack.print(stack);
		SortStack.sortAscending(stack);
		SortStack.print(stack);
	}

}
