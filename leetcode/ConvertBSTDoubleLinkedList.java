package leetcode;

public class ConvertBSTDoubleLinkedList {

	static class Node {
		Node left, right;
		int v;
		
		public Node(int val) {
			v = val;
			left = right = null;
		}
		
		public void print() {
			StringBuilder sb = new StringBuilder();
			sb.append(v);
			Node r = right;
			Node l = this;
			while (null != r) {
				if (r.left == l) sb.append('<');
				sb.append("->").append(r.v);
				l = r;
				r = r.right;
			}
			System.out.println(sb.toString());
		}
	}
	
	private Node convert(Node bst, boolean head) {
		Node h = bst, t = bst;
		if (null != bst.left) h = convert(bst.left, false);
		if (h != bst) {
			h.right = bst;
			bst.left = h;
		}
		if (null != bst.right) t = convert(bst.right, true);
		if (t != bst) {
			t.left = bst;
			bst.right = t;
		}
		if (head) return h;
		return t;
	}
	
	public Node convert(Node bst) {
		if (null == bst) return null;
		Node res = convert(bst, true);
		while (res.left != null) res = res.left;
		return res;
	}
	
	public static void main(String[] args) {
		Node r = new Node(8);
		r.left = new Node(4);
		r.left.left = new Node(2);
		r.left.right = new Node(6);
		r.right = new Node(12);
		ConvertBSTDoubleLinkedList c = new ConvertBSTDoubleLinkedList();
		Node conv = c.convert(r);
		//while (conv.left != null) conv = conv.left;
		if (null == conv) System.out.println("Empty tree.");
		conv.print();

	}

}
