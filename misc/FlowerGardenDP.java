package interview;

import java.util.Arrays;

public class FlowerGardenDP {
	
	private final static int INITIAL_SIZE = 1;
	private Component[] components;
	private int size;
	private int capacity;
	
	class Component {
		int bloom;
		int wilt;
		int size;
		int capacity;
		
		int[] parts;
		
		public Component(int bloom, int wilt, int height) {
			this.size = 0;
			this.capacity = INITIAL_SIZE;
			this.parts = new int[this.capacity];
			this.bloom = Integer.MAX_VALUE;
			this.wilt = Integer.MIN_VALUE;
			this.add(bloom, wilt, height);
		}
		
		public boolean isEligible(int bloom, int wilt) {
			if ((bloom >= this.bloom && bloom <= this.wilt) 
					|| (wilt <= this.wilt && wilt >= this.bloom)
					|| (bloom <= this.bloom && wilt >= this.wilt))
				return true;
			return false;
		}
		
		public void add(int bloom, int wilt, int height) {
			// insert into proper place in parts based on ascending height values
			if (this.size == 0) parts[0] = height;
			else {
				if (this.size == this.capacity) this.resize();
				int lo = 0, hi = this.size;
				while (hi > lo) {
					int mid = lo + (hi - lo) / 2;
					if (height > parts[mid]) lo = mid+1;
					else hi = mid;
				}
				
				this.insert(lo, height);
			}
			this.size++;
			
			this.bloom = min(bloom, this.bloom);
			this.wilt = max(wilt, this.wilt);
		}
		
		public int tallest() {
			if (this.size > 0) return this.parts[this.size-1];
			return Integer.MIN_VALUE;
		}
		
		public int shortest() {
			if (this.size > 0) return this.parts[0];
			return Integer.MAX_VALUE;
		}
		
		public void copyTo(int[] arr, int start) {
			for (int i=0; i<size; i++) arr[start++] = parts[i];
		}
		
		public int size() {
			return size;
		}
		
		private int min(int first, int second) {
			if (first < second) return first;
			return second;
		}
		
		private int max(int first, int second) {
			if (first > second) return first;
			return second;
		}
		
		private void resize() {
			this.capacity*=2;
			parts = Arrays.copyOf(parts, this.capacity);
		}
		
		private void insert(int index, int height) {
			if (index < 0 || index > this.size) throw new IllegalArgumentException("Index out of bounds while inserting height to component.");
			for (int i=this.size; i > index; i--) parts[i] = parts[i-1];
			parts[index] = height;
		}
	}
	
	public FlowerGardenDP() {
		capacity = INITIAL_SIZE;
		components = new Component[capacity];
		size = 0;
	}
	
	public void addComponent(Component component) {
		if (size == 0) components[0] = component;
		else {
			if (size == capacity) resize();
			int lo = 0, hi = size;
			while (hi > lo) {
				int mid = lo + (hi - lo) / 2;
				if (component.tallest() > components[mid].tallest()) hi = mid;
				else lo = mid+1;
			}
			
			insert(lo, component);
		}
		size++;
	}
	
	public int[] arrange(int[] bloom, int[] wilt, int[] height) {
		int[] result = new int[bloom.length];
		for (int i=0; i < bloom.length; i++) {
			if (size == 0) addComponent(new Component(bloom[i], wilt[i], height[i]));
			else {
				boolean spared = false;
				for (int j=0; j < size; j++) {
					if (components[j].isEligible(bloom[i], wilt[i])) {
						components[j].add(bloom[i], wilt[i], height[i]);
						spared = true;
						break;
					}
				}
				
				if (!spared) 
					addComponent(new Component(bloom[i], wilt[i], height[i]));
			}
		}
		
		int copyStartingAt=0;
		for (int i=0; i < size; i++) {
			components[i].copyTo(result, copyStartingAt);
			copyStartingAt+=components[i].size();
		}
		
		return result;
	}
	
	private void resize() {
		capacity*=2;
		components = Arrays.copyOf(components, capacity);
	}
	
	private void insert(int index, Component component) {
		if (index < 0 || index > size) throw new IllegalArgumentException("Index out of bounds while inserting component.");
		for (int i=size; i > index; i--) components[i] = components[i-1];
		components[index] = component;
	}

	public static void main(String[] args) {
		int[] bloom = new int[] {
			//1, 2, 11, 10
			//1, 3, 1, 3, 1, 3
			//1, 5, 10, 15, 20
			//1, 5, 10, 15, 20
			//1, 5, 10, 15, 20
			1, 1, 1, 1, 1
		};
		
		int[] wilt = new int[] {
			//4, 3, 12, 13
			//2, 4 ,2, 4, 2, 4
			//5, 10, 14, 20, 25
			//5, 10, 15, 20, 25
			//4, 9, 14, 19, 24
			365, 365, 365, 365, 365
		};
		
		int[] height = new int[] {
			//3, 2, 5, 4
			//1, 2, 3, 4, 5, 6
			//5, 4, 3, 2, 1
			//5, 4, 3, 2, 1
			5, 4, 3, 2, 1
		};
		
		FlowerGardenDP sol = new FlowerGardenDP();
		int[] result = sol.arrange(bloom, wilt, height);
		
		StringBuilder str = new StringBuilder();
		for (int i=0; i<result.length; i++) {
			str.append(result[i]);
			if (i < result.length-1) str.append(",");
		}

		System.out.format("Result is {%s}", str.toString());
	}

}