package careercup.google;

public class BinaryTreePrintRelativePath {
	static class Node {
		Node left, right;
		char val;
		
		public Node(char c) {
			val = c;
			left = null;
			right = null;
		}
	}

	public static final String SEPARATOR = ",";
	
	public static void print(String path, int min) {
		String[] tokens = path.split(SEPARATOR);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < tokens.length; i++) {
			char val = tokens[i].charAt(0);
			int index = Integer.parseInt(tokens[i].substring(1));
			for (int j = 0; j < index-min; j++) builder.append("_");
			builder.append(val).append("\n");
		}
		
		System.out.print(builder.toString());
	}
	
	public static void printPath(Node root, String path, int min, int index) {
		if (null == root) return;
		if (min > index) min = index;
		String str = (path.length() == 0) ?
				String.format("%c%d", root.val, index)
				: String.format("%s,%c%d", path, root.val, index);

		if (null == root.left && null == root.right) print(str, min);
		printPath(root.left, str, min, index-1);
		printPath(root.right, str, min, index+1);
	}
	
	public static void main(String[] args) {
		Node a = new Node('A');
		Node b = new Node('B');
		Node c = new Node('C');
		Node d = new Node('D');
		Node e = new Node('E');
		Node f = new Node('F');
		Node g = new Node('G');

		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		a.left = b;
		a.right = c;
		printPath(a, "", Integer.MAX_VALUE, 0);
	}

}
