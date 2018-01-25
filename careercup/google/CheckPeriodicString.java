import java.util.PriorityQueue;
import java.util.Comparator;

class CheckPeriodicString {
	static class Interval {
		int l, r, d;

		public Interval(int left, int right) {
			l = left;
			r = right;
			d = right-left+1;
		}
	}

	public static boolean isPeriodic(String str) {
		Comparator<Interval> cmp = new Comparator<Interval>() {
				@Override
				public int compare(Interval a, Interval b) {
					if (b.d > a.d) return 1;
					else if (a.d > b.d) return -1;
					return 0;
				}
			};

		PriorityQueue<Interval> leftB = new PriorityQueue<>(cmp);
		PriorityQueue<Interval> rightB = new PriorityQueue<>(cmp);


		int len = str.length();

		for (int i=0; i<len; i++) {
			if (str.charAt(i) == str.charAt(0) && i != 0) rightB.add(new Interval(i, len-1));
			if (str.charAt(i) == str.charAt(len-1) && i != len-1) leftB.add(new Interval(0, i));
		}

		while (!leftB.isEmpty() && !rightB.isEmpty()) {
			Interval pLeft = leftB.peek();
			Interval pRight = rightB.peek();
			System.out.printf("(%d, %d) length: %d with (%d, %d) length: %d\n", pLeft.l, pLeft.r, pLeft.d, pRight.l, pRight.r, pRight.d);
			if (pLeft.d > pRight.d) leftB.poll();
			else if (pRight.d > pLeft.d) rightB.poll();
			else if (isPeriodic(str, str.substring(pLeft.l, pLeft.r+1))) {
				System.out.printf("%s is periodic using %s several times.\n", str, str.substring(pLeft.l, pLeft.r+1));
				return true;
			} else {
				leftB.poll();
				rightB.poll();
			}
		}

		return false;
	}

	private static boolean isPeriodic(String str, String cmp) {
		if (str.length() == cmp.length() && str.equals(cmp)) return true;
		if (str.length() % cmp.length() != 0) return false;
		return str.substring(0, cmp.length()).equals(cmp) && isPeriodic(str.substring(cmp.length()), cmp);
	}

	public static void main(String[] args) {
		isPeriodic("ababab");
		isPeriodic("xxxxx");
		isPeriodic("aabbaaabba");
	}
}