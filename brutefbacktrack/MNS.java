package brutefbacktrack;

import java.util.Arrays;

import java.util.Vector;

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
	
	private boolean contains(Candidate c) {
		for (int i = 0; i < found.size(); i++) if (c.equals(found.elementAt(i))) return true;
		return false;
	}
	
	private int permute(int[] numbers, int swap) {
		if (swap == numbers.length) {
			Candidate c = new Candidate(numbers);
			if (c.isMNS() && !contains(c)) found.add(c);
		} else {
			for (int i = swap; i < numbers.length; i++)			
			{	
				int[] newA = Arrays.copyOf(numbers, numbers.length);
				int temp = newA[swap];
				newA[swap] = newA[i];
				newA[i] = temp;

				permute(newA, swap+1);
			}
		}
		
		return found.size();
	}
	
	Vector<Candidate> found = new Vector<MNS.Candidate>();

	public int combos(int[] numbers) {
		return permute(numbers, 0);
	}
	
	public static void main(String[] args) {
		int[] numbers = 	
			{1, 1, 1, 1, 1, 1, 1, 1, 4};
		
		System.out.println(new MNS().combos(numbers));
	}

}
