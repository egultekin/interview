package commontypes;

public class BTNodeWithParentLink<T> {
	T val;
	BTNodeWithParentLink<T> left, right, parent;
	
	public BTNodeWithParentLink(T value) {
		this(value, null, null, null);
	}
	
	public BTNodeWithParentLink(T value, BTNodeWithParentLink<T> l, BTNodeWithParentLink<T> r, 
			BTNodeWithParentLink<T> p) {
		val = value;
		left = l;
		right = r;
		parent = p;
		if (null != left) left.setParent(this);
		if (null != right) right.setParent(this);
	}
	
	public BTNodeWithParentLink<T> l() { return this.left; }
	public BTNodeWithParentLink<T> r() { return this.right; }
	public BTNodeWithParentLink<T> p() { return this.parent; }
	public void setParent(BTNodeWithParentLink<T> p) { this.parent = p; }
	public T v() { return this.val; } 
	
	public int depth() {
		int res = 0;
		BTNodeWithParentLink<T> parent = this;
		while (parent != null) {
			res++;
			parent = parent.p();
		}
		return res;
	}
}
