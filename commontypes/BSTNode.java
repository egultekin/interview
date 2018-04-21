package commontypes;

public class BSTNode<T> {
	T val;
	BSTNode<T> left, right;
	
	public BSTNode(T value) {
		this(value, null, null);
	}
	
	public BSTNode(T value, BSTNode<T> l, BSTNode<T> r) {
		this.val = value;
		left = l;
		right = r;
	}
	
	public BSTNode<T> l() { return this.left; }
	public BSTNode<T> r() { return this.right; }
	public T value() { return this.val; }
}
