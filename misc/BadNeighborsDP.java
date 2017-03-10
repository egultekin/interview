package misc;

public class BadNeighborsDP {
	private int[] maxDonations;
	
	public int maxDonations(int[] donations) {
		if (null == donations || donations.length < 2) return 0;
		
		maxDonations = new int[donations.length];
		maxDonations[0] = donations[0];
		maxDonations[1] = donations[1];
		if (donations.length < 3) return max(maxDonations[0], maxDonations[1]);
		for (int i=2; i < donations.length-1; i++)
			for (int j=0; j < i-1; j++) {
				maxDonations[i] = max(maxDonations[i], maxDonations[j]+donations[i]);
			}
		
		return max(maxDonations[donations.length-2], maxDonations[donations.length-3]-donations[0]+donations[donations.length-1]);
	}
	
	private int max(int i, int j) {
		if (i > j) return i;
		return j;
	}

	public static void main(String[] args) {
		int[] donations1 = new int[] {
			10, 3, 2, 5, 7, 8
		};

		int[] donations2 = new int[] {
			11, 15
		};

		int[] donations3 = new int[] {
			7, 7, 7, 7, 7, 7, 7
		};

		int[] donations4 = new int[] {
			1, 2, 3, 4, 5, 1, 2, 3, 4, 5
		};

		int[] donations5 = new int[] {
				94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,  
				  6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
				  52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72
		};
		
		BadNeighborsDP sol = new BadNeighborsDP();
		System.out.format("Maximum donations collectable for input 1 is %d. \n", sol.maxDonations(donations1));
		System.out.format("Maximum donations collectable for input 1 is %d. \n", sol.maxDonations(donations2));
		System.out.format("Maximum donations collectable for input 1 is %d. \n", sol.maxDonations(donations3));
		System.out.format("Maximum donations collectable for input 1 is %d. \n", sol.maxDonations(donations4));
		System.out.format("Maximum donations collectable for input 1 is %d. \n", sol.maxDonations(donations5));
	}

}
