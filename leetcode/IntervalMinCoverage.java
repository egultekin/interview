package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;
import java.util.List;

public class IntervalMinCoverage {
	static class Interval {
		int begin, end;
		public Interval(int b, int e) {
			begin = b;
			end = e;
		}
	}
	
	private static int minCoverage(Interval[] intervals, Interval coverInt, List<Interval> solutions) {
		Arrays.sort(intervals, getComparatorByBegin());
		int cover = coverInt.begin;
		int bInd = -1;
		int i = 0;
		while (cover <= coverInt.end && i < intervals.length) {
			Interval next = intervals[i];
			if (next.begin <= cover && next.end >= cover) {
				if (bInd < 0 || intervals[bInd].end < next.end)
					bInd = i;
				if (i == intervals.length-1) {
					cover = intervals[bInd].end+1;
					solutions.add(intervals[bInd]);
					bInd = -1;
				}
			} else if (next.begin > cover) {
				if (bInd < 0) return -1;
				cover = intervals[bInd].end+1;
				solutions.add(intervals[bInd]);
				bInd = -1;
				i--;
			}
			i++;
		}
		if (cover <= coverInt.end) return -1;
		return solutions.size();
	}
	
	public static int minCoverage(Interval[] intervals, Interval coverInt) {
		List<Interval> solutions = new Vector<Interval>();
		int res = minCoverage(intervals, coverInt, solutions);
		if (res > 0) {
			StringBuilder sb = new StringBuilder();
			Iterator<Interval> it = solutions.iterator();
			Interval interval = it.next();
			sb.append(String.format("[%d-%d]", interval.begin, interval.end));
			while (it.hasNext()) {
				interval = it.next();
				sb.append(',').append(String.format("[%d-%d]", interval.begin, interval.end));
			}
		
			System.out.println(sb.toString());
		}
		
		return res;
	}
	
	public static Comparator<Interval> getComparatorByBegin() {
		return new Comparator<Interval>() {
			@Override
			public int compare(Interval i1, Interval i2) {
				if (i1.begin < i2.begin) return -1;
				else if (i1.begin > i2.begin) return 1;
				return 0;
			}
		};
	}

	public static void main(String[] args) {
		Interval[] intervals = new Interval[] {
				new Interval(7, 12),
				new Interval(4,19),
				new Interval(20, 28),
				new Interval(3,5),
				new Interval(12,13),
				new Interval(3, 10),
				new Interval(15,7)
		};
		
		System.out.println(IntervalMinCoverage.minCoverage(intervals, new Interval(10, 23)));
		
		Interval[] intervals2 = new Interval[] {
				new Interval(12, 19),
				new Interval(10, 12),
				new Interval(20, 23),
				new Interval(15, 17),
				new Interval(12, 13),
				new Interval(10, 10),
				new Interval(3,5)
		};
		
		System.out.println(IntervalMinCoverage.minCoverage(intervals2, new Interval(10, 23)));
		
		Interval[] intervals3 = new Interval[] {
				new Interval(3, 6),
				new Interval(4, 5),
				new Interval(7, 10),
				new Interval(6, 9),
				new Interval(7, 12),
				new Interval(12, 17),
				new Interval(10,13),
				new Interval(18, 22),
				new Interval(16, 18)
		};
		
		System.out.println(IntervalMinCoverage.minCoverage(intervals3, new Interval(7, 22)));
	}

}
