package dp;
public class SchoolAssembly {
	// bucket[color]
	int[] bucket;
	private static final int PACK_SIZE = 20;
	private static final int COLORS = 5;
	
	public int getBeans(int kids, int quantity) {
		bucket = new int[] { 1, 1, 1, 1, 1 };
		int pack = ((quantity - 1) * COLORS + 19) / 20;
		int remaining = pack * 20 - (quantity-1) * COLORS;

		while(kids > 0) {
			if (remaining == 0) {
				pack++;
				remaining=PACK_SIZE;
			}
			
			if (bucket[0] == 0) {
				kids--;
				bucket[0] = quantity;
			}
			else {
				bucket[0]--;
				remaining--;
			}
		}
		
		return pack;
	}
			
	public static void main(String[] args) {
		int kids = 999;
		int quantity = 25;
		System.out.format("Kids: %d Quantity: %d Result: %d", kids, quantity, new SchoolAssembly().getBeans(kids, quantity));
	}

}
