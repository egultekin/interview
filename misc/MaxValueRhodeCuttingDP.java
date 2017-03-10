package misc;

public class MaxValueRhodeCuttingDP {

	
	public MaxValueRhodeCuttingDP() {
		// TODO Auto-generated constructor stub
	}
	
	public int maxValueCut(int[] cutLength, int[] cutValue, int length) {
		if (null == cutLength || null == cutValue || length <= 0 || cutLength.length != cutValue.length)
			throw new IllegalArgumentException("Invalid parameters.");
		
		int[] solution = new int[length+1];
		
		for (int i=0; i < length+1; i++) solution[i] = 0;
		for (int i=0; i < cutLength.length; i++) solution[cutLength[i]] = cutValue[i];
		for (int sum=2; sum <= length; sum++)
			for (int i=0; i < cutLength.length; i++)
				if (sum >= cutLength[i] && solution[sum] < solution[sum-cutLength[i]] + cutValue[i])
					solution[sum] = solution[sum-cutLength[i]] + cutValue[i];
		
		return solution[length];
	}

	public static void main(String[] args) {
		int[] cutLength = new int[] { 1, 9, 2, 3, 5, 7};
		int[] cutValue = new int[] { 1, 2, 3, 4, 5, 6 };
		int length = 20;

		MaxValueRhodeCuttingDP s = new MaxValueRhodeCuttingDP();
		System.out.format("Max value collectable cutting a rhode of length %d is %d.", 
				length, s.maxValueCut(cutLength, cutValue, length));
	}

}
