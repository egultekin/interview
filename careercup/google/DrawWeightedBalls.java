import java.util.Arrays;

class DrawWeightedBalls {
	public static int draw(int[] weightArr) {
		//int sum = 0;
		//for (int i=0; i<weightArr.length; i++) sum += weightArr[i];
		double probPicked = Math.pow(Math.random(), (double)1/weightArr[0]);
		int picked = 0;
		for (int i=1; i<weightArr.length; i++) {
			double probCalc = Math.pow((double)Math.random(), (double)1/weightArr[i]);
			if (probCalc > probPicked) {
				probPicked = probCalc;
				picked = i;
			}
		}

		System.out.printf("Picked ball with id %d that has an initial weight of %.2f\n", picked, (double)1/weightArr[picked]);
		return  picked;
	}

	public static void main(String[] args) {
		int[] arr = new int[] {5, 10, 35};
		int[] c = new int[arr.length];
		Arrays.fill(c, 0);
		for (int i=0; i < 100; i++) {
			c[draw(arr)]++;
		}
		
		for (int i=0; i<c.length; i++)
			System.out.printf("Number %d is picked %d times\n", i, c[i]);
	}
}