package cci;

import commontypes.BTNodeWithParentLink;

public class FirstCommonAncestor {
	public static BTNodeWithParentLink<Integer> ancestor(
			BTNodeWithParentLink<Integer> a, 
			BTNodeWithParentLink<Integer> b) {
		int ad = a.depth();
		int bd = b.depth();
		BTNodeWithParentLink<Integer> ap = a.p(), bp = b.p();
		
		while (ad > bd) {
			ap = ap.p();
			ad--;
		}
		
		while (bd > ad) {
			bp = bp.p();
			bd--;
		}
		
		while (ap != bp) {
			ap = ap.p();
			bp = bp.p();
		}
		
		System.out.println(String.format("Common ancestor is labeled as %d.", ap.v()));
		return ap;
	}

	public static void main(String[] args) {
		BTNodeWithParentLink<Integer> n1 = new BTNodeWithParentLink<Integer>(1);
		BTNodeWithParentLink<Integer> n2 = new BTNodeWithParentLink<Integer>(2);
		BTNodeWithParentLink<Integer> n4 = new BTNodeWithParentLink<Integer>(4);
		BTNodeWithParentLink<Integer> n3 = new BTNodeWithParentLink<Integer>(3, n2, n4, null);
		BTNodeWithParentLink<Integer> n5 = new BTNodeWithParentLink<Integer>(5, n1, n3, null);
		BTNodeWithParentLink<Integer> n9 = new BTNodeWithParentLink<Integer>(9);
		BTNodeWithParentLink<Integer> n12 = new BTNodeWithParentLink<Integer>(12, n9, null, null);
		BTNodeWithParentLink<Integer> n8 = new BTNodeWithParentLink<Integer>(8, n5, n12, null);
		
		ancestor(n3, n9);
		
	}
	
}
