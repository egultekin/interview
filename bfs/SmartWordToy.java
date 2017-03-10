package bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class SmartWordToy {
	
	private static int LWORD = 4;
	HashSet<String> marked = new HashSet<String>();
	LinkedList<String> queue = new LinkedList<String>();
	
	public int minPresses(String start, String finish, String[] forbid) {
		int presses = 0;
		queue.addLast(start);
		marked.add(start);
		int countForIncrement = queue.size();
		
		while (!queue.isEmpty() && !start.equals(finish)) {
			start = queue.poll();
			countForIncrement--;

			if (valid(start, forbid)) {			
				for (String next : next(start)) {
					if (valid(next,forbid) && !marked.contains(next)) {
						queue.addLast(next);
						marked.add(next);
					}
				}
				
				if (countForIncrement == 0 &&  queue.size() > 0) {
					presses++;
					countForIncrement = queue.size();
				}
			}
		}
		
		if (start.equals(finish)) return presses;
		
		return -1;
	}
	
	private boolean valid(String check, String[] forbid) {
		return !violatesAny(check, forbid);
	}
	
	private Iterable<String> next(String current) {
		ArrayList<String> list = new ArrayList<String>();
		
		for (int i=0; i<LWORD; i++) {
			char c = current.charAt(i);
			
			// previous
			if (c-1 < 'a') list.add(replaceCharAt(current, i, 'z'));
			else list.add(replaceCharAt(current, i, (char) (c-1)));
			
			// next
			if (c + 1 > 'z') list.add(replaceCharAt(current, i, 'a'));
			else list.add(replaceCharAt(current, i, (char) (c+1)));
		}
		
		return list;
	}
	
	private String replaceCharAt(String str, int index, char replaceWith) {
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < LWORD; i++) {
			if (i == index) result.append(replaceWith);
			else result.append(str.charAt(i));
		}
		
		return result.toString();
	}
	
	private boolean violates(String check, String forbidden) {
		String[] split = forbidden.split(" ");
		for (int i=0; i<LWORD; i++) {
			if (!split[i].contains(check.subSequence(i, i+1))) return false;
		}
		
		return true;
	}
	
	private boolean violatesAny(String check, String[] forbiddenList) {
		for (int i=0; i < forbiddenList.length; i++)
			if (violates(check, forbiddenList[i])) return true;
		return false;
	}

	public static void main(String[] args) {
		String start = "aaaa";
		String finish = "mmnn";
		
		String[] forbid = {

				 };
		
		System.out.println(new SmartWordToy().minPresses(start, finish, forbid));

	}

}
