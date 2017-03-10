package cci;

import java.util.LinkedList;

public class MinTree {

	static class Node {
		Node right, left;
		int val;
		
		public Node(int v) {
			val = v;
			right = left = null;
		}
	}
	
	public static Node minTree(int[] arr, int left, int right) {
		if (left > right) return null;
		int mid = (left+right)/2;
		if ((right-left+1)%2 == 0) mid++;
		Node node = new Node(arr[mid]);
		node.left = minTree(arr, left, mid-1);
		node.right = minTree(arr, mid+1, right);
		return node;
	}
	
	public static void print(Node root, int size) {
		if (null == root) return;
		LinkedList<Node> queue = new LinkedList<>();
		StringBuilder builder = new StringBuilder();
		int left = 0, level = 1;
		queue.add(root);
		left++;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			for (int i = 0; i < size*2/((level+1)*2); i++) builder.append(" ");
			builder.append(node.val);
			left--;
			if (null != node.left)	queue.add(node.left);
			if (null != node.right) queue.add(node.right);
			if (left == 0) {
				builder.append('\n');
				left = queue.size();
				if (queue.size() > 0) level*=2;
			}
		}

		System.out.println(builder.toString());
	}
	
	public static void main(String[] args) {
		int[] sample1 = {1, 10, 25, 62, 74, 89, 92, 98};
		MinTree.print(MinTree.minTree(sample1, 0, sample1.length-1), sample1.length);

		int[] sample2 = {98};
		MinTree.print(MinTree.minTree(sample2, 0, sample2.length-1), sample2.length);
		
		int[] sample3 = {67, 72};
		MinTree.print(MinTree.minTree(sample3, 0, sample3.length-1), sample3.length);
	}

}
