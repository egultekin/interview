package brutefbacktrack;

import java.util.HashSet;
import java.util.Set;

public class MNS {
	private static final int SIZE = 9;
	
	class Candidate {
		public Candidate(int[] n) {
			this.numbers = n;
		}
		
		public boolean isMNS() {
			int width = (int)Math.floor(Math.sqrt(SIZE));
			int compareTo = this.rowSum(0);
			int row = 1, col = 0;
			boolean isMNS = true;
			while (row < width && col < width && isMNS) {
				if (row < width) if (compareTo == rowSum(row)) row++; else isMNS = false;
				if (col < width) if (compareTo == colSum(col)) col++; else isMNS = false;
			}
			
			return isMNS;
		}
		
		private int rowSum(int row) {
			int width = (int)Math.floor(Math.sqrt(SIZE));
			int sum = 0;
			for (int i = 0; i < width; i++) {
				sum += this.numbers[row*width+i];
			}
			return sum;
		}
		
		private int colSum(int col) {
			int width = (int)Math.floor(Math.sqrt(SIZE));
			int sum = 0;
			for (int i = 0; i < width; i++) {
				sum += this.numbers[i*width+col];
			}
			return sum;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Candidate)) return false;
			Candidate c = (Candidate) obj;
			for (int i = 0; i < SIZE; i++)
				if (c.numbers[i] != this.numbers[i]) return false;
			return true;
		}
		
		int[] numbers;
		
	}
	
	private int permute(int[] numbers, int swap) {
		for (int i = swap+1; i < numbers.length - 1; i++) {
			int temp = numbers[swap];
			numbers[swap] = numbers[i];
			numbers[i] = temp;
			
			Candidate c = new Candidate(numbers);
			if (c.isMNS() && !found.contains(c)) found.add(c);
			
		}
	}
	
	Set<Candidate> found = new HashSet<>();

	public int combos(int[] numbers) {
		return -1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
