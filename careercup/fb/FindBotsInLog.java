package careercup.fb;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class FindBotsInLog {
	
	static class Log {
		String id;
		int time;
		
		public Log(String i, int t) {
			id = i;
			time = t;
		}
	}
	
	public HashSet<String> getBots(Log[] logs, int m, int n) {
		int st = logs[logs.length-1].time-n+1;
		int s = 0;
		while (logs[s].time < st) s++;
		int t = n+1;
		HashMap<String, int[]> map = new HashMap<>();
		for (int i=s; i < logs.length; i++) {
			Log l = logs[i];
			if (!map.containsKey(l.id)) map.put(l.id, new int[t]);
			int[] a = map.get(l.id);
			a[l.time-logs[s].time]++;
		}
		HashSet<String> res = new HashSet<>();
		Iterator<String> keys = map.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			if (isBot(map.get(key), n, m)) res.add(key);
		}
		return res;
		
	}
	
	public static void print(Iterator<String> it) {
		StringBuilder sb = new StringBuilder();
		if (it.hasNext()) sb.append(it.next());
		else sb.append("No bots.\n");
		while (it.hasNext()) sb.append(',').append(it.next());
		System.out.println(sb.toString());
	}
	
	private boolean isBot(int[] a, int n, int m) {
		int sum = 0;
		int l = a.length;
		for (int i=0; i < l; i++)
			if (i < n) sum = sum+a[i];
			else {
				if (sum >= m) return true;
				sum = sum-a[i-n]+a[i];
			}
		return false;
	}

	public static void main(String[] args) {
		Log[] logs = new Log[] {
				new Log("a", 5),
				new Log("a", 5),
				new Log("a", 5),
				new Log("b", 6),
				new Log("b", 7),
				new Log("c", 7),
				new Log("c", 8),
				new Log("c", 8),
				new Log("c", 8),
				new Log("a", 10)
		};
		
		FindBotsInLog fbl = new FindBotsInLog();

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		while (N-- > 0) {
			int m = sc.nextInt();
			int n = sc.nextInt();
			FindBotsInLog.print(fbl.getBots(logs, m, n).iterator());
		}
		sc.close();
	}

}
