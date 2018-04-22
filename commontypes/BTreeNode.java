package commontypes;

public class BTreeNode<T> {
	private T val;
	private BTreeNode<T> left, right;
	
	public BTreeNode(T value, BTreeNode<T> l, BTreeNode<T> r) {
		this.val = value;
		this.left = l;
		this.right = r;
	}
	
	public BTreeNode(T value) {
		this(value, null, null);
	}
	
	public T value() { return this.val; }
	public BTreeNode<T> l() { return this.left; }
	public BTreeNode<T> r() { return this.right; }
}
