package cci;

public class LineIntersects {
	
	public static double[] intersects(int[] l11, int[] l12, int[] l21, int[] l22) {
		int a1 = l12[1]-l11[1];
		int b1 = l11[0]-l12[0];
		int a2 = l22[1]-l21[1];
		int b2 = l21[0]-l22[0];
		int c1 = a1*l11[0]+b1*l11[1];
		int c2 = a2*l21[0]+b2*l22[1];
		double det = a1*b2-a2*b1;
		
		if (det == 0)
			throw new RuntimeException("Lines do not intersect");
		
		double[] r = new double[2];
		r[0] = ((double)b2*c1-b1*c2)/det;
		r[1] = ((double)a1*c2-a2*c1)/det;
		
		return r;
	}

	public static void main(String[] args) {
		try {
			LineIntersects.intersects(new int[] {3,4}, new int[] {1,2}, new int[] {6,8}, new int[] {2,4});
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}

		double[] r = LineIntersects.intersects(new int[] {3,7}, new int[] {5,8}, new int[] {4, 6}, new int[] {4, 12});
		System.out.printf("Lines intersect at {%.2f,%.2f}\n", r[0], r[1]);
	}

}
