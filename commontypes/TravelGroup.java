package commontypes;

import java.util.Arrays;

public class TravelGroup {

	private int buddy1, buddy2, size;
	
	public TravelGroup(int b1) {
		this.buddy1 = b1;
		this.buddy2 = b1;
		this.size = 1;
	}
	
	public TravelGroup(int b1, int b2) {
		this.buddy1 = b1;
		this.buddy2 = b2;
		this.size = 2;
	}
	
	public int faster() {
		return Math.min(this.buddy1, this.buddy2);
	}
	
	public int slower() {
		return Math.max(this.buddy1, this.buddy2);
	}
	
	public int size() {
		return this.size;
	}
	
	public int[] departs(int[] group) {
		if (null == group) return null;
		if (group.length <= this.size()) return null;
		
		int[] result = new int[group.length - this.size];
		int targetIndex = 0;
		boolean bf1 = true, bf2 = true;

		for (int i = 0; i < group.length; i++) {
			if (bf1 && group[i] == this.buddy1) bf1 = false;
			else if (this.size > 1 && bf2 && group[i] == this.buddy2) bf2 = false;
			else result[targetIndex++] = group[i];
		}
		
		return result;
	}
	
	public int[] arrives(int[] group) {
		int groupLength = 0;
		if (null != group) groupLength = group.length; 
		int[] result = new int[groupLength + this.size];
		int index = 0;
		if (index < this.size) result[index++] = this.buddy1;
		if (index < this.size) result[index++] = this.buddy2;

		if (null != group) {
			while (index < result.length) {
				result[index] = group[index-this.size];
				index++;
			}
		}
		return result;
	}
	
	
}
