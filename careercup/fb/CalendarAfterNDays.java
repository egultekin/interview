package careercup.fb;

import java.util.Scanner;

public class CalendarAfterNDays {
	
	static class Calendar {
		int day, month, year;
		public Calendar(int y, int m, int d) {
			year = y;
			month = m;
			day = d;
		}
	}
	
	static final int[] limits = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	static final int leapYear = 1900;
	
	private static boolean isLeapYear(int year) {
		boolean leap = (year%100 > 0) || year%400 == 0;
		return leap&&year%4==0;
	}
	
	public static Calendar daysAfter(Calendar cal, int n) {
		Calendar res = new Calendar(cal.year, cal.month, cal.day);
		while (n > 0) {
			int day = res.day;
			int month = res.month;
			int year = res.year;
			int bound = limits[month];
			if (isLeapYear(year) && month == 2) bound = 29;
			if (day + n > bound) {
				res.month = (month == 12) ? 1 : month+1;
				res.day = 1;
				res.year = (month == 12) ? year+1 : year;
				n = n-bound+day-1;
			} else {
				res.day = day+n;
				n = 0;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int year = sc.nextInt();
		int month = sc.nextInt();
		int day = sc.nextInt();
		Calendar cal = new Calendar(year, month, day);
		while (t-- > 0) {
			Calendar after = daysAfter(cal, sc.nextInt());
			System.out.printf("%d-%d-%d\n", after.year, after.month, after.day);
		}
		sc.close();
	}

}
