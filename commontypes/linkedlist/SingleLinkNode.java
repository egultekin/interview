package commontypes.linkedlist;

public class SingleLinkNode<T> {
	public T val;
	public SingleLinkNode<T> next;
	
	public SingleLinkNode(T v) {
		val = v;
		next = null;
	}
}
